package Client.signup;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import java.util.List;
import java.util.ArrayList;

import java.nio.charset.Charset;
import java.lang.reflect.Field;

import Client.provideIDRandom.ProvideIDRandom;
import Client.clientprocess.ClientProcess;
import Client.accountinfor.AccountInfor;
import Client.accountGUI.AccountGUI;

public class Signup extends JFrame {

	public JPanel contentPane = new JPanel();
	public JPanel infoFillPn = new JPanel();
	public JPanel signupPn = new JPanel();

	public JTextField usernameField = new JTextField();
	public JTextField fullNameField = new JTextField();
	public JTextField phoneNumField = new JTextField();
	
	public JPasswordField passwordField = new JPasswordField();
	public JPasswordField retypasswordField = new JPasswordField();
	
	public JComboBox dayCbbox = new JComboBox();
	public JComboBox monthCbbox = new JComboBox();
	public JComboBox yearCbbox = new JComboBox();
	public JComboBox sexcomboBox = new JComboBox();

	public JLabel lblUsername = new JLabel("Username");
	public JLabel lblPassword = new JLabel("Password");
	public JLabel lblRetypassword = new JLabel("Retype Password");
	public JLabel lblFullName = new JLabel("Full Name");
	public JLabel lblBirthday = new JLabel("Date of birth");
	public JLabel lblPhoneNum = new JLabel("Phone number");
	public JLabel title = new JLabel("CREATE YOUR ACCOUNT");
	public JLabel lblSex = new JLabel("Sex");
	public JLabel label = new JLabel("LOG IN YOUR ACCOUNT");
	
	public JButton btnSignup = new JButton("Sign up");
	public JButton btnCancel = new JButton("Cancel");

	public ProvideIDRandom pAIDRan = new  ProvideIDRandom();
	public ClientProcess clientProcess = new ClientProcess();

	public Signup(ClientProcess cP) {
		setTitle("Sign up");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setPreferredSize(new Dimension(398, 460));
		pack();

		clientProcess = cP;
		
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		signupPn.setBackground(new Color(255, 255, 153));
		signupPn.setForeground(new Color(255, 255, 153));
		signupPn.setBounds(0, 0, 392, 432);
		contentPane.add(signupPn);
		signupPn.setLayout(null);
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(new Color(51, 153, 102));
		title.setFont(new Font("Lucida Console", Font.BOLD, 14));
		title.setBounds(10, 0, 372, 51);
		
		signupPn.add(title);
		signupPn.add(infoFillPn);
		signupPn.add(btnSignup);
		signupPn.add(btnCancel);

		infoFillPn.add(lblUsername);
		infoFillPn.add(usernameField);
		infoFillPn.add(lblPassword);
		infoFillPn.add(lblRetypassword);
		infoFillPn.add(lblFullName);
		infoFillPn.add(fullNameField);
		infoFillPn.add(lblBirthday);
		infoFillPn.add(lblPhoneNum);
		infoFillPn.add(phoneNumField);
		infoFillPn.add(passwordField);
		infoFillPn.add(retypasswordField);
		infoFillPn.add(dayCbbox);
		infoFillPn.add(monthCbbox);
		infoFillPn.add(yearCbbox);
		infoFillPn.add(lblSex);
		infoFillPn.add(sexcomboBox);	

		infoFillPn.setBackground(new Color(153, 204, 153));
		infoFillPn.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 153, 51), new Color(0, 153, 102)));
		infoFillPn.setBounds(10, 51, 372, 302);
		infoFillPn.setLayout(null);
		
		lblUsername.setBounds(6, 16, 105, 20);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		usernameField.setBounds(154, 16, 112, 20);
		usernameField.setColumns(10);
		
		lblPassword.setBounds(6, 57, 105, 20);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblRetypassword.setBounds(6, 98, 150, 20);
		lblRetypassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblFullName.setBounds(6, 139, 105, 20);
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		fullNameField.setBounds(154, 139, 195, 20);
		fullNameField.setColumns(10);
		
		lblBirthday.setBounds(6, 180, 105, 20);
		lblBirthday.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblPhoneNum.setBounds(6, 262, 120, 20);
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		phoneNumField.setBounds(154, 262, 195, 20);
		phoneNumField.setColumns(10);
		
		passwordField.setBounds(154, 57, 112, 20);
		
		retypasswordField.setBounds(154, 98, 112, 20);
		
		dayCbbox.setModel(new DefaultComboBoxModel());
		for(int i = 1; i <= 31; i++){
			dayCbbox.addItem(i);
		}
		dayCbbox.setBounds(154, 180, 58, 20);
		dayCbbox.setEditable(true);
		dayCbbox.setSelectedItem("Day");
		dayCbbox.setEditable(false);
		
		monthCbbox.setModel(new DefaultComboBoxModel());
		for(int i = 1; i <= 12; i++) {
			monthCbbox.addItem(i);
		}
		monthCbbox.setBounds(216, 180, 70, 20);
		monthCbbox.setEditable(true);
		monthCbbox.setSelectedItem("Month");
		monthCbbox.setEditable(false);
		
		yearCbbox.setMaximumRowCount(12);
		yearCbbox.setModel(new DefaultComboBoxModel());
		for(int i = 1920; i <= 2005; i++){
			yearCbbox.addItem(i);
		}
		yearCbbox.setBounds(292, 180, 70, 20);
		yearCbbox.setEditable(true);
		yearCbbox.setSelectedItem("Year");
		yearCbbox.setEditable(false);
		
		lblSex.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSex.setBounds(6, 221, 86, 20);
		
		sexcomboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		sexcomboBox.setBounds(154, 221, 80, 20);

		btnSignup.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				signupClick();
			}
		});

		btnSignup.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/icon_signup.png"));
		btnSignup.setBounds(34, 364, 159, 46);
		
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				dispose();
			}
		});

		btnCancel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/cancel.png"));
		btnCancel.setBounds(198, 364, 159, 46);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 51, 102));
		label.setFont(new Font("Magneto", Font.BOLD, 14));
		label.setBounds(10, 26, 264, 46);	
	}

	public Boolean checkRetyPassword(JPasswordField typ,JPasswordField retyp){
		String typPass = new String(typ.getPassword());
		String retypPass = new String(retyp.getPassword());
		if (retypPass.equals(typPass) && !retypPass.isEmpty()){
			return true;
		}
		else{ 
			return false;
		}
	}
	
	public Boolean checkPhoneNumber(JTextField PhoneField){
		String phonenum = new String(PhoneField.getText());
		if(phonenum.matches("[0-9]+") || phonenum.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void signupClick(){
		if(clientProcess.lock == 0){
			try{
				String aid,usrname,password,fullname,dateofbirth,sex,phonenum;
				if(usernameField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Fill Your Username","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(passwordField.getPassword().length == 0){
					JOptionPane.showMessageDialog(null, "Fill Your Password","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!checkRetyPassword(passwordField, retypasswordField)){
					JOptionPane.showMessageDialog(null, "The Retype-Password Is Incorrect","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!checkPhoneNumber(phoneNumField)){
					JOptionPane.showMessageDialog(null, "Your PhoneNumber Must Be Digits","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(dayCbbox.getSelectedIndex() < 0 ||monthCbbox.getSelectedIndex() < 0 || yearCbbox.getSelectedIndex() < 0){
					JOptionPane.showMessageDialog(null,"Fill Your Date Of Birth");
					return;
				}
				if(!check_date(dayCbbox, monthCbbox, yearCbbox)){
					JOptionPane.showMessageDialog(null,"Date Of Birth Is Not Exist","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!usernameField.getText().equals("") && !check_text(usernameField.getText())){
					JOptionPane.showMessageDialog(null, "Username Must Be Letters Or Digits","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(!passwordField.getText().equals("") && !check_text(passwordField.getText())){
					JOptionPane.showMessageDialog(null, "Password Must Be Letters Or Digits","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				usrname = new String(usernameField.getText());
				password = new String(passwordField.getPassword());
				fullname = new String(fullNameField.getText());
				dateofbirth = new String(yearCbbox.getSelectedItem() + "-" + monthCbbox.getSelectedItem() + "-" + dayCbbox.getSelectedItem());
				sex = new String((String)sexcomboBox.getSelectedItem());
				phonenum = new String(phoneNumField.getText());
				
				do{
					;
				}
				while(!clientProcess.request.toString().equals(""));
				
				clientProcess.getRequestFromClient("dataQuery(Account~AID~\"\"~\"\"~\"\"~\"\")");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				List<List<String>> resultList;
				resultList = clientProcess.getResultList();
				clientProcess.setResultList();
				aid = new String(pAIDRan.ProvideIDRandom(resultList,"ACCT"));
				do{
					;
				}
				while(!clientProcess.request.toString().equals(""));
				clientProcess.getRequestFromClient("insertDataQuery(Account~"+ aid + "~" + usrname + "~" + password + "~" + fullname + "~" + dateofbirth + "~" + phonenum + "~" + sex + "~1" +")");
				do{
					if(clientProcess.lock == 1){
						clientProcess.setRequest();
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						clientProcess.setRequest();
						return ;
					}
					//Vong lap nay dung de cho den khi co ket qua
				}while(!clientProcess.request.toString().equals(""));
				if(!clientProcess.getResultAlterQuery().equals("0")){
					AccountInfor accInfor = new AccountInfor();
					accInfor.setAID(aid);
					accInfor.setUsername(usrname);
					accInfor.setPassword(password);
					accInfor.setFullname(fullname);
					accInfor.setBirthday(dateofbirth);
					accInfor.setPhonenumber(phonenum);
					accInfor.setSex(sex);

					AccountGUI accGUI = new AccountGUI(accInfor,clientProcess);
					accGUI.addWindowListener(new WindowAdapter(){
						public void windowClosing(WindowEvent we){
							do{
								;
							}
							while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("updateDataQuery(Account~Status~0~Status = 1 and Username = '"+ accInfor.getUsername() +"')");
							clientProcess.printRequest();
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									if(clientProcess.listUserIsOnline.contains(accInfor.getUsername())){
										clientProcess.listUserIsOnline.remove(accInfor.getUsername());
									}
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							if(clientProcess.listUserIsOnline.contains(accInfor.getUsername())){
								clientProcess.listUserIsOnline.remove(accInfor.getUsername());
							}
						}
					});
					
					usernameField.setText("");
					passwordField.setText("");
					fullNameField.setText("");
					phoneNumField.setText("");
					retypasswordField.setText("");
					
					dayCbbox.setEditable(true);
					monthCbbox.setEditable(true);
					yearCbbox.setEditable(true);
					sexcomboBox.setEditable(true);
					
					dayCbbox.setSelectedItem("Day");
					monthCbbox.setSelectedItem("Month");
					yearCbbox.setSelectedItem("Year");
					sexcomboBox.setSelectedItem("Male");

					dayCbbox.setEditable(false);
					monthCbbox.setEditable(false);
					yearCbbox.setEditable(false);
					sexcomboBox.setEditable(false);
					
					JOptionPane.showMessageDialog(null, "Your Account Has Been Created","Announce",JOptionPane.INFORMATION_MESSAGE);
					clientProcess.listUserIsOnline.add(usrname);
				}
				else{
					JOptionPane.showMessageDialog(null, "Your Account Cann't Be Created","Announce",JOptionPane.ERROR_MESSAGE);
				}
				clientProcess.setResultAlterQuery();
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		else{
			JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
		}
	}

	private Boolean check_date(JComboBox day,JComboBox month,JComboBox year){
		int[] monthdays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int iday = day.getSelectedIndex()+1;
		int imonth = month.getSelectedIndex();
		int iyear = year.getSelectedIndex()+1920;
		monthdays[1] = (iyear%4 == 0)? 29 : 28;
		if(iday<=monthdays[imonth]){
			return true;
		}
		else {
			return false;
		}
	}

	private Boolean check_text(String text){
		if (text.matches("[A-Za-z0-9]+")){
			return true;
		}
		else{
			return false;
		}
	}
	
	public JComboBox getDayCbbox() {
		return dayCbbox;
	}

	public JComboBox getMonthCbbox(){
		return monthCbbox;
	}

	public JComboBox getYearCbbox() {
		return yearCbbox;
	}

	public JTextField getUsernameField() {
		return usernameField;
	}
	
	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	public JPasswordField getRetypasswordField() {
		return retypasswordField;
	}
	
	public JTextField getFullNameField() {
		return fullNameField;
	}
	
	public JTextField getPhoneNumField() {
		return phoneNumField;
	}
	
	public JPanel getSignupPn() {
		return signupPn;
	}
	
	public JComboBox getSexcomboBox() {
		return sexcomboBox;
	}
}
