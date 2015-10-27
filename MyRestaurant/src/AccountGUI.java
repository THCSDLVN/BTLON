import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.Iterator;
import java.util.List;

public class AccountGUI extends JFrame {

	private JPanel contentPane;
	static String[] resList = new String[] {"VietAnh", "asfasf", "asfasf", "afsfsaf", "fsaasfs", "fsdsdgsd", "werwefsdg", "afsetgretweg", "asfwegsdbs", "wetwgsdgg", "wefsdgreyergds", "asfsegf", "asdksanfks", "asfnweghaiowejgv;lz", "alfhiwleuyglbmsldf", "wlehilwegerkgfdb"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String[] acc = new String[100];
					AccountGUI frame = new AccountGUI(acc);
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
	public AccountGUI(String Acc[]) {
		super("My Restaurants");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setPreferredSize(new Dimension(470,530));
		pack();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel nameLbl = new JLabel(Acc[1]);
		nameLbl.setBounds(244, 72, 194, 22);
		contentPane.add(nameLbl);
		
		JLabel phoneLbl = new JLabel(Acc[4]);
		phoneLbl.setBounds(244, 106, 194, 22);
		contentPane.add(phoneLbl);
		
		JLabel faceLbl = new JLabel(Acc[5]);
		faceLbl.setBounds(244, 140, 194, 22);
		contentPane.add(faceLbl);
		
		JLabel avatarLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Resource/female.png")));
		avatarLbl.setBounds(45, 15, 168, 172);
		contentPane.add(avatarLbl);
		
		
		String query = "dataQuery(Restaurant~RestaurantName~\"\"~\"\"~\"\"~\"\"~\"\"~\"\")";
		SQLConnection c = new SQLConnection();
		c.getRes();
		List<List<String>> resNameList = c.resultList;// List tra ve.
		String res[] = new String[resNameList.size()];
		int index = 0;
		for(List<String> innerLs : resNameList) {
			for (Iterator<String> i = innerLs.iterator(); i.hasNext();) {
				res[index++] = i.next();
			}
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 232, 401, 239);
		contentPane.add(scrollPane);
		JList restaurantList = new JList();
		scrollPane.setViewportView(restaurantList);
		AbstractListModel<String> model = new AbstractListModel<String>() {
			
			@Override
			public int getSize() {
				return res.length;
			}
			
			@Override
			public String getElementAt(int index) {
				return res[index];
			}
		};
		restaurantList.setModel(model);
		
		JButton logOutBtn = new JButton("New button");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogInGUI().setVisible(true);
			}
		});
		logOutBtn.setBounds(387, 15, 51, 45);
		contentPane.add(logOutBtn);
		
		JLabel resListLbl = new JLabel("Restaurants List",JLabel.CENTER);
		resListLbl.setBounds(37, 205, 401, 15);
		contentPane.add(resListLbl);
		
		JButton refreshBtn = new JButton("New button");
		refreshBtn.setBounds(308, 15, 51, 45);
		contentPane.add(refreshBtn);
		
		JButton featureBtn = new JButton("Feature");
		featureBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = restaurantList.getSelectedIndex();
				String selectRestaurant = model.getElementAt(index);
				new SelectedRestaurantGUI(selectRestaurant).setVisible(true);
			}
		});
		featureBtn.setBounds(348, 480, 90, 25);
		contentPane.add(featureBtn);
		
	
			
	}
}
