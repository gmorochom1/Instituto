package ec.edu.ups.appdis.jpa.mail;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Controlador {

	public static void enviarCVorreo(String destinatario, String asunto, String cuerpo) throws Exception {
		String remitente1 = "palmer.instituto@gmail.com";
		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.user", remitente1);
			props.put("mail.smtp.clave", "Patito.123");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.ssl.enable", "true");

			Session s = Session.getDefaultInstance(props, null);
			BodyPart texto = new MimeBodyPart();
			texto.setText(cuerpo);

			MimeMultipart m = new MimeMultipart();
			m.addBodyPart(texto);
			System.out.println("Obteniendo reporte");
			String contents = new String(Files.readAllBytes(Paths.get("reporteCursos.html")));
			MimeMessage mensaje = new MimeMessage(s);
			mensaje.setFrom(new InternetAddress("palmer.instituto@gmail.com"));
			mensaje.addRecipients(Message.RecipientType.TO, destinatario);
			mensaje.setSubject(asunto);
			mensaje.setContent(m);
			mensaje.setText(contents, "utf-8", "html");
			Transport t = s.getTransport("smtps");
			System.out.println("!!!!!!!!!!!!!!!!!!!!-[-------------------------");
			t.connect("smtp.gmail.com", remitente1, "Patito.123");
			t.sendMessage(mensaje, mensaje.getAllRecipients());
			System.out.println("************************************");
			t.close();

		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}
}
