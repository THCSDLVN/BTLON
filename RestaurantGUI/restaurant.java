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
	private JTable FoodMenutbl;
	
	private foodedit FeditDlog;
	private JTable OrderTable;
	private JComboBox AddressCb;
	private JButton btnDelete;
	private JButton btnEditPrice;
	private JButton btnAddFood;
	private JButton btnRQAccept;
	private JButton btnReject;
	private JButton btnrefresh;

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
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfile editdialog = new EditProfile(new String[]{"LOTTE","Mu Cang Chai"});
				editdialog.setVisible(true);
			}
		});
		btnEditProfile.setBounds(3, 142, 136, 33);
		InfoPane.add(btnEditProfile);
		
		AddressCb = new JComboBox();
		AddressCb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				selected_address = AddressCb.getSelectedIndex();
				ClearOrderDetail();
				refreshOrder();
			}
		});
		AddressCb.setBounds(189, 93, 288, 28);
		InfoPane.add(AddressCb);
		AddressCb.setModel(new DefaultComboBoxModel(Sequence_address));
		
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
				lblTotalCostInfo.setBounds(420, 315, 160, 25);
				RequestPane.add(lblTotalCostInfo);
				
				btnRQAccept = new JButton("Accept");
				btnRQAccept.setEnabled(false);
				btnRQAccept.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try{
							
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				});
				btnRQAccept.setBounds(675, 307, 89, 40);
				RequestPane.add(btnRQAccept);
				
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
				RQFoodtbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
				RQFoodtbl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				RQFoodtbl.setRowHeight(25);
				RQFoodtbl.setRowSelectionAllowed(false);
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
				
				btnReject = new JButton("Reject");
				btnReject.setEnabled(false);
				btnReject.setBounds(582, 307, 89, 40);
				RequestPane.add(btnReject);
		
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
				ClearOrderDetail();
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
			String query = "SELECT DISTINCT FullName,StatusReser,Birthday,PhoneNumber,Time,AID FROM reservation natural join account WHERE ResAddress = '"+ Sequence_address[selected_address]+"'";
			Statement stmt = SQLConnection.conn.createStatement();
			RequestInfo = new SqlArrayList(stmt.executeQuery(query));
			((DefaultTableModel)OrderTable.getModel()).setRowCount(0);
			OrderTable.setModel(new DefaultTableModel(
			RequestInfo.get2dArray()
			,
			new String[] {
				"Customer Name", "Status"
			}
			) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});	
			OrderTable.setFont(new Font("Tahoma", Font.PLAIN, 15));
			OrderTable.getColumnModel().getColumn(0).setPreferredWidth(107);
			OrderTable.getColumnModel().getColumn(1).setPreferredWidth(44);
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
				String AID = RequestInfo.getRow(selected_index)[5];
				String FullName = RequestInfo.getRow(selected_index)[0];
				String birthday = RequestInfo.getRow(selected_index)[2];
				String phonenum = RequestInfo.getRow(selected_index)[3];
				String Time = RequestInfo.getRow(selected_index)[4];
				
				Statement stmt = SQLConnection.conn.createStatement();
				
				String foodlistquery = "SELECT Foodname,Cost,Quantity FROM reservation WHERE ResAddress = '"+ Sequence_address[selected_address] +"' AND AID = '"+ AID +"' AND `Time` ='"+ Time +"'";		
				SqlArrayList RQfoodlist = new SqlArrayList(stmt.executeQuery(foodlistquery));
				
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
							"FOOD NAME", "PRICE", "QUANTITY"
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
					RQFoodtbl.getColumnModel().getColumn(0).setPreferredWidth(194);
					RQFoodtbl.getColumnModel().getColumn(1).setPreferredWidth(106);
				RQfoodlist.close();
				totalcost.close();
				stmt.close();
				btnRQAccept.setEnabled(true);
				btnReject.setEnabled(true);
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
					"FOOD NAME", "PRICE", "QUANTITY"
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
			RQFoodtbl.getColumnModel().getColumn(0).setPreferredWidth(194);
			RQFoodtbl.getColumnModel().getColumn(1).setPreferredWidth(106);
		btnRQAccept.setEnabled(false);
		btnReject.setEnabled(false);
	}
	
	private void delete_Order()
	{
		
	}
}