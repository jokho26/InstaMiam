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
import modeles.Utilisateur;

@MultipartConfig
@WebServlet(name = "ProfilServlet", urlPatterns = {"/Profil"})
public class ProfilServlet extends SuperServletVerification {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.processRequest(request, response);
        
        if (isAlreadyForwarded)
            return;
        
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
                } else {
                    request.setAttribute("messageErreur", "{{tab_lang.profil.messageErreur}}");
                }
            } else if (action.equals("changerImage")) {
                enregistrerPhotoProfil(request, gestionnaireUtilisateurs.getUtilisateurById(Integer.parseInt(session.getAttribute("utilisateurConnecte").toString())));
            }
        }

        // On récupere l'utilisateur loggé
        Utilisateur u = gestionnaireUtilisateurs.getUtilisateurById(Integer.parseInt(session.getAttribute("utilisateurConnecte").toString()));
        request.setAttribute("profil", u);

        String forwardTo = "monProfil.jsp";
        RequestDispatcher dp = request.getRequestDispatcher(forwardTo);

        dp.forward(request, response);

    }

    /**
     * Méthode permettant d'enregistrer un fichier uploadé lors d'une
     * transaction avec la dropzone.
     *
     * @param request
     * @throws ServletException
     * @throws IOException
     */
    private void enregistrerPhotoProfil(HttpServletRequest request, Utilisateur u) throws ServletException, IOException {
        // On créer le repertoire temporaire de stockage
        final String path = getServletConfig().getServletContext().getRealPath("/") + File.separator + "profil";
        File dir = new File(path);
        dir.mkdirs();

        final Part filePart = request.getPart("file");

        // On récupere l'id de l'image
        String id;
        // Si l'utilisateur n'a pas encore d'ID attitré, on va en créer un
        if (u.getImageProfil().contains("default")) {
            UUID uuid = UUID.randomUUID();
            id = uuid.toString();
        } else {
            // Sinon on récupere l'id déjà utilisé
            id = u.getImageProfil().substring(0, u.getImageProfil().lastIndexOf("."));
        }

        // On créée le nom du fichier final
        final String fileName = id + getExtentionFile(filePart);
        gestionnaireUtilisateurs.setPhotoProfil(u.getId(), fileName);

        OutputStream out = null;
        InputStream filecontent = null;

        // On récupere le contenu du fichier et on le sauvegarde sur le serveur
        try {
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println(new File(path + File.separator + fileName).getAbsolutePath());

        } catch (FileNotFoundException fne) {
            // TODO
            System.out.println("Exception dans l'upload du fichier.\n" + fne.getMessage());
        } // On ferme les streams
        finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }

    /**
     * Méthode permettant de récuperer le nom du fichier qu'on upload
     *
     * @param part
     * @return
     */
    private String getExtentionFile(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf("."), fileName.length());
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
