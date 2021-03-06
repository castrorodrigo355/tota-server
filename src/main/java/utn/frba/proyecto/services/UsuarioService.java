package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.utils.PasswordUtil;

public class UsuarioService {

	public List<Usuarios> getUsuariosRestantes(Usuarios usuario) {
		return RepositorioUsuarios.getInstance().getAllUsers(usuario);
	}

	public Usuarios crearUsuario(String nombre, String apellido, String password, String email) {
		Usuarios usuario = new Usuarios(nombre, apellido, PasswordUtil.hashPassword(password), email, true);
		RepositorioUsuarios.getInstance().agregarUsuario(usuario);
		return usuario;
	}
	
	public Usuarios crearUsuario(String nombre, String apellido, String password, String email, Marcas marca) {
		Usuarios usuario = new Usuarios(nombre, apellido, PasswordUtil.hashPassword(password), email);
		RepositorioUsuarios.getInstance().agregarUsuario(usuario);
		RepositorioMarcas.getInstance().agregarUsuario(marca, usuario);
		return usuario;
	}

	public Usuarios getUsuario(int id) {
		return RepositorioUsuarios.getInstance().getUserById(id);
	}

	public void eliminarUsuario(Usuarios usuario) {
		RepositorioUsuarios.getInstance().removeUser(usuario);
	}

	public Usuarios modificarUsuario(int id, String nombre, String apellido, String password, String email) {
		return RepositorioUsuarios.getInstance().modifyUser(id, nombre, apellido, password, email);
	}

	public Usuarios getUsuarioByUsernameAndPassword(String username, String password) {
		return RepositorioUsuarios.getInstance().getUserByCredentials(username, password);
	}

	public List<Usuarios> getAdministradoresRestantes(Usuarios usuario) {
		return RepositorioUsuarios.getInstance().getAdministradoresRestantes(usuario);
	}

}