package controlador.usuarios;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.ServicioUsuario;

@WebServlet("/perfilUsuario.do")
public class PerfilUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1815859895935687870L;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("vistas/perfil.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		int id = usuario.getId();
	}

}
