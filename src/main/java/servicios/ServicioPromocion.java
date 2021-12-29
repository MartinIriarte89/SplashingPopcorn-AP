package servicios;

import java.util.ArrayList;

import modelo.Pelicula;
import modelo.Promocion;
import persistencia.FabricaDAO;
import persistencia.PromocionDAO;

public class ServicioPromocion {

	PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();

	public ArrayList<Promocion> listar() {
		return promocionDAO.cargar();
	}
	
	public Promocion editar(int id, String titulo, ArrayList<Pelicula> peliculas, String descripcion,
			String urlPortada) {
		Promocion promocion = buscarPor(id);

		promocion.setTitulo(titulo);
		promocion.setDescripcion(descripcion);
		promocion.setUrlPortada(urlPortada);

		if (promocion.esValida()) {
			promocionDAO.editar(promocion);
		}
		return promocion;
	}

	public void borrar(int id) {
		Promocion promocion = buscarPor(id);
		promocionDAO.borrar(promocion);
	}

	public Promocion buscarPor(int id) {
		return promocionDAO.buscarPor(id);
	}
}
