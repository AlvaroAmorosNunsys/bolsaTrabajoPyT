package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadoCandidatura.class)
public abstract class EstadoCandidatura_ {

	public static volatile SingularAttribute<EstadoCandidatura, Long> id;
	public static volatile SingularAttribute<EstadoCandidatura, String> nombre;
	public static volatile SetAttribute<EstadoCandidatura, HistorialCandidatura> historialCandidaturas;

	public static final String ID = "id";
	public static final String NOMBRE = "nombre";
	public static final String HISTORIAL_CANDIDATURAS = "historialCandidaturas";

}

