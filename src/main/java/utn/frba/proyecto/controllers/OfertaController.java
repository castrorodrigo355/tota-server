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
import utn.frba.proyecto.repositorios.RepositorioPublicidades;
import utn.frba.proyecto.services.PublicidadService;
import utn.frba.proyecto.utils.AuthenticationUtil;

public class OfertaController {

	private static final String rutaOfertas = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/ofertas";

	public OfertaController(final OfertaService ofertaService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/ofertas", (request, response) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<Ofertas> ofertasMarcaUsuario = new ArrayList<Ofertas>();
			
			List<Ofertas> ofertasSistema = RepositorioOfertas.getInstance().getAllOfertas();
			
			// Aca tengo al usuario
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(request);
			
			// Aca tengo la marca del usuario
			Marcas marca = RepositorioMarcas.getInstance().getMarcaByUsuario(usuario);
			
			// Aca tengo las publicidades de la marca del usuario
			List<Publicidades> publicidadesMarcaUsuario = marca.getPublicidades();

			for(Ofertas unaOferta : ofertasSistema){
				for(Publicidades unaPublicidad : publicidadesMarcaUsuario){
					if(unaPublicidad.getPub_id() == unaOferta.getPublicidades().getPub_id()){
						ofertasMarcaUsuario.add(unaOferta);
					}
				}
			}
			
			List<Publicidades> publicidadesMarcaUsuarioSinOfertas = new ArrayList<Publicidades>();
			
			for(Publicidades unaPublicidad : publicidadesMarcaUsuario){
				if(unaPublicidad.getOferta() == null){
					publicidadesMarcaUsuarioSinOfertas.add(unaPublicidad);
				}
			}
			
			if (usuario != null || marca != null) {
				map.put("usuario", usuario);
				map.put("ofertas", ofertasMarcaUsuario);
				map.put("publicidadesMarcaUsuario", publicidadesMarcaUsuarioSinOfertas);
				return new ModelAndView(map, "ofertas.hbs");
			} else {
				return null;
			}

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
			String fotoOferta = oferta.getPublicidades().getPath();
			ofertaService.eliminarOferta(oferta);
			File fotoParaBorrar = new File(rutaOfertas, fotoOferta);
			fotoParaBorrar.delete();
			return null;
		}, json());

		post("/ofertas", (req, res) -> {
			String descOferta = req.queryParams("descripcion");
			int pub_id = Integer.parseInt(req.queryParams("pub_id"));

			Publicidades publicidad = RepositorioPublicidades.getInstance().getPublicidadById(pub_id);

			String path = publicidad.getPath();
			String idPublicidad = String.valueOf(publicidad.getPub_id());
			String imagenQR = "qr.png";
			GeneradorCodigoQR generador = new GeneradorCodigoQR();
			generador.generarCodigoQR(imagenQR, descOferta);
			String extension = "";
			String ultimos3 = path.substring(path.length() - 3, path.length());
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

			String nombreFinal = idPublicidad + extension;
			Mezclador miMezclador = new Mezclador();
			miMezclador.mezclarImagenes(path, imagenQR, nombreFinal);
			ofertaService.crearOferta(descOferta, publicidad);
			ofertaService.getOfertas();
			res.redirect("/ofertas");
			return null;
		}, json());

		after("/ofertas/*", (req, res) -> {
			res.type("application/json");
		});
	}
}