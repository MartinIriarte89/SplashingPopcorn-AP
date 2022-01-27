package modelo.objetoNulo;

import java.util.ArrayList;

import modelo.Itinerario;
import modelo.Sugerencia;

public class ItinerarioNulo extends Itinerario {

	private static ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>();

	public ItinerarioNulo() {
		super(0, compras, 0, 0);
	}

	public static Itinerario construir() {
		return new ItinerarioNulo();
	}

	public boolean esNulo() {
		return true;
	}
}
