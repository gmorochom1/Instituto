package ec.edu.ups.appdis.jpa.view;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import ec.edu.ups.appdis.jpa.bussiness.UsuarioBussiness;
import ec.edu.ups.appdis.jpa.model.Instituto;
import ec.edu.ups.appdis.jpa.model.Usuario;
import ec.edu.ups.appdis.jpa.security.MenuFacadeLocal;

@ManagedBean
public class UsuarioBean {

	@Inject
	private MenuFacadeLocal EJBUsuario;
	@Inject
	private UsuarioBussiness usuarioBussiness;
	@Inject
	private FacesContext facesContext;

	private Usuario newUsuario;
	private List<Usuario> usuarios;
	private Instituto newInstituto;
	private boolean editar = false;
	private List<String> comboBox;

	@PostConstruct
	public void init() {
		newUsuario = new Usuario();

		comboBox = new ArrayList<>();

		usuarios = usuarioBussiness.getListUsuarios();

	}

	public UsuarioBussiness getUsuarioBussiness() {
		return usuarioBussiness;
	}

	public void setUsuarioBussiness(UsuarioBussiness usuarioBussiness) {
		this.usuarioBussiness = usuarioBussiness;
	}

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public Usuario getNewUsuario() {
		return newUsuario;
	}

	public void setNewUsuario(Usuario newUsuario) {
		this.newUsuario = newUsuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public Instituto getNewInstituto() {
		return newInstituto;
	}

	public void setNewInstituto(Instituto newInstituto) {
		this.newInstituto = newInstituto;
	}

	public List<String> getComboBox() {
		return comboBox;
	}

	public void setComboBox(List<String> comboBox) {
		this.comboBox = comboBox;
	}

	public String loadUsuario(String email) {
		try {
			Usuario us = usuarioBussiness.load(email);
			newUsuario.setEmail(us.getEmail());
			newUsuario.setPassword(us.getPassword());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public String guardarUsuarios() {
		System.out.println(editar);
		try {
			if (editar == true) {
				System.out.println(newUsuario.getEmail());
				usuarioBussiness.actualizar(newUsuario);
				init();
			} else {
				usuarioBussiness.save(newUsuario);
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
			usuarioBussiness.eliminar(cedula);
			System.out.println("Registro eliminado");
			return "index?faces-redirect=true";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage());
			facesContext.addMessage(null, m);

			e.printStackTrace();
		}
		return null;
	}

	public String editar(Usuario usuario) {
		newUsuario = usuario;
		editar = true;
		return "create-profesor";
	}

	public String iniciarSesion() {
		Usuario us;
		String redireccion = null;
		try {
			us = EJBUsuario.iniciarSesion(newUsuario);
			if (us != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
				redireccion = "index.xhtml?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Credenciales incorrectas"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error!"));
		}
		return redireccion;
	}

}
