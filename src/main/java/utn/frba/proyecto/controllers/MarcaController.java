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
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class MarcaController {

	public MarcaController(MarcaService marcaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/marcas", (request, response) -> {
			List<Marcas> marcas = marcaService.listarMarcas();
			
			List<Usuarios> usuarios = RepositorioUsuarios.getInstance().getAllUsers();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("marcas", marcas);
			map.put("usuarios", usuarios);
			return new ModelAndView(map, "marcas.hbs");
		}, engine);

		get("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			Marcas marca = marcaService.getMarcaById(marca_id);
			if (marca != null) {
				return marca;
			} else {
				res.status(400);
				return "No hay Marcas con Id " + marca_id;
			}

		}, json());

		put("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			Marcas marca = marcaService.getMarcaById(marca_id);
			
			String nombre = req.queryParams("nombre");
			String descripcion = req.queryParams("descripcion");

			marca.setNombre(nombre);
			marca.setDescripcion(descripcion);
			return marcaService.modificarMarca(marca_id, nombre, descripcion);
		}, json());

		delete("/marcas/:marca_id", (req, res) -> {
			int marca_id = Integer.parseInt(req.params(":marca_id"));
			Marcas marca = marcaService.getMarcaById(marca_id);
			marcaService.quitarMarca(marca);
			return null;
		}, json());

		post("/marcas", (req, res) -> {
			String nombre = req.queryParams("nombre");
			String descripcion = req.queryParams("descripcion");
			
			return marcaService.crearMarca(nombre, descripcion);
		}, json());
		
		after("/marcas/*", (req, res) -> {
			res.type("application/json");
		});

	}

}