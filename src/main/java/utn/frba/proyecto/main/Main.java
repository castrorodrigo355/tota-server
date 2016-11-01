package utn.frba.proyecto.main;

import static spark.SparkBase.port;

import static spark.SparkBase.staticFileLocation;
import static spark.Spark.after;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import utn.frba.proyecto.controllers.CamaraController;
import utn.frba.proyecto.controllers.InicioController;
import utn.frba.proyecto.controllers.LoginController;
import utn.frba.proyecto.controllers.MarcaController;
import utn.frba.proyecto.controllers.OfertaController;
import utn.frba.proyecto.controllers.PublicidadController;
import utn.frba.proyecto.controllers.ReporteController;
import utn.frba.proyecto.controllers.TelevisorController;
import utn.frba.proyecto.controllers.UbicacionController;
import utn.frba.proyecto.controllers.UsuarioController;
import utn.frba.proyecto.services.LoginService;
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.services.CamaraService;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.services.ReporteService;
import utn.frba.proyecto.services.TelevisorService;
import utn.frba.proyecto.services.UbicacionService;
import utn.frba.proyecto.services.UsuarioService;
import utn.frba.proyecto.services.OfertaService;

public class Main {

	public static void main(String[] args) {

		staticFileLocation("/public");
		port(8080);

		new InicioController();
		new LoginController(new LoginService());
		new UsuarioController(new UsuarioService());
		//new PublicidadController(new PublicidadService());
		new MarcaController(new MarcaService());
		new UbicacionController(new UbicacionService());
		new CamaraController(new CamaraService());
		new TelevisorController(new TelevisorService());
		//new OfertaController(new OfertaService());
		new ReporteController(new ReporteService());
		
		after((request, response) -> {
			PerThreadEntityManagers.getEntityManager();
			PerThreadEntityManagers.closeEntityManager();
		});
	
	}

}