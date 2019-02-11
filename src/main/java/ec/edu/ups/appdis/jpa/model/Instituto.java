package ec.edu.ups.appdis.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Instituto {

	@Id
	private int idInstituto;
	@NotNull
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String nombre;
	@NotNull
	private String direccion;
	@NotNull
	private String telefono;

	public int getIdInstituto() {
		return idInstituto;
	}

	public void setIdInstituto(int idInstituto) {
		this.idInstituto = idInstituto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
