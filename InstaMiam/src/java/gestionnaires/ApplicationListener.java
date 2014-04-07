/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionnaires;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
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
        gestionnaireUtilisateurs.creerAlbum("Album 1", u);
        gestionnaireUtilisateurs.creerAlbum("Album 2", u);
        gestionnaireUtilisateurs.creerAlbum("Album 3", u);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
