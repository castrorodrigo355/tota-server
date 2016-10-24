package utn.frba.proyecto.controllers;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.services.LoginService;
import utn.frba.proyecto.utils.AuthenticationUtil;
import utn.frba.proyecto.utils.PasswordUtil;

public class LoginController {

	public LoginController(LoginService loginService) {

		HandlebarsTemplateEngine engine = new HandlebarsTemplateEngine();

		get("/", (req, res) -> {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("title", "SMartketing");

			return new ModelAndView(map, "login.hbs");

		}, engine);
		
		before("/", (req, res) -> {
			Usuarios usuario = AuthenticationUtil.getAuthenticatedUser(req);
			if (usuario != null) {
				res.redirect("/inicio");
				halt();
			}
		});

		get("/exit", (request, response) -> {
			AuthenticationUtil.removeAuthenticationUser(request);
			response.redirect("/");
			halt();
			return null;
		});

		post("/login", (request, response) -> {

			String email = request.queryParams("email");
			String password = request.queryParams("password");
			Map<String, Object> map = new HashMap<>();

			if (!Objects.isNull(email) && !Objects.isNull(password)) {

				Usuarios usuario;
				try {
					usuario = RepositorioUsuarios.getInstance().getUserByEmail(email);

					if (Objects.isNull(usuario)) {
						map.put("error", "El usuario no existe, debe registrarse en el sistema");
						return new ModelAndView(map, "login.hbs");
					}

					if (PasswordUtil.verifyPassword(password, usuario.getPassword())) {
						AuthenticationUtil.addAuthenticatedUser(request, usuario);
						response.redirect("/");
					} else {
						map.put("error", "La contraseña es incorrecta. Ingrese nuevamente");
						return new ModelAndView(map, "login.hbs");
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			map.put("error", "Debe ingresar nombre de usuario y contraseña");
			return new ModelAndView(map, "login.hbs");

		}, engine);

	}

}