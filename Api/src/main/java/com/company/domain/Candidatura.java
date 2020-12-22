package com.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Candidatura.
 */
@Entity
@Table(name = "candidatura")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Candidatura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToMany(mappedBy = "candidatura")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HistorialCandidatura> historialCandidaturas = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "candidaturas", allowSetters = true)
    private EstadoCandidatura estadoCandidatura;

    @ManyToOne
    @JsonIgnoreProperties(value = "candidaturas", allowSetters = true)
    private Fuente fuente;

    @ManyToOne
    @JsonIgnoreProperties(value = "candidaturas", allowSetters = true)
    private Posicion posicion;

    @ManyToOne
    @JsonIgnoreProperties(value = "candidaturas", allowSetters = true)
    private Persona persona;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<HistorialCandidatura> getHistorialCandidaturas() {
        return historialCandidaturas;
    }

    public Candidatura historialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
        return this;
    }

    public Candidatura addHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.add(historialCandidatura);
        historialCandidatura.setCandidatura(this);
        return this;
    }

    public Candidatura removeHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.remove(historialCandidatura);
        historialCandidatura.setCandidatura(null);
        return this;
    }

    public void setHistorialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
    }

    public EstadoCandidatura getEstadoCandidatura() {
        return estadoCandidatura;
    }

    public Candidatura estadoCandidatura(EstadoCandidatura estadoCandidatura) {
        this.estadoCandidatura = estadoCandidatura;
        return this;
    }

    public void setEstadoCandidatura(EstadoCandidatura estadoCandidatura) {
        this.estadoCandidatura = estadoCandidatura;
    }

    public Fuente getFuente() {
        return fuente;
    }

    public Candidatura fuente(Fuente fuente) {
        this.fuente = fuente;
        return this;
    }

    public void setFuente(Fuente fuente) {
        this.fuente = fuente;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public Candidatura posicion(Posicion posicion) {
        this.posicion = posicion;
        return this;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public Persona getPersona() {
        return persona;
    }

    public Candidatura persona(Persona persona) {
        this.persona = persona;
        return this;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidatura)) {
            return false;
        }
        return id != null && id.equals(((Candidatura) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidatura{" +
            "id=" + getId() +
            "}";
    }
}
