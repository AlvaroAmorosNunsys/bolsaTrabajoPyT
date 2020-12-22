package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UnidadDeNegocio.class)
public abstract class UnidadDeNegocio_ {

	public static volatile SetAttribute<UnidadDeNegocio, Posicion> posicions;
	public static volatile SingularAttribute<UnidadDeNegocio, Long> id;
	public static volatile SetAttribute<UnidadDeNegocio, Usuario> usuarios;
	public static volatile SingularAttribute<UnidadDeNegocio, String> nombre;

	public static final String POSICIONS = "posicions";
	public static final String ID = "id";
	public static final String USUARIOS = "usuarios";
	public static final String NOMBRE = "nombre";

}

