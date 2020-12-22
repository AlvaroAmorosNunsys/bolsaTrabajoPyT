package com.company.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistorialCandidatura.class)
public abstract class HistorialCandidatura_ {

	public static volatile SingularAttribute<HistorialCandidatura, Posicion> posicion;
	public static volatile SingularAttribute<HistorialCandidatura, Candidatura> candidatura;
	public static volatile SingularAttribute<HistorialCandidatura, LocalDate> fechaCambio;
	public static volatile SingularAttribute<HistorialCandidatura, LocalDate> fechaModificacion;
	public static volatile SingularAttribute<HistorialCandidatura, String> nombreEditor;
	public static volatile SingularAttribute<HistorialCandidatura, Boolean> porDefecto;
	public static volatile SingularAttribute<HistorialCandidatura, Long> id;
	public static volatile SingularAttribute<HistorialCandidatura, EstadoCandidatura> estadoCandidatura;

	public static final String POSICION = "posicion";
	public static final String CANDIDATURA = "candidatura";
	public static final String FECHA_CAMBIO = "fechaCambio";
	public static final String FECHA_MODIFICACION = "fechaModificacion";
	public static final String NOMBRE_EDITOR = "nombreEditor";
	public static final String POR_DEFECTO = "porDefecto";
	public static final String ID = "id";
	public static final String ESTADO_CANDIDATURA = "estadoCandidatura";

}

