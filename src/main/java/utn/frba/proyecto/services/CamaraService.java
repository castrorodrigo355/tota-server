package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioCamaras;
import utn.frba.proyecto.repositorios.RepositorioUbicaciones;

public class CamaraService {

	public List<Camaras> getCamaras() {
		return RepositorioCamaras.getInstance().getAllCamaras();
	}

	public Camaras getCamara(int id) {
		return RepositorioCamaras.getInstance().getCamaraById(id);
	}

	public Camaras crearCamara(String direccion, String endpoint, Ubicaciones ubicacion) {
		Camaras camara = new Camaras(direccion, endpoint);
		// camara.setDescripcionUbicacion(ubicacion.getDescripcion());
		RepositorioCamaras.getInstance().addCamara(camara);
		RepositorioUbicaciones.getInstance().agregarCamara(ubicacion, camara);
		return camara;
	}

	public void eliminarCamara(Camaras camara) {
		RepositorioCamaras.getInstance().eliminarCamara(camara);
		// RepositorioUbicaciones.getInstance().quitarCamara(ubicacion, camara);
	}

	public Camaras modificarCamara(int id, String ipdir, String endpoint) {
		return RepositorioCamaras.getInstance().modificarCamara(id, ipdir, endpoint);
	}
}