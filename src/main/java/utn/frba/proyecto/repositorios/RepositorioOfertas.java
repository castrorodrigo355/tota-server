package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import utn.frba.proyecto.entities.Ofertas;

public class RepositorioOfertas implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioOfertas instance = new RepositorioOfertas();

	public static RepositorioOfertas getInstance() {
		return instance;
	}
	
	public List<Ofertas> getAllOfertas() {
		return entityManager().createQuery("from Ofertas", Ofertas.class).getResultList();
	}
	
	public void modifyOferta(Ofertas oferta) {
		withTransaction(() -> {
			entityManager().persist(oferta);
		});
	}

	public void removeOferta(Ofertas oferta) {
		withTransaction(() -> {
			entityManager().remove(oferta);
		});
	}
	
	public Ofertas getOfertaById(int id) {
		return entityManager().find(Ofertas.class, id);
	}
	
	public void addOferta(Ofertas oferta) {
		withTransaction(() -> {
			entityManager().persist(oferta);
		});
	}

}