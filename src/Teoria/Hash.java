package Teoria;
import java.security.MessageDigest;

public class Hash {
	public static void main(String[] args) {
		try {
			System.out.println("1.- Obteniendo la instancia con algoritmo MD5");
			MessageDigest md = MessageDigest.getInstance("MD5");
			System.out.println("2.- Actualizando el contenido de la instancia");
			byte[] c1 = "Primera cadena".getBytes();
			byte[] c2 = "Segunda caden".getBytes();
			byte[] c3 = "Tercera cadena".getBytes();
			md.update(c1);
			md.update(c2);
			md.update(c3);
			System.out.println("3.- Calculando el resumen");
			byte[] resumen = md.digest();
			System.out.println("Resumen 1: " + new String(resumen));
			System.out.println("- Reiniciando la instancia -");
			md.reset();
			System.out.println("2.- Actualizando el contenido de la instancia");
			byte[] c4 = "Cuarta cadena".getBytes();
			md.update(c4);
			System.out.println("3.- Calculando el resumen");
			resumen = md.digest();
			System.out.println("Resumen 2: " + new String(resumen));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}