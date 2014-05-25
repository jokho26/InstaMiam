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
        Utilisateur u = gestionnaireUtilisateurs.creeUtilisateur("Jean", "Bon", "test", "email@email.com", "test");
        Album a = gestionnaireUtilisateurs.creerAlbum("Album 1", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 2", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 3", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 4", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 5", u.getId(), Album.ALBUM_PRIVE);
        gestionnaireUtilisateurs.creerAlbum("Album 6", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 7", u.getId(), Album.ALBUM_PRIVE);
        gestionnaireUtilisateurs.creerAlbum("Album 8", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 9", u.getId(), Album.ALBUM_PRIVE);
        gestionnaireUtilisateurs.creerAlbum("Album 10", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album 11", u.getId(), Album.ALBUM_PRIVE);
        
      
        
        gestionnaireUtilisateurs.creerPhoto("Photo1.png", a.getId());
        gestionnaireUtilisateurs.creerPhoto( "Photo2.png", a.getId());
        gestionnaireUtilisateurs.creerPhoto("Photo3.png", a.getId());
        
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u.getId(), "Commentaire 1 ! LLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOLOL");
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u.getId(), "Commentaire 2 !");
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u.getId(), "Commentaire 3 !");
        
        gestionnaireUtilisateurs.creeUtilisateur("Anne", "Orak", "Anne", "Anne@gmail.com", "Anne");
        gestionnaireUtilisateurs.creeUtilisateur("John", "Doeuf", "John", "John@gmail.com", "John");
        Utilisateur u2 = gestionnaireUtilisateurs.creeUtilisateur("Serge", "LeLama", "LeLama", "LeLama@gmail.com", "LeLama");
        Album prive = gestionnaireUtilisateurs.creerAlbum("Album Prive test", u2.getId(), Album.ALBUM_PRIVE);
        gestionnaireUtilisateurs.creerAlbum("Album Public 1", u2.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Album Public 2", u2.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerPhoto("Photoprive1.png", prive.getId());
        
        // Test de partage d'album
        gestionnaireUtilisateurs.partagerAlbum(prive.getId(), u.getId());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
