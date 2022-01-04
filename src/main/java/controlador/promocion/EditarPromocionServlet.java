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
import servicios.ServicioPelicula;
import servicios.ServicioPromocion;

@WebServlet("/editarPromocion.ad")
public class EditarPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 4518186313984691446L;
	private ServicioPromocion servPromocion;
	private ServicioPelicula servPelicula;

	public void init() throws ServletException {
		super.init();
		this.servPromocion = new ServicioPromocion();
		this.servPelicula = new ServicioPelicula();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		int id = Integer.parseInt(request.getParameter("idEdit"));
		String titulo = request.getParameter("tituloEdit");
		String[] idPeliculas = request.getParameter("idPeliculasEdit").split(",");
		String descripcion = request.getParameter("descripcionEdit");
		String urlPortada = request.getParameter("urlPortadaEdit");
		String tipoPromocion = request.getParameter("tipoPromocionEdit");
		double beneficio = Double.parseDouble(request.getParameter("beneficioEdit"));

		for (String idPeli : idPeliculas) {
			peliculas.add(servPelicula.buscarPor(Integer.parseInt(idPeli)));
		}

		Promocion promocion = servPromocion.editar(id, titulo, peliculas, descripcion, urlPortada, tipoPromocion,
				beneficio);

		if (promocion.esValida()) {
			response.sendRedirect("promociones");

		} else {
			request.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promocion");
			dispatcher.forward(request, response);
		}
	}
}
