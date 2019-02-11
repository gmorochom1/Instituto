package ec.edu.ups.appdis.jpa.mail;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named
@ManagedBean
@RequestScoped
public class Correo {

	private String recipient;
	private String subject;
	private String message;
	private String statusMessage = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public String send() {
		statusMessage = "Message Sent";
		try {
			Controlador.enviarCVorreo(recipient, subject, message);
			System.out.println("Mensaje Enviado");
		} catch (Exception ex) {
			statusMessage = ex.getMessage();
			System.out.println("Error" + ex);
		}
		return "index";
	}

}