package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Reportes;
import utn.frba.proyecto.repositorios.RepositorioReportes;

public class ReporteService {

	public List<Reportes> getReportes() {
		return RepositorioReportes.getInstance().getAllReportes();
	}
}