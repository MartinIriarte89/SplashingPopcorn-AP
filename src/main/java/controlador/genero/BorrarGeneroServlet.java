package controlador.genero;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import servicios.ServicioGenero;

@WebServlet("/borrarGenero.ad")
public class BorrarGeneroServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4373590678586843834L;
	private ServicioGenero servicioGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioGenero = new ServicioGenero();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("genero");
		Genero genero = servicioGenero.borrar(nombre);

		if (genero.esValido()) {
			resp.sendRedirect("peliculas");
		} else {
			req.setAttribute("generoErrores", genero.getErrores());
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(req, resp);
		}
	}
}
