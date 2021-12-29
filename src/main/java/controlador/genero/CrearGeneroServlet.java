package controlador.genero;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import servicios.ServicioGenero;

@WebServlet("/crearGenero.ad")
public class CrearGeneroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServicioGenero servicioGenero;
     
	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioGenero = new ServicioGenero();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("nombre");
		
		servicioGenero.crear(nombre);
		
		response.sendRedirect("peliculas.do");
	}

}
