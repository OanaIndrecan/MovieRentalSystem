package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;
    @ManyToOne

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;

    public Salle(Long id, String name, int nombrePlace, Cinema cinema, Collection<Place> places, Collection<Projection> projections) {
        this.id = id;
        this.name = name;
        this.nombrePlace = nombrePlace;
        this.cinema = cinema;
        this.places = places;
        this.projections = projections;
    }

    public Salle() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getNombrePlace() {
        return this.nombrePlace;
    }

    public Cinema getCinema() {
        return this.cinema;
    }

    public Collection<Place> getPlaces() {
        return this.places;
    }

    public Collection<Projection> getProjections() {
        return this.projections;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNombrePlace(int nombrePlace) {
        this.nombrePlace = nombrePlace;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setPlaces(Collection<Place> places) {
        this.places = places;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setProjections(Collection<Projection> projections) {
        this.projections = projections;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Salle)) return false;
        final Salle other = (Salle) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        if (this.getNombrePlace() != other.getNombrePlace()) return false;
        final Object this$cinema = this.getCinema();
        final Object other$cinema = other.getCinema();
        if (this$cinema == null ? other$cinema != null : !this$cinema.equals(other$cinema)) return false;
        final Object this$places = this.getPlaces();
        final Object other$places = other.getPlaces();
        if (this$places == null ? other$places != null : !this$places.equals(other$places)) return false;
        final Object this$projections = this.getProjections();
        final Object other$projections = other.getProjections();
        if (this$projections == null ? other$projections != null : !this$projections.equals(other$projections))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Salle;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        result = result * PRIME + this.getNombrePlace();
        final Object $cinema = this.getCinema();
        result = result * PRIME + ($cinema == null ? 43 : $cinema.hashCode());
        final Object $places = this.getPlaces();
        result = result * PRIME + ($places == null ? 43 : $places.hashCode());
        final Object $projections = this.getProjections();
        result = result * PRIME + ($projections == null ? 43 : $projections.hashCode());
        return result;
    }

    public String toString() {
        return "Salle(id=" + this.getId() + ", name=" + this.getName() + ", nombrePlace=" + this.getNombrePlace() + ", cinema=" + this.getCinema() + ", places=" + this.getPlaces() + ", projections=" + this.getProjections() + ")";
    }
}
