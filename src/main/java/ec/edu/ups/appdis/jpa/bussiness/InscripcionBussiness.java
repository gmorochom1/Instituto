package ec.edu.ups.appdis.jpa.bussiness;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.dao.CursoDAO;
import ec.edu.ups.appdis.jpa.dao.EstudianteDAO;
import ec.edu.ups.appdis.jpa.dao.InscripcionDAO;
import ec.edu.ups.appdis.jpa.model.Curso;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Inscripcion;
import ec.edu.ups.appdis.jpa.model.ResumenInscripcion;

@Stateless
public class InscripcionBussiness {

	@Inject
	private InscripcionDAO dao;
	@Inject
	private EstudianteDAO daoEstudiante;
	@Inject
	private CursoDAO daoCurso;

	public void save(Inscripcion inscripcion) throws Exception {
		Inscripcion aux = dao.read(inscripcion.getIdInscripcion());
		if (aux != null) {
			throw new Exception("inscripcion ya registrado");
		} else {
			dao.insert(inscripcion);
		}
	}

	public void eliminar(int codigo) throws Exception {
		Inscripcion aux = dao.read(codigo);
		if (aux == null) {
			throw new Exception("inscripcion no registrado");
		} else {
			dao.remove(codigo);
		}

	}

	public void actualizar(Inscripcion inscripcion) throws Exception {
		Inscripcion aux = dao.read(inscripcion.getIdInscripcion());
		if (aux == null) {
			throw new Exception("inscripcion no registrado");
		} else {
			dao.update(inscripcion);
		}
	}

	public List<Inscripcion> getListInscripciones() {
		return dao.getInscripciones();
	}

	public List<Estudiante> getEstCurso(int idCurso) {
		return dao.getEstCurso(idCurso);
	}

	public List<Curso> getCursosEst(String nombre) {
		String cedula = "";
		for (Estudiante e : daoEstudiante.getEstudiantes()) {
			if (e.getNombres().equals(nombre)) {
				cedula = e.getCedula();
			}
		}
		System.out.println("Cedula del estudiante " + cedula);
		return dao.getCursosEst(cedula);
	}

	public List<Curso> getCursosEstCedula(String cedula) {
		System.out.println("Cedula del estudiante REST " + cedula);
		return dao.getCursosEst(cedula);
	}

	public ResumenInscripcion getResumenInscripcion() {
		Double totalRecaudado = 0.0;
		List<Inscripcion> inscripciones = dao.getInscripciones();
		for (Inscripcion i : inscripciones) {
			totalRecaudado = totalRecaudado + i.getCurso().getCosto();
		}
		ResumenInscripcion resumenCine = new ResumenInscripcion();
		resumenCine.setNumero(inscripciones.size());
		resumenCine.setTotalRecaudado(totalRecaudado);
		return resumenCine;
	}

	public List<Curso> filtroCursosOfertados(String cedulaEst) {

		List<Curso> cursosMatriculado = dao.getCursosEst(cedulaEst);
		List<Curso> cursostemp = daoCurso.getCursos();

		for (int i = 0; i < cursostemp.size(); i++) {
			for (Curso cursosMa : cursosMatriculado) {
				if (cursostemp.get(i).getNombre().contentEquals(cursosMa.getNombre())) {
					System.out.println("Curso ya matriculado " + cursostemp.get(i).getNombre());
					cursostemp.remove(i);
				}
			}
		}
		return cursostemp;
	}

}
