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
import utn.frba.proyecto.services.MarcaService;
import utn.frba.proyecto.services.UsuarioService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class UsuarioController {

	public UsuarioController(final UsuarioService usuarioService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/usuarios", (request, response) -> {
			List<Usuarios> usuarios = usuarioService.getUsuarios();
			List<Marcas> marcas = new MarcaService().getMarcas();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("usuarios", usuarios);
			map.put("marcas", marcas);
			return new ModelAndView(map, "usuarios.hbs");
		}, engine);

		get("/usuarios/:user_id", (req, res) -> {
			int user_id = Integer.parseInt(req.params(":user_id"));
			Usuarios usuario = usuarioService.getUsuario(user_id);
			if (usuario != null) {
				return usuario;
			} else {
				res.status(400);
				return "No hay usuarios con Id " + user_id;
			}
		}, json());

		put("/usuarios/:user_id", (req, res) -> {
			int user_id = Integer.parseInt(req.params(":user_id"));
			Usuarios usuario = usuarioService.getUsuario(user_id);
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			String password = req.queryParams("password");
			String email = req.queryParams("email");

			if (usuario != null) {
				usuario.setNombre(nombre);
				usuario.setApellido(apellido);
				usuario.setPassword(password);
				usuario.setEmail(email);
				return usuarioService.modificarUsuario(usuario);
			} else {
				res.status(400);
				return "No hay usuarios con Id " + user_id;
			}
		}, json());
		
		delete("/usuarios/:user_id", (req, res) -> {
			int user_id = Integer.parseInt(req.params(":user_id"));
			Usuarios usuario = usuarioService.getUsuario(user_id);

			if (usuario != null) {
				usuarioService.eliminarUsuario(usuario);
				return usuarioService.getUsuarios();
			} else {
				res.status(400);
				return "No hay usuarios con Id " + user_id;
			}
		}, json());

		post("/usuarios", (req, res) -> {
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			String password = req.queryParams("password");
			String email = req.queryParams("email");
			int marca_id = Integer.parseInt(req.queryParams("marca_id"));
			
			MarcaService marcaService = new MarcaService();
			Marcas marca = marcaService.getMarca(marca_id);
			Usuarios usuario = usuarioService.crearUsuario(nombre, apellido, password, email, marca);
			marca.agregarUsuario(usuario);
			marcaService.modificarMarca(marca);
			usuario.setMarca(marca);
            res.redirect("/usuarios");
            return null;
		}, json());
		
		after("/usuarios/*", (req, res) -> {
			res.type("application/json");
		});

	}
}