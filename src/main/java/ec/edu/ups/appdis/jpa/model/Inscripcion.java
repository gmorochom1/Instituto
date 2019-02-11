package ec.edu.ups.appdis.jpa.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Inscripcion {

	@Id
	@GeneratedValue
	private int idInscripcion;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	@OneToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "idEstudiante")
	private Estudiante estudiante;

	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REFRESH, CascadeType.MERGE })
	@JoinColumn(name = "idCurso")
	private Curso curso;

	public int getIdInscripcion() {
		return idInscripcion;
	}

	public void setIdInscripcion(int idInscripcion) {
		this.idInscripcion = idInscripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

}
