package servicios;

import java.util.HashMap;

import modelo.Usuario;
import modelo.objetoNulo.UsuarioNulo;
import persistencia.FabricaDAO;
import persistencia.UsuarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioInicioSesion {

	HashMap<String, String> errores;

	public Usuario registro(String nombreUsuario, String contrasena) {
		UsuarioDAO usuarioDao = FabricaDAO.getUsuarioDAO();
		Usuario usuario = usuarioDao.buscarPorNombre(nombreUsuario);
		ProveedorDeConexion.cerrarConexion();

		errores = new HashMap<String, String>();

		if (usuario.esNulo()) {
			errores.put("usuario", "Usuario incorrecto");
		}
		if (!usuario.esNulo() && !usuario.checkContrasena(contrasena)) {
			usuario = UsuarioNulo.construir();
			errores.put("contrasena", "Contraseña incorrecta");
		}

		return usuario;
	}

	public HashMap<String, String> getErrores() {
		if (errores == null) {
			errores = new HashMap<String, String>();
		}
		return errores;
	}
}
