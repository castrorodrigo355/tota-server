package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Televisores;

public class RepositorioTelevisores implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioTelevisores instance = new RepositorioTelevisores();

	public static RepositorioTelevisores getInstance() {
		return instance;
	}

	public void addTelevisor(Televisores televisor) {
		withTransaction(() -> {
			entityManager().persist(televisor);
		});
	}

	public List<Televisores> getAllTelevisores() {
		List<Televisores> televisores = entityManager().createQuery("from Televisores", Televisores.class).getResultList();
		televisores.forEach(televisor -> entityManager().refresh(televisor));
		return televisores;
	}
	
	public Televisores getTelevisorById(int id) {
		return entityManager().find(Televisores.class, id);
	}
	
	public void removeTelevisor(Televisores televisor) {
		withTransaction(() -> {
			entityManager().remove(televisor);
		});
	}
	
	public Televisores modifyTelevisor(int id, String ipdir) {
		return withTransaction(() -> {
			Televisores televisor = entityManager().find(Televisores.class, id);
			televisor.setIp_dir(ipdir);
			return televisor;
		});
	}
}