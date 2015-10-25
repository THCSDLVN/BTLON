package insertdataframe.insertaccountdata;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import sqlfunc.SQLFunc;

public class InsertAccountData { 

	public JLabel labelNameAccount = new JLabel("INSERT ACCOUNT DATA");
	public JLabel accountFullNameLabel = new JLabel("FullName");
	public JLabel accountPhoneLabel = new JLabel("Phone");
	public JLabel accountUserNameLabel = new JLabel("UserName");
	public JLabel acountPasswordLabel = new JLabel("Password");
	public JLabel accountFacebookLabel = new JLabel("Facebook");
	public JLabel accountAIDLabel = new JLabel("AID");
	public JLabel accountHelpLabel = new JLabel("HELP");

	public JTextField accountFullNameTextField = new JTextField();
	public JTextField accountPhoneTextField = new JTextField();
	public JTextField accountUserNameTextField = new JTextField();
	public JTextField accountPasswordTextField = new JTextField();
	public JTextField accountFacebookTextField = new JTextField();
	public JTextField accountAIDTextField = new JTextField();

	public JButton buttonOKAccount = new JButton("OK");

	public SQLFunc sqlFunc;

	public InsertAccountData(JPanel panelAccount, JFrame frame){

		panelAccount.setLayout(null);

		panelAccount.add(labelNameAccount);
		panelAccount.add(accountFullNameLabel);
		panelAccount.add(accountPhoneLabel);
		panelAccount.add(accountUserNameLabel);
		panelAccount.add(acountPasswordLabel);
		panelAccount.add(accountFacebookLabel);
		panelAccount.add(accountAIDLabel);

		panelAccount.add(accountFullNameTextField);
		panelAccount.add(accountPhoneTextField);
		panelAccount.add(accountUserNameTextField);
		panelAccount.add(accountPasswordTextField);
		panelAccount.add(accountFacebookTextField);
		panelAccount.add(accountAIDTextField);

		panelAccount.add(buttonOKAccount);

		labelNameAccount.setFont(new Font("Ubuntu", 1, 24));
		labelNameAccount.setForeground(new Color(246, 9, 9));
		labelNameAccount.setHorizontalAlignment(SwingConstants.CENTER);
		labelNameAccount.setBounds(170, 10, 320, 40);

		accountFullNameLabel.setFont(new Font("Ubuntu", 1, 16));
		accountFullNameLabel.setForeground(new Color(22, 117, 245));
		accountFullNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountFullNameLabel.setBorder(BorderFactory.createEtchedBorder());
		accountFullNameLabel.setBounds(20, 150, 120, 40);

		accountPhoneLabel.setFont(new Font("Ubuntu", 1, 16));
		accountPhoneLabel.setForeground(new Color(22, 117, 245));
		accountPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountPhoneLabel.setBorder(BorderFactory.createEtchedBorder());
		accountPhoneLabel.setBounds(20, 230, 120, 40);

		accountUserNameLabel.setFont(new Font("Ubuntu", 1, 16));
		accountUserNameLabel.setForeground(new Color(22, 117, 245));
		accountUserNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountUserNameLabel.setBorder(BorderFactory.createEtchedBorder());
		accountUserNameLabel.setBounds(350, 80, 120, 40);

		acountPasswordLabel.setFont(new Font("Ubuntu", 1, 16));
		acountPasswordLabel.setForeground(new Color(22, 117, 245));
		acountPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acountPasswordLabel.setBorder(BorderFactory.createEtchedBorder());
		acountPasswordLabel.setBounds(350, 150, 120, 40);

		accountFacebookLabel.setFont(new Font("Ubuntu", 1, 16));
		accountFacebookLabel.setForeground(new Color(22, 117, 245));
		accountFacebookLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountFacebookLabel.setBorder(BorderFactory.createEtchedBorder());
		accountFacebookLabel.setBounds(350, 230, 120, 40);

		accountAIDLabel.setFont(new Font("Ubuntu", 1, 16));
		accountAIDLabel.setForeground(new Color(22, 117, 245));
		accountAIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountAIDLabel.setBorder(BorderFactory.createEtchedBorder());
		accountAIDLabel.setBounds(20, 80, 120, 40);

		accountFullNameTextField.setFont(new Font("Ubuntu", 1, 18));
		accountFullNameTextField.setBounds(160, 150, 160, 40);

		accountPhoneTextField.setFont(new Font("Ubuntu", 1, 18));
		accountPhoneTextField.setBounds(160, 230, 160, 40);

		accountUserNameTextField.setFont(new Font("Ubuntu", 1, 18));
		accountUserNameTextField.setBounds(490, 80, 160, 40);

		accountPasswordTextField.setFont(new Font("Ubuntu", 1, 18));
		accountPasswordTextField.setBounds(490, 150, 160, 40);

		accountFacebookTextField.setFont(new Font("Ubuntu", 1, 18));
		accountFacebookTextField.setBounds(490, 230, 160, 40);

		accountAIDTextField.setFont(new Font("Ubuntu", 1, 18));
		accountAIDTextField.setBounds(160, 80, 160, 40);

		buttonOKAccount.setFont(new Font("Ubuntu", 1, 18));
		buttonOKAccount.setForeground(new Color(22, 117, 245));
		buttonOKAccount.setBounds(260, 350, 120, 30);
		buttonOKAccount.setBackground(Color.WHITE);
		if(buttonOKAccount.getActionListeners().length < 1){
			buttonOKAccount.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					sqlFunc = new SQLFunc();
					String aid = new String(accountAIDTextField.getText());
					String fullName = new String(accountFullNameTextField.getText());
					String userName = new String(accountUserNameTextField.getText());
					String password = new String(accountPasswordTextField.getText());
					String phone = new String(accountPhoneTextField.getText());
					String face = new String(accountFacebookTextField.getText());

					if(sqlFunc.insertDataQuery("Account",aid,fullName,userName,password,phone,face,"","") > 0){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertAccountDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						
						accountAIDTextField.setText("");
						accountFullNameTextField.setText("");
						accountUserNameTextField.setText("");
						accountPasswordTextField.setText("");
						accountPhoneTextField.setText("");
						accountFacebookTextField.setText("");
					}
					else{
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
						JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertAccountDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
					}
				}
			});
		}

		accountHelpLabel.setFont(new Font("Ubuntu", 1, 16));
		accountHelpLabel.setForeground(new Color(22, 117, 245));
		accountHelpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelAccount.add(accountHelpLabel);
		accountHelpLabel.setBounds(20, 360, 40, 20);
		accountHelpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(accountHelpLabel.getMouseListeners().length < 1){
			accountHelpLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to insert new account data to the database.");
					msg.append("\nInput data into textField correlative to labelName");
					msg.append("\nEach time you can only insert one record to table");
					JOptionPane.showMessageDialog(null,msg.toString(),"Help Of InsertAccountDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
				}
				public void mouseEntered(MouseEvent me) {
					final Map attributes = (new Font("Ubuntu", 1, 16)).getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(new Font(attributes));
				}
				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(new Font("Ubuntu", 1, 16));
				}
			});
		}

		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				accountAIDTextField.setText("");
				accountFullNameTextField.setText("");
				accountUserNameTextField.setText("");
				accountPasswordTextField.setText("");
				accountPhoneTextField.setText("");
				accountFacebookTextField.setText("");
			}
		});
	}
}