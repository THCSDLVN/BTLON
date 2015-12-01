package Client.myrestaurantoption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import Client.restaurant.Restaurant;
import Client.createmyres.CreateMyRes;
import Client.clientprocess.ClientProcess;

public class MyRestaurantOption extends JFrame {

	public MyRestaurantOption(ClientProcess clientProcess,String resName,String resID,List<String> restaurantnames,List<String> restaurantids,String aid,JComboBox myresCb) {
		super(resName);
		setResizable(false);
		setPreferredSize(new Dimension(225,99));
		pack();
		getContentPane().setLayout(null);
		
		JButton deleteMyResBtn = new JButton("");
		deleteMyResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure Want To Delete This Restaurant", "", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.NO_OPTION);
				else{
					if(clientProcess.lock == 0){
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Reservation~ResAddress in (select Address from SequenceRestaurant where ResID = '"+ resID +"')}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.setResultAlterQuery();

						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Provide~ResID = '"+ resID +"'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.setResultAlterQuery();

						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{SequenceRestaurant~ResID = '"+ resID +"'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.setResultAlterQuery();

						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Restaurant~ResID = '"+ resID +"'}");
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
							if(restaurantnames != null){
								restaurantnames.remove(resName);
								restaurantids.remove(resID);
								myresCb.removeAllItems();
								myresCb.setModel(new DefaultComboBoxModel(restaurantnames.toArray()));
							}
							JOptionPane.showMessageDialog(null, "Delete Successfully");
							do{
								;
							}while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("dataQuery{Restaurant~Resname~\"\"~AID = '" + aid + "'~\"\"~\"\"}");
							clientProcess.printRequest();
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							List<List<String>> resultlist = new ArrayList();
							resultlist = clientProcess.getResultList();
							clientProcess.setResultList();
							
							if(resultlist.size() == 0){
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("deleteDataQuery{AccountAssignment~RLID = 'ROLE0002' and AID = '" + aid + "'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.setResultAlterQuery();
							}
							dispose();
						}
						else{
							JOptionPane.showMessageDialog(null, "Delete Unsuccessfully");
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
				}
			}
		});
		if(resID == null){
			deleteMyResBtn.setEnabled(false);
		}
		deleteMyResBtn.setBounds(154, 12, 59, 59);
		getContentPane().add(deleteMyResBtn);
		deleteMyResBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/deleteicon.png"));
		deleteMyResBtn.setBackground(Color.WHITE);

		JButton viewManageBtn = new JButton("");
		viewManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restaurant restaurant = new Restaurant(clientProcess,resID,resName);
				dispose();
			}
		});
		if(resID == null){
			viewManageBtn.setEnabled(false);
		}
		viewManageBtn.setBounds(12, 12, 59, 59);
		getContentPane().add(viewManageBtn);
		viewManageBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/openicon.png"));
		viewManageBtn.setBackground(Color.WHITE);
		
		JButton addMyNewResBtn = new JButton("");
		addMyNewResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMyRes(aid,clientProcess,myresCb,restaurantnames,restaurantids).setVisible(true);
				dispose();
			}
		});
		addMyNewResBtn.setBounds(83, 12, 59, 59);
		getContentPane().add(addMyNewResBtn);
		addMyNewResBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/addicon.png"));
		addMyNewResBtn.setBackground(Color.WHITE);
	}
}
