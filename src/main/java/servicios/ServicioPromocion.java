package servicios;

import java.util.ArrayList;

import modelo.Pelicula;
import modelo.Promocion;
import modelo.PromocionAPorB;
import modelo.PromocionAbsoluta;
import modelo.PromocionPorcentual;
import persistencia.FabricaDAO;
import persistencia.PromocionDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioPromocion {

	PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();

	public ArrayList<Promocion> listar() {
		ArrayList<Promocion> promociones = promocionDAO.cargar();
		ProveedorDeConexion.cerrarConexion();
		return promociones;
	}

	public Promocion crear(String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada,
			String tipoPromocion, double descuento) {
		
		Promocion promocion;

		if (tipoPromocion.equalsIgnoreCase("Porcentual"))
			promocion = new PromocionPorcentual(titulo, peliculas, (int) descuento, descripcion, urlPortada);
		else if (tipoPromocion.equalsIgnoreCase("Absoluta"))
			promocion = new PromocionAbsoluta(titulo, peliculas, descuento, descripcion, urlPortada);
		else
			promocion = new PromocionAPorB(titulo, peliculas, (int) descuento, descripcion, urlPortada);

		if (promocion.esValida()) {
			promocionDAO.insertar(promocion, descuento, tipoPromocion);
			ProveedorDeConexion.cerrarConexion();
		}

		return promocion;
	}

	public Promocion editar(int id, String titulo, ArrayList<Pelicula> peliculas, String descripcion,
			String urlPortada) {
		Promocion promocion = buscarPor(id);

		promocion.setTitulo(titulo);
		promocion.setDescripcion(descripcion);
		promocion.setUrlPortada(urlPortada);

		if (promocion.esValida()) {
			promocionDAO.editar(promocion);
			ProveedorDeConexion.cerrarConexion();
		}
		return promocion;
	}

	public void borrar(int id) {
		Promocion promocion = buscarPor(id);
		promocionDAO.borrar(promocion);
		ProveedorDeConexion.cerrarConexion();
	}

	public Promocion buscarPor(int id) {
		Promocion promocion = promocionDAO.buscarPor(id);
		ProveedorDeConexion.cerrarConexion();
		return promocion;
		
	}
}
