package servicios;

import java.util.HashMap;

import modelo.Sugerencia;
import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.PeliculaDAO;
import persistencia.UsuarioDAO;

public class ServicioComprar {

	PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
	UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	ServicioTransaccion transaccion = new ServicioTransaccion();

	public HashMap<String, String> comprar(Usuario usuario, Sugerencia sugerencia) {
		HashMap<String, String> errores = new HashMap<String, String>();

		if (!usuario.puedeComprarA(sugerencia)) {
			errores.put("usuario", "Su dinero o su tiempo no es suficiente");
		}

		if (!sugerencia.tieneStock()) {
			errores.put("producto", "No hay stock disponible");
		}

		if (errores.isEmpty()) {
			usuario.comprar(sugerencia);
			sugerencia.restarStock();
			if(!transaccion.actualizarEnBBDD(usuario, sugerencia)) {
				errores.put("fallo", "Algo salió mal al procesar la compra, intenta nuevamente");
			}
		}
		return errores;
	}
}
