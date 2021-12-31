package servicios;

import modelo.Itinerario;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioItinerario {

	ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
	
	public Itinerario buscarPor(int id) {
		Itinerario itinerario = itinerarioDAO.buscarPor(id);
		ProveedorDeConexion.cerrarConexion();
		return itinerario;
	}
}
