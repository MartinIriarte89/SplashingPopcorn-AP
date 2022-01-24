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

@WebServlet("/crearGenero.ad")
public class CrearGeneroServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private ServicioGenero servicioGenero;
     
	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioGenero = new ServicioGenero();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nombre = request.getParameter("genero");
		Genero genero = servicioGenero.crear(nombre);
		
		if(genero.esValido()) {
			response.sendRedirect("peliculas");
		}else {
			request.setAttribute("generoErrores", genero.getErrores());
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}	
	}

}
