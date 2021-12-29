package controlador.peliculas;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioPelicula;

@WebServlet("/borrarPelicula.ad")
public class BorrarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -7587297484477960461L;
	private ServicioPelicula servicioPelicula;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		servicioPelicula.borrar(id);

		response.sendRedirect("peliculas");
	}
}
