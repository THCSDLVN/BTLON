package Client.myrestaurantoption;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

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
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import Client.restaurant.Restaurant;
import Client.createmyres.CreateMyRes;
import Client.clientprocess.ClientProcess;
import Client.deletesequencerestaurant.DeleteSequenceRestaurant;

public class MyRestaurantOption extends JFrame {

	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	JPanel panel4 = new JPanel();

	JLabel openlbl = new JLabel("Open Restaurant");
	JLabel addlbl = new JLabel("Add Restaurant");
	JLabel deleteReslbl = new JLabel("Delete Restaurant");
	JLabel deleteSeqlbl = new JLabel("Delete Store");

	public MyRestaurantOption(ClientProcess clientProcess,String resName,String resID,List<String> restaurantnames,List<String> restaurantids,String aid,JComboBox myresCb) {
		super(resName);
		setResizable(false);
		setPreferredSize(new Dimension(340,280));
		pack();
		getContentPane().setLayout(null);

		panel1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		getContentPane().add(panel1);
		panel1.setBounds(0, 0, 340, 70);
		panel1.setLayout(null);

		panel2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		getContentPane().add(panel2);
		panel2.setBounds(0, 70, 340, 70);
		panel2.setLayout(null);

		panel3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		getContentPane().add(panel3);
		panel3.setBounds(0, 140, 340, 70);
		panel3.setLayout(null);

		panel4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		getContentPane().add(panel4);
		panel4.setBounds(0, 210, 340, 70);
		panel4.setLayout(null);

		openlbl.setFont(new Font("Ubuntu", 0, 18));
		openlbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel1.add(openlbl);
		openlbl.setBounds(30, 10, 160, 40);

		addlbl.setFont(new Font("Ubuntu", 0, 18));
		addlbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(addlbl);
		addlbl.setBounds(30, 10, 160, 40);

		deleteReslbl.setFont(new Font("Ubuntu", 0, 17));
		deleteReslbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel3.add(deleteReslbl);
		deleteReslbl.setBounds(30, 10, 160, 40);

		deleteSeqlbl.setFont(new Font("Ubuntu", 0, 18));
		deleteSeqlbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel4.add(deleteSeqlbl);
		deleteSeqlbl.setBounds(30, 10, 160, 40);

		panel1.setBackground(Color.WHITE);
		panel3.setBackground(Color.WHITE);

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
						}
						else{
							JOptionPane.showMessageDialog(null, "Delete Unsuccessfully");
						}
						dispose();
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
		deleteMyResBtn.setBounds(240, 10, 50, 50);
		panel3.add(deleteMyResBtn);
		deleteMyResBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/deleteicon2.png"));

		JButton deleteSeqBtn = new JButton("");
		panel4.add(deleteSeqBtn);
		if(resID == null){
			deleteSeqBtn.setEnabled(false);
		}
		deleteSeqBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("dataQuery{SequenceRestaurant~Address~\"\"~ResID = '" + resID +"'~\"\"~\"\"}");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				List<List<String>> result = new ArrayList();
				result = clientProcess.getResultList();
				clientProcess.setResultList();
				
				List<String> listAdd = new ArrayList();
				for(List<String> innerLs : result) {
					for (Iterator<String> j = innerLs.iterator(); j.hasNext();) {
						listAdd.add(j.next().toString());
					}
				}

				new DeleteSequenceRestaurant(clientProcess,resName,resID,restaurantnames,restaurantids,listAdd,aid,myresCb);
				dispose();
			}
		});
		deleteSeqBtn.setIcon(new ImageIcon(""));
		deleteSeqBtn.setBounds(240, 10, 50, 50);
		deleteSeqBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/deleteicon1.png"));

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
		viewManageBtn.setBounds(240, 10, 50, 50);
		panel1.add(viewManageBtn);
		viewManageBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/openicon.png"));
		
		JButton addMyNewResBtn = new JButton("");
		addMyNewResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMyRes(aid,clientProcess,myresCb,restaurantnames,restaurantids).setVisible(true);
				dispose();
			}
		});
		addMyNewResBtn.setBounds(240, 10, 50, 50);
		panel2.add(addMyNewResBtn);
		addMyNewResBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/addicon.png"));
	}
}
