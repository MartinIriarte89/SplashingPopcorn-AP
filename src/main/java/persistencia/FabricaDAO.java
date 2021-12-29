package persistencia;

public class FabricaDAO {

	private static PeliculaDAO peliculaDAO = new PeliculaDAO();
	private static PromocionDAO promocionDAO = new PromocionDAO();
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	private static ItinerarioDAO itinerarioDAO = new ItinerarioDAO();
	private static GeneroDAO generoDAO = new GeneroDAO();

	public static PeliculaDAO getPeliculaDAO() {
		return peliculaDAO;
	}

	public static PromocionDAO getPromocionDAO() {
		return promocionDAO;
	}

	public static UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	public static ItinerarioDAO getItinerarioDAO() {
		return itinerarioDAO;
	}
	
	public static GeneroDAO getGeneroDAO() {
		return generoDAO;
	}
}
