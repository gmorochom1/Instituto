package ec.edu.ups.appdis.jpa.services;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.appdis.jpa.bussiness.CursoBussiness;
import ec.edu.ups.appdis.jpa.model.Curso;

@Path("/curso")
public class CursoServiceRest {

	@Inject
	private CursoBussiness cBussiness;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Curso> getCursos() {
		return cBussiness.getListCursos();
	}

	@GET
	@Path("/misCursos")
	@Produces("application/json")
	public List<Curso> loadMisCursos(@QueryParam("cedula") String cedula) {
		try {
			System.out.println("Parametro cedulaProfesor REST " + cedula);
			return cBussiness.getCurProf(cedula);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/infoCurso")
	@Produces("application/json")
	public List<Curso> infoCurso(@QueryParam("idcurso") int idcurso) {
		try {
			return cBussiness.getInfoCurso(idcurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
