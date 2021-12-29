package modelo.objetoNulo;

import modelo.Genero;

public class GeneroNulo extends Genero{
	
	
	public GeneroNulo() {
		super("");
	}
	
	public static Genero construir() {
		return new GeneroNulo();
	}
	
	public boolean esNulo() {
		return true;
	}
}
