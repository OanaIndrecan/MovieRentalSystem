package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomClient;
    private double prix;
    private Integer codePayment;
    private boolean reserve;
    @ManyToOne
    private Place place;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Projection projection;

    public Ticket(Long id, String nomClient, double prix, Integer codePayment, boolean reserve, Place place, Projection projection) {
        this.id = id;
        this.nomClient = nomClient;
        this.prix = prix;
        this.codePayment = codePayment;
        this.reserve = reserve;
        this.place = place;
        this.projection = projection;
    }

    public Ticket() {
    }

    public Long getId() {
        return this.id;
    }

    public String getNomClient() {
        return this.nomClient;
    }

    public double getPrix() {
        return this.prix;
    }

    public Integer getCodePayment() {
        return this.codePayment;
    }

    public boolean isReserve() {
        return this.reserve;
    }

    public Place getPlace() {
        return this.place;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setCodePayment(Integer codePayment) {
        this.codePayment = codePayment;
    }

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ticket)) return false;
        final Ticket other = (Ticket) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$nomClient = this.getNomClient();
        final Object other$nomClient = other.getNomClient();
        if (this$nomClient == null ? other$nomClient != null : !this$nomClient.equals(other$nomClient)) return false;
        if (Double.compare(this.getPrix(), other.getPrix()) != 0) return false;
        final Object this$codePayment = this.getCodePayment();
        final Object other$codePayment = other.getCodePayment();
        if (this$codePayment == null ? other$codePayment != null : !this$codePayment.equals(other$codePayment))
            return false;
        if (this.isReserve() != other.isReserve()) return false;
        final Object this$place = this.getPlace();
        final Object other$place = other.getPlace();
        if (this$place == null ? other$place != null : !this$place.equals(other$place)) return false;
        final Object this$projection = this.getProjection();
        final Object other$projection = other.getProjection();
        if (this$projection == null ? other$projection != null : !this$projection.equals(other$projection))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ticket;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $nomClient = this.getNomClient();
        result = result * PRIME + ($nomClient == null ? 43 : $nomClient.hashCode());
        final long $prix = Double.doubleToLongBits(this.getPrix());
        result = result * PRIME + (int) ($prix >>> 32 ^ $prix);
        final Object $codePayment = this.getCodePayment();
        result = result * PRIME + ($codePayment == null ? 43 : $codePayment.hashCode());
        result = result * PRIME + (this.isReserve() ? 79 : 97);
        final Object $place = this.getPlace();
        result = result * PRIME + ($place == null ? 43 : $place.hashCode());
        final Object $projection = this.getProjection();
        result = result * PRIME + ($projection == null ? 43 : $projection.hashCode());
        return result;
    }

    public String toString() {
        return "Ticket(id=" + this.getId() + ", nomClient=" + this.getNomClient() + ", prix=" + this.getPrix() + ", codePayment=" + this.getCodePayment() + ", reserve=" + this.isReserve() + ", place=" + this.getPlace() + ", projection=" + this.getProjection() + ")";
    }
}
