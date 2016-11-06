package utn.frba.proyecto.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Ofertas {

	@Id
	@GeneratedValue
	private int of_id;
	private String descripcion;
	
	@OneToOne
	@JoinColumn(name = "pub_id")
	private Publicidades publicidades;

	public Publicidades getPublicidades() {
		return publicidades;
	}
	
	public void setPublicidades(Publicidades publicidades) {
		this.publicidades = publicidades;
	}
	
	public Ofertas(){}
	public Ofertas(String descripcion){
		this.descripcion = descripcion;
	}

	public int getOf_id() {
		return of_id;
	}

	public void setOf_id(int of_id) {
		this.of_id = of_id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return "Ofertas [of_id=" + of_id + ", descripcion=" + descripcion + "]";
	}
}