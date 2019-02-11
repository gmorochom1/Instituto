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

import ec.edu.ups.appdis.jpa.bussiness.ProfesorBussiness;
import ec.edu.ups.appdis.jpa.model.Profesor;

@Path("/profesor")
public class ProfesorServiceRest {

	@Inject
	private ProfesorBussiness pBussiness;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Profesor> getProfesores() {
		return pBussiness.getListProfesores();
	}

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insertProfesor(Profesor curso) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			pBussiness.save(curso);
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
	public Response updateProfesor(Profesor curso) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			pBussiness.actualizar(curso);
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
	public Response deleteProfesor(String cedula) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			pBussiness.eliminar(cedula);
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
	@Path("/loginPro")
	@Produces("application/json")
	public List<Profesor> loginProfesor(@QueryParam("email") String email, @QueryParam("password") String password) {
		try {
			return pBussiness.validarLoginProfesor(email, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
