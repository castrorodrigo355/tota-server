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
import utn.frba.proyecto.entities.Personas;
import utn.frba.proyecto.services.PersonaService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class PersonaController {

	public PersonaController(final PersonaService personaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/personas", (request, response) -> {
			List<Personas> personas = personaService.getPersonas();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
			map.put("personas", personas);
			return new ModelAndView(map, "personas.hbs");
		}, engine);

		get("/personas/:pers_id", (req, res) -> {
			int id = Integer.parseInt(req.params(":pers_id"));
			Personas persona = personaService.getPersona(id);

			if (persona != null) {
				return persona;
			} else {
				res.status(400);
				return "No hay personas con Id " + id;
			}

		}, json());

		put("/personas/:pers_id", (req, res) -> {
			int id = Integer.parseInt(req.params(":pers_id"));
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			String edad = req.queryParams("edad");
			Personas persona = personaService.getPersona(id);

			if (persona != null) {
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setEdad(edad);
				return personaService.modificarPersona(persona);
			} else {
				res.status(400);
				return "No hay personas con Id " + id;
			}
		}, json());

		delete("/personas/:pers_id", (req, res) -> {
			int id = Integer.parseInt(req.params(":pers_id"));
			Personas persona = personaService.getPersona(id);

			if (persona != null) {
				personaService.eliminarPersona(persona);
				return personaService.getPersonas();
			} else {
				res.status(400);
				return "No hay personas con Id " + id;
			}
		}, json());

		post("/personas", (req, res) -> {
			String nombre = req.queryParams("nombre");
			String apellido = req.queryParams("apellido");
			String edad = req.queryParams("edad");
			String strId = req.queryParams("pers_id");
			if(strId==null || strId.isEmpty()){
				personaService.crearPersona(nombre, apellido, edad);
			}else{
				int id = Integer.parseInt(strId);
				Personas persona = personaService.getPersona(id);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setEdad(edad);
				personaService.modificarPersona(persona);
			}
			res.redirect("/personas");
			return null;
			/*
			int id = Integer.parseInt(req.queryParams("pers_id"));
			if(id == 100){
				String nombre = req.queryParams("nombre");
				String apellido = req.queryParams("apellido");
				String edad = req.queryParams("edad");
				personaService.crearPersona(nombre, apellido, edad);
				res.redirect("/personas");
				return null;	
			}else{
				String nombre = req.queryParams("nombre");
				String apellido = req.queryParams("apellido");
				String edad = req.queryParams("edad");
				Personas persona = personaService.getPersona(id);
				persona.setNombre(nombre);
				persona.setApellido(apellido);
				persona.setEdad(edad);
				personaService.modificarPersona(persona);
				res.redirect("/personas");
				return null;
			}
			*/
		}, json());
		
		after("/personas/*", (req, res) -> {
			res.type("application/json");
		});
	}
}