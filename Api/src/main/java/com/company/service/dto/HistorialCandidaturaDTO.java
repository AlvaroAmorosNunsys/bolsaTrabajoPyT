package com.company.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.company.domain.HistorialCandidatura} entity.
 */
public class HistorialCandidaturaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private LocalDate fechaCambio;

    @NotNull
    private Boolean porDefecto;

    private LocalDate fechaModificacion;

    private String nombreEditor;


    private Long posicionId;

    private String posicionTitulo;

    private Long candidaturaId;

    private Long estadoCandidaturaId;

    private String estadoCandidaturaNombre;
    
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

    public Long getCandidaturaId() {
        return candidaturaId;
    }

    public void setCandidaturaId(Long candidaturaId) {
        this.candidaturaId = candidaturaId;
    }

    public Long getEstadoCandidaturaId() {
        return estadoCandidaturaId;
    }

    public void setEstadoCandidaturaId(Long estadoCandidaturaId) {
        this.estadoCandidaturaId = estadoCandidaturaId;
    }

    public String getEstadoCandidaturaNombre() {
        return estadoCandidaturaNombre;
    }

    public void setEstadoCandidaturaNombre(String estadoCandidaturaNombre) {
        this.estadoCandidaturaNombre = estadoCandidaturaNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistorialCandidaturaDTO)) {
            return false;
        }

        return id != null && id.equals(((HistorialCandidaturaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialCandidaturaDTO{" +
            "id=" + getId() +
            ", fechaCambio='" + getFechaCambio() + "'" +
            ", porDefecto='" + isPorDefecto() + "'" +
            ", fechaModificacion='" + getFechaModificacion() + "'" +
            ", nombreEditor='" + getNombreEditor() + "'" +
            ", posicionId=" + getPosicionId() +
            ", posicionTitulo='" + getPosicionTitulo() + "'" +
            ", candidaturaId=" + getCandidaturaId() +
            ", estadoCandidaturaId=" + getEstadoCandidaturaId() +
            ", estadoCandidaturaNombre='" + getEstadoCandidaturaNombre() + "'" +
            "}";
    }
}
