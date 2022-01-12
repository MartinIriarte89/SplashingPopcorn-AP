package controlador.usuarios;

import java.io.IOException;

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
import utilidades.Validacion;

@WebServlet("/crearUsuario.ad")
public class CrearUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -6626649526922232487L;
	private ServicioUsuario servUsuario;
	private ServicioGenero servGenero;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servUsuario = new ServicioUsuario();
		this.servGenero = new ServicioGenero();
		validarDatos = new Validacion();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String contrasena = validarDatos.esContrasenaValida(req.getParameter("contrasena"));
		String admin = req.getParameter("admin");
		double dineroDisponible = validarDatos.esNumeroDoubleValido(req.getParameter("dinero"));
		int tiempoDisponible = validarDatos.esNumeroEnteroValido(req.getParameter("tiempo"));
		String urlPerfil = req.getParameter("fotoPerfil");
		String preferenciaNombre = req.getParameter("genero");
		boolean esAdmin = false;

		Genero preferencia = servGenero.buscarPor(preferenciaNombre);
		if (admin != null) {
			esAdmin = true;
		}

		Usuario usuarioTemp = servUsuario.crear(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (usuarioTemp.esValido()) {
			resp.sendRedirect("listarUsuarios.ad");
		} else {
			req.setAttribute("usuarioCrear", usuarioTemp);
			req.setAttribute("serv", "crear");
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
