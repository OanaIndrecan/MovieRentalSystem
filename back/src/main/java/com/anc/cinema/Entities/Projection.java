package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
public class Projection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateProjection;
    private double prix;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Salle salle;
    @ManyToOne
    private Film film;
    @OneToMany(mappedBy = "projection")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;
    @ManyToOne
    private Seance seance;

    public Projection(Long id, Date dateProjection, double prix, Salle salle, Film film, Collection<Ticket> tickets, Seance seance) {
        this.id = id;
        this.dateProjection = dateProjection;
        this.prix = prix;
        this.salle = salle;
        this.film = film;
        this.tickets = tickets;
        this.seance = seance;
    }

    public Projection() {
    }

    public Long getId() {
        return this.id;
    }

    public Date getDateProjection() {
        return this.dateProjection;
    }

    public double getPrix() {
        return this.prix;
    }

    public Salle getSalle() {
        return this.salle;
    }

    public Film getFilm() {
        return this.film;
    }

    public Collection<Ticket> getTickets() {
        return this.tickets;
    }

    public Seance getSeance() {
        return this.seance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateProjection(Date dateProjection) {
        this.dateProjection = dateProjection;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Projection)) return false;
        final Projection other = (Projection) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$dateProjection = this.getDateProjection();
        final Object other$dateProjection = other.getDateProjection();
        if (this$dateProjection == null ? other$dateProjection != null : !this$dateProjection.equals(other$dateProjection))
            return false;
        if (Double.compare(this.getPrix(), other.getPrix()) != 0) return false;
        final Object this$salle = this.getSalle();
        final Object other$salle = other.getSalle();
        if (this$salle == null ? other$salle != null : !this$salle.equals(other$salle)) return false;
        final Object this$film = this.getFilm();
        final Object other$film = other.getFilm();
        if (this$film == null ? other$film != null : !this$film.equals(other$film)) return false;
        final Object this$tickets = this.getTickets();
        final Object other$tickets = other.getTickets();
        if (this$tickets == null ? other$tickets != null : !this$tickets.equals(other$tickets)) return false;
        final Object this$seance = this.getSeance();
        final Object other$seance = other.getSeance();
        if (this$seance == null ? other$seance != null : !this$seance.equals(other$seance)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Projection;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $dateProjection = this.getDateProjection();
        result = result * PRIME + ($dateProjection == null ? 43 : $dateProjection.hashCode());
        final long $prix = Double.doubleToLongBits(this.getPrix());
        result = result * PRIME + (int) ($prix >>> 32 ^ $prix);
        final Object $salle = this.getSalle();
        result = result * PRIME + ($salle == null ? 43 : $salle.hashCode());
        final Object $film = this.getFilm();
        result = result * PRIME + ($film == null ? 43 : $film.hashCode());
        final Object $tickets = this.getTickets();
        result = result * PRIME + ($tickets == null ? 43 : $tickets.hashCode());
        final Object $seance = this.getSeance();
        result = result * PRIME + ($seance == null ? 43 : $seance.hashCode());
        return result;
    }

    public String toString() {
        return "Projection(id=" + this.getId() + ", dateProjection=" + this.getDateProjection() + ", prix=" + this.getPrix() + ", salle=" + this.getSalle() + ", film=" + this.getFilm() + ", tickets=" + this.getTickets() + ", seance=" + this.getSeance() + ")";
    }
}



