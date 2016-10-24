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
	
    private static String nombreCazador;

	public static void main(String[] args) {
    	
    	List <Usuarios> usuarios = RepositorioUsuarios.getInstance().getAllUsers();
    	Usuarios unUsuario = usuarios.get(3);
    	String datosUsuario = unUsuario.toString();
    	System.out.println(datosUsuario);
    	
    }
}