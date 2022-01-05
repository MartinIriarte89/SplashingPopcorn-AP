package servicios.validaciones;

import java.util.HashMap;
import java.util.Map;

import utilidades.Comparador;
import utilidades.Patrones;

public class ValidacionDatosUsuario {
	private Map<String, String> errores;

	public Map<String, String> getErrores() {
		return this.errores;
	}

	public boolean esNumeroValido(String numero) {
		return Comparador.comparar(numero, Patrones.NUMERO_ENTERO);
	}

	public boolean datosSonValidos(String nombre, String usuario, String contrasena) {
		validarDatos(nombre, usuario, contrasena);
		return errores.isEmpty();
	}
	
	public boolean datosSonValidos(String nombre, String usuario, String contrasena,
			String dineroDisponible, String tiempoDisponible) {
		validarDatos(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible);
		return errores.isEmpty();
	}

	public boolean datosSonValidos(String id, String nombre, String usuario, String contrasena,
			String dineroDisponible, String tiempoDisponible) {
		validarDatos(id, nombre, usuario, contrasena, dineroDisponible, tiempoDisponible);
		return errores.isEmpty();
	}
	

	private void validarDatos(String nombre, String usuario, String contrasena) {
		errores = new HashMap<String, String>();

		if (nombre == null || nombre.equals(""))
			errores.put("nombre", "Nombre invalido");
		if (usuario == null || usuario.equals(""))
			errores.put("usuario", "Usuario invalido");
		if (!Comparador.comparar(contrasena, Patrones.CONTRASENA_VALIDA))
			errores.put("contrasena", "Contraseña invalida");
	}
	
	private void validarDatos(String nombre, String usuario, String contrasena,
			String dineroDisponible, String tiempoDisponible) {
		errores = new HashMap<String, String>();

		if (nombre == null || nombre.equals(""))
			errores.put("nombre", "Nombre invalido");
		if (usuario == null || usuario.equals(""))
			errores.put("usuario", "Usuario invalido");
		if (!Comparador.comparar(contrasena, Patrones.CONTRASENA_VALIDA))
			errores.put("contrasena", "Contraseña invalida");
		if (!Comparador.comparar(dineroDisponible, Patrones.NUMERO_DOUBLE))
			errores.put("dinero", "Formato invalido");
		if (!Comparador.comparar(tiempoDisponible, Patrones.NUMERO_ENTERO))
			errores.put("tiempo", "Formato invalido");
	}

	private void validarDatos(String id, String nombre, String usuario, String contrasena,
			String dineroDisponible, String tiempoDisponible) {
		errores = new HashMap<String, String>();

		if (!Comparador.comparar(id, Patrones.NUMERO_ENTERO))
			errores.put("id", "El usuario no existe");
		if (nombre == null || nombre.equals(""))
			errores.put("nombre", "Nombre invalido");
		if (usuario == null || usuario.equals(""))
			errores.put("usuario", "Usuario invalido");
		if (!Comparador.comparar(contrasena, Patrones.CONTRASENA_VALIDA))
			errores.put("contrasena", "Contraseña invalida");
		if (!Comparador.comparar(dineroDisponible, Patrones.NUMERO_DOUBLE))
			errores.put("dinero", "Formato invalido");
		if (!Comparador.comparar(tiempoDisponible, Patrones.NUMERO_ENTERO))
			errores.put("tiempo", "Formato invalido");
	}

}
