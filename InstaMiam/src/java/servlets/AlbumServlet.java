package servlets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modeles.Album;
import modeles.Notification;
import modeles.Photo;

/**
 * Servlet s'occupant de l'affichage d'un album
 */
@MultipartConfig
@WebServlet(name = "AlbumServlet", urlPatterns = {"/Album"})
public class AlbumServlet extends SuperServletVerification {
    
    private static long lastCheck = System.currentTimeMillis();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        if (isAlreadyForwarded)
            return;
        
        String forwardTo = "album.jsp";
        
        // Si l'id de l'album n'est pas passé en paramètre, on redirige vers 404
        int idAlbum;
        if (request.getParameter("idAlbum") != null) {
            idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
            
            // Si l'id est invalide, 404 error
            if (gestionnaire.getAlbumById(idAlbum) == null) {
                dispatch404Error(request, response);
                return;   
            }
            
            request.setAttribute("idAlbum", idAlbum);
        }
        else {
            dispatch404Error(request, response);
            return;
        }
        
        // On récupere l'id de l'utilisateur connecté
        HttpSession session = request.getSession();
        int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));

         // On recupère l'album à afficher
        Album albumAAfficher = gestionnaire.getAlbumById(idAlbum);

        // On verifie que l'utilisateur a bien le droit d'afficher cet album
        if (albumAAfficher.getTypePartage() == Album.ALBUM_PRIVE
                && albumAAfficher.getUtilisateur().getId() != idUtilisateur
                && !albumAAfficher.getUtilisateursPartages().contains(gestionnaire.getUtilisateurById(idUtilisateur))) {
            request.setAttribute("messageErreur", "Vous n'avez pas les droits pour acceder à cet album !");
            dispatch404Error(request, response);
            return;
        }
        
        String action = request.getParameter("action");
        if (action != null) {
            // Action l'ors de l'ajout d'un fichier dans la dropzone
            if (action.equals("uploadFile")) {
                Object idTransaction = request.getParameter("idTransaction");
                if (idTransaction != null) {
                    enregistrerFichier(request, idTransaction.toString());
                }
            } // Action lors de la suppression d'un fichier de la dropzone
            else if (action.equals("removeFile")) {
                Object nomFichierObject = request.getParameter("nameFile");
                Object idTransaction = request.getParameter("idTransaction");

                if (nomFichierObject != null && idTransaction != null) {
                    removeFileTransaction(nomFichierObject.toString(), idTransaction.toString());
                }
            } // Action lors d'une validation d'ajout de toutes les photos
            else if (action.equals("validUpload")) {
                Object idTransaction = request.getParameter("idTransaction");

                if (idTransaction != null) {
                    // Si l'utilisateur peut modifier l'album
                    if (isUtilisateurAbleToModifyAlbum(idUtilisateur, idAlbum)){
                        confirmerUploadPhotos(idAlbum, idTransaction.toString());
                        gestionnaire.notifierUtilisateursPartages(idAlbum, idUtilisateur, Notification.NOTIFICATION_AJOUT_PHOTO_ALBUM);
                    }
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("ajouterCommentaire")) {
                String text = request.getParameter("commentaire");
                if (text != null) {
                    gestionnaire.ajouterCommentaireAlbum(idAlbum, idUtilisateur, text);
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("supprimerAlbum")) {
                // Si l'utilisateur peut modifier l'album
                if (isUtilisateurAbleToModifyAlbum(idUtilisateur, idAlbum)) {
                    gestionnaire.supprimerAlbum(idAlbum);

                    forwardTo = "/ListeAlbums";
                    RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                    dp.forward(request, response);
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("modifierAlbum")) {
                String nomAlbum = request.getParameter("nomAlbum");
                if (nomAlbum != null && isUtilisateurAbleToModifyAlbum(idUtilisateur, idAlbum)) {
                    gestionnaire.modifierAlbum(idAlbum, nomAlbum);
                }
                
            } //Partage de l'album
            else if (action.equals("partagerAlbum") || action.equals("supprimerPartage")) {

                // Si l'utilisateur peut modifier l'album
                if (isUtilisateurAbleToModifyAlbum(idUtilisateur, idAlbum)) {
                    int idUtilisateurPartage;
                    Album a;

                    try {
                        idUtilisateurPartage = Integer.parseInt(request.getParameter("idUtilisateur"));
                    } catch (NumberFormatException e) {
                        response.setStatus(500);
                        return;
                    }
                    switch (action) {
                        case "partagerAlbum":
                            if (!gestionnaire.partagerAlbum(idAlbum, idUtilisateurPartage, idUtilisateur)) {
                                response.setStatus(500);
                                return;
                            }   break;
                        case "supprimerPartage":
                            if (!gestionnaire.supprimerPartage(idAlbum, idUtilisateurPartage)) {
                                response.setStatus(500);
                                return;
                            }   break;
                    }

                    a = gestionnaire.getAlbumById(idAlbum);
                    request.setAttribute("listeUtilisateursPartages", a.getUtilisateursPartages());

                    forwardTo = "/listeUtilisateursPartages.jsp";
                    RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                    dp.forward(request, response);
                    return;
                }
            }
        }

        // On recupère l'album à afficher mis à jour
        albumAAfficher = gestionnaire.getAlbumById(idAlbum);
        
        request.setAttribute("album", albumAAfficher);

        // Ajout d'un identifiant unique pour la transaction d'ajout de photos
        UUID idTransaction = UUID.randomUUID();
        request.setAttribute("idTransaction", idTransaction.toString());

        // On nettoie le fichier tmp
        nettoyerTMP();

        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
    }

    /**
     * Méthode permettant d'enregistrer un fichier uploadé lors d'une
     * transaction avec la dropzone.
     *
     * @param request
     * @param idTransaction
     * @throws ServletException
     * @throws IOException
     */
    private void enregistrerFichier(HttpServletRequest request, String idTransaction) throws ServletException, IOException {
        // On créer le repertoire temporaire de stockage
        final String path = getServletConfig().getServletContext().getRealPath("/") + File.separator + "tmp" + File.separator + idTransaction;
        File dir = new File(path);
        dir.mkdirs();

        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        // On récupere le contenu du fichier et on le sauvegarde sur le serveur
        try {
            out = new FileOutputStream(new File(path + File.separator +  fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println("Nouveau fichier : " + new File(path + File.separator + fileName).getAbsolutePath());

        } catch (FileNotFoundException fne) {
            System.out.println("Exception dans l'upload du fichier.\n" + fne.getMessage());
        } // On ferme les streams
        finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }

    /**
     * Méthode permettant de récuperer le nom du fichier qu'on upload
     *
     * @param part
     * @return
     */
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    /**
     * Méthode permettant de supprimer un fichier d'une transaction
     *
     * @param nom
     * @param idTransaction
     */
    private void removeFileTransaction(String nom, String idTransaction) {
        String cheminFichier = getServletConfig().getServletContext().getRealPath("/") + "tmp" + File.separator + idTransaction + File.separator + nom;
        File file = new File(cheminFichier);
        file.delete();
    }

    /**
     * Méthode utilisé pour la confirmation d'upload de toutes les photos de la
     * transaction.
     *
     * @param idAlbum
     * @param idTransaction
     */
    private void confirmerUploadPhotos(int idAlbum, String idTransaction) {
        // On va parcourir les photos présentes dans le fichier tmp de la transaction
        String path = getServletConfig().getServletContext().getRealPath("/") + File.separator + "tmp" + File.separator + idTransaction;
        File transacRep = new File(path);

        File[] listeImage = transacRep.listFiles();
        if (listeImage == null) {
            return;
        }
        for (File image : listeImage) {
            // On crée la photo en base de données et on l'ajoute à l'album
            Album a = gestionnaire.getAlbumById(idAlbum);
            Photo photo = gestionnaire.creerPhoto(image.getName(), idAlbum);

            // On va déplacer les images dans le dossier de l'album
            String nouveauRepertoire = getServletConfig().getServletContext().getRealPath("/")
                    + File.separator + "albums" + File.separator + a.getIdUnique() + File.separator;

            File nouveauFichier = new File(nouveauRepertoire + photo.getIdUnique() + photo.getNom().substring(photo.getNom().indexOf(".")));

            // On créer le repertoire de l'album si il n'est pas déjà crée
            new File(nouveauRepertoire).mkdirs();

            // On déplace l'image
            image.renameTo(nouveauFichier);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * méthode permettant de nettoyer les fichiers temporaires de plus de 10
     * minutes
     */
    private void nettoyerTMP() {
        // Si la dernière modification a été faite il y a plus de 1h
        if (System.currentTimeMillis() - lastCheck >= 3600000) {
            // On vide le TMP et on remet la date de derniere verification à maintenant
            lastCheck = System.currentTimeMillis();
        }
        else {
            // Sinon on ne fait rien
            return;
        }
        
        // On va parcourir les photos présentes dans le fichier tmp de la transaction
        String path = getServletConfig().getServletContext().getRealPath("/") + File.separator + "tmp";
        File transacRep = new File(path);

        File[] listeImage = transacRep.listFiles();
        if (listeImage == null) {
            return;
        }

        // On va comparer la date de dernière modification des répertoires avec l'heure actuelle moins une heure
        Calendar heureActuelleMoins1Heures = Calendar.getInstance();
        heureActuelleMoins1Heures.set(Calendar.HOUR_OF_DAY, heureActuelleMoins1Heures.get(Calendar.HOUR_OF_DAY) - 1);
        
        Calendar lastModification = Calendar.getInstance();

        for (File folderTransaction : listeImage) {
            lastModification.setTimeInMillis(folderTransaction.lastModified());

            // Si la derniere modification est supérieur à 1h, on delete le repertoire et son contenue
            if (lastModification.before(heureActuelleMoins1Heures)) {
                for (File image : folderTransaction.listFiles())
                    image.delete();

                folderTransaction.delete();
            }
        }
    }

    /**
     * Verification si l'utilisateur a les droits de modification de l'album (si il le possède)
     * @param idUtilisateur
     * @param idAlbum
     * @return 
     */
    private boolean isUtilisateurAbleToModifyAlbum(int idUtilisateur, int idAlbum) {
         return gestionnaire.getUtilisateurById(idUtilisateur).getAlbums().contains(gestionnaire.getAlbumById(idAlbum));
    }

}
