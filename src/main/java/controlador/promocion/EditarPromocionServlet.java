package controlador.promocion;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pelicula;
import modelo.Promocion;
import servicios.ServicioGuardarImagen;
import servicios.ServicioPelicula;
import servicios.ServicioPromocion;
import utilidades.Validacion;

@WebServlet("/editarPromocion.ad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 20, // 20 MB
maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditarPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4518186313984691446L;
	private ServicioPromocion servPromocion;
	private ServicioPelicula servPelicula;
	private ServicioGuardarImagen servGuardarImagen;
	private Validacion validarDatos;

	public void init() throws ServletException {
		super.init();
		this.servPromocion = new ServicioPromocion();
		this.servPelicula = new ServicioPelicula();
		this.servGuardarImagen = new ServicioGuardarImagen();
		this.validarDatos = new Validacion();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/promociones");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		int id = validarDatos.esNumeroEnteroValido(request.getParameter("idEdit"));
		String titulo = request.getParameter("tituloEdit");
		String[] idPeliculas = validarDatos.split(request.getParameter("idPeliculasEdit"), ",");
		String descripcion = request.getParameter("descripcionEdit");
		String urlPortada = request.getPart("urlPortadaEdit").getSubmittedFileName();
		String tipoPromocion = request.getParameter("tipoPromocionEdit");
		double beneficio = validarDatos.esNumeroDoubleValido(request.getParameter("beneficioEdit"));

		for (String idPeli : idPeliculas) {
			peliculas.add(servPelicula.buscarPor(validarDatos.esNumeroEnteroValido(idPeli)));
		}
		
		if (!urlPortada.equals("")) {
			urlPortada = id + urlPortada;
			servGuardarImagen.guardarFotoPortadaPromocion(urlPortada, request.getPart("urlPortadaEdit"));
			urlPortada = "imagenes/portadas/promociones/" + urlPortada;
		}

		Promocion promocion = servPromocion.editar(id, titulo, peliculas, descripcion, urlPortada, tipoPromocion,
				beneficio);

		if (!promocion.esNulo()) {
			if (promocion.esValida()) {
				response.sendRedirect("promociones");
			} else {
				request.setAttribute("promocionTempEdit", promocion);
				request.setAttribute("serv", "editar");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("flash", "La promoción no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		}
	}
}
