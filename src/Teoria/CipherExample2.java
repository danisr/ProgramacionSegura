package Teoria;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Cifrado de un mensaje utilizando la clase Cipher En este ejemplo se usa el
 * algoritmo DES
 **/
public class CipherExample2 {
	public static void main(String[] args) {
		try {
			System.out.println("Obteniendo generador de claves con cifrado DES");
			KeyGenerator keygen = KeyGenerator.getInstance("DES"); //Hay que pasar el mismo algoritmo de anterior clase
			System.out.println("Generando la clave");
			SecretKey key = keygen.generateKey();
			
			System.out.println("Obteniendo objeto Cipher con cifrado DES");
			Cipher miCipher = Cipher.getInstance("DES");
			
			System.out.println("Configurando Cipher par encriptar");
			miCipher.init(Cipher.ENCRYPT_MODE, key); //Se cifra con la llave
			
			System.out.println("Preparando mensaje");
			String mensaje = "Mensaje de prueba";
			String mensaje2 = "Mensaje de prueba";
			System.out.println("mensaje original: " + mensaje); //Mensaje antes de cifrar
			
			System.out.println("Cifrando mensaje");
			String mensajeCifrado = new String(miCipher.doFinal(mensaje.getBytes()));
			String mensajeCifrado2 = new String(miCipher.doFinal(mensaje2.getBytes()));
			System.out.println("Mensaje cifrado1: " + mensajeCifrado);
			System.out.println("Mensaje cifrado2: " + mensajeCifrado);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}