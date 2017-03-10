package Teoria;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class SecureClient {
	public static void main(String[] args) {
		try {
			System.out.println("Obteniendo la factoria de sockets cliente");
			SSLSocketFactory socketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
			System.out.println("Creando socket cliente");
			SSLSocket clientSocket = (SSLSocket) socketFactory.createSocket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("Localhost", 5555);
			clientSocket.connect(addr);
			
			OutputStream os = clientSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			String mensaje = "Mensaje desde el cliente transmitido por SSL";
			os.write(mensaje.getBytes());
			System.out.println("Mensaje envido");
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Cliente finalizado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}