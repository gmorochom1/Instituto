package ec.edu.ups.appdis.jpa.view;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.bussiness.EstudianteBussiness;
import ec.edu.ups.appdis.jpa.bussiness.InstitutoBussiness;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Instituto;

@ManagedBean
public class EstudianteBean {

	@Inject
	private InstitutoBussiness institutoBussiness;

	@Inject
	private EstudianteBussiness estudianteBussiness;

	@Inject
	private FacesContext facesContext;

	private Estudiante newEstudiante;
	private List<Estudiante> estudiantes;
	private Instituto newInstituto;
	private boolean editar = false;
	private String cedulaEstudiante;
	private String estado;
	private String cBusqueda;

	@PostConstruct
	public void init() {
		newEstudiante = new Estudiante();
		estudiantes = estudianteBussiness.getListEstudiantes();

	}

	public String getcBusqueda() {
		return cBusqueda;
	}

	public void setcBusqueda(String cBusqueda) {
		this.cBusqueda = cBusqueda;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Estudiante getNewEstudiante() {
		return newEstudiante;
	}

	public void setNewEstudiante(Estudiante newEstudiante) {
		this.newEstudiante = newEstudiante;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
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

	public String getCedulaEstudiante() {
		return cedulaEstudiante;
	}

	public void setCedulaEstudiante(String cedulaEstudiante) {
		this.cedulaEstudiante = cedulaEstudiante;
	}

	public String loadEstudiante(String cedula) {
		try {
			Estudiante e = estudianteBussiness.load(cedula);
			newEstudiante.setCedula(e.getCedula());
			newEstudiante.setNombres(e.getNombres());
			newEstudiante.setDireccion(e.getDireccion());
			newEstudiante.setEmail(e.getEmail());
			// newEstudiante.setInstituto(e.getInstituto());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String guardarEstudiante() {
		System.out.println(editar);
		newInstituto = institutoBussiness.infoIntituto(1);
		try {
			if (editar == true) {
				estudianteBussiness.actualizar(newEstudiante);
				init();
			} else {
				estudianteBussiness.save(newEstudiante);
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

	public String borrar(String cedula) {
		try {
			estudianteBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
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

	public String editar(Estudiante estudiante) {
		newEstudiante = estudiante;
		editar = true;
		return "create-estudiante";
	}

	public void buscarCoincidencia(String campoBusqueda) {
		try {
			estudiantes = estudianteBussiness.loadCoincidencias(campoBusqueda);
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}
	}

	public String validarCedula(String cedula) {
		if (Pattern.matches("[a-zA-Z]+", cedula) == false && cedula.length() > 2) {

			int suma = 0;
			if (cedula.length() == 9) {
				estado = "Faltan digitos de su cédula";
			} else {
				int a[] = new int[cedula.length() / 2];
				int b[] = new int[(cedula.length() / 2)];
				int c = 0;
				int d = 1;
				for (int i = 0; i < cedula.length() / 2; i++) {
					a[i] = Integer.parseInt(String.valueOf(cedula.charAt(c)));
					c = c + 2;
					if (i < (cedula.length() / 2) - 1) {
						b[i] = Integer.parseInt(String.valueOf(cedula.charAt(d)));
						d = d + 2;
					}
				}

				for (int i = 0; i < a.length; i++) {
					a[i] = a[i] * 2;
					if (a[i] > 9) {
						a[i] = a[i] - 9;
					}
					suma = suma + a[i] + b[i];
				}
				int aux = suma / 10;
				int dec = (aux + 1) * 10;
				if ((dec - suma) == Integer.parseInt(String.valueOf(cedula.charAt(cedula.length() - 1))))
					// return "Cédula valida";
					estado = "Cédula valida";
				else if (suma % 10 == 0 && cedula.charAt(cedula.length() - 1) == '0') {
					estado = "Cédula valida";
					// return "Cédula valida";
				} else {
					estado = "Cédula invalida";
					// return "Cédula invalida";
				}
			}

		} else {
			estado = "Solo debe contener numeros";
		}
		return null;
	}

}
