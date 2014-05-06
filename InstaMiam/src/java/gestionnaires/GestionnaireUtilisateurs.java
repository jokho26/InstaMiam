/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gestionnaires;

import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.Album;
import modeles.Commentaire;
import modeles.Photo;
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
        // Si le login est déjà utilisé dans la base de données on ne créer pas le comptes
        if (isLoginUsef(login)) {
            return null;
        }
        else {
            Utilisateur u = new Utilisateur(nom, prenom, login,email,motDePasse);  
            em.persist(u);
            em.flush();
            return u;
        }
    }
    
    public boolean isLoginUsef(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:param");
        q.setParameter("param", login);
       
        if (q.getResultList().isEmpty())
            return false;
        else
            return true;
    }
  
    /**
     * TODO à virer ?
     * @return 
     */
    public Collection<Utilisateur> getAllUsers() {  
        // Exécution d'une requête équivalente à un select *  
        Query q = em.createQuery("select u from Utilisateur u");  
        return q.getResultList();  
    }
    
    
    
     /**
     * Méthode permettant de récuperer un utilisateur à partir de se informations de connexion.
     * @param login
     * @param password
     * @return 
     */
    public Utilisateur getUserByConnexion(String login, String password) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:param");
        q.setParameter("param", login);
       
        if (q.getResultList().isEmpty()) {
            return null;
        }
        else {
            Utilisateur u = (Utilisateur) q.getSingleResult();
            
            if (u.getMotDePasse().equals(password))
                return u;
            else
                return null;
        }
    }
    
    public Album creerAlbum(String nom, int idUser) {
        Utilisateur u = em.find(Utilisateur.class, idUser);
        
        Album a = new Album(nom);
        em.persist(a);
        em.flush();
        
        a.setUtilisateur(u);
        u.ajouterAlbum(a);
        return a;
    }
    
    public Photo creerPhoto(String nom, String nomFichier, int idAlbum) {
        Album a = em.find(Album.class, idAlbum);
        Photo p = new Photo(nom, nomFichier);
        
        em.persist(p);
        em.flush();
        
        p.setAlbum(a);
        a.ajouterPhoto(p);
        
        return p;
    }
    
    public void ajouterCommentairePhoto(Photo photo, Utilisateur auteur, String text) {
        Commentaire c = new Commentaire(text);
        em.persist(c);
        em.flush();
        
        Photo p = em.find(Photo.class, photo.getId());
        p.ajouterCommentaire(c);
        c.setAuteur(em.find(Utilisateur.class, auteur.getId()));
        c.setPhoto(p);
    }
    
    public void ajouterCommentaireAlbum(Album album, Utilisateur auteur, String text) {
        Commentaire c = new Commentaire(text);
        
        em.persist(c);
        em.flush();
        
        Album a = em.find(Album.class, album.getId());
        a.ajouterCommentaire(c);
        c.setAlbum(a);
        c.setAuteur(em.find(Utilisateur.class, auteur.getId()));
    }
    
    
    public List<Album> getListeAlbumsByIdUser(int idUser) {
        Utilisateur u = em.find(Utilisateur.class, idUser);
        return u.getAlbums();
        /*
        Utilisateur u = em.find(Utilisateur.class, idUser);
        
        Query q = em.createQuery("select a from Album a where a.utilisateur=:param");
        q.setParameter("param", u);
       
        if (q.getResultList().isEmpty()) {
            return null;
        }
        else {
            return q.getResultList();
        }*/
    }     
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
