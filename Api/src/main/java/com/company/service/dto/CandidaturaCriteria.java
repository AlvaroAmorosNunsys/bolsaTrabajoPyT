package com.company.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link com.company.domain.Candidatura} entity. This class is used
 * in {@link com.company.web.rest.CandidaturaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /candidaturas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CandidaturaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter historialCandidaturaId;

    private LongFilter estadoCandidaturaId;

    private LongFilter fuenteId;

    private LongFilter posicionId;

    private LongFilter personaId;

    public CandidaturaCriteria() {
    }

    public CandidaturaCriteria(CandidaturaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.historialCandidaturaId = other.historialCandidaturaId == null ? null : other.historialCandidaturaId.copy();
        this.estadoCandidaturaId = other.estadoCandidaturaId == null ? null : other.estadoCandidaturaId.copy();
        this.fuenteId = other.fuenteId == null ? null : other.fuenteId.copy();
        this.posicionId = other.posicionId == null ? null : other.posicionId.copy();
        this.personaId = other.personaId == null ? null : other.personaId.copy();
    }

    @Override
    public CandidaturaCriteria copy() {
        return new CandidaturaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter getHistorialCandidaturaId() {
        return historialCandidaturaId;
    }

    public void setHistorialCandidaturaId(LongFilter historialCandidaturaId) {
        this.historialCandidaturaId = historialCandidaturaId;
    }

    public LongFilter getEstadoCandidaturaId() {
        return estadoCandidaturaId;
    }

    public void setEstadoCandidaturaId(LongFilter estadoCandidaturaId) {
        this.estadoCandidaturaId = estadoCandidaturaId;
    }

    public LongFilter getFuenteId() {
        return fuenteId;
    }

    public void setFuenteId(LongFilter fuenteId) {
        this.fuenteId = fuenteId;
    }

    public LongFilter getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(LongFilter posicionId) {
        this.posicionId = posicionId;
    }

    public LongFilter getPersonaId() {
        return personaId;
    }

    public void setPersonaId(LongFilter personaId) {
        this.personaId = personaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CandidaturaCriteria that = (CandidaturaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(historialCandidaturaId, that.historialCandidaturaId) &&
            Objects.equals(estadoCandidaturaId, that.estadoCandidaturaId) &&
            Objects.equals(fuenteId, that.fuenteId) &&
            Objects.equals(posicionId, that.posicionId) &&
            Objects.equals(personaId, that.personaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        historialCandidaturaId,
        estadoCandidaturaId,
        fuenteId,
        posicionId,
        personaId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CandidaturaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (historialCandidaturaId != null ? "historialCandidaturaId=" + historialCandidaturaId + ", " : "") +
                (estadoCandidaturaId != null ? "estadoCandidaturaId=" + estadoCandidaturaId + ", " : "") +
                (fuenteId != null ? "fuenteId=" + fuenteId + ", " : "") +
                (posicionId != null ? "posicionId=" + posicionId + ", " : "") +
                (personaId != null ? "personaId=" + personaId + ", " : "") +
            "}";
    }

}
