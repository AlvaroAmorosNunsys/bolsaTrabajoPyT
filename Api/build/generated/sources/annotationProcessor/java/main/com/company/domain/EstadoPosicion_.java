package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EstadoPosicion.class)
public abstract class EstadoPosicion_ {

	public static volatile SetAttribute<EstadoPosicion, HistorialPosicion> historialPosicions;
	public static volatile SingularAttribute<EstadoPosicion, Long> id;
	public static volatile SingularAttribute<EstadoPosicion, String> nombre;

	public static final String HISTORIAL_POSICIONS = "historialPosicions";
	public static final String ID = "id";
	public static final String NOMBRE = "nombre";

}

