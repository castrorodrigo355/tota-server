package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Camaras;

public class RepositorioCamaras implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioCamaras instance = new RepositorioCamaras();

	public static RepositorioCamaras getInstance() {
		return instance;
	}

	public void addCamara(Camaras camara) {
		withTransaction(() -> {
			entityManager().persist(camara);
		});
	}
	
	public List<Camaras> getAllCamaras() {
		return withTransaction(() -> {
			return entityManager().createQuery("from Camaras", Camaras.class).getResultList();
		});
	}

	public Camaras getCamaraById(int id) {
		return entityManager().find(Camaras.class, id);
	}
	
	public void eliminarCamara(Camaras camara) {
		withTransaction(() -> {
			entityManager().remove(camara);
		});
	}

	public void modificarCamara(Camaras camara) {
		withTransaction(() -> {
			entityManager().persist(camara);
		});
	}

}
