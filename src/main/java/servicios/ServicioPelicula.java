package servicios;

import java.util.ArrayList;
import modelo.Pelicula;
import persistencia.FabricaDAO;
import persistencia.PeliculaDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioPelicula {

	PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();

	public ArrayList<Pelicula> listar() {
		ArrayList<Pelicula> peliculas = peliculaDAO.cargar();
		ProveedorDeConexion.cerrarConexion();
		return peliculas;
	}

	public Pelicula crear(String titulo, double precio, int duracion, int stock, String genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {

		Pelicula pelicula = new Pelicula(titulo, precio, duracion, stock, genero, descripcion, urlPortada, urlFondo,
				anioLanzamiento, lema);
		if (pelicula.esValida()) {
			peliculaDAO.insertar(pelicula);
			ProveedorDeConexion.cerrarConexion();
		}
		return pelicula;
	}

	public Pelicula editar(int id, String titulo, double precio, int duracion, int stock, String genero,
			String descripcion, String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		Pelicula pelicula = buscarPor(id);

		pelicula.setTitulo(titulo);
		pelicula.setPrecio(precio);
		pelicula.setDuracion(duracion);
		pelicula.setGenero(genero);
		pelicula.setStock(stock);
		pelicula.setDescripcion(descripcion);
		if (urlPortada != null) {
			pelicula.setUrlPortada(urlPortada);
		}
		if (urlFondo != null) {
			pelicula.setUrlFondo(urlFondo);
		}
		pelicula.setAnioLanzamiento(anioLanzamiento);

		if (lema != null) {
			pelicula.setLema(lema);
		}
		if (pelicula.esValida()) {
			peliculaDAO.editar(pelicula);
			ProveedorDeConexion.cerrarConexion();
		}
		return pelicula;
	}

	public void borrar(int id) {
		Pelicula pelicula = buscarPor(id);
		peliculaDAO.borrar(pelicula);
		ProveedorDeConexion.cerrarConexion();
	}

	public Pelicula buscarPor(int id) {
		Pelicula pelicula = peliculaDAO.buscarPor(id);
		ProveedorDeConexion.cerrarConexion();
		return pelicula;
	}
}
