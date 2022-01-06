package utilidades;

public class Validacion {

	public int esNumeroEnteroValido(String numero) {
		if(!Comparador.comparar(numero, Patron.NUMERO_ENTERO)) {
			numero = Patron.FORMATO_INCORRECTO;
		}
		int num = Integer.parseInt(numero);
		
		return num;
	}

	public double esNumeroDoubleValido(String numero) {
		if(!Comparador.comparar(numero, Patron.NUMERO_DOUBLE)) {
			numero = Patron.FORMATO_INCORRECTO;
		}
		double num = Double.parseDouble(numero);
		
		return num;
	}
	
	public String esContrasenaValida(String contrasena) {
		if(!Comparador.comparar(contrasena, Patron.NUMERO_DOUBLE)) {
			contrasena = Patron.CONTRASEÑA_INVALIDA;
		}
		
		return contrasena;
	}

}
