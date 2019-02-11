package ec.edu.ups.appdis.jpa.view;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.bussiness.CarreraBussiness;
import ec.edu.ups.appdis.jpa.bussiness.EstudianteBussiness;
import ec.edu.ups.appdis.jpa.bussiness.InstitutoBussiness;
import ec.edu.ups.appdis.jpa.model.Carrera;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Instituto;

@ManagedBean
public class InstitutoBean {
	@Inject
	private InstitutoBussiness institutoBussiness;

	@Inject
	private CarreraBussiness carreraBussiness;

	@Inject
	private EstudianteBussiness estudianteBussiness;

	@Inject
	private FacesContext facesContext;

	private Instituto newInstituto;
	private Carrera newCarrera;
	private Estudiante newEstudiante;
	private List<Carrera> carreras;
	private List<Estudiante> estudiantes;
	private boolean edit = false;

	@PostConstruct
	public void init() {

		newCarrera = new Carrera();
		newEstudiante = new Estudiante();
		carreras = carreraBussiness.getListCarreras();
		estudiantes = estudianteBussiness.getListEstudiantes();
		infoInstituto();
	}

	public Instituto getNewInstituto() {
		return newInstituto;
	}

	public void setNewInstituto(Instituto newInstituto) {
		this.newInstituto = newInstituto;
	}

	public Carrera getNewCarrera() {
		return newCarrera;
	}

	public void setNewCarrera(Carrera newCarrera) {
		this.newCarrera = newCarrera;
	}

	public Estudiante getNewEstudiante() {
		return newEstudiante;
	}

	public void setNewEstudiante(Estudiante newEstudiante) {
		this.newEstudiante = newEstudiante;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public String guardarInstituto() {
		System.out.println(edit);
		try {
			if (edit == true) {
				institutoBussiness.actualizar(newInstituto);
				System.out.println("Registro actualizado");
				init();
			} else {
				institutoBussiness.save(newInstituto);
				System.out.println("Registro guardado");
				init();
			}
			return "index?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			e.printStackTrace();
		}
		return null;
	}

	public String borrar(int id) {
		try {
			institutoBussiness.eliminar(id);
			System.out.println("Registro eliminado");
			return "index?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);

			e.printStackTrace();
		}
		return null;
	}

	public String editar(Instituto instituto) {
		newInstituto = instituto;
		edit = true;
		return "create-instituto";
	}

	public String infoInstituto() {
		newInstituto = institutoBussiness.infoIntituto(1);
		return null;
	}
}
