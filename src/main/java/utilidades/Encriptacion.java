package utilidades;

import org.mindrot.jbcrypt.BCrypt;

public class Encriptacion {
	
	private static final int RONDAS = 13;
	
	public static String hash(String contrasena) {
		String salt = BCrypt.gensalt(RONDAS);
		return BCrypt.hashpw(contrasena, salt);
	}
	
	public static boolean coincidencia(String contrasena, String contrasenaEncrip) {
		return BCrypt.checkpw(contrasena, contrasenaEncrip);
	}
	
	
}
