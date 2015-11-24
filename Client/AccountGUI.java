package Client.accountGUI;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import Client.accountinfor.AccountInfor;
import Client.solvearraylist.SolveArrayList;
import Client.clientprocess.ClientProcess;
import Client.hidepanel.HidePanel;

public class AccountGUI extends JFrame{

	public JPanel contentPane = new JPanel();
	public JPanel menuAndOrder;
	public JPanel resInfo = new JPanel();
	public JPanel userPanel = new JPanel();
	public JPanel panel = new JPanel();
	public JPanel restaurantPanel = new JPanel();
	public HidePanel menuPanel,orderPanel;

	public JTable table = new JTable();
	
	public int like = 0;
	public int row,column;
	
	public String resName = new String("");
	public String resAddr = new String("");
	public String resNumber = new String("");
	public String[] foodMenuColumns = {"Food","Price"}; 
	public String[][] data;
	
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
	public JLabel likeAvaLbl = new JLabel(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/red_heart.png"));
	public JLabel resLikeLbl = new JLabel((String) null);
	public JLabel fullnameLbl;
	public JLabel phoneNumberLbl;
	public JLabel birthdayLbl;
	
	public JButton likeBtn = new JButton("");
	public JButton backBtn = new JButton("");
	public JButton refreshBtn = new JButton("");
	public JButton menuBtn = new JButton();
	public JButton orderBtn = new JButton();
	public JButton btnFeature = new JButton("Feature");

	public JScrollPane scrollPane = new JScrollPane();

	public AccountInfor accInfor = new AccountInfor();
	public ClientProcess clientProcess = new ClientProcess();

	public AccountGUI(AccountInfor aI,ClientProcess cP) {
		super(aI.getFullname());
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
		
		fullnameIconLbl.setBounds(154, 12, 43, 38);
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
		
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		scrollPane.setBounds(12, 359, 416, 188);
		userPanel.add(scrollPane);
		
		String[] columnNames = {"Restaurant","Address","Like"};
		List<List<String>> resList = new ArrayList<>();			
		clientProcess.getRequestFromClient("dataQuery(Restaurant,SequenceRestaurant~Resname,Address,NumberLike~ResID~\"\"~\"\"~\"\")");
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
		DefaultTableModel model = new DefaultTableModel(x, columnNames);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(184);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		scrollPane.setViewportView(table);

		btnFeature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					column = table.getSelectedColumn();
					row = table.getSelectedRow();
					if(row < 0){
						return;
					}
					resName = (String)model.getValueAt(row, 0);
					resAddr = (String)model.getValueAt(row, 1);
					List<List<String>> resNumber = new ArrayList();
					clientProcess.getRequestFromClient("dataQuery(Account~PhoneNumber~\"\"~AID = (select AID from Restaurant where Resname = '" + resName +"')~\"\"~\"\")");
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
					
					getResNameLbl_1().setText(resName);
					getResAddressLbl().setText(resAddr);
					getResPhoneLbl().setText(resNumber.get(0).get(0));
					JTable menu = getMenuPanel().getMenu();
					getMenuPanel().setResAddress(resAddr);
					List<List<String>> foodMenu = new ArrayList();
					clientProcess.getRequestFromClient("dataQuery(Provide~FoodName,Cost~\"\"~ResID = (select ResID from SequenceRestaurant where Address = '" + resAddr +"')~\"\"~\"\")");
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

					data = new SolveArrayList().ConvertFromArrayList(foodMenu);
					DefaultTableModel model = new DefaultTableModel(data, foodMenuColumns);
					menu.setModel(model);
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
		
		likeAvaLbl.setBounds(175, 162, 43, 38);
		resInfo.add(likeAvaLbl);
		
		resLikeLbl.setBounds(219, 162, 169, 38);
		resInfo.add(resLikeLbl);
		
		likeBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/flying_heart.png"));
		likeBtn.setBounds(12, 232, 55, 56);
		restaurantPanel.add(likeBtn);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanel_1().setVisible(true);
				if(getMenuPanel().isVisible()){
					getMenuPanel().setVisible(false);
				}
				if(getOrderPanel().isVisible()){
					getOrderPanel().setVisible(false);
				}
			}
		});
		backBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png"));
		backBtn.setBounds(357, 232, 55, 56);
		restaurantPanel.add(backBtn);
		
		refreshBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/refresh.png"));
		refreshBtn.setBounds(290, 232, 55, 56);
		restaurantPanel.add(refreshBtn);
		
		menuAndOrder = new JPanel();
		menuAndOrder.setBounds(12, 300, 400, 284);
		menuAndOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		restaurantPanel.add(menuAndOrder);
		menuAndOrder.setLayout(null);
		
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMenuPanel().setVisible(true);
				JTable menu = getMenuPanel().getMenu();
				List<List<String>> foodMenu = new ArrayList();
				clientProcess.getRequestFromClient("dataQuery(Provide~FoodName,Cost~\"\"~ResID = (select ResID from SequenceRestaurant where Address = '" + resAddr +"')~\"\"~\"\")");
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
				
				data = new SolveArrayList().ConvertFromArrayList(foodMenu);
				DefaultTableModel model = new DefaultTableModel(data, foodMenuColumns);
				menu.setModel(model);
				getPanel_1().setVisible(false);
			}
		});
		menuBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/food.png"));
		menuBtn.setBounds(12, 65, 172, 162);
		menuAndOrder.add(menuBtn);
		
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrderPanel().setVisible(true);
				getPanel_1().setVisible(false);
			}
		});
		orderBtn.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/request-128.png"));
		orderBtn.setBounds(216, 65, 172, 162);
		menuAndOrder.add(orderBtn);
		
		menuPanel = new HidePanel(menuAndOrder.getBorder(),accInfor.getAID(),clientProcess);
		restaurantPanel.add(menuPanel);
		menuPanel.setVisible(false);
		
		orderPanel = new HidePanel(menuAndOrder.getBorder(),accInfor.getAID(),clientProcess);
		restaurantPanel.add(orderPanel);
		orderPanel.setVisible(false);
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
}