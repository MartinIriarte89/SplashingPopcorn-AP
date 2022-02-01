package modelo;

import java.util.ArrayList;
import java.util.Map;

public class PromocionPorcentual extends Promocion {

	private double porcentajeACobrar;

	public PromocionPorcentual(String nombre, ArrayList<Pelicula> peliculas, int porcentajeDescuento,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(nombre, peliculas, descripcion, urlPortada, porcentajeDescuento, tipoPromocion);
		this.porcentajeACobrar = (100 - porcentajeDescuento) / 100.0;
	}

	public PromocionPorcentual(int id, String nombre, ArrayList<Pelicula> peliculas, int porcentajeDescuento,
			String descripcion, String urlPortada, String tipoPromocion) {
		super(id, nombre, peliculas, descripcion, urlPortada, porcentajeDescuento, tipoPromocion);
		this.porcentajeACobrar = (100 - porcentajeDescuento) / 100.0;
	}

	@Override
	public double getPrecio() {
		return super.getPrecio() * this.porcentajeACobrar;
	}

	@Override
	public void validar() {
		super.validar();
		Map<String, String> errores = super.getErrores();

		if (super.getBeneficio() < 0 || super.getBeneficio() > 100) {
			errores.put("beneficio", "El descuento debe estar comprendido entre 0 y 100");
		}
		super.setErrores(errores);
	}
}