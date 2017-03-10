package Teoria;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

//Simétrico: al hacer un bind el servidor se queda parado esperando conexión
public class SecureServer {
	public static void main(String[] args) {
		try {
			System.out.println("Obteniendo la factoria de sockets servidor");
			SSLServerSocketFactory serverSocketFactory = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
			System.out.println("Creando socket servidor");
			SSLServerSocket serverSocket = (SSLServerSocket) serverSocketFactory.createServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("Localhost", 5555);
			serverSocket.bind(addr);
			System.out.println("Aceptando conexiones");
			SSLSocket newSocket = (SSLSocket) serverSocket.accept();
			System.out.println("Conexión recibida");
			
			InputStream is = newSocket.getInputStream();
			byte[] mensaje = new byte[25];
			is.read(mensaje);
			System.out.println("Mensaje recibido: " + new String(mensaje));
			
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("Programa servidor finalizado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}