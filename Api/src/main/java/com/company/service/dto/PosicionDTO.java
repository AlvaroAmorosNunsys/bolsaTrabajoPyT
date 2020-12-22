package com.company.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * A DTO for the {@link com.company.domain.Posicion} entity.
 */
public class PosicionDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(max = 250)
    private String titulo;

    @NotNull
    @Size(max = 5000)
    private String descripcion;

    @NotNull
    @Min(value = 1)
    private Integer numeroPuestos;

    private Long salarioMinimo;

    private Long salarioMaximo;

    private LocalDate fechaAlta;

    @NotNull
    private LocalDate fechaNecesidad;


    private Long estadoPosicionId;

    private String estadoPosicionNombre;

    private Long tipoJornadaId;

    private String tipoJornadaNombre;

    private Long unidadDeNegocioId;

    private String unidadDeNegocioNombre;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public Long getSalarioMinimo() {
        return salarioMinimo;
    }

    public void setSalarioMinimo(Long salarioMinimo) {
        this.salarioMinimo = salarioMinimo;
    }

    public Long getSalarioMaximo() {
        return salarioMaximo;
    }

    public void setSalarioMaximo(Long salarioMaximo) {
        this.salarioMaximo = salarioMaximo;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public LocalDate getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(LocalDate fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public Long getEstadoPosicionId() {
        return estadoPosicionId;
    }

    public void setEstadoPosicionId(Long estadoPosicionId) {
        this.estadoPosicionId = estadoPosicionId;
    }

    public String getEstadoPosicionNombre() {
        return estadoPosicionNombre;
    }

    public void setEstadoPosicionNombre(String estadoPosicionNombre) {
        this.estadoPosicionNombre = estadoPosicionNombre;
    }

    public Long getTipoJornadaId() {
        return tipoJornadaId;
    }

    public void setTipoJornadaId(Long tipoJornadaId) {
        this.tipoJornadaId = tipoJornadaId;
    }

    public String getTipoJornadaNombre() {
        return tipoJornadaNombre;
    }

    public void setTipoJornadaNombre(String tipoJornadaNombre) {
        this.tipoJornadaNombre = tipoJornadaNombre;
    }

    public Long getUnidadDeNegocioId() {
        return unidadDeNegocioId;
    }

    public void setUnidadDeNegocioId(Long unidadDeNegocioId) {
        this.unidadDeNegocioId = unidadDeNegocioId;
    }

    public String getUnidadDeNegocioNombre() {
        return unidadDeNegocioNombre;
    }

    public void setUnidadDeNegocioNombre(String unidadDeNegocioNombre) {
        this.unidadDeNegocioNombre = unidadDeNegocioNombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PosicionDTO)) {
            return false;
        }

        return id != null && id.equals(((PosicionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PosicionDTO{" +
            "id=" + getId() +
            ", titulo='" + getTitulo() + "'" +
            ", descripcion='" + getDescripcion() + "'" +
            ", numeroPuestos=" + getNumeroPuestos() +
            ", salarioMinimo=" + getSalarioMinimo() +
            ", salarioMaximo=" + getSalarioMaximo() +
            ", fechaAlta='" + getFechaAlta() + "'" +
            ", fechaNecesidad='" + getFechaNecesidad() + "'" +
            ", estadoPosicionId=" + getEstadoPosicionId() +
            ", estadoPosicionNombre='" + getEstadoPosicionNombre() + "'" +
            ", tipoJornadaId=" + getTipoJornadaId() +
            ", tipoJornadaNombre='" + getTipoJornadaNombre() + "'" +
            ", unidadDeNegocioId=" + getUnidadDeNegocioId() +
            ", unidadDeNegocioNombre='" + getUnidadDeNegocioNombre() + "'" +
            "}";
    }
}
