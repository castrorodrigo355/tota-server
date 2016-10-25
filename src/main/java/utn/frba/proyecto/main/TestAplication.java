package utn.frba.proyecto.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import static spark.Spark.*;

public class TestAplication {

	public static void main(String[] args) {
    	
		String descripcion = "nombreImagen.gif";
		int extensionImagenSeleccionada = descripcion.length();
		System.out.println("la longitud es: " + extensionImagenSeleccionada);
		String ultimos3 = descripcion.substring(extensionImagenSeleccionada - 3,extensionImagenSeleccionada);
    	System.out.println("Los ultimos 3 son: " + ultimos3);
    	switch(ultimos3){
	    	case "png": System.out.println("La extension es png"); break;
	    	case "jpg": System.out.println("La extension es jpg"); break;
	    	default: System.out.println("La extension es gif"); break; 
    	}
    }
}