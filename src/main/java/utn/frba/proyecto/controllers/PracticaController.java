package utn.frba.proyecto.controllers;
import java.util.List;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.services.UsuarioService;
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.services.PracticaService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class PracticaController {

	public PracticaController(final PracticaService practicaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/practicas", (request, response) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Marcas> listaDeMarcas = new ArrayList<>();
			List<Marcas> marcas = RepositorioMarcas.getInstance().getAllBrands();

			/*
			List<Marcas> listaDeMarcas = new ArrayList<>();
			List<Marcas> marcas= new MarcaService().getMarcas();
			*/
			for(Marcas unaMarca : marcas){
				listaDeMarcas.add(unaMarca);
			}
			
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("listaDeMarcas", listaDeMarcas);
			return new ModelAndView(map, "practicas.hbs");
		}, engine);

	}
}