package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Estudiante;

@Stateless
public class EstudianteDAO {

	@Inject
	private EntityManager em;

	public void insert(Estudiante estudiante) {
		em.persist(estudiante);
	}

	public void update(Estudiante estudiante) {
		em.merge(estudiante);
	}

	public void remove(String id) {
		em.remove(this.read(id));
	}

	public Estudiante read(String id) {
		Estudiante estudiante = em.find(Estudiante.class, id);
		return estudiante;
	}

	public List<Estudiante> getEstudiantes() {
		String jpql = "SELECT e FROM Estudiante e";
		Query query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> lista = query.getResultList();
		return lista;
	}

	public List<Estudiante> buscarEstudiantes(String campo) {
		String jpql = "SELECT e FROM Estudiante e WHERE e.cedula LIKE '%" + campo + "%' or e.nombres LIKE '%" + campo
				+ "%'";
		Query query = em.createQuery(jpql, Estudiante.class);
		List<Estudiante> lista = query.getResultList();
		return lista;
	}

	public List<Estudiante> listEstAutenticacion(String un, String pass) {
		String jpql = "SELECT e FROM Estudiante e WHERE e.cedula=:un AND e.password=:pass";
		Query query = em.createQuery(jpql, Estudiante.class);
		query.setParameter("un", un);
		query.setParameter("pass", pass);
		List<Estudiante> listado = query.getResultList();
		return listado;
	}

	public List<Estudiante> autenticacionEst(String email, String pass) {
		String jpql = "SELECT e FROM Estudiante e WHERE e.email=:email AND e.password=:pass";
		Query query = em.createQuery(jpql, Estudiante.class);
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Estudiante> listado = query.getResultList();
		return listado;
	}

}
