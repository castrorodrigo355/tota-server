package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Televisores;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.entities.Usuarios;

public class RepositorioUbicaciones implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioUbicaciones instance = new RepositorioUbicaciones();

	public static RepositorioUbicaciones getInstance() {
		return instance;
	}
	
	public List<Ubicaciones> getAllUbicaciones() {
		return entityManager().createQuery("from Ubicaciones", Ubicaciones.class).getResultList();
	}
	
	public void modifyUbicacion(Ubicaciones ubicacion) {
		withTransaction(() -> {
			entityManager().persist(ubicacion);
		});
	}

	public void removeUbicacion(Ubicaciones ubicacion) {
		withTransaction(() -> {
			entityManager().remove(ubicacion);
		});
	}
	
	public void agregarCamaraAUbicacion(Ubicaciones ubicacion, Camaras camara) {
		withTransaction(() -> {
			ubicacion.agregarCamara(camara);
		});
	}
	
	public void agregarTelevisorAUbicacion(Ubicaciones ubicacion, Televisores televisor) {
		withTransaction(() -> {
			ubicacion.agregarTelevisor(televisor);
		});
	}
	
	public void quitarCamaraAUbicacion(Ubicaciones ubicacion, Camaras camara) {
		withTransaction(() -> {
			ubicacion.quitarCamara(camara);
		});
	}
	
	public void quitarTelevisorAUbicacion(Ubicaciones ubicacion, Televisores televisor) {
		withTransaction(() -> {
			ubicacion.quitarTelevisor(televisor);
		});
	}
	
	public Ubicaciones getUbicacionById(int ubicacion_id) {
		return entityManager().find(Ubicaciones.class, ubicacion_id);
	}
	
	public void agregarUbicacion(Ubicaciones ubicacion) {
		withTransaction(() -> {
			entityManager().persist(ubicacion);
		});
	}

}
