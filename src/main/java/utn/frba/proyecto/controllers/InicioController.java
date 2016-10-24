package utn.frba.proyecto.controllers;
import java.util.List;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utn.frba.proyecto.utils.JSONUtils.json;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.services.CamaraService;
import utn.frba.proyecto.services.UbicacionService;
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