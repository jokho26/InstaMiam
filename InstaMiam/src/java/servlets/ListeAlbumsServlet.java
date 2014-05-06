/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import gestionnaires.GestionnaireUtilisateurs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import modeles.Album;
import modeles.Commentaire;
import modeles.Utilisateur;

/**
 *
 */
@WebServlet(name = "ListeAlbumsServlet", urlPatterns = {"/ListeAlbums"})
@MultipartConfig
public class ListeAlbumsServlet extends HttpServlet {

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
         String forwardTo="listeAlbums.jsp";
         
        String action = request.getParameter("action");
          
        // On récupere la session
        HttpSession session = request.getSession();
        
        Utilisateur u = (Utilisateur)(session.getAttribute("utilisateurConnecte"));
        request.setAttribute("listeAlbums", gestionnaireUtilisateurs.getListeAlbumsByIdUser(u.getId()));
        
        List<Album> liste = gestionnaireUtilisateurs.getListeAlbumsByIdUser(u.getId());
        
        for (Album a : liste) {
            System.out.println(a.getNomAlbum() + ": ");
            if (a.getCommentaires() != null) {
                List<Commentaire> listeC = a.getCommentaires();
                for (Commentaire c : listeC)
                    System.out.println("  "  + c.getText());
            }
            else {
                System.out.println("  Vide");
            }
            System.out.println();
        }
        
        
         if (action != null) {
             if (action.equals("ajouterAlbum")) {
                 String nomAlbum = request.getParameter("nomAlbum");
                 gestionnaireUtilisateurs.creerAlbum(nomAlbum, u.getId());
                 request.setAttribute("listeAlbums", gestionnaireUtilisateurs.getListeAlbumsByIdUser(u.getId()));
             }
             else if (action.equals("upload")) {
                enregistrerFichier(request);
            }
        }
        
         
         
         RequestDispatcher dp = request.getRequestDispatcher(forwardTo);

        dp.forward(request, response);
    }
    
    private void enregistrerFichier(HttpServletRequest request) throws ServletException, IOException {
        // Create path components to save the file
        final String path = getServletConfig().getServletContext().getRealPath("/") + File.separator+ "albums";
        final Part filePart = request.getPart("file");
        final String fileName = getFileName(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println(new File(path + File.separator
                    + fileName).getAbsolutePath());

        } catch (FileNotFoundException fne) {
            // TODO
            System.out.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }
    
    private String getFileName(final Part part) {
            final String partHeader = part.getHeader("content-disposition");
         
            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                            content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
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
