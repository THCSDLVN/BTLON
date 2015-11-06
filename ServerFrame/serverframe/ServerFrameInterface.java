package serverframe;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface ServerFrameInterface {
	public JButton buttonExit = new JButton();
	public JButton buttonQuery = new JButton();	
	public JLabel queryLabel = new JLabel("Data Query");
	public JLabel exitLabel = new JLabel("Exit");
	public JLabel diagramLabel = new JLabel("ERRDIAGRAM");
	public JLabel appNameLabel = new JLabel();
}