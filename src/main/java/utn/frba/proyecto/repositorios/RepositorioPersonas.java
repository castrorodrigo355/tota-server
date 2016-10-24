package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Personas;

public class RepositorioPersonas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioPersonas instance = new RepositorioPersonas();

	public static RepositorioPersonas getInstance() {
		return instance;
	}

	public List<Personas> getAllPersonas() {
		return entityManager().createQuery("from Personas", Personas.class).getResultList();
	}

	public Personas getPersonaById(int id) {
		return entityManager().find(Personas.class, id);
	}

	public void addPersona(Personas p) {
		withTransaction(() -> {
			entityManager().persist(p);
		});
	}

	public void removePersona(Personas p) {
		withTransaction(() -> {
			entityManager().remove(p);
		});
	}

	public void modifyPersona(Personas p) {
		withTransaction(() -> {
			entityManager().persist(p);
		});
	}

}