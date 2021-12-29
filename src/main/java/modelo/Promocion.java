package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Promocion implements Sugerencia {

	private int id;
	private String titulo;
	private ArrayList<Pelicula> peliculas;
	private String genero;
	private String descripcion;
	private String urlPortada;
	private Map<String, String> errores;

	public Promocion(String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada) {
		this.titulo = titulo;
		if (peliculas != null) {
			this.genero = peliculas.get(0).getGenero();
		}
		this.peliculas = peliculas;
		this.descripcion = descripcion;
		this.urlPortada = urlPortada;
	}

	public Promocion(int id, String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada) {
		this.titulo = titulo;
		if (peliculas != null) {
			this.genero = peliculas.get(0).getGenero();
		}
		this.peliculas = peliculas;
		this.id = id;
		this.descripcion = descripcion;
		this.urlPortada = urlPortada;
	}

	public ArrayList<Pelicula> getPeliculas() {
		return this.peliculas;
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
		double precio = 0;
		for (Pelicula atraccion : peliculas) {
			precio += atraccion.getPrecio();
		}
		return precio;
	}

	@Override
	public int getDuracion() {
		int duracion = 0;
		for (Pelicula pelicula : peliculas) {
			duracion += pelicula.getDuracion();
		}
		return duracion;
	}

	@Override
	public int getStock() {
		int stock = peliculas.get(0).getStock();
		for (Pelicula pelicula : peliculas) {
			stock = pelicula.getStock() < stock ? pelicula.getStock() : stock;
		}
		return stock;
	}

	@Override
	public String getGenero() {
		return genero;
	}

	@Override
	public String getUrlFondo() {
		return "";
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
		return 0;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada;
	}

	@Override
	public boolean esPromocion() {
		return true;
	}

	public boolean esNulo() {
		return false;
	}

	@Override
	public void restarStock() {
		for (Pelicula pelicula : peliculas) {
			pelicula.restarStock();
		}
	}

	@Override
	public boolean tieneStock() {
		return getStock() > 0;
	}

	@Override
	public boolean noEstaIncluidaEn(ArrayList<Pelicula> peliculasCompradas) {
		boolean noEstaIncluida = true;
		for (Pelicula pelicula : peliculas) {
			if (peliculasCompradas.contains(pelicula)) {
				noEstaIncluida = false;
				break;
			}
		}
		return noEstaIncluida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(peliculas, id, titulo, genero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		return Objects.equals(peliculas, other.peliculas) && id == other.id && Objects.equals(titulo, other.titulo)
				&& Objects.equals(genero, other.genero);
	}

	public Map<String, String> getErrores() {
		return errores;
	}

	@Override
	public boolean esValida() {
		validar();
		return errores.isEmpty();
	}

	@Override
	public void validar() {

		errores = new HashMap<String, String>();

		for (Pelicula pelicula : peliculas) {
			if (!pelicula.esValida()) {// no sé si vale la pena identificar cada error de las pelis
				errores.put("", "Los datos de la película no son válidos");
			}
		}
		if (!this.generosIguales(peliculas)) {
			errores.put("peliculas", "Los géneros de las película deben ser iguales");
		}
	}

	// SIRVE PARA VALIDAR QUE TODAS LAS PELIS PERTENEZCAN AL MISMO GÉNERO
	public boolean generosIguales(ArrayList<Pelicula> peliculas) {

		for (int i = 0; i < peliculas.size(); i++) {

			if (peliculas.get(i).getGenero().equals(peliculas.get(i++).getGenero())) {
				return true;
			}
		}
		return false;
	}
}