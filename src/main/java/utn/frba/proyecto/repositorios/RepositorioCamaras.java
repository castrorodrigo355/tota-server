package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Televisores;

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
		List<Camaras> camaras = entityManager().createQuery("from Camaras", Camaras.class).getResultList();
		camaras.stream().forEach(camara -> entityManager().refresh(camara));
		return camaras;
	}

	public void agregarTelevisor(Camaras camara, Televisores televisor) {
		withTransaction(() -> {
			camara.agregarTelevisor(televisor);
		});
	}
	
	public void quitarTelevisor(Camaras camara, Televisores televisor) {
		withTransaction(() -> {
			camara.quitarTelevisor(televisor);
		});
	}
	
	public Camaras modificarCamara(int id, String ipdir, String endpoint) {
		return withTransaction(() -> {
			Camaras camara = entityManager().find(Camaras.class, id);
			camara.setIp_dir(ipdir);
			camara.setEndpoint(endpoint);
			return camara;
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

}
