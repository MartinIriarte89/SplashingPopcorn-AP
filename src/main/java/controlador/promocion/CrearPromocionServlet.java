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

@WebServlet("/crearPromocion.ad")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, maxFileSize = 1024 * 1024 * 20, // 20 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class CrearPromocionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -6648217727244375105L;
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

		String titulo = request.getParameter("titulo");
		String[] idPeliculas = validarDatos.split(request.getParameter("idPeliculas"), ",");
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getPart("urlPortada").getSubmittedFileName();
		String tipoPromocion = request.getParameter("tipoPromocion");
		double beneficio = validarDatos.esNumeroDoubleValido(request.getParameter("beneficio"));

		for (String id : idPeliculas) {
			peliculas.add(servPelicula.buscarPor(validarDatos.esNumeroEnteroValido(id)));
		}

		if (!urlPortada.equals("")) {
			if (!servGuardarImagen.guardarFotoPortadaPromocion(urlPortada, request.getPart("urlPortada"))) {
				Promocion promocion = servPromocion.crearPromocionTemp(titulo, peliculas, descripcion, urlPortada,
						tipoPromocion, beneficio);
				request.setAttribute("errorImagen", servGuardarImagen.getErrores());
				request.setAttribute("promocionTempCrear", promocion);
				request.setAttribute("serv", "crear");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
				dispatcher.forward(request, response);
				return;
			}
			urlPortada = "imagenes/portadas/promociones/" + urlPortada;
		}

		Promocion promocion = servPromocion.crear(titulo, peliculas, descripcion, urlPortada, tipoPromocion, beneficio);

		if (promocion.esValida()) {
			response.sendRedirect("promociones");

		} else {
			request.setAttribute("promocionTempCrear", promocion);
			request.setAttribute("serv", "crear");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		}
	}
}
