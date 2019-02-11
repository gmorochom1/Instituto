package ec.edu.ups.appdis.jpa.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Curso.class)
public abstract class Curso_ {

	public static volatile SingularAttribute<Curso, String> horario;
	public static volatile SingularAttribute<Curso, Date> fechaInicio;
	public static volatile SingularAttribute<Curso, Double> costo;
	public static volatile SingularAttribute<Curso, Integer> idCurso;
	public static volatile SingularAttribute<Curso, String> dias;
	public static volatile SingularAttribute<Curso, Profesor> profesor;
	public static volatile SingularAttribute<Curso, Carrera> carrera;
	public static volatile SingularAttribute<Curso, String> nombre;
	public static volatile SingularAttribute<Curso, Date> fechaFin;

}

