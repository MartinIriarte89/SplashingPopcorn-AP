package controlador.sesion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Genero;
import modelo.Usuario;
import servicios.ServicioGenero;
import servicios.ServicioUsuario;
import utilidades.Validacion;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 2441232102012928593L;
	private ServicioGenero servGenero;
	private Validacion validarDatos;
	private ServicioUsuario servUsuario;

	@Override
	public void init() throws ServletException {
		super.init();
		servGenero = new ServicioGenero();
		validarDatos = new Validacion();
		servUsuario = new ServicioUsuario();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Genero> generos = servGenero.listar();
		req.setAttribute("generos", generos);

		RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/vistas/registro.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		String usuario = req.getParameter("usuario");
		String preferenciaNombre = req.getParameter("genero");
		String contrasena = validarDatos.esContrasenaValida(req.getParameter("contrasena"));
		String contrasenaRepetida = validarDatos.esContrasenaValida(req.getParameter("contrasenaRepetida"));
		String fotoPerfilDefecto = "imagenes/fotoPerfilDefecto.jpg";

		Genero preferencia = servGenero.buscarPor(preferenciaNombre);
		
		ArrayList<Genero> generos = servGenero.listar();

		if (!servUsuario.esCambioContrasenaValido(contrasena, contrasenaRepetida)) {
			HashMap<String, String> erroresContrasena = servUsuario.getErrores();
			req.setAttribute("erroresContrasena", erroresContrasena);
			req.setAttribute("generos", generos);
			req.setAttribute("datosPrecargados", getDatosPrecargados(nombre, usuario));
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/vistas/registro.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
		if(servUsuario.esUsuarioExistente(usuario)) {
			req.setAttribute("usuarioExistente", "El usuario ya existe, intente con uno distinto");
			req.setAttribute("generos", generos);
			req.setAttribute("datosPrecargados", getDatosPrecargados(nombre, usuario));
			
			RequestDispatcher dispatcher = req.getServletContext().getRequestDispatcher("/vistas/registro.jsp");
			dispatcher.forward(req, resp);
			return;
		}
		
		Usuario usuarioTemp = servUsuario.crear(nombre, usuario, contrasena, 1000, 1000, preferencia, fotoPerfilDefecto , false);

		if (usuarioTemp.esValido()) {
			req.setAttribute("success", "¡Cuenta creada correctamente! se te obsequió 1000 pesos");

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/inicio");
			dispatcher.forward(req, resp);
		} else {
			req.setAttribute("usuarioTemp", usuarioTemp);
			req.setAttribute("generos", generos);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/vistas/registro.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	private HashMap<String, String> getDatosPrecargados(String nombre, String usuario){
		HashMap<String, String> datosPrecargados = new HashMap<String, String>();
		datosPrecargados.put("nombre", nombre);
		datosPrecargados.put("usuario", usuario);
		return datosPrecargados;
	}
}
