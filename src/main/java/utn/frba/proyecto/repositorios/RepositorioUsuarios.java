package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Usuarios;

public class RepositorioUsuarios implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioUsuarios instance = new RepositorioUsuarios();

	public void agregarUsuario(Usuarios usuario) {
		withTransaction(() -> {
			entityManager().persist(usuario);
		});
	}

	public static RepositorioUsuarios getInstance() {
		return instance;
	}

	public List<Usuarios> getAllUsers() {
		List<Usuarios> usuarios = entityManager().createQuery("from Usuarios", Usuarios.class).getResultList();
		usuarios.forEach(usuario -> entityManager().refresh(usuario));
		return usuarios;
	}

	public void modifyUser(Usuarios usuario) {
		withTransaction(() -> {
			entityManager().persist(usuario);
		});
	}

	public Usuarios getUserById(int id) {
		return entityManager().find(Usuarios.class, id);
	}

	public Usuarios getUserByCredentials(String email, String password) {
		return entityManager()
				.createQuery("from Usuarios U where U.email = :email and U.password = :password", Usuarios.class)
				.setParameter("email", email).setParameter("password", password).getSingleResult();
	}

	public Usuarios removeUser(Usuarios usuario) {
		withTransaction(() -> {
			entityManager().remove(usuario);
		});
		return usuario;
	}

	public Usuarios getUserByEmail(String email) {
		return entityManager().createQuery("from Usuarios U where U.email = :email", Usuarios.class)
				.setParameter("email", email).getSingleResult();
	}

}