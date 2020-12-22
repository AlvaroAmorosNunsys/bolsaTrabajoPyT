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
 * Criteria class for the {@link com.company.domain.EstadoPosicion} entity. This class is used
 * in {@link com.company.web.rest.EstadoPosicionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /estado-posicions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EstadoPosicionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombre;

    private LongFilter historialPosicionId;

    public EstadoPosicionCriteria() {
    }

    public EstadoPosicionCriteria(EstadoPosicionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.historialPosicionId = other.historialPosicionId == null ? null : other.historialPosicionId.copy();
    }

    @Override
    public EstadoPosicionCriteria copy() {
        return new EstadoPosicionCriteria(this);
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

    public LongFilter getHistorialPosicionId() {
        return historialPosicionId;
    }

    public void setHistorialPosicionId(LongFilter historialPosicionId) {
        this.historialPosicionId = historialPosicionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EstadoPosicionCriteria that = (EstadoPosicionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(historialPosicionId, that.historialPosicionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombre,
        historialPosicionId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EstadoPosicionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (historialPosicionId != null ? "historialPosicionId=" + historialPosicionId + ", " : "") +
            "}";
    }

}
