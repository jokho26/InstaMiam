/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionnaires;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.Utilisateur;

/**
 * Classe GestionnaireUtilisateurs
 * </br></br>
 * Gestionnaire en charge des utilisateurs de la base de données.
 */
@Stateless
public class GestionnaireUtilisateurs {

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext(unitName = "InstaMiamPU")
    private EntityManager em;  
  
    public void creerUtilisateursDeTest() {  
        creeUtilisateur("John", "Lennon", "jlennon", "@gmail.com", "");  
        creeUtilisateur("Paul", "Mac Cartney", "pmc","@gmail.com","");  
        creeUtilisateur("Ringo", "Starr", "rstarr","@gmail.com","");  
        creeUtilisateur("Georges", "Harisson", "georgesH","@gmail.com","");  
    }  
  
    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String email, String motDePasse) {  
        Utilisateur u = new Utilisateur(nom, prenom, login,email,motDePasse);  
        em.persist(u);  
        return u;  
    }  
  
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }  
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
