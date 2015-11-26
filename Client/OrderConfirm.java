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

import Client.clientprocess.ClientProcess;

public class OrderConfirm extends JDialog {

	public int quantity = 1;
	public int total ;

	public JLabel foodnameAvaLbl = new JLabel();
	public JLabel foodnameLbl;
	public JLabel lblFood;
	public JLabel lblUsd = new JLabel("VND");
	
	public JSpinner spinner = new JSpinner();
	
	public JButton btnConfirm = new JButton("Confirm");

	public OrderConfirm(ClientProcess clientProcess,String foodname,int foodPrice,String aid,String orderDate,String resAddress) {
		total = quantity * foodPrice;
		setPreferredSize(new Dimension(297, 182));
		setResizable(false);
		pack();
		getContentPane().setLayout(null);
		
		foodnameAvaLbl.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/foodname.png"));
		foodnameAvaLbl.setBounds(12, 12, 45, 41);
		getContentPane().add(foodnameAvaLbl);
		
		foodnameLbl = new JLabel(foodname);
		foodnameLbl.setBounds(53, 12, 159, 30);
		getContentPane().add(foodnameLbl);
		
		lblFood = new JLabel(Integer.toString(total),JLabel.RIGHT);
		lblFood.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/coins.png"));
		lblFood.setBounds(12, 54, 159, 41);
		getContentPane().add(lblFood);
		
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				quantity = (int)spinner.getValue();
				total = quantity * foodPrice;
				lblFood.setText(Integer.toString(total));
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(211, 18, 51, 20);
		getContentPane().add(spinner);
		
		lblUsd.setBounds(221, 54, 51, 41);
		getContentPane().add(lblUsd);
		
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("insertDataQuery(Reservation~" + aid + "~" + resAddress + "~" + foodname + "~" + orderDate + "~" + Integer.toString(quantity) + "~0~" + Integer.toString(total) +"~\"\")");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
				dispose();
			}
		});
		btnConfirm.setBounds(95, 118, 117, 25);
		getContentPane().add(btnConfirm);
	}
}
