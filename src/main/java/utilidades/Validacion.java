package utilidades;

public class Validacion {

	public int esNumeroEnteroValido(String numero) {
		int num;
		try {
			num = Integer.parseInt(numero);
		} catch (NumberFormatException e) {
			num = Patron.NUMERO_NO_VALIDO;
		}catch (NullPointerException e) {
			num = Patron.NUMERO_NO_VALIDO;
		}
		return num;
	}

	public double esNumeroDoubleValido(String numero) {
		double num;
		try {
			num = Double.parseDouble(numero);
		} catch (NumberFormatException e) {
			num = Patron.NUMERO_NO_VALIDO;
		}catch (NullPointerException e) {
			num = Patron.NUMERO_NO_VALIDO;
		}

		return num;
	}

	public String esContrasenaValida(String contrasena) {
		if (!Comparador.comparar(contrasena, Patron.CONTRASENA_VALIDA)) {
			contrasena = Patron.CONTRASEÑA_INVALIDA;
		}
		return contrasena;
	}
	
	public String [] split(String texto , String regex) {
		String [] array = null;
		try {
			array = texto.split(regex);
		}catch (NullPointerException e) {
			array = new String[1];
			array [0] = "0";
		}
		return array;
	}	
}
