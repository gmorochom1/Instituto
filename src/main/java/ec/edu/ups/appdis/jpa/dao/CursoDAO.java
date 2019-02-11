package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Curso;

@Stateless
public class CursoDAO {

	@Inject
	private EntityManager em;

	public void insert(Curso curso) {
		em.persist(curso);
	}

	public void update(Curso curso) {
		em.merge(curso);
	}

	public void eliminar(Curso curso) {
		em.remove(curso);
	}

	public void remove(int id) {
		System.out.println("Curso eliminar DAO " + id);
		em.remove(this.read(id));
	}

	public Curso read(int id) {
		Curso curso = em.find(Curso.class, id);
		return curso;
	}

	public List<Curso> getCursos() {
		String jpql = "SELECT c FROM Curso c";
		Query query = em.createQuery(jpql, Curso.class);
		List<Curso> lista = query.getResultList();
		return lista;
	}

	public List<Curso> getCurProf(String cedula) {
		String jpql = "SELECT c FROM Profesor p, Curso c WHERE c.profesor=p.cedula AND c.profesor='" + cedula + "'";
		Query query = em.createQuery(jpql, Curso.class);
		List<Curso> lista = query.getResultList();
		return lista;
	}

	public List<Curso> getInfoCurso(int idCurso) {
		String jpql = "SELECT c FROM Curso c WHERE c.idCurso =" + idCurso;
		Query query = em.createQuery(jpql, Curso.class);
		List<Curso> lista = query.getResultList();
		return lista;
	}
}
