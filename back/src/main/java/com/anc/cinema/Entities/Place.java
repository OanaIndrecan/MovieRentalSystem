package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private double longitude, latitude, altitude;
    @ManyToOne()
    private Salle salle;
    @OneToMany(mappedBy = "place")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Ticket> tickets;

    public Place(Long id, int numero, double longitude, double latitude, double altitude, Salle salle, Collection<Ticket> tickets) {
        this.id = id;
        this.numero = numero;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.salle = salle;
        this.tickets = tickets;
    }

    public Place() {
    }

    public Long getId() {
        return this.id;
    }

    public int getNumero() {
        return this.numero;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public Salle getSalle() {
        return this.salle;
    }

    public Collection<Ticket> getTickets() {
        return this.tickets;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Place)) return false;
        final Place other = (Place) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (this.getNumero() != other.getNumero()) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getAltitude(), other.getAltitude()) != 0) return false;
        final Object this$salle = this.getSalle();
        final Object other$salle = other.getSalle();
        if (this$salle == null ? other$salle != null : !this$salle.equals(other$salle)) return false;
        final Object this$tickets = this.getTickets();
        final Object other$tickets = other.getTickets();
        if (this$tickets == null ? other$tickets != null : !this$tickets.equals(other$tickets)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Place;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        result = result * PRIME + this.getNumero();
        final long $longitude = Double.doubleToLongBits(this.getLongitude());
        result = result * PRIME + (int) ($longitude >>> 32 ^ $longitude);
        final long $latitude = Double.doubleToLongBits(this.getLatitude());
        result = result * PRIME + (int) ($latitude >>> 32 ^ $latitude);
        final long $altitude = Double.doubleToLongBits(this.getAltitude());
        result = result * PRIME + (int) ($altitude >>> 32 ^ $altitude);
        final Object $salle = this.getSalle();
        result = result * PRIME + ($salle == null ? 43 : $salle.hashCode());
        final Object $tickets = this.getTickets();
        result = result * PRIME + ($tickets == null ? 43 : $tickets.hashCode());
        return result;
    }

    public String toString() {
        return "Place(id=" + this.getId() + ", numero=" + this.getNumero() + ", longitude=" + this.getLongitude() + ", latitude=" + this.getLatitude() + ", altitude=" + this.getAltitude() + ", salle=" + this.getSalle() + ", tickets=" + this.getTickets() + ")";
    }
}
