import javax.swing.SwingUtilities;

import Client.login.Login;

public class MakeClientGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Login loginFrame = new Login();
			}
		});
	}
}