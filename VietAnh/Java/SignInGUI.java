import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

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
import java.util.Map;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
public class SignInGUI extends JFrame {
	private JPanel contentPane;
	private JTextField usrTextfld;
	private JPasswordField passwordField;
	//static String[] resList = new String[] {"VietAnh", "asfasf", "asfasf", "afsfsaf", "fsaasfs", "fsdsdgsd", "werwefsdg", "afsetgretweg", "asfwegsdbs", "wetwgsdgg", "wefsdgreyergds", "asfsegf", "asdksanfks", "asfnweghaiowejgv;lz", "alfhiwleuyglbmsldf", "wlehilwegerkgfdb"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						SignInGUI frame = new SignInGUI();
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
	public SignInGUI() {
		super("Log In");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setForeground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel appNameIcon = new JLabel(new ImageIcon(this.getClass().getResource("/Picture/AppName.png")));
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
				String usr = usrTextfld.getText();
				String pass = new String(passwordField.getPassword());
				String query = "dataQuery(Account~*~UserName = '" + usr + "' and PassWord = '" + pass + "'~\"\"~\"\"~\"\"~\"\")";
				Account a = new Account();
				SQLConnection c = new SQLConnection();
				List<List<String>> accList = c.getAcc(usr, pass);
				String[][] x = SolveArrayList.ConvertFromArrayList(accList);
				if(x == null){
					JOptionPane.showMessageDialog(null, "Your username or password is not correct");
				}
				else{
					a.setAID(x[0][0]);
					a.setUsername(x[0][1]);
					a.setPassword(x[0][2]);
					a.setFullname(x[0][3]);
					a.setBirthday(x[0][4]);
					a.setSex(x[0][5]);
					a.setPhonenumber(x[0][6]);
					new AccountGUI(a).setVisible(true);
					//dispose();
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
		Font font = lblSignUp.getFont();
		lblSignUp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				Map attributes = font.getAttributes();
				attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				lblSignUp.setFont(font.deriveFont(attributes));
			}
		});
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//new SignUpGUI().setVisible(true);
				//dispose();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblSignUp.setFont(font);
			}
		});
		lblSignUp.setBounds(315, 250, 70, 15);
		contentPane.add(lblSignUp);
	}
}
