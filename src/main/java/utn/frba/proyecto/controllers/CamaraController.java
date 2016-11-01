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
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.services.CamaraService;
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.services.UbicacionService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class CamaraController {

	public CamaraController(final CamaraService camaraService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/camaras", (request, response) -> {
			List<Camaras> camaras = camaraService.getCamaras();
			List<Ubicaciones> ubicaciones = new UbicacionService().getUbicaciones();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("camaras", camaras);
			map.put("ubicaciones", ubicaciones);
			return new ModelAndView(map, "camaras.hbs");
		}, engine);

		get("/camaras/:cam_id", (req, res) -> {
			int cam_id = Integer.parseInt(req.params(":cam_id"));
			Camaras camara = camaraService.getCamara(cam_id);

			if (camara != null) {
				return camara;
			} else {
				res.status(400);
				return "No hay Camaras con Id " + cam_id;
			}

		}, json());

		put("/camaras/:cam_id", (req, res) -> {
			int cam_id = Integer.parseInt(req.params(":cam_id"));
			Camaras camara = camaraService.getCamara(cam_id);
			
			String ip_dir = req.queryParams("ip_dir");
			String endpoint = req.queryParams("endpoint");
			
			camara.setIp_dir(ip_dir);
			camara.setEndpoint(endpoint);
			return camaraService.modificarCamara(cam_id, ip_dir, endpoint);
		}, json());

		delete("/camaras/:cam_id", (req, res) -> {
			int cam_id = Integer.parseInt(req.params(":cam_id"));
			Camaras camara = camaraService.getCamara(cam_id);

			if (camara != null) {
				camaraService.eliminarCamara(camara);
				return camaraService.getCamaras();
			} else {
				res.status(400);
				return "No hay camaras con Id " + cam_id;

			}
		}, json());

		post("/camaras", (req, res) -> {
			String ip_dir = req.queryParams("ip_dir");
			String endpoint = req.queryParams("endpoint");
			int ubicacion_id = Integer.parseInt(req.queryParams("ubicacion_id"));
			
			
			Ubicaciones ubicacion = new UbicacionService().getUbicacion(ubicacion_id);
			return camaraService.crearCamara(ip_dir, endpoint, ubicacion);
		}, json());
		
		after("/camaras/*", (req, res) -> {
			res.type("application/json");
		});
	}
}