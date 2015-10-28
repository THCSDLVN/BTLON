import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
	private JPanel panel;

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/Hungres/icon_signup.png")));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ContentPane = new JPanel();
		ContentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ContentPane);
		ContentPane.setLayout(null);
		setSize(500, 600);
		
		panel = new JPanel();
		panel.setBounds(51, 0, 393, 539);
		ContentPane.add(panel);
		panel.setLayout(null);
		
		JLabel signUpIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/AppName.png")));
		signUpIconLbl.setHorizontalAlignment(SwingConstants.CENTER);
		signUpIconLbl.setBounds(-45, 0, 480, 90);
		panel.add(signUpIconLbl);
		
		JLabel nameLbl = new JLabel("Your Name");
		nameLbl.setBounds(0, 114, 103, 15);
		panel.add(nameLbl);
		
		nameTextFld = new JTextField();
		nameTextFld.setBounds(161, 114, 232, 27);
		panel.add(nameTextFld);
		nameTextFld.setColumns(10);
		
		usrLbl_1 = new JLabel("Username");
		usrLbl_1.setBounds(0, 182, 103, 15);
		panel.add(usrLbl_1);
		
		usrTextFld = new JTextField();
		usrTextFld.setBounds(161, 182, 232, 27);
		panel.add(usrTextFld);
		usrTextFld.setColumns(10);
		
		pwLbl = new JLabel("Password");
		pwLbl.setBounds(0, 250, 103, 15);
		panel.add(pwLbl);
		
		pwPassFld = new JPasswordField();
		pwPassFld.setBounds(161, 250, 232, 27);
		panel.add(pwPassFld);
		
		JLabel rePwLbl = new JLabel("Re-type Password");
		rePwLbl.setBounds(0, 318, 143, 15);
		panel.add(rePwLbl);
		
		rePwPassfld = new JPasswordField();
		rePwPassfld.setBounds(161, 318, 232, 27);
		panel.add(rePwPassfld);
		
		phoneLbl = new JLabel("Phone Number");
		phoneLbl.setBounds(0, 386, 143, 15);
		panel.add(phoneLbl);
		
		phoneTextFld = new JTextField();
		phoneTextFld.setBounds(161, 386, 232, 27);
		panel.add(phoneTextFld);
		phoneTextFld.setColumns(10);
		
		faceLbl = new JLabel("Facebook");
		faceLbl.setBounds(0, 454, 143, 15);
		panel.add(faceLbl);
		
		faceTextFld = new JTextField();
		faceTextFld.setBounds(161, 454, 232, 27);
		panel.add(faceTextFld);
		faceTextFld.setColumns(10);
		
		JButton signUpBtn = new JButton("Sign Up");
		signUpBtn.setBounds(261, 496, 132, 43);
		panel.add(signUpBtn);
		signUpBtn.setIcon(new ImageIcon(this.getClass().getResource("/Hungres/icon_signup.png")));
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(0, 496, 132, 43);
		panel.add(btnCancel);
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/Hungres/cancel.png")));
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new LogInGUI().setVisible(true);
				dispose();
			}
		});
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setFullName(nameTextFld.getText());
				a.setUsername(usrTextFld.getText());
				a.setPassword(new String(pwPassFld.getPassword()));
				a.setPhoneNumber(phoneTextFld.getText());
				a.setFacebook(faceTextFld.getText());
				if(a.getUsername().equals("abc"))
					ContentPane.getComponents()[1].setVisible(true);
				else
					ContentPane.getComponents()[1].setVisible(false);
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

		ButtonGroup sex = new ButtonGroup();
		JRadioButton maleRdbtn = new JRadioButton("Male");
		maleRdbtn.setBounds(161, 149, 69, 23);
		panel.add(maleRdbtn);
		maleRdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setSex(true);
			}
		});
		sex.add(maleRdbtn);
		
		JRadioButton femaleRdbtn = new JRadioButton("Female");
		femaleRdbtn.setBounds(267, 149, 103, 23);
		panel.add(femaleRdbtn);
		femaleRdbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.setSex(false);
			}
		});
		sex.add(femaleRdbtn);
		
		JPanel checkUsrname = new JPanel();
		checkUsrname.setBounds(456, 188, 10, 10);
		checkUsrname.setBackground(Color.RED);
		checkUsrname.setVisible(false);
		if(usrTextFld.getText().equals("avc"))
			ContentPane.getComponents()[1].setVisible(true);
		ContentPane.add(checkUsrname);
	}
}
