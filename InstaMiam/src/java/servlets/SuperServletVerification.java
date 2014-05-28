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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Siddi Steven
 */
public abstract class SuperServletVerification extends HttpServlet {
    
    protected boolean isAlreadyForwarded;
    
    @EJB
    protected GestionnaireUtilisateurs gestionnaireUtilisateurs;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        isAlreadyForwarded = false;
        
        // On récupere l'id de l'utilisateur connecté
        HttpSession session = request.getSession();
        Object objIdUtilisateur = session.getAttribute("utilisateurConnecte");
        
        if (objIdUtilisateur == null) {
            dispatch404Error(request, response);
            isAlreadyForwarded = true;
            return;
        }
        
        int idUtilisateur = (int) (objIdUtilisateur);
        
         // On ajoute la liste des utilisateurs
        request.setAttribute("listeUtilisateur", gestionnaireUtilisateurs.getAllOtherUser(idUtilisateur));

        request.setAttribute("listeNotificationsSize", gestionnaireUtilisateurs.getListeNotificationNonLues(idUtilisateur).size());
    }
    
    protected void dispatch404Error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = "404Error.jsp";
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
        isAlreadyForwarded = true;
    }
    
}
