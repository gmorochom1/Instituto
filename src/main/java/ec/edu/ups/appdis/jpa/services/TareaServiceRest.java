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

import ec.edu.ups.appdis.jpa.bussiness.TareaBussiness;
import ec.edu.ups.appdis.jpa.model.Profesor;
import ec.edu.ups.appdis.jpa.model.Tarea;

@Path("/tarea")
public class TareaServiceRest {

	@Inject
	private TareaBussiness tBussiness;

	@GET
	@Path("/tareas")
	@Produces("application/json")
	public List<Tarea> tareas(@QueryParam("idCurso") int idCurso) {
		try {
			System.out.println("Parametro idCurso REST TAREA " + idCurso);
			return tBussiness.getTareasPorCurso(idCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@POST
	@Path("/insert")
	@Consumes("application/json")
	@Produces("application/json")
	public Response insert(Tarea tarea) {
		Response.ResponseBuilder builder = null;
		Map<String, String> data = new HashMap<>();
		try {
			tBussiness.save(tarea);
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
	@Path("/list")
	@Produces("application/json")
	public List<Tarea> getTareas() {
		return tBussiness.getListTareas();
	}

}
