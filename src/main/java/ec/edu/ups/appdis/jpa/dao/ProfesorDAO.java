package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.appdis.jpa.model.Profesor;

@Stateless
public class ProfesorDAO {
	@Inject
	private EntityManager em;

	public void insert(Profesor profesor) {
		em.persist(profesor);
	}

	public void update(Profesor profesor) {
		em.merge(profesor);
	}

	public void remove(String id) {
		em.remove(this.read(id));
	}

	public Profesor read(String id) {
		Profesor profesor = em.find(Profesor.class, id);
		return profesor;
	}

	public List<Profesor> getProfesores() {
		String jpql = "SELECT p FROM Profesor p";
		Query query = em.createQuery(jpql, Profesor.class);
		List<Profesor> lista = query.getResultList();
		return lista;
	}

	public List<Profesor> autenticacionPro(String email, String pass) {
		String jpql = "SELECT p FROM Profesor p WHERE p.email=:email AND p.password=:pass";
		Query query = em.createQuery(jpql, Profesor.class);
		query.setParameter("email", email);
		query.setParameter("pass", pass);
		List<Profesor> listado = query.getResultList();
		return listado;
	}
}
