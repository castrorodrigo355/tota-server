package utn.frba.proyecto.services;

import java.util.List;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Personas;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.repositorios.RepositorioCamaras;
import utn.frba.proyecto.repositorios.RepositorioPersonas;

public class PersonaService {

	public List<Personas> getPersonas() {
		return RepositorioPersonas.getInstance().getAllPersonas();
	}

	public Personas getPersona(int id) {
		return RepositorioPersonas.getInstance().getPersonaById(id);
	}

	public Personas crearPersona(String nombre, String apellido, String edad) {
		Personas p = new Personas(nombre, apellido, edad);
		RepositorioPersonas.getInstance().addPersona(p);
		return p;
	}

	public void eliminarPersona(Personas p) {
		RepositorioPersonas.getInstance().removePersona(p);
	}

	public Personas modificarPersona(Personas p) {
		RepositorioPersonas.getInstance().modifyPersona(p);
		return p;
	}
}