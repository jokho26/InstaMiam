package gestionnaires;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import modeles.Album;
import modeles.Utilisateur;

/**
 * Le listener de l'application web InstaMiam.
 * Sert pour l'injection de données au déploiement de l'application.
 */
public class ApplicationListener implements ServletContextListener {

     @EJB
    private Gestionnaire gestionnaireUtilisateurs;
    
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Utilisateur u = gestionnaireUtilisateurs.creeUtilisateur("Jean", "Bon", "test", "jeanB@email.com", "test");
        Utilisateur u2 = gestionnaireUtilisateurs.creeUtilisateur("Serge", "LeLama", "LeLama", "LeLama@gmail.com", "LeLama");
        Utilisateur u3 = gestionnaireUtilisateurs.creeUtilisateur("Anne", "Orak", "Anne", "Anne@gmail.com", "Anne");
        gestionnaireUtilisateurs.creeUtilisateur("John", "Doeuf", "John", "John@gmail.com", "John");
        
        // Album de Jean Bon
        Album a = gestionnaireUtilisateurs.creerAlbum("Mon album public", u.getId(), Album.ALBUM_PUBLIC);
        gestionnaireUtilisateurs.creerAlbum("Mon album privé", u.getId(), Album.ALBUM_PRIVE);
        
        // Album de Serge LeLama
        Album prive = gestionnaireUtilisateurs.creerAlbum("Album Super Secret de Serge", u2.getId(), Album.ALBUM_PRIVE);
        gestionnaireUtilisateurs.creerAlbum("Album Public de Serge", u2.getId(), Album.ALBUM_PUBLIC);
        
        // Album de Anne
        Album a2 = gestionnaireUtilisateurs.creerAlbum("Album super secret de Anne", u.getId(), Album.ALBUM_PRIVE);
        Album a3 =gestionnaireUtilisateurs.creerAlbum("Nouvel Album super secret de Anne", u.getId(), Album.ALBUM_PRIVE);
        
        
        // Partage des albums vers Jean
        
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u2.getId(), "Pourquoi ton album est vide ?");
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u.getId(), "Pour faire parler les curieux !");
        gestionnaireUtilisateurs.ajouterCommentaireAlbum(a.getId(), u2.getId(), ":\'(");
        
        // Partage de l'album privé de Serge à Jean
        gestionnaireUtilisateurs.partagerAlbum(prive.getId(), u.getId(), u2.getId());
        
        // Partage des albums de Anne
        gestionnaireUtilisateurs.partagerAlbum(a2.getId(), u.getId(), u3.getId());
        gestionnaireUtilisateurs.partagerAlbum(a3.getId(), u.getId(), u3.getId());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
