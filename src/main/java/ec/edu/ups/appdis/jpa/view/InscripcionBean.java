package ec.edu.ups.appdis.jpa.view;

import java.io.BufferedOutputStream;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.appdis.jpa.bussiness.CursoBussiness;
import ec.edu.ups.appdis.jpa.bussiness.EstudianteBussiness;
import ec.edu.ups.appdis.jpa.bussiness.InscripcionBussiness;
import ec.edu.ups.appdis.jpa.bussiness.InstitutoBussiness;
import ec.edu.ups.appdis.jpa.model.Curso;
import ec.edu.ups.appdis.jpa.model.Estudiante;
import ec.edu.ups.appdis.jpa.model.Inscripcion;
import ec.edu.ups.appdis.jpa.model.Instituto;
import ec.edu.ups.appdis.jpa.model.ResumenInscripcion;

//@SessionScoped
@ManagedBean
public class InscripcionBean {
	@Inject
	private InstitutoBussiness institutoBussiness;

	@Inject
	private InscripcionBussiness inscripcionBussiness;

	@Inject
	private EstudianteBussiness estudianteBussiness;

	@Inject
	private CursoBussiness cursoBussiness;

	@Inject
	private FacesContext facesContext;

	private Inscripcion newInscripcion;
	private List<Inscripcion> inscripciones;
	private Instituto newInstituto;

	private Curso newCursoTemp;
	private Estudiante newEstudianteTemp;
	private List<Curso> cursos;
	private List<Estudiante> estudiantes;
	private boolean editar = false;
	private List<String> comboCursos;
	private String cursoSeleccionado;
	private String estudianteCedula;

	private List<Estudiante> estudiantesPorCurso;

	private List<Curso> cursosPorEstudiante;
	private List<String> comboEstudiantes;
	private String estudianteSeleccionado;
	Date date = new Date();
	private int numeroIns;
	private Double totalRecaudado;

	private String encontrado;

	@PostConstruct
	public void init() {

		newInscripcion = new Inscripcion();
		newCursoTemp = new Curso();
		newEstudianteTemp = new Estudiante();
		newEstudianteTemp.setCedula(" ");
		resumen();

		comboCursos = new ArrayList<>();
		comboEstudiantes = new ArrayList<>();

		inscripciones = inscripcionBussiness.getListInscripciones();
		cursos = cursoBussiness.getListCursos();
		estudiantes = estudianteBussiness.getListEstudiantes();

		for (Curso d : cursos) {
			comboCursos.add(d.getNombre());
		}

		for (Estudiante e : estudiantes) {
			comboEstudiantes.add(e.getNombres());
		}

	}

	public Inscripcion getNewInscripcion() {
		return newInscripcion;
	}

	public void setNewInscripcion(Inscripcion newInscripcion) {
		this.newInscripcion = newInscripcion;
	}

	public List<Inscripcion> getInscripciones() {
		return inscripciones;
	}

	public void setInscripciones(List<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
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

	public Curso getNewCursoTemp() {
		return newCursoTemp;
	}

	public void setNewCursoTemp(Curso newCursoTemp) {
		this.newCursoTemp = newCursoTemp;
	}

	public Estudiante getNewEstudianteTemp() {
		return newEstudianteTemp;
	}

	public void setNewEstudianteTemp(Estudiante newEstudianteTemp) {
		this.newEstudianteTemp = newEstudianteTemp;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public List<String> getComboCursos() {
		return comboCursos;
	}

	public void setComboCursos(List<String> comboCursos) {
		this.comboCursos = comboCursos;
	}

	public String getCursoSeleccionado() {
		return cursoSeleccionado;
	}

	public void setCursoSeleccionado(String cursoSeleccionado) {
		this.cursoSeleccionado = cursoSeleccionado;
	}

	public String getEstudianteCedula() {
		return estudianteCedula;
	}

	public void setEstudianteCedula(String estudianteCedula) {
		this.estudianteCedula = estudianteCedula;
	}

	public List<Estudiante> getEstudiantesPorCurso() {
		return estudiantesPorCurso;
	}

	public void setEstudiantesPorCurso(List<Estudiante> estudiantesPorCurso) {
		this.estudiantesPorCurso = estudiantesPorCurso;
	}

	public List<Curso> getCursosPorEstudiante() {
		return cursosPorEstudiante;
	}

	public void setCursosPorEstudiante(List<Curso> cursosPorEstudiante) {
		this.cursosPorEstudiante = cursosPorEstudiante;
	}

	public List<String> getComboEstudiantes() {
		return comboEstudiantes;
	}

	public void setComboEstudiantes(List<String> comboEstudiantes) {
		this.comboEstudiantes = comboEstudiantes;
	}

	public String getEstudianteSeleccionado() {
		return estudianteSeleccionado;
	}

	public void setEstudianteSeleccionado(String estudianteSeleccionado) {
		this.estudianteSeleccionado = estudianteSeleccionado;
	}

	public int getNumeroIns() {
		return numeroIns;
	}

	public void setNumeroIns(int numeroIns) {
		this.numeroIns = numeroIns;
	}

	public Double getTotalRecaudado() {
		return totalRecaudado;
	}

	public void setTotalRecaudado(Double totalRecaudado) {
		this.totalRecaudado = totalRecaudado;
	}

	public String getEncontrado() {
		return encontrado;
	}

	public void setEncontrado(String encontrado) {
		this.encontrado = encontrado;
	}

	public String loadEstudiante(String cedula) {
		try {
			if (estudianteBussiness.load(cedula) == null) {
				encontrado = "Estudiante no encontrado verifique la Cédula";
			} else {
				newEstudianteTemp = estudianteBussiness.load(cedula);
				encontrado = "Estudiante encontrado";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void loadCursos(String nombre) {
		System.out.println(nombre);
		try {
			for (Curso c : cursos) {
				if (c.getNombre().equals(nombre)) {
					newCursoTemp = cursoBussiness.load(c.getIdCurso());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String guardarInscripcion(String cedulaEstudiante, String nombreCurso) {
		System.out.println(editar);
		newInstituto = institutoBussiness.infoIntituto(1);
		Curso curso = new Curso();
		Estudiante estudiante = new Estudiante();

		try {
			if (editar == true) {
				// newInscripcion.setInstituto(newInstituto);
				newInscripcion.setEstudiante(newEstudianteTemp);
				newInscripcion.setCurso(newCursoTemp);
				inscripcionBussiness.actualizar(newInscripcion);
				init();
			} else {

				for (Curso c : cursos) {
					if (c.getNombre().equals(nombreCurso)) {
						curso = cursoBussiness.load(c.getIdCurso());
					}
				}

				if (estudianteBussiness.load(cedulaEstudiante) == null) {
					encontrado = "Estudiante no encontrado verifique la Cédula";
				} else {
					estudiante = estudianteBussiness.load(cedulaEstudiante);
					encontrado = "Estudiante encontrado";
				}

				if (curso.getNombre() == null || estudiante.getCedula() == null || newInscripcion.getFecha() == null) {
					System.out.println("Mal ingresado los datos o campos vacios");
				} else {
					System.out.println("ENTRE Crear Inscripcion");
					System.out.println(newCursoTemp.getNombre());
					System.out.println(newInscripcion.getFecha());
					newInscripcion.setEstudiante(estudiante);
					newInscripcion.setCurso(curso);
					inscripcionBussiness.save(newInscripcion);
					System.out.println("Registro guardado");
					init();
				}
			}
			init();

			return "index?faces-redirect=true";
		} catch (Exception e) {
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);
			// e.printStackTrace();
		}
		return null;
	}

	public String borrar(int id) {
		try {
			inscripcionBussiness.eliminar(id);
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

	public String editar(Inscripcion inscripcion) {
		newInscripcion = inscripcion;
		String ced = estudianteCedula = inscripcion.getEstudiante().getCedula();
		loadEstudiante(ced);
		editar = true;
		if (editar == true) {
			for (int i = 0; i < cursos.size(); i++) {
				for (Curso cur : cursos) {
					if (cur.getNombre().contentEquals(inscripcion.getCurso().getNombre())) {
						comboCursos.set(0, cur.getNombre());
						loadCursos(cur.getNombre());
					}
				}
			}
		}
		return "create-inscripcion";
	}

	public String loadEstCurso(String nombreCurso) {
		try {
			List<Curso> lista = cursos;
			int idCurso = 0;
			for (Curso c : lista) {
				if (c.getNombre().equals(nombreCurso)) {
					System.out.println("Nombre Curso " + nombreCurso + " idCurso " + c.getIdCurso());
					idCurso = c.getIdCurso();
					estudiantesPorCurso = inscripcionBussiness.getEstCurso(idCurso);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String loadEstCursoPorID(int idCurso) {
		try {
			estudiantesPorCurso = inscripcionBussiness.getEstCurso(idCurso);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String loadCursosEst(String nombreEstudiante) {
		try {
			cursosPorEstudiante = inscripcionBussiness.getCursosEst(nombreEstudiante);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String resumen() {

		ResumenInscripcion temp = inscripcionBussiness.getResumenInscripcion();
		numeroIns = temp.getNumero();
		totalRecaudado = temp.getTotalRecaudado();

		return null;
	}

	String str = "http://localhost:8080/Proyecto-Interciclo/list-cursos.xhtml";

	public String generate() throws Exception {

		URL url = new URL(str);
		URLConnection urlConn = url.openConnection();
		urlConn.setRequestProperty("Cookie",
				"user=invitado; domain=http://localhost:8080; path=/Proyecto-Interciclo/list-cursos.xhtml");

		InputStream inputStream = urlConn.getInputStream();
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("reporteCurso.html"));
		BufferedInputStream input = new BufferedInputStream(inputStream);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^" + input);

		int i;
		while ((i = input.read()) != -1) {
			out.write(i);

		}

		input.close();
		out.flush();
		out.close();
		return "correo";
	}

}
