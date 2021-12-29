package modelo;

import java.util.ArrayList;

public class PromocionAbsoluta extends Promocion {

	private double precioDePaquete;

	public PromocionAbsoluta(String nombre, ArrayList<Pelicula> atracciones, double precioDePaquete, String descripcion,
			 String urlPortada) {
		super(nombre, atracciones, descripcion, urlPortada);
		this.precioDePaquete = precioDePaquete;
	}

	public PromocionAbsoluta(int id, String nombre, ArrayList<Pelicula> atracciones, double precioDePaquete,
			String descripcion, String urlPortada) {
		super(id, nombre, atracciones, descripcion, urlPortada);
		this.precioDePaquete = precioDePaquete;
	}

	@Override
	public double getPrecio() {
		return this.precioDePaquete;
	}
}