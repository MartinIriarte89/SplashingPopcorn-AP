package controlador.sesion;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Promocion;
import modelo.Usuario;
import servicios.ServicioInicioSesion;

@WebServlet("/inicioSesion")
public class InicioSesionServlet extends HttpServlet {

	private static final long serialVersionUID = -3328951887099112364L;
	private ServicioInicioSesion servInicioSesion;
	ArrayList<Promocion> promociones;

	public void init() throws ServletException {
		super.init();
		servInicioSesion = new ServicioInicioSesion();
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String usuario = req.getParameter("usuario");
		String contrasena = req.getParameter("contrasena");

		Usuario nuevoUsuario = servInicioSesion.registro(usuario, contrasena);

		if (!nuevoUsuario.esNulo()) {
			req.getSession().setAttribute("usuario", nuevoUsuario);
			resp.sendRedirect("inicio");
		} else {
			req.setAttribute("flash", "Nombre de usuario o contraseña incorrectos");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/inicio");
			dispatcher.forward(req, resp);
		}
	}
}
