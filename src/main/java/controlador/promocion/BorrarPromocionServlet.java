package controlador.promocion;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import servicios.ServicioPromocion;

@WebServlet("/borrarPromocion.ad")
public class BorrarPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1393783007483630823L;
	private ServicioPromocion servicioPromocion;

	public void init() throws ServletException {
		super.init();
		this.servicioPromocion = new ServicioPromocion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		servicioPromocion.borrar(id);

		response.sendRedirect("promociones");
	}

}
