package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Camaras;

public class RepositorioPracticas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioPracticas instance = new RepositorioPracticas();

	public static RepositorioPracticas getInstance() {
		return instance;
	}

}
