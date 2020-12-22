package com.company.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UnidadDeNegocio.
 */
@Entity
@Table(name = "unidad_de_negocio")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UnidadDeNegocio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "nombre", length = 250, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "unidadDeNegocio")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Posicion> posicions = new HashSet<>();

    @OneToMany(mappedBy = "unidadDeNegocio")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Usuario> usuarios = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public UnidadDeNegocio nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Posicion> getPosicions() {
        return posicions;
    }

    public UnidadDeNegocio posicions(Set<Posicion> posicions) {
        this.posicions = posicions;
        return this;
    }

    public UnidadDeNegocio addPosicion(Posicion posicion) {
        this.posicions.add(posicion);
        posicion.setUnidadDeNegocio(this);
        return this;
    }

    public UnidadDeNegocio removePosicion(Posicion posicion) {
        this.posicions.remove(posicion);
        posicion.setUnidadDeNegocio(null);
        return this;
    }

    public void setPosicions(Set<Posicion> posicions) {
        this.posicions = posicions;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public UnidadDeNegocio usuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public UnidadDeNegocio addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        usuario.setUnidadDeNegocio(this);
        return this;
    }

    public UnidadDeNegocio removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
        usuario.setUnidadDeNegocio(null);
        return this;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UnidadDeNegocio)) {
            return false;
        }
        return id != null && id.equals(((UnidadDeNegocio) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UnidadDeNegocio{" +
            "id=" + getId() +
            ", nombre='" + getNombre() + "'" +
            "}";
    }
}
