package utn.frba.proyecto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Ofertas {

	@Id
	@GeneratedValue
	private int of_id;
	private String descripcion;

	public Ofertas(){
	}
	public Ofertas(String descripcion){
		this.descripcion = descripcion;
	}
	
	public int getId() {
		return of_id;
	}

	public void setId(int of_id) {
		this.of_id = of_id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}