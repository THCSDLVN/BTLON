package Client.login;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.font.TextAttribute;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Toolkit;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import Client.signup.Signup;
import Client.clientprocess.ClientProcess;
import Client.accountinfor.AccountInfor;
import Client.solvearraylist.SolveArrayList;
import Client.accountGUI.AccountGUI;

public class Login {

	public JFrame frmLoginAsRestaurant = new JFrame();
	
	public JTextField usernameField = new JTextField();
	
	public JPasswordField passwordField = new JPasswordField();
	
	public JLabel title = new JLabel();
	public JLabel lblHelp = new JLabel("Help");
	public JLabel lblSignUp = new JLabel("Sign Up");
	public JLabel background = new JLabel();
	public JLabel lblUsername = new JLabel("Username");
	public JLabel lblNewLabel = new JLabel("Password");
	public JLabel lblServer = new JLabel("Server");
	
	public JButton btnlogin = new JButton("SIGN IN");
	public JButton btnback = new JButton();

	public JPanel panel;

	public Signup signup;
	public String username = "";
	public String passd = "";
	public String loginquery = new String();

	public ClientProcess clientProcess = new ClientProcess();
	
	public Login(ClientProcess cP) {
		clientProcess = cP;
		signup = new Signup(clientProcess);
		initialize();	
	}
	
	public void initialize() {
		frmLoginAsRestaurant.setTitle("Login");
		frmLoginAsRestaurant.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frmLoginAsRestaurant.setVisible(true);
		frmLoginAsRestaurant.setPreferredSize(new Dimension(459, 285));
		frmLoginAsRestaurant.setResizable(false);
		frmLoginAsRestaurant.pack();

		frmLoginAsRestaurant.getContentPane().setLayout(null);

		frmLoginAsRestaurant.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				if(!clientProcess.listUserIsOnline.isEmpty()){
					for (Iterator<String> i = clientProcess.listUserIsOnline.iterator(); i.hasNext();) {
						String usn = new String(i.next());
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Account~Status~0~Status = 1 and Username ='" + usn + "'}");
						clientProcess.printRequest();
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								clientProcess.listUserIsOnline.clear();
								System.exit(0);
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
					}
					clientProcess.listUserIsOnline.clear();
				}
				clientProcess.getRequestFromClient("exit");
				clientProcess.setRequest();
				System.exit(0);
			}
		});

		frmLoginAsRestaurant.getContentPane().add(lblUsername);
		frmLoginAsRestaurant.getContentPane().add(lblNewLabel);
		frmLoginAsRestaurant.getContentPane().add(usernameField);
		frmLoginAsRestaurant.getContentPane().add(passwordField);
		frmLoginAsRestaurant.getContentPane().add(title);
		frmLoginAsRestaurant.getContentPane().add(btnlogin,BorderLayout.CENTER);
		frmLoginAsRestaurant.getContentPane().add(btnback,BorderLayout.CENTER);
		frmLoginAsRestaurant.getContentPane().add(lblHelp);
		frmLoginAsRestaurant.getContentPane().add(lblSignUp);
		frmLoginAsRestaurant.getContentPane().add(lblServer);
		frmLoginAsRestaurant.getContentPane().add(clientProcess.label);
		frmLoginAsRestaurant.getContentPane().add(background);

		clientProcess.label.setBounds(424, 258, 14, 14);

		lblServer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServer.setBounds(366, 259, 59, 14);
		
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(82, 82, 72, 14);
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(82, 118, 72, 14);

		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 12));
		if(lblHelp.getMouseListeners().length < 1){
			lblHelp.addMouseListener(new MouseAdapter() {
				Font original;
				public void mouseEntered(MouseEvent e) {
					original = e.getComponent().getFont();
					Map attribute = original.getAttributes();
					attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					e.getComponent().setFont(original.deriveFont(attribute));
					frmLoginAsRestaurant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				public void mouseExited(MouseEvent e) {
					e.getComponent().setFont(original);
					frmLoginAsRestaurant.setCursor(Cursor.getDefaultCursor());
				}

				public void mouseClicked(MouseEvent e) {
					ImageIcon ic = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/pen.png");
					JOptionPane.showMessageDialog(null, "Some Information Here.." + "\nIf Circle 's Color Is Blue - Server Is Online" + "\nIf Circle 's Color Is Red - Server Is Crash", "Help",JOptionPane.INFORMATION_MESSAGE,ic);
				}
			});
		}
		lblHelp.setBounds(23, 261, 46, 14);
			
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		if(lblSignUp.getMouseListeners().length < 1){
			lblSignUp.addMouseListener(new MouseAdapter() {
				Font original;
				public void mouseEntered(MouseEvent me) {
					original = me.getComponent().getFont();
					Map attribute = original.getAttributes();
					attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(original.deriveFont(attribute));
					frmLoginAsRestaurant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				}

				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(original);
					frmLoginAsRestaurant.setCursor(Cursor.getDefaultCursor());
				}

				public void mouseClicked(MouseEvent me) {
					if(!signup.isShowing()) signup = new Signup(clientProcess);			
					signup.setFocusable(true);
					signup.setVisible(true);
				}
			});
		}
		lblSignUp.setBounds(20, 240, 59, 15);

		title.setForeground(new Color(0, 51, 102));
		title.setFont(new Font("Magneto", Font.BOLD, 14));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(91, 11, 300, 46);
		title.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/AppName.png"));
			
		usernameField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent de) {
				changed();
			}
			
			public void insertUpdate(DocumentEvent de) {
				changed();
			}
			
			public void changedUpdate(DocumentEvent de) {
				changed();
			}
			
			public void changed() {
				username = usernameField.getText();
				if(username.length() * passd.length() > 0){
					btnlogin.setEnabled(true);
				}
				else{
					btnlogin.setEnabled(false);
				}
			}
		});
		usernameField.setBounds(177, 78, 123, 25);
		usernameField.setColumns(10);
		
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			public void removeUpdate(DocumentEvent de) {
				changed();
			}
			
			public void insertUpdate(DocumentEvent de) {
				changed();
			}
			
			public void changedUpdate(DocumentEvent de) {
				changed();
			}
			
			public void changed(){
				passd = new String(passwordField.getPassword()); 
				if(username.length() * passd.length() > 0){
					btnlogin.setEnabled(true);
				}
				else{
					btnlogin.setEnabled(false);
				}
			}
		});
		passwordField.setBounds(177, 114, 123, 25);
		
		btnlogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				try{
					if(btnlogin.isEnabled()){
						if(clientProcess.lock == 0){
							do{
								;
							}
							while(!clientProcess.request.toString().equals(""));			
							clientProcess.getRequestFromClient("dataQuery{Account~*~\"\"~Username = '" + username + "' and Password = '" + passd + "' and Status = 0~\"\"~\"\"}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							List<List<String>> rsl = clientProcess.getResultList();
							clientProcess.setResultList();
							if(!rsl.isEmpty()){
								do{
									;
								}
								while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("updateDataQuery{Account~Status~1~Username = '" + username + "'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));			
								if(!clientProcess.getResultAlterQuery().equals("0")){
									String result[][] = SolveArrayList.ConvertFromArrayList(rsl);
									AccountInfor accInfor = new AccountInfor();
									accInfor.setAID(result[0][0]);
									accInfor.setUsername(result[0][1]);
									accInfor.setPassword(result[0][2]);
									accInfor.setFullname(result[0][3]);
									accInfor.setBirthday(result[0][4]);
									accInfor.setPhonenumber(result[0][5]);
									accInfor.setSex(result[0][6]);
									clientProcess.listUserIsOnline.add(username);
									AccountGUI accGUI = new AccountGUI(accInfor,clientProcess);
									accGUI.addWindowListener(new WindowAdapter(){
										public void windowClosing(WindowEvent we){
											do{
												;
											}
											while(!clientProcess.request.toString().equals(""));
											clientProcess.getRequestFromClient("updateDataQuery{Account~Status~0~Status = 1 and Username = '"+ accInfor.getUsername() +"'}");
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
								}
								else{
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								}
								clientProcess.setResultAlterQuery();
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect Username Or Password Or This Account Is Being Signined","Login Failed",JOptionPane.INFORMATION_MESSAGE);
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						}
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		btnlogin.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/login.png"));
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnlogin.setEnabled(false);
		btnlogin.setBounds(171, 169, 123, 36);

		btnback.setBackground(UIManager.getColor("Button.light"));
		btnback.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/exit.png"));
		btnback.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!clientProcess.listUserIsOnline.isEmpty()){
					for (Iterator<String> i = clientProcess.listUserIsOnline.iterator(); i.hasNext();) {
						String usn = new String(i.next());
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery{Account~Status~0~Status = 1 and Username ='" + usn + "'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								clientProcess.listUserIsOnline.clear();
								System.exit(0);
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
					}
					clientProcess.listUserIsOnline.clear();
				}
				clientProcess.getRequestFromClient("exit");
				clientProcess.setRequest();
				System.exit(0);
			}
		});
		btnback.setBounds(204, 218, 59, 36);
		
		background.setOpaque(true);
		background.setBackground(new Color(119,200,140));
		background.setBounds(0, 0, 459, 285);
	}	
}
