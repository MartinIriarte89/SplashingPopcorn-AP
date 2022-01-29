package servicios;

import java.io.IOException;
import java.util.HashMap;

import jakarta.servlet.http.Part;

public class ServicioGuardarImagen {

	private final String RUTA_PERFIL_USUARIO = "./opt/tomcat/webapps/ROOT/imagenes/perfiles/";
	private final String RUTA_PORTADA_PELICULA = "./opt/tomcat/webapps/ROOT/imagenes/portadas/peliculas/";
	private final String RUTA_FONDO_PELICULA = "./opt/tomcat/webapps/ROOT/imagenes/fondos/peliculas/";
	private final String RUTA_PORTADA_PROMOCION = "./opt/tomcat/webapps/ROOT/imagenes/portadas/promociones/";
	private HashMap<String, String> errores;

	public boolean guardarFotoPerfilUsuario(String nombreArchivo, Part parteDeArchivo) {
		if (esImagenValida(parteDeArchivo)) {
			String rutaCompleta = RUTA_PERFIL_USUARIO + nombreArchivo;
			guardarArchivo(rutaCompleta, parteDeArchivo);
		}
		return errores.isEmpty();
	}

	public boolean guardarFotoPortadaPelicula(String nombreArchivo, Part parteDeArchivo) {
		if (esImagenValida(parteDeArchivo)) {
			String rutaCompleta = RUTA_PORTADA_PELICULA + nombreArchivo;
			guardarArchivo(rutaCompleta, parteDeArchivo);
		}
		return errores.isEmpty();
	}

	public Object getErrores() {
		return errores;
	}

	public boolean guardarFotoFondoPelicula(String nombreArchivo, Part parteDeArchivo) {
		if (esImagenValida(parteDeArchivo)) {
			String rutaCompleta = RUTA_FONDO_PELICULA + nombreArchivo;
			guardarArchivo(rutaCompleta, parteDeArchivo);
		}
		return errores.isEmpty();
	}

	public boolean guardarFotoPortadaPromocion(String nombreArchivo, Part parteDeArchivo) {
		if (esImagenValida(parteDeArchivo)) {
			String rutaCompleta = RUTA_PORTADA_PROMOCION + nombreArchivo;
			guardarArchivo(rutaCompleta, parteDeArchivo);
		}
		return errores.isEmpty();
	}

	private void guardarArchivo(String ruta, Part parteDeArchivo) {
		try {
			parteDeArchivo.write(ruta);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean esImagenValida(Part imagen) {
		validar(imagen);
		return errores.isEmpty();
	}

	private void validar(Part imagen) {
		errores = new HashMap<String, String>();
		if (!esExtensionImagen(imagen.getSubmittedFileName())) {
			errores.put("error", "Debe ser un formato de imagen válido (jpeg, jpg, png, gif o webp).");
		}
		if (!esTamañoImagenAceptado(imagen.getSize())) {
			errores.put("error", "El tamaño no debe superar 1 mb.");
		}
	}

	private boolean esExtensionImagen(String nombreImagen) {
		boolean estado = false;
		if (nombreImagen.endsWith(".jpg") || nombreImagen.endsWith(".png") || nombreImagen.endsWith(".jpeg")
				|| nombreImagen.endsWith(".gif") || nombreImagen.endsWith(".webp")) {
			estado = true;
		}

		return estado;
	}

	private boolean esTamañoImagenAceptado(long tamañoImagen) {
		return tamañoImagen <= 1000000;
	}
}
