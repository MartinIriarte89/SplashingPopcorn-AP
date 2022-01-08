package controlador.itinerario;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Itinerario;
import modelo.Usuario;
import servicios.ServicioItinerario;
import utilidades.Patron;
import utilidades.Validacion;

@WebServlet("/listarItinerario.do")
public class ListarItinerarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4749530656702019722L;
	private ServicioItinerario servItinerario;
	private Validacion validarDatos;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.servItinerario = new ServicioItinerario();
		this.validarDatos = new Validacion();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = validarDatos.esNumeroEnteroValido(req.getParameter("id"));
		
		//CREAR CLASE ITINERARIO NULO PARA VALIDAR.
		
		if(id != Patron.NUMERO_NO_VALIDO) {
			Itinerario itinerario = servItinerario.buscarPor(id);
			req.getSession().setAttribute("itinerario", itinerario);
			resp.sendRedirect("vistas/misCompras.jsp");
		}else {
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
			req.getSession().setAttribute("itinerario", usuario.getItinerario());
			
			resp.sendRedirect("vistas/misCompras.jsp");
		}
	}
}
