package servicios;

import java.util.ArrayList;

import modelo.Genero;
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

	public Pelicula crear(String titulo, double precio, int duracion, int stock, Genero genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {

		Pelicula pelicula = new Pelicula(titulo, precio, duracion, stock, genero, descripcion, urlPortada, urlFondo,
				anioLanzamiento, lema);
		if (pelicula.esValida()) {
			peliculaDAO.insertar(pelicula);
			ProveedorDeConexion.cerrarConexion();
		}
		return pelicula;
	}

	public Pelicula editar(int id, String titulo, double precio, int duracion, int stock, Genero genero,
			String descripcion, String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		Pelicula pelicula = buscarPor(id);

		if (!pelicula.esNulo()) {
			pelicula.setTitulo(titulo);
			pelicula.setPrecio(precio);
			pelicula.setDuracion(duracion);
			pelicula.setGenero(genero);
			pelicula.setStock(stock);
			pelicula.setDescripcion(descripcion);
			pelicula.setUrlPortada(urlPortada);
			pelicula.setUrlFondo(urlFondo);
			pelicula.setAnioLanzamiento(anioLanzamiento);
			pelicula.setLema(lema);
			
			if (pelicula.esValida()) {
				peliculaDAO.editar(pelicula);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return pelicula;
	}

	public Pelicula borrar(int id) {
		Pelicula pelicula = buscarPor(id);

		if (!pelicula.esNulo()) {
			peliculaDAO.borrar(pelicula);
		}
		ProveedorDeConexion.cerrarConexion();

		return pelicula;
	}
	
	public ArrayList<Integer> idPromocionesEnLasQueSeEncuentra(int idPelicula){
		ArrayList<Integer> idsPromos = peliculaDAO.idPromocionesEnLasQueSeEncuentra(idPelicula);
		return idsPromos;
	}

	public Pelicula buscarPor(int id) {
		Pelicula pelicula = peliculaDAO.buscarSinEliminadasPor(id);
		ProveedorDeConexion.cerrarConexion();
		return pelicula;
	}
}
