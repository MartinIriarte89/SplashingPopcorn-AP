package controlador.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.ServicioUsuario;

@WebServlet("/editarUsuario.do")
public class EditarUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 750290554834772235L;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));

		Usuario usuario = servUsuario.buscar(id);
		req.setAttribute("usuario", usuario);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		// VALIDAR LOS DATOS ANTES DE PARCEARLOS

		int id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String contrasena = req.getParameter("contrasena");
		double dineroDisponible = Double.parseDouble(req.getParameter("dinero disponible"));
		int tiempoDisponible = Integer.parseInt(req.getParameter("tiempo disponible"));
		String preferencia = req.getParameter("preferencia");
		String urlPerfil = req.getParameter("url perfil");
		boolean esAdmin = req.getParameter("administrador").equals("Si") ? true : false;

		Usuario usuarioTemp = servUsuario.editar(id, nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (usuarioTemp.esValido()) {
			resp.sendRedirect("/vistas/usuarios.jsp");
		} else {
			req.setAttribute("usuario_temporal", usuarioTemp);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("vistas/usuarios.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
