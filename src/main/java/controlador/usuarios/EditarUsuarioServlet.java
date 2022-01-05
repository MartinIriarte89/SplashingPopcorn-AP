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

@WebServlet("/editarUsuario.do")
public class EditarUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 750290554834772235L;
	private ServicioUsuario servUsuario;
	private ValidacionDatosUsuario validarDatos;
	private ServicioGenero servGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
		validarDatos = new ValidacionDatosUsuario();
		servGenero = new ServicioGenero();
	}

	//CREO QUE ESTE METODO SE VA A PODER ELIMINAR
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if (!validarDatos.esNumeroValido(id)) {
			req.setAttribute("flash", "formato de id invalido");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
			return;
		}

		int idUsuario = Integer.parseInt(id);
		Usuario usuario = servUsuario.buscar(idUsuario);

		if (!usuario.esNulo()) {
			req.setAttribute("usuario", usuario);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios.jsp");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("flash", "El usuario no existe");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);

		Usuario user = (Usuario) req.getSession().getAttribute("usuario");

		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String contrasena = req.getParameter("contrasena");
		String urlPerfil = req.getParameter("url perfil");
		String preferenciaNombre = req.getParameter("preferencia");
		boolean esAdmin = false;
		int id = user.getId();
		double dineroDisponible = user.getDineroDisponible();
		int tiempoDisponible = user.getTiempoDisponible();

		Genero preferencia = servGenero.buscarPor(preferenciaNombre);

		// Para que un usuario comun no pueda vulnerar el html y editar valores que no
		// tiene permitido se verifica si es admin o no.
		if (user.esAdmin()) {
			String idString = req.getParameter("id");
			String dineroDisponibleString = req.getParameter("dinero disponible");
			String tiempoDisponibleString = req.getParameter("tiempo disponible");

			if (!validarDatos.datosSonValidos(idString, nombre, usuario, contrasena, dineroDisponibleString,
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

			id = Integer.parseInt(idString);
			dineroDisponible = Double.parseDouble(dineroDisponibleString);
			tiempoDisponible = Integer.parseInt(tiempoDisponibleString);
			esAdmin = req.getParameter("administrador").equals("Si") ? true : false;

		} else {

			if (!validarDatos.datosSonValidos(nombre, usuario, contrasena)) {
				Map<String, String> errores = validarDatos.getErrores();
				req.setAttribute("errores", errores);

				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("");
				dispatcher.forward(req, resp);
				return;
			}

			if (preferencia.esNulo()) {
				req.setAttribute("flash", "El genero no existe");

				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("");
				dispatcher.forward(req, resp);
				return;
			}
		}

		Usuario usuarioTemp = servUsuario.editar(id, nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (usuarioTemp.esValido()) {
			resp.sendRedirect("/vistas/usuarios.jsp");
		} else {
			req.setAttribute("errores", usuarioTemp.getErrors());

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("vistas/usuarios.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
