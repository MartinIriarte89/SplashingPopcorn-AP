package servicios;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.http.Part;

public class ServicioGuardarImagen {

	private final String RUTA_PERFIL_USUARIO = "D:/Martin/Cursos/Argentina Programa/Webapp_Proyecto_Final/src/main/webapp/imagenes/perfiles/";

	public boolean guardarFotoPerfilUsuario(String nombreArchivo, Collection<Part> partesDeArchivo) {
		String rutaCompleta = RUTA_PERFIL_USUARIO + nombreArchivo;
		return guardarArchivo(rutaCompleta, partesDeArchivo);
	}

	private boolean guardarArchivo(String ruta, Collection<Part> partesDeArchivo) {
		try {
			for (Part parte : partesDeArchivo) {
				parte.write(ruta);
			}
		} catch (IOException e) {
			return false;
		}
		return true;
	}
}
