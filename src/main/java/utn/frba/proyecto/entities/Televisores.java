package utn.frba.proyecto.entities;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Televisores {

	@Id
	@GeneratedValue
	private int tv_id;
	private String ip_dir;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubicacion_id")
	private Ubicaciones ubicacion;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cam_id")
	private Camaras camara;
	
	public Televisores() {

	}
	
	public Televisores(String ip_dir) {
		this.ip_dir = ip_dir;
	}

	public int getId() {
		return tv_id;
	}

	public void setId(int tv_id) {
		this.tv_id = tv_id;
	}

	public String getIp_dir() {
		return ip_dir;
	}

	public void setIp_dir(String ip_dir) {
		this.ip_dir = ip_dir;
	}

	public Ubicaciones getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicaciones ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Camaras getCamara() {
		return camara;
	}

	public void setCamara(Camaras camara) {
		this.camara = camara;
	}

}