package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class PromocionAPorB extends Promocion {

	private ArrayList<Pelicula> atraccionesACobrar = new ArrayList<Pelicula>();

	public PromocionAPorB(String nombre, ArrayList<Pelicula> peliculas, int cantPromosACobrar, String descripcion,
			String urlPortada, String tipoPromocion) {
		super(nombre, peliculas, descripcion, urlPortada, cantPromosACobrar, tipoPromocion);
		Collections.sort(peliculas);

		if (cantPromosACobrar <= peliculas.size()) {
			for (int i = 0; i < cantPromosACobrar; i++) {
				atraccionesACobrar.add(peliculas.get(i));
			}
		} else {
			for (Pelicula pelicula : peliculas) {
				atraccionesACobrar.add(pelicula);
			}
		}
	}

	public PromocionAPorB(int id, String nombre, ArrayList<Pelicula> peliculas, int cantPromosACobrar,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(id, nombre, peliculas, descripcion, urlPortada, cantPromosACobrar, tipoPromocion);
		Collections.sort(peliculas);

		for (int i = 0; i < cantPromosACobrar; i++) {
			atraccionesACobrar.add(peliculas.get(i));
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

	@Override
	public void validar() {
		super.validar();
		Map<String, String> errores = super.getErrores();

		if (super.getPeliculas().size() < super.getBeneficio()) {
			errores.put("beneficio", "Las películas a regalar no pueden superar la cantidad de películas en promoción");
		}
		super.setErrores(errores);
	}
}
