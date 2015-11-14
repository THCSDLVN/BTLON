package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JTextField FullNameField;
	private JTextField PhoneNumField;
	private JPasswordField passwordField;
	private JPasswordField RetypasswordField;
	private JComboBox dayCbbox;
	private JComboBox MonthCbbox;
	private JComboBox YearCbbox;
	private JPanel signupPn;
	private JPanel successPn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icon_signup.png")));
		setTitle("Sign up");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((new Random()).nextInt(300)+100,(new Random()).nextInt(300), 398, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		signupPn = new JPanel();
		signupPn.setBackground(new Color(255, 255, 153));
		signupPn.setForeground(new Color(255, 255, 153));
		signupPn.setBounds(0, 0, 392, 432);
		contentPane.add(signupPn);
		signupPn.setLayout(null);
		
		JLabel Title = new JLabel("CREATE YOUR ACCOUNT");
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		Title.setForeground(new Color(51, 153, 102));
		Title.setFont(new Font("Lucida Console", Font.BOLD, 14));
		Title.setBounds(10, 21, 372, 51);
		signupPn.add(Title);
		
		JPanel InfoFillPn = new JPanel();
		InfoFillPn.setBackground(new Color(153, 204, 153));
		InfoFillPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 102)));
		InfoFillPn.setBounds(10, 91, 372, 248);
		signupPn.add(InfoFillPn);
		InfoFillPn.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(6, 16, 105, 20);
		InfoFillPn.add(lblUsername);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		UsernameField = new JTextField();
		UsernameField.setBounds(154, 16, 112, 20);
		InfoFillPn.add(UsernameField);
		UsernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(6, 57, 105, 20);
		InfoFillPn.add(lblPassword);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblRetypassword = new JLabel("Re-type password");
		lblRetypassword.setBounds(6, 98, 138, 20);
		InfoFillPn.add(lblRetypassword);
		lblRetypassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblFullName = new JLabel("FullName");
		lblFullName.setBounds(6, 139, 105, 20);
		InfoFillPn.add(lblFullName);
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		FullNameField = new JTextField();
		FullNameField.setBounds(154, 139, 195, 20);
		InfoFillPn.add(FullNameField);
		FullNameField.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Date of birth");
		lblBirthday.setBounds(6, 180, 105, 20);
		InfoFillPn.add(lblBirthday);
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNum = new JLabel("Phone number");
		lblPhoneNum.setBounds(6, 221, 105, 20);
		InfoFillPn.add(lblPhoneNum);
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		PhoneNumField = new JTextField();
		PhoneNumField.setBounds(154, 221, 195, 20);
		InfoFillPn.add(PhoneNumField);
		PhoneNumField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(154, 57, 112, 20);
		InfoFillPn.add(passwordField);
		
		RetypasswordField = new JPasswordField();
		RetypasswordField.setBounds(154, 98, 112, 20);
		InfoFillPn.add(RetypasswordField);
		
		dayCbbox = new JComboBox();
		dayCbbox.setModel(new DefaultComboBoxModel());
		for(int i=1; i<=31; i++) dayCbbox.addItem(i);
		dayCbbox.setBounds(154, 180, 46, 20);
		dayCbbox.setEditable(true);
		dayCbbox.setSelectedItem("Day");
		dayCbbox.setEditable(false);
		InfoFillPn.add(dayCbbox);
		
		MonthCbbox = new JComboBox();
		MonthCbbox.setModel(new DefaultComboBoxModel());
		for(int i=1; i<=12; i++) MonthCbbox.addItem(i);
		MonthCbbox.setBounds(216, 180, 60, 20);
		MonthCbbox.setEditable(true);
		MonthCbbox.setSelectedItem("Month");
		MonthCbbox.setEditable(false);
		InfoFillPn.add(MonthCbbox);
		
		YearCbbox = new JComboBox();
		YearCbbox.setMaximumRowCount(12);
		YearCbbox.setModel(new DefaultComboBoxModel());
		for(int i=1920; i<=2005; i++) YearCbbox.addItem(i);
		YearCbbox.setBounds(292, 180, 56, 20);
		YearCbbox.setEditable(true);
		YearCbbox.setSelectedItem("Year");
		YearCbbox.setEditable(false);
		InfoFillPn.add(YearCbbox);
		
		JLabel lblLine1 = new JLabel("-");
		lblLine1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLine1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLine1.setBounds(200, 180, 16, 19);
		InfoFillPn.add(lblLine1);
		
		JLabel lblLine2 = new JLabel("-");
		lblLine2.setHorizontalAlignment(SwingConstants.CENTER);
		lblLine2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLine2.setBounds(276, 180, 16, 20);
		InfoFillPn.add(lblLine2);
		
		JButton btnSignup = new JButton("Sign up");
		btnSignup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				signupClick();
			}
		});
		btnSignup.setIcon(new ImageIcon(this.getClass().getResource("/icon_signup.png")));
		btnSignup.setBounds(34, 364, 159, 46);
		signupPn.add(btnSignup);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		btnCancel.setBounds(198, 364, 159, 46);
		signupPn.add(btnCancel);
		
		successPn = new JPanel();
		successPn.setVisible(false);
		successPn.setBounds(0, 0, 392, 432);
		contentPane.add(successPn);
		successPn.setLayout(null);
		
		JLabel message = new JLabel("YOUR ACCOUNT HAS BEEN CREATED");
		message.setFont(new Font("Arial", Font.BOLD, 15));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(0, 115, 392, 111);
		successPn.add(message);
	
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {				
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Check-icon.png"));
		btnNewButton.setBounds(171, 237, 50, 50);
		successPn.add(btnNewButton);
		
		JLabel label = new JLabel("LOG IN YOUR ACCOUNT");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 51, 102));
		label.setFont(new Font("Magneto", Font.BOLD, 14));
		label.setBounds(10, 26, 264, 46);
		
	}
	
	private Boolean checkRetyPassword(JPasswordField typ,JPasswordField retyp)
	{
		String typPass = new String(typ.getPassword());
		String retypPass = new String(retyp.getPassword());
		if (retypPass.equals(typPass) && !retypPass.isEmpty())
			return true;
		else 
			return false;
	}
	
	private Boolean checkPhoneNumber(JTextField PhoneField)
	{
		String phonenum = new String(PhoneField.getText());
		if(phonenum.matches("[0-9]+") || phonenum.isEmpty())
			return true;
		else
			return false;
	}
	
	private void signupClick()
	{
		try
		{
			if(UsernameField.getText().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Fill your Username");
				return;
			}
			if(passwordField.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(null, "Fill your Password");
				return;
			}
			if(!checkRetyPassword(passwordField, RetypasswordField))
			{
				JOptionPane.showMessageDialog(null, "The Retype-Password is incorrect");
				return;
			}
			if(!checkPhoneNumber(PhoneNumField))
			{
				JOptionPane.showMessageDialog(null, "Your PhoneNumber must be digits");
				return;
			}
			
			signupPn.setVisible(false);
			successPn.setVisible(true);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
	
	public JComboBox getDayCbbox() {
		return dayCbbox;
	}
	public JComboBox getMonthCbbox()
	{
		return MonthCbbox;
	}
	public JComboBox getYearCbbox() {
		return YearCbbox;
	}
	public JTextField getUsernameField() {
		return UsernameField;
	}
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	public JPasswordField getRetypasswordField() {
		return RetypasswordField;
	}
	public JTextField getFullNameField() {
		return FullNameField;
	}
	public JTextField getPhoneNumField() {
		return PhoneNumField;
	}
	public JPanel getSignupPn() {
		return signupPn;
	}
	public JPanel getSuccessPn() {
		return successPn;
	}
}
