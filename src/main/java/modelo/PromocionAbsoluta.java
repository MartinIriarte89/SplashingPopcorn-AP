package modelo;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	private double precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Pelicula> atracciones, double precioDePaquete, String descripcion,
			 String urlPortada, String tipoPromocion) {
		super(nombre, atracciones, descripcion, urlPortada, precioDePaquete, tipoPromocion);
		this.precioDePaquete = precioDePaquete;
	}

	public PromocionAbsoluta(int id, String nombre, ArrayList<Pelicula> atracciones, double precioDePaquete,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(id, nombre, atracciones, descripcion, urlPortada, precioDePaquete, tipoPromocion);
		this.precioDePaquete = precioDePaquete;
	}

	@Override
	public double getPrecio() {
		return this.precioDePaquete;
	}
}