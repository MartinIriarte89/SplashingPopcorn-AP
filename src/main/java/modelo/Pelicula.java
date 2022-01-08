package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import utilidades.Comparador;
import utilidades.Patron;

public class Pelicula implements Sugerencia, Comparable<Pelicula> {

	private int id;
	private String titulo;
	private double precio;
	private int duracion;
	private int stock;
	private Genero genero;
	private String urlFondo;
	private String urlPortada;
	private String descripcion;
	private int anioLanzamiento;
	private String lema;
	private Map<String, String> errores;

	public Pelicula(String titulo, double precio, int duracion, int stock, Genero genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		this.titulo = titulo;
		this.precio = precio;
		this.duracion = duracion;
		this.stock = stock;
		this.genero = genero;
		setDescripcion(descripcion);
		this.urlPortada = urlPortada == null ? "" : urlPortada;
		this.urlFondo = urlFondo == null ? "" : urlFondo;
		this.anioLanzamiento = anioLanzamiento;
		setLema(lema);
	}

	public Pelicula(int id, String titulo, double precio, int duracion, int stock, Genero genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		this.titulo = titulo;
		this.precio = precio;
		this.duracion = duracion;
		this.stock = stock;
		this.genero = genero;
		this.id = id;
		setDescripcion(descripcion);
		setUrlPortada(urlPortada);
		setUrlFondo(urlFondo);
		this.anioLanzamiento = anioLanzamiento;
		setLema(lema);
	}

	public Map<String, String> getErrores() {
		return this.errores;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public String getTitulo() {
		return this.titulo;
	}

	@Override
	public double getPrecio() {
		return this.precio;
	}

	@Override
	public int getDuracion() {
		return this.duracion;
	}

	@Override
	public int getStock() {
		return this.stock;
	}

	@Override
	public Genero getGenero() {
		return this.genero;
	}

	@Override
	public String getUrlFondo() {
		return this.urlFondo;
	}

	@Override
	public String getUrlPortada() {
		return this.urlPortada;
	}

	@Override
	public String getDescripcion() {
		return this.descripcion;
	}

	public int getAnioLanzamiento() {
		return this.anioLanzamiento;
	}

	public String getLema() {
		return lema;
	}

	public void setLema(String lema) {
		this.lema = lema == null ? "" : lema;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public void setUrlFondo(String urlFondo) {
		this.urlFondo = urlFondo == null ? this.urlFondo : urlFondo;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada == null ? this.urlPortada : urlPortada;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion;
	}

	public void setAnioLanzamiento(int anioLanzamiento) {
		this.anioLanzamiento = anioLanzamiento;
	}

	public void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public void restarStock() {
		this.stock--;
	}

	@Override
	public boolean tieneStock() {
		return this.getStock() > 0;
	}

	public boolean esNulo() {
		return false;
	}

	@Override
	public boolean noEstaIncluidaEn(ArrayList<Pelicula> peliculasCompradas) {
		return !peliculasCompradas.contains(this);
	}

	@Override
	public int compareTo(Pelicula o) {
		return -Double.compare(this.precio, o.precio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(stock, duracion, id, titulo, precio, genero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		return stock == other.stock && Double.doubleToLongBits(duracion) == Double.doubleToLongBits(other.duracion)
				&& id == other.id && Objects.equals(titulo, other.titulo) && precio == other.precio
				&& Objects.equals(genero, other.genero);
	}

	@Override
	public boolean esValida() {
		validar();
		return errores.isEmpty();
	}

	@Override
	public void validar() {
		errores = new HashMap<String, String>();

		if (precio <= 0) {
			errores.put("precio", "Debe ser mayor a 0");
		}
		if (duracion <= 0) {
			errores.put("duracion", "Debe ser mayor a 0");
		}
		if (duracion > 240) {
			errores.put("duracion", "Excede el tiempo máximo");
		}
		if (stock < 0) {
			errores.put("stock", "Debe ser positivo");
		}

		if (precio == Patron.NUMERO_NO_VALIDO) {
			errores.put("precio", "Formato incorrecto, debe ser un número");
		}

		if (duracion == Patron.NUMERO_NO_VALIDO) {
			errores.put("duracion", "Formato incorrecto, debe ser un número");
		}

		if (stock == Patron.NUMERO_NO_VALIDO) {
			errores.put("stock", "Formato incorrecto, debe ser un número");
		}

		if (anioLanzamiento < 1895) {
			errores.put("anioLanzamiento", "Debe ser posterior a 1895");
		}

		if (anioLanzamiento == Patron.NUMERO_NO_VALIDO) {
			errores.put("anioLanzamiento", "Formato incorrecto, debe ser un número");
		}
		if (titulo == null) {
			errores.put("titulo", "El campo no puede estar vacío");
		}
		if (titulo != null) {
			if (titulo.length() < 2) {
				errores.put("titulo", "Debe contener al menos dos caracteres");
			}
			if (!Comparador.comparar(titulo, Patron.SIN_CARACTERES_ESPECIALES)) {
				errores.put("titulo", "No puede contener caracteres especiales");
			}
		}

		if (!Comparador.comparar(descripcion, Patron.NULO_SIN_CARACTERES_ESPECIALES)) {
			errores.put("descripcion", "No puede contener caracteres especiales");
		}

		if (!Comparador.comparar(lema, Patron.NULO_SIN_CARACTERES_ESPECIALES)) {
			errores.put("lema", "No puede contener caracteres especiales");
		}

		if (genero.esNulo()) {
			errores.put("genero", "El genero debe ser válido");
		}
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", lema=" + lema + "]";
	}

}
