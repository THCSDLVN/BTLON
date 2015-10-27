package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.tools.ForwardingFileObject;
import javax.swing.AbstractListModel;

public class restaurant extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private JLabel lblPhoneInfo;
	private JLabel lblFacebookInfo;
	private JLabel lblRateInfo;
	private JLabel lblNameInfo;
	private JLabel lblAddressInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,Connection connection) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restaurant frame = new restaurant(args,connection);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private JTable RQFoodtbl;
	private JList CustomerList;
	private JLabel lblCusNameInfo;
	private JLabel lblCusPhoneInfo;
	private JLabel lblCusFacebookInfo;
	private JLabel lblTimeInfo;
	private JLabel lblTotalCostInfo;
	
	private Connection conn; // Ket noi den co so du lieu
	private Statement stmt;
	
	private String ResID = new String(); // ID cua nha hang
	
	private SqlArrayList RequestInfo;
	private SqlArrayList FoodRQInfo;
	private JTable FoodMenutbl;

	/**
	 * Create the frame.
	 */
	public restaurant(String[] RestaurantInfo,Connection connection) {
		ResID = RestaurantInfo[0];
		
		try{
			conn = connection;
			stmt = conn.createStatement();
			RequestInfo = new SqlArrayList(stmt.executeQuery("SELECT AID,FullName,PhoneNumber,Facebook,Time,FoodList FROM reservations natural join account Where RID = '"+ResID+"'"));
			FoodRQInfo = new SqlArrayList(stmt.executeQuery("SELECT FID,FoodName,Cost FROM provide natural join menu WHERE RID = '" + ResID + "'"));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}		
		
		setTitle(RestaurantInfo[1]);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\6520\\workspace\\gui\\img\\restaurant_icon.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogout = new JButton("");
		btnlogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login.main(null);
				dispose();
			}
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
		lblAva.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\restaurant_icon.png"));
		lblAva.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 0), new Color(204, 153, 51)));
		lblAva.setBounds(0, 0, 141, 141);
		InfoPane.add(lblAva);
		
		JLabel lblNameIcon = new JLabel("");
		lblNameIcon.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Rsname.png"));
		lblNameIcon.setBounds(151, 11, 28, 28);
		InfoPane.add(lblNameIcon);
		
		lblNameInfo = new JLabel("Name of restaurant");
		lblNameInfo.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNameInfo.setBounds(189, 11, 288, 28);
		InfoPane.add(lblNameInfo);
		lblNameInfo.setText(RestaurantInfo[1]);
		
		JLabel lblPhoneIcon = new JLabel("");
		lblPhoneIcon.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\res_phone.png"));
		lblPhoneIcon.setBounds(151, 43, 28, 28);
		InfoPane.add(lblPhoneIcon);
		
		lblPhoneInfo = new JLabel("Phone number");
		lblPhoneInfo.setBounds(189, 43, 288, 28);
		InfoPane.add(lblPhoneInfo);
		lblPhoneInfo.setText(RestaurantInfo[3]);
		
		JLabel lblAddressIcon = new JLabel("");
		lblAddressIcon.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\AddressIcon.png"));
		lblAddressIcon.setBounds(151, 76, 28, 28);
		InfoPane.add(lblAddressIcon);
		
		lblAddressInfo = new JLabel("Address");
		lblAddressInfo.setBounds(189, 76, 288, 28);
		InfoPane.add(lblAddressInfo);
		lblAddressInfo.setText(RestaurantInfo[2]);
		
		JLabel lblFacebookIcon = new JLabel("");
		lblFacebookIcon.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\facebook.png"));
		lblFacebookIcon.setBounds(151, 109, 28, 28);
		InfoPane.add(lblFacebookIcon);
		
		lblFacebookInfo = new JLabel("Facebook");
		lblFacebookInfo.setBounds(189, 109, 288, 28);
		InfoPane.add(lblFacebookInfo);
		lblFacebookInfo.setText(RestaurantInfo[4]);
		
		JLabel lblStarIcon = new JLabel("");
		lblStarIcon.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\star.png"));
		lblStarIcon.setBounds(32, 143, 30, 30);
		InfoPane.add(lblStarIcon);
		
		lblRateInfo = new JLabel("0.0");
		lblRateInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRateInfo.setBounds(77, 143, 30, 30);
		InfoPane.add(lblRateInfo);
		lblRateInfo.setText(RestaurantInfo[5]);
		
		JPanel SelectPane = new JPanel();
		SelectPane.setBounds(10, 200, 774, 361);
		contentPane.add(SelectPane);
		SelectPane.setLayout(null);
		
		JButton btnRequest = new JButton("REQUEST");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnBack.setEnabled(true);
				SelectPane.setVisible(false);
				contentPane.getComponents()[2].setVisible(true);
			}
		});
		btnRequest.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
		btnRequest.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnRequest.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRequest.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\request-128.png"));
		btnRequest.setBounds(105, 105, 150, 150);
		SelectPane.add(btnRequest);
		
		JButton btnFoodmenu = new JButton("FOOD LIST");
		btnFoodmenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				btnBack.setEnabled(true);
				SelectPane.setVisible(false);
				contentPane.getComponents()[3].setVisible(true);
			}
		});
		btnFoodmenu.setHorizontalTextPosition(SwingConstants.CENTER);
		btnFoodmenu.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnFoodmenu.setFont(new Font("Monotype Corsiva", Font.BOLD, 15));
		btnFoodmenu.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\food.png"));
		btnFoodmenu.setBounds(515, 105, 150, 150);
		SelectPane.add(btnFoodmenu);
		
		JPanel RequestPane = new JPanel();
		RequestPane.setLayout(null);
		RequestPane.setBounds(10, 200, 774, 361);
		RequestPane.setVisible(false);
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
		
		JLabel lblCusPhone = new JLabel("Phone num :");
		lblCusPhone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCusPhone.setBounds(381, 87, 106, 25);
		RequestPane.add(lblCusPhone);
		
		lblCusPhoneInfo = new JLabel("");
		lblCusPhoneInfo.setBounds(497, 87, 267, 25);
		RequestPane.add(lblCusPhoneInfo);
		
		JLabel lblCusFacebook = new JLabel("Facebook :");
		lblCusFacebook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCusFacebook.setBounds(381, 123, 106, 25);
		RequestPane.add(lblCusFacebook);
		
		lblCusFacebookInfo = new JLabel("");
		lblCusFacebookInfo.setToolTipText("");
		lblCusFacebookInfo.setBounds(497, 123, 267, 25);
		RequestPane.add(lblCusFacebookInfo);
		
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
		
		JButton btnRQAccept = new JButton("Accept");
		btnRQAccept.setBounds(664, 310, 89, 40);
		RequestPane.add(btnRQAccept);
		
		JLabel lblCustomerTitle = new JLabel("CUSTOMERS");
		lblCustomerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCustomerTitle.setBounds(0, 0, 360, 40);
		RequestPane.add(lblCustomerTitle);
		
		JScrollPane CusScrlPn = new JScrollPane();
		CusScrlPn.setBounds(0, 39, 360, 322);
		RequestPane.add(CusScrlPn);
		
		CustomerList = new JList();
		CustomerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					int index = CustomerList.getSelectedIndex();
					if(index >= 0)
					{
						String[] RowInfo = RequestInfo.getRow(index);
						String foodIDs = RowInfo[5].replace(",", "','");
						SqlArrayList Foods = new SqlArrayList(stmt.executeQuery("SELECT FoodName,Cost,(SELECT SUM(COST) FROM provide natural join menu WHERE RID = '"+ResID+"' AND FID IN ('"+foodIDs+"')) AS SUM FROM provide natural join menu WHERE RID = '"+ResID+"' AND FID IN ('"+foodIDs+"') "));
						lblCusNameInfo.setText(RowInfo[1]);
						lblCusPhoneInfo.setText(RowInfo[2]);
						lblCusFacebookInfo.setText(RowInfo[3]);
						lblTimeInfo.setText(RowInfo[4]);
						DefaultTableModel model = (DefaultTableModel) RQFoodtbl.getModel();
						model.setRowCount(0);
						for(int i=0;i<Foods.getRownumber();i++) model.addRow(Foods.getRow(i));
						lblTotalCostInfo.setText(Foods.getRow(0)[2]);
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}	
			}
		});
		CustomerList.setFont(new Font("Tahoma", Font.PLAIN, 20));
		CustomerList.setModel(new AbstractListModel() {
			String[] values = RequestInfo.getColumn(1);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		CustomerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		CusScrlPn.setViewportView(CustomerList);
		
		JScrollPane RQFoodScrlPn = new JScrollPane();
		RQFoodScrlPn.setBounds(381, 185, 383, 119);
		RequestPane.add(RQFoodScrlPn);
		
		RQFoodtbl = new JTable();
		RQFoodScrlPn.setViewportView(RQFoodtbl);
		RQFoodtbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"FOOD", "PRICE"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
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
		RQFoodtbl.getColumnModel().getColumn(0).setResizable(false);
		RQFoodtbl.getColumnModel().getColumn(1).setResizable(false);
		
		JPanel FoodPane = new JPanel();
		FoodPane.setLayout(null);
		FoodPane.setBounds(10, 200, 774, 361);
		FoodPane.setVisible(false);
		contentPane.add(FoodPane);
		
		JLabel lblFoodTitle = new JLabel("FOOD MENU");
		lblFoodTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoodTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFoodTitle.setBounds(0, 0, 383, 40);
		FoodPane.add(lblFoodTitle);
		
		JButton btnEditPrice = new JButton("Edit Price");
		btnEditPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnEditPrice.setEnabled(false);
		btnEditPrice.setBounds(516, 69, 131, 92);
		FoodPane.add(btnEditPrice);
		
		JButton btnAddFood = new JButton("Add");
		btnAddFood.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddFood.setBounds(516, 220, 131, 92);
		FoodPane.add(btnAddFood);
		
		JScrollPane FoodMenuScrlPn = new JScrollPane();
		FoodMenuScrlPn.setBounds(0, 40, 383, 322);
		FoodPane.add(FoodMenuScrlPn);
		
		FoodMenutbl = new JTable();
		FoodMenutbl.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"FOOD", "PRICE"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		FoodMenutbl.getColumnModel().getColumn(0).setResizable(false);
		FoodMenutbl.getColumnModel().getColumn(1).setResizable(false);
		FoodMenuScrlPn.setViewportView(FoodMenutbl);
		btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-icon.png")));
		btnlogout.setBounds(734, 11, 50, 50);
		contentPane.add(btnlogout);
		
		JButton btnrefresh = new JButton("");
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
		btnBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(btnBack.isEnabled())
				{
					contentPane.getComponents()[2].setVisible(false);
					contentPane.getComponents()[3].setVisible(false);
					contentPane.getComponents()[1].setVisible(true);
					btnBack.setEnabled(false);
				}
			}
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
		btnBack.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\007355-blue-jelly-icon-arrows-arrow-styled-left.png"));
		btnBack.setBounds(596, 11, 50, 50);
		contentPane.add(btnBack);
	}
	
	public JButton getBtnBack() {
		return btnBack;
	}
	public JLabel getLblPhoneInfo() {
		return lblPhoneInfo;
	}
	public JLabel getLblFacebookInfo() {
		return lblFacebookInfo;
	}
	public JLabel getLblRateInfo() {
		return lblRateInfo;
	}
	public JLabel getLblNameInfo() {
		return lblNameInfo;
	}
	public JLabel getLblAddressInfo() {
		return lblAddressInfo;
	}
	public JList getCustomerList() {
		return CustomerList;
	}
	public JTable getRQFoodList() {
		return RQFoodtbl;
	}
	public JLabel getLblCusNameInfo() {
		return lblCusNameInfo;
	}
	public JLabel getLblCusPhoneInfo() {
		return lblCusPhoneInfo;
	}
	public JLabel getLblCusFacebookInfo() {
		return lblCusFacebookInfo;
	}
	public JLabel getLblTimeInfo() {
		return lblTimeInfo;
	}
	public JLabel getLblTotalCostInfo() {
		return lblTotalCostInfo;
	}
}
