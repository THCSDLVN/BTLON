package Client.createaddress;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Client.clientprocess.ClientProcess;
import Client.check.Check;

public class CreateAddress extends JDialog {
	public JTextField addressField;
	public Check check = new Check();

	String resID;
	
	public CreateAddress(ClientProcess clientProcess,String id) {
		resID = id;
		setTitle("Create Address");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 198);
		getContentPane().setLayout(null);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(48, 59, 77, 28);
		getContentPane().add(lblAddress);
		
		JLabel addressIMG = new JLabel("");
		addressIMG.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/AddressIcon.png"));
		addressIMG.setBounds(10, 59, 28, 28);
		getContentPane().add(addressIMG);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(addressField.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Fill the new Address");
					return;
				}
				else{	
					if(clientProcess.lock == 0){	
						String new_address = addressField.getText();
						if(check.check_text(new_address)){
							do{
								;
							}
							while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("insertDataQuery{SequenceRestaurant~" + new_address + "~" + resID + "~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String result = new String();
							result = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();
							
							if(!result.equals("0")){
								JOptionPane.showMessageDialog(null,"Create Successfully");
							}
							else{
								JOptionPane.showMessageDialog(null,"Create Unsuccessfully");
								return ;
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"Wrong Address - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Annouce",JOptionPane.ERROR_MESSAGE);
							return ;
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Server Has Crased","Annouce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
					dispose();				
				}
			}
		});
		btnOK.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/Check-icon.png"));
		btnOK.setBounds(30, 105, 132, 54);
		getContentPane().add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/cancel.png"));
		btnCancel.setBounds(207, 105, 132, 54);
		getContentPane().add(btnCancel);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(132, 59, 219, 28);		
		getContentPane().add(addressField);
		
		JLabel lblNewLabel = new JLabel("INPUT YOUR NEW ADDRESS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 349, 28);
		getContentPane().add(lblNewLabel);
	}
}
