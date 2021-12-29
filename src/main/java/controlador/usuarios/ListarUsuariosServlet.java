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

@WebServlet("/listarUsuarios.ad")
public class ListarUsuariosServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -8000847468930284586L;
	private ServicioUsuario servUsuario;
	private ServicioGenero servGenero;
	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
		servGenero = new ServicioGenero();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Usuario> usuarios = servUsuario.listar();
		ArrayList<Genero> generos = servGenero.listar();
		req.setAttribute("usuarios", usuarios);
		req.setAttribute("generos", generos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Usuario> usuarios = servUsuario.listar();
		ArrayList<Genero> generos = servGenero.listar();
		req.setAttribute("usuarios", usuarios);
		req.setAttribute("generos", generos);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/usuarios.jsp");
		dispatcher.forward(req, resp);
	}
}
