package utn.frba.proyecto.utils;

import com.google.gson.Gson;

import spark.ResponseTransformer;

public class JSONUtils {

	public static String toJSON(Object model) {
		return new Gson().toJson(model);
	}

	public static ResponseTransformer json() {
		return JSONUtils::toJSON;
	}

}
