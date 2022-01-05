package controlador.peliculas;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Pelicula;
import modelo.Usuario;
import persistencia.FabricaDAO;
import servicios.ServicioComprar;
import servicios.ServicioPelicula;
import servicios.validaciones.ValidacionDatosPelicula;

@WebServlet("/comprarPelicula.do")
public class ComprarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4083616287286069135L;
	private ServicioComprar servicioComprarPeli;
	private ServicioPelicula servPelicula;
	private ValidacionDatosPelicula validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioComprarPeli = new ServicioComprar();
		this.servPelicula = new ServicioPelicula();
		this.validarDatos = new ValidacionDatosPelicula();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idString = request.getParameter("id");

		if (!validarDatos.esNumeroValido(idString)) {
			request.setAttribute("flash", "La película no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
			return;
		}

		int peliculaId = Integer.parseInt(idString);
		Pelicula pelicula = servPelicula.buscarPor(peliculaId);

		if (!pelicula.esNulo()) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Map<String, String> errores = servicioComprarPeli.comprar(usuario, pelicula);

			Usuario nuevoUsuario = FabricaDAO.getUsuarioDAO().buscarPorId(usuario.getId());
			request.getSession().setAttribute("usuario", nuevoUsuario);

			if (errores.isEmpty()) {
				request.setAttribute("success", "¡Gracias por comprar!");
			} else {
				request.setAttribute("error", errores);
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("flash", "La película no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/peliculas");
			dispatcher.forward(request, response);
		}
	}
}
