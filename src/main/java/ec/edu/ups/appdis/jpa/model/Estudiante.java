package ec.edu.ups.appdis.jpa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Estudiante {

	@NotEmpty
	@Id
	private String cedula;
	@NotNull
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	private String nombres;
	@NotEmpty
	@Email
	@NotNull
	private String email;
	@NotEmpty
	@NotNull
	private String password;
	@NotEmpty
	@NotNull
	private String direccion;

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
