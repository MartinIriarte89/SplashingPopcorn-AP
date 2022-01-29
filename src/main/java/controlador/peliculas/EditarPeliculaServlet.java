package controlador.peliculas;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import modelo.Pelicula;
import servicios.ServicioGenero;
import servicios.ServicioGuardarImagen;
import servicios.ServicioPelicula;
import utilidades.Validacion;

@WebServlet("/editarPelicula.ad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 3132664092732174419L;
	private ServicioPelicula servicioPelicula;
	private ServicioGenero servGenero;
	private ServicioGuardarImagen servGuardarImagen;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servGenero = new ServicioGenero();
		this.servGuardarImagen = new ServicioGuardarImagen();
		this.validarDatos = new Validacion();
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
		String urlPortada = request.getPart("urlPortada").getSubmittedFileName();
		String urlFondo = request.getPart("urlFondo").getSubmittedFileName();
		int anioLanzamiento = validarDatos.esNumeroEnteroValido(request.getParameter("anioLanzamiento"));
		String lema = request.getParameter("lema");
		String generoNombre = request.getParameter("genero");

		Genero genero = servGenero.buscarPor(generoNombre);

		if (!urlPortada.equals("")) {
			urlPortada = id + urlPortada;
			if (!servGuardarImagen.guardarFotoPortadaPelicula(urlPortada, request.getPart("urlPortada"))) {
				Pelicula pelicula = new Pelicula(titulo, precio, duracion, stock, genero, descripcion, urlPortada,
						urlFondo, anioLanzamiento, lema);
				request.setAttribute("errorImagenPortada", servGuardarImagen.getErrores());
				request.setAttribute("peliEditar", pelicula);
				request.setAttribute("serv", "editar");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
				dispatcher.forward(request, response);
				return;
			}
			urlPortada = "imagenes/portadas/peliculas/" + urlPortada;
		}

		if (!urlFondo.equals("")) {
			urlFondo = id + urlFondo;
			if (!servGuardarImagen.guardarFotoFondoPelicula(urlFondo, request.getPart("urlFondo"))) {
				Pelicula pelicula = new Pelicula(titulo, precio, duracion, stock, genero, descripcion, urlPortada,
						urlFondo, anioLanzamiento, lema);
				request.setAttribute("errorImagenFondo", servGuardarImagen.getErrores());
				request.setAttribute("peliEditar", pelicula);
				request.setAttribute("serv", "editar");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
				dispatcher.forward(request, response);
				return;
			}
			urlFondo = "imagenes/fondos/peliculas/" + urlFondo;
		}

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
		} else {
			request.setAttribute("flash", "La película no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
