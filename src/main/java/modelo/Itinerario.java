package modelo;

import java.util.ArrayList;
import java.util.Objects;

public class Itinerario {

	private int fkUsuario;
	private ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>();
	private ArrayList<Pelicula> peliculasCompradas = new ArrayList<Pelicula>();
	private double costoDelItinerario;
	private int duracionDelItinerario;

	public Itinerario(int idUsuario, ArrayList<Sugerencia> compras, double costoDelItinerario,
			int duracionDelItinerario) {
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

	public int getFkUsuario() {
		return this.fkUsuario;
	}

	public double getCostoDelItinerario() {
		return this.costoDelItinerario;
	}

	public int getDuracionDelItinerario() {
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

	@Override
	public int hashCode() {
		return Objects.hash(compras, costoDelItinerario, duracionDelItinerario, fkUsuario, peliculasCompradas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itinerario other = (Itinerario) obj;
		return Objects.equals(compras, other.compras) && costoDelItinerario == other.costoDelItinerario
				&& Double.doubleToLongBits(duracionDelItinerario) == Double
						.doubleToLongBits(other.duracionDelItinerario)
				&& fkUsuario == other.fkUsuario && Objects.equals(peliculasCompradas, other.peliculasCompradas);
	}

	@Override
	public String toString() {
		return "Itinerario [fkUsuario=" + fkUsuario + ", compras=" + compras + ", costoDelItinerario="
				+ costoDelItinerario + ", duracionDelItinerario=" + duracionDelItinerario + "]";
	}
}
