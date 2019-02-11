package ec.edu.ups.appdis.jpa.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.bussiness.CarreraBussiness;
import ec.edu.ups.appdis.jpa.bussiness.InstitutoBussiness;
import ec.edu.ups.appdis.jpa.model.Carrera;
import ec.edu.ups.appdis.jpa.model.Instituto;

@ManagedBean
public class CarreraBean {
	@Inject
	private InstitutoBussiness institutoBussiness;

	@Inject
	private CarreraBussiness carreraBussiness;

	@Inject
	private FacesContext facesContext;

	private Carrera newCarrera;
	private List<Carrera> carreras;
	private Instituto newInstituto;
	private boolean editar = false;
	private String nombreCarrera;

	@PostConstruct
	public void init() {
		newCarrera = new Carrera();
		newInstituto = institutoBussiness.infoIntituto(1);
		carreras = carreraBussiness.getListCarreras();
	}

	public Carrera getNewCarrera() {
		return newCarrera;
	}

	public void setNewCarrera(Carrera newCarrera) {
		this.newCarrera = newCarrera;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Instituto getNewInstituto() {
		return newInstituto;
	}

	public void setNewInstituto(Instituto newInstituto) {
		this.newInstituto = newInstituto;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public String getNombreCarrera() {
		return nombreCarrera;
	}

	public void setNombreCarrera(String nombreCarrera) {
		this.nombreCarrera = nombreCarrera;
	}

	public String loadCarrera(String nombre) {
		try {
			Carrera c = carreraBussiness.load(nombre);
			//newCarrera.setInstituto(c.getInstituto());
			newCarrera.setNombre(c.getNombre());
			newCarrera.setDirector(c.getDirector());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String guardarCarrera() {
		System.out.println(editar);
		newInstituto = institutoBussiness.infoIntituto(1);
		//newCarrera.setInstituto(newInstituto);
		try {
			if (editar == true) {
				System.out.println(newCarrera.getNombre());
				carreraBussiness.actualizar(newCarrera);
				init();
			} else {
				carreraBussiness.save(newCarrera);
				init();
			}
			System.out.println("Registro guardado");
			return "index?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			e.printStackTrace();
		}
		return null;
	}

	public String borrar(String nombre) {
		try {
			System.out.println("Codigo a eliminar: " + nombre);
			carreraBussiness.eliminar(nombre);
			init();
			return "index?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);

			e.printStackTrace();
		}
		return null;
	}

	public String editar(Carrera carrera) {
		newCarrera = carrera;
		editar = true;
		return "create-carrera";
	}
}
