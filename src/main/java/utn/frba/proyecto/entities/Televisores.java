package utn.frba.proyecto.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Televisores {

	@Id
	@GeneratedValue
	private int tv_id;
	private String ip_dir;

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

}