package com.company.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Persona.
 */
@Entity
@Table(name = "persona")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "documento_identidad", nullable = false, unique = true)
    private String documentoIdentidad;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @NotNull
    @Size(max = 250)
    @Column(name = "apellidos", length = 250, nullable = false)
    private String apellidos;

    @NotNull
    @Size(max = 250)
    @Column(name = "email", length = 250, nullable = false)
    private String email;

    @NotNull
    @Column(name = "telefono", nullable = false)
    private String telefono;

    
    @Lob
    @Column(name = "curriculum", nullable = false)
    private byte[] curriculum;

    @Column(name = "curriculum_content_type", nullable = false)
    private String curriculumContentType;

    @Size(max = 5000)
    @Column(name = "comentarios", length = 5000)
    private String comentarios;

    @OneToMany(mappedBy = "persona")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Candidatura> candidaturas = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoIdentidad() {
        return documentoIdentidad;
    }

    public Persona documentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
        return this;
    }

    public void setDocumentoIdentidad(String documentoIdentidad) {
        this.documentoIdentidad = documentoIdentidad;
    }

    public String getNombre() {
        return nombre;
    }

    public Persona nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Persona apellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public Persona email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Persona telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public byte[] getCurriculum() {
        return curriculum;
    }

    public Persona curriculum(byte[] curriculum) {
        this.curriculum = curriculum;
        return this;
    }

    public void setCurriculum(byte[] curriculum) {
        this.curriculum = curriculum;
    }

    public String getCurriculumContentType() {
        return curriculumContentType;
    }

    public Persona curriculumContentType(String curriculumContentType) {
        this.curriculumContentType = curriculumContentType;
        return this;
    }

    public void setCurriculumContentType(String curriculumContentType) {
        this.curriculumContentType = curriculumContentType;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Persona comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public Persona candidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
        return this;
    }

    public Persona addCandidatura(Candidatura candidatura) {
        this.candidaturas.add(candidatura);
        candidatura.setPersona(this);
        return this;
    }

    public Persona removeCandidatura(Candidatura candidatura) {
        this.candidaturas.remove(candidatura);
        candidatura.setPersona(null);
        return this;
    }

    public void setCandidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Persona)) {
            return false;
        }
        return id != null && id.equals(((Persona) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Persona{" +
            "id=" + getId() +
            ", documentoIdentidad='" + getDocumentoIdentidad() + "'" +
            ", nombre='" + getNombre() + "'" +
            ", apellidos='" + getApellidos() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", curriculum='" + getCurriculum() + "'" +
            ", curriculumContentType='" + getCurriculumContentType() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            "}";
    }
}
