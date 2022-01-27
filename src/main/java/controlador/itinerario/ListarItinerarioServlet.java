package controlador.itinerario;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import servicios.ServicioItinerario;
import servicios.ServicioUsuario;
import utilidades.Validacion;

@WebServlet("/listarItinerario.do")
public class ListarItinerarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4749530656702019722L;
	private ServicioItinerario servItinerario;
	private ServicioUsuario servUsuario;
	private Validacion validarDatos;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.servItinerario = new ServicioItinerario();
		this.servUsuario = new ServicioUsuario();
		this.validarDatos = new Validacion();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(req.getParameter("id"));
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		
		if(usuario.esAdmin()) {
			Usuario usuarioBuscado = servUsuario.buscarPor(id);
			
			if(!usuarioBuscado.esNulo() && !usuarioBuscado.esAdmin()) {
				req.setAttribute("itinerario", usuarioBuscado.getItinerario());
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/misCompras.jsp");
				dispatcher.forward(req, resp);
			}else {
				req.setAttribute("flash", "Usuario no encontrado");
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listarUsuarios.ad");
				dispatcher.forward(req, resp);
			}
			
		}else {
			req.setAttribute("itinerario", usuario.getItinerario());
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/misCompras.jsp");
			dispatcher.forward(req, resp);
		}
	}
}
