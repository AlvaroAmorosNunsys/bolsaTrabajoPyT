package com.company.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Posicion.
 */
@Entity
@Table(name = "posicion")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Posicion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "titulo", length = 250, nullable = false)
    private String titulo;

    @NotNull
    @Size(max = 5000)
    @Column(name = "descripcion", length = 5000, nullable = false)
    private String descripcion;

    @NotNull
    @Min(value = 1)
    @Column(name = "numero_puestos", nullable = false)
    private Integer numeroPuestos;

    @Column(name = "salario_minimo")
    private Long salarioMinimo;

    @Column(name = "salario_maximo")
    private Long salarioMaximo;

    @Column(name = "fecha_alta")
    private LocalDate fechaAlta;

    @NotNull
    @Column(name = "fecha_necesidad", nullable = false)
    private LocalDate fechaNecesidad;

    @OneToMany(mappedBy = "posicion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Candidatura> candidaturas = new HashSet<>();

    @OneToMany(mappedBy = "posicion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HistorialCandidatura> historialCandidaturas = new HashSet<>();

    @OneToMany(mappedBy = "posicion")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<HistorialPosicion> historialPosicions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = "posicions", allowSetters = true)
    private EstadoPosicion estadoPosicion;

    @ManyToOne
    @JsonIgnoreProperties(value = "posicions", allowSetters = true)
    private TipoJornada tipoJornada;

    @ManyToOne
    @JsonIgnoreProperties(value = "posicions", allowSetters = true)
    private UnidadDeNegocio unidadDeNegocio;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Posicion titulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Posicion descripcion(String descripcion) {
        this.descripcion = descripcion;
        return this;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroPuestos() {
        return numeroPuestos;
    }

    public Posicion numeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
        return this;
    }

    public void setNumeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public Long getSalarioMinimo() {
        return salarioMinimo;
    }

    public Posicion salarioMinimo(Long salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
        return this;
    }

    public void setSalarioMinimo(Long salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public Long getSalarioMaximo() {
        return salarioMaximo;
    }

    public Posicion salarioMaximo(Long salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
        return this;
    }

    public void setSalarioMaximo(Long salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public Posicion fechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
        return this;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaNecesidad() {
        return fechaNecesidad;
    }

    public Posicion fechaNecesidad(LocalDate fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
        return this;
    }

    public void setFechaNecesidad(LocalDate fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public Set<Candidatura> getCandidaturas() {
        return candidaturas;
    }

    public Posicion candidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
        return this;
    }

    public Posicion addCandidatura(Candidatura candidatura) {
        this.candidaturas.add(candidatura);
        candidatura.setPosicion(this);
        return this;
    }

    public Posicion removeCandidatura(Candidatura candidatura) {
        this.candidaturas.remove(candidatura);
        candidatura.setPosicion(null);
        return this;
    }

    public void setCandidaturas(Set<Candidatura> candidaturas) {
        this.candidaturas = candidaturas;
    }

    public Set<HistorialCandidatura> getHistorialCandidaturas() {
        return historialCandidaturas;
    }

    public Posicion historialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
        return this;
    }

    public Posicion addHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.add(historialCandidatura);
        historialCandidatura.setPosicion(this);
        return this;
    }

    public Posicion removeHistorialCandidatura(HistorialCandidatura historialCandidatura) {
        this.historialCandidaturas.remove(historialCandidatura);
        historialCandidatura.setPosicion(null);
        return this;
    }

    public void setHistorialCandidaturas(Set<HistorialCandidatura> historialCandidaturas) {
        this.historialCandidaturas = historialCandidaturas;
    }

    public Set<HistorialPosicion> getHistorialPosicions() {
        return historialPosicions;
    }

    public Posicion historialPosicions(Set<HistorialPosicion> historialPosicions) {
        this.historialPosicions = historialPosicions;
        return this;
    }

    public Posicion addHistorialPosicion(HistorialPosicion historialPosicion) {
        this.historialPosicions.add(historialPosicion);
        historialPosicion.setPosicion(this);
        return this;
    }

    public Posicion removeHistorialPosicion(HistorialPosicion historialPosicion) {
        this.historialPosicions.remove(historialPosicion);
        historialPosicion.setPosicion(null);
        return this;
    }

    public void setHistorialPosicions(Set<HistorialPosicion> historialPosicions) {
        this.historialPosicions = historialPosicions;
    }

    public EstadoPosicion getEstadoPosicion() {
        return estadoPosicion;
    }

    public Posicion estadoPosicion(EstadoPosicion estadoPosicion) {
        this.estadoPosicion = estadoPosicion;
        return this;
    }

    public void setEstadoPosicion(EstadoPosicion estadoPosicion) {
        this.estadoPosicion = estadoPosicion;
    }

    public TipoJornada getTipoJornada() {
        return tipoJornada;
    }

    public Posicion tipoJornada(TipoJornada tipoJornada) {
        this.tipoJornada = tipoJornada;
        return this;
    }

    public void setTipoJornada(TipoJornada tipoJornada) {
        this.tipoJornada = tipoJornada;
    }

    public UnidadDeNegocio getUnidadDeNegocio() {
        return unidadDeNegocio;
    }

    public Posicion unidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
        return this;
    }

    public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
        this.unidadDeNegocio = unidadDeNegocio;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Posicion)) {
            return false;
        }
        return id != null && id.equals(((Posicion) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Posicion{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", numeroPuestos=" + getNumeroPuestos() +
            ", salarioMinimo=" + getSalarioMinimo() +
            ", salarioMaximo=" + getSalarioMaximo() +
            ", fechaAlta='" + getFechaAlta() + "'" +
            ", fechaNecesidad='" + getFechaNecesidad() + "'" +
            "}";
    }
}
