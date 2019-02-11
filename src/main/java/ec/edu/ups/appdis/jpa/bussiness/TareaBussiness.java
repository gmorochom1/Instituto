package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.dao.TareaDAO;
import ec.edu.ups.appdis.jpa.model.Tarea;

@Stateless
public class TareaBussiness {
	@Inject
	private TareaDAO dao;

	public void save(Tarea tarea) throws Exception {
		System.out.println("Codigo curso tarea " + tarea.getCurso().getIdCurso());
		dao.insert(tarea);
	}

	public void eliminar(int codigo) throws Exception {
		Tarea aux = dao.read(codigo);
		if (aux == null) {
			throw new Exception("Tarea no registrado");
		} else {
			dao.remove(aux);
		}

	}

	public void actualizar(Tarea tarea) throws Exception {
		Tarea aux = dao.read(tarea.getCodigo());
		if (aux == null) {
			throw new Exception("Tarea no registrado");
		} else {
			dao.update(tarea);
		}
	}

	public List<Tarea> getListTareas() {
		return dao.getTareas();
	}

	public List<Tarea> getTareasPorCurso(int idCurso) {
		return dao.getTareasPorCurso(idCurso);
	}
}
