package modelo;

import java.util.ArrayList;

public interface Sugerencia {

	public int getId();
	
	public String getTitulo();

	public double getPrecio();

	public int getDuracion();

	public int getStock();

	public Genero getGenero();

	public boolean esPromocion();

	public void restarStock();
	
	public String getUrlFondo();
	
	public String getUrlPortada();
	
	public String getDescripcion();
	
	public boolean tieneStock();

	public boolean noEstaIncluidaEn(ArrayList<Pelicula> atraccionesCompradas);
	
	public boolean esValida();
	
	public void validar();
}
