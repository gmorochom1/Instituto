package ec.edu.ups.appdis.jpa.services;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import ec.edu.ups.appdis.jpa.bussiness.CarreraBussiness;
import ec.edu.ups.appdis.jpa.model.Carrera;

@Path("/carrera")
public class CarreraServiceRest {

	@Inject
	private CarreraBussiness cBussiness;

	@GET
	@Path("/list")
	@Produces("application/json")
	public List<Carrera> getCarreras() {
		return cBussiness.getListCarreras();
	}
}
