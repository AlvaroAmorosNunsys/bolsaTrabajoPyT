package com.company.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Posicion.class)
public abstract class Posicion_ {

	public static volatile SingularAttribute<Posicion, String> descripcion;
	public static volatile SingularAttribute<Posicion, LocalDate> fechaNecesidad;
	public static volatile SetAttribute<Posicion, HistorialPosicion> historialPosicions;
	public static volatile SingularAttribute<Posicion, UnidadDeNegocio> unidadDeNegocio;
	public static volatile SingularAttribute<Posicion, LocalDate> fechaAlta;
	public static volatile SingularAttribute<Posicion, EstadoPosicion> estadoPosicion;
	public static volatile SingularAttribute<Posicion, String> titulo;
	public static volatile SingularAttribute<Posicion, Long> salarioMaximo;
	public static volatile SingularAttribute<Posicion, Long> salarioMinimo;
	public static volatile SetAttribute<Posicion, HistorialCandidatura> historialCandidaturas;
	public static volatile SingularAttribute<Posicion, TipoJornada> tipoJornada;
	public static volatile SingularAttribute<Posicion, Integer> numeroPuestos;
	public static volatile SingularAttribute<Posicion, Long> id;
	public static volatile SetAttribute<Posicion, Candidatura> candidaturas;

	public static final String DESCRIPCION = "descripcion";
	public static final String FECHA_NECESIDAD = "fechaNecesidad";
	public static final String HISTORIAL_POSICIONS = "historialPosicions";
	public static final String UNIDAD_DE_NEGOCIO = "unidadDeNegocio";
	public static final String FECHA_ALTA = "fechaAlta";
	public static final String ESTADO_POSICION = "estadoPosicion";
	public static final String TITULO = "titulo";
	public static final String SALARIO_MAXIMO = "salarioMaximo";
	public static final String SALARIO_MINIMO = "salarioMinimo";
	public static final String HISTORIAL_CANDIDATURAS = "historialCandidaturas";
	public static final String TIPO_JORNADA = "tipoJornada";
	public static final String NUMERO_PUESTOS = "numeroPuestos";
	public static final String ID = "id";
	public static final String CANDIDATURAS = "candidaturas";

}

