package daoTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import persistencia.FabricaDAO;
import persistencia.ItinerarioDAO;
import persistencia.PeliculaDAO;
import persistencia.PromocionDAO;
import persistencia.UsuarioDAO;

public class FabricaDAOTest {

	@Test
	public void getAtraccionDAOTest() {
		PeliculaDAO atraccionDAO = FabricaDAO.getPeliculaDAO();
		PeliculaDAO otroAtraccionDAO = FabricaDAO.getPeliculaDAO();
		
		assertNotNull(atraccionDAO);
		assertSame(atraccionDAO, otroAtraccionDAO);
	}
	
	@Test
	public void getPromocionDAOTest() {
		PromocionDAO promocionDAO = FabricaDAO.getPromocionDAO();
		PromocionDAO otroPromocionDAO = FabricaDAO.getPromocionDAO();
		
		assertNotNull(promocionDAO);
		assertSame(promocionDAO, otroPromocionDAO);
	}
	
	@Test
	public void getUsuarioDAOTest() {
		UsuarioDAO usuarioDAO = FabricaDAO.getUsuarioDAO();
		UsuarioDAO otroUsuarioDAO = FabricaDAO.getUsuarioDAO();
		
		assertNotNull(usuarioDAO);
		assertSame(usuarioDAO, otroUsuarioDAO);
	}
	
	@Test
	public void getItinerarioDAOTest() {
		ItinerarioDAO itinerarioDAO = FabricaDAO.getItinerarioDAO();
		ItinerarioDAO otroItinerarioDAO = FabricaDAO.getItinerarioDAO();
		
		assertNotNull(itinerarioDAO);
		assertSame(itinerarioDAO, otroItinerarioDAO);
	}
}
