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
import modelo.Usuario;
import persistencia.FabricaDAO;
import servicios.ServicioComprarPelicula;

@WebServlet("/comprarPelicula.do")
public class ComprarPeliculaServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4083616287286069135L;
	private ServicioComprarPelicula servicioComprarPeli;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioComprarPeli = new ServicioComprarPelicula();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int peliculaId = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Map<String, String> errores = servicioComprarPeli.comprar(usuario.getId(), peliculaId);

		Usuario nuevoUsuario = FabricaDAO.getUsuarioDAO().buscarPorId(usuario.getId());
		request.getSession().setAttribute("usuario", nuevoUsuario);

		if (errores.isEmpty()) {
			request.setAttribute("success", "¡Gracias por comprar!");
		} else {
			request.setAttribute("errors", errores);
			request.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/peliculas");
		dispatcher.forward(request, response);
	}

}
