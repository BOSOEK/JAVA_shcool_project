package Server_Test;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class Test {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(9000);
			socket = serverSocket.accept();
			java.io.InputStream in = socket.getInputStream();
			java.io.OutputStream out = socket.getOutputStream();
			byte arr[] = new byte[100];
			in.read(arr);
			System.out.println(new String(arr));
			String str = "Hi Client!";
			out.write(str.getBytes());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				socket.close();
			}
			catch (Exception ignored) {
				
			}
			try {
				serverSocket.close();
			}
			catch (Exception ignored) {}
		}
	}

}
