package ec.edu.ups.appdis.jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Inscripcion.class)
public abstract class Inscripcion_ {

	public static volatile SingularAttribute<Inscripcion, Estudiante> estudiante;
	public static volatile SingularAttribute<Inscripcion, Date> fecha;
	public static volatile SingularAttribute<Inscripcion, Curso> curso;
	public static volatile SingularAttribute<Inscripcion, Integer> idInscripcion;

}

