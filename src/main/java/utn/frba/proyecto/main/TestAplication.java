package utn.frba.proyecto.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import utn.frba.proyecto.repositorios.RepositorioOfertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Ofertas;
import static spark.Spark.*;

public class TestAplication {

	public static void main(String[] args) {
    	
		/*
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
    	*/
		
		/*
		RepositorioOfertas rof = new RepositorioOfertas();
		List<Ofertas> ofertas = rof.getAllOfertas();
		for(Ofertas unaOferta : ofertas){
			System.out.println(unaOferta.toString());
		}*/
		
		/*
		int posicionGuion = 0;
		String marca = "24-adidas";
		for(int i = 0; i < marca.length(); i++){
			if(marca.charAt(i) == '-'){
				posicionGuion = i;
			}
		}
		String idMarca = marca.substring(0, posicionGuion);
		System.out.println("Marca ID: " + idMarca);
		*/
		
		File origen = new File("C:/Users/LaTota/Desktop/origen/gato.jpg");
        File destino = new File("C:/Users/LaTota/Desktop/destino/gato2.jpg");

        try {
                InputStream in = new FileInputStream(origen);
                OutputStream out = new FileOutputStream(destino);
                        
                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }
        
                in.close();
                out.close();
        } catch (IOException ioe){
                ioe.printStackTrace();
        }
    }
}