/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import gestionnaires.GestionnaireUtilisateurs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modeles.Album;
import modeles.Photo;
import modeles.Utilisateur;

/**
 *
 * @author Siddi Steven
 */
@MultipartConfig
@WebServlet(name = "AlbumServlet", urlPatterns = {"/Album"})
public class AlbumServlet extends HttpServlet {

    @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String forwardTo = "album.jsp";

        // TODO ajouter une redirection 404 si ID pas bon
        int idAlbum = -1;
        if (request.getParameter("idAlbum") != null) {
            idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
            request.setAttribute("idAlbum", idAlbum);
        }

        // On récupere l'id de l'utilisateur connecté
        HttpSession session = request.getSession();
        int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));

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

                if (idTransaction != null && idAlbum > 0) {
                    confirmerUploadPhotos(idAlbum, idTransaction.toString());
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("ajouterCommentaire")) {
                String text = request.getParameter("commentaire");
                if (idAlbum > 0 && text != null) {
                    gestionnaireUtilisateurs.ajouterCommentaireAlbum(idAlbum, idUtilisateur, text);
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("supprimerAlbum")) {
                if (idAlbum > 0) {
                    gestionnaireUtilisateurs.supprimerAlbum(idAlbum);

                    forwardTo = "/ListeAlbums";
                    RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                    dp.forward(request, response);
                }
            } // Action lors d'un ajout de commentaire
            else if (action.equals("modifierAlbum")) {
                if (idAlbum > 0) {
                    String nomAlbum = request.getParameter("nomAlbum");
                    if (nomAlbum != null) {
                        gestionnaireUtilisateurs.modifierAlbum(idAlbum, nomAlbum);
                    }
                }
            } //Partage de l'album
            else if (action.equals("partagerAlbum")) {

                int idUtilisateurPartage;
                try {
                    idUtilisateurPartage = Integer.parseInt(request.getParameter("idUtilisateur"));
                } catch (NumberFormatException e) {
                    response.setStatus(500);
                    return;
                }
                if (gestionnaireUtilisateurs.partagerAlbum(idAlbum, idUtilisateurPartage)) {
                    Album a = gestionnaireUtilisateurs.getAlbumById(idAlbum);

                    request.setAttribute("listeUtilisateursPartages", a.getUtilisateursPartages());

                    forwardTo = "/listeUtilisateursPartages.jsp";
                    RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                    dp.forward(request, response);
                    return;
                } else {
                    response.setStatus(500);
                    return;
                }

            }
        }

        // On recupère l'album à afficher
        Album albumAAfficher = gestionnaireUtilisateurs.getAlbumById(idAlbum);

        // On verifie que l'utilisateur a bien le droit d'afficher cette album
        if (albumAAfficher != null) {
            if (albumAAfficher.getUtilisateur().getId() != idUtilisateur) {
                albumAAfficher = null;
            }
        }

        request.setAttribute("album", albumAAfficher);

        // Ajout d'un identifiant unique pour la transaction d'ajout de photos
        UUID idTransaction = UUID.randomUUID();
        request.setAttribute("idTransaction", idTransaction.toString());

        // On ajoute la liste des utilisateurs
        request.setAttribute("listeUtilisateur", gestionnaireUtilisateurs.getAllOtherUser(idUtilisateur));

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
            out = new FileOutputStream(new File(path + File.separator + System.currentTimeMillis()
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println(new File(path + File.separator + fileName).getAbsolutePath());

        } catch (FileNotFoundException fne) {
            // TODO
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
        for (File image : listeImage) {
            // On crée la photo en base de données et on l'ajoute à l'album
            Album a = gestionnaireUtilisateurs.getAlbumById(idAlbum);
            Photo photo = gestionnaireUtilisateurs.creerPhoto(image.getName().substring(13), idAlbum);

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

}
