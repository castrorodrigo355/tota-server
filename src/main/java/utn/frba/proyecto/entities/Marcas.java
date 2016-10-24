package utn.frba.proyecto.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Marcas {

	@Id
	@GeneratedValue
	private int marca_id;
	private String nombre;
	private String descripcion;

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "marca_id")
	private List<Publicidades> publicidades = new ArrayList<Publicidades>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "marca_id")
	private List<Usuarios> usuarios = new ArrayList<Usuarios>();
	
	public Marcas() {
	}

	public Marcas(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getId() {
		return marca_id;
	}

	public void setId(int id) {
		this.marca_id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	// ***************************************************
	public void agregarPublicidad(Publicidades publicidad) {
		this.publicidades.add(publicidad);
	}

	public void quitarPublicidad(Publicidades publicidad) {
		this.publicidades.remove(publicidad);
	}
	
	public List<Publicidades> getPublicidades() {
		return publicidades;
	}

	public void setPublicidades(List<Publicidades> publicidades) {
		this.publicidades = publicidades;
	}
	// ***************************************************
	public void agregarUsuario(Usuarios usuario) {
		this.usuarios.add(usuario);
	}

	public void quitarUsuario(Usuarios usuario) {
		this.usuarios.remove(usuario);
	}
	
	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}
}