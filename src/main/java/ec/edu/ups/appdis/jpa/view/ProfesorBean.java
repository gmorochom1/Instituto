package ec.edu.ups.appdis.jpa.view;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.bussiness.CarreraBussiness;
import ec.edu.ups.appdis.jpa.bussiness.ProfesorBussiness;
import ec.edu.ups.appdis.jpa.model.Carrera;
import ec.edu.ups.appdis.jpa.model.Profesor;

@ManagedBean
public class ProfesorBean {
	@Inject
	private CarreraBussiness carreraBussiness;

	@Inject
	private ProfesorBussiness profesorBussiness;

	@Inject
	private FacesContext facesContext;

	private Profesor newProfesor;
	private List<Profesor> profesores;

	private Carrera newCarrera;
	private List<Carrera> carreras;

	private boolean editar = false;
	private List<String> comboBox;
	private String carreraSeleccionada;
	private String cedulaProfesor;
	private String estado;

	@PostConstruct
	public void init() {

		newProfesor = new Profesor();
		newCarrera = new Carrera();
		comboBox = new ArrayList<>();
		carreras = carreraBussiness.getListCarreras();
		profesores = profesorBussiness.getListProfesores();

		for (Carrera dep : carreras) {
			comboBox.add(dep.getNombre());
		}

	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Profesor getNewProfesor() {
		return newProfesor;
	}

	public void setNewProfesor(Profesor newProfesor) {
		this.newProfesor = newProfesor;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
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

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public List<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(List<String> comboBox) {
		this.comboBox = comboBox;
	}

	public String getCarreraSeleccionada() {
		return carreraSeleccionada;
	}

	public void setCarreraSeleccionada(String carreraSeleccionada) {
		this.carreraSeleccionada = carreraSeleccionada;
	}

	public String getCedulaProfesor() {
		return cedulaProfesor;
	}

	public void setCedulaProfesor(String cedulaProfesor) {
		this.cedulaProfesor = cedulaProfesor;
	}

	public String loadProfesor(String cedula) {
		try {
			Profesor p = profesorBussiness.load(cedula);
			newProfesor.setCedula(p.getCedula());
			newProfesor.setNombre(p.getNombre());
			newProfesor.setDireccion(p.getDireccion());
			newProfesor.setEmail(p.getEmail());
			newProfesor.setCarrera(p.getCarrera());
			newProfesor.setEspecialidad(p.getEspecialidad());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String guardarProfesor() {
		System.out.println(editar);
		System.out.println(carreraSeleccionada);

		for (Carrera i : carreras) {
			if (i.getNombre().contentEquals(carreraSeleccionada.trim())) {
				newCarrera.setNombre(i.getNombre());
				newCarrera.setDirector(i.getDirector());
				System.out.println("CODIGO CARRERA : " + newCarrera.getNombre());
			}
		}
		try {
			if (editar == true) {
				newProfesor.setCarrera(newCarrera);
				profesorBussiness.actualizar(newProfesor);
				init();
			} else {
				newProfesor.setCarrera(newCarrera);
				profesorBussiness.save(newProfesor);
				init();
			}
			System.out.println("Registro guardado");
			return "index?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);

			e.printStackTrace();
		}
		return null;
	}

	public String borrar(String cedula) {
		try {
			profesorBussiness.eliminar(cedula);
			System.out.println("Registro eliminado " + cedula);
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

	public String editar(Profesor profesor) {
		newProfesor = profesor;
		editar = true;
		if (editar == true) {
			for (int i = 0; i < carreras.size(); i++) {
				for (Carrera carrera : carreras) {
					if (carrera.getNombre().contentEquals(profesor.getCarrera().getNombre())) {
						comboBox.set(0, carrera.getNombre());
					}
				}
			}
		}
		return "create-profesor";
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
