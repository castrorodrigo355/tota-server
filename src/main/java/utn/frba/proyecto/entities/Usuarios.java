package utn.frba.proyecto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuarios {

	@GeneratedValue
	@Id
	private int user_id;
	private String nombre;
	private String apellido;
	private String password;
	private String email;
	private boolean admin;

	public Usuarios() {}

	public Usuarios(String nombre, String apellido, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
	}

	public Usuarios(String nombre, String apellido, String password, String email, boolean admin) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}

	public Usuarios(String nombre, String apellido, String password, String email) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.email = email;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

}