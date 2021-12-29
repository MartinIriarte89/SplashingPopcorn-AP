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

@WebServlet("/listarItinerario.do")
public class ListarItinerarioServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4749530656702019722L;
	private ServicioItinerario servItinerario;
	@Override
	public void init() throws ServletException {
		super.init();
		servItinerario = new ServicioItinerario();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		
		if(id != null) {
			Itinerario itinerario = servItinerario.buscarPor(Integer.parseInt(id));
			req.getSession().setAttribute("itinerario", itinerario);
			resp.sendRedirect("vistas/misCompras.jsp");
		}else {
			Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
			req.getSession().setAttribute("itinerario", usuario.getItinerario());
			
			resp.sendRedirect("vistas/misCompras.jsp");
		}
	}
}
