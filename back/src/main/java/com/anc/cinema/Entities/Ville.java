package com.anc.cinema.Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    @OneToMany(mappedBy = "ville")
    private Collection<Cinema> cinemas;

    public Ville(Long id, String name, double longitude, double latitude, double altitude, Collection<Cinema> cinemas) {
        this.id = id;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.cinemas = cinemas;
    }

    public Ville() {
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

    public Collection<Cinema> getCinemas() {
        return this.cinemas;
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

    public void setCinemas(Collection<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ville)) return false;
        final Ville other = (Ville) o;
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
        final Object this$cinemas = this.getCinemas();
        final Object other$cinemas = other.getCinemas();
        if (this$cinemas == null ? other$cinemas != null : !this$cinemas.equals(other$cinemas)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ville;
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
        final Object $cinemas = this.getCinemas();
        result = result * PRIME + ($cinemas == null ? 43 : $cinemas.hashCode());
        return result;
    }

    public String toString() {
        return "Ville(id=" + this.getId() + ", name=" + this.getName() + ", longitude=" + this.getLongitude() + ", latitude=" + this.getLatitude() + ", altitude=" + this.getAltitude() + ", cinemas=" + this.getCinemas() + ")";
    }
}
