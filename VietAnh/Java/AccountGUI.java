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

public class AccountGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public SQLConnection c = new SQLConnection();
	public int like = 0;
	private JPanel menuAndOrder;
	public int row,column;
	public String resName = "",resAddr = "",resNumber = "";
	private JPanel resInfo;
	private JLabel resNameLbl;
	private JLabel ResAddressLbl;
	private HidePanel menuPanel,orderPanel;
	private JLabel resPhoneLbl_1;
	public String[] foodMenuColumns = {"Food","Price"}; 
	public String[][] data;
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
		userAvaLbl.setBounds(12, 12, 128, 176);
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		scrollPane.setBounds(12, 359, 416, 188);
		userPanel.add(scrollPane);
		
		table = new JTable();
		String[] columnNames = {"Restaurant","Address","Like"};
		List<List<String>> resList = new ArrayList<>();
		resList = c.getRes();
		String[][] x = SolveArrayList.ConvertFromArrayList(resList);
		DefaultTableModel model = new DefaultTableModel(x, columnNames);
		table.setModel(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(184);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		scrollPane.setViewportView(table);

		JButton btnFeature = new JButton("Feature");
		btnFeature.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				column = table.getSelectedColumn();
				row = table.getSelectedRow();
				if(row < 0)
					return;
				resName = (String)model.getValueAt(row, 0);
				resAddr = (String)model.getValueAt(row, 1);
				List<List<String>> resNumber = c.getResInfo(resAddr);
				getResNameLbl_1().setText(resName);
				getResAddressLbl().setText(resAddr);
				getResPhoneLbl().setText(resNumber.get(0).get(0));
				JTable menu = getMenuPanel().getMenu();
				List<List<String>> foodMenu = c.getFoodFromRes(resAddr);
				data = new SolveArrayList().ConvertFromArrayList(foodMenu);
				DefaultTableModel model = new DefaultTableModel(data, foodMenuColumns);
				menu.setModel(model);
			}
		});
		btnFeature.setBounds(311, 559, 117, 25);
		userPanel.add(btnFeature);
		
		JLabel Restaurants = new JLabel("Restaurants",JLabel.CENTER);
		Restaurants.setIcon(new ImageIcon(this.getClass().getResource("/Picture/menu.png")));
		Restaurants.setFont(new Font("", Font.ITALIC, 14));
		Restaurants.setBounds(12, 306, 416, 41);
		userPanel.add(Restaurants);
		
		JPanel restaurantPanel = new JPanel();
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
		
		JLabel likeAvaLbl = new JLabel(new ImageIcon(AccountGUI.class.getResource("/Picture/red_heart.png")));
		likeAvaLbl.setBounds(175, 162, 43, 38);
		resInfo.add(likeAvaLbl);
		
		JLabel resLikeLbl = new JLabel((String) null);
		resLikeLbl.setBounds(219, 162, 169, 38);
		resInfo.add(resLikeLbl);
		
		JButton likeBtn = new JButton("");
		likeBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/flying_heart.png")));
		likeBtn.setBounds(12, 232, 55, 56);
		restaurantPanel.add(likeBtn);
		
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPanel_1().setVisible(true);
				if(getMenuPanel().isVisible())
					getMenuPanel().setVisible(false);
				if(getOrderPanel().isVisible())
					getOrderPanel().setVisible(false);
			}
		});
		backBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/007355-blue-jelly-icon-arrows-arrow-styled-left.png")));
		backBtn.setBounds(357, 232, 55, 56);
		restaurantPanel.add(backBtn);
		
		JButton refreshBtn = new JButton("");
		refreshBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/refresh.png")));
		refreshBtn.setBounds(290, 232, 55, 56);
		restaurantPanel.add(refreshBtn);
		
		menuAndOrder = new JPanel();
		menuAndOrder.setBounds(12, 300, 400, 284);
		menuAndOrder.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		restaurantPanel.add(menuAndOrder);
		menuAndOrder.setLayout(null);
		
		JButton menuBtn = new JButton();
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getMenuPanel().setVisible(true);
				JTable menu = getMenuPanel().getMenu();
				List<List<String>> foodMenu = c.getFoodFromRes(resAddr);
				data = new SolveArrayList().ConvertFromArrayList(foodMenu);
				DefaultTableModel model = new DefaultTableModel(data, foodMenuColumns);
				menu.setModel(model);
				getPanel_1().setVisible(false);
			}
		});
		menuBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/food.png")));
		menuBtn.setBounds(12, 65, 172, 162);
		menuAndOrder.add(menuBtn);
		
		JButton orderBtn = new JButton();
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getOrderPanel().setVisible(true);
				getPanel_1().setVisible(false);
			}
		});
		orderBtn.setIcon(new ImageIcon(AccountGUI.class.getResource("/Picture/request-128.png")));
		orderBtn.setBounds(216, 65, 172, 162);
		menuAndOrder.add(orderBtn);
		
		menuPanel = new HidePanel(menuAndOrder.getBorder());
		restaurantPanel.add(menuPanel);
		menuPanel.setVisible(false);
		
		orderPanel = new HidePanel(menuAndOrder.getBorder());
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
		return ResAddressLbl;
	}
	public HidePanel getMenuPanel() {
		return menuPanel;
	}
	public JLabel getResPhoneLbl() {
		return resPhoneLbl_1;
	}
	public HidePanel getOrderPanel(){
		return orderPanel;
	}
}
