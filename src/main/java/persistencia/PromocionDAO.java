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
				if (!promocion.esNulo()) {
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

	public boolean insertar(Promocion promocion) {
		String sqlPromocion = "INSERT INTO promociones (tipo_promocion, nombre, descuento, descripcion, foto_portada) VALUES (?, ?, ?, ?, ?)";
		String sqlInsertarPeliculas = "INSERT INTO peliculas_en_promocion (fk_promocion, fk_pelicula) VALUES (?, ?)";
		String sqlUltimoId = "SELECT max(id) as 'id' FROM promociones";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declarPromo = conexion.prepareStatement(sqlPromocion);
			PreparedStatement declarInsertarPelic = conexion.prepareStatement(sqlInsertarPeliculas);
			PreparedStatement declarUltimoId = conexion.prepareStatement(sqlUltimoId);

			declarPromo.setString(1, promocion.getTipoPromocion());
			declarPromo.setString(2, promocion.getTitulo());

			switch (promocion.getTipoPromocion()) {
			case "porcentual":
				declarPromo.setInt(3, (int) promocion.getBeneficio());
				break;
			case "absoluta":
				declarPromo.setDouble(3, promocion.getBeneficio());
				break;
			case "axb":
				declarPromo.setInt(3, (int) promocion.getBeneficio());
				break;
			}
			declarPromo.setString(4, promocion.getDescripcion());
			declarPromo.setString(5, promocion.getUrlPortada());

			declarPromo.executeUpdate();

			ResultSet ultimoId = declarUltimoId.executeQuery();
			ultimoId.next();

			// Se insterta peliculas en Peliculas en promocion
			ArrayList<Pelicula> peliculas = promocion.getPeliculas();

			for (Pelicula pelicula : peliculas) {
				declarInsertarPelic.setInt(1, ultimoId.getInt("id"));
				declarInsertarPelic.setInt(2, pelicula.getId());
				declarInsertarPelic.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}

	public boolean editar(Promocion promocion) {
		String sqlPromocion = "UPDATE promociones SET tipo_promocion = ?, nombre = ?, descuento = ?, descripcion = ?, foto_portada = ? WHERE id = ?";
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
				declarInsertarPelic.executeUpdate();
			}

			// Por ultimo se actualiza los datos de Promocion
			declarPromo.setString(1, promocion.getTipoPromocion());
			declarPromo.setString(2, promocion.getTitulo());
			switch (promocion.getTipoPromocion()) {
			case "porcentual":
				declarPromo.setInt(3, (int) promocion.getBeneficio());
				break;
			case "absoluta":
				declarPromo.setDouble(3, promocion.getBeneficio());
				break;
			case "axb":
				declarPromo.setInt(3, (int) promocion.getBeneficio());
				break;
			}
			declarPromo.setString(4, promocion.getDescripcion());
			declarPromo.setString(5, promocion.getUrlPortada());
			declarPromo.setInt(6, promocion.getId());

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

	public Promocion buscarSinEliminadasPor(int id) {
		Promocion promocion;
		String sql = "SELECT * FROM promociones WHERE id = ? and borrado_logico = 0";
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
	
	public Promocion buscarConEliminadasPor(int id) {
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
			Pelicula pelicula = peliculaDAO.buscarSinEliminadasPor(resultadosIdPelic.getInt("id pelicula"));
			if (!pelicula.esNulo()) {
				peliculasDeProm.add(pelicula);
			}
		}

		if (!peliculasDeProm.isEmpty()) {
			if (tipoPromocion.equalsIgnoreCase("Porcentual"))
				return new PromocionPorcentual(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada,
						tipoPromocion);
			else if (tipoPromocion.equalsIgnoreCase("Absoluta"))
				return new PromocionAbsoluta(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada,
						tipoPromocion);
			else if (tipoPromocion.equalsIgnoreCase("AxB"))
				return new PromocionAPorB(id, nombre, peliculasDeProm, descuento, descripcion, urlPortada,
						tipoPromocion);
			else
				throw new Error();
		} else
			return PromocionNula.construir();
	}
}
