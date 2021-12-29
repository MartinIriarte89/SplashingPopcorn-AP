package servicios;

import java.util.ArrayList;

import modelo.Genero;
import persistencia.FabricaDAO;
import persistencia.GeneroDAO;

public class ServicioGenero {
	GeneroDAO generoDAO = FabricaDAO.getGeneroDAO();
	
	
	public ArrayList<Genero> listar() {
		return generoDAO.cargar();
	}
	
	public void borrar(String nombre) {
		Genero genero = buscarPor(nombre);
		generoDAO.borrar(genero);
	}
	
	public Genero buscarPor(String nombre) {
		return generoDAO.buscarPor(nombre);
	}
	public Genero crear(String nombre) {
		Genero genero = new Genero(nombre);
		generoDAO.insertar(genero);
		
		return genero;
	}
}
