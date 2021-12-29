package servicios;

import java.util.HashMap;

import modelo.Promocion;
import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.PromocionDAO;
import persistencia.UsuarioDAO;

public class ServicioComprarPromocion {

	PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();
	UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	// ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	ServicioTransaccion transaccion = new ServicioTransaccion();

	public HashMap<String, String> comprar(int idUsuario, int idPromocion) {
		HashMap<String, String> errores = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
		Promocion promocion = promocionDAO.buscarPor(idPromocion);

		if (!usuario.puedeComprarA(promocion)) {
			errores.put("usuario", "Su dinero o tiempo no es suficiente");
		}

		if (!promocion.tieneStock()) {
			errores.put("promocion", "No hay stock disponible");
		}

		if (errores.isEmpty()) {
			usuario.comprar(promocion);
			promocion.restarStock();
			
			
			
			
			transaccion.actualizarEnBBDD(usuario, promocion);

		}
		return errores;
	}

}
