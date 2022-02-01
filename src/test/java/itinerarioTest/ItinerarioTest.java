package itinerarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.Genero;
import modelo.Itinerario;
import modelo.Pelicula;
import modelo.PromocionPorcentual;
import modelo.Sugerencia;

public class ItinerarioTest {

	Pelicula pelicula;
	Pelicula pelicula2;
	Pelicula pelicula3;
	Pelicula pelicula4;
	ArrayList<Pelicula> misPeliculas = new ArrayList<Pelicula>();
	Sugerencia promocion;

	@Before
	public void setup() {
		pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		pelicula4 = new Pelicula(33, "Avatar", 230, 161, 7, new Genero("Aventura"), "", "", "", 2009, "");
		misPeliculas.add(pelicula);
		misPeliculas.add(pelicula2);
		misPeliculas.add(pelicula3);
		promocion = new PromocionPorcentual(1, "Animate a la acción", misPeliculas, 20, null, null, "porcentual");
	}

	@Test
	public void crearItinerarioTest() {
		Itinerario itinerario = new Itinerario(1, new ArrayList<Sugerencia>(), 0, 0);
		assertNotNull(itinerario);
	}

	@Test
	public void crearItinerarioConComprasTest() {
		ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>(misPeliculas);
		Itinerario itinerario = new Itinerario(1, compras, 570, 380);

		assertNotNull(itinerario);
		assertFalse(itinerario.getCompras().isEmpty());
	}

	@Test
	public void agregarCompraTest() {
		Itinerario itinerario = new Itinerario(1, new ArrayList<Sugerencia>(), 0, 0);
		itinerario.agregarLaCompraDe(pelicula4);
		itinerario.agregarLaCompraDe(promocion);

		double costoObtenido = itinerario.getCostoDelItinerario();
		double costoEsperado = 686;
		int duracionObtenida = itinerario.getDuracionDelItinerario();
		int duracionEsperada = 541;

		assertFalse(itinerario.getCompras().isEmpty());
		assertEquals(costoEsperado, costoObtenido, 0);
		assertEquals(duracionEsperada, duracionObtenida);
	}

	@Test
	public void mostrarSiSugerenciaSeComproTest() {
		Itinerario itinerario = new Itinerario(1, new ArrayList<Sugerencia>(), 0, 0);
		itinerario.agregarLaCompraDe(pelicula2);
		itinerario.agregarLaCompraDe(pelicula3);

		assertTrue(itinerario.noTieneA(pelicula));
		assertFalse(itinerario.noTieneA(pelicula2));
		assertFalse(itinerario.noTieneA(pelicula3));
	}

	@Test
	public void mostrarSiSugerenciaSeComproAnteriormenteTest() {
		ArrayList<Sugerencia> atraccionesYaCompradas = new ArrayList<Sugerencia>();
		atraccionesYaCompradas.add(pelicula2);
		atraccionesYaCompradas.add(pelicula3);

		Itinerario itinerario = new Itinerario(2, atraccionesYaCompradas, 360, 263);

		assertTrue(itinerario.noTieneA(pelicula));
		assertFalse(itinerario.noTieneA(pelicula2));
		assertFalse(itinerario.noTieneA(pelicula3));
	}

}
