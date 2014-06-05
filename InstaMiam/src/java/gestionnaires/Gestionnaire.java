package gestionnaires;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modeles.Album;
import modeles.Commentaire;
import modeles.Notification;
import modeles.Photo;
import modeles.Utilisateur;

/**
 * Classe Gestionnaire
 </br></br>
 * Gestionnaire en charge des données de la base de données.
 */
@Stateless
public class Gestionnaire {

    // Ici injection de code : on n'initialise pas. L'entity manager sera créé  
    // à partir du contenu de persistence.xml  
    @PersistenceContext(unitName = "InstaMiamPU")
    private EntityManager em;

    /**
     * Méthode pour créer un utilisateur
     * @param nom Le nom
     * @param prenom Le prénom
     * @param login Le login
     * @param email L'email
     * @param motDePasse Le mot de passe
     * @return L'utilisateur créé
     */
    public Utilisateur creeUtilisateur(String nom, String prenom, String login, String email, String motDePasse) {
        // Si le login est déjà utilisé dans la base de données on ne créer pas le comptes
        if (isLoginUsed(login)) {
            return null;
        } else {
            Utilisateur u = new Utilisateur(nom, prenom, login, email, motDePasse);
            em.persist(u);
            em.flush();
            return u;
        }
    }

    /**
     * Méthode permettant de savoir si un compte avec ce login existe déjà.
     * 
     * @param login Le login à tester
     * @return Boolean indiquant si un compte avec ce login existe déjà
     */
    public boolean isLoginUsed(String login) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:param");
        q.setParameter("param", login);

        return !q.getResultList().isEmpty();
    }

    /**
     * Méthode permettant de récuperer un utilisateur à partir de se
     * informations de connexion.
     *
     * @param login Le login
     * @param password Le mot de passe
     * @return L'utilisateur
     */
    public Utilisateur getUserByConnexion(String login, String password) {
        Query q = em.createQuery("select u from Utilisateur u where u.login=:param");
        q.setParameter("param", login);

        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            Utilisateur u = (Utilisateur) q.getSingleResult();

            if (u.getMotDePasse().equals(password)) {
                return u;
            } else {
                return null;
            }
        }
    }

    /**
     * Méthode de création d'album
     * 
     * @param nom
     * @param idUser
     * @param typePartage
     * @return 
     */
    public Album creerAlbum(String nom, int idUser, int typePartage) {
        Utilisateur u = em.find(Utilisateur.class, idUser);

        Album a = new Album(nom, typePartage);
        em.persist(a);
        em.flush();

        a.setUtilisateur(u);
        u.ajouterAlbum(a);
        return a;
    }

    /**
     * Méthode pour récuperer un utilisateur par son id
     * @param id
     * @return 
     */
    public Utilisateur getUtilisateurById(int id) {
        return em.find(Utilisateur.class, id);
    }

    /**
     * Méthode pour créer une Photo
     * @param nom
     * @param idAlbum
     * @return 
     */
    public Photo creerPhoto(String nom, int idAlbum) {
        Album a = em.find(Album.class, idAlbum);
        Photo p = new Photo(nom);

        em.persist(p);
        em.flush();

        p.setAlbum(a);
        a.ajouterPhoto(p);

        return p;
    }

    /**
     * Méthode pour ajouter un commentaire à une photo
     * @param idPhoto
     * @param idAuteur
     * @param text 
     */
    public void ajouterCommentairePhoto(int idPhoto, int idAuteur, String text) {
        Commentaire c = new Commentaire(text);
        em.persist(c);
        em.flush();

        Photo p = em.find(Photo.class, idPhoto);
        p.ajouterCommentaire(c);
        c.setAuteur(em.find(Utilisateur.class, idAuteur));
        c.setPhoto(p);
        
        // Notification au proprietaire de la photo
        if (idAuteur != p.getAlbum().getUtilisateur().getId())
            creerNotification(p.getAlbum().getUtilisateur().getId(), p.getAlbum().getId(), idAuteur, Notification.NOTIFICATION_COMMENTAIRE_PHOTO);
        
        // Notifications aux personnes dont l'album est partagé
        for (Utilisateur utilisateurPartage : p.getAlbum().getUtilisateursPartages()) {
            if (idAuteur != utilisateurPartage.getId())
                creerNotification(utilisateurPartage.getId(), p.getAlbum().getId(), p.getAlbum().getUtilisateur().getId(), Notification.NOTIFICATION_COMMENTAIRE_PHOTO);
        }
    }

    /**
     * Méthode pour ajouter un commentaire à un album
     * @param idAlbum
     * @param idAuteur
     * @param text 
     */
    public void ajouterCommentaireAlbum(int idAlbum, int idAuteur, String text) {
        Commentaire c = new Commentaire(text);

        em.persist(c);
        em.flush();

        Album a = em.find(Album.class, idAlbum);
        a.ajouterCommentaire(c);
        c.setAlbum(a);
        c.setAuteur(em.find(Utilisateur.class, idAuteur));
        
        // Notification au proprietaire de la photo
        if (idAuteur != a.getUtilisateur().getId())
            creerNotification(a.getUtilisateur().getId(), a.getId(), idAuteur, Notification.NOTIFICATION_COMMENTAIRE_ALBUM);
        
        for (Utilisateur utilisateurPartage : a.getUtilisateursPartages()) {
            if (idAuteur != utilisateurPartage.getId())
                creerNotification(utilisateurPartage.getId(), idAlbum, a.getUtilisateur().getId(), Notification.NOTIFICATION_COMMENTAIRE_ALBUM);
        }
    }

    /**
     * Méthode pour récuperer la liste des albums d'un utilisateur à partir de son id
     * @param idUser
     * @return 
     */
    public List<Album> getListeAlbumsByIdUser(int idUser) {
        Utilisateur u = em.find(Utilisateur.class, idUser);
        return u.getAlbums();
      
    }
   
    /**
     * Méthode pour récuperer une photo par son id
     * @param idPhoto
     * @return 
     */
    public Photo getPhotoById(int idPhoto) {
        return em.find(Photo.class, idPhoto);
    }

    /**
     * Méthode pour récuperer la liste de tous les utilisateurs moins celui passé en paramètre
     * @param idUtilisateur
     * @return 
     */
    public List<Utilisateur> getAllOtherUser(int idUtilisateur) {
        Query q = em.createQuery("select u from Utilisateur u where u.id!=:param");
        q.setParameter("param", idUtilisateur);

        return q.getResultList();
    }

    /**
     * Méthode pour récuperer un album par son id
     * @param idAlbum
     * @return 
     */
    public Album getAlbumById(int idAlbum) {
        return em.find(Album.class, idAlbum);
    }

    /**
     * Méthode pour modifier les informations d'une photo
     * @param idPhoto
     * @param nom
     * @param description 
     */
    public void modifierPhoto(int idPhoto, String nom, String description) {
        Photo p = getPhotoById(idPhoto);
        p.setNom(nom);
        p.setDescription(description);
        em.flush();
    }

    /**
     * Méthode pour supprimer une photo
     * @param idPhoto 
     */
    public void supprimerPhoto(int idPhoto) {
        Photo p = getPhotoById(idPhoto);

        // On retire la photo de l'album
        p.getAlbum().getPhotos().remove(p);

        // On supprime les commentaires liés
        for (Commentaire c : p.getCommentaires()) {
            em.remove(c);
        }

        // Si il couvre un album remettre la couverture par défaut
        if (p.getAlbumCouvert() != null) {
            p.getAlbum().setPhotoDeCouverture(null);
        }

        // Finalement, on supprime la photo
        em.remove(p);
        em.flush();
    }

    /**
     * Méthode pour suppromer un album
     * @param idAlbum 
     */
    public void supprimerAlbum(int idAlbum) {
        Album a = getAlbumById(idAlbum);

        // On supprime les photos liées à l'album
        ArrayList<Integer> listeIdPhoto = new ArrayList();
        for (Photo p : a.getPhotos()) {
            listeIdPhoto.add(p.getId());
        }
        for (int id : listeIdPhoto) {
            supprimerPhoto(id);
        }

        // On supprime les commentaires de l'album
        for (Commentaire c : a.getCommentaires()) {
            em.remove(c);
        }

        // On supprime l'album de la liste des albums des utilisateurs
        a.getUtilisateur().getAlbums().remove(a);
        
        // On supprime les notifications liées à cette albums
        Query q = em.createQuery("Select n from Notification n where n.albumCible=:param");
        q.setParameter("param", a);
        List<Notification> liste = q.getResultList();
        
        for (Notification n : liste) {
            em.remove(n);
        }
        
        // On supprimer les utilisateurs en partage sur cette album
        for (Utilisateur utilisateurPartage : a.getUtilisateursPartages())
            utilisateurPartage.getAlbumsPartages().remove(a);
        em.flush();
        
        // Finalement, on supprime l'album
        em.remove(a);
        em.flush();
    }

    /**
     * Méthode pour modifier un album
     * @param idAlbum
     * @param nomAlbum 
     */
    public void modifierAlbum(int idAlbum, String nomAlbum) {
        Album a = getAlbumById(idAlbum);

        a.setNomAlbum(nomAlbum);
        em.flush();
    }

    /**
     * Méthode pour modifier les informations d'un utilisateur
     * @param idUtilisateur
     * @param nom
     * @param prenom
     * @param email
     * @param mdp 
     */
    public void modifierUtilisateur(int idUtilisateur, String nom, String prenom, String email, String mdp) {
        Utilisateur u = getUtilisateurById(idUtilisateur);

        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setMotDePasse(mdp);

        em.flush();
    }

    /**
     * Méthode pour partager un album
     * @param idAlbum
     * @param idUtilisateurPartage
     * @param idUtilisateurPartageur
     * @return 
     */
    public boolean partagerAlbum(int idAlbum, int idUtilisateurPartage, int idUtilisateurPartageur) {
        Utilisateur u = getUtilisateurById(idUtilisateurPartage);
        Album a = getAlbumById(idAlbum);

        if (a == null || u == null || a.getUtilisateursPartages().contains(u) || u.getAlbumsPartages().contains(a)) {
            return false;
        }

        u.getAlbumsPartages().add(a);
        a.getUtilisateursPartages().add(u);

        creerNotification(idUtilisateurPartage, idAlbum, idUtilisateurPartageur, Notification.NOTIFICATION_PARTAGE_ALBUM);
        em.flush();

        return true;
    }

    /**
     * Méthode pour modifier la photo de profil d'un utilisateur
     * @param idUtilisateur
     * @param fileName 
     */
    public void setPhotoProfil(int idUtilisateur, String fileName) {
        Utilisateur u = getUtilisateurById(idUtilisateur);
        u.setImageProfil(fileName);
        em.flush();
    }

    /**
     * Méthode pour supprimer le partage d'un album
     * @param idAlbum
     * @param idUtilisateurPartage
     * @return 
     */
    public boolean supprimerPartage(int idAlbum, int idUtilisateurPartage) {
        Utilisateur u = getUtilisateurById(idUtilisateurPartage);
        Album a = getAlbumById(idAlbum);

        if (a == null || u == null || !a.getUtilisateursPartages().contains(u) || !u.getAlbumsPartages().contains(a)) {
            return false;
        }

        u.getAlbumsPartages().remove(a);
        a.getUtilisateursPartages().remove(u);

        em.flush();

        return true;
    }

    /**
     * Méthode pour récuperer les albums visibles d'un utilisateur qu'un autre essaye de consulter
     * @param idUtilisateurSource
     * @param idUtilisateurCible
     * @return 
     */
    public List<Album> getAlbumsVisibles(int idUtilisateurSource, int idUtilisateurCible) {
        List<Album> listeAlbumsVisibles = new ArrayList();

        List<Album> tousLesAlbums = getListeAlbumsByIdUser(idUtilisateurCible);

        Utilisateur utilisateurSource = getUtilisateurById(idUtilisateurSource);

        for (Album a : tousLesAlbums) {
            if (a.getTypePartage() == Album.ALBUM_PUBLIC || a.getUtilisateursPartages().contains(utilisateurSource)) {
                listeAlbumsVisibles.add(a);
            }
        }

        return listeAlbumsVisibles;
    }

    /**
     * Méthode pour modifier la photo de couverture d'un album
     * @param idAlbum
     * @param idPhoto 
     */
    public void setPhotoCouverture(int idAlbum, int idPhoto) {
        Album a = getAlbumById(idAlbum);
        Photo p = getPhotoById(idPhoto);

        a.setPhotoDeCouverture(p);
        p.setAlbumCouvert(a);
        em.flush();
    }

    /**
     * Méthode pour créer une notification
     * @param idUtilisateurNotifie
     * @param idAlbumCible
     * @param idUtilisateurNotifieur
     * @param typeNotification
     * @return 
     */
    public Notification creerNotification(int idUtilisateurNotifie, int idAlbumCible, int idUtilisateurNotifieur, int typeNotification) {
        Utilisateur utilisateurNotifie = getUtilisateurById(idUtilisateurNotifie);
        Utilisateur utilisateurNotifieur = getUtilisateurById(idUtilisateurNotifieur);
        Album albumCible = getAlbumById(idAlbumCible);

        Notification n = new Notification(utilisateurNotifie, albumCible, utilisateurNotifieur, typeNotification);
        em.persist(n);
        em.flush();

        return n;
    }

    /**
     * Méthode pour récuperer les notification non lues d'un utilisateur
     * @param idUser
     * @return 
     */
    public List<Notification> getListeNotificationNonLues(int idUser) {
        Utilisateur u = em.find(Utilisateur.class, idUser);

        Query q = em.createQuery("select n from Notification n where n.utilisateurNotifie=:param and n.isLue=:false");
        q.setParameter("param", u);
        q.setParameter("false", false);

        if (q.getResultList().isEmpty()) {
            return new ArrayList<>();
        } else {
            return q.getResultList();
        }

    }

    /**
     * Méthode pour récuperer les notifications lues d'un utilisateur
     * @param idUtilisateur
     * @param nb
     * @param offset
     * @return 
     */
    public List<Notification> getListeNotificationLues(int idUtilisateur, int nb, int offset) {
        Utilisateur u = em.find(Utilisateur.class, idUtilisateur);

        Query q = em.createQuery("select n from Notification n where n.utilisateurNotifie=:param and n.isLue=:false");
        q.setParameter("param", u);
        q.setParameter("false", true);

        if (q.getResultList().isEmpty() || offset >= q.getResultList().size()) {
            return new ArrayList<>();
        } else {
            if (offset + nb >= q.getResultList().size()) {
                return q.getResultList().subList(offset, q.getResultList().size());
            } else {
                return q.getResultList().subList(offset, offset + nb);
            }
        }
    }

    /**
     * Méthode pour indiquer que les notifications non lues sont lues
     * @param idUtilisateur 
     */
    public void setNotificationsLues(int idUtilisateur) {
        Utilisateur u = em.find(Utilisateur.class, idUtilisateur);

        Query q = em.createQuery("select n from Notification n where n.utilisateurNotifie=:param and n.isLue=:false");
        q.setParameter("param", u);
        q.setParameter("false", false);

        if (q.getResultList().isEmpty()) {
            return;
        } else {
            for (Notification n : (List<Notification>) q.getResultList()) {
                n.setIsLue(true);
            }
        }

        em.flush();
    }

    /**
     * Méthode pour notifier un utilisateur d'un partage
     * @param idAlbum
     * @param idUtilisateur
     * @param typeNotif 
     */
    public void notifierUtilisateursPartages(int idAlbum, int idUtilisateur, int typeNotif) {
        Utilisateur u = getUtilisateurById(idUtilisateur);
        Album a = getAlbumById(idAlbum);
        
        for(Utilisateur u2 : a.getUtilisateursPartages()){
            creerNotification(u2.getId(), idAlbum, idUtilisateur, typeNotif);
        }
        
        em.flush();
    }

}
