package servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Photo;

/**
 * Servlet en charge de l'affichage de la photo
 */
@WebServlet(name = "PhotoServlet", urlPatterns = {"/Photo"})
public class PhotoServlet extends SuperServletVerification {

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

        if (isAlreadyForwarded) {
            return;
        }

        // On récupere la session
        HttpSession session = request.getSession();
        int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));

        Object idPhotoObject = request.getParameter("idPhoto");

        int idPhoto;
        Photo p;
        // Si aucun ID n'est passé, on redirige vers un 404
        if (idPhotoObject != null) {
            idPhoto = Integer.parseInt(idPhotoObject.toString());

            p = gestionnaire.getPhotoById(idPhoto);
            if (p == null) {
                dispatch404Error(request, response);
                return;
            }

            request.setAttribute("idPhoto", idPhoto);
        } else {
            dispatch404Error(request, response);
            return;
        }

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("ajouterCommentaire")) {
                // Recupération des paramètres du formulaire
                String text = request.getParameter("commentaire");
                if (text != null) {
                    gestionnaire.ajouterCommentairePhoto(idPhoto, idUtilisateur, text);
                }
            } else if (action.equals("modifierPhoto")) {
                if (request.getParameter("nomPhoto") != null && request.getParameter("description") != null) {
                    gestionnaire.modifierPhoto(idPhoto, request.getParameter("nomPhoto"), request.getParameter("description"));
                }
            } else if (action.equals("supprimerPhoto")) {
                String forwardTo = "/Album?idAlbum=" + p.getAlbum().getId();

                deleteFilePhoto(p.getAlbum().getIdUnique(), p.getNomFichier());
                gestionnaire.supprimerPhoto(idPhoto);

                RequestDispatcher dp = request.getRequestDispatcher(forwardTo);

                dp.forward(request, response);
                return;
            } else if (action.equals("definirCouverture")) {
                gestionnaire.setPhotoCouverture(p.getAlbum().getId(), p.getId());
            }
        }

        p = gestionnaire.getPhotoById(idPhoto);

        // On verifie que l'utilisateur a bien le droit d'afficher cette photo
        if (p != null) {
            if (p.getAlbum().getUtilisateur().getId() != idUtilisateur
                    && !p.getAlbum().getUtilisateursPartages().contains(gestionnaire.getUtilisateurById(idUtilisateur))) {
                p = null;
                request.setAttribute("messageErreur", "Vous n'avez pas les droits pour acceder à cette photo !");
            }
        }

        request.setAttribute("photo", p);
        String forwardTo = "photo.jsp";
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);

        dp.forward(request, response);
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
     * Méthode pour supprimer la photogrpahie du disque
     * @param idUniqueAlbum
     * @param nomFichier 
     */
    private void deleteFilePhoto(String idUniqueAlbum, String nomFichier) {
        String cheminFichier = getServletConfig().getServletContext().getRealPath("/") + "albums" + File.separator + idUniqueAlbum + File.separator + nomFichier;
        File file = new File(cheminFichier);
        file.delete();
    }

}
