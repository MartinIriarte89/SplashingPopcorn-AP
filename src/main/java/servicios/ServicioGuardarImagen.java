package servicios;

import java.io.IOException;

import jakarta.servlet.http.Part;

public class ServicioGuardarImagen {

	private final String RUTA_PERFIL_USUARIO = "D:/Martin/Cursos/Argentina Programa/Webapp_Proyecto_Final/src/main/webapp/imagenes/perfiles/";
	private final String RUTA_PORTADA_PELICULA = "D:/Martin/Cursos/Argentina Programa/Webapp_Proyecto_Final/src/main/webapp/imagenes/portadas/peliculas/";
	private final String RUTA_FONDO_PELICULA = "D:/Martin/Cursos/Argentina Programa/Webapp_Proyecto_Final/src/main/webapp/imagenes/fondos/peliculas/";
	private final String RUTA_PORTADA_PROMOCION = "D:/Martin/Cursos/Argentina Programa/Webapp_Proyecto_Final/src/main/webapp/imagenes/portadas/promociones/";

	public boolean guardarFotoPerfilUsuario(String nombreArchivo, Part parteDeArchivo) {
		String rutaCompleta = RUTA_PERFIL_USUARIO + nombreArchivo;
		return guardarArchivo(rutaCompleta, parteDeArchivo);
	}

	public boolean guardarFotoPortadaPelicula(String nombreArchivo, Part parteDeArchivo) {
		String rutaCompleta = RUTA_PORTADA_PELICULA + nombreArchivo;
		return guardarArchivo(rutaCompleta, parteDeArchivo);
	}

	public boolean guardarFotoFondoPelicula(String nombreArchivo, Part parteDeArchivo) {
		String rutaCompleta = RUTA_FONDO_PELICULA + nombreArchivo;
		return guardarArchivo(rutaCompleta, parteDeArchivo);
	}

	public boolean guardarFotoPortadaPromocion(String nombreArchivo, Part parteDeArchivo) {
		String rutaCompleta = RUTA_PORTADA_PROMOCION + nombreArchivo;
		return guardarArchivo(rutaCompleta, parteDeArchivo);
	}

	private boolean guardarArchivo(String ruta, Part parteDeArchivo) {
		try {
			parteDeArchivo.write(ruta);

		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
