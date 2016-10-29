package utn.frba.proyecto.main;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Televisores;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.repositorios.RepositorioCamaras;
import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.repositorios.RepositorioOfertas;
import utn.frba.proyecto.repositorios.RepositorioPublicidades;
import utn.frba.proyecto.repositorios.RepositorioTelevisores;
import utn.frba.proyecto.repositorios.RepositorioUbicaciones;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.utils.PasswordUtil;

public class Bootstrap implements WithGlobalEntityManager, TransactionalOps {

	public static void main(String[] args) {

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.run();
	}

	private void run() {
		withTransaction(() -> {
			Usuarios rodrigo = new Usuarios("Rodrigo", "Castro", PasswordUtil.hashPassword("admin"), "rodrigo.castro@gmail.com", true);
			RepositorioUsuarios.getInstance().agregarUsuario(rodrigo);
			
			Usuarios fedeA = new Usuarios("Fede", "Alva", PasswordUtil.hashPassword("admin"), "fede.alva@gmail.com", false);
			Usuarios fedeC = new Usuarios("fede", "cze", PasswordUtil.hashPassword("admin"), "fede.cze@gmail.com", false);
			Usuarios eze = new Usuarios("ezequiel", "ogando", PasswordUtil.hashPassword("admin"), "eze.ogando@gmail.com", false);
			Usuarios ana = new Usuarios("ana", "sosa", PasswordUtil.hashPassword("admin"), "ana.sosa@gmail.com", false);
			
			RepositorioUsuarios.getInstance().agregarUsuario(fedeA);
			RepositorioUsuarios.getInstance().agregarUsuario(fedeC);
			RepositorioUsuarios.getInstance().agregarUsuario(eze);
			RepositorioUsuarios.getInstance().agregarUsuario(ana);
					
			
			// ********************************************************************************************
			Ubicaciones ubicacion1 = new Ubicaciones("Ubicacion #1"); Ubicaciones ubicacion2 = new Ubicaciones("Ubicacion #2"); Ubicaciones ubicacion3 = new Ubicaciones("Ubicacion #3");
			
			Camaras camara1 = new Camaras("10.24.192.151"); Camaras camara2 = new Camaras("10.24.192.152"); Camaras camara3 = new Camaras("10.24.192.153"); 
			Camaras camara4 = new Camaras("10.24.192.154"); Camaras camara5 = new Camaras("10.24.192.155"); Camaras camara6 = new Camaras("10.24.192.156"); 
			
			Televisores televisor1 = new Televisores("Techo"); Televisores televisor2 = new Televisores("Piso");
			Televisores televisor3 = new Televisores("Pared"); Televisores televisor4 = new Televisores("Puerta");
			Televisores televisor5 = new Televisores("Banio"); Televisores televisor6 = new Televisores("Escalera");
			Televisores televisor7 = new Televisores("Ascensor"); Televisores televisor8 = new Televisores("Poste");
			
			camara1.agregarTelevisor(televisor1); camara1.agregarTelevisor(televisor2);
			camara2.agregarTelevisor(televisor3); camara2.agregarTelevisor(televisor4); camara2.agregarTelevisor(televisor5);
			camara3.agregarTelevisor(televisor6); camara3.agregarTelevisor(televisor7); camara3.agregarTelevisor(televisor8);
			
			ubicacion1.agregarCamara(camara1); ubicacion1.agregarCamara(camara2);
			ubicacion2.agregarCamara(camara3); ubicacion2.agregarCamara(camara4);
			ubicacion3.agregarCamara(camara5); ubicacion3.agregarCamara(camara6);
			
			ubicacion1.agregarTelevisor(televisor1); ubicacion1.agregarTelevisor(televisor2); ubicacion1.agregarTelevisor(televisor3);
			ubicacion2.agregarTelevisor(televisor4); ubicacion2.agregarTelevisor(televisor5); ubicacion2.agregarTelevisor(televisor6);
			ubicacion3.agregarTelevisor(televisor7); ubicacion3.agregarTelevisor(televisor8);
			
			RepositorioTelevisores.getInstance().addTelevisor(televisor1); RepositorioTelevisores.getInstance().addTelevisor(televisor2);
			RepositorioTelevisores.getInstance().addTelevisor(televisor3); RepositorioTelevisores.getInstance().addTelevisor(televisor4);
			RepositorioTelevisores.getInstance().addTelevisor(televisor5); RepositorioTelevisores.getInstance().addTelevisor(televisor6);
			RepositorioTelevisores.getInstance().addTelevisor(televisor7); RepositorioTelevisores.getInstance().addTelevisor(televisor8);
			
			RepositorioCamaras.getInstance().addCamara(camara1); RepositorioCamaras.getInstance().addCamara(camara2);
			RepositorioCamaras.getInstance().addCamara(camara3); RepositorioCamaras.getInstance().addCamara(camara4);
			RepositorioCamaras.getInstance().addCamara(camara5); RepositorioCamaras.getInstance().addCamara(camara6);
			
			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion1);
			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion2);
			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion3);			
			// ********************************************************************************************
			Marcas marca1 = new Marcas("Adidas", "Imposible is nothing");
			Marcas marca2 = new Marcas("Nike", "Just do it");
			Marcas marca3 = new Marcas("Salomon", "Montaña, vino y partuza");
			Marcas marca4 = new Marcas("Topper", "No me compra nadie");

			Publicidades p1 = new Publicidades("H",75,85,8,11,"desc1", "path1"); 
			Publicidades p2 = new Publicidades("M",65,75,10,13,"desc2","path2");
			Publicidades p3 = new Publicidades("H",40,45,12,15,"desc3","path3"); 
			Publicidades p4 = new Publicidades("M",45,55,14,17,"desc4","path4");
			Publicidades p5 = new Publicidades("H",35,45,18,21,"desc5","path5"); 
			Publicidades p6 = new Publicidades("M",25,35,20,23,"desc6","path6");
			Publicidades p7 = new Publicidades("H",55,65,16,19,"desc7","path7"); 
			Publicidades p8 = new Publicidades("M",60,80,21,24,"desc8","path8");
			Publicidades p9 = new Publicidades("M",10,15,10,15,"desc9","path9");
			
			Ofertas oferta1 = new Ofertas("45%");
			Ofertas oferta2 = new Ofertas("25%");
			Ofertas oferta3 = new Ofertas("35%");
			Ofertas oferta4 = new Ofertas("10%");
			Ofertas oferta5 = new Ofertas("15%");
			
			oferta1.setPublicidades(p1);
			oferta2.setPublicidades(p2);
			
			oferta3.setPublicidades(p7);
			oferta4.setPublicidades(p8);
			oferta5.setPublicidades(p9);
			
			RepositorioPublicidades.getInstance().addPublicidad(p1); RepositorioPublicidades.getInstance().addPublicidad(p2);
			RepositorioPublicidades.getInstance().addPublicidad(p3); RepositorioPublicidades.getInstance().addPublicidad(p4);
			RepositorioPublicidades.getInstance().addPublicidad(p5); RepositorioPublicidades.getInstance().addPublicidad(p6);
			RepositorioPublicidades.getInstance().addPublicidad(p7); RepositorioPublicidades.getInstance().addPublicidad(p8);
																	 RepositorioPublicidades.getInstance().addPublicidad(p9);

			RepositorioOfertas.getInstance().addOferta(oferta1); 
			RepositorioOfertas.getInstance().addOferta(oferta2);
			RepositorioOfertas.getInstance().addOferta(oferta3); 
			RepositorioOfertas.getInstance().addOferta(oferta4);
			RepositorioOfertas.getInstance().addOferta(oferta5);
			
			marca1.agregarPublicidad(p1); marca1.agregarPublicidad(p2); 
			marca2.agregarPublicidad(p3); marca2.agregarPublicidad(p4);
			marca3.agregarPublicidad(p5); marca3.agregarPublicidad(p6);
			
			marca4.agregarPublicidad(p7); marca4.agregarPublicidad(p8); marca4.agregarPublicidad(p9);
			
			RepositorioMarcas.getInstance().addBrand(marca1);
			RepositorioMarcas.getInstance().addBrand(marca2);
			RepositorioMarcas.getInstance().addBrand(marca3);
			RepositorioMarcas.getInstance().addBrand(marca4);
			
			fedeA.setMarca(marca1);
			RepositorioUsuarios.getInstance().agregarUsuario(fedeA);
			
			fedeC.setMarca(marca2);
			RepositorioUsuarios.getInstance().agregarUsuario(fedeC);

			eze.setMarca(marca3);
			RepositorioUsuarios.getInstance().agregarUsuario(eze);
			
			ana.setMarca(marca4);
			RepositorioUsuarios.getInstance().agregarUsuario(ana);
		
		});
		System.exit(0);
	}
}