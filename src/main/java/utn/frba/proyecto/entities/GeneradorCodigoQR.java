package utn.frba.proyecto.entities;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class GeneradorCodigoQR {
	private static final int ancho = 200;
	private static final int alto = 200;
	private static final String formato = "png";
	private static final String ruta = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/qrs/";
	// private static final String ruta = "C:/Users/LaTota/workspace50/tota-server-master/tota-server/src/main/resources/public/qrs/";
	// /img
	// /of
	public GeneradorCodigoQR(){}
		
	public void generarCodigoQR(String nombreImagenQR, String datos) throws FileNotFoundException, IOException, WriterException {
		BitMatrix matrix;
		MultiFormatWriter writer = new MultiFormatWriter();
		matrix = ((com.google.zxing.Writer) writer).encode(datos, BarcodeFormat.QR_CODE, ancho, alto);

		BufferedImage image = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				int grayValue = (matrix.get(x, y) ? 0 : 1) & 0xff;
				image.setRGB(x, y, (grayValue == 0 ? 0 : 0xFFFFFF));
			}
		}
		FileOutputStream qrCode = new FileOutputStream(ruta+nombreImagenQR);
		ImageIO.write(image, formato, qrCode);
		qrCode.close();
		System.out.println("Codigo QR Generado");
	}
}