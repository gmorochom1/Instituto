package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import ec.edu.ups.appdis.jpa.model.Tarea;

@Stateless
public class TareaDAO {

	@Inject
	private EntityManager em;

	public void insert(Tarea tarea) {
		em.persist(tarea);
	}

	public void update(Tarea tarea) {
		em.merge(tarea);
	}

	public void remove(Tarea tarea) {
		em.remove(tarea);
	}

	public Tarea read(int codigo) {
		Tarea tarea = em.find(Tarea.class, codigo);
		return tarea;
	}

	public List<Tarea> getTareas() {
		String jpql = "SELECT t FROM Tarea t";
		Query query = em.createQuery(jpql, Tarea.class);
		List<Tarea> lista = query.getResultList();
		return lista;
	}
	
	public List<Tarea> getTareasPorCurso(int idCurso) {
		String jpql = "SELECT t FROM Curso c, Tarea t WHERE t.curso=c.idCurso and t.curso=" + idCurso;
		Query query = em.createQuery(jpql, Tarea.class);
		List<Tarea> lista = query.getResultList();
		return lista;
	}
}
