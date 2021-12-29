package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import modelo.Usuario;
import modelo.objetoNulo.UsuarioNulo;
import persistencia.commons.ProveedorDeConexion;

public class UsuarioDAO {

	public boolean insertar(Usuario usuario) {
		String sql = "INSERT INTO usuarios (nombre, usuario, contrasena, administrador, dinero_disp, tiempo_disp, preferencia, url_perfil) VALUES (?,?, ?, ?, ?, ?, ?,?)";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setString(1, usuario.getNombre());
			declaracion.setString(2, usuario.getUsuario());
			declaracion.setString(3, usuario.getContrasena());
			declaracion.setInt(4, usuario.esAdmin() ? 1 : 0);
			declaracion.setDouble(5, usuario.getDineroDisponible());
			declaracion.setInt(6, usuario.getTiempoDisponible());
			declaracion.setString(7, usuario.getPreferencia());
			declaracion.setString(8, usuario.getUrlPerfil());
			declaracion.executeUpdate();

			return true;
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
	}

	public boolean borrar(Usuario usuario) {
		String sql = "UPDATE usuarios SET borrado_logico = 1 WHERE id = ?";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setInt(1, usuario.getId());

			declaracion.executeUpdate();
			return true;
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
	}

	public int actualizar(Usuario usuario) {
		String sql = "UPDATE usuarios SET nombre = ? ,usuario = ?, contrasena = ?, dinero_disp = ?, tiempo_disp = ?, preferencia = ?, url_perfil = ?, administrador = ? WHERE id = ?";
		int filasModificadas = 0;

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setString(1, usuario.getNombre());
			declaracion.setString(2, usuario.getUsuario());
			declaracion.setString(3, usuario.getContrasena());
			declaracion.setDouble(4, usuario.getDineroDisponible());
			declaracion.setInt(5, usuario.getTiempoDisponible());
			declaracion.setString(6, usuario.getPreferencia());
			declaracion.setString(7, usuario.getUrlPerfil());
			declaracion.setInt(8, usuario.esAdmin() ? 1 : 0);
			declaracion.setInt(9, usuario.getId());

			filasModificadas = declaracion.executeUpdate();
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return filasModificadas;
	}

	public Usuario buscarPorNombre(String nombre) {
		String sql = "SELECT * FROM usuarios WHERE usuario = ? AND borrado_logico = 0 ";
		Usuario usuario;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setString(1, nombre);
			ResultSet resultados = declaracion.executeQuery();

			usuario = UsuarioNulo.construir();

			if (resultados.next()) {
				usuario = crearUsuario(resultados);
				ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
				usuario.setItinerario(itinerarioDAO.buscarPor(usuario.getId()));

			}

		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}

		return usuario;
	}

	public Usuario buscarPorId(int id) {
		String sql = "SELECT * FROM usuarios WHERE id = ? AND borrado_logico = 0 ";
		Usuario usuario;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setInt(1, id);
			ResultSet resultados = declaracion.executeQuery();

			usuario = UsuarioNulo.construir();

			if (resultados.next()) {
				usuario = crearUsuario(resultados);
				ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
				usuario.setItinerario(itinerarioDAO.buscarPor(usuario.getId()));
			}

		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}

		return usuario;
	}

	public ArrayList<Usuario> cargar() {
		String sql = "SELECT * FROM usuarios WHERE borrado_logico = 0";

		ArrayList<Usuario> usuarios;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			ResultSet resultados = declaracion.executeQuery();

			usuarios = new ArrayList<Usuario>();

			while (resultados.next()) {
				Usuario usuario = crearUsuario(resultados);
				ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
				usuario.setItinerario(itinerarioDAO.buscarPor(usuario.getId()));
				usuarios.add(usuario);
			}

		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return usuarios;
	}

	private Usuario crearUsuario(ResultSet resultados) throws Exception {
		int id = resultados.getInt("id");
		String nombre = resultados.getString("nombre");
		String usuario = resultados.getString("usuario");
		String contrasena = resultados.getString("contrasena");
		double dinero = resultados.getDouble("dinero_disp");
		int tiempo = resultados.getInt("tiempo_disp");
		String preferencia = resultados.getString("preferencia");
		String urlPerfil = resultados.getString("url_perfil");
		boolean esAdmin = (1 == resultados.getInt("administrador"));

		return new Usuario(id, nombre, usuario, contrasena, dinero, tiempo, preferencia, urlPerfil, esAdmin);
	}

}
