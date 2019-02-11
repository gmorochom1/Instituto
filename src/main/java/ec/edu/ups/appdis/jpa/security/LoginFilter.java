package ec.edu.ups.appdis.jpa.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response= (HttpServletResponse)res;
		String path = ((HttpServletRequest) request).getRequestURI();
		System.out.println(path);
		if (path.contains("create-usuario.xhtml")) {
		    chain.doFilter(request, response); // Just continue chain.
		} else {
		System.out.println("_+_+jkdhjhkdd_");
		System.out.println(req.getParameter("register"));
			System.out.println("cookies1");
			String userName = null;
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user"))
						userName = cookie.getValue();
				}
			}
			if (userName == null)
				{response.sendRedirect(request.getContextPath() + "/login.html");}	
			else {
				chain.doFilter(req, res);// Logged in, just continue request.
		    }
		}
	}
		

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
