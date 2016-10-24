package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Televisores;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioTelevisores;

public class TelevisorService {

	public List<Televisores> getTelevisores() {
		return RepositorioTelevisores.getInstance().getAllTelevisores();
	}

	public Televisores getTelevisor(int id) {
		return RepositorioTelevisores.getInstance().getTelevisorById(id);
	}
	
	public Televisores crearTelevisor(String ip_dir, Ubicaciones ubicacion, Camaras camara) {
		Televisores televisor = new Televisores(ip_dir);
		RepositorioTelevisores.getInstance().addTelevisor(televisor);
		return televisor;
	}

	public void eliminarTelevisor(Televisores televisor) {
		RepositorioTelevisores.getInstance().removeTelevisor(televisor);
	}

	public Televisores modificarTelevisor(Televisores televisor) {
		RepositorioTelevisores.getInstance().modifyTelevisor(televisor);
		return televisor;
	}
}