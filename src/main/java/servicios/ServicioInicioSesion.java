package servicios;

import modelo.Usuario;
import modelo.objetoNulo.UsuarioNulo;
import persistencia.FabricaDAO;
import persistencia.UsuarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioInicioSesion {

	public Usuario registro(String nombreUsuario, String contrasena) {
		UsuarioDAO usuarioDao = FabricaDAO.getUsuarioDAO();
		Usuario usuario = usuarioDao.buscarPorNombre(nombreUsuario);
		ProveedorDeConexion.cerrarConexion();
		
		if (!usuario.esNulo() && !usuario.checkContrasena(contrasena)) {
			usuario = UsuarioNulo.construir();
		}
		
		return usuario;
	}
}
