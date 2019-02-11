package ec.edu.ups.appdis.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Profesor.class)
public abstract class Profesor_ {

	public static volatile SingularAttribute<Profesor, String> password;
	public static volatile SingularAttribute<Profesor, String> cedula;
	public static volatile SingularAttribute<Profesor, String> direccion;
	public static volatile SingularAttribute<Profesor, Carrera> carrera;
	public static volatile SingularAttribute<Profesor, String> nombre;
	public static volatile SingularAttribute<Profesor, String> especialidad;
	public static volatile SingularAttribute<Profesor, String> email;

}

