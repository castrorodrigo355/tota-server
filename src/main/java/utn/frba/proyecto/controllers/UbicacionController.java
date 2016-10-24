package utn.frba.proyecto.controllers;

import java.util.List;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utn.frba.proyecto.utils.JSONUtils.json;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.services.UbicacionService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class UbicacionController {

	public UbicacionController(final UbicacionService ubicacionService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/ubicaciones", (request, response) -> {
			List<Ubicaciones> ubicaciones = ubicacionService.getUbicaciones();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("ubicaciones", ubicaciones);
			return new ModelAndView(map, "ubicaciones.hbs");
		}, engine);

		get("/ubicaciones/:ubicacion_id", (req, res) -> {
			int ubicacion_id = Integer.parseInt(req.params(":ubicacion_id"));
			Ubicaciones ubicacion = ubicacionService.getUbicacion(ubicacion_id);

			if (ubicacion != null) {
				return ubicacion;
			} else {
				res.status(400);
				return "No hay ubicaciones con Id " + ubicacion_id;
			}

		}, json());

		put("/ubicaciones/:ubicacion_id", (req, res) -> {
			int ubicacion_id = Integer.parseInt(req.params(":ubicacion_id"));
			String descripcion = req.queryParams("descripcion");
			Ubicaciones ubicacion = ubicacionService.getUbicacion(ubicacion_id);

			if (ubicacion != null) {
				ubicacion.setDescripcion(descripcion);
				return ubicacionService.modificarUbicacion(ubicacion);
			} else {
				res.status(400);
				return "No hay ubicaciones con Id " + ubicacion_id;
			}
		}, json());

		delete("/ubicaciones/:ubicacion_id", (req, res) -> {
			int ubicacion_id = Integer.parseInt(req.params(":ubicacion_id"));
			Ubicaciones ubicacion = ubicacionService.getUbicacion(ubicacion_id);

			if (ubicacion != null) {
				ubicacionService.eliminarUbicacion(ubicacion);
				return ubicacionService.getUbicaciones();
			} else {
				res.status(400);
				return "No hay ubicaciones con Id " + ubicacion_id;

			}
		}, json());

		post("/ubicaciones", (req, res) -> {
			String descripcion = req.queryParams("descripcion");
			return ubicacionService.crearUbicacion(descripcion);
		}, json());

	}

}