package servicios.validaciones;

import java.util.Map;

import utilidades.Comparador;
import utilidades.Patrones;

public class ValidacionDatosPelicula {
	private Map<String, String> errores;

	public Map<String, String> getErrores() {
		return this.errores;
	}

	public boolean esNumeroValido(String numero) {
		return Comparador.comparar(numero, Patrones.NUMERO_ENTERO);
	}

	public boolean datosSonValidos(String titulo, String precio, String duracion, String stock, String descripcion,
			String anioLanzamiento, String lema) {
		validarDatos(titulo, precio, duracion, stock, descripcion, anioLanzamiento, lema);
		return errores.isEmpty();
	}

	public boolean datosSonValidos(String id, String titulo, String precio, String duracion, String stock,
			String descripcion, String anioLanzamiento, String lema) {
		validarDatos(id, titulo, precio, duracion, stock, descripcion, anioLanzamiento, lema);
		return errores.isEmpty();
	}

	private void validarDatos(String titulo, String precio, String duracion, String stock, String descripcion,
			String anioLanzamiento, String lema) {
		if (!Comparador.comparar(titulo, Patrones.SIN_CARACTERES_ESPECIALES))
			errores.put("titulo", "Título inválido");
		if (!Comparador.comparar(precio, Patrones.NUMERO_DOUBLE))
			errores.put("precio", "Formato inválido");
		if (!Comparador.comparar(duracion, Patrones.NUMERO_ENTERO))
			errores.put("duracion", "Formato inválido");
		if (!Comparador.comparar(stock, Patrones.NUMERO_ENTERO))
			errores.put("stock", "Formato inválido");
		if (!Comparador.comparar(descripcion, Patrones.NULO_SIN_CARACTERES_ESPECIALES))
			errores.put("descripcion", "Descripción inválida");
		if (!Comparador.comparar(anioLanzamiento, Patrones.NUMERO_ENTERO))
			errores.put("anioLanzamiento", "Formato inválido");
		if (!Comparador.comparar(lema, Patrones.NULO_SIN_CARACTERES_ESPECIALES))
			errores.put("lema", "Lema inválido");

	}

	private void validarDatos(String id, String titulo, String precio, String duracion, String stock,
			String descripcion, String anioLanzamiento, String lema) {

		if (!Comparador.comparar(id, Patrones.NUMERO_ENTERO))
			errores.put("id", "El usuario no existe");
		if (!Comparador.comparar(titulo, Patrones.SIN_CARACTERES_ESPECIALES))
			errores.put("titulo", "Título inválido");
		if (!Comparador.comparar(precio, Patrones.NUMERO_DOUBLE))
			errores.put("precio", "Formato inválido");
		if (!Comparador.comparar(duracion, Patrones.NUMERO_ENTERO))
			errores.put("duracion", "Formato inválido");
		if (!Comparador.comparar(stock, Patrones.NUMERO_ENTERO))
			errores.put("stock", "Formato inválido");
		if (!Comparador.comparar(descripcion, Patrones.NULO_SIN_CARACTERES_ESPECIALES))
			errores.put("descripcion", "Descripción inválida");
		if (!Comparador.comparar(anioLanzamiento, Patrones.NUMERO_ENTERO))
			errores.put("anioLanzamiento", "Formato inválido");
		if (!Comparador.comparar(lema, Patrones.NULO_SIN_CARACTERES_ESPECIALES))
			errores.put("lema", "Lema inválido");
	}
}
