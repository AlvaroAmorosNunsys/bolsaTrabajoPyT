package com.company.domain;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HistorialPosicion.class)
public abstract class HistorialPosicion_ {

	public static volatile SingularAttribute<HistorialPosicion, Posicion> posicion;
	public static volatile SingularAttribute<HistorialPosicion, LocalDate> fechaCambio;
	public static volatile SingularAttribute<HistorialPosicion, LocalDate> fechaModificacion;
	public static volatile SingularAttribute<HistorialPosicion, String> nombreEditor;
	public static volatile SingularAttribute<HistorialPosicion, EstadoPosicion> estadoPosicion;
	public static volatile SingularAttribute<HistorialPosicion, Boolean> porDefecto;
	public static volatile SingularAttribute<HistorialPosicion, Long> id;

	public static final String POSICION = "posicion";
	public static final String FECHA_CAMBIO = "fechaCambio";
	public static final String FECHA_MODIFICACION = "fechaModificacion";
	public static final String NOMBRE_EDITOR = "nombreEditor";
	public static final String ESTADO_POSICION = "estadoPosicion";
	public static final String POR_DEFECTO = "porDefecto";
	public static final String ID = "id";

}

