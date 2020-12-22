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
 * Criteria class for the {@link com.company.domain.HistorialCandidatura} entity. This class is used
 * in {@link com.company.web.rest.HistorialCandidaturaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /historial-candidaturas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HistorialCandidaturaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter fechaCambio;

    private BooleanFilter porDefecto;

    private LocalDateFilter fechaModificacion;

    private StringFilter nombreEditor;

    private LongFilter posicionId;

    private LongFilter candidaturaId;

    private LongFilter estadoCandidaturaId;

    public HistorialCandidaturaCriteria() {
    }

    public HistorialCandidaturaCriteria(HistorialCandidaturaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fechaCambio = other.fechaCambio == null ? null : other.fechaCambio.copy();
        this.porDefecto = other.porDefecto == null ? null : other.porDefecto.copy();
        this.fechaModificacion = other.fechaModificacion == null ? null : other.fechaModificacion.copy();
        this.nombreEditor = other.nombreEditor == null ? null : other.nombreEditor.copy();
        this.posicionId = other.posicionId == null ? null : other.posicionId.copy();
        this.candidaturaId = other.candidaturaId == null ? null : other.candidaturaId.copy();
        this.estadoCandidaturaId = other.estadoCandidaturaId == null ? null : other.estadoCandidaturaId.copy();
    }

    @Override
    public HistorialCandidaturaCriteria copy() {
        return new HistorialCandidaturaCriteria(this);
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

    public LongFilter getPosicionId() {
        return posicionId;
    }

    public void setPosicionId(LongFilter posicionId) {
        this.posicionId = posicionId;
    }

    public LongFilter getCandidaturaId() {
        return candidaturaId;
    }

    public void setCandidaturaId(LongFilter candidaturaId) {
        this.candidaturaId = candidaturaId;
    }

    public LongFilter getEstadoCandidaturaId() {
        return estadoCandidaturaId;
    }

    public void setEstadoCandidaturaId(LongFilter estadoCandidaturaId) {
        this.estadoCandidaturaId = estadoCandidaturaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HistorialCandidaturaCriteria that = (HistorialCandidaturaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(fechaCambio, that.fechaCambio) &&
            Objects.equals(porDefecto, that.porDefecto) &&
            Objects.equals(fechaModificacion, that.fechaModificacion) &&
            Objects.equals(nombreEditor, that.nombreEditor) &&
            Objects.equals(posicionId, that.posicionId) &&
            Objects.equals(candidaturaId, that.candidaturaId) &&
            Objects.equals(estadoCandidaturaId, that.estadoCandidaturaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        fechaCambio,
        porDefecto,
        fechaModificacion,
        nombreEditor,
        posicionId,
        candidaturaId,
        estadoCandidaturaId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "HistorialCandidaturaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (fechaCambio != null ? "fechaCambio=" + fechaCambio + ", " : "") +
                (porDefecto != null ? "porDefecto=" + porDefecto + ", " : "") +
                (fechaModificacion != null ? "fechaModificacion=" + fechaModificacion + ", " : "") +
                (nombreEditor != null ? "nombreEditor=" + nombreEditor + ", " : "") +
                (posicionId != null ? "posicionId=" + posicionId + ", " : "") +
                (candidaturaId != null ? "candidaturaId=" + candidaturaId + ", " : "") +
                (estadoCandidaturaId != null ? "estadoCandidaturaId=" + estadoCandidaturaId + ", " : "") +
            "}";
    }

}
