package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.repositorios.RepositorioMarcas;

public class MarcaService {

	public List<Marcas> listarMarcas() {
		return RepositorioMarcas.getInstance().listarMarcas();
	}

	public Marcas agregarMarca(String nombre, String descripcion) {
		Marcas marca = new Marcas(nombre, descripcion);
		RepositorioMarcas.getInstance().agregarMarca(marca);
		return marca;
	}

	public Marcas getMarcaById(int id) {
		return RepositorioMarcas.getInstance().getMarca(id);
	}
	
	public void quitarMarca(Marcas marca) {
		RepositorioMarcas.getInstance().quitarMarca(marca);
	}

	public Marcas modificarMarca(int id, String nombre, String descripcion) {
		return RepositorioMarcas.getInstance().modificarMarca(id, nombre, descripcion);
	}
	
}