package ec.edu.ups.appdis.jpa.services;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.appdis.jpa.bussiness.InscripcionBussiness;
import ec.edu.ups.appdis.jpa.model.Curso;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Inscripcion;
import ec.edu.ups.appdis.jpa.model.ResumenInscripcion;

@Path("/inscripcion")
public class InscripcionServiceRest {

	@Inject
	private InscripcionBussiness eBussiness;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Inscripcion> getnscripciones() {
		return eBussiness.getListInscripciones();
	}

	@GET
	@Path("/misCursos")
	@Produces("application/json")
	public List<Curso> loadMisCursos(@QueryParam("cedula") String cedula) {
		try {
			System.out.println("Parametro cedula REST " + cedula);
			return eBussiness.getCursosEstCedula(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/participantes")
	@Produces("application/json")
	public List<Estudiante> loadParticipantes(@QueryParam("idCurso") int idCurso) {
		try {
			System.out.println("Parametro idCurso REST " + idCurso);
			return eBussiness.getEstCurso(idCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/resumen")
	@Produces("application/json")
	public ResumenInscripcion getResumenInscripcion() {
		return eBussiness.getResumenInscripcion();
	}

	@GET
	@Path("/filtroCursos")
	@Produces("application/json")
	public List<Curso> filtroCursos(@QueryParam("cedula") String cedula) {
		try {
			System.out.println("Parametro cedula REST filtroCursos " + cedula);
			return eBussiness.filtroCursosOfertados(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
