package Client.restaurant;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import Client.clientprocess.ClientProcess;
import Client.sqlarraylist.SqlArrayList;
import Client.deletefood.DeleteFood;
import Client.foodedit.Foodedit;
import Client.createaddress.CreateAddress;
import Client.foodcreate.Foodcreate;

public class Restaurant extends JFrame {

	public JPanel contentPane;
	public JPanel inforPane = new JPanel();
	public JPanel foodPane = new JPanel();
	
	public JButton btnBack;
	public JButton btnDelete;
	public JButton btnEditPrice;
	public JButton btnAddFood;
	public JButton btnReject;
	public JButton btnrefresh;
	public JButton btnDeleteOrder;
	public JButton btnLock;
	public JButton btnlogout = new JButton("");
	
	public JTable rqfoodbl;
	public JTable foodMenuTbl;
	public JTable orderTable;

	public JLabel lblNameInfo;
	public JLabel lblCusNameInfo;
	public JLabel lblbirthdayInfo;
	public JLabel lblPhonenumInfo;
	public JLabel lblTimeInfo;
	public JLabel lblTotalCostInfo;
	public JLabel lblFoodTitle = new JLabel("FOOD MENU");
	public JLabel lblAva = new JLabel("");
	public JLabel lblNameIcon = new JLabel("");
	public JLabel lblAddressIcon = new JLabel("");

	public String sequence_address[] = new String[]{};
	public String resID = new String();
	public String resname = new String();

	public int selected_address;
	
	public SqlArrayList requestInfor = new SqlArrayList();
	public SqlArrayList foodmenuInfor = new SqlArrayList();
	public SqlArrayList rqFoodList = new SqlArrayList();
	
	public Foodedit feditDlog;
	
	public JComboBox addressCb = new JComboBox();

	public JScrollPane foodMenuScrlPane = new JScrollPane();

	public Restaurant(ClientProcess clientProcess,String rID,String rname) {
		resID = rID;
		resname = rname;
		LoadAddress(clientProcess);
		setVisible(true);
		setPreferredSize(new Dimension(800, 600));
		setResizable(false);
		pack();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnlogout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/door-out-icon.png"));
			}
			public void mouseExited(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/door-icon.png"));
			}
		});
		
		inforPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 51), null));
		inforPane.setBounds(10, 11, 487, 178);
		contentPane.add(inforPane);
		inforPane.setLayout(null);
		
		lblAva.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/restaurant_icon.png"));
		lblAva.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 0), new Color(204, 153, 51)));
		lblAva.setBounds(0, 0, 141, 141);
		inforPane.add(lblAva);
		
		lblNameIcon.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/pen1.png"));
		lblNameIcon.setBounds(151, 37, 28, 28);
		inforPane.add(lblNameIcon);
		
		lblNameInfo = new JLabel(resname);
		lblNameInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameInfo.setBounds(192, 37, 288, 28);
		inforPane.add(lblNameInfo);
		
		lblAddressIcon.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/AddressIcon.png"));
		lblAddressIcon.setBounds(151, 93, 28, 28);
		inforPane.add(lblAddressIcon);
		
		addressCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected_address = addressCb.getSelectedIndex();
				ClearOrderDetail();
				auto_delete(clientProcess);
				refreshOrder(clientProcess);
			}
		});
		addressCb.setBounds(189, 93, 288, 28);
		inforPane.add(addressCb);
										
		JPanel requestPane = new JPanel();
		requestPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
		requestPane.setLayout(null);
		requestPane.setBounds(10, 200, 774, 361);
		requestPane.setVisible(false);
										
		JPanel selectPane = new JPanel();
		selectPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(102, 153, 51), new Color(102, 153, 102)));
		selectPane.setBounds(10, 200, 774, 361);
		contentPane.add(selectPane);
		selectPane.setLayout(null);
										
		JButton btnRequest = new JButton("REQUEST");		
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearOrderDetail();
				auto_delete(clientProcess);
				refreshOrder(clientProcess);
				btnBack.setEnabled(true);
				selectPane.setVisible(false);
				contentPane.getComponents()[2].setVisible(true);
			}
		});
										btnRequest.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
										btnRequest.setVerticalTextPosition(SwingConstants.BOTTOM);
										btnRequest.setHorizontalTextPosition(SwingConstants.CENTER);
										btnRequest.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/request-128.png"));
										btnRequest.setBounds(105, 105, 150, 150);
										selectPane.add(btnRequest);
										
										JButton btnFoodmenu = new JButton("FOOD MENU");
										btnFoodmenu.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												refresh_food(clientProcess);
												btnBack.setEnabled(true);
												selectPane.setVisible(false);
												contentPane.getComponents()[3].setVisible(true);
											}
										});
										btnFoodmenu.setHorizontalTextPosition(SwingConstants.CENTER);
										btnFoodmenu.setVerticalTextPosition(SwingConstants.BOTTOM);
										btnFoodmenu.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
										btnFoodmenu.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/food.png"));
										btnFoodmenu.setBounds(515, 105, 150, 150);
										selectPane.add(btnFoodmenu);
										contentPane.add(requestPane);
										
										JLabel lblInforTitle = new JLabel("REQUEST INFO");
										lblInforTitle.setHorizontalAlignment(SwingConstants.CENTER);
										lblInforTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
										lblInforTitle.setBounds(371, 0, 403, 40);
										requestPane.add(lblInforTitle);
										
										JLabel lblCusName = new JLabel("Customer name :");
										lblCusName.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusName.setBounds(381, 51, 126, 25);
										requestPane.add(lblCusName);
										
										lblCusNameInfo = new JLabel("");
										lblCusNameInfo.setToolTipText("");
										lblCusNameInfo.setBounds(497, 51, 267, 25);
										requestPane.add(lblCusNameInfo);
										
										JLabel lblCusPhone = new JLabel("Date of birth:");
										lblCusPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusPhone.setBounds(381, 87, 106, 25);
										requestPane.add(lblCusPhone);
										
										lblbirthdayInfo = new JLabel("");
										lblbirthdayInfo.setBounds(497, 87, 267, 25);
										requestPane.add(lblbirthdayInfo);
										
										JLabel lblCusFacebook = new JLabel("Phone number:");
										lblCusFacebook.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusFacebook.setBounds(381, 123, 106, 25);
										requestPane.add(lblCusFacebook);
										
										lblPhonenumInfo = new JLabel("");
										lblPhonenumInfo.setToolTipText("");
										lblPhonenumInfo.setBounds(497, 123, 267, 25);
										requestPane.add(lblPhonenumInfo);
										
										JLabel lblTime = new JLabel("Time:");
										lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblTime.setBounds(381, 159, 106, 25);
										requestPane.add(lblTime);
										
										lblTimeInfo = new JLabel("");
										lblTimeInfo.setBounds(497, 159, 267, 25);
										requestPane.add(lblTimeInfo);
										
										JLabel lblTotal = new JLabel("Total :");
										lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblTotal.setBounds(381, 315, 50, 25);
										requestPane.add(lblTotal);
										
										lblTotalCostInfo = new JLabel("");
										lblTotalCostInfo.setBounds(430, 315, 138, 25);
										requestPane.add(lblTotalCostInfo);
										
										JLabel lblCustomerTitle = new JLabel("CUSTOMERS");
										lblCustomerTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										lblCustomerTitle.setHorizontalAlignment(SwingConstants.CENTER);
										lblCustomerTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
										lblCustomerTitle.setBounds(0, 0, 360, 40);
										requestPane.add(lblCustomerTitle);
										
										JScrollPane rqFoodScrlPn = new JScrollPane();
										rqFoodScrlPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										rqFoodScrlPn.setBounds(381, 185, 383, 119);
										requestPane.add(rqFoodScrlPn);
										
										rqfoodbl = new JTable();
										rqfoodbl.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {
												if(rqfoodbl.getSelectedRow()< 0)
												{
													btnReject.setEnabled(false);
													btnDeleteOrder.setEnabled(false);
													btnLock.setEnabled(false);
												}
												else
												{
													btnReject.setEnabled(true);
													btnDeleteOrder.setEnabled(true);
													btnLock.setEnabled(true);
												}
											}
										});
										rqfoodbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
										rqfoodbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										rqfoodbl.setRowHeight(25);
										rqfoodbl.setRowSelectionAllowed(true);
										rqFoodScrlPn.setViewportView(rqfoodbl);
										
										JScrollPane orderScrlPn = new JScrollPane();
										orderScrlPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										orderScrlPn.setBounds(0, 39, 360, 322);
										requestPane.add(orderScrlPn);
										
										orderTable = new JTable();
										orderTable.setRowHeight(25);
										orderTable.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {
												get_OrderDetail(clientProcess);
											}
										});
										orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										orderTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
										orderScrlPn.setViewportView(orderTable);
										
										btnReject = new JButton("Deny");
										btnReject.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												deny_order(clientProcess);
											}
										});
										btnReject.setFont(new Font("Tahoma", Font.PLAIN, 10));
										btnReject.setEnabled(false);
										btnReject.setBounds(701, 307, 63, 40);
										requestPane.add(btnReject);
										
										btnDeleteOrder = new JButton("Delete");
										btnDeleteOrder.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												delete_order(clientProcess);
											}
										});
										btnDeleteOrder.setFont(new Font("Tahoma", Font.PLAIN, 8));
										btnDeleteOrder.setEnabled(false);
										btnDeleteOrder.setBounds(635, 307, 63, 40);
										requestPane.add(btnDeleteOrder);
										
										btnLock = new JButton("Lock");
										btnLock.setFont(new Font("Tahoma", Font.PLAIN, 10));
										btnLock.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												Lock_Order(clientProcess);
											}
										});
										btnLock.setEnabled(false);
										btnLock.setBounds(569, 307, 63, 40);
										requestPane.add(btnLock);
		
		foodPane.setLayout(null);
		foodPane.setBounds(10, 200, 774, 361);
		foodPane.setVisible(false);
		contentPane.add(foodPane);
		
		lblFoodTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 153, 0), new Color(153, 153, 51)));
		lblFoodTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoodTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFoodTitle.setBounds(0, 0, 383, 40);
		foodPane.add(lblFoodTitle);
		
		btnEditPrice = new JButton("Edit");
		btnEditPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEditPrice.isEnabled())
				{
					int index = foodMenuTbl.getSelectedRow();				
					Foodedit edit = new Foodedit(clientProcess,foodmenuInfor.getRow(index),resID);
					edit.setVisible(true);
					refresh_food(clientProcess);
				}
			}
		});
		btnEditPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditPrice.setEnabled(false);
		btnEditPrice.setBounds(516, 150, 131, 60);
		foodPane.add(btnEditPrice);
		
		btnAddFood = new JButton("Add");
		btnAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Foodcreate FdCrt = new Foodcreate(clientProcess,resID);
				FdCrt.setVisible(true);
				refresh_food(clientProcess);
			}
		});
		btnAddFood.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddFood.setBounds(516, 40, 131, 60);
		foodPane.add(btnAddFood);
		
		foodMenuScrlPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 153, 0), new Color(153, 153, 51)));
		foodMenuScrlPane.setBounds(0, 40, 383, 322);
		foodPane.add(foodMenuScrlPane);
		
		foodMenuTbl = new JTable();
		foodMenuTbl.setRowHeight(25);
		foodMenuTbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		foodMenuTbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		foodMenuTbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = foodMenuTbl.getSelectedRow();
				if(index >= 0)
				{
					btnEditPrice.setEnabled(true);
					btnDelete.setEnabled(true);
				}
				else 
				{
					btnEditPrice.setEnabled(false);
					btnDelete.setEnabled(false);
				}
			}
		});
		foodMenuScrlPane.setViewportView(foodMenuTbl);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = foodMenuTbl.getSelectedRow();
				DeleteFood deldialog = new DeleteFood(clientProcess,foodmenuInfor.getRow(select)[0],resID);
				deldialog.setVisible(true);
				refresh_food(clientProcess);
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(516, 263, 131, 60);
		foodPane.add(btnDelete);
		btnlogout.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/door-icon.png"));
		btnlogout.setBounds(734, 11, 50, 50);
		contentPane.add(btnlogout);
		
		btnrefresh = new JButton("");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadAddress(clientProcess);
				ClearOrderDetail();
				auto_delete(clientProcess);
				refreshOrder(clientProcess);	
				refresh_food(clientProcess);
			}
		});
		btnrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image ref = (new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/refresh.png")).getImage();
				Image hlgt = ref.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
				btnrefresh.setIcon(new ImageIcon(hlgt));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnrefresh.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/refresh.png"));
			}
		});
		btnrefresh.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/refresh.png"));
		btnrefresh.setBounds(665, 11, 50, 50);
		contentPane.add(btnrefresh);
		
		btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnBack.isEnabled())
				{
					contentPane.getComponents()[2].setVisible(false);
					contentPane.getComponents()[3].setVisible(false);
					contentPane.getComponents()[1].setVisible(true);
					btnBack.setEnabled(false);
				}
			}
		});
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(btnBack.isEnabled())
				{
					Image back = (new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png")).getImage();
					Image hlgt = back.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
					btnBack.setIcon(new ImageIcon(hlgt));
				}			
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnBack.isEnabled()){
					btnBack.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png"));
				}
			}
		});
		btnBack.setEnabled(false);
		btnBack.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png"));
		btnBack.setBounds(596, 11, 50, 50);
		contentPane.add(btnBack);
	}
	
	public void LoadAddress(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try{
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("dataQuery{SequenceRestaurant~Address~\"\"~ResID = '" + resID + "'~\"\"~\"\"}");
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
				if(result.size() != 0){
					SqlArrayList seq_address = new SqlArrayList(result);
					sequence_address = seq_address.getColumn(0);
					selected_address = 0;		
					addressCb.setModel(new DefaultComboBoxModel(sequence_address));
					if(sequence_address.length == 0){
						lblNameInfo.setText("");
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void refreshOrder(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try{
				auto_delete(clientProcess);
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return ;
				}
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("dataQuery{Reservation,Account~FullName,Birthday,PhoneNumber,Time,Reservation.AID~Reservation.AID = Account.AID~ResAddress = '" + sequence_address[selected_address] + "'~\"\"~\"\"}");
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

				requestInfor = new SqlArrayList(result);
				((DefaultTableModel)orderTable.getModel()).setRowCount(0);
				orderTable.setModel(new DefaultTableModel(
				requestInfor.get2dArray()
				,
				new String[] {
					"Customer Name"
				}
				) {
				Class[] columnTypes = new Class[] {
					Object.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});	
				orderTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
				orderTable.getColumnModel().getColumn(0).setPreferredWidth(151);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void refresh_food(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try{
				
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("dataQuery{Provide~Foodname,Cost,DescribeFood~\"\"~ResID = '" + resID + "'~\"\"~\"\"}");
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

				foodmenuInfor = new SqlArrayList(result);
				foodMenuTbl.setModel(new DefaultTableModel(
						foodmenuInfor.get2dArray(),
						new String[] {
							"FOOD", "PRICE"
						}
					) {
						boolean[] columnEditables = new boolean[] {
							false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});
					foodMenuTbl.getColumnModel().getColumn(0).setResizable(false);
					foodMenuTbl.getColumnModel().getColumn(1).setResizable(false);
			
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			btnEditPrice.setEnabled(false);
			btnDelete.setEnabled(false);
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void get_OrderDetail(ClientProcess clientProcess)
	{
		try{
			int selected_index = orderTable.getSelectedRow();
			if(selected_index < 0){
				ClearOrderDetail();
			}
			else
			{
				if(clientProcess.lock == 0){
					String AID = requestInfor.getRow(selected_index)[4];
					String FullName = requestInfor.getRow(selected_index)[0];
					String birthday = requestInfor.getRow(selected_index)[1];
					String phonenum = requestInfor.getRow(selected_index)[2];
					String Time = requestInfor.getRow(selected_index)[3];
					if(sequence_address.length == 0){
						JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
						return ;
					}
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Reservation~Foodname,Cost,Quantity,StatusReser,NextStatusForRestaurant,RestaurantRight~\"\"~ResAddress = '" + sequence_address[selected_address] + "' and AID = '" + AID +"' and Time = '" + Time + "'~\"\"~\"\"}");
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
					
					rqFoodList = new SqlArrayList(result);
					
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Reservation~SUM(Cost * Quantity)~\"\"~ResAddress = '" + sequence_address[selected_address] + "' and AID = '" + AID +"' and Time = '" + Time + "'~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					List<List<String>> result1 = new ArrayList();
					result1 = clientProcess.getResultList();
					clientProcess.setResultList();
					
					SqlArrayList totalcost = new SqlArrayList(result1);
					String Total = totalcost.getRow(0)[0];
					
					lblCusNameInfo.setText(FullName);
					lblbirthdayInfo.setText(birthday);
					lblPhonenumInfo.setText(phonenum);
					lblTimeInfo.setText(Time);
					lblTotalCostInfo.setText(Total);
					
					rqfoodbl.setModel(new DefaultTableModel(
							rqFoodList.get2dArray()
							,
							new String[] {
								"FOOD NAME", "PRICE", "QUANTITY","STATUS"
							}
						) {
							Class[] columnTypes = new Class[] {
								String.class, String.class, String.class,String.class
							};
							public Class getColumnClass(int columnIndex) {
								return columnTypes[columnIndex];
							}
							boolean[] columnEditables = new boolean[] {
								false, false, false,false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
						});
						rqfoodbl.getColumnModel().getColumn(0).setPreferredWidth(100);
						rqfoodbl.getColumnModel().getColumn(1).setPreferredWidth(50);
						rqfoodbl.getColumnModel().getColumn(2).setPreferredWidth(25);
						rqfoodbl.getColumnModel().getColumn(3).setPreferredWidth(25);
					totalcost.close();
			
					btnReject.setEnabled(false);
					btnDeleteOrder.setEnabled(false);
					btnLock.setEnabled(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ClearOrderDetail()
	{
		lblCusNameInfo.setText("");
		lblbirthdayInfo.setText("");
		lblPhonenumInfo.setText("");
		lblTimeInfo.setText("");
		lblTotalCostInfo.setText("");
		
		rqfoodbl.setModel(new DefaultTableModel(
				new Object[][]{},
				new String[] {
					"FOOD NAME", "PRICE", "QUANTITY","STATUS"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
				boolean[] columnEditables = new boolean[] {
					false, false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
			rqfoodbl.getColumnModel().getColumn(0).setPreferredWidth(100);
			rqfoodbl.getColumnModel().getColumn(1).setPreferredWidth(50);
			rqfoodbl.getColumnModel().getColumn(2).setPreferredWidth(25);
			rqfoodbl.getColumnModel().getColumn(3).setPreferredWidth(25);
		btnReject.setEnabled(false);
		btnDeleteOrder.setEnabled(false);
		btnLock.setEnabled(false);
	}
	
	public void Lock_Order(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try{
				int selected_customer = orderTable.getSelectedRow();
				int selected_foodorder = rqfoodbl.getSelectedRow();
				if(selected_customer < 0 || selected_foodorder < 0) return;
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return ;
				}
				
				if(!checkorder_changed(clientProcess))
				{
					JOptionPane.showMessageDialog(null, "Data have been changed. Click OK to refresh");
					refreshOrder(clientProcess);
					ClearOrderDetail();
					return;
				}
				
				if(!rqFoodList.getRow(selected_foodorder)[4].toLowerCase().contains("Lock".toLowerCase()))
				{
					JOptionPane.showMessageDialog(null, "Can not Lock this Order");
					return;
				}
				
				String AID = requestInfor.getRow(selected_customer)[4];
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String time = requestInfor.getRow(selected_customer)[3];
				String foodname = rqFoodList.getRow(selected_foodorder)[0];
				Date ordertime = format.parse(time);
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				
				Date cur = cal.getTime();

				if(cur.after(ordertime) || ordertime.compareTo(cur) == 0)
				{
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("updateDataQuery{Reservation~StatusReser~Lock~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + time +"' and Foodname = '"+ foodname +"'}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					String result1 = new String();
					result1 = clientProcess.getResultAlterQuery();
					clientProcess.setResultAlterQuery();

					if(result1.equals("0")){
						JOptionPane.showMessageDialog(null,"Can't Lock");
						return ;
					}
					else{
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Reservation~CustomerRight~Change Status~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + time +"' and Foodname = '"+ foodname +"' and StatusReser = 'Lock'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String result2 = new String();
						result2 = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();

						if(result2.equals("0")){
							JOptionPane.showMessageDialog(null,"Can't Lock");
							return ;
						}
						else{
							do{
								;
							}while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("updateDataQuery{Reservation~RestaurantRight~Delete After Order Day 1 Day~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + time +"' and Foodname = '"+ foodname +"'and StatusReser = 'Lock'}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String result3 = new String();
							result3 = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();

							if(result3.equals("0")){
								JOptionPane.showMessageDialog(null,"Can't Lock");
								return ;
							}
							else{
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForCustomer~OK~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + time +"' and Foodname = '"+ foodname +"'and StatusReser = 'Lock'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								String result4 = new String();
								result4 = clientProcess.getResultAlterQuery();
								clientProcess.setResultAlterQuery();

								if(result4.equals("0")){
									JOptionPane.showMessageDialog(null,"Can't Lock");
									return ;
								}
								else{
									do{
										;
									}while(!clientProcess.request.toString().equals(""));
									clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForRestaurant~Nothing~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + time +"' and Foodname = '"+ foodname +"'and StatusReser = 'Lock'}");
									do{
										if(clientProcess.lock == 1){
											clientProcess.setRequest();
											JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
											return ;
										}
										//Vong lap nay dung de cho den khi co ket qua
									}while(!clientProcess.request.toString().equals(""));
									String result5 = new String();
									result5 = clientProcess.getResultAlterQuery();
									clientProcess.setResultAlterQuery();

									if(result5.equals("0")){
										JOptionPane.showMessageDialog(null,"Can't Lock");
										return ;
									}
									else{
										JOptionPane.showMessageDialog(null,"Lock Succesfully");
									}
								}
							}
						}
					}
					refreshOrder(clientProcess);
					ClearOrderDetail();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "This Order can not be locked yet");
				}
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void delete_order(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try
			{
				int selected_customer = orderTable.getSelectedRow();
				int selected_foodorder = rqfoodbl.getSelectedRow();
				if(selected_customer < 0 || selected_foodorder < 0) return;
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return ;
				}
				
				if(!checkorder_changed(clientProcess))
				{
					JOptionPane.showMessageDialog(null, "Data have been changed.Click OK to refresh");
					refreshOrder(clientProcess);
					ClearOrderDetail();
					return;
				}
				
				if(!rqFoodList.getRow(selected_foodorder)[5].toLowerCase().equals("Delete".toLowerCase()))
				{
					JOptionPane.showMessageDialog(null, "Can not delete this Order");
					return;
				}
				
				String AID = requestInfor.getRow(selected_customer)[4];
				String Time = requestInfor.getRow(selected_customer)[3];
				String food_name = rqFoodList.getRow(selected_foodorder)[0];
				
				do{
						;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("deleteDataQuery{Reservation~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"'}");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.setResultAlterQuery();

				refreshOrder(clientProcess);
				ClearOrderDetail();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void deny_order(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try
			{
				int selected_customer = orderTable.getSelectedRow();
				int selected_foodorder = rqfoodbl.getSelectedRow();
				if(selected_customer < 0 || selected_foodorder < 0) return;
				
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return ;
				}

				if(!checkorder_changed(clientProcess))
				{
					JOptionPane.showMessageDialog(null, "Data have been changed.Click OK to refresh");
					refreshOrder(clientProcess);
					ClearOrderDetail();
					return;
				}
				
				if(!rqFoodList.getRow(selected_foodorder)[4].toLowerCase().contains("Deny".toLowerCase()))
				{
					JOptionPane.showMessageDialog(null, "Can not Deny this Order");
					return;
				}
				
				String AID = requestInfor.getRow(selected_customer)[4];
				String Time = requestInfor.getRow(selected_customer)[3];
				String food_name = rqFoodList.getRow(selected_foodorder)[0];
				
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("updateDataQuery{Reservation~StatusReser~Deny~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"'}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					String result1 = new String();
					result1 = clientProcess.getResultAlterQuery();
					clientProcess.setResultAlterQuery();

					if(result1.equals("0")){
						JOptionPane.showMessageDialog(null,"Can't Deny");
						return ;
					}
					else{
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Reservation~CustomerRight~Delete~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"' and StatusReser = 'Deny'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String result2 = new String();
						result2 = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();

						if(result2.equals("0")){
							JOptionPane.showMessageDialog(null,"Can't Deny");
							return ;
						}
						else{
							do{
								;
							}while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("updateDataQuery{Reservation~RestaurantRight~Nothing~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"'and StatusReser = 'Deny'}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String result3 = new String();
							result3 = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();

							if(result3.equals("0")){
								JOptionPane.showMessageDialog(null,"Can't Deny");
								return ;
							}
							else{
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForCustomer~Nothing~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"'and StatusReser = 'Deny'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								String result4 = new String();
								result4 = clientProcess.getResultAlterQuery();
								clientProcess.setResultAlterQuery();

								if(result4.equals("0")){
									JOptionPane.showMessageDialog(null,"Can't Deny");
									return ;
								}
								else{
									do{
										;
									}while(!clientProcess.request.toString().equals(""));
									clientProcess.getRequestFromClient("updateDataQuery{Reservation~NextStatusForRestaurant~Nothing~AID = '" + AID +"' and ResAddress = '" + sequence_address[selected_address] +"' and Time = '" + Time +"' and Foodname = '"+ food_name +"'and StatusReser = 'Deny'}");
									do{
										if(clientProcess.lock == 1){
											clientProcess.setRequest();
											JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
											return ;
										}
										//Vong lap nay dung de cho den khi co ket qua
									}while(!clientProcess.request.toString().equals(""));
									String result5 = new String();
									result5 = clientProcess.getResultAlterQuery();
									clientProcess.setResultAlterQuery();

									if(result5.equals("0")){
										JOptionPane.showMessageDialog(null,"Can't Deny");
										return ;
									}
									else{
										JOptionPane.showMessageDialog(null,"Deny Succesfully");
									}
								}
							}
						}
					}
				refreshOrder(clientProcess);
				ClearOrderDetail();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public void auto_delete(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try
			{
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return ;
				}
				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("deleteDataQuery{Reservation~StatusReser = 'OK' and ResAddress = '" + sequence_address[selected_address] + "'}");
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
				clientProcess.getRequestFromClient("dataQuery{Reservation~Time~\"\"~StatusReser IN('Lock','Updating') and ResAddress = '" + sequence_address[selected_address] + "'~\"\"~\"\"}");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				List<List<String>> result = clientProcess.getResultList();
				clientProcess.setResultList();
				
				SqlArrayList check_array = new SqlArrayList(result);
				for(int i = 0; i < check_array.getRownumber();i++)
				{
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String time = check_array.getRow(i)[0];
					Date ordertime = format.parse(time);
					
					Calendar deleteD = Calendar.getInstance();
					deleteD.setTime(ordertime);
					deleteD.add(Calendar.DATE,+1);
					
					Date deleteDay = deleteD.getTime();
					Date cur = Calendar.getInstance().getTime();
					if(cur.after(deleteDay))
					{
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Reservation~Time = '" + time + "' and StatusReser IN('Lock','Updating') and ResAddress = '" + sequence_address[selected_address] + "'}");
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
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return ;
		}
	}
	
	public boolean CompareData(SqlArrayList a,SqlArrayList b)
	{
		if(a.getRownumber()!=b.getRownumber() || a.getColnumber()!=b.getColnumber())
		{
			return false;
		}
		else
		{
			for(int i = 0;i< a.getRownumber();i++)
			{
				for(int j = 0;j < a.getColnumber();j++)
				{
					if(!a.getRow(i)[j].equals(b.getRow(i)[j]))
					{
						return false;
					}
				}
			}
			return true;
		}
	}
	
	public boolean checkorder_changed(ClientProcess clientProcess)
	{
		if(clientProcess.lock == 0){
			try{

				int selected_customer = orderTable.getSelectedRow();
				int selected_foodorder = rqfoodbl.getSelectedRow();
				String AID,Time;
				if(sequence_address.length == 0){
					JOptionPane.showMessageDialog(null,"This Restaurant Has Been Destroyed");
					return false;
				}

				do{
					;
				}while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("dataQuery{Reservation,Account~FullName,Birthday,PhoneNumber,Time,Reservation.AID~Reservation.AID = Account.AID~ResAddress = '" + sequence_address[selected_address] + "'~\"\"~\"\"}");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return false;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				List<List<String>> result = new ArrayList();
				result = clientProcess.getResultList();
				clientProcess.setResultList();

				SqlArrayList new_data = new SqlArrayList(result);
			
				if(CompareData(requestInfor, new_data))
				{
					AID = requestInfor.getRow(selected_customer)[4];
					Time = requestInfor.getRow(selected_customer)[3];
					
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Reservation~Foodname,Cost,Quantity,StatusReser,NextStatusForRestaurant,RestaurantRight~\"\"~ResAddress = '" + sequence_address[selected_address] + "' and AID = '" + AID + "' and Time = '" + Time + "'~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return false;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					List<List<String>> result1 = new ArrayList();
					result1 = clientProcess.getResultList();
					clientProcess.setResultList();

					SqlArrayList new_data2 = new SqlArrayList(result1);
					if(!CompareData(rqFoodList, new_data2)){
						return false;
					}
					else{
						return true;
					}
				}
				else
				{
					return false;
				}			
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				return false;
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	public JComboBox getAddressCb() {
		return addressCb;
	}
}