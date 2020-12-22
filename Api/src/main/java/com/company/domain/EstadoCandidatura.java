package com.company.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A EstadoCandidatura.
 */
@Entity
@Table(name = "estado_candidatura")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EstadoCandidatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "estadoCandidatura")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HistorialCandidatura> historialCandidaturas = new HashSet<>();

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

    public EstadoCandidatura nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<HistorialCandidatura> getHistorialCandidaturas() {
        return historialCandidaturas;
    }

    public EstadoCandidatura historialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
        return this;
    }

    public EstadoCandidatura addHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.add(historialCandidatura);
        historialCandidatura.setEstadoCandidatura(this);
        return this;
    }

    public EstadoCandidatura removeHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.remove(historialCandidatura);
        historialCandidatura.setEstadoCandidatura(null);
        return this;
    }

    public void setHistorialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EstadoCandidatura)) {
            return false;
        }
        return id != null && id.equals(((EstadoCandidatura) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadoCandidatura{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
