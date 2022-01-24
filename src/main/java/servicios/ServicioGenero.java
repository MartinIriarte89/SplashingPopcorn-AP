package servicios;

import java.util.ArrayList;

import modelo.Genero;
import persistencia.FabricaDAO;
import persistencia.GeneroDAO;
import persistencia.commons.ProveedorDeConexion;

public class ServicioGenero {
	GeneroDAO generoDAO = FabricaDAO.getGeneroDAO();

	public ArrayList<Genero> listar() {
		ArrayList<Genero> generos = generoDAO.cargar();
		ProveedorDeConexion.cerrarConexion();
		return generos;
	}

	public Genero borrar(String nombre) {
		Genero genero = buscarPor(nombre);
		if (!genero.esNulo()) {
			if (!generoDAO.borrar(genero)) {
				genero.getErrores().put("borrar",
						"No se puede eliminar porque existen películas asociadas a este género");
			}
			ProveedorDeConexion.cerrarConexion();
		}
		return genero;
	}

	public Genero buscarPor(String nombre) {
		Genero genero = generoDAO.buscarPor(nombre);
		ProveedorDeConexion.cerrarConexion();
		return genero;
	}

	public Genero crear(String nombre) {
		Genero genero = new Genero(nombre);
		if(genero.esValido()) {
			generoDAO.insertar(genero);
			ProveedorDeConexion.cerrarConexion();
		}
		return genero;
	}
}
