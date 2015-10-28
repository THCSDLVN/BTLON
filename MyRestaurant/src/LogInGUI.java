import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class LogInGUI extends JFrame {
	private JPanel contentPane;
	private JTextField usrTextfld;
	private JPasswordField passwordField;
	static String[] resList = new String[] {"VietAnh", "asfasf", "asfasf", "afsfsaf", "fsaasfs", "fsdsdgsd", "werwefsdg", "afsetgretweg", "asfwegsdbs", "wetwgsdgg", "wefsdgreyergds", "asfsegf", "asdksanfks", "asfnweghaiowejgv;lz", "alfhiwleuyglbmsldf", "wlehilwegerkgfdb"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						LogInGUI frame = new LogInGUI();
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
	public LogInGUI() {
		super("Log In");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel appNameIcon = new JLabel(new ImageIcon(this.getClass().getResource("/Resource/AppName.png")));
		appNameIcon.setBounds(79, 0, 300, 68);
		contentPane.add(appNameIcon);
		JLabel usrLbl = new JLabel("Username");
		usrLbl.setBounds(79, 101, 84, 28);
		contentPane.add(usrLbl);
		usrTextfld = new JTextField();
		usrTextfld.setBounds(180, 101, 205, 28);
		contentPane.add(usrTextfld);
		usrTextfld.setColumns(10);
		JLabel pwLbl = new JLabel("Password");
		pwLbl.setBounds(79, 147, 70, 15);
		contentPane.add(pwLbl);
		passwordField = new JPasswordField();
		passwordField.setBounds(180, 141, 205, 28);
		contentPane.add(passwordField);
		JButton signInBtn = new JButton("Sign In");
		signInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signInBtn.setEnabled(false);
				String usr = usrTextfld.getText();
				String pass = new String(passwordField.getPassword());
				String query = "dataQuery(Account~*~UserName = '" + usr + "' and PassWord = '" + pass + "'~\"\"~\"\"~\"\"~\"\")";
				//Select * from Account where UserName = usr and PassWord = pass
				List<List<String>> accList = null;//List tra ve.
				SQLConnection c = new SQLConnection();
				c.logIn(usr,pass);
				accList = c.resultList;
				if(accList.size() == 0){
					JOptionPane.showMessageDialog(null, "Your username or password is not correct");
					signInBtn.setEnabled(true);
				}
				else{
					String[] acc = new String[7];
					int index = 0;
					for(List<String> innerLs : accList) {
						for (Iterator<String> i = innerLs.iterator(); i.hasNext();) {
							acc[index++] = i.next();
						}
					}
					AccountGUI a = new AccountGUI(acc);
					a.setVisible(true);
					dispose();
				}
			}	
		});
		signInBtn.setBounds(303, 181, 82, 38);
		contentPane.add(signInBtn);
		JButton exitBtn = new JButton("Exit");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExitConfirm a = new ExitConfirm();
				a.setVisible(true);
			}
		});
		exitBtn.setBounds(180, 181, 90, 38);
		contentPane.add(exitBtn);
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new SignUpGUI().setVisible(true);
				//dispose();
			}
		});
		lblSignUp.setBounds(320, 254, 70, 15);
		contentPane.add(lblSignUp);
	}
}
