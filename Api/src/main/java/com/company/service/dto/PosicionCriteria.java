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
 * Criteria class for the {@link com.company.domain.Posicion} entity. This class is used
 * in {@link com.company.web.rest.PosicionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /posicions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PosicionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter titulo;

    private StringFilter descripcion;

    private IntegerFilter numeroPuestos;

    private LongFilter salarioMinimo;

    private LongFilter salarioMaximo;

    private LocalDateFilter fechaAlta;

    private LocalDateFilter fechaNecesidad;

    private LongFilter candidaturaId;

    private LongFilter historialCandidaturaId;

    private LongFilter historialPosicionId;

    private LongFilter estadoPosicionId;

    private LongFilter tipoJornadaId;

    private LongFilter unidadDeNegocioId;

    public PosicionCriteria() {
    }

    public PosicionCriteria(PosicionCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.titulo = other.titulo == null ? null : other.titulo.copy();
        this.descripcion = other.descripcion == null ? null : other.descripcion.copy();
        this.numeroPuestos = other.numeroPuestos == null ? null : other.numeroPuestos.copy();
        this.salarioMinimo = other.salarioMinimo == null ? null : other.salarioMinimo.copy();
        this.salarioMaximo = other.salarioMaximo == null ? null : other.salarioMaximo.copy();
        this.fechaAlta = other.fechaAlta == null ? null : other.fechaAlta.copy();
        this.fechaNecesidad = other.fechaNecesidad == null ? null : other.fechaNecesidad.copy();
        this.candidaturaId = other.candidaturaId == null ? null : other.candidaturaId.copy();
        this.historialCandidaturaId = other.historialCandidaturaId == null ? null : other.historialCandidaturaId.copy();
        this.historialPosicionId = other.historialPosicionId == null ? null : other.historialPosicionId.copy();
        this.estadoPosicionId = other.estadoPosicionId == null ? null : other.estadoPosicionId.copy();
        this.tipoJornadaId = other.tipoJornadaId == null ? null : other.tipoJornadaId.copy();
        this.unidadDeNegocioId = other.unidadDeNegocioId == null ? null : other.unidadDeNegocioId.copy();
    }

    @Override
    public PosicionCriteria copy() {
        return new PosicionCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitulo() {
        return titulo;
    }

    public void setTitulo(StringFilter titulo) {
        this.titulo = titulo;
    }

    public StringFilter getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(StringFilter descripcion) {
        this.descripcion = descripcion;
    }

    public IntegerFilter getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(IntegerFilter numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public LongFilter getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(LongFilter salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public LongFilter getSalarioMaximo() {
        return salarioMaximo;
    }

    public void setSalarioMaximo(LongFilter salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
    }

    public LocalDateFilter getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDateFilter fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDateFilter getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(LocalDateFilter fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public LongFilter getCandidaturaId() {
        return candidaturaId;
    }

    public void setCandidaturaId(LongFilter candidaturaId) {
        this.candidaturaId = candidaturaId;
    }

    public LongFilter getHistorialCandidaturaId() {
        return historialCandidaturaId;
    }

    public void setHistorialCandidaturaId(LongFilter historialCandidaturaId) {
        this.historialCandidaturaId = historialCandidaturaId;
    }

    public LongFilter getHistorialPosicionId() {
        return historialPosicionId;
    }

    public void setHistorialPosicionId(LongFilter historialPosicionId) {
        this.historialPosicionId = historialPosicionId;
    }

    public LongFilter getEstadoPosicionId() {
        return estadoPosicionId;
    }

    public void setEstadoPosicionId(LongFilter estadoPosicionId) {
        this.estadoPosicionId = estadoPosicionId;
    }

    public LongFilter getTipoJornadaId() {
        return tipoJornadaId;
    }

    public void setTipoJornadaId(LongFilter tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    public LongFilter getUnidadDeNegocioId() {
        return unidadDeNegocioId;
    }

    public void setUnidadDeNegocioId(LongFilter unidadDeNegocioId) {
        this.unidadDeNegocioId = unidadDeNegocioId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PosicionCriteria that = (PosicionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(titulo, that.titulo) &&
            Objects.equals(descripcion, that.descripcion) &&
            Objects.equals(numeroPuestos, that.numeroPuestos) &&
            Objects.equals(salarioMinimo, that.salarioMinimo) &&
            Objects.equals(salarioMaximo, that.salarioMaximo) &&
            Objects.equals(fechaAlta, that.fechaAlta) &&
            Objects.equals(fechaNecesidad, that.fechaNecesidad) &&
            Objects.equals(candidaturaId, that.candidaturaId) &&
            Objects.equals(historialCandidaturaId, that.historialCandidaturaId) &&
            Objects.equals(historialPosicionId, that.historialPosicionId) &&
            Objects.equals(estadoPosicionId, that.estadoPosicionId) &&
            Objects.equals(tipoJornadaId, that.tipoJornadaId) &&
            Objects.equals(unidadDeNegocioId, that.unidadDeNegocioId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        titulo,
        descripcion,
        numeroPuestos,
        salarioMinimo,
        salarioMaximo,
        fechaAlta,
        fechaNecesidad,
        candidaturaId,
        historialCandidaturaId,
        historialPosicionId,
        estadoPosicionId,
        tipoJornadaId,
        unidadDeNegocioId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PosicionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (titulo != null ? "titulo=" + titulo + ", " : "") +
                (descripcion != null ? "descripcion=" + descripcion + ", " : "") +
                (numeroPuestos != null ? "numeroPuestos=" + numeroPuestos + ", " : "") +
                (salarioMinimo != null ? "salarioMinimo=" + salarioMinimo + ", " : "") +
                (salarioMaximo != null ? "salarioMaximo=" + salarioMaximo + ", " : "") +
                (fechaAlta != null ? "fechaAlta=" + fechaAlta + ", " : "") +
                (fechaNecesidad != null ? "fechaNecesidad=" + fechaNecesidad + ", " : "") +
                (candidaturaId != null ? "candidaturaId=" + candidaturaId + ", " : "") +
                (historialCandidaturaId != null ? "historialCandidaturaId=" + historialCandidaturaId + ", " : "") +
                (historialPosicionId != null ? "historialPosicionId=" + historialPosicionId + ", " : "") +
                (estadoPosicionId != null ? "estadoPosicionId=" + estadoPosicionId + ", " : "") +
                (tipoJornadaId != null ? "tipoJornadaId=" + tipoJornadaId + ", " : "") +
                (unidadDeNegocioId != null ? "unidadDeNegocioId=" + unidadDeNegocioId + ", " : "") +
            "}";
    }

}
