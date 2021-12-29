package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Pelicula;
import modelo.Promocion;
import modelo.PromocionAPorB;
import modelo.PromocionAbsoluta;
import modelo.PromocionPorcentual;
import modelo.objetoNulo.PromocionNula;
import persistencia.commons.ProveedorDeConexion;

public class PromocionDAO {

	public ArrayList<Promocion> cargar() {
		String sqlDatosPromocion = "SELECT * FROM promociones WHERE borrado_logico = 0";
		String sqlPelisEnPromocion = "SELECT fk_pelicula AS 'id pelicula' FROM peliculas_en_promocion WHERE fk_promocion = ?";
		ArrayList<Promocion> promociones = new ArrayList<Promocion>();

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declarDatosProm = conexion.prepareStatement(sqlDatosPromocion);

			PreparedStatement declarPelicEnProm = conexion.prepareStatement(sqlPelisEnPromocion);

			ResultSet resultadosPromociones = declarDatosProm.executeQuery();

			while (resultadosPromociones.next()) {
				declarPelicEnProm.setInt(1, resultadosPromociones.getInt("id"));
				ResultSet resultadosIdPelic = declarPelicEnProm.executeQuery();
				Promocion promocion = crearPromocion(resultadosPromociones, resultadosIdPelic);
				if(!promocion.esNulo()) {
					promociones.add(promocion);
				}
			}
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return promociones;
	}

	public boolean actualizarStock(Promocion promocion) {
		PeliculaDAO peliculaDAO = new PeliculaDAO();
		ArrayList<Pelicula> peliculas = promocion.getPeliculas();

		for (Pelicula pelicula : peliculas) {
			peliculaDAO.actualizarStock(pelicula);
		}
		return true;
	}

	public boolean editar(Promocion promocion) {
		String sqlPromocion = "UPDATE promociones SET titulo = ?, descripcion = ?, foto_portada = ?, WHERE id = ?";
		String sqlBorrarPeliculas = "DELETE FROM peliculas_en_promocion WHERE fk_promocion = ?";
		String sqlInsertarPeliculas = "INSERT INTO peliculas_en_promocion (fk_promocion, fk_pelicula) VALUES (?, ?)";

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declarPromo = conexion.prepareStatement(sqlPromocion);
			PreparedStatement declarBorrarPelic = conexion.prepareStatement(sqlBorrarPeliculas);
			PreparedStatement declarInsertarPelic = conexion.prepareStatement(sqlInsertarPeliculas);

			// Se borran peliculas de Peliculas en promocion
			declarBorrarPelic.setInt(1, promocion.getId());
			declarBorrarPelic.executeUpdate();

			// Se insterta peliculas en Peliculas en promocion
			ArrayList<Pelicula> peliculas = promocion.getPeliculas();

			for (Pelicula pelicula : peliculas) {
				declarInsertarPelic.setInt(1, promocion.getId());
				declarInsertarPelic.setInt(2, pelicula.getId());
			}

			// Por ultimo se actualiza los datos de Promocion
			declarPromo.setString(1, promocion.getTitulo());
			declarPromo.setString(2, promocion.getDescripcion());
			declarPromo.setString(3, promocion.getUrlPortada());
			declarPromo.setInt(4, promocion.getId());
			declarPromo.executeUpdate();

		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
		return true;
	}

	public boolean borrar(Promocion promocion) {
		String sql = "UPDATE promociones SET borrado_logico = 1 WHERE id = ?";

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setInt(1, promocion.getId());
			declaracion.executeUpdate();

			return true;
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}

	public Promocion buscarPor(int id) {
		Promocion promocion;
		String sql = "SELECT * FROM promociones WHERE id = ?";
		String idPeliculas = "SELECT fk_pelicula AS 'id pelicula' FROM peliculas_en_promocion WHERE fk_promocion = ?";
		try {

			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			PreparedStatement declarIdPeliculas = conexion.prepareStatement(idPeliculas);

			declaracion.setInt(1, id);
			declarIdPeliculas.setInt(1, id);

			ResultSet resultados = declaracion.executeQuery();
			ResultSet resultIdPeliculas = declarIdPeliculas.executeQuery();

			promocion = PromocionNula.construir();

			if (resultados.next()) {
				promocion = crearPromocion(resultados, resultIdPeliculas);
			}

		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return promocion;
	}

	private Promocion crearPromocion(ResultSet resultadosProm, ResultSet resultadosIdPelic) throws Exception {
		PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();

		String tipoPromocion = resultadosProm.getString("tipo_promocion");
		int id = resultadosProm.getInt("id");
		String nombre = resultadosProm.getString("nombre");
		int descuento = resultadosProm.getInt("descuento");
		String descripcion = resultadosProm.getString("descripcion");
		String urlPortada = resultadosProm.getString("foto_portada");
		ArrayList<Pelicula> peliculasDeProm = new ArrayList<Pelicula>();

		while (resultadosIdPelic.next()) {
			Pelicula pelicula = peliculaDAO.buscarPor(resultadosIdPelic.getInt("id pelicula"));
			if (!pelicula.esNulo()) {
				peliculasDeProm.add(pelicula);
			}
		}

		if (!peliculasDeProm.isEmpty()) {
			if (tipoPromocion.equalsIgnoreCase("Porcentual"))
				return new PromocionPorcentual(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada);
			else if (tipoPromocion.equalsIgnoreCase("Absoluta"))
				return new PromocionAbsoluta(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada);
			else if (tipoPromocion.equalsIgnoreCase("AxB"))
				return new PromocionAPorB(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada);
			else
				throw new Error();
		} else
			return PromocionNula.construir();
	}
}
