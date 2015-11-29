package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class restaurant extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private JLabel lblNameInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args/*,Connection connection*/) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restaurant frame = new restaurant(/*args,connection*/);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTable RQFoodtbl;
	private JLabel lblCusNameInfo;
	private JLabel lblbirthdayInfo;
	private JLabel lblPhonenumInfo;
	private JLabel lblTimeInfo;
	private JLabel lblTotalCostInfo;
	
	private String ResID = new String("REST0001");
	private String ResName = new String("123");
	private String Sequence_address[];
	private int selected_address;
	
	private SqlArrayList RequestInfo = new SqlArrayList();
	private SqlArrayList FoodMenuInfo = new SqlArrayList();
	private SqlArrayList RQfoodlist = new SqlArrayList();
	private JTable FoodMenutbl;
	
	private foodedit FeditDlog;
	private JTable OrderTable;
	private JComboBox AddressCb = new JComboBox();
	private JButton btnDelete;
	private JButton btnEditPrice;
	private JButton btnAddFood;
	private JButton btnReject;
	private JButton btnrefresh;
	private JButton btnDeleteOrder;
	private JButton btnLock;

	/**
	 * Create the frame.
	 */
	public restaurant(/*String[] RestaurantInfo,Connection connection*/) {
		//ResID = RestaurantInfo[0];
		SQLConnection.DBConnect();
		LoadAddress();
		//setTitle(RestaurantInfo[1]);
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/restaurant_icon.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogout = new JButton("");
		btnlogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnlogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-out-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-icon.png")));
			}
		});
		
		JPanel InfoPane = new JPanel();
		InfoPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 51), null));
		InfoPane.setBounds(10, 11, 487, 178);
		contentPane.add(InfoPane);
		InfoPane.setLayout(null);
		
		JLabel lblAva = new JLabel("");
		lblAva.setIcon(new ImageIcon(this.getClass().getResource("/restaurant_icon.png")));
		lblAva.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 0), new Color(204, 153, 51)));
		lblAva.setBounds(0, 0, 141, 141);
		InfoPane.add(lblAva);
		
		JLabel lblNameIcon = new JLabel("");
		lblNameIcon.setIcon(new ImageIcon(this.getClass().getResource("/Rsname.png")));
		lblNameIcon.setBounds(151, 37, 28, 28);
		InfoPane.add(lblNameIcon);
		
		lblNameInfo = new JLabel(ResName);
		lblNameInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameInfo.setBounds(189, 37, 288, 28);
		InfoPane.add(lblNameInfo);
		//lblPhoneInfo.setText(RestaurantInfo[3]);
		
		JLabel lblAddressIcon = new JLabel("");
		lblAddressIcon.setIcon(new ImageIcon(this.getClass().getResource("/AddressIcon.png")));
		lblAddressIcon.setBounds(151, 93, 28, 28);
		InfoPane.add(lblAddressIcon);
		
		AddressCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected_address = AddressCb.getSelectedIndex();
				ClearOrderDetail();
				auto_delete();
				refreshOrder();
			}
		});
		AddressCb.setBounds(189, 93, 288, 28);
		InfoPane.add(AddressCb);
		
		
		JButton btnAddAddress = new JButton("Create Address");
		btnAddAddress.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EditProfile edit_dialog = new EditProfile(ResID);
				edit_dialog.setVisible(true);
				LoadAddress();
				ClearOrderDetail();
				auto_delete();
				refreshOrder();
			}
		});
		btnAddAddress.setBounds(2, 142, 139, 33);
		InfoPane.add(btnAddAddress);
								
										JPanel RequestPane = new JPanel();
										RequestPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										RequestPane.setLayout(null);
										RequestPane.setBounds(10, 200, 774, 361);
										RequestPane.setVisible(false);
										//lblRateInfo.setText(RestaurantInfo[5]);
										
										JPanel SelectPane = new JPanel();
										SelectPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(102, 153, 51), new Color(102, 153, 102)));
										SelectPane.setBounds(10, 200, 774, 361);
										contentPane.add(SelectPane);
										SelectPane.setLayout(null);
										
										JButton btnRequest = new JButton("REQUEST");		
										btnRequest.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												ClearOrderDetail();
												auto_delete();
												refreshOrder();
												btnBack.setEnabled(true);
												SelectPane.setVisible(false);
												contentPane.getComponents()[2].setVisible(true);
											}
										});
										btnRequest.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
										btnRequest.setVerticalTextPosition(SwingConstants.BOTTOM);
										btnRequest.setHorizontalTextPosition(SwingConstants.CENTER);
										btnRequest.setIcon(new ImageIcon(this.getClass().getResource("/request-128.png")));
										btnRequest.setBounds(105, 105, 150, 150);
										SelectPane.add(btnRequest);
										
										JButton btnFoodmenu = new JButton("FOOD MENU");
										btnFoodmenu.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												refresh_food();
												btnBack.setEnabled(true);
												SelectPane.setVisible(false);
												contentPane.getComponents()[3].setVisible(true);
											}
										});
										btnFoodmenu.setHorizontalTextPosition(SwingConstants.CENTER);
										btnFoodmenu.setVerticalTextPosition(SwingConstants.BOTTOM);
										btnFoodmenu.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
										btnFoodmenu.setIcon(new ImageIcon(this.getClass().getResource("/food.png")));
										btnFoodmenu.setBounds(515, 105, 150, 150);
										SelectPane.add(btnFoodmenu);
										contentPane.add(RequestPane);
										
										JLabel lblRQÌnoTitle = new JLabel("REQUEST INFO");
										lblRQÌnoTitle.setHorizontalAlignment(SwingConstants.CENTER);
										lblRQÌnoTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
										lblRQÌnoTitle.setBounds(371, 0, 403, 40);
										RequestPane.add(lblRQÌnoTitle);
										
										JLabel lblCusName = new JLabel("Customer name :");
										lblCusName.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusName.setBounds(381, 51, 106, 25);
										RequestPane.add(lblCusName);
										
										lblCusNameInfo = new JLabel("");
										lblCusNameInfo.setToolTipText("");
										lblCusNameInfo.setBounds(497, 51, 267, 25);
										RequestPane.add(lblCusNameInfo);
										
										JLabel lblCusPhone = new JLabel("Date of birth:");
										lblCusPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusPhone.setBounds(381, 87, 106, 25);
										RequestPane.add(lblCusPhone);
										
										lblbirthdayInfo = new JLabel("");
										lblbirthdayInfo.setBounds(497, 87, 267, 25);
										RequestPane.add(lblbirthdayInfo);
										
										JLabel lblCusFacebook = new JLabel("Phone number:");
										lblCusFacebook.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblCusFacebook.setBounds(381, 123, 106, 25);
										RequestPane.add(lblCusFacebook);
										
										lblPhonenumInfo = new JLabel("");
										lblPhonenumInfo.setToolTipText("");
										lblPhonenumInfo.setBounds(497, 123, 267, 25);
										RequestPane.add(lblPhonenumInfo);
										
										JLabel lblTime = new JLabel("Time:");
										lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblTime.setBounds(381, 159, 106, 25);
										RequestPane.add(lblTime);
										
										lblTimeInfo = new JLabel("");
										lblTimeInfo.setBounds(497, 159, 267, 25);
										RequestPane.add(lblTimeInfo);
										
										JLabel lblTotal = new JLabel("Total :");
										lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
										lblTotal.setBounds(381, 315, 40, 25);
										RequestPane.add(lblTotal);
										
										lblTotalCostInfo = new JLabel("");
										lblTotalCostInfo.setBounds(420, 315, 138, 25);
										RequestPane.add(lblTotalCostInfo);
										
										JLabel lblCustomerTitle = new JLabel("CUSTOMERS");
										lblCustomerTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										lblCustomerTitle.setHorizontalAlignment(SwingConstants.CENTER);
										lblCustomerTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
										lblCustomerTitle.setBounds(0, 0, 360, 40);
										RequestPane.add(lblCustomerTitle);
										
										JScrollPane RQFoodScrlPn = new JScrollPane();
										RQFoodScrlPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										RQFoodScrlPn.setBounds(381, 185, 383, 119);
										RequestPane.add(RQFoodScrlPn);
										
										RQFoodtbl = new JTable();
										RQFoodtbl.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {
												if(RQFoodtbl.getSelectedRow()< 0)
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
										RQFoodtbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
										RQFoodtbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										RQFoodtbl.setRowHeight(25);
										RQFoodtbl.setRowSelectionAllowed(true);
										RQFoodScrlPn.setViewportView(RQFoodtbl);
										
										JScrollPane OrderSCrlPn = new JScrollPane();
										OrderSCrlPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 51, 51), new Color(0, 51, 102)));
										OrderSCrlPn.setBounds(0, 39, 360, 322);
										RequestPane.add(OrderSCrlPn);
										
										OrderTable = new JTable();
										OrderTable.setRowHeight(25);
										OrderTable.addMouseListener(new MouseAdapter() {
											@Override
											public void mouseClicked(MouseEvent arg0) {
												get_OrderDetail();
												
											}
										});
										OrderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
										OrderTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
										OrderSCrlPn.setViewportView(OrderTable);
										
										btnReject = new JButton("Deny");
										btnReject.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												deny_order();
											}
										});
										btnReject.setFont(new Font("Tahoma", Font.PLAIN, 10));
										btnReject.setEnabled(false);
										btnReject.setBounds(701, 307, 63, 40);
										RequestPane.add(btnReject);
										
										btnDeleteOrder = new JButton("Delete");
										btnDeleteOrder.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												delete_order();
											}
										});
										btnDeleteOrder.setFont(new Font("Tahoma", Font.PLAIN, 10));
										btnDeleteOrder.setEnabled(false);
										btnDeleteOrder.setBounds(635, 307, 63, 40);
										RequestPane.add(btnDeleteOrder);
										
										btnLock = new JButton("Lock");
										btnLock.setFont(new Font("Tahoma", Font.PLAIN, 10));
										btnLock.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
												Lock_Order();
											}
										});
										btnLock.setEnabled(false);
										btnLock.setBounds(569, 307, 63, 40);
										RequestPane.add(btnLock);
		
		JPanel FoodPane = new JPanel();
		FoodPane.setLayout(null);
		FoodPane.setBounds(10, 200, 774, 361);
		FoodPane.setVisible(false);
		contentPane.add(FoodPane);
		
		JLabel lblFoodTitle = new JLabel("FOOD MENU");
		lblFoodTitle.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 153, 0), new Color(153, 153, 51)));
		lblFoodTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoodTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFoodTitle.setBounds(0, 0, 383, 40);
		FoodPane.add(lblFoodTitle);
		
		btnEditPrice = new JButton("Edit");
		btnEditPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnEditPrice.isEnabled())
				{
					int index = FoodMenutbl.getSelectedRow();				
					foodedit edit = new foodedit(FoodMenuInfo.getRow(index),ResID);
					edit.setVisible(true);
					refresh_food();
				}
			}
		});
		btnEditPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditPrice.setEnabled(false);
		btnEditPrice.setBounds(516, 150, 131, 60);
		FoodPane.add(btnEditPrice);
		
		btnAddFood = new JButton("Add");
		btnAddFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodcreate FdCrt = new foodcreate(ResID);
				FdCrt.setVisible(true);
				refresh_food();
			}
		});
		btnAddFood.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddFood.setBounds(516, 40, 131, 60);
		FoodPane.add(btnAddFood);
		
		JScrollPane FoodMenuScrlPn = new JScrollPane();
		FoodMenuScrlPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(153, 153, 0), new Color(153, 153, 51)));
		FoodMenuScrlPn.setBounds(0, 40, 383, 322);
		FoodPane.add(FoodMenuScrlPn);
		
		FoodMenutbl = new JTable();
		FoodMenutbl.setRowHeight(25);
		FoodMenutbl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		FoodMenutbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		FoodMenutbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int index = FoodMenutbl.getSelectedRow();
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
		FoodMenuScrlPn.setViewportView(FoodMenutbl);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = FoodMenutbl.getSelectedRow();
				DeleteFood deldialog = new DeleteFood(FoodMenuInfo.getRow(select)[0],ResID);
				deldialog.setVisible(true);
				refresh_food();
			}
		});
		btnDelete.setEnabled(false);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnDelete.setBounds(516, 263, 131, 60);
		FoodPane.add(btnDelete);
		btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-icon.png")));
		btnlogout.setBounds(734, 11, 50, 50);
		contentPane.add(btnlogout);
		
		btnrefresh = new JButton("");
		btnrefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoadAddress();
				ClearOrderDetail();
				auto_delete();
				refreshOrder();	
				refresh_food();
			}
		});
		btnrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image ref = (new ImageIcon(this.getClass().getResource("/refresh.png"))).getImage();
				Image hlgt = ref.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
				btnrefresh.setIcon(new ImageIcon(hlgt));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnrefresh.setIcon(new ImageIcon(this.getClass().getResource("/refresh.png")));
			}
		});
		btnrefresh.setIcon(new ImageIcon(this.getClass().getResource("/refresh.png")));
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
					Image back = (new ImageIcon(this.getClass().getResource("/007355-blue-jelly-icon-arrows-arrow-styled-left.png"))).getImage();
					Image hlgt = back.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
					btnBack.setIcon(new ImageIcon(hlgt));
				}			
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(btnBack.isEnabled())
					btnBack.setIcon(new ImageIcon(this.getClass().getResource("/007355-blue-jelly-icon-arrows-arrow-styled-left.png")));
			}
		});
		btnBack.setEnabled(false);
		btnBack.setIcon(new ImageIcon(this.getClass().getResource("/007355-blue-jelly-icon-arrows-arrow-styled-left.png")));
		btnBack.setBounds(596, 11, 50, 50);
		contentPane.add(btnBack);
	}
	
	private void LoadAddress()
	{
		try{
			String query = new String("SELECT Address from sequencerestaurant Where ResID ='"+ ResID +"'");
			Statement stmt = SQLConnection.conn.createStatement();
			SqlArrayList seq_address = new SqlArrayList(stmt.executeQuery(query));
			Sequence_address = seq_address.getColumn(0);
			selected_address = 0;		
			AddressCb.setModel(new DefaultComboBoxModel(Sequence_address));
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void refreshOrder()
	{
		try{
			auto_delete();
			String query = "SELECT DISTINCT FullName,Birthday,PhoneNumber,Time,AID FROM reservation natural join account WHERE ResAddress = '"+ Sequence_address[selected_address]+"'";
			Statement stmt = SQLConnection.conn.createStatement();
			RequestInfo = new SqlArrayList(stmt.executeQuery(query));
			((DefaultTableModel)OrderTable.getModel()).setRowCount(0);
			OrderTable.setModel(new DefaultTableModel(
			RequestInfo.get2dArray()
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
			OrderTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
			OrderTable.getColumnModel().getColumn(0).setPreferredWidth(151);
			stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void refresh_food()
	{
		try{
			String query = "SELECT Foodname,Cost,DescribeFood FROM provide WHERE ResID = '" + ResID +"'";
			Statement stmt = SQLConnection.conn.createStatement();
			FoodMenuInfo = new SqlArrayList(stmt.executeQuery(query));
			FoodMenutbl.setModel(new DefaultTableModel(
					FoodMenuInfo.get2dArray(),
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
				FoodMenutbl.getColumnModel().getColumn(0).setResizable(false);
				FoodMenutbl.getColumnModel().getColumn(1).setResizable(false);
				stmt.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		btnEditPrice.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	private void get_OrderDetail()
	{
		try{
			int selected_index = OrderTable.getSelectedRow();
			if(selected_index < 0)
			{
				ClearOrderDetail();
			}
			else
			{
				String AID = RequestInfo.getRow(selected_index)[4];
				String FullName = RequestInfo.getRow(selected_index)[0];
				String birthday = RequestInfo.getRow(selected_index)[1];
				String phonenum = RequestInfo.getRow(selected_index)[2];
				String Time = RequestInfo.getRow(selected_index)[3];
				
				Statement stmt = SQLConnection.conn.createStatement();
				
				String foodlistquery = "SELECT Foodname,Cost,Quantity,StatusReser,NextStatusForRestaurant,RestaurantRight FROM reservation WHERE ResAddress = '"+ Sequence_address[selected_address] +"' AND AID = '"+ AID +"' AND `Time` ='"+ Time +"'";		
				RQfoodlist = new SqlArrayList(stmt.executeQuery(foodlistquery));
				
				String totalCostquery ="SELECT SUM(Cost * Quantity) FROM reservation WHERE ResAddress = '"+ Sequence_address[selected_address] +"' AND AID = '"+ AID +"' AND `Time` ='"+ Time +"'";
				SqlArrayList totalcost = new SqlArrayList(stmt.executeQuery(totalCostquery));
				String Total = totalcost.getRow(0)[0];
				
				lblCusNameInfo.setText(FullName);
				lblbirthdayInfo.setText(birthday);
				lblPhonenumInfo.setText(phonenum);
				lblTimeInfo.setText(Time);
				lblTotalCostInfo.setText(Total);
				
				RQFoodtbl.setModel(new DefaultTableModel(
						RQfoodlist.get2dArray()
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
					RQFoodtbl.getColumnModel().getColumn(0).setPreferredWidth(100);
					RQFoodtbl.getColumnModel().getColumn(1).setPreferredWidth(50);
					RQFoodtbl.getColumnModel().getColumn(2).setPreferredWidth(25);
					RQFoodtbl.getColumnModel().getColumn(3).setPreferredWidth(25);
				totalcost.close();
				stmt.close();
				btnReject.setEnabled(false);
				btnDeleteOrder.setEnabled(false);
				btnLock.setEnabled(false);
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void ClearOrderDetail()
	{
		lblCusNameInfo.setText("");
		lblbirthdayInfo.setText("");
		lblPhonenumInfo.setText("");
		lblTimeInfo.setText("");
		lblTotalCostInfo.setText("");
		
		RQFoodtbl.setModel(new DefaultTableModel(
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
			RQFoodtbl.getColumnModel().getColumn(0).setPreferredWidth(100);
			RQFoodtbl.getColumnModel().getColumn(1).setPreferredWidth(50);
			RQFoodtbl.getColumnModel().getColumn(2).setPreferredWidth(25);
			RQFoodtbl.getColumnModel().getColumn(3).setPreferredWidth(25);
		btnReject.setEnabled(false);
		btnDeleteOrder.setEnabled(false);
		btnLock.setEnabled(false);
	}
	
	private void Lock_Order()
	{
		try
		{
			int selected_customer = OrderTable.getSelectedRow();
			int selected_foodorder = RQFoodtbl.getSelectedRow();
			if(selected_customer < 0 || selected_foodorder < 0) return;
			
			if(!checkorder_changed())
			{
				JOptionPane.showMessageDialog(null, "Data have been changed.Click OK to refresh");
				refreshOrder();
				ClearOrderDetail();
				return;
			}
			
			if(!RQfoodlist.getRow(selected_foodorder)[4].toLowerCase().contains("Delete".toLowerCase()))
			{
				JOptionPane.showMessageDialog(null, "Can not Lock this Order");
				return;
			}
			
			String AID = RequestInfo.getRow(selected_customer)[4];
			
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String time = RequestInfo.getRow(selected_customer)[3];
			String foodname = RQfoodlist.getRow(selected_foodorder)[0];
			Date ordertime = format.parse(time);
			
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			cal.add(Calendar.DATE, -1);
			
			if(ordertime.after(cal.getTime()))
			{
				 Statement upt_stmt = SQLConnection.conn.createStatement();
				 String update ="UPDATE reservation SET StatusReser = 'Lock',CustomerRight = 'Change Status',RestaurantRight = 'Delete After Order Day 1 Day',NextStatusForCustomer = 'OK',NextStatusForRestaurant = 'Nothing'   WHERE AID = '" + AID +"' AND ResAddress = '" + Sequence_address[selected_address] +"' AND Time ='" + time +"' AND Foodname = '"+ foodname +"'";
				 upt_stmt.executeUpdate(update);
				 refreshOrder();
				 ClearOrderDetail();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "This Order can not be locked yet");
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void delete_order()
	{
		try
		{
			int selected_customer = OrderTable.getSelectedRow();
			int selected_foodorder = RQFoodtbl.getSelectedRow();
			if(selected_customer < 0 || selected_foodorder < 0) return;
			
			if(!checkorder_changed())
			{
				JOptionPane.showMessageDialog(null, "Data have been changed.Click OK to refresh");
				refreshOrder();
				ClearOrderDetail();
				return;
			}
			
			if(!RQfoodlist.getRow(selected_foodorder)[5].toLowerCase().equals("Delete".toLowerCase()))
			{
				JOptionPane.showMessageDialog(null, "Can not delete this Order");
				return;
			}
			
			String AID = RequestInfo.getRow(selected_customer)[4];
			String Time = RequestInfo.getRow(selected_customer)[3];
			String food_name = RQfoodlist.getRow(selected_foodorder)[0];
			String query_delete = "DELETE FROM reservation WHERE AID = '" + AID +"' AND ResAddress = '" + Sequence_address[selected_address] +"' AND Time ='"+ Time +"' AND Foodname = '"+food_name +"'";
			
			Statement stmt = SQLConnection.conn.createStatement();
			stmt.executeUpdate(query_delete);
			stmt.close();
			refreshOrder();
			ClearOrderDetail();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void deny_order()
	{
		try
		{
			int selected_customer = OrderTable.getSelectedRow();
			int selected_foodorder = RQFoodtbl.getSelectedRow();
			if(selected_customer < 0 || selected_foodorder < 0) return;
			
			if(!checkorder_changed())
			{
				JOptionPane.showMessageDialog(null, "Data have been changed.Click OK to refresh");
				refreshOrder();
				ClearOrderDetail();
				return;
			}
			
			if(!RQfoodlist.getRow(selected_foodorder)[4].toLowerCase().contains("Deny".toLowerCase()))
			{
				JOptionPane.showMessageDialog(null, "Can not Deny this Order");
				return;
			}
			
			String AID = RequestInfo.getRow(selected_customer)[4];
			String Time = RequestInfo.getRow(selected_customer)[3];
			String food_name = RQfoodlist.getRow(selected_foodorder)[0];
			String query_upd = "UPDATE reservation SET StatusReser = 'Deny',CustomerRight = 'Delete',RestaurantRight = 'Nothing',NextStatusForCustomer = 'Nothing',NextStatusForRestaurant = 'Nothing' WHERE AID = '" + AID +"' AND ResAddress = '" + Sequence_address[selected_address] +"' AND Time ='"+ Time +"' AND Foodname = '"+food_name +"'";
			
			Statement stmt = SQLConnection.conn.createStatement();
			stmt.executeUpdate(query_upd);
			stmt.close();
			
			refreshOrder();
			ClearOrderDetail();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void auto_delete()
	{
		try
		{
			String delete_query = "DELETE FROM reservation WHERE StatusReser = 'OK' AND ResAddress = '" + Sequence_address[selected_address]+"'";
			Statement stmt = SQLConnection.conn.createStatement();
			stmt.executeUpdate(delete_query);
			
			String check_query = "SELECT DISTINCT `TIME` FROM reservation WHERE StatusReser = 'Lock' AND ResAddress = '" + Sequence_address[selected_address]+"'";
			SqlArrayList check_array = new SqlArrayList(stmt.executeQuery(check_query));
			for(int i = 0; i< check_array.getRownumber();i++)
			{
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				String time = check_array.getRow(i)[0];
				Date ordertime = format.parse(time);
				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
				cal.add(Calendar.DATE, 1);
				
				if(ordertime.after(cal.getTime()))
				{
					 String delete = "DELETE FROM reservation WHERE Time = '" + time +"' AND StatusReser = 'Lock' AND ResAddress = '" + Sequence_address[selected_address] +"'";
					 stmt.executeUpdate(delete);
				}
			}
			stmt.close();
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private boolean CompareData(SqlArrayList a,SqlArrayList b)
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
	
	private boolean checkorder_changed()
	{
		try{
			int selected_customer = OrderTable.getSelectedRow();
			int selected_foodorder = RQFoodtbl.getSelectedRow();
			String AID,Time;
		
			Statement stmt = SQLConnection.conn.createStatement();
			String query_1 = "SELECT DISTINCT FullName,Birthday,PhoneNumber,Time,AID FROM reservation natural join account WHERE ResAddress = '"+ Sequence_address[selected_address] +"'";
			SqlArrayList new_data = new SqlArrayList(stmt.executeQuery(query_1));
		
			if(CompareData(RequestInfo, new_data))
			{
				AID = RequestInfo.getRow(selected_customer)[4];
				Time = RequestInfo.getRow(selected_customer)[3];
				String query_2 = "SELECT Foodname,Cost,Quantity,StatusReser,NextStatusForRestaurant,RestaurantRight FROM reservation WHERE ResAddress = '"+ Sequence_address[selected_address] +"' AND AID = '"+ AID +"' AND `Time` ='"+ Time +"'";
				SqlArrayList new_data2 = new SqlArrayList(stmt.executeQuery(query_2));
				stmt.close();
				if(!CompareData(RQfoodlist, new_data2))
				{
					return false;
				}
				else return true;
			}
			else
			{
				stmt.close();
				return false;
			}			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	public JComboBox getAddressCb() {
		return AddressCb;
	}
}