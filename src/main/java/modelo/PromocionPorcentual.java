package modelo;

import java.util.ArrayList;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, ArrayList<Pelicula> atracciones, int porcentajeDescuento,
			String descripcion, String urlPortada) {
		super(nombre, atracciones, descripcion, urlPortada);
		this.porcentajeACobrar = (100 - porcentajeDescuento) / 100.0;
	}

	public PromocionPorcentual(int id, String nombre, ArrayList<Pelicula> atracciones, int porcentajeDescuento,
			String descripcion, String urlPortada) {
		super(id, nombre, atracciones, descripcion, urlPortada);
		this.porcentajeACobrar = (100 - porcentajeDescuento) / 100.0;
	}

	@Override
	public double getPrecio() {
		return super.getPrecio() * this.porcentajeACobrar;
	}
}