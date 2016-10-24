package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.utils.PasswordUtil;

public class UsuarioService {

	public List<Usuarios> getUsuarios() {
		return RepositorioUsuarios.getInstance().getAllUsers();
	}

	public Usuarios getUsuario(int id) {
		return RepositorioUsuarios.getInstance().getUserById(id);
	}

	public Usuarios getUsuarioByUsernameAndPassword(String username, String password) {
		return RepositorioUsuarios.getInstance().getUserByCredentials(username, password);
	}

	public Usuarios crearUsuario(String nombre, String apellido, String password, String email, Marcas marca) {
		Usuarios usuario = new Usuarios(nombre, apellido, PasswordUtil.hashPassword(password), email, false);
		RepositorioUsuarios.getInstance().agregarUsuario(usuario);
		return usuario;
	}

	public void eliminarUsuario(Usuarios usuario) {
		RepositorioUsuarios.getInstance().removeUser(usuario);
	}

	public Usuarios modificarUsuario(Usuarios usuario) {
		RepositorioUsuarios.getInstance().modifyUser(usuario);
		return usuario;
	}

}