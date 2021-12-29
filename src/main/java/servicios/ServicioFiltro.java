package servicios;

import java.util.ArrayList;

import modelo.Promocion;
import modelo.Usuario;

public class ServicioFiltro {

	ServicioPromocion servicioPromo = new ServicioPromocion();
	
	public ArrayList<Promocion> promosFiltradas(Usuario usuario) {
		ArrayList<Promocion> promociones =  servicioPromo.listar();
		ArrayList<Promocion> promocionesFiltradas = new ArrayList<Promocion>(promociones);
		
		for (Promocion promocion : promociones) {
			if(!usuario.puedeComprarA(promocion) || !promocion.tieneStock()) {
				promocionesFiltradas.remove(promocion);
			}
		}
		promocionesFiltradas.sort(new ServicioOrdenarSugerencias(usuario.getPreferencia()));
		
		return promocionesFiltradas;
	} 
}
