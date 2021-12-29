package controlador.promocion;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import persistencia.FabricaDAO;
import servicios.ServicioComprarPromocion;

@WebServlet("/comprarPromocion.do")
public class ComprarPromocionServlet extends HttpServlet implements Servlet{
	private static final long serialVersionUID = 4039954273142666646L;
	private ServicioComprarPromocion servicioComprarPromo;
	
	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioComprarPromo = new ServicioComprarPromocion();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int promocionId = Integer.parseInt(request.getParameter("id"));
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		Map<String, String> errores = servicioComprarPromo.comprar(usuario.getId(), promocionId);

		Usuario nuevoUsuario = FabricaDAO.getUsuarioDAO().buscarPorId(usuario.getId());
		request.getSession().setAttribute("usuario", nuevoUsuario);

		if (errores.isEmpty()) {
			request.setAttribute("success", "¡Gracias por comprar!");
		} else {
			request.setAttribute("errors", errores);
			request.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
		dispatcher.forward(request, response);
	}
}
