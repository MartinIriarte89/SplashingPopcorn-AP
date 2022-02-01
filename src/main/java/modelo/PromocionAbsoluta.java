package modelo;

import java.util.ArrayList;
import java.util.Map;

public class PromocionAbsoluta extends Promocion {

	private double precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Pelicula> peliculas, double precioDePaquete, String descripcion,
			String urlPortada, String tipoPromocion) {
		super(nombre, peliculas, descripcion, urlPortada, precioDePaquete, tipoPromocion);
		this.precioDePaquete = precioDePaquete;
	}

	public PromocionAbsoluta(int id, String nombre, ArrayList<Pelicula> peliculas, double precioDePaquete,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(id, nombre, peliculas, descripcion, urlPortada, precioDePaquete, tipoPromocion);
		this.precioDePaquete = precioDePaquete;
	}

	@Override
	public double getPrecio() {
		return this.precioDePaquete;
	}

	@Override
	public void validar() {
		super.validar();
		Map <String, String> errores = super.getErrores();
		
		if (super.getBeneficio() < 0) {
			errores.put("beneficio", "El precio debe ser un número positivo");
		}
		
		super.setErrores(errores);
	}
}