package utn.frba.proyecto.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Ubicaciones {

	@Id
	@GeneratedValue
	private int ubicacion_id;
	private String descripcion;

	@OneToMany
	@JoinColumn(name = "ubicacion_id")
	private List<Camaras> camaras = new ArrayList<Camaras>();
	
	@OneToMany
	@JoinColumn(name = "ubicacion_id")
	private List<Televisores> televisores = new ArrayList<Televisores>();

	public Ubicaciones() {}

	public Ubicaciones(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getId() {
		return ubicacion_id;
	}

	public void setId(int ubicacion_id) {
		this.ubicacion_id = ubicacion_id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// **************************************************************
	public void agregarCamara(Camaras camara) {
		this.camaras.add(camara);
	}

	public void quitarCamara(Camaras camara) {
		this.camaras.remove(camara);
	}
	
	public List<Camaras> getCamaras() {
		return camaras;
	}

	public void setCamaras(List<Camaras> camaras) {
		this.camaras = camaras;
	}
	// **************************************************************
	public void agregarTelevisor(Televisores televisor) {
		this.televisores.add(televisor);
	}

	public void quitarTelevisor(Televisores televisor) {
		this.televisores.remove(televisor);
	}
	
	public List<Televisores> getTelevisores() {
		return televisores;
	}

	public void setTelevisores(List<Televisores> televisores) {
		this.televisores = televisores;
	}
}