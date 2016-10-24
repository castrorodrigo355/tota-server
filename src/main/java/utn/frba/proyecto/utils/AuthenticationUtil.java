package utn.frba.proyecto.utils;

import spark.Request;
import utn.frba.proyecto.entities.Usuarios;

public class AuthenticationUtil {

	private static final String USER_SESSION_ID = "user";

	public static Usuarios getAuthenticatedUser(Request request) {
		return request.session().attribute(USER_SESSION_ID);
	}

	public static void addAuthenticatedUser(Request request, Usuarios user) {
		request.session().attribute(USER_SESSION_ID, user);
	}

	public static void removeAuthenticationUser(Request request) {
		request.session().removeAttribute(USER_SESSION_ID);
	}

}
