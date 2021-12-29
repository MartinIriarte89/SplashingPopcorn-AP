package modelo.objetoNulo;

import modelo.Usuario;

public class UsuarioNulo extends Usuario {

	public UsuarioNulo() {
		super(0, "", "", "", 0, 0, "", "", false);
	}

	public static Usuario construir() {
		return new UsuarioNulo();
	}

	public boolean esNulo() {
		return true;
	}
}
