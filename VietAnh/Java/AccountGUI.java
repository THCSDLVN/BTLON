import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;

import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JComboBox;

public class AccountGUI extends JFrame {

	private JPanel contentPane;
	private JTable resListTable;
	public SQLConnection c = new SQLConnection();
	public int like = 0;
	private JPanel menuAndOrder;
	public int row,column;
	public String resName = "",resAddr = "",resNumber = "";
	private JPanel resInfo;
	private JLabel resNameLbl;
	private JLabel ResAddressLbl;
	private HidePanel menuPanel;
	private JLabel resPhoneLbl_1;
	public String[] foodMenuColumns = {"Food","Price"}; 
	public String[][] foodData;
	public String[] orderColumns = {"Time"};
	public String[][] orderData;
	
	// Code moi.
	public String[] myResname,myResID; 
	JComboBox myResCbox;
	JButton goToMyResBtn;
	String[][] myRestaurants;
	
	// Code moi.
	private HidePanel2 myOrderPanel;
	private JPanel restaurantPanel;
	private MyOrder myOrderView;
	/**
	 * Launch the application.
	*/
	
	public static void main(String[] args) {
		Account a = new Account();
		a.setAID("ACC0101");
		a.setBirthday("30-1-1995");
		a.setFullname("Nguyen Viet Anh");
		a.setPassword("123");
		a.setPhonenumber("113");
		a.setSex("Male");
		a.setUsername("vansanjp");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountGUI frame = new AccountGUI(a);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountGUI(Account a) {
		super(a.getFullname());
		setResizable(false);
		setBounds(100, 100, 900, 620);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel userPanel = new JPanel();
		userPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 204, 204), null));
		userPanel.setBounds(12, 12, 440, 596);
		contentPane.add(userPanel);
		userPanel.setLayout(null);
		
		JLabel appNameIcon = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/AppName.png")));
		appNameIcon.setBounds(12, 12, 428, 75);
		userPanel.add(appNameIcon);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		panel.setBounds(12, 99, 416, 200);
		userPanel.add(panel);
		panel.setLayout(null);
		
		JLabel userAvaLbl = new JLabel();
		if(a.getSex().equals("Male")){
			userAvaLbl.setIcon(new ImageIcon(this.getClass().getResource("/Picture/male.png")));
		}
		else
			userAvaLbl.setIcon(new ImageIcon(this.getClass().getResource("/Picture/female.png")));
		userAvaLbl.setBounds(12, 12, 128, 138);
		panel.add(userAvaLbl);
		
		JLabel fullnameIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/Rsname.png")));
		fullnameIconLbl.setBounds(154, 12, 43, 38);
		panel.add(fullnameIconLbl);
		
		JLabel phoneNumberIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/res_phone.png")));
		phoneNumberIconLbl.setBounds(152, 62, 43, 38);
		panel.add(phoneNumberIconLbl);
		
		JLabel birthdayIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/birthday.png")));
		birthdayIconLbl.setBounds(152, 112, 43, 38);
		panel.add(birthdayIconLbl);
		
		JLabel fullnameLbl = new JLabel(a.getFullname());
		fullnameLbl.setBounds(209, 12, 195, 38);
		panel.add(fullnameLbl);
		
		JLabel phoneNumberLbl = new JLabel(a.getPhoneNumber());
		phoneNumberLbl.setBounds(207, 62, 195, 38);
		panel.add(phoneNumberLbl);
		
		JLabel birthdayLbl = new JLabel(a.getBirthday());
		birthdayLbl.setBounds(207, 112, 195, 38);
		panel.add(birthdayLbl);
		
		// Code moi
		JLabel myResLbl = new JLabel("My Restaurants");
		myResLbl.setBounds(12, 162, 128, 26);
		panel.add(myResLbl);
		
		//Code moi
		myResCbox = new JComboBox();
		myResCbox.setBounds(154, 162, 207, 26);
		panel.add(myResCbox);
		List<List<String>> myResList = c.getMyRes(a.getAID()); // SQL : select ResID,Resname from Restaurant 
															   // where AID = '"+myAID+"'
		if(myResList.size() == 0);
		else{
			myRestaurants = SolveArrayList.ConvertFromArrayList(myResList);
			int i;
			for(i=0;i<myRestaurants.length;i++){
				myResCbox.addItem(myRestaurants[i][1]);
			}
		}
		
		//Code moi
		goToMyResBtn = new JButton("go");
		goToMyResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = myResCbox.getSelectedIndex();
				if(index == -1);
				else{
					System.out.println(myRestaurants[index][0] + " " + myRestaurants[index][1]);
					//Send myRestaurants[index][0] (ResID) and myRestaurants[index][1] (Resname) to Hung's RestaurantGUI.
					int success = c.DeleteThisRestaurant(myRestaurants[index][0]);
					if(success != 0){
						JOptionPane.showMessageDialog(null, "Thanh Cong");
						myResCbox.removeItemAt(index);
					}
				}
			}
		});
		goToMyResBtn.setBounds(373, 162, 36, 25);
		panel.add(goToMyResBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		scrollPane.setBounds(12, 359, 416, 188);
		userPanel.add(scrollPane);
		
		resListTable = new JTable();
		String[] columnNames = {"Restaurant","Address","Like"};
		List<List<String>> resList = new ArrayList<>();
		resList = c.getRes();// SQL : select Resname,Address from Restaurant natural join SequenceRestaurant
		String[][] x = SolveArrayList.ConvertFromArrayList(resList);
		DefaultTableModel model = new DefaultTableModel(x, columnNames);
		resListTable.setModel(model);
		resListTable.getColumnModel().getColumn(0).setResizable(false);
		resListTable.getColumnModel().getColumn(0).setPreferredWidth(184);
		resListTable.getColumnModel().getColumn(1).setResizable(false);
		resListTable.getColumnModel().getColumn(1).setPreferredWidth(133);
		resListTable.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(resListTable);

		JButton btnFeature = new JButton("Feature");
		btnFeature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel)resListTable.getModel();
				column = resListTable.getSelectedColumn();
				row = resListTable.getSelectedRow();
				if(row < 0)
					return;
				resName = (String)model.getValueAt(row, 0);
				resAddr = (String)model.getValueAt(row, 1);
				List<List<String>> resNumber = c.getResInfo(resName); // SQL : select PhoneNumber from Account "
						//+ "where AID = (select AID from SequenceRestaurant where Address = '"+resAddr+"')"
				if(resNumber.size() == 0){
					JOptionPane.showMessageDialog(null, "Nha hang khong ton tai");
					row = resListTable.getSelectedRow();
					DefaultTableModel tempModel = (DefaultTableModel)resListTable.getModel();
					tempModel.removeRow(row);
					row = -1;
					return;
				}
				getResNameLbl_1().setText(resName);
				getResAddressLbl().setText(resAddr);
				getResPhoneLbl().setText(resNumber.get(0).get(0));
				getPanel_1().setVisible(true);
				if(getMenuPanel().isVisible())
					getMenuPanel().setVisible(false);
				if(getMyOrderPanel().isVisible())
					getMyOrderPanel().setVisible(false);
			}
		});
		btnFeature.setBounds(311, 559, 117, 25);
		userPanel.add(btnFeature);
		
		JLabel Restaurants = new JLabel("Restaurants",JLabel.CENTER);
		Restaurants.setIcon(new ImageIcon(this.getClass().getResource("/Picture/menu.png")));
		Restaurants.setFont(new Font("", Font.ITALIC, 14));
		Restaurants.setBounds(12, 306, 416, 41);
		userPanel.add(Restaurants);
		
		restaurantPanel = new JPanel();
		restaurantPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 204, 204), null));
		restaurantPanel.setBounds(464, 12, 424, 596);
		contentPane.add(restaurantPanel);
		restaurantPanel.setLayout(null);
		
		resInfo = new JPanel();
		resInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		resInfo.setBounds(12, 12, 400, 208);
		restaurantPanel.add(resInfo);
		resInfo.setLayout(null);
		
		JLabel resAvaLbl = new JLabel();
		resAvaLbl.setIcon(new ImageIcon(this.getClass().getResource("/Picture/restaurant_icon.png")));
		resAvaLbl.setBounds(12, 32, 151, 144);
		resInfo.add(resAvaLbl);
		
		JLabel resNameAvaLbl = new JLabel(new ImageIcon(AccountGUI.class.getResource("/Picture/home.png")));
		resNameAvaLbl.setBounds(175, 12, 43, 38);
		resInfo.add(resNameAvaLbl);
		
		JLabel resAddressAvaLbl = new JLabel(new ImageIcon(AccountGUI.class.getResource("/Picture/AddressIcon.png")));
		resAddressAvaLbl.setBounds(175, 62, 43, 38);
		resInfo.add(resAddressAvaLbl);
		
		JLabel resPhoneAvaLbl = new JLabel(new ImageIcon(AccountGUI.class.getResource("/Picture/res_phone.png")));
		resPhoneAvaLbl.setBounds(175, 112, 43, 38);
		resInfo.add(resPhoneAvaLbl);
		
		resNameLbl = new JLabel(resName);
		resNameLbl.setBounds(219, 12, 169, 38);
		resInfo.add(resNameLbl);
		
		ResAddressLbl = new JLabel(resAddr);
		ResAddressLbl.setBounds(219, 62, 169, 38);
		resInfo.add(ResAddressLbl);
		
		resPhoneLbl_1 = new JLabel((String) null);
		resPhoneLbl_1.setBounds(219, 112, 169, 38);
		resInfo.add(resPhoneLbl_1);
		
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanel_1().setVisible(true);
				if(getMenuPanel().isVisible())
					getMenuPanel().setVisible(false);
				if(getMyOrderPanel().isVisible())
					getMyOrderPanel().setVisible(false);
				if(getOrderViewPanel().isVisible())
					getOrderViewPanel().setVisible(false);
			}
		});
		backBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/007355-blue-jelly-icon-arrows-arrow-styled-left.png")));
		backBtn.setBounds(357, 232, 55, 56);
		restaurantPanel.add(backBtn);
		
		JButton refreshBtn = new JButton("");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Restaurant","Address","Like"};
				List<List<String>> resList = new ArrayList<>();
				resList = c.getRes();// SQL : select Resname,Address from Restaurant natural join SequenceRestaurant
				String[][] x = SolveArrayList.ConvertFromArrayList(resList);
				DefaultTableModel model = new DefaultTableModel();
				int i;
				for(i=0;i<3;i++)
					model.addColumn(columnNames[i]);
				for(i=0;i<x.length;i++)
					model.addRow(x[i]);
				//System.out.println(model.getRowCount());
				resListTable.setModel(model);
			}
		});
		refreshBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/refresh.png")));
		refreshBtn.setBounds(290, 232, 55, 56);
		restaurantPanel.add(refreshBtn);
		
		menuAndOrder = new JPanel();
		menuAndOrder.setBounds(12, 300, 400, 284);
		menuAndOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		restaurantPanel.add(menuAndOrder);
		menuAndOrder.setLayout(null);
		
		JButton menuBtn = new JButton();// Nho kiem tra xem con NH do khong ?
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.getResInfo(resName).size() == 0){
					JOptionPane.showMessageDialog(null, "This Restaurant Is Not Exist");
					DefaultTableModel tempModel = (DefaultTableModel)resListTable.getModel();
					if(row < 0)
						return;
					tempModel.removeRow(row);
					row = -1;
					return;
				}
				else{
					JTable menu = getMenuPanel().getMenu();
					List<List<String>> foodMenu = c.getFoodFromRes(resAddr); // SQL : select FoodName,Cost from Provide "
					//+ "where ResID = (select ResID from SequenceRestaurant where Address = '"+ResAddress+"
					getMenuPanel().setResAddress(resAddr);// Input resAddr into HidePanel.
					foodData = new SolveArrayList().ConvertFromArrayList(foodMenu);
					DefaultTableModel model = new DefaultTableModel(foodData, foodMenuColumns){
						boolean[] columnEditables = new boolean[] {
								false, false, false
							};
							public boolean isCellEditable(int row, int column) {
								return columnEditables[column];
							}
					};
					menu.setModel(model);
					// Code moi.
					// SQL:select distinct `Time` from `Reservation` "
					//+ "where AID = '"+AID+"' and `ResAddress` = '"+resAddress+"'
					List<List<String>> myOrderDate = c.getMyOrderDateFromThisRes(a.getAID(), resAddr);
					for(List<String> innerLs : myOrderDate){
						for(Iterator<String> i = innerLs.iterator();i.hasNext();){
							getMenuPanel().getOrderItem().addItem(i.next());
						}
					}
					getPanel_1().setVisible(false);
					getMenuPanel().setVisible(true);
				}
			}
		});
		menuBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/food.png")));
		menuBtn.setBounds(12, 65, 172, 162);
		menuAndOrder.add(menuBtn);
		
		//Code moi.
		JButton orderBtn = new JButton(); // Nho kiem tra xem nha hang con o do khong?
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.getResInfo(resName).size() == 0){
					JOptionPane.showMessageDialog(null, "This Restaurant Is Not Exist");
					DefaultTableModel tempModel = (DefaultTableModel)resListTable.getModel();
					if(row < 0)
						return;
					tempModel.removeRow(row);
					row = -1;
					return;
				}
				HidePanel2 orderPanel = getMyOrderPanel();
				orderPanel.setResAddress(resAddr);
				JTable orderTable = getMyOrderPanel().getOrderTable();
				List<List<String>> myOrderList = c.getMyOrderFromThisRes(a.getAID(),resAddr);
				orderData = SolveArrayList.ConvertFromArrayList(myOrderList);
				DefaultTableModel model = new DefaultTableModel(orderData, orderColumns);
				orderTable.setModel(model);
				orderPanel.setVisible(true);
				getPanel_1().setVisible(false);
			}
		});
		orderBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/request-128.png")));
		orderBtn.setBounds(216, 65, 172, 162);
		menuAndOrder.add(orderBtn);
		
		menuPanel = new HidePanel(this,menuAndOrder.getBorder(),a.getAID());
		restaurantPanel.add(menuPanel);
		menuPanel.setVisible(false);
		
		myOrderPanel = new HidePanel2(this,menuAndOrder.getBorder(),a.getAID());
		restaurantPanel.add(myOrderPanel);
		myOrderPanel.setVisible(false);
		
		myOrderView = new MyOrder(myOrderPanel);
		restaurantPanel.add(myOrderView);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMyRes(a.getAID()).setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/cancel.png")));
		button.setBounds(12, 232, 55, 56);
		restaurantPanel.add(button);
		myOrderView.setVisible(false);
	}
	public JPanel getPanel_1() {
		return menuAndOrder;
	}
	public JPanel getResInfo() {
		return resInfo;
	}
	public JLabel getResNameLbl_1() {
		return resNameLbl;
	}
	public JLabel getResAddressLbl() {
		return ResAddressLbl;
	}
	public HidePanel getMenuPanel() {
		return menuPanel;
	}
	public JLabel getResPhoneLbl() {
		return resPhoneLbl_1;
	}
	public HidePanel2 getMyOrderPanel(){
		return myOrderPanel;
	}
	public JPanel getRestaurantPanel() {
		return restaurantPanel;
	}
	public MyOrder getOrderViewPanel(){
		return myOrderView;
	}
}
