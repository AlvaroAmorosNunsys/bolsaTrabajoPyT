package com.company.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Persona.class)
public abstract class Persona_ {

	public static volatile SingularAttribute<Persona, String> documentoIdentidad;
	public static volatile SingularAttribute<Persona, String> apellidos;
	public static volatile SingularAttribute<Persona, String> curriculumContentType;
	public static volatile SingularAttribute<Persona, Long> id;
	public static volatile SingularAttribute<Persona, byte[]> curriculum;
	public static volatile SingularAttribute<Persona, String> telefono;
	public static volatile SingularAttribute<Persona, String> nombre;
	public static volatile SingularAttribute<Persona, String> comentarios;
	public static volatile SingularAttribute<Persona, String> email;
	public static volatile SetAttribute<Persona, Candidatura> candidaturas;

	public static final String DOCUMENTO_IDENTIDAD = "documentoIdentidad";
	public static final String APELLIDOS = "apellidos";
	public static final String CURRICULUM_CONTENT_TYPE = "curriculumContentType";
	public static final String ID = "id";
	public static final String CURRICULUM = "curriculum";
	public static final String TELEFONO = "telefono";
	public static final String NOMBRE = "nombre";
	public static final String COMENTARIOS = "comentarios";
	public static final String EMAIL = "email";
	public static final String CANDIDATURAS = "candidaturas";

}

