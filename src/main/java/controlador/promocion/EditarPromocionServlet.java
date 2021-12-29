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
import modelo.Promocion;
import servicios.ServicioPromocion;

@WebServlet("/editarPromocion.ad")
public class EditarPromocionServlet extends HttpServlet implements Servlet{

	private static final long serialVersionUID = 4518186313984691446L;
	private ServicioPromocion servicioPromocion;

	public void init() throws ServletException {
		super.init();
		this.servicioPromocion = new ServicioPromocion();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));

		Promocion promocion = servicioPromocion.buscarPor(id);
		request.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("vistas/promocion.jsp"); //RUTA AL modal
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String titulo = request.getParameter("titulo");
		String pelicula1 = request.getParameter("pelicula1");
		String pelicula2 = request.getParameter("pelicula2");
		String pelicula3 = request.getParameter("pelicula3");
		String descripcion = request.getParameter("descripcion");
		String urlPortada = request.getParameter("urlPortada");
		
		//falta pedirle al servicio pelicula que traiga esas pelis y meterlas en un array
		//el tema es que no tengo de d�nde buscar el id.
		
		/*ArrayList<Pelicula> pelisEnPromo =new ArrayList<>();
		
		Promocion promocion = servicioPromocion.editar(0, titulo, pelisEnPromo, descripcion, urlPortada);*/
		
	}

}
