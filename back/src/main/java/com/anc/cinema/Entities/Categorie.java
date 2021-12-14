package com.anc.cinema.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Categorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "categorie")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Film> films;

    public Categorie(Long id, String name, Collection<Film> films) {
        this.id = id;
        this.name = name;
        this.films = films;
    }

    public Categorie() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Collection<Film> getFilms() {
        return this.films;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setFilms(Collection<Film> films) {
        this.films = films;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Categorie)) return false;
        final Categorie other = (Categorie) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$films = this.getFilms();
        final Object other$films = other.getFilms();
        if (this$films == null ? other$films != null : !this$films.equals(other$films)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Categorie;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $films = this.getFilms();
        result = result * PRIME + ($films == null ? 43 : $films.hashCode());
        return result;
    }

    public String toString() {
        return "Categorie(id=" + this.getId() + ", name=" + this.getName() + ", films=" + this.getFilms() + ")";
    }
}
