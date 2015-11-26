package Client.editprofileframe;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.font.TextAttribute;

import java.util.Map;

import Client.clientprocess.ClientProcess;
import Client.accountinfor.AccountInfor;

public class EditProfileFrame extends JFrame{
	
	public JLabel lblPass = new JLabel("PassWord");
	public JLabel lblFullname = new JLabel("Full Name");
	public JLabel lblPhoneNumber = new JLabel("Phone Number");
	public JLabel title = new JLabel("EDIT PROFILE");
	public JLabel helplbl = new JLabel("HELP");

	public JButton buttonConfirm = new JButton("Confirm");

	public JTextField passTextField = new JTextField();
	public JTextField nameTextField = new JTextField();
	public JTextField phoneTextField = new JTextField();

	public EditProfileFrame(AccountInfor accInf, ClientProcess clientProcess,JLabel lblName,JLabel lblPhone){
		super("Edit Profile Frame");
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setPreferredSize(new Dimension(400,300));
		pack();

		getContentPane().add(lblPhoneNumber);
		getContentPane().add(lblFullname);
		getContentPane().add(lblPass);
		getContentPane().add(title);
		getContentPane().add(buttonConfirm);
		getContentPane().add(passTextField);
		getContentPane().add(nameTextField);
		getContentPane().add(phoneTextField);
		getContentPane().add(helplbl);

		passTextField.setBounds(210, 70, 150, 27);
		nameTextField.setBounds(210, 120, 150, 27);
		phoneTextField.setBounds(210, 170, 150, 27);

		passTextField.setText(accInf.getPassword());
		nameTextField.setText(accInf.getFullname());
		phoneTextField.setText(accInf.getPhoneNumber());

		title.setFont(new Font("Ubuntu", 1, 24));
		title.setForeground(new Color(253, 26, 2));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(110, 10, 180, 28);

		lblPhoneNumber.setFont(new Font("Ubuntu", 1, 17));
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setBorder(BorderFactory.createEtchedBorder());
		lblPhoneNumber.setBounds(40, 170, 130, 30);
		lblPhoneNumber.setForeground(new Color(34, 135, 254));

		lblFullname.setFont(new Font("Ubuntu", 1, 17));
		lblFullname.setHorizontalAlignment(SwingConstants.CENTER);
		lblFullname.setText("Full Name");
		lblFullname.setBorder(BorderFactory.createEtchedBorder());
		lblFullname.setBounds(40, 120, 130, 30);
		lblFullname.setForeground(new Color(34, 135, 254));

		lblPass.setFont(new Font("Ubuntu", 1, 17));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass.setText("Password");
		lblPass.setBorder(BorderFactory.createEtchedBorder());
		lblPass.setBounds(40, 70, 130, 30);
		lblPass.setForeground(new Color(34, 135, 254));

		helplbl.setForeground(new Color(34, 135, 254));
		helplbl.setFont(new Font("Ubuntu", 1, 15));
		helplbl.setHorizontalAlignment(SwingConstants.CENTER);
		helplbl.setBounds(10, 270, 38, 18);
		helplbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(helplbl.getMouseListeners().length < 1){
			helplbl.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					JOptionPane.showMessageDialog(null,"Change Infor In TextField","Help",JOptionPane.INFORMATION_MESSAGE);
				}

				public void mouseEntered(MouseEvent me) {
					final Map attributes = (new Font("Ubuntu", 1, 15)).getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(new Font(attributes));
				}
				
				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(new Font("Ubuntu", 1, 15));
				}
			});
		}

		buttonConfirm.setText("Confirm");
		buttonConfirm.setBounds(160, 240, 90, 30);
		buttonConfirm.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(clientProcess.lock == 0){
					int mark = 0;
					String newPass = new String(passTextField.getText());
					String newPhone = new String(phoneTextField.getText());
					String newName = new String(nameTextField.getText());
					if(!newPass.equals(accInf.getPassword())){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery(Account~Password~" + newPass + "~AID = '"+ accInf.getAID() +"')");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						if(!clientProcess.getResultAlterQuery().equals("0")){
							clientProcess.setResultAlterQuery();
							mark++;
							accInf.setPassword(passTextField.getText());
							JOptionPane.showMessageDialog(null, "Change Password Successful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							clientProcess.setResultAlterQuery();
							JOptionPane.showMessageDialog(null, "Change Password Unsuccessful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(!newPhone.equals(accInf.getPhoneNumber())){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery(Account~PhoneNumber~" + newPhone + "~AID = '"+ accInf.getAID() +"')");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						if(!clientProcess.getResultAlterQuery().equals("0")){
							clientProcess.setResultAlterQuery();
							mark++;
							accInf.setPhonenumber(phoneTextField.getText());
							lblPhone.setText(accInf.getPhoneNumber());
							JOptionPane.showMessageDialog(null, "Change PhoneNumber Successful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							clientProcess.setResultAlterQuery();
							JOptionPane.showMessageDialog(null, "Change PhoneNumber Unsuccessful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(!newName.equals(accInf.getFullname())){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("updateDataQuery(Account~FullName~" + newName + "~AID = '"+ accInf.getAID() +"')");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						if(!clientProcess.getResultAlterQuery().equals("0")){
							clientProcess.setResultAlterQuery();
							mark++;
							accInf.setFullname(nameTextField.getText());
							lblName.setText(accInf.getFullname());
							JOptionPane.showMessageDialog(null, "Change Name Successful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
						else{
							clientProcess.setResultAlterQuery();
							JOptionPane.showMessageDialog(null, "Change Name Unsuccessful","Announce",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					if(mark == 0){
						JOptionPane.showMessageDialog(null,"Nothing Change","Annouce",JOptionPane.INFORMATION_MESSAGE);
					}
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}
		});
	}
}