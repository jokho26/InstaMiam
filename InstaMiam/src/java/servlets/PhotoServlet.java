/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import gestionnaires.GestionnaireUtilisateurs;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Photo;

/**
 *
 * @author Jokho
 */
@WebServlet(name = "PhotoServlet", urlPatterns = {"/Photo"})
public class PhotoServlet extends HttpServlet {

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
        
        // On récupere la session
        HttpSession session = request.getSession();
        
        int idUtilisateur = (int)(session.getAttribute("utilisateurConnecte"));
        
        Object idPhotoObject = request.getParameter("idPhoto");
        
        int idPhoto = -1;
        
        if (idPhotoObject != null) {
            idPhoto = Integer.parseInt(idPhotoObject.toString());
            request.setAttribute("idPhoto", idPhoto);
        }
        
        String action = request.getParameter("action");
         if (action != null) {
            if (action.equals("ajouterCommentaire")) {
                // Recupération des paramètres du formulaire
                String text = request.getParameter("commentaire");
                System.out.println("Ajout de commentaire : " + idPhoto + " - " + text);
                if (idPhoto > 0 && text != null) {
                    gestionnaireUtilisateurs.ajouterCommentairePhoto(idPhoto, idUtilisateur, text);
             }
            }
            else if (action.equals("modifierPhoto")) {
                
                Photo p = gestionnaireUtilisateurs.getPhotoById(idPhoto);
                if (p != null && request.getParameter("nomPhoto") != null && request.getParameter("description") != null){
                    gestionnaireUtilisateurs.modifierPhoto(idPhoto, request.getParameter("nomPhoto"), request.getParameter("description"));
                }
            }
        }
        
        
        
        Photo photo = gestionnaireUtilisateurs.getPhotoById(idPhoto);
        request.setAttribute("photo", photo);
        
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

}
