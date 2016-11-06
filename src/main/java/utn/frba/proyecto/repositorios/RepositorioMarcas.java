package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Usuarios;

public class RepositorioMarcas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioMarcas instance = new RepositorioMarcas();

	public static RepositorioMarcas getInstance() {
		return instance;
	}

	public List<Marcas> listarMarcas() {
		List<Marcas> marcas = entityManager().createQuery("from Marcas", Marcas.class).getResultList();
		marcas.stream().forEach(marca -> entityManager().refresh(marca));
		return marcas;
	}

	public Marcas getMarca(int id) {
		Marcas marca = entityManager().find(Marcas.class, id);
		return marca;
	}
	
	public void agregarMarca(Marcas marca) {
		withTransaction(() -> {
			entityManager().persist(marca);
		});
	}

	public Marcas modificarMarca(int id, String nombre, String descripcion) {
		return withTransaction(() -> {
			Marcas marca = entityManager().find(Marcas.class, id);
			marca.setNombre(nombre);
			marca.setDescripcion(descripcion);
			return marca;
		});
	}

	public void quitarMarca(Marcas marca) {
		withTransaction(() -> {
			entityManager().remove(marca);
		});
	}

	public Marcas getMarcaByNombre(String nombre) {
		return entityManager().createQuery("from Marcas M where M.nombre = :nombre", Marcas.class)
				.setParameter("nombre", nombre).getSingleResult();
	}

	public Marcas getMarcaByUsuario(Usuarios usuario) {
		return entityManager().createQuery("from Marcas M where :usuario MEMBER OF M.usuarios", Marcas.class)
				.setParameter("usuario", usuario).getSingleResult();
	}

	public void agregarUsuario(Marcas marca, Usuarios usuario) {
		withTransaction(() -> {
			marca.agregarUsuario(usuario);
		});
	}

	public void quitarUsuario(Marcas marca, Usuarios usuario) {
		withTransaction(() -> {
			marca.quitarUsuario(usuario);
		});
	}
	
	public void agregarPublicidad(Marcas marca, Publicidades publicidad) {
		withTransaction(() -> {
			marca.agregarPublicidad(publicidad);
		});
	}

	public void quitarPublicidad(Marcas marca, Publicidades publicidad) {
		withTransaction(() -> {
			marca.quitarPublicidad(publicidad);
		});
	}

}