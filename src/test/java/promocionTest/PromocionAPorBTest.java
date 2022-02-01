package promocionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.Genero;
import modelo.Pelicula;
import modelo.Promocion;
import modelo.PromocionAPorB;

public class PromocionAPorBTest {

	Pelicula pelicula;
	Pelicula pelicula2;
	Pelicula pelicula3;
	ArrayList<Pelicula> misPeliculas = new ArrayList<Pelicula>();
	Promocion miPromo;

	@Before
	public void setup() {
		pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Acción"), "", "", "", 2021, "");
		pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Acción"), "", "", "", 2021, "");
		misPeliculas.add(pelicula);
		misPeliculas.add(pelicula2);
		misPeliculas.add(pelicula3);
		miPromo = new PromocionAPorB(1, "Animate a la acción", misPeliculas, 2, null, null, "axb");
	}

	@Test
	public void crearPromocionTest() {
		assertNotNull(miPromo);
	}

	@Test
	public void obtenerPrecioDePromocionTest() {
		double precioObtenido = miPromo.getPrecio();
		double precioEsperado = 410;

		assertEquals(precioEsperado, precioObtenido, 0);
	}

	@Test
	public void obtenerCupoDePromocionTest() {
		int cupoObtenido = miPromo.getStock();
		int cupoEsperado = 4;

		assertEquals(cupoEsperado, cupoObtenido);
	}

	@Test
	public void restarCupoTest() {
		miPromo.restarStock();

		int cupoObtenido = miPromo.getStock();
		int cupoEsperado = 3;

		assertEquals(cupoEsperado, cupoObtenido);
	}

	@Test
	public void noMostrarPromocionSiNoTieneCupoTest() {
		Pelicula pelicula = new Pelicula(8, "Apex", 160, 108, 1, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Acción"), "", "", "", 2021, "");
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();
		peliculas.add(pelicula);
		peliculas.add(pelicula2);
		Promocion promo = new PromocionAPorB(1, "Animate a la acción", peliculas, 2, null, null, "axb");
		promo.restarStock();

		assertFalse(promo.tieneStock());
	}

	@Test
	public void obtenerDuracionDePromocionTest() {
		int duracionObtenida = miPromo.getDuracion();
		int duracionEsperada = 380;

		assertEquals(duracionEsperada, duracionObtenida);
	}

	@Test
	public void noEstaIncluidaTest() {
		ArrayList<Pelicula> peliculaIncluida = new ArrayList<Pelicula>();
		ArrayList<Pelicula> peliculaNoIncluida = new ArrayList<Pelicula>();
		peliculaIncluida.add(pelicula);
		peliculaNoIncluida
				.add(new Pelicula(105, "Nueva Pelicula", 150, 60, 2, new Genero("Acción"), "", "", "", 2021, ""));

		assertFalse(miPromo.noEstaIncluidaEn(peliculaIncluida));
		assertTrue(miPromo.noEstaIncluidaEn(peliculaNoIncluida));
	}
}
