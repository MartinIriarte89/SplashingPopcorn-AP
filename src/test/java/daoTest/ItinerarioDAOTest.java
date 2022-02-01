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
import modelo.Itinerario;
import modelo.Pelicula;
import modelo.Sugerencia;
import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.UsuarioDAO;
import persistencia.commons.ProveedorDeConexion;

public class ItinerarioDAOTest {
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
	public void cargarItinerariosTest() {
		ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();

		Pelicula pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		ArrayList<Sugerencia> sugerenciasEsperadas = new ArrayList<Sugerencia>();
		sugerenciasEsperadas.add(pelicula);
		Itinerario itinerario = new Itinerario(2, sugerenciasEsperadas, pelicula.getPrecio(), pelicula.getDuracion());

		ArrayList<Itinerario> itinerariosEsperados = new ArrayList<Itinerario>();
		itinerariosEsperados.add(itinerario);

		ArrayList<Itinerario> itinerariosObtenidos = itinerarioDAO.cargarItinerarios();

		assertEquals(itinerariosEsperados, itinerariosObtenidos);
	}

	@Test
	public void insertarComprasEnBBDDTest() {
		ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
		UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();

		Pelicula pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");

		ArrayList<Sugerencia> sugerenciasEsperadas = new ArrayList<Sugerencia>();
		sugerenciasEsperadas.add(pelicula);
		sugerenciasEsperadas.add(pelicula2);
		Itinerario itinerarioEsperado = new Itinerario(2, sugerenciasEsperadas, 370, 225);

		ArrayList<Usuario> usuariosObtenidos = usuarioDAO.cargar();
		ArrayList<Itinerario> itinerariosObtenidos = itinerarioDAO.cargarItinerarios();
		Itinerario itinerarioObtenido = itinerariosObtenidos.get(0);

		assertNotEquals(itinerarioEsperado, itinerarioObtenido);

		Usuario usuarioObtenido = usuariosObtenidos.get(0);
		usuarioObtenido.setItinerario(itinerarioObtenido);
		usuarioObtenido.comprar(pelicula2);

		usuarioDAO.actualizar(usuarioObtenido);
		itinerarioDAO.actualizar(itinerarioObtenido);
		itinerariosObtenidos = itinerarioDAO.cargarItinerarios();
		itinerarioObtenido = itinerariosObtenidos.get(0);

		assertEquals(itinerarioEsperado, itinerarioObtenido);
	}

}
