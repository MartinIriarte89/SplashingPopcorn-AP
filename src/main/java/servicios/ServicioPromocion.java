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
			promocion = new PromocionPorcentual(titulo, peliculas, (int) descuento, descripcion, urlPortada,
					tipoPromocion);
		else if (tipoPromocion.equalsIgnoreCase("Absoluta"))
			promocion = new PromocionAbsoluta(titulo, peliculas, descuento, descripcion, urlPortada, tipoPromocion);
		else
			promocion = new PromocionAPorB(titulo, peliculas, (int) descuento, descripcion, urlPortada, tipoPromocion);

		if (promocion.esValida()) {
			promocionDAO.insertar(promocion);
			ProveedorDeConexion.cerrarConexion();
		}

		return promocion;
	}

	public Promocion editar(int id, String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada,
			String tipoPromocion, double beneficio) {
		Promocion promocion = buscarPor(id);

		if (!promocion.esNulo()) {
			if (!tipoPromocion.equalsIgnoreCase(promocion.getTipoPromocion())) {

				if (tipoPromocion.equalsIgnoreCase("porcentual"))
					promocion = new PromocionPorcentual(id, titulo, peliculas, (int) beneficio, descripcion, urlPortada,
							tipoPromocion);
				else if (tipoPromocion.equalsIgnoreCase("absoluta"))
					promocion = new PromocionAbsoluta(id, titulo, peliculas, beneficio, descripcion, urlPortada,
							tipoPromocion);
				else
					promocion = new PromocionAPorB(id, titulo, peliculas, (int) beneficio, descripcion, urlPortada,
							tipoPromocion);

			} else {
				promocion.setTitulo(titulo);
				promocion.setPeliculas(peliculas);
				promocion.setDescripcion(descripcion);
				promocion.setUrlPortada(urlPortada);
				promocion.setTipoPromocion(tipoPromocion);
				promocion.setBeneficio(beneficio);
			}

			if (promocion.esValida()) {
				promocionDAO.editar(promocion);
				ProveedorDeConexion.cerrarConexion();
			}
		}
		return promocion;
	}

	public Promocion borrar(int id) {
		Promocion promocion = buscarPor(id);
		if (!promocion.esNulo()) {
			promocionDAO.borrar(promocion);
		}
		ProveedorDeConexion.cerrarConexion();
		return promocion;
	}

	public Promocion buscarPor(int id) {
		Promocion promocion = promocionDAO.buscarSinEliminadasPor(id);
		ProveedorDeConexion.cerrarConexion();
		return promocion;

	}
}
