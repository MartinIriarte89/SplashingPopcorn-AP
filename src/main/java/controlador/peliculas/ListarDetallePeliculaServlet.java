package controlador.peliculas;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pelicula;
import servicios.ServicioPelicula;
import utilidades.Validacion;

@WebServlet("/listarDetallePelicula")
public class ListarDetallePeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1734014027653055948L;
	private ServicioPelicula servicioPelicula;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioPelicula = new ServicioPelicula();
		this.validarDatos = new Validacion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));
		Pelicula pelicula = servicioPelicula.buscarPor(id);
		
		if(pelicula.esNulo()) {
			request.setAttribute("flash", "usuario no encontrado");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
			return;
		}
		
		request.setAttribute("pelicula", pelicula);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/detalles.jsp");

		dispatcher.forward(request, response);
	}
}