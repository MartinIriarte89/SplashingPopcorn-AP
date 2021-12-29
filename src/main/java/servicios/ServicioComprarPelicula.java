package servicios;

import java.util.HashMap;

import modelo.Pelicula;
import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.PeliculaDAO;
import persistencia.UsuarioDAO;

public class ServicioComprarPelicula {

	PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
	UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
	ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	ServicioTransaccion transaccion = new ServicioTransaccion();

	public HashMap<String, String> comprar(int idUsuario, int idPelicula) {
		HashMap<String, String> errores = new HashMap<String, String>();

		Usuario usuario = usuarioDAO.buscarPorId(idUsuario);
		Pelicula pelicula = peliculaDAO.buscarPor(idPelicula);

		if (!usuario.puedeComprarA(pelicula)) {
			errores.put("usuario", "Su dinero o tiempo no es suficiente");
		}

		if (!pelicula.tieneStock()) {
			errores.put("pelicula", "No hay stock disponible");
		}

		if (errores.isEmpty()) {
			usuario.comprar(pelicula);
			pelicula.restarStock();

			transaccion.actualizarEnBBDD(usuario, pelicula);

		}
		return errores;
	}
}
