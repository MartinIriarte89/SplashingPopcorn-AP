package modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import utilidades.Comparador;
import utilidades.Encriptacion;
import utilidades.Patron;

public class Usuario {

	private int id;
	private String nombre;
	private String usuario;
	private String contrasenaEncrip;
	private double dineroDisponible;
	private int tiempoDisponible;
	private Genero preferencia;
	private Itinerario itinerario;
	private boolean esAdmin;
	private String urlPerfil;
	private HashMap<String, String> errores;

	public Usuario(String nombre, String usuario, String contrasena, double dineroDisponible, int tiempoDisponible,
			Genero preferencia, String urlPerfil, boolean esAdmin) {
		this.nombre = nombre;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
		this.contrasenaEncrip = Encriptacion.hash(contrasena);
		this.urlPerfil = urlPerfil == null ? "" : urlPerfil;
		this.esAdmin = esAdmin;
		this.usuario = usuario;
	}

	public Usuario(int id, String nombre, String usuario, String contrasena, double dineroDisponible,
			int tiempoDisponible, Genero preferencia, String urlPerfil, boolean esAdmin) {
		this.id = id;
		this.nombre = nombre;
		this.dineroDisponible = dineroDisponible;
		this.tiempoDisponible = tiempoDisponible;
		this.preferencia = preferencia;
		this.contrasenaEncrip = contrasena;
		this.esAdmin = esAdmin;
		setUrlPerfil(urlPerfil);
		this.usuario = usuario;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public double getDineroDisponible() {
		return this.dineroDisponible;
	}

	public int getTiempoDisponible() {
		return this.tiempoDisponible;
	}

	public Genero getPreferencia() {
		return this.preferencia;
	}

	public Itinerario getItinerario() {
		return this.itinerario;
	}

	public String getUrlPerfil() {
		return this.urlPerfil;
	}

	public String getContrasena() {
		return this.contrasenaEncrip;
	}

	public Map<String, String> getErrores() {
		return this.errores;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public void setContrasena(String contrasena) {
		this.contrasenaEncrip = Encriptacion.hash(contrasena);
	}

	public void setDineroDisponible(double dineroDisponible) {
		this.dineroDisponible = dineroDisponible;
	}

	public void setTiempoDisponible(int tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public void setPreferencia(Genero preferencia) {
		this.preferencia = preferencia;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil == null ? this.urlPerfil : urlPerfil;
	}

	public void setItinerario(Itinerario itinerario) {
		this.itinerario = itinerario;
	}

	public boolean puedeComprarA(Sugerencia unaSugerencia) {
		boolean leAlcanzaDinero = this.dineroDisponible >= unaSugerencia.getPrecio();
		boolean leAlcanzaTiempo = this.tiempoDisponible >= unaSugerencia.getDuracion();
		boolean noSeCompro = this.itinerario.noTieneA(unaSugerencia);

		return leAlcanzaDinero && leAlcanzaTiempo && noSeCompro; // && tieneCupo;
	}

	public void comprar(Sugerencia unaSugerencia) {
		this.dineroDisponible -= unaSugerencia.getPrecio();
		this.tiempoDisponible -= unaSugerencia.getDuracion();
		this.itinerario.agregarLaCompraDe(unaSugerencia);
	}

	public boolean checkContrasena(String contrasena) {
		return Encriptacion.coincidencia(contrasena, this.contrasenaEncrip);
	}

	public boolean esNulo() {
		return false;
	}

	public boolean esAdmin() {
		return this.esAdmin;
	}

	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	private void validar() {
		errores = new HashMap<String, String>();

		if (dineroDisponible < 0) {
			errores.put("dinero", "No debe ser negativo");
		}
		if (tiempoDisponible < 0) {
			errores.put("tiempo", "No debe ser negativo");
		}
		if (dineroDisponible == Patron.NUMERO_NO_VALIDO) {
			errores.put("dinero", "Formato incorrecto, debe ser un número");
		}
		if (tiempoDisponible == Patron.NUMERO_NO_VALIDO) {
			errores.put("tiempo", "Formato incorrecto, debe ser un número");
		}
		if (contrasenaEncrip.equals("")) {
			errores.put("contrasena", "No debe contener caracteres especiales, y su longitud debe ser mayor a 4");
		}
		if (!Comparador.comparar(nombre, Patron.NOMBRE_APELLIDO_VALIDO)) {
			errores.put("nombre", "No debe contener caracteres especiales, ingrese nombre y apellido");
		}
		if (!Comparador.comparar(usuario, Patron.USUARIO_VALIDO)) {
			errores.put("usuario", "No debe contener caracteres especiales");
		}
		if (preferencia.esNulo()) {
			errores.put("genero", "El genero no existe");
		}
		if (nombre == null) {
			errores.put("nombre", "El campo no puede estar vacío");
		}
		if (usuario == null) {
			errores.put("usuario", "El campo no puede estar vacío");
		}
		if (preferencia == null) {
			errores.put("genero", "El campo no puede estar vacío");
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(dineroDisponible, id, itinerario, nombre, preferencia, tiempoDisponible);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return dineroDisponible == other.dineroDisponible && id == other.id
				&& Objects.equals(itinerario, other.itinerario) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(preferencia, other.preferencia)
				&& Double.doubleToLongBits(tiempoDisponible) == Double.doubleToLongBits(other.tiempoDisponible);
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + "]";
	}

}
