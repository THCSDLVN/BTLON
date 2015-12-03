package Client.foodcreate;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import java.util.List;
import java.util.ArrayList;

import Client.clientprocess.ClientProcess;
import Client.check.Check;

public class Foodcreate extends JDialog {

	public JPanel contentPanel = new JPanel();
	
	public JTextField priceField;
	public JTextField foodnameField;
	public JTextField descriptionField;

	public String resID;

	public Check check = new Check();

	public Foodcreate(ClientProcess clientProcess,String id) {
		resID = id;
		setTitle("Create Food");
		setResizable(false);
		setPreferredSize(new Dimension(300,203));
		pack();

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblfoodname = new JLabel("Food Name");
		lblfoodname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblfoodname.setBounds(10, 11, 80, 22);
		contentPanel.add(lblfoodname);
		
		JLabel lblprice = new JLabel("Price");
		lblprice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblprice.setBounds(10, 44, 80, 22);
		contentPanel.add(lblprice);
		
		priceField = new JTextField();
		priceField.setColumns(10);
		priceField.setBounds(100, 44, 184, 22);
		contentPanel.add(priceField);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					if(!check.check_text(foodnameField.getText().toString())){
						JOptionPane.showMessageDialog(null, "Wrong Foodname - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Announce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!checkisNumber(priceField)){
						JOptionPane.showMessageDialog(null, "Wrong Price - It Must Be Digits","Announce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!check.check_text(descriptionField.getText().toString())){
						JOptionPane.showMessageDialog(null, "Wrong Description - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Announce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					int foopriceI = Integer.parseInt(priceField.getText());
					if(foopriceI <= 0){
						JOptionPane.showMessageDialog(null, "Wrong Price - It Must Be Greater Than 0","Announce",JOptionPane.ERROR_MESSAGE);
						return;
					}
						String foodname = foodnameField.getText();
						String foodprice = Integer.toString(foopriceI);
						String description = descriptionField.getText();

						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Restaurant~Resname~\"\"~ResID = '" + id + "'~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
						//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						List<List<String>> resultid = new ArrayList();
						resultid = clientProcess.getResultList();
						clientProcess.setResultList();
						if(resultid.size() == 0){
							JOptionPane.showMessageDialog(null,"Create Unsucessfully");
							return ;
						}

						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
				
						clientProcess.getRequestFromClient("insertDataQuery{FoodSet~"+ foodname +"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
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
							do{
								;
							}
							while(!clientProcess.request.toString().equals(""));
				
							clientProcess.getRequestFromClient("insertDataQuery{Provide~"+ resID +"~"+ foodname +"~" + foodprice + "~" + description + "~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
							do{
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
							//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							String result2 = new String();
							result2 = clientProcess.getResultAlterQuery();
							clientProcess.setResultAlterQuery();
							
							if(!result2.equals("0")){
								JOptionPane.showMessageDialog(null, "Create Successfully");
							}
							else{
								JOptionPane.showMessageDialog(null,"Create Unsucessfully");
								return ;
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"Create Unsucessfully");
							return ;
						}
					dispose();
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}
		});
		btnOK.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/Check-icon.png"));
		btnOK.setBounds(10, 106, 119, 58);
		contentPanel.add(btnOK);
		
		JButton btnCacncel = new JButton("Cancel");
		btnCacncel.setFont(new Font("Ubuntu", 1, 12));
		btnCacncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCacncel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/cancel.png"));
		btnCacncel.setBounds(165, 106, 119, 58);
		contentPanel.add(btnCacncel);
		
		foodnameField = new JTextField();
		foodnameField.setColumns(10);
		foodnameField.setBounds(100, 11, 184, 22);
		contentPanel.add(foodnameField);
		
		JLabel lblDesciption = new JLabel("Desciption");
		lblDesciption.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesciption.setBounds(10, 77, 80, 22);
		contentPanel.add(lblDesciption);
		
		descriptionField = new JTextField();
		descriptionField.setColumns(10);
		descriptionField.setBounds(100, 77, 184, 22);
		contentPanel.add(descriptionField);
	}

	public Boolean checkisNumber(JTextField field){
		String phonenum = new String(field.getText());
		if(phonenum.matches("[0-9]+")){
			return true;
		}
		else{
			return false;
		}
	}
}