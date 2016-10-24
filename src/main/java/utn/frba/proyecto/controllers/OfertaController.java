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
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.services.OfertaService;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class OfertaController {

	public OfertaController(final OfertaService ofertaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/ofertas", (request, response) -> {
			List<Ofertas> ofertas = ofertaService.getOfertas();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("ofertas", ofertas);
			return new ModelAndView(map, "ofertas.hbs");
		}, engine);

		get("/ofertas/:of_id", (req, res) -> {
			int of_id = Integer.parseInt(req.params(":of_id"));
			Ofertas oferta = ofertaService.getOferta(of_id);

			if (oferta != null) {
				return oferta;
			} else {
				res.status(400);
				return "No hay ofertas con Id " + of_id;
			}

		}, json());

		put("/ofertas/:of_id", (req, res) -> {
			int of_id = Integer.parseInt(req.params(":of_id"));
			String descripcion = req.queryParams("descripcion");
			Ofertas oferta = ofertaService.getOferta(of_id);

			if (oferta != null) {
				oferta.setDescripcion(descripcion);
				return ofertaService.modificarOferta(oferta);
			} else {
				res.status(400);
				return "No hay ofertas con Id " + of_id;
			}
		}, json());

		delete("/ofertas/:of_id", (req, res) -> {
			int of_id = Integer.parseInt(req.params(":of_id"));
			Ofertas oferta = ofertaService.getOferta(of_id);

			if (oferta != null) {
				ofertaService.eliminarOferta(oferta);
				return ofertaService.getOfertas();
			} else {
				res.status(400);
				return "No hay ofertas con Id " + of_id;

			}
		}, json());

		post("/ofertas", (req, res) -> {
			String descripcion = req.queryParams("descripcion");
			int pub_id = Integer.parseInt(req.queryParams("pub_id"));
			
			PublicidadService publicidadService = new PublicidadService();
			Publicidades publicidad = publicidadService.getPublicidad(pub_id);
			Ofertas oferta = ofertaService.crearOferta(descripcion, publicidad);
			publicidad.agregarOferta(oferta);
			return ofertaService.getOfertas();
		}, json());

		after("/ofertas/*", (req, res) -> {
			res.type("application/json");
		});
	}
}