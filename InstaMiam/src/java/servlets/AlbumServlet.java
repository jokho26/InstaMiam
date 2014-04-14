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
import modeles.Album;
import modeles.Utilisateur;

/**
 *
 * @author Siddi Steven
 */
@WebServlet(name = "AlbumServlet", urlPatterns = {"/Album"})
public class AlbumServlet extends HttpServlet {

    
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
       
        String forwardTo="album.jsp";
        
        int idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
        
        // On récupere la session
        HttpSession session = request.getSession();
        Utilisateur u = (Utilisateur)(session.getAttribute("utilisateurConnecte"));
        
        Album albumAAfficher = null;
        
        for(Album a : gestionnaireUtilisateurs.getListeAlbumsByIdUser(u.getId()))
            if (a.getId() == idAlbum) 
                albumAAfficher=a;
        
        request.setAttribute("album", albumAAfficher); 
        
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
