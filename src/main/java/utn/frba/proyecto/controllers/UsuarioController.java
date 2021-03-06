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
			Map<String, Object> map = new HashMap<String, Object>();
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			List<Usuarios> usuarios = usuarioService.getUsuariosRestantes(usuario);
			List<Marcas> marcas = new MarcaService().listarMarcas();
			map.put("usuario", usuario);
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

			usuario.setNombre(nombre);
			usuario.setApellido(apellido);
			usuario.setPassword(password);
			usuario.setEmail(email);
			return usuarioService.modificarUsuario(user_id, nombre, apellido, password, email);
		}, json());
		
		delete("/usuarios/:user_id", (req, res) -> {
			int user_id = Integer.parseInt(req.params(":user_id"));
			Usuarios usuario = usuarioService.getUsuario(user_id);
			usuarioService.eliminarUsuario(usuario);
			return null;
		}, json());

		post("/usuarios", (req, res) -> {
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			String password = req.queryParams("password");
			String email = req.queryParams("email");
			String marca_id = req.queryParams("marca_id");
			
			if(marca_id.equals("")){
				return usuarioService.crearUsuario(nombre, apellido, password, email);
			}else{
				int id = Integer.parseInt(marca_id);
				Marcas marca = new MarcaService().getMarcaById(id);
				return usuarioService.crearUsuario(nombre, apellido, password, email, marca);
			}
		}, json());
		
		after("/usuarios/*", (req, res) -> {
			res.type("application/json");
		});

	}
}