package ec.edu.ups.appdis.jpa.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Tarea.class)
public abstract class Tarea_ {

	public static volatile SingularAttribute<Tarea, String> descripcion;
	public static volatile SingularAttribute<Tarea, Integer> codigo;
	public static volatile SingularAttribute<Tarea, String> calificacion;
	public static volatile SingularAttribute<Tarea, Curso> curso;
	public static volatile SingularAttribute<Tarea, String> fechaEntrega;

}

