package controlador.peliculas;

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
import servicios.ServicioPelicula;
import servicios.ServicioPromocion;
import utilidades.Validacion;

@WebServlet("/listarDetallePelicula")
public class ListarDetallePeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1734014027653055948L;
	private ServicioPelicula servicioPelicula;
	private Validacion validarDatos;
	private ServicioPromocion servPromocion;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.servPromocion = new ServicioPromocion();
		this.validarDatos = new Validacion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));
		Pelicula pelicula = servicioPelicula.buscarPor(id);
		ArrayList<Integer> idsPromos = servicioPelicula.idPromocionesEnLasQueSeEncuentra(pelicula.getId());
		ArrayList<Promocion> promos = new ArrayList<Promocion>();

		if (!pelicula.esNulo()) {
			for (Integer idPromo : idsPromos) {
				promos.add(servPromocion.buscarPor(idPromo));
			}

			request.setAttribute("pelicula", pelicula);
			request.setAttribute("promos", promos);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/detallesPeliculas.jsp");

			dispatcher.forward(request, response);
		} else {
			request.setAttribute("flash", "Película no encontrada");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}