package servicioOrdenarSugerenciasTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import modelo.Genero;
import modelo.Pelicula;
import modelo.Promocion;
import modelo.PromocionAbsoluta;
import modelo.PromocionPorcentual;
import modelo.Sugerencia;
import servicios.ServicioOrdenarSugerencias;

public class ServicioOrdenarSugerenciasTest {

	@Test
	public void crearOrdenarTest() {
		ServicioOrdenarSugerencias miOrden = new ServicioOrdenarSugerencias(null);
		assertNotNull(miOrden);
	}

	@Test
	public void ordenCorrectoTest() {
		ServicioOrdenarSugerencias miOrden = new ServicioOrdenarSugerencias(new Genero("Aventura"));
		Pelicula pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		Pelicula pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		Pelicula pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Ciencia ficción"), "", "", "", 2021,
				"");
		Pelicula pelicula4 = new Pelicula(33, "Avatar", 230, 161, 7, new Genero("Aventura"), "", "", "", 2009, "");
		Pelicula[] misPeliculas = { pelicula, pelicula2, pelicula3, pelicula4 };

		ArrayList<Pelicula> peliculasAventura = new ArrayList<Pelicula>();
		peliculasAventura.add(pelicula4);
		ArrayList<Pelicula> peliculasCienciaFiccion = new ArrayList<Pelicula>();
		peliculasCienciaFiccion.add(pelicula2);
		peliculasCienciaFiccion.add(pelicula3);
		Promocion promo1 = new PromocionAbsoluta(1, "Pack Ciencia Ficcion", peliculasCienciaFiccion, 300, "", "",
				"absoluta");
		Promocion promo2 = new PromocionPorcentual(2, "Pack Aventuras", peliculasAventura, 20, "", "", "porcentual");

		ArrayList<Sugerencia> ordenObtenido = new ArrayList<Sugerencia>();
		ordenObtenido.addAll(Arrays.asList(misPeliculas));
		ordenObtenido.add(promo1);
		ordenObtenido.add(promo2);
		ordenObtenido.sort(miOrden);
		ArrayList<Sugerencia> ordenEsperado = new ArrayList<Sugerencia>();
		ordenEsperado.add(promo2);
		ordenEsperado.add(promo1);
		ordenEsperado.add(pelicula4);
		ordenEsperado.add(pelicula);
		ordenEsperado.add(pelicula3);
		ordenEsperado.add(pelicula2);

		assertEquals(ordenEsperado, ordenObtenido);
	}
}
