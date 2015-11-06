package insertdataframe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import buttonflag.ButtonFlag;

import insertdataframe.insertmenudata.InsertMenuData;
import insertdataframe.insertaccountdata.InsertAccountData;
import insertdataframe.insertrestaurantdata.InsertRestaurantData;
import insertdataframe.insertreservationsdata.InsertReservationsData;
import insertdataframe.insertprovidedata.InsertProvideData;

public class InsertDataFrame implements InsertDataFrameInterface {
	public InsertMenuData insertMenuData = new InsertMenuData(panelMenu,frame);
	public InsertAccountData insertAccountData = new InsertAccountData(panelAccount,frame);
	public InsertRestaurantData insertRestaurantData = new InsertRestaurantData(panelRestaurant,frame);
	public InsertReservationsData inserReservationsData = new InsertReservationsData(panelReservations,frame);
	public InsertProvideData insertProvideData = new InsertProvideData(panelProvide,frame);

	public InsertDataFrame(ButtonFlag buttonFlag){
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(680,440));

		frame.getContentPane().setLayout(new BorderLayout());
 		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		frame.pack();

		tabbedPane.setBounds(0, 0, 680, 440);
		tabbedPane.addTab("Restaurant", panelRestaurant);
		tabbedPane.addTab("Account", panelAccount);
		tabbedPane.addTab("Menu", panelMenu);
		tabbedPane.addTab("Provide", panelProvide);
		tabbedPane.addTab("Reservations", panelReservations);

		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				buttonFlag.buttonInsertFlag = 1;
				buttonFlag.buttonUpdateFlag = 1;
				buttonFlag.buttonQueryFlag = 1;
				buttonFlag.buttonDeleteFlag = 1;
			}
		});
	}
}