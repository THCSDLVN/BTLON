import javax.swing.SwingUtilities;

import Client.login.Login;
import Client.clientprocess.ClientProcess;
import Client.clientTCPThread.ClientTCPThread;

public class Client {
	public static ClientProcess clientProcess = new ClientProcess();
	public static void main(String[] args) {
		try{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					Login login = new Login(clientProcess);		
				}
			});
			ClientTCPThread clientThread = new ClientTCPThread(clientProcess);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}