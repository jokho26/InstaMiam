/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionnaires;

import javax.ejb.EJB;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

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
        gestionnaireUtilisateurs.creeUtilisateur("Jean", "Bon", "test", "test", "test");        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
