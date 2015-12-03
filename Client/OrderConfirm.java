package Client.orderconfirm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;

import Client.clientprocess.ClientProcess;
import Client.accountGUI.AccountGUI;
import Client.check.Check;

public class OrderConfirm extends JDialog {

	public int quantity = 1;
	public double total ;
	public static int newQuantity;

	public JLabel foodnameAvaLbl = new JLabel();
	public JLabel foodnameLbl;
	public JLabel lblFood;
	public JLabel lblUsd = new JLabel("VND");
	
	public JSpinner spinner = new JSpinner();
	
	public JButton btnConfirm = new JButton("Confirm");

	public Check check = new Check();

	public OrderConfirm(AccountGUI aGUI,ClientProcess clientProcess,String foodname,double foodPrice,String aid,String orderDate,String resAddress) {
		total = quantity * foodPrice;
		initialize(foodname,total);
		
		if(check.check_date_before_after(orderDate)){
			setVisible(true);
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					quantity = (int)spinner.getValue();
					total = quantity * foodPrice;
					lblFood.setText(Double.toString(total));
				}
			});
			spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
			
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(clientProcess.lock == 0){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Provide~Foodname,Cost~\"\"~ResID = (select ResID from SequenceRestaurant where Address = '" + resAddress +"' and Foodname = '" + foodname +"')~\"\"~\"\"}");
						clientProcess.printRequest();
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						
						List<List<String>> returnThisFoodInfo = new ArrayList();
						returnThisFoodInfo = clientProcess.getResultList();
						clientProcess.setResultList();

						if(returnThisFoodInfo.size() == 0){
							sayAnnouce();
							quit(aGUI);
						}
						else{
							do{
								;
							}
							while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("insertDataQuery{Reservation~" + aid + "~" + resAddress + "~" + foodname + "~" + orderDate + "~" + Integer.toString(quantity) + "~Updating~" + Double.toString(foodPrice) +"~Change Foodname,Time,Status,Quantity~Change Status,Delete After Order Day 1 Day~Delete~Deny,Lock}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String result = new String();
							result = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();

							if(!result.equals("0")){
								JOptionPane.showMessageDialog(null,"Order Succesfully");
							}
							else{
								JOptionPane.showMessageDialog(null,"Order Unsuccesfully. Please	Back And Come In Again");
								return;
							}
						}
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					dispose();
				}
			});
		}
		else{
			JOptionPane.showMessageDialog(null,"Order Is Out Of Date. Can't Fix Anything");
		}
	}

	public OrderConfirm(ClientProcess clientProcess,String foodname,double foodPrice,String AID,String orderDate,String resAddress,int nowQuantity) {
		total = nowQuantity*foodPrice;
		newQuantity = nowQuantity;
		initialize(foodname,total);

		if(check.check_date_before_after(orderDate)){
			setVisible(true);
			spinner.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					newQuantity = (int)spinner.getValue();
					total = newQuantity * foodPrice;
					lblFood.setText(Double.toString(total));
				}
			});
			spinner.setModel(new SpinnerNumberModel(nowQuantity, 1, 100, 1));

			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(clientProcess.lock == 0){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Reservation~Quantity~" + newQuantity + "~AID = '" + AID + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'Updating' and Foodname = '" + foodname + "'}");
						clientProcess.printRequest();
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String success = new String();
						success = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();
						if(!success.equals("0")){
							JOptionPane.showMessageDialog(null, "Fix Succesfully");
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null,"Fix Unsuccesfully. Order Can Be Locked Or Be Denied Or Be Lost Or Be Delete. Please Back And Come In Again");
							return ;
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
			});
		}
		else{
			JOptionPane.showMessageDialog(null,"Order Is Out Of Date. Can't Fix Anything");
		}
	}

	public void initialize(String foodname, double total){
		setPreferredSize(new Dimension(297, 182));
		setResizable(false);
		pack();
		getContentPane().setLayout(null);
		setTitle("Order Confirm");

		foodnameAvaLbl.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/foodname.png"));
		foodnameAvaLbl.setBounds(12, 12, 45, 41);
		getContentPane().add(foodnameAvaLbl);
		
		foodnameLbl = new JLabel(foodname);
		foodnameLbl.setBounds(53, 12, 159, 30);
		getContentPane().add(foodnameLbl);

		lblFood = new JLabel(Double.toString(total),JLabel.RIGHT);
		lblFood.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/coins.png"));
		lblFood.setBounds(12, 54, 159, 41);
		getContentPane().add(lblFood);

		lblUsd.setBounds(221, 54, 51, 41);
		getContentPane().add(lblUsd);

		btnConfirm.setBounds(95, 118, 117, 25);
		getContentPane().add(btnConfirm);

		spinner.setBounds(211, 18, 51, 20);
		getContentPane().add(spinner);
	}

	public void quit(AccountGUI aGUI){
		aGUI.getPanel_1().setVisible(true);
		if(aGUI.getMenuPanel().isVisible()){
			aGUI.getMenuPanel().setVisible(false);
		}
		if(aGUI.getMyOrderPanel().isVisible()){
			aGUI.getMyOrderPanel().setVisible(false);
		}
	}

	public void sayAnnouce(){
		JOptionPane.showMessageDialog(null, "This Food Is Updated !!!");
	}
}