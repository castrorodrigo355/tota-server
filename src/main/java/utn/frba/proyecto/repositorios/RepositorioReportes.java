package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Reportes;

public class RepositorioReportes implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioReportes instance = new RepositorioReportes();

	public static RepositorioReportes getInstance() {
		return instance;
	}

	public List<Reportes> getAllReportes() {
		return withTransaction(() -> {
			return entityManager().createQuery("from Reportes", Reportes.class).getResultList();
		});
	}

}