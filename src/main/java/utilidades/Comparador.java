package utilidades;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Comparador {
	private static Pattern pat;
	private static Matcher comparador;
	
	public static boolean comparar(String cadena, String patron) {
		pat = Pattern.compile(patron);
		comparador = pat.matcher(cadena);
		return comparador.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(Comparador.comparar("Matías Ottero", Patron.NOMBRE_APELLIDO_VALIDO));
	}
}
