package servicios;

import java.util.ArrayList;

import modelo.Promocion;
import modelo.Usuario;

public class ServicioFiltrarSugerencia {

	ServicioPromocion servicioPromo = new ServicioPromocion();

	public ArrayList<Promocion> promosFiltradas(Usuario usuario) {
		ArrayList<Promocion> promocionesFiltradas = new ArrayList<Promocion>(servicioPromo.listar());

		for (Promocion promocion : promocionesFiltradas) {
			if (!usuario.puedeComprarA(promocion) || !promocion.tieneStock()) {
				promocionesFiltradas.remove(promocion);
			}
		}
		promocionesFiltradas.sort(new ServicioOrdenarSugerencias(usuario.getPreferencia()));

		return promocionesFiltradas;
	}
}
