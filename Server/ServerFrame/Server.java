import java.net.ServerSocket;

import serverframe.ServerFrame;
import TCPThread.TCPThread;

import javax.swing.SwingUtilities;

public class Server {
	public final static int DEFAULT_PORT = 5000;
		public static void main(String[] args) {
		try{
			ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					ServerFrame serverFrame = new ServerFrame();
				}
			});
			while(true){
				Runnable t = new TCPThread(serverSocket.accept());
				new Thread(t).start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}