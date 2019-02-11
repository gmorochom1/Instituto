package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.dao.CursoDAO;
import ec.edu.ups.appdis.jpa.model.Curso;

@Stateless
public class CursoBussiness {

	@Inject
	private CursoDAO dao;

	public void save(Curso curso) throws Exception {
		Curso aux = dao.read(curso.getIdCurso());
		if (aux != null) {
			throw new Exception("Curso ya registrado");
		} else {
			dao.insert(curso);
		}
	}

	public void eliminar(int codigo) throws Exception {
		Curso aux = dao.read(codigo);
		if (aux == null) {
			throw new Exception("Curso no registrado");
		} else {
			System.out.println("Curso eliminar Bussinnes  " + codigo);
			dao.eliminar(aux);
		}
	}

	public void actualizar(Curso curso) throws Exception {
		System.out.println("BUSSINESS CURSO ID" + curso.getIdCurso() + " NOMBRE " + curso.getNombre());
		Curso aux = dao.read(curso.getIdCurso());
		if (aux == null) {
			throw new Exception("Curso no registrado ID");
		} else {
			dao.update(curso);
		}
	}

	public List<Curso> getListCursos() {
		return dao.getCursos();
	}

	public Curso load(int idCurso) throws Exception {
		Curso aux = dao.read(idCurso);
		if (aux == null) {
			throw new Exception("Curso no encontrado " + idCurso);
		} else {
			return aux;
		}
	}

	public List<Curso> getCurProf(String cedula) {
		return dao.getCurProf(cedula);
	}

	public List<Curso> getInfoCurso(int codigo) {
		return dao.getInfoCurso(codigo);
	}
}
