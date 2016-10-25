package utn.frba.proyecto.entities;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Mezclador {
	
	private static final String rutaImagenPublicidad = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/img";
	private static final String rutaImagenQR = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/qrs";
	private static final String rutaOfertas = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/ofertas";
	
	public Mezclador(){}
	
	public void mezclarImagenes(String imgPublicidad, String imgQR, String imgFinal) throws IOException{

		BufferedImage imagenPublicidad = ImageIO.read(new File(rutaImagenPublicidad, imgPublicidad));
		BufferedImage imagenQR = ImageIO.read(new File(rutaImagenQR, imgQR));

		int w = Math.max(imagenPublicidad.getWidth(), imagenQR.getWidth());
		int h = Math.max(imagenPublicidad.getHeight(), imagenQR.getHeight());
		
		BufferedImage imagenCombinada = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		Graphics g = imagenCombinada.getGraphics();
		g.drawImage(imagenPublicidad, 0, 0, null);
		g.drawImage(imagenQR, 0, 0, null);

		ImageIO.write(imagenCombinada, "PNG", new File(rutaOfertas, imgFinal));
		
		File fotoParaBorrar = new File(rutaImagenQR, imgQR);
		fotoParaBorrar.delete();
	}
}