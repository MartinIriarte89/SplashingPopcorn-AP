package servicios;

import java.sql.Connection;
import java.sql.SQLException;

import modelo.Pelicula;
import modelo.Promocion;
import modelo.Sugerencia;
import modelo.Usuario;
import persistencia.DatosPerdidosError;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.PeliculaDAO;
import persistencia.PromocionDAO;
import persistencia.UsuarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioTransaccion {

	PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
	ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
	PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();

	public boolean actualizarEnBBDD(Usuario usuario, Sugerencia compra) {
		Connection conexion = null;
		try {
			conexion = ProveedorDeConexion.getConexion();
			conexion.setAutoCommit(false);

			try {
				usuarioDAO.actualizar(usuario);
				itinerarioDAO.actualizar(usuario.getItinerario());
				if(compra.esPromocion()) {
					Promocion promoCompra = (Promocion)compra;
					promocionDAO.actualizarStock(promoCompra);
				}else {
					peliculaDAO.actualizarStock((Pelicula)compra);
				}
				conexion.commit();

			} catch (DatosPerdidosError e) {
				conexion.rollback();
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				conexion.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
