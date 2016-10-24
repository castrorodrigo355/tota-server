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
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class MarcaController {

	public MarcaController(MarcaService marcaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/marcas", (request, response) -> {
			List<Marcas> marcas = marcaService.getMarcas();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("marcas", marcas);
			return new ModelAndView(map, "marcas.hbs");
		}, engine);

		get("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			Marcas marca = marcaService.getMarca(marca_id);
			if (marca != null) {
				return marca;
			} else {
				res.status(400);
				return "No hay Marcas con Id " + marca_id;
			}

		}, json());

		put("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			String nombre = req.queryParams("nombre");
			String descripcion = req.queryParams("descripcion");
			Marcas marca = marcaService.getMarca(marca_id);

			if (marca != null) {
				marca.setNombre(nombre);
				return marcaService.modificarMarca(marca);
			} else {
				res.status(400);
				return "No hay Marcas con Id " + marca_id;
			}
		}, json());

		delete("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			Marcas marca = marcaService.getMarca(marca_id);

			if (marca != null) {
				marcaService.eliminarMarca(marca);
				return marcaService.getMarcas();
			} else {
				res.status(400);
				return "No hay Marcas con Id " + marca_id;

			}
		}, json());

		post("/marcas", (req, res) -> {
			String nombre = req.queryParams("nombre");
			String descripcion = req.queryParams("descripcion");
			
			marcaService.crearMarca(nombre, descripcion);
			res.redirect("/marcas");
            return null;
		}, json());
		
		after("/marcas/*", (req, res) -> {
			res.type("application/json");
		});

	}

}
