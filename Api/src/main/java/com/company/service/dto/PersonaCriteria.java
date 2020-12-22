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
 * Criteria class for the {@link com.company.domain.Persona} entity. This class is used
 * in {@link com.company.web.rest.PersonaResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /personas?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PersonaCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter documentoIdentidad;

    private StringFilter nombre;

    private StringFilter apellidos;

    private StringFilter email;

    private StringFilter telefono;

    private StringFilter comentarios;

    private LongFilter candidaturaId;

    public PersonaCriteria() {
    }

    public PersonaCriteria(PersonaCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.documentoIdentidad = other.documentoIdentidad == null ? null : other.documentoIdentidad.copy();
        this.nombre = other.nombre == null ? null : other.nombre.copy();
        this.apellidos = other.apellidos == null ? null : other.apellidos.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.comentarios = other.comentarios == null ? null : other.comentarios.copy();
        this.candidaturaId = other.candidaturaId == null ? null : other.candidaturaId.copy();
    }

    @Override
    public PersonaCriteria copy() {
        return new PersonaCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(StringFilter documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public StringFilter getNombre() {
        return nombre;
    }

    public void setNombre(StringFilter nombre) {
        this.nombre = nombre;
    }

    public StringFilter getApellidos() {
        return apellidos;
    }

    public void setApellidos(StringFilter apellidos) {
        this.apellidos = apellidos;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getComentarios() {
        return comentarios;
    }

    public void setComentarios(StringFilter comentarios) {
        this.comentarios = comentarios;
    }

    public LongFilter getCandidaturaId() {
        return candidaturaId;
    }

    public void setCandidaturaId(LongFilter candidaturaId) {
        this.candidaturaId = candidaturaId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PersonaCriteria that = (PersonaCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(documentoIdentidad, that.documentoIdentidad) &&
            Objects.equals(nombre, that.nombre) &&
            Objects.equals(apellidos, that.apellidos) &&
            Objects.equals(email, that.email) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(comentarios, that.comentarios) &&
            Objects.equals(candidaturaId, that.candidaturaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        documentoIdentidad,
        nombre,
        apellidos,
        email,
        telefono,
        comentarios,
        candidaturaId
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (documentoIdentidad != null ? "documentoIdentidad=" + documentoIdentidad + ", " : "") +
                (nombre != null ? "nombre=" + nombre + ", " : "") +
                (apellidos != null ? "apellidos=" + apellidos + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (comentarios != null ? "comentarios=" + comentarios + ", " : "") +
                (candidaturaId != null ? "candidaturaId=" + candidaturaId + ", " : "") +
            "}";
    }

}
