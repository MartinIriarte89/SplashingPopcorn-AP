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
import modelo.Promocion;
import modelo.Usuario;
import persistencia.FabricaDAO;
import servicios.ServicioComprar;
import servicios.ServicioPromocion;
import utilidades.Validacion;

@WebServlet("/comprarPromocion.do")
public class ComprarPromocionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 4039954273142666646L;
	private ServicioComprar servicioComprarPromo;
	private ServicioPromocion servPromocion;
	private Validacion validarDatos;

	@Override
	public void init() throws ServletException {
		super.init();
		this.servicioComprarPromo = new ServicioComprar();
		this.servPromocion = new ServicioPromocion();
		this.validarDatos = new Validacion();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = validarDatos.esNumeroEnteroValido(request.getParameter("id"));
		Promocion promocion = servPromocion.buscarPor(id);

		if (!promocion.esNulo()) {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Map<String, String> errores = servicioComprarPromo.comprar(usuario, promocion);

			Usuario nuevoUsuario = FabricaDAO.getUsuarioDAO().buscarPorId(usuario.getId());
			request.getSession().setAttribute("usuario", nuevoUsuario);

			if (errores.isEmpty()) {
				request.setAttribute("success", "¡Gracias por comprar!");
			} else {
				request.setAttribute("error", errores);
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("flash", "La promoción no existe");

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/promociones");
			dispatcher.forward(request, response);
		}
	}
}
