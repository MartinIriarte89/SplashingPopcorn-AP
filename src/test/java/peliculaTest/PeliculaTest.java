package peliculaTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.Genero;
import modelo.Pelicula;

public class PeliculaTest {

	Pelicula pelicula;
	Genero genero;

	@Before
	public void setup() {
		genero = new Genero("Aventuras");
		pelicula = new Pelicula(1, "VueltaAlMundo", 15, 30, 2, genero, "", "", "", 2021, "");
	}

	@Test
	public void crearPeliculaTest() {
		assertNotNull(pelicula);
	}

	@Test
	public void restarCupoTest() {
		pelicula.restarStock();

		int cupoObtenido = pelicula.getStock();
		int cupoEsperado = 1;

		assertEquals(cupoEsperado, cupoObtenido);
	}

	@Test
	public void noMostrarPeliculaSiNoTieneCupoTest() {
		Pelicula pelicula = new Pelicula(2, "ElColiseo", 2, 60, 1, genero, "", "", "", 2021, "");
		pelicula.restarStock();

		assertFalse(pelicula.tieneStock());
	}

	@Test
	public void noEstaIncluidaTest() {
		ArrayList<Pelicula> peliculaIncluida = new ArrayList<Pelicula>();
		ArrayList<Pelicula> peliculaNoIncluida = new ArrayList<Pelicula>();
		peliculaIncluida.add(pelicula);
		peliculaNoIncluida.add(new Pelicula(3, "LaGalaxia", 3, 60, 2, genero, "", "", "", 2021, ""));

		assertFalse(pelicula.noEstaIncluidaEn(peliculaIncluida));
		assertTrue(pelicula.noEstaIncluidaEn(peliculaNoIncluida));
	}
}
