package persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Pelicula;
import modelo.objetoNulo.PeliculaNula;
import persistencia.commons.ProveedorDeConexion;

public class PeliculaDAO {


	public ArrayList<Pelicula> cargar() {
		String sql = "SELECT * FROM peliculas WHERE borrado_logico = 0";
		ArrayList<Pelicula> peliculas = new ArrayList<Pelicula>();

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			ResultSet resultados = declaracion.executeQuery();

			while (resultados.next()) {
				peliculas.add(crearPelicula(resultados));
			}
		} catch (Exception e) {
			throw new DatosPerdidosError(e);
		}
		return peliculas;
	}

	public boolean insertar(Pelicula pelicula) {
		String sql = "INSERT INTO peliculas (titulo, fecha_estreno, fk_genero, duracion_min, precio, stock, lema, descripcion, foto_portada, foto_fondo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setString(1, pelicula.getTitulo());
			declaracion.setInt(2, pelicula.getAnioLanzamiento());
			declaracion.setString(3, pelicula.getGenero());
			declaracion.setInt(4, pelicula.getDuracion());
			declaracion.setDouble(5, pelicula.getPrecio());
			declaracion.setInt(6, pelicula.getStock());
			declaracion.setString(7, pelicula.getLema());
			declaracion.setString(8, pelicula.getDescripcion());
			declaracion.setString(9, pelicula.getUrlPortada());
			declaracion.setString(10, pelicula.getUrlFondo());

			declaracion.executeUpdate();
			return true;

		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}

	public boolean borrar(Pelicula pelicula) {
		String sql = "UPDATE peliculas SET borrado_logico = 1 WHERE id = ?";
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setInt(1, pelicula.getId());
			declaracion.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
	}

	public int editar(Pelicula pelicula) {
		String sql = "UPDATE peliculas SET titulo = ?, precio = ?, duracion_min = ?, stock = ?, fk_genero = ?, descripcion = ?, foto_portada = ?, foto_fondo = ?, fecha_estreno = ?, lema = ? WHERE id = ?";
		int filasModificadas = 0;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setString(1, pelicula.getTitulo());
			declaracion.setDouble(2, pelicula.getPrecio());
			declaracion.setInt(3, pelicula.getDuracion());
			declaracion.setInt(4, pelicula.getStock());
			declaracion.setString(5, pelicula.getGenero());
			declaracion.setString(6, pelicula.getDescripcion());
			declaracion.setString(7, pelicula.getUrlPortada());
			declaracion.setString(8, pelicula.getUrlFondo());
			declaracion.setInt(9, pelicula.getAnioLanzamiento());
			declaracion.setString(10, pelicula.getLema());
			declaracion.setInt(11, pelicula.getId());
			
			filasModificadas = declaracion.executeUpdate();
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
		return filasModificadas;
	}

	public int actualizarStock(Pelicula pelicula) {
		String sql = "UPDATE peliculas SET stock = ? WHERE id = ?";
		int filasModificadas = 0;
		try {
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);

			declaracion.setInt(1, pelicula.getStock());
			declaracion.setInt(2, pelicula.getId());

			filasModificadas = declaracion.executeUpdate();
		} catch (SQLException e) {
			throw new DatosPerdidosError(e);
		}
		return filasModificadas;
	}


	public Pelicula buscarPor(int id) {
		Pelicula pelicula;
		try {
			String sql = "SELECT * FROM peliculas WHERE id = ? and borrado_logico = 0";
			Connection conexion = ProveedorDeConexion.getConexion();
			PreparedStatement declaracion = conexion.prepareStatement(sql);
			declaracion.setInt(1, id);

			ResultSet resultados = declaracion.executeQuery();

			pelicula = PeliculaNula.construir();

			if (resultados.next()) {
				pelicula = crearPelicula(resultados);
			}
		} catch (Exception e) {
			throw new DatosPerdidosError(e);

		}
		return pelicula;
	}

	private Pelicula crearPelicula(ResultSet resultados) throws Exception {
		int id = resultados.getInt("id");
		String titulo = resultados.getString("titulo");
		int precio = resultados.getInt("precio");
		int duracion = resultados.getInt("duracion_min");
		int stock = resultados.getInt("stock");
		String genero = resultados.getString("fk_genero");
		String descripcion = resultados.getString("descripcion");
		String urlPortada = resultados.getString("foto_portada");
		String urlBackdrop = resultados.getString("foto_fondo");
		int anioLanzamiento = resultados.getInt("fecha_estreno");
		String lema = resultados.getString("lema");

		return new Pelicula(id, titulo, precio, duracion, stock, genero, descripcion, urlPortada, urlBackdrop,
				anioLanzamiento, lema);
	}
}
