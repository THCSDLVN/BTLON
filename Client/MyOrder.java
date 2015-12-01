package Client.myorder;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Client.clientprocess.ClientProcess;
import Client.orderconfirm.OrderConfirm;
import Client.check.Check;
import Client.hidepanel2.HidePanel2;
import Client.createorder.CreateOrder;

public class MyOrder extends JPanel {
	public JTable orderContentTable = new JTable();
	
	public String aid = "";
	public String resAddress = "";
	public String orderDate = "";
	
	public JLabel lblTime = new JLabel();

	public JButton fixBtn = new JButton("");
	public JButton deleteBtn = new JButton("");
	public JButton btnChangeTime = new JButton("");
	public JButton okBtn = new JButton("");

	public JScrollPane scrollPane = new JScrollPane();
	
	public int row; 		// Dong dc chon trong bang cac mon an trong 1 order.

	public Check check = new Check();
	
	public MyOrder(HidePanel2 parent, ClientProcess clientProcess) {
		this.setBounds(12, 300, 400, 284);
		setLayout(null);
		
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(12, 12, 262, 26);
		add(lblTime);
		
		scrollPane.setBounds(12, 48, 376, 163);
		add(scrollPane);
		
		scrollPane.setViewportView(orderContentTable);
		
		fixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0){
					return;
				}

				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				Double cost = Double.parseDouble((String)getOrderContentTable().getValueAt(row, 2));
				int nowQuantity = Integer.parseInt((String)getOrderContentTable().getValueAt(row, 1));
				OrderConfirm x =  new OrderConfirm(clientProcess,foodname, cost, aid, orderDate, resAddress, nowQuantity);
			}
		});
		fixBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/fix.png"));
		fixBtn.setBounds(266, 218, 55, 54);
		fixBtn.setEnabled(false);
		add(fixBtn);

		orderContentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = orderContentTable.getSelectedRow();
				String status = (String)orderContentTable.getValueAt(row, 3);
				if(status.equals("Updating")){
					getFixBtn().setEnabled(true);
					getDeleteBtn().setEnabled(true);
				}
				else{
					if(status.equals("Deny")){
						getDeleteBtn().setEnabled(true);
					}
					else{
						getFixBtn().setEnabled(false);
						getDeleteBtn().setEnabled(false);
					}
				}
			}
		});
		scrollPane.setViewportView(orderContentTable);
		
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0){
					return;
				}
				if(clientProcess.lock == 0){
					String foodname = (String)getOrderContentTable().getValueAt(row, 0);
					String status = (String)getOrderContentTable().getValueAt(row, 3);
					if(status.equals("Delete")){
						JOptionPane.showMessageDialog(null,"Your " + foodname + " Is Being Waited For Deleting By Restaurant. Can't Do Anything");
					}
					else if(status.equals("OK")){
						JOptionPane.showMessageDialog(null, "Your " + foodname + " Is OK. Can't Do Anything");
					}
					else if(status.equals("Lock")){
						JOptionPane.showMessageDialog(null,"Your " + foodname + " Is Locked. Can't Delete");
					}
					else if(status.equals("Updating")){
						if(check.check_date_before_after(orderDate)){
							int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete " + foodname + "","Delete Confirm",JOptionPane.YES_NO_OPTION);
							if(confirm == JOptionPane.NO_OPTION){
								;
							}
							else{
								String result = new String();

								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("updateDataQuery{Reservation~StatusReser~Delete~AID = '" + aid + "' and Foodname = '" + foodname + "' and Time = '" + orderDate + "' and ResAddress = '" + resAddress +"' and StatusReser = 'Updating'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));

								result = clientProcess.getResultAlterQuery();
								clientProcess.setResultAlterQuery();
								if(result.equals("0")){
									JOptionPane.showMessageDialog(null,"Order Is Lost Or Be Locked Or Be Denied. Please Back And Come In Again");
									return ;
								}
								else{

									do{
										;
									}while(!clientProcess.request.toString().equals(""));
									clientProcess.getRequestFromClient("updateDataQuery{Reservation~CustomerRight~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and Time = '" + orderDate + "' and ResAddress = '" + resAddress +"' and StatusReser = 'Delete'}");
									do{
										if(clientProcess.lock == 1){
											clientProcess.setRequest();
											JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
											return ;
										}
										//Vong lap nay dung de cho den khi co ket qua
									}while(!clientProcess.request.toString().equals(""));

									result = clientProcess.getResultAlterQuery();
									clientProcess.setResultAlterQuery();
									if(result.equals("0")){
										JOptionPane.showMessageDialog(null,"Order Is Lost Or Be Locked Or Be Denied. Please Back And Come In Again");
										return ;
									}
									else{

										do{
											;
										}while(!clientProcess.request.toString().equals(""));
										clientProcess.getRequestFromClient("updateDataQuery{Reservation~RestaurantRight~Delete~AID = '" + aid + "' and Foodname = '" + foodname + "' and Time = '" + orderDate + "' and ResAddress = '" + resAddress +"' and StatusReser = 'Delete'}");
										do{
											if(clientProcess.lock == 1){
												clientProcess.setRequest();
												JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
												return ;
											}
											//Vong lap nay dung de cho den khi co ket qua
										}while(!clientProcess.request.toString().equals(""));

										result = clientProcess.getResultAlterQuery();
										clientProcess.setResultAlterQuery();
										if(result.equals("0")){
											JOptionPane.showMessageDialog(null,"Order Is Lost Or Be Locked Or Be Denied. Please Back And Come In Again");
											return ;
										}
										else{

											do{
												;
											}while(!clientProcess.request.toString().equals(""));
											clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForCustomer~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and Time = '" + orderDate + "' and ResAddress = '" + resAddress +"' and StatusReser = 'Delete'}");
											do{
												if(clientProcess.lock == 1){
													clientProcess.setRequest();
													JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
													return ;
												}
												//Vong lap nay dung de cho den khi co ket qua
											}while(!clientProcess.request.toString().equals(""));

											result = clientProcess.getResultAlterQuery();
											clientProcess.setResultAlterQuery();
											if(result.equals("0")){
												JOptionPane.showMessageDialog(null,"Order Is Lost Or Be Locked Or Be Denied. Please Back And Come In Again");
												return ;
											}
											else{

												do{
													;
												}while(!clientProcess.request.toString().equals(""));
												clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForRestaurant~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and Time = '" + orderDate + "' and ResAddress = '" + resAddress +"' and StatusReser = 'Delete'}");
												do{
													if(clientProcess.lock == 1){
														clientProcess.setRequest();
														JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
														return ;
													}
													//Vong lap nay dung de cho den khi co ket qua
												}while(!clientProcess.request.toString().equals(""));
										
												result = clientProcess.getResultAlterQuery();
												clientProcess.setResultAlterQuery();
												if(!result.equals("0")){
													getOrderContentTable().setValueAt("Delete", row, 3);
												}
												else{
													JOptionPane.showMessageDialog(null, "Order Is Lost Or Be Locked Or Be Denied. Please Back And Come In Again");
													return ;
												}
											}
										}
									}
								}
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"Order Is Out Of Date. Can't Do Anything");
							return ;
						}
					}
					else if(status.equals("Deny")){
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Reservation~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String success = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();

						if(!success.equals("0")){
							JOptionPane.showMessageDialog(null,"Delete Successfully");
							DefaultTableModel tempModel = (DefaultTableModel)getOrderContentTable().getModel();
							tempModel.removeRow(row);
							return ;
						}
						else{
							JOptionPane.showMessageDialog(null,"Delete Unsuccessfully. Please Back And Come In Again");	
							return ;
						}
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}
		});
		deleteBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/delete.png"));
		deleteBtn.setBounds(333, 218, 55, 54);
		add(deleteBtn);
		
		btnChangeTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				new CreateOrder(clientProcess,foodname,aid,resAddress,orderDate,parent.getOrderTable()).setVisible(true);
			}
		});
		btnChangeTime.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/calendar.png"));
		btnChangeTime.setBounds(354, 12, 34, 26);
		add(btnChangeTime);

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0){
					return;
				}
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				String status = (String)getOrderContentTable().getValueAt(row, 3); 
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("updateDataQuery{Reservation~StatusReser~OK~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'Lock'}");
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
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("updateDataQuery{Reservation~CustomerRight~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'OK'}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					String success1 = new String();
					success1 = clientProcess.getResultAlterQuery();
					clientProcess.setResultAlterQuery();
					if(!success1.equals("0")){
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Reservation~RestaurantRight~Delete~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'OK'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String success2 = new String();
						success2 = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();
						if(!success2.equals("0")){
							do{
								;
							}while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForCustomer~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'OK'}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String success3 = new String();
							success3 = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();
							if(!success3.equals("0")){
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForRestaurant~Nothing~AID = '" + aid + "' and Foodname = '" + foodname + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "' and StatusReser = 'OK'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								String success4 = new String();
								success4 = clientProcess.getResultAlterQuery();
								clientProcess.setResultAlterQuery();
								if(!success4.equals("0")){
									getOrderContentTable().setValueAt("OK", row, 3);
								}
								else{
									JOptionPane.showMessageDialog(null, "This Order 's Status Is Not Lock Or Be Deleted. Please Back And Come In Again");
									return;
								}
							}
							else{
								JOptionPane.showMessageDialog(null, "This Order 's Status Is Not Lock Be Deleted. Please Back And Come In Again");
								return;
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "This Order 's Status Is Not Lock Be Deleted. Please Back And Come In Again");
							return;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "This Order 's Status Is Not Lock Be Deleted. Please Back And Come In Again");
						return;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "This Order 's Status Is Not Lock Be Deleted. Please Back And Come In Again");
					return;
				}
			}
		});
		okBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/ok.png"));
		okBtn.setBounds(199, 218, 55, 54);
		add(okBtn);
	}

	public void setAID(String aid){
		this.aid = aid;
	}

	public void setResAddress(String resAddress){
		this.resAddress = resAddress;
	}

	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}

	public String getOrderDate(){
		return(orderDate);
	}

	public String getAID(){
		return(aid);
	}

	public String getResAddress(){
		return(resAddress);
	}

	public JTable getOrderContentTable() {
		return orderContentTable;
	}

	public JButton getFixBtn() {
		return fixBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}
}
