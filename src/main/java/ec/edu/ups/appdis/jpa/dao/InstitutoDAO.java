package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Instituto;

@Stateless
public class InstitutoDAO {
	@Inject
	private EntityManager em;

	public void insert(Instituto instituto) {
		em.persist(instituto);
	}

	public void update(Instituto instituto) {
		em.merge(instituto);
	}

	public void remove(int id) {
		em.remove(this.read(id));
	}

	public Instituto read(int id) {
		Instituto instituto = em.find(Instituto.class, id);
		return instituto;
	}

	public List<Instituto> getInstitutos() {
		String jpql = "SELECT i FROM Instituto i";
		Query query = em.createQuery(jpql, Instituto.class);
		List<Instituto> lista = query.getResultList();
		return lista;
	}
}
