package ec.edu.ups.appdis.jpa.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import ec.edu.ups.appdis.jpa.bussiness.EstudianteBussiness;
import ec.edu.ups.appdis.jpa.model.Estudiante;

@Path("/estudiante")
public class EstudianteServiceRest {

	@Inject
	private EstudianteBussiness eBussiness;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Estudiante> getEstudiantes() {
		return eBussiness.getListEstudiantes();
	}

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertEstudiante(Estudiante curso) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			eBussiness.save(curso);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.OK).entity(data);
		}
		return builder.build();

	}

	@POST
	@Path("/update")
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEstudiante(Estudiante curso) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			eBussiness.actualizar(curso);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.OK).entity(data);
		}
		return builder.build();

	}

	@POST
	@Path("/delete")
	@Consumes("application/json")
	@Produces("application/json")
	public Response deleteEstudiante(String cedula) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			eBussiness.eliminar(cedula);
			data.put("code", "1");
			data.put("message", "OK");
			builder = Response.status(Response.Status.OK).entity(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data.put("code", "99");
			data.put("message", e.getMessage());
			builder = Response.status(Response.Status.OK).entity(data);
		}
		return builder.build();
	}

	@GET
	@Path("/login")
	@Produces("application/json")
	public List<Estudiante> loginEst(@QueryParam("cedula") String cedula, @QueryParam("password") String password) {
		try {
			return eBussiness.validarLogin(cedula, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/loginEst")
	@Produces("application/json")
	public List<Estudiante> loginEstudiante(@QueryParam("email") String email,
			@QueryParam("password") String password) {
		try {
			return eBussiness.validarLoginEstudiante(email, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
