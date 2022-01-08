package controlador.promocion;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pelicula;
import modelo.Promocion;
import servicios.ServicioPromocion;
import utilidades.Validacion;

@WebServlet("/listarDetallePromocion")
public class ListarDetallePromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4201459252912686767L;
	private ServicioPromocion servicioPromocion;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPromocion = new ServicioPromocion();
		this.validarDatos = new Validacion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));

		Promocion promocion = servicioPromocion.buscarPor(id);

		if (!promocion.esNulo()) {
			ArrayList<Pelicula> peliculas = promocion.getPeliculas();
			request.setAttribute("promocion", promocion);
			request.setAttribute("peliculas", peliculas);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/detallesPromociones.jsp");

			dispatcher.forward(request, response);
		} else {
			request.setAttribute("flash", "Promoción no encontrada");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		}

	}

}
