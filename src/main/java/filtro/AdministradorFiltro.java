package filtro;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import modelo.Usuario;

@WebFilter(urlPatterns = "*.ad")
public class AdministradorFiltro implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		Usuario usuario = (Usuario) ((HttpServletRequest) request).getSession().getAttribute("usuario");

		if (usuario != null) {
			if (usuario.esAdmin()) {
				chain.doFilter(request, response);
			} else {
				request.setAttribute("flash", "Debe ser ");

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("flash", "Por favor, ingresa al sistema");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
