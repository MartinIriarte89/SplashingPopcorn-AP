package persistencia;

public class DatosPerdidosError extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7730113530431037650L;
	
	public DatosPerdidosError(Exception e) {
		super(e);
	}

}
