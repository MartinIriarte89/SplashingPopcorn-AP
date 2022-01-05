package controlador.peliculas;

import java.io.IOException;
import java.util.Map;

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
import servicios.validaciones.ValidacionDatosPelicula;

@WebServlet("/editarPelicula.ad")
public class EditarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3132664092732174419L;
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servGenero;
	private ValidacionDatosPelicula validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servGenero = new ServicioGenero();
		validarDatos = new ValidacionDatosPelicula();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("id");
		String titulo = request.getParameter("titulo");
		String precioString = request.getParameter("precio");
		String duracionString = request.getParameter("duracion");
		String stockString = request.getParameter("stock");
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getParameter("urlPortada");
		String urlFondo = request.getParameter("urlFondo");
		String anioLanzamientoString = request.getParameter("anioLanzamiento");
		String lema = request.getParameter("lema");
		String generoNombre = request.getParameter("genero");

		if (!validarDatos.datosSonValidos(idString, titulo, precioString, duracionString, stockString, descripcion,
				anioLanzamientoString, lema)) {
			Map<String, String> errores = validarDatos.getErrores();

			request.setAttribute("errores", errores);

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
			return;
		}

		Genero genero = servGenero.buscarPor(generoNombre);
		
		if (genero.esNulo()) {
			request.setAttribute("flash", "El genero no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
			return;
		}

		int id = Integer.parseInt(idString);
		double precio = Double.parseDouble(precioString);
		int duracion = Integer.parseInt(duracionString);
		int stock = Integer.parseInt(stockString);
		int anioLanzamiento = Integer.parseInt(anioLanzamientoString);

		Pelicula pelicula = servicioPelicula.editar(id, titulo, precio, duracion, stock, genero, descripcion,
				urlPortada, urlFondo, anioLanzamiento, lema);

		if (pelicula.esValida()) {
			response.sendRedirect("peliculas");
		} else {
			request.setAttribute("errores", pelicula.getErrores());

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
