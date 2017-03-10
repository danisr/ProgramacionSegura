package Ejercicios;

import java.security.MessageDigest;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Hash {
	// Recibe como argumento de programa el algoritmo de resumen.
	// Para coger 2ª vez ctrl+z hacer run as java aplication.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		byte[] msj = null;
		boolean salir = false;
		try {
			System.out.println("Obteniendo la instancia con algoritmo: " + args[0]);
			MessageDigest md = MessageDigest.getInstance(args[0]);
			System.out.println("Actualizando el contenido de la instancia...");

			int i = 1;			
			while (!salir) {
				try {	
					System.out.println("Escriba su mensaje... ('Ctrl+z' PARA TERMINAR):");
					String mensaje = scan.next();
					System.out.println("Mensaje " + i + ": " + mensaje);
					msj = mensaje.getBytes();
					md.update(msj);
					i++;					
				} catch (NoSuchElementException n) { //ctrl+z
					salir = true;
				}
			}			
			System.out.println("Calculando el resumen...");
			byte[] resumen = md.digest();
			System.err.println("Resumen: " + new String(resumen));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}