package utn.frba.proyecto.services;

import java.util.List;
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.repositorios.RepositorioOfertas;

public class OfertaService {

	public List<Ofertas> getOfertas() {
		return RepositorioOfertas.getInstance().getAllOfertas();
	}

	public Ofertas getOferta(int id) {
		return RepositorioOfertas.getInstance().getOfertaById(id);
	}

	public Ofertas crearOferta(String descripcion, Publicidades publicidad) {
		Ofertas oferta = new Ofertas(descripcion);
		RepositorioOfertas.getInstance().addOferta(oferta);
		return oferta;
	}

	public void eliminarOferta(Ofertas oferta) {
		RepositorioOfertas.getInstance().removeOferta(oferta);
	}

	public Ofertas modificarOferta(Ofertas oferta) {
		RepositorioOfertas.getInstance().modifyOferta(oferta);
		return oferta;
	}
}
