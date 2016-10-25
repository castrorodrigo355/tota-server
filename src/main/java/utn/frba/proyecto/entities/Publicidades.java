package utn.frba.proyecto.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Publicidades {

	@GeneratedValue
	@Id
	private int pub_id;
	private String sexo;
	private int edad_min;
	private int edad_max;
	private int horario_min;
	private int horario_max;
	private String descripcion;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "pub_id")
	private List<Ofertas> ofertas = new ArrayList<Ofertas>();
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id")
	private Marcas marca;
	
	public Publicidades() {}
	public Publicidades(String sexo, int edad_min, int edad_max, int horario_min, int horario_max, String descripcion) {
		this.sexo = sexo;
		this.edad_min = edad_min;
		this.edad_max = edad_max;
		this.horario_min = horario_min;
		this.horario_max = horario_max;
		this.descripcion = descripcion;
	}

	public int getId() {
		return pub_id;
	}

	public void setId(int pub_id) {
		this.pub_id = pub_id;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getEdad_min() {
		return edad_min;
	}

	public void setEdad_min(int edad_min) {
		this.edad_min = edad_min;
	}

	public int getEdad_max() {
		return edad_max;
	}

	public void setEdad_max(int edad_max) {
		this.edad_max = edad_max;
	}

	public int getHorario_min() {
		return horario_min;
	}

	public void setHorario_min(int horario_min) {
		this.horario_min = horario_min;
	}

	public int getHorario_max() {
		return horario_max;
	}

	public void setHorario_max(int horario_max) {
		this.horario_max = horario_max;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void agregarOferta(Ofertas oferta) {
		this.ofertas.add(oferta);
	}

	public void quitarOferta(Ofertas oferta) {
		this.ofertas.remove(oferta);
	}
	
	public List<Ofertas> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Ofertas> ofertas) {
		this.ofertas = ofertas;
	}
	
	public Marcas getMarca() {
		return marca;
	}
	public void setMarca(Marcas marca) {
		this.marca = marca;
	}
	
}