package controlador.inicio;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Promocion;
import modelo.Usuario;
import servicios.ServicioFiltrarSugerencia;
import servicios.ServicioPromocion;

@WebServlet("/inicio")
public class Inicio extends HttpServlet implements Servlet {

	private static final long serialVersionUID = -4824357444673542251L;
	private ServicioPromocion servPromocion;
	private ServicioFiltrarSugerencia servFiltro;
	ArrayList<Promocion> promociones;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servPromocion = new ServicioPromocion();
		this.servFiltro = new ServicioFiltrarSugerencia();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");

		if (usuario == null)
			promociones = servPromocion.listar();
		else {
			req.getSession().removeAttribute("promociones");
			promociones = servFiltro.promosFiltradas(usuario);
		}
		req.getSession().setAttribute("promociones", promociones);
		RequestDispatcher disparcher = req.getRequestDispatcher("index.jsp");
		disparcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
		
		if (usuario == null)
			promociones = servPromocion.listar();
		else {
			req.getSession().removeAttribute("promociones");
			promociones = servFiltro.promosFiltradas(usuario);
		}
		req.getSession().setAttribute("promociones", promociones);
		RequestDispatcher disparcher = req.getRequestDispatcher("index.jsp");
		disparcher.forward(req, resp);
	}
}
