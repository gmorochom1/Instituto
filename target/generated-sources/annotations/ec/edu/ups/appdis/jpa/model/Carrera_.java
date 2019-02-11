package ec.edu.ups.appdis.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Carrera.class)
public abstract class Carrera_ {

	public static volatile ListAttribute<Carrera, Curso> listaCursos;
	public static volatile ListAttribute<Carrera, Profesor> listaProfesores;
	public static volatile SingularAttribute<Carrera, String> director;
	public static volatile SingularAttribute<Carrera, String> nombre;

}

