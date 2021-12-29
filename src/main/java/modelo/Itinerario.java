package modelo;

import java.util.ArrayList;

public class Itinerario {

	private int fkUsuario;
	private ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>();
	private ArrayList<Pelicula> peliculasCompradas = new ArrayList<Pelicula>();
	private int costoDelItinerario;
	private double duracionDelItinerario;

	public Itinerario(int idUsuario, ArrayList<Sugerencia> compras, int costoDelItinerario,
			double duracionDelItinerario) {
		this.fkUsuario = idUsuario;
		this.compras = compras;
		this.costoDelItinerario = costoDelItinerario;
		this.duracionDelItinerario = duracionDelItinerario;

		for (Sugerencia sugerencia : compras) {
			agregarPeliculaComprada(sugerencia);
		}
	}

	public ArrayList<Sugerencia> getCompras() {
		return compras;
	}

	public ArrayList<Pelicula> getPeliculasCompradas() {
		return this.peliculasCompradas;
	}

	public int getFkUsuario() {
		return this.fkUsuario;
	}

	public int getCostoDelItinerario() {
		return this.costoDelItinerario;
	}

	public double getDuracionDelItinerario() {
		return this.duracionDelItinerario;
	}

	public void agregarLaCompraDe(Sugerencia unaSugerencia) {
		this.costoDelItinerario += unaSugerencia.getPrecio();
		this.duracionDelItinerario += unaSugerencia.getDuracion();
		this.compras.add(unaSugerencia);
		agregarPeliculaComprada(unaSugerencia);
	}

	public boolean noTieneA(Sugerencia unaSugerencia) {
		return unaSugerencia.noEstaIncluidaEn(this.peliculasCompradas);
	}

	private void agregarPeliculaComprada(Sugerencia sugerencia) {
		if (sugerencia.esPromocion()) {
			Promocion miPromo = (Promocion) sugerencia;
			peliculasCompradas.addAll(miPromo.getPeliculas());
		} else {
			peliculasCompradas.add((Pelicula) sugerencia);
		}
	}
}
