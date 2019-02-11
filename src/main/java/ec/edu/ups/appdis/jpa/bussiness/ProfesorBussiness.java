package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.ProfesorDAO;
import ec.edu.ups.appdis.jpa.model.Profesor;

@Stateless
public class ProfesorBussiness {
	@Inject
	private ProfesorDAO dao;

	public void save(Profesor profesor) throws Exception {
		Profesor aux = dao.read(profesor.getCedula());
		if (aux != null) {
			throw new Exception("Profesor ya registrado");
		} else {
			dao.insert(profesor);
		}
	}

	public void eliminar(String cedula) throws Exception {
		Profesor aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Profesor no registrado");
		} else {
			dao.remove(cedula);
		}

	}

	public void actualizar(Profesor profesor) throws Exception {
		Profesor aux = dao.read(profesor.getCedula());
		if (aux == null) {
			throw new Exception("Profesor no registrado");
		} else {
			dao.update(profesor);
		}
	}

	public List<Profesor> getListProfesores() {
		return dao.getProfesores();
	}

	public Profesor load(String cedula) throws Exception {
		Profesor aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Profesor no registrado");
		} else {
			return aux;
		}
	}

	public List<Profesor> validarLoginProfesor(String email, String password) {
		return dao.autenticacionPro(email, password);
	}
}
