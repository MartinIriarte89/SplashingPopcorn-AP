package usuarioTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import modelo.Genero;
import modelo.Itinerario;
import modelo.Pelicula;
import modelo.PromocionPorcentual;
import modelo.Sugerencia;
import modelo.Usuario;

public class UsuarioTest {

	Usuario usuario;
	Pelicula pelicula;
	Pelicula pelicula2;
	Pelicula pelicula3;
	ArrayList<Pelicula> misPeliculas = new ArrayList<Pelicula>();
	PromocionPorcentual promocion;

	@Before
	public void setup() {
		usuario = new Usuario(1, "Pablo Ramos", "pabloramos", "", 3000, 3000, new Genero("Acción"), "", false);
		pelicula = new Pelicula(5, "Alerta roja", 210, 117, 10, new Genero("Acción"), "", "", "", 2021, "");
		pelicula2 = new Pelicula(8, "Apex", 160, 108, 4, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		pelicula3 = new Pelicula(33, "Duna", 200, 155, 24, new Genero("Ciencia ficción"), "", "", "", 2021, "");
		misPeliculas.add(pelicula);
		misPeliculas.add(pelicula2);
		promocion = new PromocionPorcentual(1, "Promo Epica", misPeliculas, 15, null, null, "porcentual");
	}

	@Test
	public void crearUsuarioTest() {
		assertNotNull(usuario);
	}

	@Test
	public void comprarTest() {
		Itinerario itinerario = new Itinerario(usuario.getId(), new ArrayList<Sugerencia>(), 0, 0);
		usuario.setItinerario(itinerario);
		usuario.comprar(pelicula3);

		double dineroDispObtenido = usuario.getDineroDisponible();
		double dineroDispEsperado = 2800;
		int tiempoDispObtenido = usuario.getTiempoDisponible();
		int tiempoDispEsperado = 2845;

		assertEquals(dineroDispEsperado, dineroDispObtenido, 0);
		assertEquals(tiempoDispEsperado, tiempoDispObtenido);
	}

	@Test
	public void setItinerarioTest() {
		ArrayList<Sugerencia> compras = new ArrayList<Sugerencia>(misPeliculas);
		Itinerario miItinerario = new Itinerario(1, compras, 10, 5);

		assertNull(usuario.getItinerario());

		usuario.setItinerario(miItinerario);

		assertNotNull(usuario.getItinerario());
	}

	@Test
	public void usuarioPuedeComprarTest() {
		Itinerario itinerario = new Itinerario(usuario.getId(), new ArrayList<Sugerencia>(), 0, 0);
		usuario.setItinerario(itinerario);
		double dineroDisp = usuario.getDineroDisponible();
		int tiempoDisp = usuario.getTiempoDisponible();
		Pelicula pelicula = new Pelicula(8, "Apex", dineroDisp, tiempoDisp, 4, new Genero("Ciencia ficción"), "", "",
				"", 2021, "");

		assertTrue(usuario.puedeComprarA(pelicula));
	}

	@Test
	public void usuarioNoLeAlcanzaDineroTest() {
		Itinerario itinerario = new Itinerario(usuario.getId(), new ArrayList<Sugerencia>(), 0, 0);
		usuario.setItinerario(itinerario);
		double dineroDisp = usuario.getDineroDisponible();
		int tiempoDisp = usuario.getTiempoDisponible();
		Pelicula pelicula = new Pelicula(8, "Apex", dineroDisp + 1, tiempoDisp, 4, new Genero("Ciencia ficción"), "",
				"", "", 2021, "");

		assertFalse(usuario.puedeComprarA(pelicula));
	}

	@Test
	public void usuarioNoLeAlcanzaTiempoTest() {
		Itinerario itinerario = new Itinerario(usuario.getId(), new ArrayList<Sugerencia>(), 0, 0);
		usuario.setItinerario(itinerario);
		double dineroDisp = usuario.getDineroDisponible();
		int tiempoDisp = usuario.getTiempoDisponible();
		Pelicula pelicula = new Pelicula(8, "Apex", dineroDisp, tiempoDisp + 1, 4, new Genero("Ciencia ficción"), "",
				"", "", 2021, "");

		assertFalse(usuario.puedeComprarA(pelicula));
	}
}
