package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Marcas;

public class RepositorioMarcas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioMarcas instance = new RepositorioMarcas();

	public static RepositorioMarcas getInstance() {
		return instance;
	}

	public List<Marcas> getAllBrands() {
		return entityManager().createQuery("from Marcas", Marcas.class).getResultList();
	}

	public Marcas getBrandById(int id) {
		return entityManager().find(Marcas.class, id);
	}

	public void addBrand(Marcas marca) {
		withTransaction(() -> {
			entityManager().persist(marca);
		});
	}

	public void removeBrand(Marcas marca) {
		withTransaction(() -> {
			entityManager().remove(marca);
		});
	}

	public void modifyBrand(Marcas marca) {
		withTransaction(() -> {
			entityManager().persist(marca);
		});
	}

}
