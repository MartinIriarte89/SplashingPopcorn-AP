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

	// CREO QUE ESTE METODO SE VA A PODER ELIMINAR
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(req.getParameter("id"));
		Usuario usuario = servUsuario.buscar(id);

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
		String contrasena = validarDatos.esContrasenaValida(req.getParameter("contrasena"));
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
			id = validarDatos.esNumeroEnteroValido(req.getParameter("id"));
			dineroDisponible = validarDatos.esNumeroDoubleValido(req.getParameter("dinero disponible"));
			tiempoDisponible = validarDatos.esNumeroEnteroValido(req.getParameter("tiempo disponible"));
			esAdmin = req.getParameter("administrador").equals("Si") ? true : false;
		}

		Usuario usuarioTemp = servUsuario.editar(id, nombre, usuario, contrasena, dineroDisponible, tiempoDisponible,
				preferencia, urlPerfil, esAdmin);

		if (!usuarioTemp.esNulo()) {
			if (usuarioTemp.esValido()) {
				resp.sendRedirect("/vistas/usuarios.jsp");
			} else {
				req.setAttribute("usuarioTemp", usuarioTemp);

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("vistas/usuarios.jsp");
				dispatcher.forward(req, resp);
			}
		} else {
			req.setAttribute("flash", "El usuario no existe");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
