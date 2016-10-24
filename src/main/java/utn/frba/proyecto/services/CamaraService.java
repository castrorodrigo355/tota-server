package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioCamaras;

public class CamaraService {

	public List<Camaras> getCamaras() {
		return RepositorioCamaras.getInstance().getAllCamaras();
	}

	public Camaras getCamara(int id) {
		return RepositorioCamaras.getInstance().getCamaraById(id);
	}

	public Camaras crearCamara(String direccion, Ubicaciones ubicacion) {
		Camaras camara = new Camaras(direccion);
		RepositorioCamaras.getInstance().addCamara(camara);
		return camara;
	}

	public void eliminarCamara(Camaras camara) {
		RepositorioCamaras.getInstance().eliminarCamara(camara);
	}

	public Camaras modificarCamara(Camaras camara) {
		RepositorioCamaras.getInstance().modificarCamara(camara);
		return camara;
	}
}