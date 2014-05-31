package modeles;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Entité representant un commentaire.
 */
@Entity
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Album lié au commentaire si commentaire d'album
    @JoinColumn(name = "album", referencedColumnName = "id")
    @ManyToOne
    private Album album;
    
    // Photo liée au commentaire si commentaire d'une photo
    @JoinColumn(name = "photo", referencedColumnName = "id")
    @ManyToOne
    private Photo photo;
    
    private String text;
    
    // Auteur du commentaire
    @JoinColumn(name = "auteur", referencedColumnName = "id")
    @ManyToOne
    private Utilisateur auteur;
    
    public Commentaire() {
    }
    
    /**
     * Constructeur d'un commentaire
     * @param text 
     */
    public Commentaire(String text) {
        this.text=text;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeles.Commentaire[ id=" + id + " ]";
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Utilisateur getAuteur() {
        return auteur;
    }

    public void setAuteur(Utilisateur auteur) {
        this.auteur = auteur;
    }
    
    
    
}
