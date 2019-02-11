package ec.edu.ups.appdis.jpa.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Carrera {

	@Id
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String nombre;
	@NotNull
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String director;
	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	@JoinColumn(name = "Carrera")
	private List<Profesor> listaProfesores;

	@JsonIgnore
	@OneToMany(cascade = { CascadeType.ALL })
	@JoinColumn(name = "Carrera")
	private List<Curso> listaCursos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public List<Profesor> getListaProfesores() {
		return listaProfesores;
	}

	public void setListaProfesores(List<Profesor> listaProfesores) {
		this.listaProfesores = listaProfesores;
	}

	public List<Curso> getListaCursos() {
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public void addCurso(Curso curso) {
		if (this.listaCursos == null)
			this.listaCursos = new ArrayList<>();
		this.listaCursos.add(curso);
	}

	public void addProfesor(Profesor profesor) {
		if (this.listaProfesores == null)
			this.listaProfesores = new ArrayList<>();
		this.listaProfesores.add(profesor);
	}

}
