package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

public class UsuarioDAOTest {
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
	public void cargarUsuariosTest() {
		UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();

		Pelicula pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		sugerencias.add(pelicula);

		Usuario usuarioEsperado = new Usuario(2, "José Olaechea","joseolaechea", "", 2000, 2000, new Genero("Animación"), "", false);
		Itinerario itinerarioEsperado = new Itinerario(usuarioEsperado.getId(), sugerencias, 210, 117);
		usuarioEsperado.setItinerario(itinerarioEsperado);
		ArrayList<Usuario> usuariosEsperados = new ArrayList<Usuario>();

		ArrayList<Usuario> usuariosObtenidos = usuarioDAO.cargar();
		usuariosEsperados.add(usuarioEsperado);

		assertNotNull(usuariosObtenidos);
		assertFalse(usuariosObtenidos.isEmpty());
		assertEquals(usuariosEsperados, usuariosObtenidos);
	}

	@Test
	public void actualizarUsuarioTest() {
		UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
		ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();

		Pelicula pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		ArrayList<Sugerencia> sugerencias = new ArrayList<Sugerencia>();
		sugerencias.add(pelicula);
		sugerencias.add(pelicula2);

		Usuario usuarioEsperado = new Usuario(2, "José Olaechea","joseolaechea", "", 1840, 1892, new Genero("Animación"), "", false);
		Itinerario itinerario = new Itinerario(usuarioEsperado.getId(), sugerencias, 370, 225);
		usuarioEsperado.setItinerario(itinerario);

		ArrayList<Usuario> usuariosEsperados = new ArrayList<Usuario>();
		usuariosEsperados.add(usuarioEsperado);
		ArrayList<Usuario> usuariosObtenidos = usuarioDAO.cargar();

		assertNotEquals(usuariosEsperados, usuariosObtenidos);

		Usuario usuarioObtenido = usuariosObtenidos.get(0);
		usuarioObtenido.comprar(pelicula2);
		usuarioDAO.actualizar(usuarioObtenido);
		itinerarioDAO.actualizar(usuarioObtenido.getItinerario());
		usuariosObtenidos = usuarioDAO.cargar();

		assertEquals(usuariosEsperados, usuariosObtenidos);
	}
}
