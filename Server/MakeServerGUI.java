import javax.swing.SwingUtilities;

import serverframe.ServerFrame;

public class MakeServerGUI {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerFrame serverFrame = new ServerFrame();
			}
		});
	}
}