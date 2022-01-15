package controlador.usuarios;

import java.io.IOException;
import java.util.HashMap;

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

@WebServlet("/editarUsuario.do")
public class EditarUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 750290554834772235L;
	private ServicioUsuario servUsuario;
	private Validacion validarDatos;
	private ServicioGenero servGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
		validarDatos = new Validacion();
		servGenero = new ServicioGenero();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Usuario user = (Usuario) req.getSession().getAttribute("usuario");
		Usuario usuarioTemp;
		String ruta;
		if (user.esAdmin()) {
			ruta = "/listarUsuarios.ad";
			int id = validarDatos.esNumeroEnteroValido(req.getParameter("id"));
			String nombre = req.getParameter("nombre");
			String usuario = req.getParameter("usuario");
			String urlPerfil = req.getParameter("url perfil");
			String preferenciaNombre = req.getParameter("genero");
			double dineroDisponible = validarDatos.esNumeroDoubleValido(req.getParameter("dinero"));
			int tiempoDisponible = validarDatos.esNumeroEnteroValido(req.getParameter("tiempo"));
			boolean esAdmin = req.getParameter("admin").equals("admin") ? true : false;

			Genero preferencia = servGenero.buscarPor(preferenciaNombre);

			usuarioTemp = servUsuario.editar(id, nombre, usuario, dineroDisponible, tiempoDisponible, preferencia,
					urlPerfil, esAdmin);
		} else {
			ruta = "/perfilUsuario.do";
			int id = user.getId();
			String nombre = req.getParameter("nombre");
			String usuario = req.getParameter("usuario");
			String preferenciaNombre = req.getParameter("genero");
			String urlPerfil = req.getParameter("url perfil");
			String contrasenaActual = validarDatos.esContrasenaValida(req.getParameter("contrasenaActual"));
			String contrasenaNueva = validarDatos.esContrasenaValida(req.getParameter("contrasenaNueva"));
			String contrasenaRepetida = validarDatos.esContrasenaValida(req.getParameter("contrasenaRepetida"));
			Genero preferencia = servGenero.buscarPor(preferenciaNombre);

			if (!contrasenaActual.equals("")) {
				if (!user.checkContrasena(contrasenaActual)) {
					req.setAttribute("contrasenaActual", "Contraseña incorrecta");

					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/perfilUsuario.do");
					dispatcher.forward(req, resp);
					return;
				}
				if (!servUsuario.esCambioContrasenaValido(contrasenaNueva, contrasenaRepetida)) {
					HashMap<String, String> erroresContrasena = servUsuario.getErrores();
					req.setAttribute("erroresContrasena", erroresContrasena);

					RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/perfilUsuario.do");
					dispatcher.forward(req, resp);
					return;
				}

				usuarioTemp = servUsuario.editarContrasena(id, contrasenaNueva);

			} else if (urlPerfil != null) {
				usuarioTemp = servUsuario.editarFotoPerfil(id, urlPerfil);
			} else {
				usuarioTemp = servUsuario.editarDatosPersonales(id, nombre, usuario, preferencia);
			}
		}

		if (!usuarioTemp.esNulo()) {
			if (usuarioTemp.esValido()) {
				req.setAttribute("success", "¡Usuario editado correctamente!");
				if (!user.esAdmin())
					req.getSession().setAttribute("usuario", usuarioTemp);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ruta);
				dispatcher.forward(req, resp);
			} else {
				req.setAttribute("usuarioEditar", usuarioTemp);
				req.setAttribute("serv", "editar");

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ruta);
				dispatcher.forward(req, resp);
			}
		} else {
			req.setAttribute("flash", "El usuario no existe");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher(ruta);
			dispatcher.forward(req, resp);
		}
	}
}
