package modelo;

import java.util.HashMap;
import java.util.Objects;

import utilidades.Comparador;
import utilidades.Patron;

public class Genero {

	private String nombre;
	private HashMap<String, String> errores = new HashMap<String,String>();

	public Genero(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public boolean esNulo() {
		return false;
	}
	
	public HashMap<String, String> getErrores() {
		return this.errores;
	}
	
	public boolean esValido() {
		validar();
		return errores.isEmpty();
	}

	private void validar() {
		if(nombre == null) {
			errores.put("nombre", "El campo no puede estar vacio.");
		}else {
			if(!Comparador.comparar(nombre, Patron.SIN_CARACTERES_ESPECIALES)) {
				errores.put("nombre", "El campo no puede estar vacio o contener caracteres especiales.");
			}
		}
		if(this.esNulo()) {
			errores.put("nulo", "No se encontró");
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genero other = (Genero) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
