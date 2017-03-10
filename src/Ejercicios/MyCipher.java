package Ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyCipher {
	private SecretKey key;
	private String algoritmo;

	public static void main(String[] args) throws NoSuchAlgorithmException {
		MyCipher cipher = new MyCipher("DES");

		Scanner scan = new Scanner(System.in);
		boolean salir = false;
		int seleccion = 0;
		while (!salir) {
			System.out.println("..........................\n" + ". 1 CifradoDES  \n" + ". 2 Descifrado \n"
					+ ". 3 Salir \n" + "..........................");
			try {
				System.out.print("Introduzca el número de la opción deseada: ");
				seleccion = scan.nextInt();
				System.out.println("OPCION SELECCIONADA: " + seleccion);
				switch (seleccion) {
				case 1:
					cipher.cifrar("original.txt", "cifrado.txt"); // Mensaje a cifrar, Fichero destino cifrado
					cipher.guardarKey("clave.txt");
					break;
				case 2:
					cipher.cargarKey(new File("clave.txt"));
					cipher.descifrar("cifrado.txt", "descifrado.txt");
					break;
				case 3: // Salir
					System.err.println("Se cierra el programa");
					System.exit(0);
				default: // No valido
					System.out.println("Opcion invalida: marque un numero de 1 a 3");
					break;
				}
			} catch (Exception e) {
				System.out.println("Excepcion por opcion invalida: marque un numero de 1 a 3");
				// flushing scanner
				e.printStackTrace();
				scan.next();
			}
		}
	}

	public MyCipher(String algoritmo) throws NoSuchAlgorithmException {
		KeyGenerator generator = KeyGenerator.getInstance(algoritmo);
		this.key = generator.generateKey();
		this.algoritmo = algoritmo;
	}

	public MyCipher(File clave) throws NoSuchAlgorithmException {
		this.key = cargarKey(clave);
		this.algoritmo = key.getAlgorithm();
	}

	public void cifrar(String rutaOrigen, String rutaDestino) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File inFile = new File(rutaOrigen);
			in = new FileInputStream(inFile);
			out = new FileOutputStream(rutaDestino);
			byte[] plano = new byte[(int) inFile.length()];
			in.read(plano);
			System.out.println("Mensaje Original: " + new String(plano));

			Cipher cipher = Cipher.getInstance(algoritmo);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] cifrado = cipher.doFinal(plano);
			out.write(cifrado);
			System.out.println("Mensaje Cifrado: " + new String(cifrado));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void descifrar(String rutaOrigen, String rutaDestino) {
		FileInputStream in = null;
		FileOutputStream out = null;
		try {
			File inFile = new File(rutaOrigen);
			in = new FileInputStream(inFile);
			out = new FileOutputStream(rutaDestino);
			byte[] cifrado = new byte[(int) inFile.length()];
			in.read(cifrado);
			System.out.println("Mensaje Cifrado: " + new String(cifrado));

			Cipher cipher = Cipher.getInstance(algoritmo);
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plano = cipher.doFinal(cifrado);
			out.write(plano);
			System.out.println("Mensaje Descifrado: " + new String(plano));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void guardarKey(String ruta) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(ruta);
			byte[] clave = Base64.getEncoder().encode(key.getEncoded());
			out.write(clave);
			System.out.println("Clave Guardada: " + new String(clave));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public SecretKey cargarKey(File inFile) {
		FileInputStream in = null;
		try {
			in = new FileInputStream(inFile);
			byte[] clave = new byte[(int) inFile.length()];
			in.read(clave);
			System.out.println("Clave Recuperada: " + new String(clave));
			byte[] decodedKey = Base64.getDecoder().decode(clave);
			SecretKey key = new SecretKeySpec(decodedKey, 0, decodedKey.length, algoritmo);
			return key;
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}