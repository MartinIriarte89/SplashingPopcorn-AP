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
import utilidades.Validacion;

//crear filtro de administrador.
@WebServlet("/borrarUsuario.ad")
public class BorrarUsuarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 5819851703771386520L;
	private ServicioUsuario servUsuario;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		servUsuario = new ServicioUsuario();
		validarDatos = new Validacion();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = validarDatos.esNumeroEnteroValido(req.getParameter("id").strip());
		Usuario usuario = servUsuario.eliminar(id);

		if (!usuario.esNulo()) {
			resp.sendRedirect("listarUsuarios.ad");
		} else {
			req.setAttribute("flash", "El usuario no existe");

			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/listarUsuarios.ad");
			dispatcher.forward(req, resp);
		}
	}
}
