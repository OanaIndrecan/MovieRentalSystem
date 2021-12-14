package com.anc.cinema.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;

    public Seance(Long id, Date heureDebut) {
        this.id = id;
        this.heureDebut = heureDebut;
    }

    public Seance() {
    }

    public Long getId() {
        return this.id;
    }

    public Date getHeureDebut() {
        return this.heureDebut;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Seance)) return false;
        final Seance other = (Seance) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$heureDebut = this.getHeureDebut();
        final Object other$heureDebut = other.getHeureDebut();
        if (this$heureDebut == null ? other$heureDebut != null : !this$heureDebut.equals(other$heureDebut))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Seance;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $heureDebut = this.getHeureDebut();
        result = result * PRIME + ($heureDebut == null ? 43 : $heureDebut.hashCode());
        return result;
    }

    public String toString() {
        return "Seance(id=" + this.getId() + ", heureDebut=" + this.getHeureDebut() + ")";
    }
}
