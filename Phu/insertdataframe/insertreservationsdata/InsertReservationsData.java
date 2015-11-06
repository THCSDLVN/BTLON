package insertdataframe.insertreservationsdata;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import sqlfunc.SQLFunc;

public class InsertReservationsData {
	public JLabel labelReservationsName = new JLabel("INSERT RESERVATIONS DATA");
	public JLabel reservationsAIDLabel = new JLabel("AID");
	public JLabel reservationsFoodListLabel = new JLabel("FoodList");
	public JLabel reservationsTimeLabel = new JLabel("Time");
	public JLabel reservationsRIDLabel = new JLabel("FID");
	public JLabel reservationsHelpLabel = new JLabel("HELP");

	public JTextField reservationsTimeTextField = new JTextField();
	public JTextField reservationsFoodListTextField = new JTextField();
	public JTextField reservationsAIDTextField = new JTextField();
	public JTextField reservationsRIDTextField = new JTextField();

	public JButton buttonOKReservations = new JButton("OK");

	public SQLFunc sqlFunc;

	public InsertReservationsData(JPanel panelReservations, JFrame frame){

		panelReservations.setLayout(null);
		
		panelReservations.add(labelReservationsName);
		panelReservations.add(reservationsAIDLabel);
		panelReservations.add(reservationsFoodListLabel);
		panelReservations.add(reservationsTimeLabel);
		panelReservations.add(reservationsRIDLabel);
		panelReservations.add(reservationsHelpLabel);

		panelReservations.add(reservationsTimeTextField);
		panelReservations.add(reservationsFoodListTextField);
		panelReservations.add(reservationsAIDTextField);
		panelReservations.add(reservationsRIDTextField);

		panelReservations.add(buttonOKReservations);

		labelReservationsName.setFont(new Font("Ubuntu", 1, 24)); 
		labelReservationsName.setForeground(new Color(246, 9, 9));
		labelReservationsName.setHorizontalAlignment(SwingConstants.CENTER);
		labelReservationsName.setBounds(180, 20, 350, 28);

		reservationsAIDLabel.setFont(new Font("Ubuntu", 1, 16));
		reservationsAIDLabel.setForeground(new Color(22, 117, 245));
		reservationsAIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationsAIDLabel.setBorder(BorderFactory.createEtchedBorder());
		reservationsAIDLabel.setBounds(140, 80, 120, 40);

		reservationsRIDLabel.setFont(new Font("Ubuntu", 1, 16));
		reservationsRIDLabel.setForeground(new Color(22, 117, 245));
		reservationsRIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationsRIDLabel.setBorder(BorderFactory.createEtchedBorder());
		reservationsRIDLabel.setBounds(140, 140, 120, 40);

		reservationsFoodListLabel.setFont(new Font("Ubuntu", 1, 16));
		reservationsFoodListLabel.setForeground(new Color(22, 117, 245));
		reservationsFoodListLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationsFoodListLabel.setBorder(BorderFactory.createEtchedBorder());
		reservationsFoodListLabel.setBounds(140, 200, 120, 40);

		reservationsTimeLabel.setFont(new Font("Ubuntu", 1, 16));
		reservationsTimeLabel.setForeground(new Color(22, 117, 245));
		reservationsTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationsTimeLabel.setBorder(BorderFactory.createEtchedBorder());
		reservationsTimeLabel.setBounds(140, 260, 120, 40);

		reservationsAIDTextField.setFont(new Font("Ubuntu", 1, 18));
		reservationsAIDTextField.setBounds(320, 80, 250, 40);

		reservationsRIDTextField.setFont(new Font("Ubuntu", 1, 18)); 
		reservationsRIDTextField.setBounds(320, 140, 250, 40);

		reservationsFoodListTextField.setFont(new Font("Ubuntu", 1, 18));
		reservationsFoodListTextField.setBounds(320, 200, 250, 40);

		reservationsTimeTextField.setFont(new Font("Ubuntu", 1, 18)); 
		reservationsTimeTextField.setBounds(320, 260, 250, 40);

		reservationsHelpLabel.setFont(new Font("Ubuntu", 1, 16));
		reservationsHelpLabel.setForeground(new Color(22, 117, 245));
		reservationsHelpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reservationsHelpLabel.setBounds(30, 370, 40, 20);
		reservationsHelpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(reservationsHelpLabel.getMouseListeners().length < 1){
			reservationsHelpLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to insert new reservations data to the database.");
					msg.append("\nInput data into textField correlative to labelName");
					msg.append("\nEach time you can only insert one record to table");
					JOptionPane.showMessageDialog(null,msg.toString(),"Help Of InsertreservationsDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
				}
				
				public void mouseEntered(MouseEvent me) {
					final Map attributes = (new Font("Ubuntu", 1, 16)).getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(new Font(attributes));
				}
				
				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(new Font("Ubuntu", 1, 16));
				}
			});
		}

		buttonOKReservations.setFont(new Font("Ubuntu", 1, 18)); 
		buttonOKReservations.setForeground(new Color(22, 117, 245));
		buttonOKReservations.setText("OK");
		buttonOKReservations.setBounds(270, 360, 120, 30);
		buttonOKReservations.setBackground(Color.WHITE);
		if(buttonOKReservations.getActionListeners().length < 1){
			buttonOKReservations.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					sqlFunc = new SQLFunc();
					String aid = new String(reservationsAIDTextField.getText());
					String rid = new String(reservationsRIDTextField.getText());
					String foodlist = new String(reservationsFoodListTextField.getText());
					String time = new String(reservationsTimeTextField.getText());

					if(sqlFunc.insertDataQuery("Reservations",aid,rid,foodlist,time,"","","","") > 0){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertReservationsDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						
						reservationsAIDTextField.setText("");
						reservationsRIDTextField.setText("");
						reservationsFoodListTextField.setText("");
						reservationsTimeTextField.setText("");
					}
					else{
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
						JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertReservationsDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
					}
				}
			});
		}
		
		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				reservationsAIDTextField.setText("");
				reservationsRIDTextField.setText("");
				reservationsFoodListTextField.setText("");
				reservationsTimeTextField.setText("");
			}
		});
	}
}