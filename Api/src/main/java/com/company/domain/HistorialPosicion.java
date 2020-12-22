package com.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A HistorialPosicion.
 */
@Entity
@Table(name = "historial_posicion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class HistorialPosicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "fecha_cambio", nullable = false)
    private LocalDate fechaCambio;

    @NotNull
    @Column(name = "por_defecto", nullable = false)
    private Boolean porDefecto;

    @Column(name = "fecha_modificacion")
    private LocalDate fechaModificacion;

    @Column(name = "nombre_editor")
    private String nombreEditor;

    @ManyToOne
    @JsonIgnoreProperties(value = "historialPosicions", allowSetters = true)
    private EstadoPosicion estadoPosicion;

    @ManyToOne
    @JsonIgnoreProperties(value = "historialPosicions", allowSetters = true)
    private Posicion posicion;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public HistorialPosicion fechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
        return this;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Boolean isPorDefecto() {
        return porDefecto;
    }

    public HistorialPosicion porDefecto(Boolean porDefecto) {
        this.porDefecto = porDefecto;
        return this;
    }

    public void setPorDefecto(Boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public HistorialPosicion fechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
        return this;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public HistorialPosicion nombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
        return this;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    public EstadoPosicion getEstadoPosicion() {
        return estadoPosicion;
    }

    public HistorialPosicion estadoPosicion(EstadoPosicion estadoPosicion) {
        this.estadoPosicion = estadoPosicion;
        return this;
    }

    public void setEstadoPosicion(EstadoPosicion estadoPosicion) {
        this.estadoPosicion = estadoPosicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public HistorialPosicion posicion(Posicion posicion) {
        this.posicion = posicion;
        return this;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialPosicion)) {
            return false;
        }
        return id != null && id.equals(((HistorialPosicion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialPosicion{" +
            "id=" + getId() +
            ", fechaCambio='" + getFechaCambio() + "'" +
            ", porDefecto='" + isPorDefecto() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", nombreEditor='" + getNombreEditor() + "'" +
            "}";
    }
}
