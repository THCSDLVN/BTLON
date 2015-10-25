package insertdataframe;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JFrame;

public interface InsertDataFrameInterface {
	public JFrame frame = new JFrame("Insert Data Frame");

	public JPanel panelRestaurant = new JPanel();
	public JPanel panelAccount = new JPanel();
	public JPanel panelMenu = new JPanel();
	public JPanel panelProvide = new JPanel();
	public JPanel panelReservations = new JPanel();
	public JPanel panelFoodShip = new JPanel();

	public JTabbedPane tabbedPane = new JTabbedPane();
}