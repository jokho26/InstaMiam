/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import gestionnaires.GestionnaireUtilisateurs;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Notification;
import modeles.Photo;

/**
 *
 * @author Christian
 */
@WebServlet(name = "NotificationsServlet", urlPatterns = {"/Notifications"})
public class NotificationsServlet extends SuperServletVerification {

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

        super.processRequest(request, response);
        if (isAlreadyForwarded) {
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        // On récupere la session
        HttpSession session = request.getSession();

        int idUtilisateur = (int) (session.getAttribute("utilisateurConnecte"));

        String action = request.getParameter("action");
        if (action != null) {
            if (action.equals("chargerPlus")) {
                String offsetStr = request.getParameter("offset");
                int offset;

                if (offsetStr != null) {
                    offset = Integer.parseInt(offsetStr);
                } else {
                    return;
                }

                List<Notification> liste = gestionnaireUtilisateurs.getListeNotificationLues(idUtilisateur, 3, offset);

                request.setAttribute("listeNotificationsLues", liste);

                String forwardTo = "/notificationsSupplementaires.jsp";
                RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                dp.forward(request, response);
                return;
            }
        }

        request.setAttribute("utilisateur", gestionnaireUtilisateurs.getUtilisateurById(idUtilisateur));
        request.setAttribute("listeNotificationsNonLues", gestionnaireUtilisateurs.getListeNotificationNonLues(idUtilisateur));
        request.setAttribute("listeNotificationsLues", gestionnaireUtilisateurs.getListeNotificationLues(idUtilisateur, 3, 0));

        gestionnaireUtilisateurs.setNotificationsLues(idUtilisateur);

        String forwardTo = "notifications.jsp";
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
