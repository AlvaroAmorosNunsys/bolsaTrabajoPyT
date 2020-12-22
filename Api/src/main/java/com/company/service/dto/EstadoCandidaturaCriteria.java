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
 * Criteria class for the {@link com.company.domain.EstadoCandidatura} entity. This class is used
 * in {@link com.company.web.rest.EstadoCandidaturaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /estado-candidaturas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EstadoCandidaturaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private LongFilter historialCandidaturaId;

    public EstadoCandidaturaCriteria() {
    }

    public EstadoCandidaturaCriteria(EstadoCandidaturaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.historialCandidaturaId = other.historialCandidaturaId == null ? null : other.historialCandidaturaId.copy();
    }

    @Override
    public EstadoCandidaturaCriteria copy() {
        return new EstadoCandidaturaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public LongFilter getHistorialCandidaturaId() {
        return historialCandidaturaId;
    }

    public void setHistorialCandidaturaId(LongFilter historialCandidaturaId) {
        this.historialCandidaturaId = historialCandidaturaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EstadoCandidaturaCriteria that = (EstadoCandidaturaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(historialCandidaturaId, that.historialCandidaturaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombre,
        historialCandidaturaId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadoCandidaturaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (historialCandidaturaId != null ? "historialCandidaturaId=" + historialCandidaturaId + ", " : "") +
            "}";
    }

}
