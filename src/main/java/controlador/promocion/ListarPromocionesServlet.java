package controlador.promocion;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import modelo.Pelicula;
import modelo.Promocion;
import servicios.ServicioGenero;
import servicios.ServicioPelicula;
import servicios.ServicioPromocion;

@WebServlet("/promociones")
public class ListarPromocionesServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 5489548939818339428L;
	private ServicioPromocion servicioPromocion;
	private ServicioGenero servicioGenero;
	private ServicioPelicula servicioPelicula;

	public void init() throws ServletException {
		super.init();
		this.servicioGenero = new ServicioGenero();
		this.servicioPromocion = new ServicioPromocion();
		this.servicioPelicula = new ServicioPelicula();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Promocion promocion = (Promocion) request.getAttribute("promocion");
		
		ArrayList<Pelicula> peliculas = servicioPelicula.listar();
		ArrayList<Genero> generos = servicioGenero.listar();
		ArrayList<Promocion> promociones = servicioPromocion.listar();

		request.setAttribute("peliculas", peliculas);
		request.setAttribute("generos", generos);
		request.setAttribute("promociones", promociones);
		request.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones.jsp");

		dispatcher.forward(request, response);
	}

}
