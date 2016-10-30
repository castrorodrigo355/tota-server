package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Marcas;

public class RepositorioPracticas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioPracticas instance = new RepositorioPracticas();

	public static RepositorioPracticas getInstance() {
		return instance;
	}

	public List<Marcas> getAllBrands() {
		List<Marcas> marcas = entityManager().createQuery("from Marcas", Marcas.class).getResultList();
		marcas.forEach(marca -> entityManager().refresh(marca));
		return marcas;
	}
	
}
