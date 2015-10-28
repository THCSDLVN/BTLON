package insertdataframe.insertrestaurantdata;

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
import javax.swing.JCheckBox;

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

public class InsertRestaurantData { 

	public JLabel labelNameRestaurant = new JLabel("INSERT RESTAURANT DATA");
	public JLabel restaurantRIDLabel = new JLabel("RID");
	public JLabel restaurantFullNameLabel = new JLabel("Restaurant Name");
	public JLabel restaurantAddressLabel = new JLabel("Address");
	public JLabel restaurantPhoneLabel = new JLabel("Phone");
	public JLabel restaurantResNameLabel = new JLabel("ResName");
	public JLabel restaurantPasswordLabel = new JLabel("Password");
	public JLabel restaurantFacebookLabel = new JLabel("Facebook");
	public JLabel restaurantHelpLabel = new JLabel("HELP");

	public JTextField restaurantRIDTextField = new JTextField();
	public JTextField restaurantFullNameTextField = new JTextField();
	public JTextField restaurantAddressTextField = new JTextField();
	public JTextField restaurantPhoneTextField = new JTextField();
	public JTextField restaurantFacebookTextField = new JTextField();
	public JTextField restaurantResNameTextField = new JTextField();
	public JTextField restaurantPasswordTextField = new JTextField();

	public JCheckBox checkBox = new JCheckBox();

	public JButton buttonOKRestaurant = new JButton("OK");

	public SQLFunc sqlFunc;

	public InsertRestaurantData(JPanel panelRestaurant, JFrame frame){

		panelRestaurant.setLayout(null);

		panelRestaurant.add(labelNameRestaurant);
		panelRestaurant.add(restaurantRIDLabel);
		panelRestaurant.add(restaurantFullNameLabel);
		panelRestaurant.add(restaurantAddressLabel);
		panelRestaurant.add(restaurantPhoneLabel);
		panelRestaurant.add(restaurantFacebookLabel);
		panelRestaurant.add(restaurantResNameLabel);
		panelRestaurant.add(restaurantPasswordLabel);
		panelRestaurant.add(restaurantHelpLabel);

		panelRestaurant.add(restaurantRIDTextField);
		panelRestaurant.add(restaurantFullNameTextField);
		panelRestaurant.add(restaurantAddressTextField);
		panelRestaurant.add(restaurantPhoneTextField);
		panelRestaurant.add(restaurantFacebookTextField);
		panelRestaurant.add(restaurantResNameTextField);
		panelRestaurant.add(restaurantPasswordTextField);

		panelRestaurant.add(checkBox);

		panelRestaurant.add(buttonOKRestaurant);

		labelNameRestaurant.setFont(new Font("Ubuntu", 1, 24));
		labelNameRestaurant.setForeground(new Color(246, 9, 9));
		labelNameRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
		labelNameRestaurant.setBounds(180, 20, 330, 28);

		restaurantRIDLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantRIDLabel.setForeground(new Color(22, 117, 245));
		restaurantRIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantRIDLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantRIDLabel.setBounds(20, 80, 130, 40);

		restaurantFullNameLabel.setFont(new Font("Ubuntu", 1, 14));
		restaurantFullNameLabel.setForeground(new Color(22, 117, 245));
		restaurantFullNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantFullNameLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantFullNameLabel.setBounds(20, 140, 130, 40);

		restaurantAddressLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantAddressLabel.setForeground(new Color(22, 117, 245));
		restaurantAddressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantAddressLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantAddressLabel.setBounds(20, 200, 130, 40);

		restaurantPhoneLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantPhoneLabel.setForeground(new Color(22, 117, 245));
		restaurantPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantPhoneLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantPhoneLabel.setBounds(20, 260, 130, 40);

		restaurantFacebookLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantFacebookLabel.setForeground(new Color(22, 117, 245));
		restaurantFacebookLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantFacebookLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantFacebookLabel.setBounds(350, 80, 130, 40);

		restaurantResNameLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantResNameLabel.setForeground(new Color(22, 117, 245));
		restaurantResNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantResNameLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantResNameLabel.setBounds(350, 140, 130, 40);

		restaurantPasswordLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantPasswordLabel.setForeground(new Color(22, 117, 245));
		restaurantPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantPasswordLabel.setBorder(BorderFactory.createEtchedBorder());
		restaurantPasswordLabel.setBounds(350, 200, 130, 40);

		restaurantRIDTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantRIDTextField.setBounds(170, 80, 160, 40);

		restaurantFullNameTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantFullNameTextField.setBounds(170, 140, 160, 40);

		restaurantAddressTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantAddressTextField.setBounds(170, 200, 160, 40);

		restaurantPhoneTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantPhoneTextField.setBounds(170, 260, 160, 40);

		restaurantFacebookTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantFacebookTextField.setBounds(500, 80, 160, 40);

		restaurantResNameTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantResNameTextField.setBounds(500, 140, 160, 40);

		restaurantPasswordTextField.setFont(new Font("Ubuntu", 1, 18));
		restaurantPasswordTextField.setBounds(500, 200, 160, 40);

		checkBox.setFont(new Font("Ubuntu", 1, 15));
		checkBox.setForeground(new Color(22, 112, 245));
		checkBox.setText("Are You Sure Want To Create Account");
		checkBox.setBounds(360, 270, 310, 21);
		checkBox.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me){
				if(checkBox.isSelected()){
					buttonOKRestaurant.setEnabled(true);
				}
				else{
					buttonOKRestaurant.setEnabled(false);
				}
			}
		});

		buttonOKRestaurant.setFont(new Font("Ubuntu", 1, 18));
		buttonOKRestaurant.setForeground(new Color(22, 117, 245));
		buttonOKRestaurant.setBounds(270, 360, 120, 30);
		buttonOKRestaurant.setBackground(Color.WHITE);
		buttonOKRestaurant.setEnabled(false);
		if(buttonOKRestaurant.getActionListeners().length < 1){
			buttonOKRestaurant.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					sqlFunc = new SQLFunc();
					String rid = new String(restaurantRIDTextField.getText());
					String restaurantFullName = new String(restaurantFullNameTextField.getText());
					String address = new String(restaurantAddressTextField.getText());
					String resName = new String(restaurantResNameTextField.getText());
					String password = new String(restaurantPasswordTextField.getText());
					String phone = new String(restaurantPhoneTextField.getText());
					String face = new String(restaurantFacebookTextField.getText());

					if(sqlFunc.insertDataQuery("Restaurant",rid,restaurantFullName,address,phone,face,"",resName,password) > 0){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertrestaurantDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						
						restaurantRIDTextField.setText("");
						restaurantFullNameTextField.setText("");
						restaurantAddressTextField.setText("");
						restaurantPhoneTextField.setText("");
						restaurantFacebookTextField.setText("");
						restaurantResNameTextField.setText("");
						restaurantPasswordTextField.setText("");

						checkBox.setSelected(false);
						buttonOKRestaurant.setEnabled(false);
					}
					else{
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
						JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertrestaurantDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
					}
				}
			});
		}

		restaurantHelpLabel.setFont(new Font("Ubuntu", 1, 16));
		restaurantHelpLabel.setForeground(new Color(22, 117, 245));
		restaurantHelpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		restaurantHelpLabel.setBounds(30, 360, 49, 17);
		restaurantHelpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(restaurantHelpLabel.getMouseListeners().length < 1){
			restaurantHelpLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to insert new restaurant data to the database.");
					msg.append("\nInput data into textField correlative to labelName");
					msg.append("\nEach time you can only insert one record to table");
					JOptionPane.showMessageDialog(null,msg.toString(),"Help Of InsertrestaurantDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
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
				restaurantRIDTextField.setText("");
				restaurantFullNameTextField.setText("");
				restaurantAddressTextField.setText("");
				restaurantPhoneTextField.setText("");
				restaurantFacebookTextField.setText("");
				restaurantResNameTextField.setText("");
				restaurantPasswordTextField.setText("");

				checkBox.setSelected(false);
				buttonOKRestaurant.setEnabled(false);
			}
		});
	}
}