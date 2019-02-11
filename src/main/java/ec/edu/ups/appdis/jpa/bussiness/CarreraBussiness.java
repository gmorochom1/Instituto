package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.CarreraDAO;
import ec.edu.ups.appdis.jpa.model.Carrera;

@Stateless
public class CarreraBussiness {
	@Inject
	private CarreraDAO dao;

	public void save(Carrera carrera) throws Exception {
		Carrera aux = dao.read(carrera.getNombre());
		if (aux != null) {
			throw new Exception("Carrera ya registrado");
		} else {
			dao.insert(carrera);
		}
	}

	public void eliminar(String nombre) throws Exception {
		Carrera aux = dao.read(nombre);
		if (aux == null) {
			throw new Exception("Carrera no registrado");
		} else {
			System.out.println("hola me llamo: " + aux.getNombre());
			dao.remove(aux);
		}

	}

	public void actualizar(Carrera carrera) throws Exception {
		Carrera aux = dao.read(carrera.getNombre());
		if (aux == null) {
			throw new Exception("Carrera no registrado");
		} else {
			dao.update(carrera);
		}
	}

	public List<Carrera> getListCarreras() {
		return dao.getCarreras();
	}

	public Carrera load(String nombre) throws Exception {
		Carrera aux = dao.read(nombre);
		if (aux == null) {
			throw new Exception("Carrera no registrado");
		} else {
			return aux;
		}
	}
	/*
	 * public Carrera load(String nombre) throws Exception { List<Carrera> lista =
	 * getListCarreras(); int codigoCarrera = 0; for (Carrera c : lista) { if
	 * (c.getNombre().equalsIgnoreCase(nombre)) { codigoCarrera = c.getIdCarrera();
	 * } } Carrera aux = dao.read(codigoCarrera); if (aux == null) { throw new
	 * Exception("Carrera no registrado"); } else { return aux; } }
	 */

}
