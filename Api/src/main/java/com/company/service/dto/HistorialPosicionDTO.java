package com.company.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.company.domain.HistorialPosicion} entity.
 */
public class HistorialPosicionDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate fechaCambio;

    @NotNull
    private Boolean porDefecto;

    private LocalDate fechaModificacion;

    private String nombreEditor;


    private Long estadoPosicionId;

    private String estadoPosicionNombre;

    private Long posicionId;

    private String posicionTitulo;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDate fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public Boolean isPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(Boolean porDefecto) {
        this.porDefecto = porDefecto;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(String nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    public Long getEstadoPosicionId() {
        return estadoPosicionId;
    }

    public void setEstadoPosicionId(Long estadoPosicionId) {
        this.estadoPosicionId = estadoPosicionId;
    }

    public String getEstadoPosicionNombre() {
        return estadoPosicionNombre;
    }

    public void setEstadoPosicionNombre(String estadoPosicionNombre) {
        this.estadoPosicionNombre = estadoPosicionNombre;
    }

    public Long getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(Long posicionId) {
        this.posicionId = posicionId;
    }

    public String getPosicionTitulo() {
        return posicionTitulo;
    }

    public void setPosicionTitulo(String posicionTitulo) {
        this.posicionTitulo = posicionTitulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialPosicionDTO)) {
            return false;
        }

        return id != null && id.equals(((HistorialPosicionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialPosicionDTO{" +
            "id=" + getId() +
            ", fechaCambio='" + getFechaCambio() + "'" +
            ", porDefecto='" + isPorDefecto() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", nombreEditor='" + getNombreEditor() + "'" +
            ", estadoPosicionId=" + getEstadoPosicionId() +
            ", estadoPosicionNombre='" + getEstadoPosicionNombre() + "'" +
            ", posicionId=" + getPosicionId() +
            ", posicionTitulo='" + getPosicionTitulo() + "'" +
            "}";
    }
}
