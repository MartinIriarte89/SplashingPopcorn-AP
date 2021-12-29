package modelo.objetoNulo;

import modelo.Pelicula;

public class PeliculaNula extends Pelicula{
	
	
	public PeliculaNula() {
		super("", 0, 0, 0, "", "", "", "", 0, "");
	}
	
	public static Pelicula construir() {
		return new PeliculaNula();
	}
	
	public boolean esNulo() {
		return true;
	}

}
