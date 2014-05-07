/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionnaires;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import modeles.Album;
import modeles.Photo;
import modeles.Utilisateur;

/**
 * le listener de l'application web InstaMiam.
 *
 * @author Jokho
 */
public class ApplicationListener implements ServletContextListener {

     @EJB
    private GestionnaireUtilisateurs gestionnaireUtilisateurs;
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Utilisateur u = gestionnaireUtilisateurs.creeUtilisateur("Jean", "Bon", "test", "test", "test");
        Album a = gestionnaireUtilisateurs.creerAlbum("Album 1", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 2", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 3", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 4", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 5", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 6", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 7", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 8", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 9", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 10", u.getId());
        gestionnaireUtilisateurs.creerAlbum("Album 11", u.getId());
        
        gestionnaireUtilisateurs.creerPhoto("Photo 1", "Photo1.png", a.getId());
        gestionnaireUtilisateurs.creerPhoto("Photo 2", "Photo2.png", a.getId());
        gestionnaireUtilisateurs.creerPhoto("Photo 3", "Photo3.png", a.getId());
        
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u.getId(), "Commentaire 1 !");
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
