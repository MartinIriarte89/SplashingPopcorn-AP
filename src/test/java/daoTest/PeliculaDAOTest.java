package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Genero;
import modelo.Pelicula;
import persistencia.FabricaDAO;
import persistencia.PeliculaDAO;
import persistencia.commons.ProveedorDeConexion;

public class PeliculaDAOTest {
	private static Connection conexion;

	@BeforeClass
	public static void setUp() throws SQLException {
		conexion = ProveedorDeConexion.getConexion("jdbc:mysql://localhost:3306/bbb_pruebas");
		conexion.setAutoCommit(false);
	}

	@After
	public void tearDown() throws SQLException {
		conexion.rollback();
	}

	@AfterClass
	public static void tearDownClass() throws SQLException {
		conexion.setAutoCommit(true);
		ProveedorDeConexion.cerrarConexion();
	}

	@Test
	public void cargarPeliculasTest() {
		PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
		Pelicula pelicula1 = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		Pelicula pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Ciencia ficción"), "", "", "", 2021,
				"");
		Pelicula pelicula4 = new Pelicula(10, "Avatar", 230, 161, 7, new Genero("Aventura"), "", "", "", 2009, "");
		ArrayList<Pelicula> peliculasEsperadas = new ArrayList<Pelicula>();
		peliculasEsperadas.add(pelicula1);

		peliculasEsperadas.add(pelicula2);
		peliculasEsperadas.add(pelicula4);
		peliculasEsperadas.add(pelicula3);
		ArrayList<Pelicula> peliculasObtenidas = peliculaDAO.cargar();

		assertEquals(peliculasEsperadas, peliculasObtenidas);
	}

	@Test
	public void actualizarPorCupoTest() {
		PeliculaDAO peliculaDAO = FabricaDAO.getPeliculaDAO();
		ArrayList<Pelicula> peliculasObtenidas = peliculaDAO.cargar();
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>(peliculasObtenidas);

		int cupoInicial = peliculasObtenidas.get(1).getStock();
		peliculasObtenidas.get(1).restarStock();
		for (Pelicula pelicula : peliculas) {
			peliculaDAO.actualizarStock(pelicula);
		}
		peliculasObtenidas = peliculaDAO.cargar();
		int cupoModificado = peliculasObtenidas.get(1).getStock();

		assertNotEquals(cupoInicial, cupoModificado);
	}
}
