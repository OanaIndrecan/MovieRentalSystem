package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    private int nombreSales;

    @OneToMany(mappedBy = "cinema")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

    public Cinema(Long id, String name, double longitude, double latitude, double altitude, int nombreSales, Collection<Salle> salles, Ville ville) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.nombreSales = nombreSales;
        this.salles = salles;
        this.ville = ville;
    }

    public Cinema() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
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

    public int getNombreSales() {
        return this.nombreSales;
    }

    public Collection<Salle> getSalles() {
        return this.salles;
    }

    public Ville getVille() {
        return this.ville;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setNombreSales(int nombreSales) {
        this.nombreSales = nombreSales;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setSalles(Collection<Salle> salles) {
        this.salles = salles;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Cinema)) return false;
        final Cinema other = (Cinema) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (Double.compare(this.getLongitude(), other.getLongitude()) != 0) return false;
        if (Double.compare(this.getLatitude(), other.getLatitude()) != 0) return false;
        if (Double.compare(this.getAltitude(), other.getAltitude()) != 0) return false;
        if (this.getNombreSales() != other.getNombreSales()) return false;
        final Object this$salles = this.getSalles();
        final Object other$salles = other.getSalles();
        if (this$salles == null ? other$salles != null : !this$salles.equals(other$salles)) return false;
        final Object this$ville = this.getVille();
        final Object other$ville = other.getVille();
        if (this$ville == null ? other$ville != null : !this$ville.equals(other$ville)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Cinema;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final long $longitude = Double.doubleToLongBits(this.getLongitude());
        result = result * PRIME + (int) ($longitude >>> 32 ^ $longitude);
        final long $latitude = Double.doubleToLongBits(this.getLatitude());
        result = result * PRIME + (int) ($latitude >>> 32 ^ $latitude);
        final long $altitude = Double.doubleToLongBits(this.getAltitude());
        result = result * PRIME + (int) ($altitude >>> 32 ^ $altitude);
        result = result * PRIME + this.getNombreSales();
        final Object $salles = this.getSalles();
        result = result * PRIME + ($salles == null ? 43 : $salles.hashCode());
        final Object $ville = this.getVille();
        result = result * PRIME + ($ville == null ? 43 : $ville.hashCode());
        return result;
    }

    public String toString() {
        return "Cinema(id=" + this.getId() + ", name=" + this.getName() + ", longitude=" + this.getLongitude() + ", latitude=" + this.getLatitude() + ", altitude=" + this.getAltitude() + ", nombreSales=" + this.getNombreSales() + ", salles=" + this.getSalles() + ", ville=" + this.getVille() + ")";
    }
}
