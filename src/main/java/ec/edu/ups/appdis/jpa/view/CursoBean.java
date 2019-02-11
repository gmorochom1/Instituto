package ec.edu.ups.appdis.jpa.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.bussiness.CarreraBussiness;
import ec.edu.ups.appdis.jpa.bussiness.CursoBussiness;
import ec.edu.ups.appdis.jpa.bussiness.ProfesorBussiness;
import ec.edu.ups.appdis.jpa.model.Curso;
import ec.edu.ups.appdis.jpa.model.Carrera;
import ec.edu.ups.appdis.jpa.model.Profesor;

//@SessionScoped
@ManagedBean
public class CursoBean {

	@Inject
	private CarreraBussiness carreraBussiness;

	@Inject
	private CursoBussiness cursoBussiness;

	@Inject
	private ProfesorBussiness profesorBussiness;

	@Inject
	private FacesContext facesContext;

	private Curso newCurso;
	private Carrera newCarrera;
	private Profesor newProfesor;
	private List<Curso> cursos;
	private List<Carrera> carreras;
	private List<Profesor> profesores;

	private List<Profesor> profesoresCarrera;

	private boolean editar = false;
	private List<String> comboBoxDepa;
	private List<String> comboBoxProf;
	private String carreraSeleccionado;
	private String profesorSeleccionado;
	private String pSeleccionado;

	private String nombreCurso;

	private Date date = new Date();

	private List<Curso> cursoPorProfesor;
	private List<String> comboBoxProfesores;
	private List<Profesor> listProfesores;

	@PostConstruct
	public void init() {

		newCurso = new Curso();
		newCarrera = new Carrera();
		newProfesor = new Profesor();

		comboBoxDepa = new ArrayList<>();
		comboBoxProfesores = new ArrayList<>();

		carreras = carreraBussiness.getListCarreras();
		cursos = cursoBussiness.getListCursos();
		listProfesores = profesorBussiness.getListProfesores();

		loadCarreras();
		for (Carrera d : carreras) {
			comboBoxDepa.add(d.getNombre());
		}

		for (Profesor p : profesorBussiness.getListProfesores()) {
			comboBoxProfesores.add(p.getNombre());
		}

	}

	public List<Profesor> getListProfesores() {
		return listProfesores;
	}

	public void setListProfesores(List<Profesor> listProfesores) {
		this.listProfesores = listProfesores;
	}

	public String getpSeleccionado() {
		return pSeleccionado;
	}

	public void setpSeleccionado(String pSeleccionado) {
		this.pSeleccionado = pSeleccionado;
	}

	public List<String> getComboBoxProfesores() {
		return comboBoxProfesores;
	}

	public void setComboBoxProfesores(List<String> comboBoxProfesores) {
		this.comboBoxProfesores = comboBoxProfesores;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Curso getNewCurso() {
		return newCurso;
	}

	public void setNewCurso(Curso newCurso) {
		this.newCurso = newCurso;
	}

	public Carrera getNewCarrera() {
		return newCarrera;
	}

	public void setNewCarrera(Carrera newCarrera) {
		this.newCarrera = newCarrera;
	}

	public Profesor getNewProfesor() {
		return newProfesor;
	}

	public void setNewProfesor(Profesor newProfesor) {
		this.newProfesor = newProfesor;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
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

	public List<String> getComboBoxDepa() {
		return comboBoxDepa;
	}

	public void setComboBoxDepa(List<String> comboBoxDepa) {
		this.comboBoxDepa = comboBoxDepa;
	}

	public List<String> getComboBoxProf() {
		return comboBoxProf;
	}

	public void setComboBoxProf(List<String> comboBoxProf) {
		this.comboBoxProf = comboBoxProf;
	}

	public String getCarreraSeleccionado() {
		return carreraSeleccionado;
	}

	public void setCarreraSeleccionado(String carreraSeleccionado) {
		this.carreraSeleccionado = carreraSeleccionado;
	}

	public String getProfesorSeleccionado() {
		return profesorSeleccionado;
	}

	public void setProfesorSeleccionado(String profesorSeleccionado) {
		this.profesorSeleccionado = profesorSeleccionado;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public List<Profesor> getProfesores() {
		return profesores;
	}

	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}

	public List<Profesor> getProfesoresCarrera() {
		return profesoresCarrera;
	}

	public void setProfesoresCarrera(List<Profesor> profesoresCarrera) {
		this.profesoresCarrera = profesoresCarrera;
	}

	public List<Curso> getCursoPorProfesor() {
		return cursoPorProfesor;
	}

	public void setCursoPorProfesor(List<Curso> cursoPorProfesor) {
		this.cursoPorProfesor = cursoPorProfesor;
	}

	public String loadCurso(String nombre) {

		try {
			for (Curso c : cursos) {
				if (c.getNombre().contentEquals(nombre)) {
					newCurso = cursoBussiness.load(c.getIdCurso());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void loadCarreras() {
		carreras = carreraBussiness.getListCarreras();
	}

	public String loadProfesores() {

		profesores = profesorBussiness.getListProfesores();
		profesoresCarrera = new ArrayList<>();
		comboBoxProf = new ArrayList<>();
		for (Carrera i : carreras) {
			if (i.getNombre().contentEquals(carreraSeleccionado.trim())) {
				for (Profesor p : profesores) {
					if (p.getCarrera().getNombre().equals(i.getNombre())) {
						profesoresCarrera.add(p);
					}
				}
			}

		}
		cargarProfesores();
		return null;
	}

	public String loadProfesoresEditar(String nombreCarrera) {

		profesores = profesorBussiness.getListProfesores();
		profesoresCarrera = new ArrayList<>();
		comboBoxProf = new ArrayList<>();
		for (Carrera i : carreras) {
			if (i.getNombre().contentEquals(nombreCarrera.trim())) {
				for (Profesor p : profesores) {
					if (p.getCarrera().getNombre().equals(i.getNombre())) {
						profesoresCarrera.add(p);
					}
				}
			}

		}
		cargarProfesores();
		return null;
	}

	public void cargarProfesores() {
		for (Profesor pc : profesoresCarrera) {
			comboBoxProf.add(pc.getNombre());
		}
	}

	public String guardarCursoProfesor() {
		System.out.println(editar);
		System.out.println("DATOS CURSO " + newCurso.getNombre() + " ID " + newCurso.getIdCurso());
		System.out.println("PROFESOR SELECCIONADO: " + profesorSeleccionado);

		for (Profesor p : listProfesores) {
			if (p.getNombre().contentEquals(profesorSeleccionado.trim())) {
				newProfesor = p;
				System.out.println("CARRERA : " + newProfesor.getCarrera().getNombre());
			}
		}

		try {
			if (editar == true) {
				newCurso.setCarrera(newProfesor.getCarrera());
				newCurso.setProfesor(newProfesor);
				cursoBussiness.actualizar(newCurso);
				init();
			} else {
				newCurso.setCarrera(newProfesor.getCarrera());
				newCurso.setProfesor(newProfesor);
				cursoBussiness.save(newCurso);
				init();
			}
			System.out.println("Registro guardado");
			return "index?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			// e.printStackTrace();
		}
		return null;
	}

	public String borrar(int codigo) {
		try {
			System.out.println("Curso eliminar " + codigo);
			cursoBussiness.eliminar(codigo);
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

	public String actualizar(Curso curso) {
		newCurso = curso;
		editar = true;

		if (editar == true) {
			for (int i = 0; i < listProfesores.size(); i++) {
				for (Profesor profCarrera : listProfesores) {
					if (profCarrera.getNombre().contentEquals(newCurso.getProfesor().getNombre())) {
						comboBoxProfesores.set(0, profCarrera.getNombre());
					}
				}
			}

		}

		return "create-curso";
	}

	public String editarCompleto(Curso curso) {
		newCurso = curso;
		editar = true;
		if (editar == true) {
			for (int i = 0; i < carreras.size(); i++) {
				for (Carrera carrera : carreras) {
					if (carrera.getNombre().contentEquals(curso.getCarrera().getNombre())) {
						comboBoxDepa.set(0, carrera.getNombre());
						loadProfesoresEditar(carrera.getNombre());
					}
				}
			}

			for (int i = 0; i < profesoresCarrera.size(); i++) {
				for (Profesor profCarrera : profesoresCarrera) {
					if (profCarrera.getNombre().contentEquals(curso.getProfesor().getNombre())) {
						comboBoxProf.set(0, profCarrera.getNombre());
					}
				}
			}

		}
		return "create-curso";
	}

	public String loadCurProfesor(String nombreProfesor) {
		try {
			System.out.println("HOLAP");
			List<Profesor> lista = profesorBussiness.getListProfesores();
			for (Profesor p : lista) {
				if (p.getNombre().equals(nombreProfesor)) {
					System.out.println("Nombre Profesor " + nombreProfesor + " cedula " + p.getCedula());
					cursoPorProfesor = cursoBussiness.getCurProf(p.getCedula());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
