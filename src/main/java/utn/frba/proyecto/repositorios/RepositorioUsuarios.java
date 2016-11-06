package utn.frba.proyecto.repositorios;

import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.utils.PasswordUtil;

public class RepositorioUsuarios implements WithGlobalEntityManager, TransactionalOps {

	private static RepositorioUsuarios instance = new RepositorioUsuarios();

	public static RepositorioUsuarios getInstance() {
		return instance;
	}

	public void agregarUsuario(Usuarios usuario) {
		withTransaction(() -> {
			entityManager().persist(usuario);
		});
	}

	public List<Usuarios> getAllUsers() {
		List<Usuarios> usuarios = entityManager().createQuery("from Usuarios", Usuarios.class).getResultList();
		usuarios.stream().forEach(usuario -> entityManager().refresh(usuario));
		return usuarios;
	}
	
	public List<Usuarios> getAllUsers(Usuarios usuario) {
		List<Usuarios> usuarios = entityManager().createQuery("from Usuarios U where U.user_id != :id", Usuarios.class)
			   .setParameter("id", usuario.getUser_id()).getResultList();
		usuarios.stream().forEach(user -> entityManager().refresh(user));
		return usuarios;
	}

	public Usuarios modifyUser(int id, String nombre, String apellido, String password, String email) {
		return withTransaction(() -> {
			Usuarios usuario = entityManager().find(Usuarios.class, id);
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setPassword(PasswordUtil.hashPassword(password));
			usuario.setEmail(email);
			return usuario;
		});
	}

	public Usuarios modifyUser(int id, String nombre, String apellido, String password, String email, Marcas marca) {
		return withTransaction(() -> {
			Usuarios usuario = entityManager().find(Usuarios.class, id);
			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setPassword(password);
			usuario.setEmail(email);
			RepositorioMarcas.getInstance().agregarUsuario(marca, usuario);
			return usuario;
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

	public void removeUser(Usuarios usuario) {
		withTransaction(() -> {
			entityManager().remove(usuario);
		});
	}

	public Usuarios getUserByEmail(String email) {
		return entityManager().createQuery("from Usuarios U where U.email = :email", Usuarios.class)
				.setParameter("email", email).getSingleResult();
	}

	public List<Usuarios> getAdministradoresRestantes(Usuarios usuario) {
		return entityManager().createQuery("from Usuarios U where U.admin = true and U.user_id != :id", Usuarios.class)
				.setParameter("id", usuario.getUser_id()).getResultList();
	}

}