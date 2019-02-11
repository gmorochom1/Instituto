package ec.edu.ups.appdis.jpa.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Usuario;
import ec.edu.ups.appdis.jpa.security.MenuFacadeLocal;


@Stateless
public class UsuarioDAO{	
	@Inject
	private EntityManager em;

	public void insert(Usuario carrera) {
		em.persist(carrera);
	}

	public void update(Usuario carrera) {
		em.merge(carrera);
	}

	public void remove(Usuario carrera) {
		System.out.println("Eliminar DAO" + carrera.getEmail());
		em.remove(carrera);
	}

	public Usuario read(String nombre) {
		Usuario carrera = em.find(Usuario.class, nombre);
		return carrera;
	}

	public List<Usuario> getUsuarios() {
		String jpql = "SELECT c FROM Usuario c";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> lista = query.getResultList();
		return lista;
	}
	
	/*public Usuario iniciarSesion(Usuario us) {
		Usuario usuario = null;
		String consulta;
		try {
			consulta = "FROM Usuario u WHERE u.email = ?1 and u.password = ?2";
			Query query = em.createQuery(consulta);
			query.setParameter(1, us.getEmail());
			query.setParameter(2, us.getPassword());

			List<Usuario> lista = query.getResultList();
			if (!lista.isEmpty()) {
				usuario = lista.get(0);
			}
		} catch (Exception e) {
			throw e;
		}
		return usuario;
	}*/
	



}
