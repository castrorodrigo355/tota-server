package utn.frba.proyecto.entities;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reportes {

	@GeneratedValue
	@Id
	private int id_reporte;
	private GregorianCalendar hora_fecha;
	private int cant_mujeres;
	private int cant_hombres;
	private int cant_personas_seleccionadas;
	private int pub_id;
	private int cam_id;

	public Reportes() {}

	public Reportes(int id, GregorianCalendar hora_fecha, int cant_mujeres, int cant_hombres, int cant_seleccionadas, int pub_id, int cam_id) {
		this.id_reporte = id;
		this.hora_fecha = hora_fecha;
		this.cant_mujeres = cant_mujeres;
		this.cant_hombres = cant_hombres;
		this.cant_personas_seleccionadas = cant_seleccionadas;
		this.pub_id = pub_id;
		this.cam_id = cam_id;
	}
	
	public int getId_reporte() {
		return id_reporte;
	}

	public void setId_reporte(int id_reporte) {
		this.id_reporte = id_reporte;
	}

	public GregorianCalendar getHora_fecha() {
		return hora_fecha;
	}

	public void setHora_fecha(GregorianCalendar hora_fecha) {
		this.hora_fecha = hora_fecha;
	}

	public int getCant_mujeres() {
		return cant_mujeres;
	}

	public void setCant_mujeres(int cant_mujeres) {
		this.cant_mujeres = cant_mujeres;
	}

	public int getCant_hombres() {
		return cant_hombres;
	}

	public void setCant_hombres(int cant_hombres) {
		this.cant_hombres = cant_hombres;
	}

	public int getCant_personas_seleccionadas() {
		return cant_personas_seleccionadas;
	}

	public void setCant_personas_seleccionadas(int cant_personas_seleccionadas) {
		this.cant_personas_seleccionadas = cant_personas_seleccionadas;
	}
	
	public int getPub_id() {
		return pub_id;
	}

	public void setPub_id(int pub_id) {
		this.pub_id = pub_id;
	}
	
	public int getCam_id() {
		return cam_id;
	}

	public void setCam_id(int cam_id) {
		this.cam_id = cam_id;
	}

}