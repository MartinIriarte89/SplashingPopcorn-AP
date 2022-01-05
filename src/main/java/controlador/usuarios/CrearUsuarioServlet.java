package controlador.usuarios;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import modelo.Usuario;
import servicios.ServicioGenero;
import servicios.ServicioUsuario;
import servicios.validaciones.ValidacionDatosUsuario;

@WebServlet("/crearUsuario.ad")
public class CrearUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -6626649526922232487L;
	private ServicioUsuario servUsuario;
	private ServicioGenero servGenero;
	private ValidacionDatosUsuario validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servUsuario = new ServicioUsuario();
		this.servGenero = new ServicioGenero();
		validarDatos = new ValidacionDatosUsuario();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String contrasena = req.getParameter("contrasena");
		String admin = req.getParameter("admin");
		String dineroDisponibleString = req.getParameter("dinero");
		String tiempoDisponibleString = req.getParameter("tiempo");
		String urlPerfil = req.getParameter("fotoPerfil");
		String preferenciaNombre = req.getParameter("genero");

		Genero preferencia = servGenero.buscarPor(preferenciaNombre);

		if (!validarDatos.datosSonValidos(nombre, usuario, contrasena, dineroDisponibleString,
				tiempoDisponibleString)) {
			Map<String, String> errores = validarDatos.getErrores();
			req.setAttribute("errores", errores);

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
			return;
		}

		if (preferencia.esNulo()) {
			req.setAttribute("flash", "El genero no existe");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
			return;
		}

		double dineroDisponible = Double.parseDouble(dineroDisponibleString);
		int tiempoDisponible = Integer.parseInt(tiempoDisponibleString);
		boolean esAdmin = false;
		if (admin != null) {
			esAdmin = true;
		}

		Usuario usuarioTemp = servUsuario.crear(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (usuarioTemp.esValido()) {
			resp.sendRedirect("listarUsuarios.ad");
		} else {
			req.setAttribute("errores", usuarioTemp.getErrors());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
