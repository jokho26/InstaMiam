/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Siddi Steven
 */
@Entity
public class Album implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nomAlbum;

    @OneToMany(mappedBy = "album")
    private List<Photo> photos;

    @OneToOne
    @JoinColumn(name = "albumCouvert", referencedColumnName = "id")
    private Photo photoDeCouverture;

    @ManyToMany
    @JoinColumn(name = "utilisateur", referencedColumnName = "albumsPartages")
    private List<Utilisateur> utilisateursPartages;

    @OneToMany(mappedBy = "album")
    private List<Commentaire> commentaires;

    @ManyToOne
    @JoinColumn(name = "utilisateur", referencedColumnName = "id")
    private Utilisateur utilisateur;

    private String idUnique;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Album() {

    }

    public Album(String nomAlbum) {
        this.nomAlbum = nomAlbum;
        idUnique = UUID.randomUUID().toString();
    }

    public String getNomAlbum() {
        return nomAlbum;
    }

    public void setNomAlbum(String nomAlbum) {
        this.nomAlbum = nomAlbum;
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
        if (!(object instanceof Album)) {
            return false;
        }
        Album other = (Album) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeles.Album[ id=" + id + " ]";
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void ajouterPhoto(Photo photo) {
        photos.add(photo);
    }

    public List<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(List<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

    public void ajouterCommentaire(Commentaire c) {
        this.commentaires.add(c);
    }

    public String getIdUnique() {
        return idUnique;
    }

    public void setIdUnique(String idUnique) {
        this.idUnique = idUnique;
    }

    /**
     * @return the utilisateursPartages
     */
    public List<Utilisateur> getUtilisateursPartages() {
        return utilisateursPartages;
    }

    /**
     * @param utilisateursPartages the utilisateursPartages to set
     */
    public void setUtilisateursPartages(List<Utilisateur> utilisateursPartages) {
        this.utilisateursPartages = utilisateursPartages;
    }

    /**
     * @return the photoDeCouverture
     */
    public Photo getPhotoDeCouverture() {
        return photoDeCouverture;
    }

    /**
     * @param photoDeCouverture the photoDeCouverture to set
     */
    public void setPhotoDeCouverture(Photo photoDeCouverture) {
        this.photoDeCouverture = photoDeCouverture;
    }

}
