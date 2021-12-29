package controlador.genero;

import java.io.IOException;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioGenero;

@WebServlet("/borrarGenero.ad")
public class BorrarGeneroServlet extends HttpServlet implements Servlet{
 
	private static final long serialVersionUID = 4373590678586843834L;
	private ServicioGenero servicioGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioGenero = new ServicioGenero();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		servicioGenero.borrar(nombre);

		response.sendRedirect("/pelicula");
		
	}
}
