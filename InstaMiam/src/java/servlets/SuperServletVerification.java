package servlets;

import gestionnaires.Gestionnaire;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet "mère" des autres servlet.
 * Factorisation du gestionnaire, de l'erreur 404 et de la verification de la connexion.
 */
public abstract class SuperServletVerification extends HttpServlet {
    
    protected boolean isAlreadyForwarded;
    
    @EJB
    protected Gestionnaire gestionnaire;
    
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
        request.setAttribute("listeUtilisateur", gestionnaire.getAllOtherUser(idUtilisateur));

        request.setAttribute("listeNotificationsSize", gestionnaire.getListeNotificationNonLues(idUtilisateur).size());
    }
    
    protected void dispatch404Error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forwardTo = "404Error.jsp";
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);
        dp.forward(request, response);
        isAlreadyForwarded = true;
    }
    
}
