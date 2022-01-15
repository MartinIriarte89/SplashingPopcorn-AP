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
import servicios.ServicioGenero;

@WebServlet("/perfilUsuario.do")
public class PerfilUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -1815859895935687870L;
	private ServicioGenero servGenero;

	@Override
	public void init() throws ServletException {
		super.init();
		servGenero = new ServicioGenero();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
