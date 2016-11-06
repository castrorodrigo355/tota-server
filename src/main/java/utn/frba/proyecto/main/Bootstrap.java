package utn.frba.proyecto.main;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import utn.frba.proyecto.utils.PasswordUtil;
import utn.frba.proyecto.entities.Marcas;
import utn.frba.proyecto.entities.Usuarios;
import utn.frba.proyecto.entities.Publicidades;
import utn.frba.proyecto.entities.Ofertas;
import utn.frba.proyecto.entities.Ubicaciones;
import utn.frba.proyecto.entities.Camaras;
import utn.frba.proyecto.entities.Televisores;

import utn.frba.proyecto.repositorios.RepositorioMarcas;
import utn.frba.proyecto.repositorios.RepositorioUsuarios;
import utn.frba.proyecto.repositorios.RepositorioPublicidades;
import utn.frba.proyecto.repositorios.RepositorioOfertas;
import utn.frba.proyecto.repositorios.RepositorioUbicaciones;
import utn.frba.proyecto.repositorios.RepositorioCamaras;
import utn.frba.proyecto.repositorios.RepositorioTelevisores;

public class Bootstrap implements WithGlobalEntityManager, TransactionalOps {

	public static void main(String[] args) {

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.run();
	}
	
	private void run() {
		withTransaction(() -> {
			
			Usuarios rodrigo = new Usuarios("Rodrigo", "Castro", PasswordUtil.hashPassword("admin"),"rodrigo.castro@gmail.com", true);
			Usuarios eduardo = new Usuarios("Eduardo", "Toledo", PasswordUtil.hashPassword("admin"),"eduardo.toledo@gmail.com", true);
			Usuarios cristian = new Usuarios("Cristian", "Guemes", PasswordUtil.hashPassword("admin"),"cristian.guemes@gmail.com", true);
			Usuarios federico = new Usuarios("Federico", "Varela", PasswordUtil.hashPassword("admin"),"federico.varela@gmail.com", true);
			Usuarios ezequiel = new Usuarios("Ezequiel", "Leverone", PasswordUtil.hashPassword("admin"),"ezequiel.leverone@gmail.com", true);
			Usuarios fedeA = new Usuarios("Fede", "Alva", PasswordUtil.hashPassword("admin"), "fede.alva@gmail.com",false);
			Usuarios fedeC = new Usuarios("fede", "cze", PasswordUtil.hashPassword("admin"), "fede.cze@gmail.com",false);
			Usuarios eze = new Usuarios("ezequiel", "ogando", PasswordUtil.hashPassword("admin"),"eze.ogando@gmail.com", false);
			Usuarios ana = new Usuarios("ana", "sosa", PasswordUtil.hashPassword("admin"), "ana.sosa@gmail.com", false);
			
			Marcas marca1 = new Marcas("Adidas", "Imposible is nothing");			marca1.agregarUsuario(fedeA);
			Marcas marca2 = new Marcas("Nike", "Just do it");						marca2.agregarUsuario(fedeC);
			Marcas marca3 = new Marcas("Salomon", "MontaÃ±a, vino y partuza");		marca3.agregarUsuario(eze);
			Marcas marca4 = new Marcas("Topper", "No me compra nadie");				marca4.agregarUsuario(ana);

			Publicidades p1 = new Publicidades("H", 75, 85, 8, 11, "desc1", "path1");		Ofertas oferta1 = new Ofertas("45%");
			Publicidades p2 = new Publicidades("M", 65, 75, 10, 13, "desc2", "path2");		Ofertas oferta2 = new Ofertas("25%");
			Publicidades p3 = new Publicidades("H", 40, 45, 12, 15, "desc3", "path3");		Ofertas oferta3 = new Ofertas("35%");
			Publicidades p4 = new Publicidades("M", 45, 55, 14, 17, "desc4", "path4");		Ofertas oferta4 = new Ofertas("10%");
			Publicidades p5 = new Publicidades("H", 35, 45, 18, 21, "desc5", "path5");		Ofertas oferta5 = new Ofertas("15%");
			Publicidades p6 = new Publicidades("M", 25, 35, 20, 23, "desc6", "path6");		Ofertas oferta6 = new Ofertas("20%");
			Publicidades p7 = new Publicidades("H", 55, 65, 16, 19, "desc7", "path7");
			Publicidades p8 = new Publicidades("M", 60, 80, 21, 24, "desc8", "path8");
			Publicidades p9 = new Publicidades("M", 10, 15, 10, 15, "desc9", "path9");
			
			oferta1.setPublicidades(p1); oferta2.setPublicidades(p2); oferta3.setPublicidades(p3);
			oferta4.setPublicidades(p7); oferta5.setPublicidades(p8); oferta6.setPublicidades(p9);
			
			/*
			p1.setOferta(oferta1);
			p2.setOferta(oferta2);
			p3.setOferta(oferta3);
			p7.setOferta(oferta4);
			p8.setOferta(oferta5); 
			p9.setOferta(oferta6);
			*/ 
			
			marca1.agregarUsuario(fedeA); 
			marca2.agregarUsuario(fedeC); 
			marca3.agregarUsuario(eze); 
			marca4.agregarUsuario(ana);
			
			marca1.agregarPublicidad(p1); marca1.agregarPublicidad(p2);
			marca2.agregarPublicidad(p3); marca2.agregarPublicidad(p4);
			
			marca4.agregarPublicidad(p5); marca4.agregarPublicidad(p6);
			marca4.agregarPublicidad(p7); marca4.agregarPublicidad(p8); marca4.agregarPublicidad(p9);
			
			RepositorioPublicidades.getInstance().addPublicidad(p1);
			RepositorioPublicidades.getInstance().addPublicidad(p2);
			RepositorioPublicidades.getInstance().addPublicidad(p3);
			RepositorioPublicidades.getInstance().addPublicidad(p4);
			RepositorioPublicidades.getInstance().addPublicidad(p5);
			RepositorioPublicidades.getInstance().addPublicidad(p6);
			RepositorioPublicidades.getInstance().addPublicidad(p7);
			RepositorioPublicidades.getInstance().addPublicidad(p8);
			RepositorioPublicidades.getInstance().addPublicidad(p9);
			
			RepositorioOfertas.getInstance().addOferta(oferta1);
			RepositorioOfertas.getInstance().addOferta(oferta2);
			RepositorioOfertas.getInstance().addOferta(oferta3);
			RepositorioOfertas.getInstance().addOferta(oferta4);
			RepositorioOfertas.getInstance().addOferta(oferta5);
			RepositorioOfertas.getInstance().addOferta(oferta6);
			
			RepositorioUsuarios.getInstance().agregarUsuario(rodrigo);
			RepositorioUsuarios.getInstance().agregarUsuario(eduardo);
			RepositorioUsuarios.getInstance().agregarUsuario(cristian);
			RepositorioUsuarios.getInstance().agregarUsuario(federico);
			RepositorioUsuarios.getInstance().agregarUsuario(ezequiel);
			RepositorioUsuarios.getInstance().agregarUsuario(fedeA);
			RepositorioUsuarios.getInstance().agregarUsuario(fedeC);
			RepositorioUsuarios.getInstance().agregarUsuario(eze);
			RepositorioUsuarios.getInstance().agregarUsuario(ana);
			
			RepositorioMarcas.getInstance().agregarMarca(marca1);
			RepositorioMarcas.getInstance().agregarMarca(marca2);
			RepositorioMarcas.getInstance().agregarMarca(marca3);
			RepositorioMarcas.getInstance().agregarMarca(marca4);
			
			// ********************************************************************************************
			
			Ubicaciones ubicacion1 = new Ubicaciones("Ubicacion #1");
			Ubicaciones ubicacion2 = new Ubicaciones("Ubicacion #2");
			Ubicaciones ubicacion3 = new Ubicaciones("Ubicacion #3");

			Camaras camara1 = new Camaras("10.24.151", "111.111"); Camaras camara2 = new Camaras("10.24.152", "222.222");
			Camaras camara3 = new Camaras("10.24.153", "333.333"); Camaras camara4 = new Camaras("10.24.154", "444.444");

			Televisores t1 = new Televisores("Techo"); Televisores t2 = new Televisores("Piso");
			Televisores t3 = new Televisores("Pared"); Televisores t4 = new Televisores("Puerta");
			Televisores t5 = new Televisores("Banio"); Televisores t6 = new Televisores("Escalera");
			Televisores t7 = new Televisores("Poste"); Televisores t8 = new Televisores("McDonald");

			camara1.agregarTelevisor(t1); camara1.agregarTelevisor(t2); 
			camara2.agregarTelevisor(t3); camara2.agregarTelevisor(t4); 
			camara3.agregarTelevisor(t5); camara3.agregarTelevisor(t6); 
			camara4.agregarTelevisor(t7); camara4.agregarTelevisor(t8);

			ubicacion1.agregarCamara(camara1); ubicacion2.agregarCamara(camara2);
			ubicacion3.agregarCamara(camara3); ubicacion3.agregarCamara(camara4);
			
			ubicacion1.agregarTelevisor(t1); ubicacion1.agregarTelevisor(t2);
			ubicacion1.agregarTelevisor(t3); ubicacion2.agregarTelevisor(t4); 
			ubicacion2.agregarTelevisor(t5); ubicacion2.agregarTelevisor(t6);
			ubicacion3.agregarTelevisor(t7); ubicacion3.agregarTelevisor(t8);

			RepositorioTelevisores.getInstance().addTelevisor(t1);
			RepositorioTelevisores.getInstance().addTelevisor(t2);
			RepositorioTelevisores.getInstance().addTelevisor(t3);
			RepositorioTelevisores.getInstance().addTelevisor(t4);
			RepositorioTelevisores.getInstance().addTelevisor(t5);
			RepositorioTelevisores.getInstance().addTelevisor(t6);
			RepositorioTelevisores.getInstance().addTelevisor(t7);
			RepositorioTelevisores.getInstance().addTelevisor(t8);

			RepositorioCamaras.getInstance().addCamara(camara1); RepositorioCamaras.getInstance().addCamara(camara2);
			RepositorioCamaras.getInstance().addCamara(camara3); RepositorioCamaras.getInstance().addCamara(camara4);

			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion1);
			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion2);
			RepositorioUbicaciones.getInstance().agregarUbicacion(ubicacion3);
			// ********************************************************************************************
		});
		System.exit(0);
	}
}