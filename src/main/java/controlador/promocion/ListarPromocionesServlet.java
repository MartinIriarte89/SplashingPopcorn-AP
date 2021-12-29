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
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servicioGenero;

	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servicioGenero = new ServicioGenero();
		this.servicioPromocion = new ServicioPromocion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Pelicula> peliculas = servicioPelicula.listar();
		ArrayList<Genero> generos = servicioGenero.listar();
		ArrayList<Promocion> promociones = servicioPromocion.listar();

		request.setAttribute("peliculas", peliculas);
		request.setAttribute("generos", generos);
		request.setAttribute("promociones", promociones);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/promociones.jsp");

		dispatcher.forward(request, response);
	}

}
