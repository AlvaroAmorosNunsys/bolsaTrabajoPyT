package com.company.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EstadoPosicion.
 */
@Entity
@Table(name = "estado_posicion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EstadoPosicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "estadoPosicion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HistorialPosicion> historialPosicions = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public EstadoPosicion nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<HistorialPosicion> getHistorialPosicions() {
        return historialPosicions;
    }

    public EstadoPosicion historialPosicions(Set<HistorialPosicion> historialPosicions) {
        this.historialPosicions = historialPosicions;
        return this;
    }

    public EstadoPosicion addHistorialPosicion(HistorialPosicion historialPosicion) {
        this.historialPosicions.add(historialPosicion);
        historialPosicion.setEstadoPosicion(this);
        return this;
    }

    public EstadoPosicion removeHistorialPosicion(HistorialPosicion historialPosicion) {
        this.historialPosicions.remove(historialPosicion);
        historialPosicion.setEstadoPosicion(null);
        return this;
    }

    public void setHistorialPosicions(Set<HistorialPosicion> historialPosicions) {
        this.historialPosicions = historialPosicions;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadoPosicion)) {
            return false;
        }
        return id != null && id.equals(((EstadoPosicion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadoPosicion{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
