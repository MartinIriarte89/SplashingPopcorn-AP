package daoTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.Genero;
import modelo.Pelicula;
import modelo.Promocion;
import modelo.PromocionPorcentual;
import persistencia.FabricaDAO;
import persistencia.PromocionDAO;
import persistencia.commons.ProveedorDeConexion;

public class PromocionDAOTest {
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
	public void cargarPromocionesTest() {
		Pelicula pelicula1 = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		Pelicula pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Ciencia ficción"), "", "", "", 2021,
				"");
		ArrayList<Pelicula> peliculasEsperadas = new ArrayList<Pelicula>();
		peliculasEsperadas.add(pelicula1);
		peliculasEsperadas.add(pelicula2);
		peliculasEsperadas.add(pelicula3);
		Promocion promoEsperada = new PromocionPorcentual(1, "Animate a la acción", peliculasEsperadas, 20, null, null,
				"porcentual");
		ArrayList<Promocion> promocionesEsperadas = new ArrayList<Promocion>();

		PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();

		ArrayList<Promocion> promocionesObtenidas = promocionDAO.cargar();
		promocionesEsperadas.add(promoEsperada);

		assertNotNull(promocionesObtenidas);
		assertFalse(promocionesObtenidas.isEmpty());
		assertEquals(promocionesEsperadas, promocionesObtenidas);
	}
}
