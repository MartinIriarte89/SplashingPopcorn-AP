package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import utilidades.Comparador;
import utilidades.Patron;

public abstract class Promocion implements Sugerencia {

	private int id;
	private String titulo;
	private ArrayList<Pelicula> peliculas;
	private Genero genero;
	private String descripcion;
	private String urlPortada;
	private Map<String, String> errores;
	private double beneficio;
	private String tipoPromocion;

	public Promocion(String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada,
			double beneficio, String tipoPromocion) {
		this.titulo = titulo;
		if (peliculas != null) {
			this.genero = peliculas.get(0).getGenero();
		}
		this.peliculas = peliculas;
		setDescripcion(descripcion);
		this.urlPortada = urlPortada == null ? "" : urlPortada;
		this.beneficio = beneficio;
		this.tipoPromocion = tipoPromocion;
	}

	public Promocion(int id, String titulo, ArrayList<Pelicula> peliculas, String descripcion, String urlPortada,
			double beneficio, String tipoPromocion) {
		this.titulo = titulo;
		if (peliculas != null) {
			this.genero = peliculas.get(0).getGenero();
		}
		this.peliculas = peliculas;
		this.id = id;
		setDescripcion(descripcion);
		setUrlPortada(urlPortada);
		this.beneficio = beneficio;
		this.tipoPromocion = tipoPromocion;
	}

	public ArrayList<Pelicula> getPeliculas() {
		return this.peliculas;
	}

	public double getBeneficio() {
		return this.beneficio;
	}

	public String getTipoPromocion() {
		return this.tipoPromocion;
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
	public Genero getGenero() {
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

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPeliculas(ArrayList<Pelicula> peliculas) {
		this.peliculas = peliculas;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion == null ? "" : descripcion;
	}

	public void setUrlPortada(String urlPortada) {
		this.urlPortada = urlPortada == null ? this.urlPortada : urlPortada;
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
		return getErrores().isEmpty();
	}

	@Override
	public void validar() {
		errores = new HashMap<String, String>();

		if (!this.sonPeliculasGenerosIguales()) {
			errores.put("peliculas", "Los géneros de las película deben coincidir");
		}

		if (peliculas.isEmpty()) {
			errores.put("peliculas", "Debe contar por lo menos con una película");
		}

		if (!sonPeliculasValidas()) {
			errores.put("peliculas", "Una o mas películas no existen");
		}
		if (titulo.length() < 2) {
			errores.put("titulo", "Debe contener al menos dos caracteres");
		}
		if (!Comparador.comparar(titulo, Patron.SIN_CARACTERES_ESPECIALES)) {
			errores.put("titulo", "No puede contener caracteres especiales");
		}
		if (!Comparador.comparar(descripcion, Patron.NULO_SIN_CARACTERES_ESPECIALES)) {
			errores.put("descripcion", "No puede contener caracteres especiales");
		}
		if (beneficio == Patron.NUMERO_NO_VALIDO) {
			errores.put("beneficio", "Formato incorrecto, debe ser un número");
		}
		if (tipoPromocion.equals("absoluta")) {
			if (beneficio < 0) {
				errores.put("beneficio", "Debe ser mayor a 0");
			}
		}
		if (tipoPromocion.equals("porcentual")) {
			if (beneficio < 0 || beneficio > 100) {
				errores.put("beneficio", "El valor debe ser entre 0 y 100");
			}
		}
		if (tipoPromocion.equals("axb")) {
			if (beneficio < 0) {
				errores.put("beneficio", "Debe ser mayor a 0");
			}
			if (beneficio > peliculas.size()) {
				errores.put("beneficio", "No puede ser mayor a la cantidad de películas en promoción");
			}
		}
		if(!tipoPromocion.equals("porcentual") && !tipoPromocion.equals("absoluta") && !tipoPromocion.equals("axb")) {
			errores.put("tipoPromocion", "No existe este tipo de promoción");
		}
		if(titulo == null) {
			errores.put("titulo", "El campo no puede estar vacío");
		}
		if(tipoPromocion == null) {
			errores.put("titulo", "El campo no puede estar vacío");
		}
	}

	// SIRVE PARA VALIDAR QUE TODAS LAS PELIS PERTENEZCAN AL MISMO GÉNERO
	private boolean sonPeliculasGenerosIguales() {
		for (int i = 0; i < peliculas.size() - 1; i++) {
			if (!peliculas.get(i).getGenero().equals(peliculas.get(i++).getGenero())) {
				return false;
			}
		}
		return true;
	}

	private boolean sonPeliculasValidas() {
		boolean sonValidas = true;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.esNulo())
				sonValidas = false;
		}
		return sonValidas;
	}

	@Override
	public String toString() {
		return "Promocion [id=" + id + ", titulo=" + titulo + "]";
	}

	public String getIdPelis() {
		String idPelis = "";

		for (int i = 0; i < peliculas.size(); i++) {
			if (i == 0) {
				idPelis = peliculas.get(i).getId() + "";
			} else
				idPelis += "," + peliculas.get(i).getId();
		}
		return idPelis;
	}

	public void setTipoPromocion(String tipoPromocion) {
		this.tipoPromocion = tipoPromocion;
	}

	public void setBeneficio(double beneficio) {
		this.beneficio = beneficio;
	}

	protected void setErrores(Map<String, String> errores) {
		this.errores = errores;
	}
}