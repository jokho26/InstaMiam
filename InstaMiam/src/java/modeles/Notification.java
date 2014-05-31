package modeles;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 * Une notification levée lors d'une action pour prévenir un utilisateur
 */
@Entity
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // L'utilisateur notifié
    @ManyToOne
    private Utilisateur utilisateurNotifie;
    
    // Utilisateur à l'origine de l'action
    @OneToOne
    private Utilisateur utilisateurNotifieur;
    
    // L'album en rapport avec la notifivation
    @OneToOne
    private Album albumCible;
    
    private int typeNotification;
    
    private boolean isLue;
    
    public final static int NOTIFICATION_PARTAGE_ALBUM = 1;
    public final static int NOTIFICATION_AJOUT_PHOTO_ALBUM = 2;


    public Notification() {}
    
    public Notification(Utilisateur utilisateurNotifie, Album albumCible, Utilisateur utilisateurNotifieur, int typeNotification ) {
        this.utilisateurNotifie=utilisateurNotifie;
        this.albumCible=albumCible;
        this.utilisateurNotifieur=utilisateurNotifieur;
        this.utilisateurNotifie=utilisateurNotifie;
        this.typeNotification=typeNotification;
        this.isLue=false;    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getUtilisateurNotifie() {
        return utilisateurNotifie;
    }

    public void setUtilisateurNotifie(Utilisateur utilisateurNotifie) {
        this.utilisateurNotifie = utilisateurNotifie;
    }

    public Utilisateur getUtilisateurNotifieur() {
        return utilisateurNotifieur;
    }

    public void setUtilisateurNotifieur(Utilisateur utilisateurNotifieur) {
        this.utilisateurNotifieur = utilisateurNotifieur;
    }

    public int getTypeNotification() {
        return typeNotification;
    }

    public boolean isIsLue() {
        return isLue;
    }

    public void setIsLue(boolean isLue) {
        this.isLue = isLue;
    }

    public void setTypeNotification(int typeNotification) {
        this.typeNotification = typeNotification;
    }



    public Album getAlbumCible() {
        return albumCible;
    }

    public void setAlbumCible(Album albumCible) {
        this.albumCible = albumCible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeles.Notification[ id=" + id + " ]";
    }
    
}
