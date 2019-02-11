package ec.edu.ups.appdis.jpa.security;

import javax.ejb.Local;

import ec.edu.ups.appdis.jpa.model.Usuario;

@Local
public interface MenuFacadeLocal {
	Usuario iniciarSesion(Usuario us) throws Exception;
}
