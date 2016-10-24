package utn.frba.proyecto.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Camaras {

	@Id
	@GeneratedValue
	private int cam_id;
	private String ip_dir;
	
	@OneToMany
	@JoinColumn(name = "cam_id")
	private List<Televisores> televisores = new ArrayList<Televisores>();

	public Camaras(String ip_dir){
		this.ip_dir = ip_dir;
	}
	public Camaras() {

	}
	
	public int getId() {
		return cam_id;
	}

	public void setId(int cam_id) {
		this.cam_id = cam_id;
	}

	public String getIp_dir() {
		return ip_dir;
	}

	public void setIp_dir(String ip_dir) {
		this.ip_dir = ip_dir;
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