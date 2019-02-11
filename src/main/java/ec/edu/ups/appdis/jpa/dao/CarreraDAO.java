package ec.edu.ups.appdis.jpa.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.appdis.jpa.model.Carrera;

@Stateless
public class CarreraDAO {
	@Inject
	private EntityManager em;

	public void insert(Carrera carrera) {
		em.persist(carrera);
	}

	public void update(Carrera carrera) {
		em.merge(carrera);
	}

	public void remove(Carrera carrera) {
		System.out.println("Eliminar DAO" + carrera.getNombre());
		em.remove(carrera);
	}

	public Carrera read(String nombre) {
		Carrera carrera = em.find(Carrera.class, nombre);
		return carrera;
	}

	public List<Carrera> getCarreras() {
		String jpql = "SELECT c FROM Carrera c";
		Query query = em.createQuery(jpql, Carrera.class);
		List<Carrera> lista = query.getResultList();
		return lista;
	}
}
