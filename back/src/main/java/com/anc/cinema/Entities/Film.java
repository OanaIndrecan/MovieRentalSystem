package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private String realisateur;
    private Date dateSortie;
    private int dure;
    private double rating;
    private String photo;

    @OneToMany(mappedBy = "film")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;
    @ManyToOne
    private Categorie categorie;

    public Film(Long id, String titre, String description, String realisateur, Date dateSortie, int dure, double rating, String photo, Collection<Projection> projections, Categorie categorie) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.realisateur = realisateur;
        this.dateSortie = dateSortie;
        this.dure = dure;
        this.rating = rating;
        this.photo = photo;
        this.projections = projections;
        this.categorie = categorie;
    }

    public Film() {
    }

    public Long getId() {
        return this.id;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getDescription() {
        return this.description;
    }

    public String getRealisateur() {
        return this.realisateur;
    }

    public Date getDateSortie() {
        return this.dateSortie;
    }

    public int getDure() {
        return this.dure;
    }

    public double getRating() {
        return this.rating;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Collection<Projection> getProjections() {
        return this.projections;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public void setDure(int dure) {
        this.dure = dure;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setProjections(Collection<Projection> projections) {
        this.projections = projections;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Film)) return false;
        final Film other = (Film) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$titre = this.getTitre();
        final Object other$titre = other.getTitre();
        if (this$titre == null ? other$titre != null : !this$titre.equals(other$titre)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$realisateur = this.getRealisateur();
        final Object other$realisateur = other.getRealisateur();
        if (this$realisateur == null ? other$realisateur != null : !this$realisateur.equals(other$realisateur))
            return false;
        final Object this$dateSortie = this.getDateSortie();
        final Object other$dateSortie = other.getDateSortie();
        if (this$dateSortie == null ? other$dateSortie != null : !this$dateSortie.equals(other$dateSortie))
            return false;
        if (this.getDure() != other.getDure()) return false;
        if (Double.compare(this.getRating(), other.getRating()) != 0) return false;
        final Object this$photo = this.getPhoto();
        final Object other$photo = other.getPhoto();
        if (this$photo == null ? other$photo != null : !this$photo.equals(other$photo)) return false;
        final Object this$projections = this.getProjections();
        final Object other$projections = other.getProjections();
        if (this$projections == null ? other$projections != null : !this$projections.equals(other$projections))
            return false;
        final Object this$categorie = this.getCategorie();
        final Object other$categorie = other.getCategorie();
        if (this$categorie == null ? other$categorie != null : !this$categorie.equals(other$categorie)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Film;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $titre = this.getTitre();
        result = result * PRIME + ($titre == null ? 43 : $titre.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $realisateur = this.getRealisateur();
        result = result * PRIME + ($realisateur == null ? 43 : $realisateur.hashCode());
        final Object $dateSortie = this.getDateSortie();
        result = result * PRIME + ($dateSortie == null ? 43 : $dateSortie.hashCode());
        result = result * PRIME + this.getDure();
        final long $rating = Double.doubleToLongBits(this.getRating());
        result = result * PRIME + (int) ($rating >>> 32 ^ $rating);
        final Object $photo = this.getPhoto();
        result = result * PRIME + ($photo == null ? 43 : $photo.hashCode());
        final Object $projections = this.getProjections();
        result = result * PRIME + ($projections == null ? 43 : $projections.hashCode());
        final Object $categorie = this.getCategorie();
        result = result * PRIME + ($categorie == null ? 43 : $categorie.hashCode());
        return result;
    }

    public String toString() {
        return "Film(id=" + this.getId() + ", titre=" + this.getTitre() + ", description=" + this.getDescription() + ", realisateur=" + this.getRealisateur() + ", dateSortie=" + this.getDateSortie() + ", dure=" + this.getDure() + ", rating=" + this.getRating() + ", photo=" + this.getPhoto() + ", projections=" + this.getProjections() + ", categorie=" + this.getCategorie() + ")";
    }
}
