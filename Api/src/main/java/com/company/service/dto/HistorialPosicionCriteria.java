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
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.company.domain.HistorialPosicion} entity. This class is used
 * in {@link com.company.web.rest.HistorialPosicionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /historial-posicions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HistorialPosicionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fechaCambio;

    private BooleanFilter porDefecto;

    private LocalDateFilter fechaModificacion;

    private StringFilter nombreEditor;

    private LongFilter estadoPosicionId;

    private LongFilter posicionId;

    public HistorialPosicionCriteria() {
    }

    public HistorialPosicionCriteria(HistorialPosicionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fechaCambio = other.fechaCambio == null ? null : other.fechaCambio.copy();
        this.porDefecto = other.porDefecto == null ? null : other.porDefecto.copy();
        this.fechaModificacion = other.fechaModificacion == null ? null : other.fechaModificacion.copy();
        this.nombreEditor = other.nombreEditor == null ? null : other.nombreEditor.copy();
        this.estadoPosicionId = other.estadoPosicionId == null ? null : other.estadoPosicionId.copy();
        this.posicionId = other.posicionId == null ? null : other.posicionId.copy();
    }

    @Override
    public HistorialPosicionCriteria copy() {
        return new HistorialPosicionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LocalDateFilter getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateFilter fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public BooleanFilter getPorDefecto() {
        return porDefecto;
    }

    public void setPorDefecto(BooleanFilter porDefecto) {
        this.porDefecto = porDefecto;
    }

    public LocalDateFilter getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDateFilter fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public StringFilter getNombreEditor() {
        return nombreEditor;
    }

    public void setNombreEditor(StringFilter nombreEditor) {
        this.nombreEditor = nombreEditor;
    }

    public LongFilter getEstadoPosicionId() {
        return estadoPosicionId;
    }

    public void setEstadoPosicionId(LongFilter estadoPosicionId) {
        this.estadoPosicionId = estadoPosicionId;
    }

    public LongFilter getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(LongFilter posicionId) {
        this.posicionId = posicionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HistorialPosicionCriteria that = (HistorialPosicionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fechaCambio, that.fechaCambio) &&
            Objects.equals(porDefecto, that.porDefecto) &&
            Objects.equals(fechaModificacion, that.fechaModificacion) &&
            Objects.equals(nombreEditor, that.nombreEditor) &&
            Objects.equals(estadoPosicionId, that.estadoPosicionId) &&
            Objects.equals(posicionId, that.posicionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fechaCambio,
        porDefecto,
        fechaModificacion,
        nombreEditor,
        estadoPosicionId,
        posicionId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialPosicionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fechaCambio != null ? "fechaCambio=" + fechaCambio + ", " : "") +
                (porDefecto != null ? "porDefecto=" + porDefecto + ", " : "") +
                (fechaModificacion != null ? "fechaModificacion=" + fechaModificacion + ", " : "") +
                (nombreEditor != null ? "nombreEditor=" + nombreEditor + ", " : "") +
                (estadoPosicionId != null ? "estadoPosicionId=" + estadoPosicionId + ", " : "") +
                (posicionId != null ? "posicionId=" + posicionId + ", " : "") +
            "}";
    }

}
