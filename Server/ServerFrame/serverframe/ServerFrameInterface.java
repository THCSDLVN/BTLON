package serverframe;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface ServerFrameInterface {
	public JButton buttonExit = new JButton("Exit");
	public JButton buttonQuery = new JButton("Data Query");	
	public JButton buttonDiagram = new JButton("ERRDiagram");
	public JLabel appNameLabel = new JLabel();
}