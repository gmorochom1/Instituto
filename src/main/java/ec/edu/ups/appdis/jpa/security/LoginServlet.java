package ec.edu.ups.appdis.jpa.security;

import java.io.IOException;
import java.io.PrintWriter;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ec.edu.ups.appdis.jpa.model.Usuario;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Usuario username = new Usuario();
	@Inject
	private MenuFacadeLocal EJB;

	public boolean loggin1() {
		Usuario us;
		boolean redireccionar = false;
		try {
			us = EJB.iniciarSesion(username);
			System.out.println(us);
			System.out.println(username.getPassword());
			System.out.println(username.getEmail());

			if (us != null) {
				redireccionar = true;
				System.out.println("Entro tru");

			} else {
				System.out.println("Entro false");
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));

		}
		return redireccionar;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("login"));
		System.out.println("_+_+_");
		System.out.println(request.getParameter("register"));
		if (request.getParameter("login") != null) {
			// get request parameters for userID and password
			String user = request.getParameter("user");
			String pwd = request.getParameter("pwd");
			username.setPassword(pwd);
			username.setEmail(user);
			System.out.println(username.getPassword());
			System.out.println(loggin1());
			if (loggin1()) {
				Cookie loginCookie = new Cookie("user", user);
				// setting cookie to expiry in 30 mins
				loginCookie.setMaxAge(5 * 60);
				response.addCookie(loginCookie);
				response.sendRedirect("index.xhtml");
			} else {
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
				PrintWriter out = response.getWriter();
				out.println("<font color=black><span>El usuario o la contraseña son incorrectos.</span></font>");
				rd.include(request, response);
			}

		} else if (request.getParameter("register") != null) {
			System.out.println("tesal");
			response.sendRedirect("create-usuario.xhtml");
		} else {
			System.out.println("index");
			response.sendRedirect("login.html");
		}

	}

}