package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Curso;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Inscripcion;

@Stateless
public class InscripcionDAO {

	@Inject
	private EntityManager em;

	public void insert(Inscripcion inscripcion) {
		em.persist(inscripcion);
	}

	public void update(Inscripcion inscripcion) {
		em.merge(inscripcion);
	}

	public void remove(int id) {
		em.remove(this.read(id));
	}

	public Inscripcion read(int id) {
		Inscripcion inscripcion = em.find(Inscripcion.class, id);
		return inscripcion;
	}

	public List<Inscripcion> getInscripciones() {
		String jpql = "SELECT i FROM Inscripcion i";
		Query query = em.createQuery(jpql, Inscripcion.class);
		List<Inscripcion> lista = query.getResultList();
		return lista;
	}

	public List<Estudiante> getEstCurso(int idCurso) {
		String jpql = "SELECT e FROM Inscripcion i, Estudiante e WHERE i.estudiante=e.cedula AND idcurso=" + idCurso;
		Query query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> lista = query.getResultList();
		return lista;
	}

	public List<Curso> getCursosEst(String cedula) {
		String jpql = "SELECT c FROM Inscripcion i, Curso c WHERE i.curso=c.idCurso and i.estudiante='" + cedula + "'";
		Query query = em.createQuery(jpql, Curso.class);
		List<Curso> lista = query.getResultList();
		return lista;
	}

}
