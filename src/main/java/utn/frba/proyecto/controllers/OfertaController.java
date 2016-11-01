package utn.frba.proyecto.controllers;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.services.OfertaService;
import static utn.frba.proyecto.utils.JSONUtils.json;
import static spark.Spark.*;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import spark.ModelAndView;
import java.util.List;
import java.util.Map;

import utn.frba.proyecto.entities.GeneradorCodigoQR;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Mezclador;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.repositorios.RepositorioOfertas;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.utils.AuthenticationUtil;
import utn.frba.proyecto.utils.ResponseError;

public class OfertaController {

	private static final String rutaOfertas = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/ofertas";
	// private static final String ruta = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/qrs/";
	// private static final String ruta = /img;
	// private static final String ruta = /of;
	
	public OfertaController(final OfertaService ofertaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/ofertas", (request, response) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Publicidades> publicidadesDeMarcaDeUsuario = new ArrayList<Publicidades>();
			List<Ofertas> ofertas = new ArrayList<Ofertas>();
			List<Publicidades> publicidadesSinOferta = new ArrayList<Publicidades>();
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			Marcas marca = RepositorioMarcas.getInstance().getMarcaByNombre(usuario.getNombreMarca());

			if(usuario == null){
				map.put("ofertas", ofertas);
				return new ModelAndView(map, "ofertas.hbs");
			}
			
			publicidadesDeMarcaDeUsuario = marca.getPublicidades();
			
			for(Publicidades unaPublicidad : publicidadesDeMarcaDeUsuario){
				if(unaPublicidad.getOferta() != null){
					Ofertas unaOferta = unaPublicidad.getOferta();
					ofertas.add(unaOferta);
				}
			}

			for(Publicidades unaPublicidad : publicidadesDeMarcaDeUsuario){
				if(unaPublicidad.getOferta() == null){
					publicidadesSinOferta.add(unaPublicidad);
				}
			}
			
			map.put("usuario", usuario);
			map.put("ofertas", ofertas);
			map.put("publicidadesSinOferta", publicidadesSinOferta);
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
				String fotoOferta = oferta.getPublicidades().getPath();
				ofertaService.eliminarOferta(oferta);
				File fotoParaBorrar = new File(rutaOfertas, fotoOferta);
				fotoParaBorrar.delete();
				ofertaService.getOfertas();
				res.redirect("/ofertas");
				return null;
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
			
			String extension = "";
			int extensionImagenSeleccionada = path.length();
			String ultimos3 = path.substring(extensionImagenSeleccionada - 3, extensionImagenSeleccionada);
	    	switch(ultimos3){
		    	case "png": extension = ".png"; break;
		    	case "jpg": extension = ".jpg"; break;
		    	default: extension = ".gif"; break; 
	    	}
			
			String nombreFinal = idPublicidad + extension;
			Mezclador miMezclador = new Mezclador();
			miMezclador.mezclarImagenes(path, imagenQR, nombreFinal);
			
			Ofertas oferta = new Ofertas(descOferta);
			oferta.setPublicidades(publicidad);
			ofertaService.crearOferta(oferta);
			publicidad.setOferta(oferta);
			
			ofertaService.getOfertas();
			res.redirect("/ofertas");
			return null;
		}, json());

		after("/ofertas/*", (req, res) -> {
			res.type("application/json");
		});
	}
}