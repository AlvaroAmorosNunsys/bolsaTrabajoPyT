package com.company.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.company.domain.Candidatura} entity.
 */
public class CandidaturaDTO implements Serializable {
    
    private Long id;


    private Long estadoCandidaturaId;

    private String estadoCandidaturaNombre;

    private Long fuenteId;

    private String fuenteNombre;

    private Long posicionId;

    private String posicionTitulo;

    private Long personaId;

    private String personaDocumentoIdentidad;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(Long fuenteId) {
        this.fuenteId = fuenteId;
    }

    public String getFuenteNombre() {
        return fuenteNombre;
    }

    public void setFuenteNombre(String fuenteNombre) {
        this.fuenteNombre = fuenteNombre;
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

    public Long getPersonaId() {
        return personaId;
    }

    public void setPersonaId(Long personaId) {
        this.personaId = personaId;
    }

    public String getPersonaDocumentoIdentidad() {
        return personaDocumentoIdentidad;
    }

    public void setPersonaDocumentoIdentidad(String personaDocumentoIdentidad) {
        this.personaDocumentoIdentidad = personaDocumentoIdentidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CandidaturaDTO)) {
            return false;
        }

        return id != null && id.equals(((CandidaturaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CandidaturaDTO{" +
            "id=" + getId() +
            ", estadoCandidaturaId=" + getEstadoCandidaturaId() +
            ", estadoCandidaturaNombre='" + getEstadoCandidaturaNombre() + "'" +
            ", fuenteId=" + getFuenteId() +
            ", fuenteNombre='" + getFuenteNombre() + "'" +
            ", posicionId=" + getPosicionId() +
            ", posicionTitulo='" + getPosicionTitulo() + "'" +
            ", personaId=" + getPersonaId() +
            ", personaDocumentoIdentidad='" + getPersonaDocumentoIdentidad() + "'" +
            "}";
    }
}
