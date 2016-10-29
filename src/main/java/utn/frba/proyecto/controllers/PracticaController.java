package utn.frba.proyecto.controllers;
import java.util.List;

import static spark.Spark.get;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Usuarios;
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
			List<Marcas> listaDeMarcas= new MarcaService().getMarcas();
			List<Usuarios> listaDeUsuarios = new ArrayList<Usuarios>();
			List<Usuarios> usuarios = new UsuarioService().getUsuarios();
			
			for(Usuarios unUsuario : usuarios){
				if(unUsuario.getId() < 3){
					listaDeUsuarios.add(unUsuario);
				}
			}
			
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("listaDeMarcas", listaDeMarcas);
			map.put("listaDeUsuarios", listaDeUsuarios);
			return new ModelAndView(map, "practicas.hbs");
		}, engine);

	}
}