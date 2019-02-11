package ec.edu.ups.appdis.jpa.security;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import ec.edu.ups.appdis.jpa.model.Usuario;

@Stateless
public class UsuarioFacade implements MenuFacadeLocal {

	@Inject
	private EntityManager em;

	protected EntityManager getEntityManager() {
		return em;
	}

	public Usuario iniciarSesion(Usuario us) {
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
	}

}
