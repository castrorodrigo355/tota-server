package utn.frba.proyecto.services;

import java.util.List;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioUbicaciones;

public class UbicacionService {

	public List<Ubicaciones> getUbicaciones() {
		return RepositorioUbicaciones.getInstance().getAllUbicaciones();
	}

	public Ubicaciones getUbicacion(int id) {
		return RepositorioUbicaciones.getInstance().getUbicacionById(id);
	}

	public Ubicaciones crearUbicacion(String descripcion) {
		Ubicaciones ubicacion = new Ubicaciones(descripcion);
		RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion);
		return ubicacion;
	}

	public void eliminarUbicacion(Ubicaciones ubicacion) {
		RepositorioUbicaciones.getInstance().removeUbicacion(ubicacion);
	}

	public Ubicaciones modificarUbicacion(Ubicaciones ubicacion) {
		RepositorioUbicaciones.getInstance().modifyUbicacion(ubicacion);
		return ubicacion;
	}

}