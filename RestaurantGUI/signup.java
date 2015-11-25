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
import java.sql.Statement;

import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import gui.SQLConnection;
import gui.ProvideAIDRandom;

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
	private JComboBox SexcomboBox;

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

	
	private String maxAID_query = new String("SELECT AID FROM account ORDER BY AID DESC LIMIT 1");
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
		Title.setBounds(10, 0, 372, 51);
		signupPn.add(Title);
		
		JPanel InfoFillPn = new JPanel();
		InfoFillPn.setBackground(new Color(153, 204, 153));
		InfoFillPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 102)));
		InfoFillPn.setBounds(10, 51, 372, 302);
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
		
		JLabel lblFullName = new JLabel("Full Name");
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
		lblPhoneNum.setBounds(6, 262, 105, 20);
		InfoFillPn.add(lblPhoneNum);
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		PhoneNumField = new JTextField();
		PhoneNumField.setBounds(154, 262, 195, 20);
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
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSex.setBounds(6, 221, 86, 20);
		InfoFillPn.add(lblSex);
		
		SexcomboBox = new JComboBox();
		SexcomboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		SexcomboBox.setBounds(154, 221, 80, 20);
		InfoFillPn.add(SexcomboBox);
		
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
		if(phonenum.matches("[0-9]+"))
			return true;
		else
			return false;
	}
	
	private Boolean check_date(JComboBox day,JComboBox month,JComboBox year)
	{
		int[] monthdays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int iday = day.getSelectedIndex()+1;
		int imonth = month.getSelectedIndex();
		int iyear = year.getSelectedIndex()+1920;
		monthdays[1] = (iyear%4 == 0)? 29 : 28;
		if(iday<=monthdays[imonth])
			return true;
		else 
			return false;
	}
	
	private Boolean check_text(String text)
	{
		if (text.matches("[A-Za-z0-9]+"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void signupClick()
	{
		try
		{
			String aid,usrname,password,fullname,dateofbirth,sex,phonenum;
			if(!check_text(UsernameField.getText()))
			{
				JOptionPane.showMessageDialog(null, "Username must be letters or digits");
				return;
			}
			if(!check_text(new String(passwordField.getPassword())))
			{
				JOptionPane.showMessageDialog(null, "Password must be letters or digits");
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
			if(dayCbbox.getSelectedIndex() < 0 ||MonthCbbox.getSelectedIndex() < 0 || YearCbbox.getSelectedIndex() < 0)
			{
				JOptionPane.showMessageDialog(null,"Fill your date of birth");
				return;
			}
			if(!check_date(dayCbbox, MonthCbbox, YearCbbox))
			{
				JOptionPane.showMessageDialog(null,"Date of birth is not exist");
				return;
			}
			
			usrname = new String(UsernameField.getText());
			password = new String(passwordField.getPassword());
			fullname = new String(FullNameField.getText());
			dateofbirth = new String(YearCbbox.getSelectedItem() + "-" + MonthCbbox.getSelectedItem() + "-" + dayCbbox.getSelectedItem());
			sex = new String((String)SexcomboBox.getSelectedItem());
			phonenum = new String(PhoneNumField.getText());
			
			Statement stmt = SQLConnection.conn.createStatement();
			SqlArrayList rs = new SqlArrayList(stmt.executeQuery(maxAID_query));
			if(rs.getRownumber() <= 0)
			{
				aid = new String("ACCT0000");
			}
			else
			{
				String maxaid = rs.getRow(0)[0];
				aid = new String((new ProvideAIDRandom()).ProvideAID(maxaid));
			}
			stmt.executeUpdate("INSERT INTO account VALUES ('"+ aid +"','" + usrname + "','" + password + "','" + fullname + "','" + dateofbirth + "','" + phonenum + "','" + sex +"','" + "1" +"');");
			stmt.close();		
			AccountGUI accgui = new AccountGUI();
			accgui.setVisible(true);
			JOptionPane.showMessageDialog(null, "Your account has been created");
			
			UsernameField.setText("");
			passwordField.setText("");
			RetypasswordField.setText("");
			FullNameField.setText("");
			dayCbbox.setEditable(true);
			MonthCbbox.setEditable(true);
			YearCbbox.setEditable(true);
			dayCbbox.setSelectedItem("Day");
			MonthCbbox.setSelectedItem("Month");
			YearCbbox.setSelectedItem("Year");
			dayCbbox.setEditable(false);
			MonthCbbox.setEditable(false);
			YearCbbox.setEditable(false);
			SexcomboBox.setSelectedIndex(0);
			PhoneNumField.setText("");
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
	}
}
