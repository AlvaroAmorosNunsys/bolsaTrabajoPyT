package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, UnidadDeNegocio> unidadDeNegocio;
	public static volatile SingularAttribute<Usuario, Long> id;
	public static volatile SingularAttribute<Usuario, User> user;

	public static final String UNIDAD_DE_NEGOCIO = "unidadDeNegocio";
	public static final String ID = "id";
	public static final String USER = "user";

}

