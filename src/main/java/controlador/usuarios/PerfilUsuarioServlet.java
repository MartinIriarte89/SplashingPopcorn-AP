package controlador.usuarios;

import java.io.IOException;
import java.util.ArrayList;

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

@WebServlet("/perfilUsuario.do")
public class PerfilUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -1815859895935687870L;
	private ServicioGenero servGenero;
	private Validacion validacion;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servGenero = new ServicioGenero();
		this.validacion = new Validacion();
		this.servUsuario = new ServicioUsuario();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = validacion.esNumeroEnteroValido(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		if (usuario.esAdmin()) {
			Usuario usuarioPerfil = servUsuario.buscarPor(id);

			if (!usuarioPerfil.esNulo() && !usuarioPerfil.esAdmin()) {
				req.setAttribute("usuarioPerfil", usuarioPerfil);

			} else {
				req.setAttribute("flash", "Usuario no encontrado");

				RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
				dispatcher.forward(req, resp);
				return;
			}
		}
		ArrayList<Genero> generos = servGenero.listar();
		req.setAttribute("generos", generos);

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/vistas/perfil.jsp");
		dispatcher.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Genero> generos = servGenero.listar();
		req.setAttribute("generos", generos);

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/vistas/perfil.jsp");
		dispatcher.forward(req, resp);
	}
}
