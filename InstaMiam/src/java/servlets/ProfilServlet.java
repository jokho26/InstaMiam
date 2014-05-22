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
import modeles.Utilisateur;

@WebServlet(name = "ProfilServlet", urlPatterns = {"/Profil"})
public class ProfilServlet extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("modifierProfil")) {
                String nom = request.getParameter("nom");
                String prenom = request.getParameter("prenom");
                String email = request.getParameter("email");
                String mdp = request.getParameter("mdp");
                
                if (nom != null && prenom != null && email != null && mdp != null) {
                    gestionnaireUtilisateurs.modifierUtilisateur(Integer.parseInt(session.getAttribute("utilisateurConnecte").toString()),
                        nom, prenom, email, mdp);
                    
                    // Message de validation
                    request.setAttribute("message", "{{tab_lang.profil.messageModification}}");
                }
                else {
                    request.setAttribute("messageErreur", "{{tab_lang.profil.messageErreur}}");
                }
            }
        }
        
        
        
        
        
        // On récupere l'utilisateur loggé
        Utilisateur u = gestionnaireUtilisateurs.getUtilisateurById(Integer.parseInt(session.getAttribute("utilisateurConnecte").toString()));
        request.setAttribute("profil", u);
        
        String forwardTo = "monProfil.jsp";
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
