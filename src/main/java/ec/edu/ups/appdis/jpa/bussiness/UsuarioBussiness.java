package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.UsuarioDAO;
import ec.edu.ups.appdis.jpa.model.Usuario;

@Stateless
public class UsuarioBussiness {
	@Inject
	private UsuarioDAO dao;

	public void save(Usuario usuario) throws Exception {
		Usuario aux = dao.read(usuario.getEmail());
		if (aux != null) {
			throw new Exception("Usuario ya registrado");
		} else {
			dao.insert(usuario);
		}
	}

	public void eliminar(String cedula) throws Exception {
		Usuario aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Usuario no registrado");
		} else {
			dao.remove(aux);
		}

	}

	public void actualizar(Usuario usuario) throws Exception {
		Usuario aux = dao.read(usuario.getEmail());
		if (aux == null) {
			throw new Exception("Usuario no registrado");
		} else {
			dao.update(usuario);
		}
	}

	public List<Usuario> getListUsuarios() {
		return dao.getUsuarios();
	}

	public Usuario load(String cedula) throws Exception {
		Usuario aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Usuario no registrado");
		} else {
			return aux;
		}
	}
	


}
