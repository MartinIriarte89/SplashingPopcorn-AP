package servicios;

import java.util.ArrayList;
import java.util.HashMap;

import modelo.Usuario;
import persistencia.FabricaDAO;
import persistencia.UsuarioDAO;

public class ServicioUsuario {

	UsuarioDAO usuarioDao = FabricaDAO.getUsuarioDAO();
	private HashMap<String, String> errores;	

	public ArrayList<Usuario> listar(){
		ArrayList<Usuario> usuarios = usuarioDao.cargar();
		return usuarios;
	}
	
	public boolean eliminar(int id) {
		Usuario usuario = usuarioDao.buscarPorId(id);
		boolean esOperacionCorrec = usuarioDao.borrar(usuario);

		return esOperacionCorrec;
	}

	public Usuario crear(String nombre, String usuario, String contrasena, double dineroDisponible,
			int tiempoDisponible, String preferencia, String urlPerfil, boolean esAdmin) {
		Usuario nuevoUsuario = new Usuario(nombre, usuario, contrasena, dineroDisponible, tiempoDisponible, preferencia,
				urlPerfil, esAdmin);

		if (nuevoUsuario.esValido()) {
			usuarioDao.insertar(nuevoUsuario);
		}
		return nuevoUsuario;
	}

	public Usuario editar(int id, String nombre, String usuario, String contrasena, double dineroDisponible,
			int tiempoDisponible, String preferencia, String urlPerfil, boolean esAdmin) {
		Usuario usuarioBBDD = usuarioDao.buscarPorId(id);
		
		usuarioBBDD.setNombre(nombre);
		usuarioBBDD.setUsuario(usuario);
		usuarioBBDD.setContrasena(contrasena);
		usuarioBBDD.setDineroDisponible(dineroDisponible);
		usuarioBBDD.setTiempoDisponible(tiempoDisponible);
		usuarioBBDD.setPreferencia(preferencia);
		usuarioBBDD.setUrlPerfil(urlPerfil);
		usuarioBBDD.setEsAdmin(esAdmin);
		
		if(usuarioBBDD.esValido()){
			usuarioDao.actualizar(usuarioBBDD);
		}
		
		return usuarioBBDD;
	}

	public Usuario buscar(int id) {
		return usuarioDao.buscarPorId(id);
	}
	
	public boolean sonDatosValidos() {
		validarDatos();
		return errores.isEmpty();
	}
	
	//VALIDAR DATOS
	private void validarDatos() {
		errores = new HashMap<String, String>();	
	}
}