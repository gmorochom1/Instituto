package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.InstitutoDAO;
import ec.edu.ups.appdis.jpa.model.Instituto;

@Stateless
public class InstitutoBussiness {
	@Inject
	private InstitutoDAO dao;

	public void save(Instituto instituto) throws Exception {
		Instituto aux = dao.read(instituto.getIdInstituto());
		if (aux != null) {
			throw new Exception("Instituto ya registrado");
		} else {
			dao.insert(instituto);
		}
	}

	public void eliminar(int codigo) throws Exception {
		Instituto aux = dao.read(codigo);
		if (aux == null) {
			throw new Exception("Instituto no registrado");
		} else {
			dao.remove(codigo);
		}

	}

	public void actualizar(Instituto instituto) throws Exception {
		Instituto aux = dao.read(instituto.getIdInstituto());
		if (aux == null) {
			throw new Exception("Instituto no registrado");
		} else {
			dao.update(instituto);
		}
	}

	public List<Instituto> getListInstitutos() {
		return dao.getInstitutos();
	}

	public Instituto infoIntituto(int idInstituto) {
		return dao.read(idInstituto);
	}
}
