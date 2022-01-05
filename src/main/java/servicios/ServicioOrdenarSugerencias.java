package servicios;

import java.util.Comparator;

import modelo.Genero;
import modelo.Sugerencia;

public class ServicioOrdenarSugerencias implements Comparator<Sugerencia> {

	private Genero usuarioTipo;

	public ServicioOrdenarSugerencias(Genero usuarioTipo) {
		this.usuarioTipo = usuarioTipo;
	}

	@Override
	public int compare(Sugerencia o1, Sugerencia o2) {
		int resultado;
		Genero o1Tipo = o1.getGenero();
		Genero o2Tipo = o2.getGenero();

		resultado = -Boolean.compare(o1.esPromocion(), o2.esPromocion());
		if (resultado == 0)
			resultado = o1Tipo.equals(usuarioTipo) && !o2Tipo.equals(usuarioTipo) ? -1
					: !o1Tipo.equals(usuarioTipo) && o2Tipo.equals(usuarioTipo) ? 1 : 0;
		if (resultado == 0)
			resultado = -Double.compare(o1.getPrecio(), o2.getPrecio());
		if (resultado == 0)
			resultado = -Integer.compare(o1.getDuracion(), o2.getDuracion());
		return resultado;
	}
}