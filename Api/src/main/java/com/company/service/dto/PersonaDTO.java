package com.company.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import javax.persistence.Lob;

/**
 * A DTO for the {@link com.company.domain.Persona} entity.
 */
public class PersonaDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String documentoIdentidad;

    @NotNull
    @Size(max = 100)
    private String nombre;

    @NotNull
    @Size(max = 250)
    private String apellidos;

    @NotNull
    @Size(max = 250)
    private String email;

    @NotNull
    private String telefono;

    
    @Lob
    private byte[] curriculum;

    private String curriculumContentType;
    @Size(max = 5000)
    private String comentarios;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte[] getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(byte[] curriculum) {
        this.curriculum = curriculum;
    }

    public String getCurriculumContentType() {
        return curriculumContentType;
    }

    public void setCurriculumContentType(String curriculumContentType) {
        this.curriculumContentType = curriculumContentType;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PersonaDTO)) {
            return false;
        }

        return id != null && id.equals(((PersonaDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PersonaDTO{" +
            "id=" + getId() +
            ", documentoIdentidad='" + getDocumentoIdentidad() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", curriculum='" + getCurriculum() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            "}";
    }
}
