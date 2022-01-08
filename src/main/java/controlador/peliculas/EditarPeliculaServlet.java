package controlador.peliculas;

import java.io.IOException;

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
import utilidades.Validacion;

@WebServlet("/editarPelicula.ad")
public class EditarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3132664092732174419L;
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servGenero;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servGenero = new ServicioGenero();
		validarDatos = new Validacion();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/peliculas");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));
		String titulo = request.getParameter("titulo");
		double precio = validarDatos.esNumeroDoubleValido(request.getParameter("precio"));
		int duracion = validarDatos.esNumeroEnteroValido(request.getParameter("duracion"));
		int stock = validarDatos.esNumeroEnteroValido(request.getParameter("stock"));
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getParameter("urlPortada");
		String urlFondo = request.getParameter("urlFondo");
		int anioLanzamiento = validarDatos.esNumeroEnteroValido(request.getParameter("anioLanzamiento"));
		String lema = request.getParameter("lema");
		String generoNombre = request.getParameter("genero");

		Genero genero = servGenero.buscarPor(generoNombre);

		Pelicula pelicula = servicioPelicula.editar(id, titulo, precio, duracion, stock, genero, descripcion,
				urlPortada, urlFondo, anioLanzamiento, lema);
		
		if (!pelicula.esNulo()) {
			if (pelicula.esValida()) {
				response.sendRedirect("peliculas");
			} else {
				request.setAttribute("peliEditar", pelicula);
				request.setAttribute("serv", "editar");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
				dispatcher.forward(request, response);
			}
		}else {
			request.setAttribute("flash", "La película no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
