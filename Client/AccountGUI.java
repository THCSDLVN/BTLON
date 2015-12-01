package Client.accountGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import java.awt.font.TextAttribute;

import Client.accountinfor.AccountInfor;
import Client.solvearraylist.SolveArrayList;
import Client.clientprocess.ClientProcess;
import Client.hidepanel.HidePanel;
import Client.editprofileframe.EditProfileFrame;
import Client.myorder.MyOrder;
import Client.myrestaurantoption.MyRestaurantOption;
import Client.hidepanel2.HidePanel2;

public class AccountGUI extends JFrame{

	public JPanel contentPane = new JPanel();
	public JPanel menuAndOrder;
	public JPanel resInfo = new JPanel();
	public JPanel userPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JPanel restaurantPanel = new JPanel();
	
	public HidePanel2 myOrderPanel;
	public MyOrder myOrderView;
	public HidePanel menuPanel,orderPanel;

	public JTable table = new JTable();
	
	public int like = 0;
	public int row,column;
	
	public String resName = new String("");
	public String resAddr = new String("");
	public String resNumber = new String("");
	public String[] foodMenuColumns = {"Food","Price","Describe"}; 
	public String[][] orderData;
	public String[][] foodData;
	public String[][] myRestaurants;
	public String[] orderColumns ={"Time"};
	
	public List<String> myRestaurantsName = new ArrayList<String>();
	public List<String> myRestaurantsID = new ArrayList<String>();

	public JLabel resNameLbl;
	public JLabel resAddressLbl;
	public JLabel resPhoneLbl_1;
	public JLabel appNameIcon = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/AppName.png"));
	public JLabel userAvaLbl = new JLabel();
	public JLabel fullnameIconLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/Rsname.png"));
	public JLabel phoneNumberIconLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/res_phone.png"));
	public JLabel birthdayIconLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/birthday.png"));
	public JLabel restaurants = new JLabel("Restaurants",JLabel.CENTER);		
	public JLabel resAvaLbl = new JLabel();
	public JLabel resNameAvaLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/home.png"));
	public JLabel resAddressAvaLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/AddressIcon.png"));
	public JLabel resPhoneAvaLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/res_phone.png"));
	public JLabel fullnameLbl;
	public JLabel phoneNumberLbl;
	public JLabel birthdayLbl;
	public JLabel editProfileLbl = new JLabel("Edit Profile");
	public JLabel myResLbl = new JLabel("My Restaurant(s)");
	
	public JButton backBtn = new JButton("");
	public JButton refreshBtn = new JButton("");
	public JButton menuBtn = new JButton();
	public JButton orderBtn = new JButton();
	public JButton btnFeature = new JButton("Feature");
	public JButton goToMyResBtn = new JButton("Go");

	public JScrollPane scrollPane = new JScrollPane();

	public JComboBox myResCbox = new JComboBox();

	public AccountInfor accInfor = new AccountInfor();
	public ClientProcess clientProcess = new ClientProcess();

	public AccountGUI(AccountInfor aI,ClientProcess cP) {
		super(aI.getUsername());
		accInfor = aI;
		clientProcess = cP;

		fullnameLbl = new JLabel(accInfor.getFullname());
		phoneNumberLbl = new JLabel(accInfor.getPhoneNumber());
		birthdayLbl = new JLabel(accInfor.getBirthday());

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		setPreferredSize(new Dimension(900, 620));
		setResizable(false);
		pack();

		contentPane.setLayout(null);
		
		userPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 204, 204), null));
		userPanel.setBounds(12, 12, 440, 596);
		contentPane.add(userPanel);
		userPanel.setLayout(null);
		
		appNameIcon.setBounds(12, 12, 428, 75);
		userPanel.add(appNameIcon);
		
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		panel.setBounds(12, 99, 416, 200);
		userPanel.add(panel);
		panel.setLayout(null);
		
		if(accInfor.getSex().equals("Male")){
			userAvaLbl.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/male.png"));
		}
		else{
			userAvaLbl.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/female.png"));
		}
		userAvaLbl.setBounds(12, 12, 128, 176);
		panel.add(userAvaLbl);
		
		fullnameIconLbl.setBounds(150, 12, 43, 38);
		panel.add(fullnameIconLbl);
		
		phoneNumberIconLbl.setBounds(152, 62, 43, 38);
		panel.add(phoneNumberIconLbl);
		
		birthdayIconLbl.setBounds(152, 112, 43, 38);
		panel.add(birthdayIconLbl);
		
		fullnameLbl.setBounds(209, 12, 195, 38);
		panel.add(fullnameLbl);
		
		phoneNumberLbl.setBounds(207, 62, 195, 38);
		panel.add(phoneNumberLbl);
		
		birthdayLbl.setBounds(207, 112, 195, 38);
		panel.add(birthdayLbl);
		
		myResLbl.setBounds(12, 162, 128, 26);
		panel.add(myResLbl);

		myResCbox.setBounds(150, 162, 207, 26);
		panel.add(myResCbox);
		List<List<String>> myResList = new ArrayList();
		do{
			;
		}
		while(!clientProcess.request.toString().equals(""));
		clientProcess.getRequestFromClient("dataQuery{Restaurant~ResID,Resname~\"\"~AID = '" + accInfor.getAID() +"'~\"\"~\"\"}");
		do{
			if(clientProcess.lock == 1){
				clientProcess.setRequest();
				JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
				dispose();	
			}
			//Vong lap nay dung de cho den khi co ket qua
		}while(!clientProcess.request.toString().equals(""));
		myResList = clientProcess.getResultList();
		clientProcess.setResultList();

		if(myResList.size() == 0){
			;
		}
		else{
			myRestaurants = SolveArrayList.ConvertFromArrayList(myResList);
			int i;
			for(i = 0; i < myRestaurants.length; i++){
				myRestaurantsName.add(myRestaurants[i][1].toString());
				myRestaurantsID.add(myRestaurants[i][0].toString());
			}
			myResCbox.setModel(new DefaultComboBoxModel(myRestaurantsName.toArray()));
		}

		goToMyResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = myResCbox.getSelectedIndex();
				if(index == -1){
					new MyRestaurantOption(clientProcess,null,null,myRestaurantsName,myRestaurantsID,accInfor.getAID(),myResCbox).setVisible(true);
				}
				else{
					new MyRestaurantOption(clientProcess,myRestaurantsName.get(index),myRestaurantsID.get(index),myRestaurantsName,myRestaurantsID, accInfor.getAID(),myResCbox).setVisible(true);
				}
			}
		});
		goToMyResBtn.setBounds(363, 162, 50, 25);
		goToMyResBtn.setFont(new Font("Ubuntu",0,12));
		panel.add(goToMyResBtn);

		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		scrollPane.setBounds(12, 359, 416, 188);
		userPanel.add(scrollPane);

		editProfileLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editProfileLbl.setFont(new Font("Tahoma", Font.BOLD, 13));
		editProfileLbl.setForeground(new Color(34, 135, 254));
		if(editProfileLbl.getMouseListeners().length < 1){
			editProfileLbl.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					EditProfileFrame edit = new EditProfileFrame(accInfor,clientProcess,fullnameLbl,phoneNumberLbl);
				}

				public void mouseEntered(MouseEvent me) {
					final Map attributes = (new Font("Tahoma", Font.BOLD, 13)).getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(new Font(attributes));
				}
				
				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(new Font("Tahoma", Font.BOLD, 13));
				}
			});
		}
		panel.add(editProfileLbl);
		editProfileLbl.setBounds(325, 123, 195, 14);
		
		String[] columnNames = {"Restaurant","Address"};
		List<List<String>> resList = new ArrayList<>();		
		do{
			;
		}
		while(!clientProcess.request.toString().equals(""));	
		clientProcess.getRequestFromClient("dataQuery{Restaurant,SequenceRestaurant~Resname,Address~Restaurant.ResID = SequenceRestaurant.ResID~\"\"~\"\"~\"\"}");
		do{
			if(clientProcess.lock == 1){
				clientProcess.setRequest();
				JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
				dispose();
			}
			//Vong lap nay dung de cho den khi co ket qua
		}while(!clientProcess.request.toString().equals(""));
		resList = clientProcess.getResultList();
		clientProcess.setResultList();
			
		String[][] x = SolveArrayList.ConvertFromArrayList(resList);
		DefaultTableModel model = new DefaultTableModel(x, columnNames){
			boolean[] columnEditables = new boolean[] {false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}	
		};
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(184);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		scrollPane.setViewportView(table);

		btnFeature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					DefaultTableModel model = (DefaultTableModel)table.getModel();
					column = table.getSelectedColumn();
					row = table.getSelectedRow();
					if(row < 0){
						return;
					}
					resName = (String)model.getValueAt(row, 0);
					resAddr = (String)model.getValueAt(row, 1);
					List<List<String>> resNumber = new ArrayList();
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Account~PhoneNumber~\"\"~AID = (select AID from Restaurant where Resname = '" + resName +"')~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					resNumber = clientProcess.getResultList();
					clientProcess.setResultList();

					if(resNumber.size() == 0){
						JOptionPane.showMessageDialog(null, "This Restaurant Doesn't Exist");
						row = table.getSelectedRow();
						DefaultTableModel tempModel = (DefaultTableModel)table.getModel();
						tempModel.removeRow(row);
						row = -1;
						resNameLbl.setText("");
						resAddressLbl.setText("");
						resPhoneLbl_1.setText("");
						return;
					}
					
					getResNameLbl_1().setText(resName);
					getResAddressLbl().setText(resAddr);
					getResPhoneLbl().setText(resNumber.get(0).get(0));
					getPanel_1().setVisible(true);

					if(getMenuPanel().isVisible()){
						getMenuPanel().setVisible(false);
					}
					if(getMyOrderPanel().isVisible()){
						getMyOrderPanel().setVisible(false);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnFeature.setBounds(311, 559, 117, 25);
		userPanel.add(btnFeature);
		
		restaurants.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/menu.png"));
		restaurants.setFont(new Font("", Font.ITALIC, 14));
		restaurants.setBounds(12, 306, 416, 41);
		userPanel.add(restaurants);
		
		restaurantPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 204, 204), null));
		restaurantPanel.setBounds(464, 12, 424, 596);
		contentPane.add(restaurantPanel);
		restaurantPanel.setLayout(null);
		
		resInfo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		resInfo.setBounds(12, 12, 400, 208);
		restaurantPanel.add(resInfo);
		resInfo.setLayout(null);
		
		resAvaLbl.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/restaurant_icon.png"));
		resAvaLbl.setBounds(12, 32, 151, 144);
		resInfo.add(resAvaLbl);
		
		resNameAvaLbl.setBounds(175, 12, 43, 38);
		resInfo.add(resNameAvaLbl);
		
		resAddressAvaLbl.setBounds(175, 62, 43, 38);
		resInfo.add(resAddressAvaLbl);
		
		resPhoneAvaLbl.setBounds(175, 112, 43, 38);
		resInfo.add(resPhoneAvaLbl);
		
		resNameLbl = new JLabel(resName);
		resNameLbl.setBounds(219, 12, 169, 38);
		resInfo.add(resNameLbl);
		
		resAddressLbl = new JLabel(resAddr);
		resAddressLbl.setBounds(219, 62, 169, 38);
		resInfo.add(resAddressLbl);
		
		resPhoneLbl_1 = new JLabel((String) null);
		resPhoneLbl_1.setBounds(219, 112, 169, 38);
		resInfo.add(resPhoneLbl_1);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanel_1().setVisible(true);
				if(getMenuPanel().isVisible()){
					getMenuPanel().setVisible(false);
				}
				if(getMyOrderPanel().isVisible()){
					getMyOrderPanel().setVisible(false);
				}
				if(getOrderViewPanel().isVisible()){
					getOrderViewPanel().setVisible(false);
				}
			}
		});
		backBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png"));
		backBtn.setBounds(357, 232, 55, 56);
		restaurantPanel.add(backBtn);
		
		refreshBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/refresh.png"));
		refreshBtn.setBounds(290, 232, 55, 56);
		restaurantPanel.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Restaurant","Address"};
				List<List<String>> resList = new ArrayList<>();

				do{
					;
				}
				while(!clientProcess.request.toString().equals(""));	
				clientProcess.getRequestFromClient("dataQuery{Restaurant,SequenceRestaurant~Resname,Address~Restaurant.ResID = SequenceRestaurant.ResID~\"\"~\"\"~\"\"}");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						dispose();
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				resList = clientProcess.getResultList();
				clientProcess.setResultList();
				
				String[][] x = SolveArrayList.ConvertFromArrayList(resList);
				DefaultTableModel model = new DefaultTableModel(){
					boolean[] columnEditables = new boolean[] {false, false, false};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}	
				};
				
				table.getColumnModel().getColumn(0).setPreferredWidth(184);
				table.getColumnModel().getColumn(1).setPreferredWidth(133);
				
				int i;
				for(i = 0; i < 2; i++){
					model.addColumn(columnNames[i]);
				}
				for(i = 0; i < x.length; i++){
					model.addRow(x[i]);
				}
				table.setModel(model);
			}
		});
		
		menuAndOrder = new JPanel();
		menuAndOrder.setBounds(12, 300, 400, 284);
		menuAndOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		restaurantPanel.add(menuAndOrder);
		menuAndOrder.setLayout(null);
		
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(resName.equals("")){
					JOptionPane.showMessageDialog(null, "You Didn't Choose Restaurant");
					return ;
				}
				if(clientProcess.lock == 0){
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Account~PhoneNumber~\"\"~AID = (select AID from Restaurant where Resname = '" + resName +"')~\"\"~\"\"}");
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

					if(result.size() == 0){
						JOptionPane.showMessageDialog(null, "This Restaurant Is Not Exist");
						DefaultTableModel tempModel = (DefaultTableModel)table.getModel();
						if(row < 0){
							return;
						}
						tempModel.removeRow(row);
						row = -1;
						return;
					}
					else{
						JTable menu = getMenuPanel().getMenu();
						List<List<String>> foodMenu = new ArrayList();
						
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Provide~Foodname,Cost,DescribeFood~\"\"~ResID = (select ResID from SequenceRestaurant where Address = '" + resAddr +"')~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						foodMenu = clientProcess.getResultList();
						clientProcess.setResultList();

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

						List<List<String>> myOrderDate = new ArrayList(); 
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Reservation~Time~\"\"~AID = '" + accInfor.getAID() + "' and ResAddress = '" + resAddr + "'~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						myOrderDate = clientProcess.getResultList();
						clientProcess.setResultList();

						for(List<String> innerLs : myOrderDate){
							for(Iterator<String> i = innerLs.iterator();i.hasNext();){
								getMenuPanel().getOrderItem().addItem(i.next());
							}
						}
						getPanel_1().setVisible(false);
						getMenuPanel().setVisible(true);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		menuBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/food.png"));
		menuBtn.setBounds(12, 65, 172, 162);
		menuAndOrder.add(menuBtn);
		
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Account~PhoneNumber~\"\"~AID = (select AID from Restaurant where Resname = '" + resName +"')~\"\"~\"\"}");
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

					if(result.size() == 0){
						JOptionPane.showMessageDialog(null, "This Restaurant Doesn't Exist. Please Press Refresh Button");
						DefaultTableModel tempModel = (DefaultTableModel)table.getModel();
						if(row < 0){
							return;
						}
						tempModel.removeRow(row);
						row = -1;
						return;
					}
					HidePanel2 orderPanel = getMyOrderPanel();
					orderPanel.setResAddress(resAddr);
					JTable orderTable = getMyOrderPanel().getOrderTable();
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Reservation~Time~\"\"~AID = '" + accInfor.getAID() +"' and ResAddress = '" + resAddr +"'~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));

					List<List<String>> myOrderList = new ArrayList();
					myOrderList = clientProcess.getResultList();
					clientProcess.setResultList();
					orderData = SolveArrayList.ConvertFromArrayList(myOrderList);
					DefaultTableModel model = new DefaultTableModel(orderData, orderColumns);
					orderTable.setModel(model);
					orderPanel.setVisible(true);
					getPanel_1().setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}
		});
		orderBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/request-128.png"));
		orderBtn.setBounds(216, 65, 172, 162);
		menuAndOrder.add(orderBtn);
		
		menuPanel = new HidePanel(this,menuAndOrder.getBorder(),accInfor.getAID(),clientProcess);
		restaurantPanel.add(menuPanel);
		menuPanel.setVisible(false);
		
		myOrderPanel = new HidePanel2(this,menuAndOrder.getBorder(),accInfor.getAID(),clientProcess);
		restaurantPanel.add(myOrderPanel);

		myOrderView = new MyOrder(myOrderPanel,clientProcess);
		restaurantPanel.add(myOrderView);
		
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
		return resAddressLbl;
	}

	public JLabel getResPhoneLbl() {
		return resPhoneLbl_1;
	}

	public HidePanel getMenuPanel() {
		return menuPanel;
	}

	public HidePanel getOrderPanel(){
		return orderPanel;
	}

	public JPanel getRestaurantPanel() {
		return restaurantPanel;
	}

	public MyOrder getOrderViewPanel(){
		return myOrderView;
	}

	public HidePanel2 getMyOrderPanel(){
		return myOrderPanel;
	}
}