import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AccountGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountGUI frame = new AccountGUI();
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
	public AccountGUI(/*Account a*/) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		JLabel userAvaLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/male.png")));
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
		
		JLabel fullnameLbl = new JLabel("Nguyen Viet Anh");
		fullnameLbl.setBounds(209, 12, 195, 38);
		panel.add(fullnameLbl);
		
		JLabel label = new JLabel("0946546530");
		label.setBounds(207, 62, 195, 38);
		panel.add(label);
		
		JLabel label_1 = new JLabel("26/09/1995");
		label_1.setBounds(207, 112, 195, 38);
		panel.add(label_1);
		
		JButton btnFeature = new JButton("Feature");
		btnFeature.setBounds(311, 559, 117, 25);
		userPanel.add(btnFeature);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, UIManager.getColor("Button.darkShadow"), null));
		scrollPane.setBounds(12, 359, 416, 188);
		userPanel.add(scrollPane);
		
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Restaurant", "Address", "Like"
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
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(true);
		table.getColumnModel().getColumn(0).setResizable(true);

		
		JLabel Restaurants = new JLabel("Restaurants",JLabel.CENTER);
		Restaurants.setIcon(new ImageIcon(this.getClass().getResource("/Picture/menu.png")));
		Restaurants.setFont(new Font("", Font.ITALIC, 14));
		Restaurants.setBounds(12, 306, 416, 41);
		userPanel.add(Restaurants);
		
		JPanel restaurantPanel = new JPanel();
		restaurantPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 204, 204), null));
		restaurantPanel.setBounds(464, 12, 424, 596);
		contentPane.add(restaurantPanel);
	}
}
