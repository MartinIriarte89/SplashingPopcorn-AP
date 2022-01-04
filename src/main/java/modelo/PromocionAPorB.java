package modelo;

import java.util.ArrayList;
import java.util.Collections;

public class PromocionAPorB extends Promocion {

	private ArrayList<Pelicula> atraccionesACobrar = new ArrayList<Pelicula>();

	public PromocionAPorB(String nombre, ArrayList<Pelicula> atracciones, int cantPromosACobrar, String descripcion,
			String urlPortada, String tipoPromocion) {
		super(nombre, atracciones, descripcion, urlPortada, cantPromosACobrar, tipoPromocion);
		Collections.sort(atracciones);

		for (int i = 0; i < cantPromosACobrar; i++) {
			atraccionesACobrar.add(atracciones.get(i));
		}
	}

	public PromocionAPorB(int id, String nombre, ArrayList<Pelicula> atracciones, int cantPromosACobrar,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(id, nombre, atracciones, descripcion, urlPortada, cantPromosACobrar, tipoPromocion);
		Collections.sort(atracciones);

		for (int i = 0; i < cantPromosACobrar; i++) {
			atraccionesACobrar.add(atracciones.get(i));
		}
	}

	@Override
	public double getPrecio() {
		int importeACobrar = 0;
		for (Pelicula atraccion : atraccionesACobrar) {
			importeACobrar += atraccion.getPrecio();
		}
		return importeACobrar;
	}
}
