package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.EstudianteDAO;
import ec.edu.ups.appdis.jpa.model.Estudiante;

@Stateless
public class EstudianteBussiness {
	@Inject
	private EstudianteDAO dao;

	public void save(Estudiante estudiante) throws Exception {
		Estudiante aux = dao.read(estudiante.getCedula());
		if (aux != null) {
			throw new Exception("Estudiante ya registrado");
		} else {
			dao.insert(estudiante);
		}
	}

	public void eliminar(String cedula) throws Exception {
		Estudiante aux = dao.read(cedula);
		if (aux == null) {
			throw new Exception("Estudiante no registrado");
		} else {
			dao.remove(cedula);
		}

	}

	public void actualizar(Estudiante estudiante) throws Exception {
		Estudiante aux = dao.read(estudiante.getCedula());
		if (aux == null) {
			throw new Exception("Estudiante no registrado");
		} else {
			dao.update(estudiante);
		}
	}

	public List<Estudiante> getListEstudiantes() {
		return dao.getEstudiantes();
	}

	public Estudiante load(String cedula) throws Exception {
		Estudiante aux = dao.read(cedula);
		if (aux == null) {
			return aux;
			// throw new Exception("Estudiante no registrado");
		} else {
			return aux;
		}
	}

	public List<Estudiante> loadCoincidencias(String campoBusqueda) throws Exception {
		return dao.buscarEstudiantes(campoBusqueda);
	}

	public List<Estudiante> validarLogin(String cedula, String password) {
		return dao.listEstAutenticacion(cedula, password);
	}

	public List<Estudiante> validarLoginEstudiante(String email, String password) {
		return dao.autenticacionEst(email, password);
	}

}
