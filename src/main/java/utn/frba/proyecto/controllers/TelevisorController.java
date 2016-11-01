package utn.frba.proyecto.controllers;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utn.frba.proyecto.utils.JSONUtils.json;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Televisores;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.services.CamaraService;
import utn.frba.proyecto.services.TelevisorService;
import utn.frba.proyecto.services.UbicacionService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class TelevisorController {

	public TelevisorController(TelevisorService televisorService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/televisores", (request, response) -> {
			List<Televisores> televisores = televisorService.getTelevisores();
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Ubicaciones> ubicaciones = new UbicacionService().getUbicaciones();
			List<Camaras> camaras = new CamaraService().getCamaras();
			
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("televisores", televisores);
			map.put("ubicaciones", ubicaciones);
			map.put("camaras", camaras);
			return new ModelAndView(map, "televisores.hbs");
		}, engine);

		get("/televisores/:tv_id", (req, res) -> {
			int tv_id = Integer.parseInt(req.params(":tv_id"));
			Televisores televisor = televisorService.getTelevisor(tv_id);
			if (televisor != null) {
				return televisor;
			} else {
				res.status(400);
				return "No hay Televisores con Id " + tv_id;
			}

		}, json());

		put("/televisores/:tv_id", (req, res) -> {
			int tv_id = Integer.parseInt(req.params(":tv_id"));
			Televisores televisor = televisorService.getTelevisor(tv_id);
			String ip_dir = req.queryParams("ip_dir");

			televisor.setIp_dir(ip_dir);
			return televisorService.modificarTelevisor(tv_id, ip_dir);
		}, json());
		
		delete("/televisores/:tv_id", (req, res) -> {
			int tv_id = Integer.parseInt(req.params(":tv_id"));
			Televisores televisor = televisorService.getTelevisor(tv_id);

			if (televisor != null) {
				televisorService.eliminarTelevisor(televisor);
				return televisorService.getTelevisores();
			} else {
				res.status(400);
				return "No hay Televisores con Id " + tv_id;

			}
		}, json());

		post("/televisores", (req, res) -> {
			String ip_dir = req.queryParams("ip_dir");
			int ubicacion_id = Integer.parseInt(req.queryParams("ubicacion_id"));
			int cam_id = Integer.parseInt(req.queryParams("cam_id"));
			
			Ubicaciones ubicacion = new UbicacionService().getUbicacion(ubicacion_id);
			Camaras camara = new CamaraService().getCamara(cam_id);
			/*
			ubicacion.agregarTelevisor(televisor);			
			camara.agregarTelevisor(televisor);
			*/
            return televisorService.crearTelevisor(ip_dir, ubicacion, camara);
		}, json());
		
		after("/televisores/*", (req, res) -> {
			res.type("application/json");
		});

	}
}