import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class SignUpGUI extends JFrame {

	private JPanel ContentPane;
	private JTextField nameTextFld;
	private JLabel usrLbl_1;
	private JTextField usrTextFld;
	private JLabel pwLbl;
	private JPasswordField pwPassFld;
	private JPasswordField rePwPassfld;
	private JLabel phoneLbl;
	private JTextField phoneTextFld;
	private JLabel faceLbl;
	private JTextField faceTextFld;
	private JButton btnCancel;
	private Account a = new Account();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpGUI frame = new SignUpGUI();
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
	public SignUpGUI() {
		super("Sign Up");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		setSize(500, 600);
		
		JLabel signUpIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/AppName.png")));
		signUpIconLbl.setBounds(51, 0, 339, 90);
		ContentPane.add(signUpIconLbl);
		
		JLabel nameLbl = new JLabel("Your Name");
		nameLbl.setBounds(51, 120, 103, 15);
		ContentPane.add(nameLbl);
		
		nameTextFld = new JTextField();
		nameTextFld.setBounds(212, 114, 232, 27);
		ContentPane.add(nameTextFld);
		nameTextFld.setColumns(10);
		
		usrLbl_1 = new JLabel("Username");
		usrLbl_1.setBounds(51, 188, 103, 15);
		ContentPane.add(usrLbl_1);
		
		usrTextFld = new JTextField();
		usrTextFld.setColumns(10);
		usrTextFld.setBounds(212, 182, 232, 27);
		ContentPane.add(usrTextFld);
		
		pwLbl = new JLabel("Password");
		pwLbl.setBounds(51, 253, 103, 15);
		ContentPane.add(pwLbl);
		
		pwPassFld = new JPasswordField();
		pwPassFld.setBounds(212, 247, 232, 27);
		ContentPane.add(pwPassFld);
		
		JLabel rePwLbl = new JLabel("Re-type Password");
		rePwLbl.setBounds(51, 314, 143, 15);
		ContentPane.add(rePwLbl);
		
		rePwPassfld = new JPasswordField();
		rePwPassfld.setBounds(212, 308, 232, 27);
		ContentPane.add(rePwPassfld);
		
		phoneLbl = new JLabel("Phone Number");
		phoneLbl.setBounds(51, 384, 143, 15);
		ContentPane.add(phoneLbl);
		
		phoneTextFld = new JTextField();
		phoneTextFld.setColumns(10);
		phoneTextFld.setBounds(212, 372, 232, 27);
		ContentPane.add(phoneTextFld);
		
		faceLbl = new JLabel("Facebook");
		faceLbl.setBounds(51, 451, 143, 15);
		ContentPane.add(faceLbl);
		
		faceTextFld = new JTextField();
		faceTextFld.setColumns(10);
		faceTextFld.setBounds(212, 445, 232, 27);
		ContentPane.add(faceTextFld);
		
		JButton signUpBtn = new JButton("Sign Up");
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setFullName(nameTextFld.getText());
				a.setUsername(usrTextFld.getText());
				a.setPassword(new String(pwPassFld.getPassword()));
				a.setPhoneNumber(phoneTextFld.getText());
				a.setFacebook(faceTextFld.getText());
				//String query = "insertDataQuery(Account~"+a.getID()+"~"+a.getFullName())";
				int result =0;
				if(result == 0){
					//Co loi sign up
				}
				else{
					// Thong bao dang ki thanh cong.
					// Quay tro ve giao dien LogIn.
					// sex.clearSelection.
				}
			}
		});
		signUpBtn.setBounds(356, 506, 88, 33);
		ContentPane.add(signUpBtn);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new LogInGUI().setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(212, 506, 94, 33);
		ContentPane.add(btnCancel);
		
		JRadioButton maleRdbtn = new JRadioButton("Male");
		maleRdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setSex(true);
			}
		});
		maleRdbtn.setBounds(212, 149, 69, 23);
		ContentPane.add(maleRdbtn);
		
		JRadioButton femaleRdbtn = new JRadioButton("Female");
		femaleRdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setSex(false);
			}
		});
		femaleRdbtn.setBounds(318, 149, 103, 23);
		ContentPane.add(femaleRdbtn);

		ButtonGroup sex = new ButtonGroup();
		sex.add(femaleRdbtn);
		sex.add(maleRdbtn);
	}
}
