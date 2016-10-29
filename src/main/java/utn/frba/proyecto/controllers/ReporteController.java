package utn.frba.proyecto.controllers;
import java.util.List;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utn.frba.proyecto.utils.JSONUtils.json;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Reportes;
import utn.frba.proyecto.services.ReporteService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class ReporteController {

	public ReporteController(final ReporteService reporteService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/reportes", (request, response) -> {
			String filtro = request.queryParams("filtro");
			Map<String, Object> map = new HashMap<String, Object>();
			if (filtro != null){
				List<Reportes> reportes = reporteService.getReportes(filtro);
				map.put("reportes", reportes);
			}
			
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			return new ModelAndView(map, "reportes.hbs");
			
		}, engine);
	}
}