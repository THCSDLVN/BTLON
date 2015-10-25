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

import buttonflag.ButtonFlag;

import insertdataframe.insertmenudata.InsertMenuData;
import insertdataframe.insertaccountdata.InsertAccountData;
import insertdataframe.insertrestaurantdata.InsertRestaurantData;
import insertdataframe.insertreservationdata.InsertReservationData;
import insertdataframe.insertprovidedata.InsertProvideData;
import insertdataframe.insertfoodshipdata.InsertFoodShipData;

public class InsertDataFrame implements InsertDataFrameInterface {
	public InsertMenuData insertMenuData = new InsertMenuData(panelMenu,frame);
	public InsertAccountData insertAccountData = new InsertAccountData(panelAccount,frame);
	public InsertRestaurantData insertRestaurantData = new InsertRestaurantData(panelRestaurant,frame);
	public InsertReservationData inserReservationData = new InsertReservationData(panelReservations,frame);
	public InsertProvideData insertProvideData = new InsertProvideData(panelProvide,frame);
	public InsertFoodShipData insertFoodShipData = new InsertFoodShipData(panelFoodShip,frame);

	public InsertDataFrame(ButtonFlag buttonFlag){
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(680,440));
		frame.pack();

		frame.getContentPane().add(tabbedPane);
		frame.getContentPane().setLayout(null);

		tabbedPane.setBounds(0, 0, 680, 440);
		tabbedPane.addTab("Restaurant", panelRestaurant);
		tabbedPane.addTab("Account", panelAccount);
		tabbedPane.addTab("Menu", panelMenu);
		tabbedPane.addTab("Provide", panelProvide);
		tabbedPane.addTab("Reservations", panelReservations);
		tabbedPane.addTab("FoodShip", panelFoodShip);

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