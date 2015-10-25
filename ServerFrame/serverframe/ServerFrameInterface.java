package serverframe;

import javax.swing.JButton;
import javax.swing.JLabel;

public interface ServerFrameInterface {
	public JButton buttonExit = new JButton("Exit");
	public JButton buttonQuery = new JButton("Data Query");
	public JButton buttonUpdate = new JButton("Update Data");
	public JButton buttonInsert= new JButton("Insert Data");
	public JButton buttonDelete = new JButton("Delete Data");
	public JLabel diagramLabel = new JLabel("ERRDIAGRAM");
	public JLabel appNameLabel = new JLabel();
}