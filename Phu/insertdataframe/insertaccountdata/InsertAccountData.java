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
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import sqlfunc.SQLFunc;

public class InsertAccountData implements MouseListener{ 

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

	public String sex = new String();

	public JCheckBox checkBox = new JCheckBox();

	public JButton buttonOKAccount = new JButton("OK");

	public SQLFunc sqlFunc;

	public ButtonGroup rbGroup = new ButtonGroup();
	public JRadioButton[] rb = new JRadioButton[3];
	public final int RB_MAX = rb.length - 1;
	public int rbSelected = RB_MAX;

	public InsertAccountData(JPanel panelAccount, JFrame frame){

		panelAccount.setLayout(null);

		panelAccount.add(labelNameAccount);

		panelAccount.add(accountAIDLabel);
		panelAccount.add(accountFullNameLabel);
		panelAccount.add(accountUserNameLabel);
		panelAccount.add(acountPasswordLabel);
		panelAccount.add(accountPhoneLabel);
		panelAccount.add(accountFacebookLabel);
		
		panelAccount.add(accountAIDTextField);
		panelAccount.add(accountFullNameTextField);
		panelAccount.add(accountUserNameTextField);
		panelAccount.add(accountPasswordTextField);
		panelAccount.add(accountPhoneTextField);
		panelAccount.add(accountFacebookTextField);

		panelAccount.add(buttonOKAccount);

		panelAccount.add(checkBox);

		labelNameAccount.setFont(new Font("Ubuntu", 1, 24));
		labelNameAccount.setForeground(new Color(246, 9, 9));
		labelNameAccount.setHorizontalAlignment(SwingConstants.CENTER);
		labelNameAccount.setBounds(180, 20, 290, 28);

		accountAIDLabel.setFont(new Font("Ubuntu", 1, 16));
		accountAIDLabel.setForeground(new Color(22, 117, 245));
		accountAIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountAIDLabel.setBorder(BorderFactory.createEtchedBorder());
		accountAIDLabel.setBounds(20, 80, 130, 40);

		accountFullNameLabel.setFont(new Font("Ubuntu", 1, 16));
		accountFullNameLabel.setForeground(new Color(22, 117, 245));
		accountFullNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountFullNameLabel.setBorder(BorderFactory.createEtchedBorder());
		accountFullNameLabel.setBounds(20, 150, 130, 40);

		accountPhoneLabel.setFont(new Font("Ubuntu", 1, 16));
		accountPhoneLabel.setForeground(new Color(22, 117, 245));
		accountPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountPhoneLabel.setBorder(BorderFactory.createEtchedBorder());
		accountPhoneLabel.setBounds(20, 220, 130, 40);

		accountUserNameLabel.setFont(new Font("Ubuntu", 1, 16));
		accountUserNameLabel.setForeground(new Color(22, 117, 245));
		accountUserNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountUserNameLabel.setBorder(BorderFactory.createEtchedBorder());
		accountUserNameLabel.setBounds(350, 80, 130, 40);

		acountPasswordLabel.setFont(new Font("Ubuntu", 1, 16));
		acountPasswordLabel.setForeground(new Color(22, 117, 245));
		acountPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acountPasswordLabel.setBorder(BorderFactory.createEtchedBorder());
		acountPasswordLabel.setBounds(350, 150, 130, 40);

		accountFacebookLabel.setFont(new Font("Ubuntu", 1, 16));
		accountFacebookLabel.setForeground(new Color(22, 117, 245));
		accountFacebookLabel.setHorizontalAlignment(SwingConstants.CENTER);
		accountFacebookLabel.setBorder(BorderFactory.createEtchedBorder());
		accountFacebookLabel.setBounds(350, 220, 130, 40);

		accountAIDTextField.setFont(new Font("Ubuntu", 1, 18));
		accountAIDTextField.setBounds(170, 80, 160, 40);

		accountFullNameTextField.setFont(new Font("Ubuntu", 1, 18));
		accountFullNameTextField.setBounds(170, 150, 160, 40);

		accountPhoneTextField.setFont(new Font("Ubuntu", 1, 18));
		accountPhoneTextField.setBounds(170, 220, 160, 40);

		accountUserNameTextField.setFont(new Font("Ubuntu", 1, 18));
		accountUserNameTextField.setBounds(500, 80, 160, 40);

		accountPasswordTextField.setFont(new Font("Ubuntu", 1, 18));
		accountPasswordTextField.setBounds(500, 150, 160, 40);

		accountFacebookTextField.setFont(new Font("Ubuntu", 1, 18));
		accountFacebookTextField.setBounds(500, 220, 160, 40);

		checkBox.setFont(new Font("Ubuntu", 1, 15));
		checkBox.setForeground(new Color(22, 112, 245));
		checkBox.setText("Are You Sure Want To Create Account ?");
		checkBox.setBounds(350, 280, 380, 21);
		checkBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				if(checkBox.isSelected()){
					buttonOKAccount.setEnabled(true);
				}
				else{
					buttonOKAccount.setEnabled(false);
				}
			}
		});

		buttonOKAccount.setEnabled(false);
		buttonOKAccount.setFont(new Font("Ubuntu", 1, 18));
		buttonOKAccount.setForeground(new Color(22, 117, 245));
		buttonOKAccount.setBounds(270, 360, 120, 30);
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

					if(sqlFunc.insertDataQuery("Account",aid,fullName,userName,password,phone,face,sex,"") > 0 && !sex.equals("")){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertAccountDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						
						accountAIDTextField.setText("");
						accountFullNameTextField.setText("");
						accountUserNameTextField.setText("");
						accountPasswordTextField.setText("");
						accountPhoneTextField.setText("");
						accountFacebookTextField.setText("");
						sex = "";

						checkBox.setSelected(false);
						buttonOKAccount.setEnabled(false);
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
		accountHelpLabel.setBounds(30, 370, 40, 20);
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

		rb[0] = new JRadioButton("Male");
		rb[1] = new JRadioButton("Female");
		rb[2] = new JRadioButton();
		
		rb[0].addMouseListener(this);
		rb[1].addMouseListener(this);
		rb[2].addMouseListener(this);

		rb[0].setBounds(30, 280, 90, 21);
		rb[1].setBounds(180, 280, 90, 21);
		
		rbGroup.add(rb[0]);
		rbGroup.add(rb[1]);
		rbGroup.add(rb[2]);
		panelAccount.add(rb[0]);
		panelAccount.add(rb[1]);
		
		rb[rbSelected].setSelected(true);

		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				accountAIDTextField.setText("");
				accountFullNameTextField.setText("");
				accountUserNameTextField.setText("");
				accountPasswordTextField.setText("");
				accountPhoneTextField.setText("");
				accountFacebookTextField.setText("");
				sex = "";
				rbGroup.clearSelection();
				checkBox.setSelected(false);
				buttonOKAccount.setEnabled(false);
			}
		});
	}

	public void mouseClicked(MouseEvent me){
		for(int x = 0; x < RB_MAX; x++){
			if(me.getSource() == rb[x]){
				if(x != rbSelected){
					rbSelected = x;
				}
				else{
					rb[RB_MAX].setSelected(true);
					rbSelected = RB_MAX;
				}
				if(rb[0].isSelected()){
					sex = "Male";
				}
				else if(rb[1].isSelected()){
					sex = "Female";
				}
				else if(rb[2].isSelected()){
					sex = "";
				}
				break;
			}
		}
	}
	
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
}