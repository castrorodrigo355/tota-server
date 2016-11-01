package utn.frba.proyecto.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utn.frba.proyecto.utils.JSONUtils.json;
import static spark.Spark.*;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.utils.AuthenticationUtil;
import utn.frba.proyecto.utils.ResponseError;

public class PublicidadController {
	// private MarcaService marcaService = new MarcaService();

	private static final String rutaDeImagenes = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/img";

	// private static final String ruta =
	// "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/qrs/";
	// private static final String ruta = /img;
	// private static final String ruta = /of;
	public PublicidadController(final PublicidadService publicidadService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		// get("/publicidades", (request, response) -> {
		// List<Publicidades> publicidades =
		// publicidadService.getPublicidades();
		// Map<String, Object> map = new HashMap<String, Object>();
		// map.put("usuario", AuthenticationUtil.getAuthenticatedUser(request));
		// map.put("publicidades", publicidades);
		// return new ModelAndView(map, "publicidades.hbs");
		//
		// }, engine);

		get("/publicidades", (request, response) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			Marcas marca = RepositorioMarcas.getInstance().getMarcaByNombre(usuario.getNombreMarca());
			if (usuario != null || marca != null) {
				map.put("usuario", usuario);
				map.put("publicidades", marca.getPublicidades());
				return new ModelAndView(map, "publicidades.hbs");
			} else {
				return null;
			}

		}, engine);

		before("/publicidades", (request, response) -> {
			Usuarios user = AuthenticationUtil.getAuthenticatedUser(request);
			if (user == null) {
				response.redirect("/");
				halt();
			}
		});

		get("/publicidades/:pub_id", (request, response) -> {
			int pub_id = Integer.parseInt(request.params(":pub_id"));
			Publicidades publicidad = publicidadService.getPublicidad(pub_id);
			if (publicidad != null) {
				return publicidad;
			}
			response.status(400);
			return new ResponseError("No hay publicidad con id '%s'", String.valueOf(pub_id));
		}, json());

		put("/publicidades/:pub_id", (req, res) -> {
			int pub_id = Integer.parseInt(req.params(":pub_id"));
			Publicidades publicidad = publicidadService.getPublicidad(pub_id);

			String sexo = req.queryParams("sexo");
			int emin = Integer.parseInt(req.queryParams("edad_min"));
			int emax = Integer.parseInt(req.queryParams("edad_max"));
			int hrmin = Integer.parseInt(req.queryParams("horario_min"));
			int hrmax = Integer.parseInt(req.queryParams("horario_max"));
			String descripcion = req.queryParams("descripcion");
			String path = req.queryParams("path");

			if (publicidad != null) {
				publicidad.setSexo(sexo);
				publicidad.setEdad_min(emin);
				publicidad.setEdad_max(emax);
				publicidad.setHorario_min(hrmin);
				publicidad.setHorario_max(hrmax);
				publicidad.setDescripcion(descripcion);
				publicidad.setPath(path);
				return publicidadService.modificarPublicidad(publicidad);
			} else {
				res.status(400);
				return "No hay publicidades con Id " + pub_id;
			}
		}, json());

		delete("/publicidades/:pub_id", (req, res) -> {
			int pub_id = Integer.parseInt(req.params(":pub_id"));
			Publicidades publicidad = publicidadService.getPublicidad(pub_id);

			if (publicidad != null) {
				String path = publicidad.getPath();
				File fotoParaBorrar = new File(rutaDeImagenes, path);
				fotoParaBorrar.delete();
				publicidadService.eliminarPublicidad(publicidad);
				return null;
			} else {
				res.status(400);
				return "No hay publicidades con Id " + pub_id;
			}
		}, json());

		post("/publicidades", (request, response) -> {
			String sexo = request.queryParams("sexo");
			int emin = Integer.parseInt(request.queryParams("edad_min"));
			int emax = Integer.parseInt(request.queryParams("edad_max"));
			int hrmin = Integer.parseInt(request.queryParams("horario_min"));
			int hrmax = Integer.parseInt(request.queryParams("horario_max"));
			String descripcion = request.queryParams("descripcion");
			String path = request.queryParams("path");

			String extension = "";
			int extensionImagenSeleccionada = path.length();
			String ultimos3 = path.substring(extensionImagenSeleccionada - 3, extensionImagenSeleccionada);
			switch (ultimos3) {
			case "png":
				extension = ".png";
				break;
			case "jpg":
				extension = ".jpg";
				break;
			default:
				extension = ".gif";
				break;
			}

			String cantPublicidades = String.valueOf(publicidadService.getPublicidades().size() + 1);
			// String ruta = "../img/";
			String nombreFinal = cantPublicidades + extension;

			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			Marcas marca = RepositorioMarcas.getInstance().getMarcaByNombre(usuario.getNombreMarca());
			Publicidades publicidad = new Publicidades(sexo, emin, emax, hrmin, hrmax, descripcion, nombreFinal);
			publicidadService.crearPublicidad(publicidad);
			RepositorioMarcas.getInstance().agregarPublicidadAMarca(publicidad, marca);

			int cantidadPublicidades = publicidadService.getPublicidades().size();
			Publicidades ultimaPublicidad = publicidadService.getPublicidades().get(cantidadPublicidades - 1);
			ultimaPublicidad.setPath(ultimaPublicidad.getId() + extension);
			publicidadService.modificarPublicidad(publicidad);

			File fichero1 = new File(rutaDeImagenes, path);
			File fichero2 = new File(rutaDeImagenes, ultimaPublicidad.getId() + extension);
			fichero1.renameTo(fichero2);

			publicidadService.getPublicidades();
			response.redirect("/publicidades");
			return null;
		}, json());

		after("/publicidades/*", (request, response) -> {
			response.type("application/json");
		});

	}
}