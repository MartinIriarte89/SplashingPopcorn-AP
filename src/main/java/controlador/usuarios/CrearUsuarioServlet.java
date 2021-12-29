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

//crear filtro de administrador.
@WebServlet("/crearUsuario.ad")
public class CrearUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -6626649526922232487L;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// VALIDAR LOS DATOS ANTES DE PARCEARLOS

		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String contrasena = req.getParameter("contrasena");
		String admin = req.getParameter("admin");
		boolean esAdmin = false;
		double dineroDisponible = Double.parseDouble(req.getParameter("dinero"));
		int tiempoDisponible = Integer.parseInt(req.getParameter("tiempo"));
		String preferencia = req.getParameter("genero");
		String urlPerfil = req.getParameter("fotoPerfil");
		
		if(admin!=null) {
			esAdmin = true;
		}
		
		Usuario usuarioTemp = servUsuario.crear(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (usuarioTemp.esValido()) {
			resp.sendRedirect("listarUsuarios.ad");
		} else {
			req.setAttribute("usuario_temporal", usuarioTemp);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
