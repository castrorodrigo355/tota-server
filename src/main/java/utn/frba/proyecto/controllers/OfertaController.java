package utn.frba.proyecto.controllers;
import java.util.List;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static utn.frba.proyecto.utils.JSONUtils.json;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.GeneradorCodigoQR;
import utn.frba.proyecto.entities.Mezclador;
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.services.OfertaService;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class OfertaController {

	private static final String rutaOfertas = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/ofertas";
	public OfertaController(final OfertaService ofertaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/ofertas", (request, response) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Publicidades> publicidadesDeMarcaDeUsuario = new ArrayList<Publicidades>();
			List<Ofertas> ofertas = new ArrayList<Ofertas>();
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			
			if(usuario == null){
				map.put("ofertas", ofertas);
				return new ModelAndView(map, "ofertas.hbs");
			}
			
			publicidadesDeMarcaDeUsuario = usuario.getMarca().getPublicidades();
			for(Publicidades unaPublicidad : publicidadesDeMarcaDeUsuario){
				Ofertas unaOferta = unaPublicidad.getOferta();
				ofertas.add(unaOferta);
			}
			
			map.put("usuario", usuario);
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
			//File fotoParaBorrar = new File(rutaOfertas, imgQR);
			//fotoParaBorrar.delete();

			if (oferta != null) {
				ofertaService.eliminarOferta(oferta);
				return ofertaService.getOfertas();
			} else {
				res.status(400);
				return "No hay ofertas con Id " + of_id;

			}
		}, json());

		post("/ofertas", (req, res) -> {
			String descOferta = req.queryParams("descripcion");
			int pub_id = Integer.parseInt(req.queryParams("pub_id"));
			
			PublicidadService publicidadService = new PublicidadService();
			Publicidades publicidad = publicidadService.getPublicidad(pub_id);
			
			String path = publicidad.getPath();
			String idPublicidad = String.valueOf(publicidad.getId());
			
			String imagenQR = "qr.png";
			
			GeneradorCodigoQR generador = new GeneradorCodigoQR();
			generador.generarCodigoQR(imagenQR, descOferta);
			
			String nombreFinal = idPublicidad + "-" + descOferta + ".png";
			Mezclador miMezclador = new Mezclador();
			miMezclador.mezclarImagenes(path, imagenQR, nombreFinal);
			
			Ofertas oferta = ofertaService.crearOferta(descOferta, publicidad);
			publicidad.setOferta(oferta);
			
			res.redirect("/ofertas");
            return null;
		}, json());

		after("/ofertas/*", (req, res) -> {
			res.type("application/json");
		});
	}
}