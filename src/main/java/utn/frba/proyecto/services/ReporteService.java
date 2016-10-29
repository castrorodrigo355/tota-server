package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Reportes;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioCamaras;
import utn.frba.proyecto.repositorios.RepositorioReportes;

public class ReporteService {

	public List<Reportes> getReportes(String filtro) {
		return RepositorioReportes.getInstance().getReportesFiltrados(filtro);
	}
}