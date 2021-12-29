package controlador.peliculas;

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
import servicios.ServicioGenero;
import servicios.ServicioPelicula;

@WebServlet("/peliculas")
public class ListarPeliculasServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 4945986439121821695L;
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servicioGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servicioGenero = new ServicioGenero();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Pelicula> peliculas = servicioPelicula.listar();
		ArrayList<Genero> generos = servicioGenero.listar();
		
		request.setAttribute("peliculas", peliculas);
		request.setAttribute("generos", generos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/peliculas.jsp");

		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ArrayList<Pelicula> peliculas = servicioPelicula.listar();
		ArrayList<Genero> generos = servicioGenero.listar();
		
		req.setAttribute("peliculas", peliculas);
		req.setAttribute("generos", generos);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/peliculas.jsp");

		dispatcher.forward(req, resp);
	}
}
