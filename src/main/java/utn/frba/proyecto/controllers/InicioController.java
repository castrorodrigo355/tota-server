package utn.frba.proyecto.controllers;

import static spark.Spark.get;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class InicioController {

	public InicioController() {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/inicio", (request, response) -> {

			// List<Usuario> usuarios = usuarioService.getUsuarios();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			//map.put("usuarios", usuarios);
			return new ModelAndView(map, "inicio.hbs");
		}, engine);

	}
}