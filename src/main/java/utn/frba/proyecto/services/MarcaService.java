package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioMarcas;

public class MarcaService {

	public List<Marcas> getMarcas() {
		return RepositorioMarcas.getInstance().getAllBrands();
	}

	public Marcas getMarca(int id) {
		return RepositorioMarcas.getInstance().getBrandById(id);
	}

	public Marcas crearMarca(String nombre, String descripcion) {
		Marcas marca = new Marcas(nombre, descripcion);
		RepositorioMarcas.getInstance().addBrand(marca);
		return marca;
	}

	public void eliminarMarca(Marcas marca) {
		RepositorioMarcas.getInstance().removeBrand(marca);
	}

	public Marcas modificarMarca(Marcas marca) {
		RepositorioMarcas.getInstance().modifyBrand(marca);
		return marca;
	}
}
