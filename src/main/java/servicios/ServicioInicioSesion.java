package servicios;

import modelo.Usuario;
import modelo.objetoNulo.UsuarioNulo;
import persistencia.FabricaDAO;
import persistencia.UsuarioDAO;

public class ServicioInicioSesion {

	public Usuario registro(String nombreUsuario, String contrasena) {
		UsuarioDAO usuarioDao = FabricaDAO.getUsuarioDAO();
		Usuario usuario = usuarioDao.buscarPorNombre(nombreUsuario);

		if (!usuario.esNulo() && !usuario.checkContrasena(contrasena)) {
			usuario = UsuarioNulo.construir();
		}
		
		return usuario;
	}
}
