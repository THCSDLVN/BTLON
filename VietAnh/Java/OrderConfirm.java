import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class OrderConfirm extends JDialog {

	public int quantity = 1;
	public static int newQuantity ;
	public double total ;

	JLabel foodnameAvaLbl = new JLabel();
	JLabel lblFood = new JLabel(Double.toString(total),JLabel.RIGHT);
	JSpinner spinner = new JSpinner();
	JLabel lblUsd = new JLabel("VND");
	JButton btnConfirm = new JButton("Confirm");

	/**
	 * Create the dialog.
	 */
	public OrderConfirm(AccountGUI aGUI,String foodname,double foodPrice,String AID,String orderDate,String resAddress) {
		total = quantity*foodPrice;
		setResizable(false);
		setBounds(400, 450, 297, 182);
		getContentPane().setLayout(null);
		
		foodnameAvaLbl.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/foodname.png")));
		foodnameAvaLbl.setBounds(12, 12, 45, 41);
		getContentPane().add(foodnameAvaLbl);
		
		JLabel foodnameLbl = new JLabel(foodname);
		foodnameLbl.setBounds(53, 12, 159, 30);
		getContentPane().add(foodnameLbl);
		
		
		lblFood.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/coins.png")));
		lblFood.setBounds(12, 54, 159, 41);
		getContentPane().add(lblFood);
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity = (int)spinner.getValue();
				total = quantity * foodPrice;
				lblFood.setText(Double.toString(total));
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(211, 18, 51, 20);
		getContentPane().add(spinner);
		
		lblUsd.setBounds(221, 54, 51, 41);
		getContentPane().add(lblUsd);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLConnection c = new SQLConnection();
				//Code moi.
				//SQL:select FoodName,Cost from Provide"
				//+ "	where ResID = (select ResID from SequenceRestaurant where Address = '"+resAddress+"') and"
				//+ "	FoodName = '"+foodName+"'
				List<List<String>> returnThisFoodInfo = c.getThisFoodInfo(resAddress, foodname);
				//SQL : 
				if(returnThisFoodInfo.size() == 0){// Neu mon an nay da bi xoa.
					sayAnnouce();
					quit(aGUI);
				}
				else{
					String[][] thisFoodInfo = SolveArrayList.ConvertFromArrayList(returnThisFoodInfo);
					double nowFoodPrice = Double.parseDouble(thisFoodInfo[0][1]);
					if(foodPrice != nowFoodPrice){// Neu mon an nay da bi thay doi gia.
						sayAnnouce();
						quit(aGUI);
					}
					else
						c.insertReservation(AID, resAddress, foodname, orderDate, quantity,foodPrice);// SQL : insert into Reversation 
					//value (AID,resAddress,foodname,orderDate,quantity,'Updating',foodPrice);
				}
				dispose();
			}
		});
		btnConfirm.setBounds(95, 118, 117, 25);
		getContentPane().add(btnConfirm);
	}
	
	//Code moi.
	public OrderConfirm(String foodname,double foodPrice,String AID,String orderDate,String resAddress,int nowQuantity) {
		total = nowQuantity*foodPrice;
		setResizable(false);
		setBounds(400, 450, 297, 182);
		getContentPane().setLayout(null);
		
		JLabel foodnameAvaLbl = new JLabel();
		foodnameAvaLbl.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/foodname.png")));
		foodnameAvaLbl.setBounds(12, 12, 45, 41);
		getContentPane().add(foodnameAvaLbl);
		
		JLabel foodnameLbl = new JLabel(foodname);
		foodnameLbl.setBounds(53, 12, 159, 30);
		getContentPane().add(foodnameLbl);
		
		
		JLabel lblFood = new JLabel(Double.toString(total),JLabel.RIGHT);
		lblFood.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/coins.png")));
		lblFood.setBounds(12, 54, 159, 41);
		getContentPane().add(lblFood);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				newQuantity = (int)spinner.getValue();
				System.out.println(newQuantity);
				total = newQuantity * foodPrice;
				lblFood.setText(Double.toString(total));
			}
		});
		spinner.setModel(new SpinnerNumberModel(nowQuantity, 1, 100, 1));
		spinner.setBounds(211, 18, 51, 20);
		getContentPane().add(spinner);
		
		JLabel lblUsd = new JLabel("VND");
		lblUsd.setBounds(221, 54, 51, 41);
		getContentPane().add(lblUsd);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SQLConnection c = new SQLConnection();
				int success = c.updateNewQuantity(AID, resAddress, foodname, orderDate, newQuantity);
				if(success != 0){
					JOptionPane.showMessageDialog(null, "Succesfully Fix");
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Nha hang khong ton tai");
				}
			}
		});
		btnConfirm.setBounds(95, 118, 117, 25);
		getContentPane().add(btnConfirm);
	}
	// Code moi.
	public void quit(AccountGUI aGUI){
		aGUI.getPanel_1().setVisible(true);
		if(aGUI.getMenuPanel().isVisible())
			aGUI.getMenuPanel().setVisible(false);
		if(aGUI.getMyOrderPanel().isVisible())
			aGUI.getMyOrderPanel().setVisible(false);
	}
	// Code moi.
	public void sayAnnouce(){
		JOptionPane.showMessageDialog(null, "this food is updated !!!");
	}
	public void createView1(){
		
	}
}
