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

@WebServlet("/crearPelicula.ad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 20, // 20 MB
maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class CrearPeliculaServlet extends HttpServlet implements Servlet {
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
			servGuardarImagen.guardarFotoPortadaPelicula(urlPortada, request.getPart("urlPortada"));
			urlPortada = "imagenes/portadas/peliculas/" + urlPortada;
		}
		
		if (!urlFondo.equals("")) {
			servGuardarImagen.guardarFotoFondoPelicula(urlFondo, request.getPart("urlFondo"));
			urlFondo = "imagenes/fondos/peliculas/" + urlFondo;
		}

		Pelicula pelicula = servicioPelicula.crear(titulo, precio, duracion, stock, genero, descripcion, urlPortada,
				urlFondo, anioLanzamiento, lema);

		if (pelicula.esValida()) {
			response.sendRedirect("peliculas");
		} else {
			request.setAttribute("peliCrear", pelicula);
			request.setAttribute("serv", "crear");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
