package utn.frba.proyecto.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
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
import utn.frba.proyecto.repositorios.RepositorioPublicidades;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.repositorios.RepositorioOfertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Ofertas;
import static spark.Spark.*;

public class TestAplication {

	public static void main(String[] args) {
    	
		List<Marcas> marcas = RepositorioMarcas.getInstance().listarMarcas();
		Marcas unaMarca = marcas.get(4);
		System.out.println(unaMarca.getDescripcion());
		String tiposDeRopa = unaMarca.getDescripcion();
		System.out.println(tiposDeRopa);
		List<String> items = Arrays.asList(tiposDeRopa.split("\\s*,\\s*"));
		for(String unItem : items){
			System.out.println(unItem);
		}
    }
}