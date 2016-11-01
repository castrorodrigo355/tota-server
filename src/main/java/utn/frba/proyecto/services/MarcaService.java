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

//		// Necesito borrar la referencia a la marca en usuarios
//		List<Usuarios> usuarios = RepositorioUsuarios.getInstance().getAllUsers();
//		usuarios.stream().filter(usuario -> marca.equals(usuario.getMarca()))
//				.forEach(usuario -> RepositorioUsuarios.getInstance().modifyUser(usuario.getId(), usuario.getNombre(),
//						usuario.getApellido(), usuario.getPassword(), usuario.getEmail(), null));

		// Necesito borrar la referencia a la marca en publicidades

		RepositorioMarcas.getInstance().quitarMarca(marca);
	}

	public Marcas modificarMarca(int id, String nombre, String descripcion) {
		return RepositorioMarcas.getInstance().modificarMarca(id, nombre, descripcion);
	}

}