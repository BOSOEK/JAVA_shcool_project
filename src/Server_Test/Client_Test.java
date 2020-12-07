package Server_Test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class Client_Test {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("192.168.154.1", 9000);
			java.io.InputStream in = socket.getInputStream();
			java.io.OutputStream out = socket.getOutputStream();
			String str = "Hello World!";
			out.write(str.getBytes());
			byte arr[] = new byte[100];
			in.read(arr);
			System.out.println(new String(arr));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				socket.close();
			}
			catch (Exception e) {
				
			}
		}
	}

}
