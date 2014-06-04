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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeles.Notification;

/**
 * Servlet s'occupant d'afficher les notifications
 */
@WebServlet(name = "NotificationsServlet", urlPatterns = {"/Notifications"})
public class NotificationsServlet extends SuperServletVerification {

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
            // Si on veut charger plus de notification
            if (action.equals("chargerPlus")) {
                String offsetStr = request.getParameter("offset");
                int offset;

                if (offsetStr != null) {
                    offset = Integer.parseInt(offsetStr);
                } else {
                    return;
                }

                // On récupere les 3 prochaines notifications à partir de l'offset
                List<Notification> liste = gestionnaire.getListeNotificationLues(idUtilisateur, 3, offset);

                request.setAttribute("listeNotificationsLues", liste);

                String forwardTo = "/notificationsSupplementaires.jsp";
                RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
                dp.forward(request, response);
                return;
            }
        }

        request.setAttribute("utilisateur", gestionnaire.getUtilisateurById(idUtilisateur));
        request.setAttribute("listeNotificationsNonLues", gestionnaire.getListeNotificationNonLues(idUtilisateur));
        request.setAttribute("listeNotificationsLues", gestionnaire.getListeNotificationLues(idUtilisateur, 3, 0));

        gestionnaire.setNotificationsLues(idUtilisateur);

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
