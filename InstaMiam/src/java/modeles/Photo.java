/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeles;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 */
@Entity
public class Photo implements Serializable {

    @ManyToOne
    @JoinColumn(name = "album", referencedColumnName = "id")
    private Album album;

    private String nom;

    @OneToOne
    @JoinColumn(name = "photoDeCouverture", referencedColumnName = "id")
    private Album albumCouvert;

    private String nomFichier;

    private String description;

    @OneToMany(mappedBy = "photo")
    private List<Commentaire> commentaires;

    private String idUnique;

    public Photo() {
        nom = "";
        nomFichier = "";
    }

    public Photo(String nom) {
        this.nom = nom;
        idUnique = UUID.randomUUID().toString();
        nomFichier = idUnique + nom.substring(nom.indexOf("."));
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomFichier() {
        return nomFichier;
    }

    public void setNomFichier(String nomFichier) {
        this.nomFichier = nomFichier;
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
        if (!(object instanceof Photo)) {
            return false;
        }
        Photo other = (Photo) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modeles.Photo[ id=" + id + " ]";
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the albumCouvert
     */
    public Album getAlbumCouvert() {
        return albumCouvert;
    }

    /**
     * @param albumCouvert the albumCouvert to set
     */
    public void setAlbumCouvert(Album albumCouvert) {
        this.albumCouvert = albumCouvert;
    }

}
