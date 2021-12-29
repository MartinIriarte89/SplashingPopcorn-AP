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

@WebServlet("/editarPelicula.ad")
public class EditarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3132664092732174419L;
	private ServicioPelicula servicioPelicula;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//metodo que valide los datos antes de parsear
		
		int id = Integer.parseInt(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		double precio = Double.parseDouble(request.getParameter("precio"));
		int duracion = Integer.parseInt(request.getParameter("duracion"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String genero = request.getParameter("genero");
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getParameter("urlPortada");
		String urlFondo = request.getParameter("urlFondo");
		int anioLanzamiento = Integer.parseInt(request.getParameter("anioLanzamiento"));
		String lema = request.getParameter("lema");

		Pelicula pelicula = servicioPelicula.editar(id, titulo, precio, duracion, stock, genero, descripcion, urlPortada,
				urlFondo, anioLanzamiento, lema);

		if (pelicula.esValida()) {
			response.sendRedirect("peliculas");
		} else {
			request.setAttribute("pelicula", pelicula);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}

}
