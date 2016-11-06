package utn.frba.proyecto.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Marcas")
public class Marcas {

	@Id
	@GeneratedValue
	private int marca_id;
	private String nombre;
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "marca_id")
	private List<Usuarios> usuarios = new ArrayList<Usuarios>();
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "marca_id")
	private List<Publicidades> publicidades = new ArrayList<Publicidades>();

	public Marcas(){}
	public Marcas(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public int getMarca_id() {
		return marca_id;
	}
	public void setMarca_id(int marca_id) {
		this.marca_id = marca_id;
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

	public List<Usuarios> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public void agregarUsuario(Usuarios usuario) {
		this.usuarios.add(usuario);
	}

	public void quitarUsuario(Usuarios usuario) {
		this.usuarios.remove(usuario);
	}

	public List<Publicidades> getPublicidades() {
		return publicidades;
	}
	
	public void setPublicidades(List<Publicidades> publicidades) {
		this.publicidades = publicidades;
	}
	
	public void agregarPublicidad(Publicidades publicidad) {
		this.publicidades.add(publicidad);
	}

	public void quitarPublicidad(Publicidades publicidad) {
		this.publicidades.remove(publicidad);
	}
}