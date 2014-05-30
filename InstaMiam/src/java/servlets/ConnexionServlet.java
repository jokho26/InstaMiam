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

/**
 * Servlet pour la connexion au site.
 */
@WebServlet(name = "ConnexionServlet", urlPatterns = {"/Connexion"})
public class ConnexionServlet extends HttpServlet {

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

        // On récupere la session
        HttpSession session = request.getSession();

        String forwardTo = "connexion.jsp";

        if (action != null) {
            if (action.equals("deconnexion")) {
                session.setAttribute("utilisateurConnecte", null);
                request.setAttribute("message", "Déconnexion réussie");
            } else if (action.equals("connexion")) {
                String login = (String) request.getParameter("login");
                String mdp = (String) request.getParameter("mdp");

                Utilisateur u = gestionnaireUtilisateurs.getUserByConnexion(login, mdp);

                if (u == null) {
                    request.setAttribute("messageErreur", "Login ou mot de passe incorrect. Try again.");
                } else {
                    session.setAttribute("utilisateurConnecte", u.getId());
                    request.setAttribute("message", "Bienvenue chez vous " + login + " !");
                    forwardTo = "/ListeAlbums";
                }
            }
        }

        // On ajoute la liste des utilisateurs
        if (session.getAttribute("utilisateurConnecte") != null) {
            int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));
            request.setAttribute("listeUtilisateur", gestionnaireUtilisateurs.getAllOtherUser(idUtilisateur));
            request.setAttribute("listeNotificationsSize", gestionnaireUtilisateurs.getListeNotificationNonLues(idUtilisateur).size());
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
