package controlador.promocion;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Promocion;
import servicios.ServicioPromocion;
import utilidades.Validacion;

@WebServlet("/borrarPromocion.ad")
public class BorrarPromocionServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -1393783007483630823L;
	private ServicioPromocion servicioPromocion;
	private Validacion validarDatos;

	public void init() throws ServletException {
		super.init();
		this.servicioPromocion = new ServicioPromocion();
		this.validarDatos = new Validacion();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));
		Promocion promocion = servicioPromocion.borrar(id);

		if (!promocion.esNulo()) {
			response.sendRedirect("promociones");
		} else {
			request.setAttribute("flash", "La promoción no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		}
	}
}
