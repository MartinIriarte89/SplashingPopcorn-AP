package controlador.peliculas;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pelicula;
import servicios.ServicioPelicula;
import utilidades.Validacion;

@WebServlet("/borrarPelicula.ad")
public class BorrarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -7587297484477960461L;
	private ServicioPelicula servicioPelicula;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.validarDatos = new Validacion();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));	
		Pelicula pelicula = servicioPelicula.borrar(id);

		if (!pelicula.esNulo()) {
			response.sendRedirect("peliculas");
		} else {
			request.setAttribute("flash", "La película no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
