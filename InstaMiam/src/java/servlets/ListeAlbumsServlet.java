/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Album;
import modeles.Utilisateur;

/**
 *
 */
@WebServlet(name = "ListeAlbumsServlet", urlPatterns = {"/ListeAlbums"})
@MultipartConfig
public class ListeAlbumsServlet extends SuperServletVerification {

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
        
        super.processRequest(request, response);
        
        if (isAlreadyForwarded)
            return;
        
        String forwardTo = "listeAlbums.jsp";

        String action = request.getParameter("action");

        // On récupere la session
        HttpSession session = request.getSession();
        int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));
        

        if (action != null) {
            if (action.equals("ajouterAlbum")) {
                String nomAlbum = request.getParameter("nomAlbum");
                String typePartage = request.getParameter("typePartage");
                int type;
                if (typePartage.equals("public"))
                    type = Album.ALBUM_PUBLIC;
                else
                    type = Album.ALBUM_PRIVE;
                
                gestionnaireUtilisateurs.creerAlbum(nomAlbum, idUtilisateur, type);
                request.setAttribute("listeAlbums", gestionnaireUtilisateurs.getListeAlbumsByIdUser(idUtilisateur));
            }
        }
        
        // Si un id précis est défini, on affiche la liste des albums de cette utilisateurs
        if (request.getParameter("idUtilisateurAAfficher") != null) {
            String strIdUtilisateurCible = request.getParameter("idUtilisateurAAfficher");
            if (strIdUtilisateurCible == null) {
                dispatch404Error(request, response);
                return;
            }
            
            int idUtilisateurCible = Integer.parseInt(strIdUtilisateurCible);
            
            Utilisateur utilisateurAAfficher = gestionnaireUtilisateurs.getUtilisateurById(idUtilisateurCible);
            if (utilisateurAAfficher == null) {
                dispatch404Error(request, response);
                return;
            }
                
            List<Album> listeAlbumsVisibles = gestionnaireUtilisateurs.getAlbumsVisibles(idUtilisateur, idUtilisateurCible);
            
            request.setAttribute("listeAlbums", listeAlbumsVisibles);
            request.setAttribute("idUtilisateurAAfficher", idUtilisateurCible);
            
            // On ajoute la liste des utilisateurs
            request.setAttribute("nomUtilisateurAAfficher", utilisateurAAfficher.getPrenom() + " " + utilisateurAAfficher.getNom());
        }
        // Sinon on affiche les albums de l'utilisateur connecté
        else {
            request.setAttribute("listeAlbums", gestionnaireUtilisateurs.getListeAlbumsByIdUser(idUtilisateur));
        }
        
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

}
