package modeles;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * Entité representant un compte utilisateur de l'application.
 *
 */
@Entity
public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom, prenom, login, email, motDePasse;

    @OneToMany(mappedBy = "utilisateur")
    private List<Album> albums;

    @ManyToMany
    @JoinColumn(name = "album", referencedColumnName = "utilisateursPartages")
    private List<Album> albumsPartages;
    
    private String imageProfil;
   
    @OneToMany(mappedBy = "utilisateurNotifie")
    private List<Notification> listeNotification;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String login, String email, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.email = email;
        this.motDePasse = motDePasse;
        this.imageProfil="default.jpg";
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public void ajouterAlbum(Album album) {
        albums.add(album);
    }

    public Album getAlbum(int index) {
        return albums.get(index);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeles.Utilisateur[ id=" + id + " ]";
    }

    public List<Notification> getListeNotification() {
        return listeNotification;
    }

    public void setListeNotification(List<Notification> listeNotification) {
        this.listeNotification = listeNotification;
    }

    /**
     * @return the albumsPartages
     */
    public List<Album> getAlbumsPartages() {
        return albumsPartages;
    }

    public void setAlbumsPartages(List<Album> albumsPartages) {
        this.albumsPartages = albumsPartages;
    }

    public String getImageProfil() {
        return imageProfil;
    }

    public void setImageProfil(String imageProfil) {
        this.imageProfil = imageProfil;
    }

}
