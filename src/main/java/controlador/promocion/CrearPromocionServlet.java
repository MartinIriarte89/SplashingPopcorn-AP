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

@WebServlet("/crearPromocion.ad")
public class CrearPromocionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -6648217727244375105L;
	private ServicioPromocion servPromocion;
	private ServicioPelicula servPelicula;

	public void init() throws ServletException {
		super.init();
		this.servPromocion = new ServicioPromocion();
		this.servPelicula = new ServicioPelicula();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		String titulo = request.getParameter("titulo");
		String[] idPeliculas = request.getParameter("idPeliculas").split(",");
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getParameter("urlPortada");
		String tipoPromocion = request.getParameter("tipoPromocion");
		double beneficio = Double.parseDouble(request.getParameter("beneficio"));

		for (String id : idPeliculas) {
			peliculas.add(servPelicula.buscarPor(Integer.parseInt(id)));
		}

		Promocion promocion = servPromocion.crear(titulo, peliculas, descripcion, urlPortada, tipoPromocion, beneficio);
	
		if (promocion.esValida()) {
			response.sendRedirect("promociones");
			
		} else {
			request.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promocion");
			dispatcher.forward(request, response);
		}
	}
}
