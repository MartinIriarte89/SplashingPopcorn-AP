	package controlador.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servicios.ServicioUsuario;

//crear filtro de administrador.
@WebServlet("/borrarUsuario.ad")
public class BorrarUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 5819851703771386520L;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int idUsuario =Integer.parseInt(req.getParameter("id"));

		boolean esOperacionCorrec = servUsuario.eliminar(idUsuario);

		if (esOperacionCorrec) {
			resp.sendRedirect("listarUsuarios.ad");
		} else {
			req.setAttribute("flash", "No se pudo eliminar al usuario");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
