package servicios;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Genero;
import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.UsuarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioUsuario {

	UsuarioDAO usuarioDao = FabricaDAO.getUsuarioDAO();
	private HashMap<String, String> errores;

	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> usuarios = usuarioDao.cargar();
		ProveedorDeConexion.cerrarConexion();
		return usuarios;
	}

	public Usuario eliminar(int id) {
		Usuario usuario = usuarioDao.buscarPorId(id);

		if (!usuario.esNulo()) {
			usuarioDao.borrar(usuario);
		}

		ProveedorDeConexion.cerrarConexion();
		return usuario;
	}

	public Usuario crear(String nombre, String usuario, String contrasena, double dineroDisponible,
			int tiempoDisponible, Genero preferencia, String urlPerfil, boolean esAdmin) {
		Usuario nuevoUsuario = new Usuario(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible, preferencia,
				urlPerfil, esAdmin);

		if (nuevoUsuario.esValido()) {
			usuarioDao.insertar(nuevoUsuario);
			ProveedorDeConexion.cerrarConexion();
		}
		return nuevoUsuario;
	}

	public Usuario editarDatosPersonales(int id, String nombre, String usuario, Genero preferencia) {
		Usuario usuarioBBDD = usuarioDao.buscarPorId(id);

		if (!usuarioBBDD.esNulo()) {
			usuarioBBDD.setNombre(nombre);
			usuarioBBDD.setUsuario(usuario);
			usuarioBBDD.setPreferencia(preferencia);

			if (usuarioBBDD.esValido()) {
				usuarioDao.actualizar(usuarioBBDD);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return usuarioBBDD;
	}

	public Usuario editarFotoPerfil(int id, String urlPerfil) {
		Usuario usuarioBBDD = usuarioDao.buscarPorId(id);

		if (!usuarioBBDD.esNulo()) {
			usuarioBBDD.setUrlPerfil(urlPerfil);

			if (usuarioBBDD.esValido()) {
				usuarioDao.actualizar(usuarioBBDD);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return usuarioBBDD;
	}

	public Usuario editarContrasena(int id, String ContrasenaNueva) {
		Usuario usuarioBBDD = usuarioDao.buscarPorId(id);

		if (!usuarioBBDD.esNulo()) {
			usuarioBBDD.setContrasena(ContrasenaNueva);

			if (usuarioBBDD.esValido()) {
				usuarioDao.actualizar(usuarioBBDD);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return usuarioBBDD;

	}

	public Usuario editar(int id, String nombre, String usuario, double dineroDisponible, int tiempoDisponible,
			Genero preferencia, String urlPerfil, boolean esAdmin) {
		Usuario usuarioBBDD = usuarioDao.buscarPorId(id);

		if (!usuarioBBDD.esNulo()) {
			usuarioBBDD.setNombre(nombre);
			usuarioBBDD.setUsuario(usuario);
			usuarioBBDD.setDineroDisponible(dineroDisponible);
			usuarioBBDD.setTiempoDisponible(tiempoDisponible);
			usuarioBBDD.setPreferencia(preferencia);
			usuarioBBDD.setUrlPerfil(urlPerfil);
			usuarioBBDD.setEsAdmin(esAdmin);

			if (usuarioBBDD.esValido()) {
				usuarioDao.actualizar(usuarioBBDD);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return usuarioBBDD;
	}

	public Usuario buscar(int id) {
		Usuario usuario = usuarioDao.buscarPorId(id);
		ProveedorDeConexion.cerrarConexion();
		return usuario;
	}

	public boolean esCambioContrasenaValido(String ContrasenaNueva, String ContrasenaRepetida) {
		validarCambioContrasena(ContrasenaNueva, ContrasenaRepetida);
		return errores.isEmpty();
	}

	private void validarCambioContrasena(String ContrasenaNueva, String ContrasenaRepetida) {
		errores = new HashMap<String, String>();
		if (ContrasenaNueva.equals(ContrasenaRepetida) && ContrasenaNueva.equals("")) {
			errores.put("contrasenaNueva", "No debe contener caracteres especiales, y su longitud debe ser mayor a 4");
		}
		if (!ContrasenaNueva.equals(ContrasenaRepetida)) {
			errores.put("contrasenaRepetida", "No hay coincidencia");
		}
	}

	public HashMap<String, String> getErrores() {
		return this.errores;
	}
}