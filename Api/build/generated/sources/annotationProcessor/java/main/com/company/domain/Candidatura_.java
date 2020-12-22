package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Candidatura.class)
public abstract class Candidatura_ {

	public static volatile SingularAttribute<Candidatura, Posicion> posicion;
	public static volatile SingularAttribute<Candidatura, Persona> persona;
	public static volatile SingularAttribute<Candidatura, Fuente> fuente;
	public static volatile SingularAttribute<Candidatura, Long> id;
	public static volatile SetAttribute<Candidatura, HistorialCandidatura> historialCandidaturas;
	public static volatile SingularAttribute<Candidatura, EstadoCandidatura> estadoCandidatura;

	public static final String POSICION = "posicion";
	public static final String PERSONA = "persona";
	public static final String FUENTE = "fuente";
	public static final String ID = "id";
	public static final String HISTORIAL_CANDIDATURAS = "historialCandidaturas";
	public static final String ESTADO_CANDIDATURA = "estadoCandidatura";

}

