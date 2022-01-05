package utilidades;

public class Patrones {

	public static final String NUMERO_ENTERO = "^\\d+$";
	public static final String NOMBRE_APELLIDO_VALIDO = "^[\\w']+[ ][^0-9_!¡?÷?¿\\\\+=@#$%ˆ&*(){}\"'|~<>;:\\[\\]]{2,}$";
	public static final String USUARIO_VALIDO = "^[\\w']+[^_!¡?÷?¿\\\\+=@#$%ˆ&*(){}\"'|~<>;:\\[\\]]$";
	public static final String CONTRASENA_VALIDA = "^[\\w']+[^ _!¡?÷?¿\\\\+=@#$%ˆ&*(){}\"'|~<>;:\\[\\]]{4,}$";
	public static final String NUMERO_DOUBLE = "^[0-9]+([.])?[0-9]+$";
	public static final String SIN_CARACTERES_ESPECIALES = "^[\\w' !¡?¿]+$";
	public static final String NULO_SIN_CARACTERES_ESPECIALES = "^[\\w' !¡?¿]*$";
	
	public static void main(String[] args) {
		System.out.println(Comparador.comparar("", NULO_SIN_CARACTERES_ESPECIALES));
		
	}
}
