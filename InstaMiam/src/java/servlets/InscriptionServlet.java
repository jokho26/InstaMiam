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

/**
 * Servlet pour l'inscription de l'utilisateur au site.
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/Inscription"})
public class InscriptionServlet extends HttpServlet {

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
        
        String action = request.getParameter("action");
        
        if (action != null) {
            if (action.equals("creerUtilisateur")) {
                String login = (String) request.getParameter("login");
                String nom = (String) request.getParameter("nom");
                String prenom = (String) request.getParameter("prenom");
                String email = (String) request.getParameter("email");
                String mdp = (String) request.getParameter("mdp");
                
                if (login != null && mdp != null && nom != null && prenom != null && email != null) {
                    gestionnaireUtilisateurs.creeUtilisateur(nom, prenom, login, email, email);
                    request.setAttribute("message", "Nouvel utilisateur " + login + " crée.");
                }
                else{
                    request.setAttribute("messageErreur", "Information incomplètes !");
                }
            }
        }
        
        String forwardTo = "inscription.jsp";
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
