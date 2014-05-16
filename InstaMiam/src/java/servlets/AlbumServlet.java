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
import java.util.UUID;
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

/**
 *
 * @author Siddi Steven
 */
@MultipartConfig
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
        
        Object idAlbumObject = request.getParameter("idAlbum");
        
        int idAlbum = -1;
        
        if (idAlbumObject != null) {
            idAlbum = Integer.parseInt(request.getParameter("idAlbum"));
            request.setAttribute("idAlbum", idAlbum);
        }
        
        // On récupere la session
        HttpSession session = request.getSession();
        int idUtilisateur = (int)(session.getAttribute("utilisateurConnecte"));
        
        String action = request.getParameter("action");
        
        System.out.println("======> Action : " + action);
        if (action != null) {
            if (action.equals("uploadFile")) {
                enregistrerFichier(request);
            }
            else if (action.equals("removeFile")) {
                // TODO ajouter des verifications
                Object nomFichierObject = request.getParameter("nameFile");
                Object idTransaction = request.getParameter("idTransaction");
 
                if (nomFichierObject != null && idTransaction != null) {
                    removeFile((String)nomFichierObject, (String)idTransaction);
                }
            }
            else if (action.equals("validUpload")) {
                System.out.println("validUpload");
            }
            else if (action.equals("ajouterCommentaire")) {
                // Recupération des paramètres du formulaire
                String text = request.getParameter("commentaire");
                if (idAlbum > 0 && text != null) {
                    gestionnaireUtilisateurs.ajouterCommentaireAlbum(idAlbum, idUtilisateur, text);
             }   
            }
        }
        
         // TODO à virer ! faire un truc plus propre
        Album albumAAfficher = null;
        for(Album a : gestionnaireUtilisateurs.getListeAlbumsByIdUser(idUtilisateur))
            if (a.getId() == idAlbum) 
                albumAAfficher=a;
        
        request.setAttribute("album", albumAAfficher);
        
        // Ajout d'un identifiant unique pour la transaction d'ajout de photos
        UUID idTransaction = UUID.randomUUID();
         request.setAttribute("idTransaction", idTransaction.toString());
        
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);

        dp.forward(request, response);
        
    }
    
    
    
    
    private void enregistrerFichier(HttpServletRequest request) throws ServletException, IOException {
         Object idTransactionObject = request.getParameter("idTransaction");
        if (idTransactionObject == null)
            return;
        String idTransaction = (String)idTransactionObject;
        
        // Create path components to save the file
        final String path = getServletConfig().getServletContext().getRealPath("/") + File.separator+ "tmp"+ File.separator+idTransaction;
        
        // Création du repertoire de destination
        File dir = new File(path);
        dir.mkdir();
        
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

    private void removeFile(String nom, String idTransaction) {
        String cheminFichier = getServletConfig().getServletContext().getRealPath("/")+ "tmp"+File.separator+idTransaction+File.separator+nom;
        File file = new File(cheminFichier);
        file.delete();
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
