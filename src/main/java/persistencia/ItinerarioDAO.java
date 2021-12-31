package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import modelo.Itinerario;
import modelo.Sugerencia;
import persistencia.commons.ProveedorDeConexion;

public class ItinerarioDAO {

	public HashMap<Integer, Itinerario> cargarItinerarios() {

		String sqlItinerarios = "SELECT * FROM itinerarios";
		String sqlIdPromociones = "SELECT fk_promocion FROM compras_de_itinerarios WHERE fk_itinerario = ? AND fk_promocion IS NOT NULL";
		String sqlIdAtracciones = "SELECT fk_atraccion FROM compras_de_itinerarios WHERE fk_itinerario = ? AND fk_atraccion IS NOT NULL";
		HashMap<Integer, Itinerario> itinerarios = new HashMap<Integer, Itinerario>();

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declarItinerario = conexion.prepareStatement(sqlItinerarios);
			PreparedStatement declarIdPromociones = conexion.prepareStatement(sqlIdPromociones);
			PreparedStatement declarIdAtracciones = conexion.prepareStatement(sqlIdAtracciones);
			ResultSet resultItinerario = declarItinerario.executeQuery();

			while (resultItinerario.next()) {
				int idItinerario = resultItinerario.getInt("id");
				declarIdPromociones.setInt(1, idItinerario);
				declarIdAtracciones.setInt(1, idItinerario);

				ResultSet resultIdPromociones = declarIdPromociones.executeQuery();
				ResultSet resultIdAtracciones = declarIdAtracciones.executeQuery();

				itinerarios.put(idItinerario,
						crearItinerario(resultItinerario, resultIdPromociones, resultIdAtracciones));
			}
			
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return itinerarios;
	}

	public Itinerario buscarPor(int id) {
		String sqlItinerarios = "SELECT * FROM itinerarios WHERE id = ?";
		String sqlIdPromociones = "SELECT fk_promocion FROM compras_de_itinerarios WHERE fk_itinerario = ? AND fk_promocion IS NOT NULL";
		String sqlIdPeliculas = "SELECT fk_pelicula FROM compras_de_itinerarios WHERE fk_itinerario = ? AND fk_pelicula IS NOT NULL";
		Itinerario itinerario;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declarItinerario = conexion.prepareStatement(sqlItinerarios);

			PreparedStatement declarIdPromociones = conexion.prepareStatement(sqlIdPromociones);
			PreparedStatement declarIdAtracciones = conexion.prepareStatement(sqlIdPeliculas);

			declarItinerario.setInt(1, id);
			declarIdPromociones.setInt(1, id);
			declarIdAtracciones.setInt(1, id);

			ResultSet resultItinerario = declarItinerario.executeQuery();
			ResultSet resultIdPromociones = declarIdPromociones.executeQuery();
			ResultSet resultIdAtracciones = declarIdAtracciones.executeQuery();

			itinerario = crearItinerario(resultItinerario, resultIdPromociones, resultIdAtracciones);

		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}

		return itinerario;
	}

	public boolean actualizar(Itinerario itinerario) {
		String sql = "UPDATE itinerarios SET costo = ?, duracion = ? WHERE id = ?";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setInt(1, itinerario.getCostoDelItinerario());
			declaracion.setDouble(2, itinerario.getDuracionDelItinerario());
			declaracion.setInt(3, itinerario.getFkUsuario());

			insertarCompras(itinerario, conexion);
			declaracion.executeUpdate();
			
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return true;
	}

	private void insertarCompras(Itinerario itinerario, Connection conexion) throws Exception {
		
		String sqlPromoComprada = "INSERT INTO compras_de_itinerarios (fk_itinerario, fk_promocion, precio, duracion_min) VALUES (?, ?, ?, ?)";
		String sqlPelicComprada = "INSERT INTO compras_de_itinerarios (fk_itinerario, fk_pelicula, precio, duracion_min) VALUES (?, ?, ?, ?)";

		PreparedStatement declarPromoComprada = conexion.prepareStatement(sqlPromoComprada);
		PreparedStatement declarPelicComprada = conexion.prepareStatement(sqlPelicComprada);
		ArrayList<Sugerencia> sugerenciasCompradas = itinerario.getCompras();
		Sugerencia ultimaCompra = sugerenciasCompradas.get(sugerenciasCompradas.size()-1);

		if (ultimaCompra.esPromocion()) {
			declarPromoComprada.setInt(1, itinerario.getFkUsuario());
			declarPromoComprada.setInt(2, ultimaCompra.getId());
			declarPromoComprada.setDouble(3, ultimaCompra.getPrecio());
			declarPromoComprada.setInt(4, ultimaCompra.getDuracion());
			declarPromoComprada.executeUpdate();
		} else {
			declarPelicComprada.setInt(1, itinerario.getFkUsuario());
			declarPelicComprada.setInt(2, ultimaCompra.getId());
			declarPelicComprada.setDouble(3, ultimaCompra.getPrecio());
			declarPelicComprada.setInt(4, ultimaCompra.getDuracion());
			declarPelicComprada.executeUpdate();
		}
	}

	private Itinerario crearItinerario(ResultSet resultItinerario, ResultSet resultIdPromo, ResultSet resultIdPelicula)
			throws Exception {
		int fkUsuario = resultItinerario.getInt("id");
		int costo = resultItinerario.getInt("costo");
		double duracion = resultItinerario.getDouble("duracion");
		PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
		PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();

		ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>();

		while (resultIdPromo.next()) {
			int idPromocion = resultIdPromo.getInt("fk_promocion");
			compras.add(promocionDAO.buscarPor(idPromocion));
		}
		while (resultIdPelicula.next()) {
			int idPelicula = resultIdPelicula.getInt("fk_pelicula");
			compras.add(peliculaDAO.buscarPor(idPelicula));
		}
		
		return new Itinerario(fkUsuario, compras, costo, duracion);
	}
}
