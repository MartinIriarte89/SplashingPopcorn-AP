package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Pelicula implements Sugerencia, Comparable<Pelicula> {

	private int id;
	private String titulo;
	private double precio;
	private int duracion;
	private int stock;
	private String genero;
	private String urlFondo;
	private String urlPortada;
	private String descripcion;
	private int anioLanzamiento;
	private String lema;
	private Map<String, String> errores;

	public Pelicula(String titulo, double precio, int duracion, int stock, String genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		this.titulo = titulo;
		this.precio = precio;
		this.duracion = duracion;
		this.stock = stock;
		this.genero = genero;
		this.descripcion = descripcion;
		this.urlPortada = urlPortada;
		this.urlFondo = urlFondo;
		this.anioLanzamiento = anioLanzamiento;
		this.lema = lema;
	}

	public Pelicula(int id, String titulo, double precio, int duracion, int stock, String genero, String descripcion,
			String urlPortada, String urlFondo, int anioLanzamiento, String lema) {
		this.titulo = titulo;
		this.precio = precio;
		this.duracion = duracion;
		this.stock = stock;
		this.genero = genero;
		this.id = id;
		this.descripcion = descripcion;
		this.urlPortada = urlPortada;
		this.urlFondo = urlFondo;
		this.anioLanzamiento = anioLanzamiento;
		this.lema = lema;
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
	public String getGenero() {
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

	@Override
	public int getAnioLanzamiento() {
		return this.anioLanzamiento;
	}

	public String getLema() {
		return lema;
	}

	public void setLema(String lema) {
		this.lema = lema;
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

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setUrlFondo(String urlFondo) {
		this.urlFondo = urlFondo;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

		if (precio < 0) {
			errores.put("precio", "Debe ser positivo");
		}
		if (duracion < 0) {
			errores.put("duracion", "Debe ser positivo");
		}
		if (duracion > 240) {
			errores.put("duracion", "Excede el tiempo máximo");
		}
		if (stock < 0) {
			errores.put("stock", "Debe ser positivo");
		}
		if (anioLanzamiento < 1500) {
			errores.put("anioLanzamiento", "Debe ser posterior a 1500");
		}
	}

	@Override
	public String toString() {
		return "Pelicula [id=" + id + ", titulo=" + titulo + ", lema=" + lema + "]";
	}

}
