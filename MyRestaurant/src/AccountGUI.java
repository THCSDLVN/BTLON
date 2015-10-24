import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
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
					//String[] acc = new String[100];
					//AccountGUI frame = new AccountGUI(acc,resList);
					//frame.setVisible(true);
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
		setPreferredSize(new Dimension(470,500));
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
		
		JButton featureBtn = new JButton("Feature");
		featureBtn.setBounds(348, 447, 90, 25);
		contentPane.add(featureBtn);
		
		JLabel avatarLbl = new JLabel(new ImageIcon(this.getClass().getResource("/female.png")));
		avatarLbl.setBounds(37, 12, 168, 172);
		contentPane.add(avatarLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 196, 401, 239);
		contentPane.add(scrollPane);
		String query = "dataQuery(Restaurant~RestaurantName~\"\"~\"\"~\"\"~\"\"~\"\"~\"\")";
		List<List<String>> resNameList = null;// List tra ve.
		int resNameListSize = 0;
		for(List<String> sublist : resNameList){
			resNameListSize += sublist.size();
		}
		String Res[] = new String[resNameListSize];
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return Res.length;
			}
			public Object getElementAt(int index) {
				return Res[index];
			}
		});
		
		JButton logOutBtn = new JButton("New button");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LogInGUI().setVisible(true);
			}
		});
		logOutBtn.setBounds(387, 15, 51, 45);
		contentPane.add(logOutBtn);
		
	
			
	}
}
