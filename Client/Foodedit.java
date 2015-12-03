package Client.foodedit;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

import Client.clientprocess.ClientProcess;
import Client.check.Check;

public class Foodedit extends JDialog {

	public final JPanel contentPanel = new JPanel();

	public JTextField PriceField;
	public JTextField DescriptionField;

	public String ResID;
	public String FoodPrice;
	public String FoodName;
	public String DescribeFood;

	public JLabel FoodnameInfo;
	
	public Check check = new Check();

	public Foodedit(ClientProcess clientProcess,String[] info,String id) {
		setModal(true);
		ResID = id;
		FoodName = info[0];
		FoodPrice = info[1];
		DescribeFood = info[2];
		
		setTitle("Edit Price");
		setResizable(false);
		setBounds(100, 100, 300, 203);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFoodName = new JLabel("Food Name  :");
		lblFoodName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFoodName.setBounds(10, 11, 90, 22);
		contentPanel.add(lblFoodName);
		
		JLabel lblPrice = new JLabel("Price            :");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(10, 44, 90, 22);
		contentPanel.add(lblPrice);
		
		FoodnameInfo = new JLabel(FoodName);
		FoodnameInfo.setBounds(100, 11, 184, 22);
		contentPanel.add(FoodnameInfo);
		
		PriceField = new JTextField(FoodPrice);
		PriceField.setBounds(100, 44, 184, 22);
		contentPanel.add(PriceField);
		PriceField.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					if(!checkisNumber(PriceField))
					{
						JOptionPane.showMessageDialog(null, "Food Price Must Be A Number","Annouce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					int Inewprice = Integer.parseInt(PriceField.getText());
					if(Inewprice <= 0){
						JOptionPane.showMessageDialog(null, "Food Price Must Greater Than 0","Annouce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					if(!check.check_text(DescriptionField.getText().toString())){
						JOptionPane.showMessageDialog(null, "Wrong Description - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Annouce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					String newprice = new String(Integer.toString(Inewprice));
					String newdescpt = DescriptionField.getText();

					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("updateDataQuery{Provide~Cost~" + newprice + "~ResID = '" + ResID +"' and Foodname = '" + FoodName +"'}");
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
						clientProcess.getRequestFromClient("updateDataQuery{Provide~DescribeFood~" + newdescpt + "~ResID = '" + ResID +"' and Foodname = '" + FoodName +"'}");
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
							JOptionPane.showMessageDialog(null,"Edit Successfully");
						}
						else{
							JOptionPane.showMessageDialog(null,"Edit Unsuccessfully");
							return ;
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Edit Unsuccessfully");
						return ;
					}
					dispose();
				}
			}
		});
		btnOK.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/Check-icon.png"));
		btnOK.setBounds(10, 110, 119, 58);
		contentPanel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Ubuntu", 1, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/cancel.png"));
		btnCancel.setBounds(165, 110, 119, 58);
		contentPanel.add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description  :");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(10, 77, 90, 22);
		contentPanel.add(lblDescription);
		
		DescriptionField = new JTextField(DescribeFood);
		DescriptionField.setColumns(10);
		DescriptionField.setBounds(100, 77, 184, 22);
		contentPanel.add(DescriptionField);
	}
	
	public Boolean checkisNumber(JTextField field)
	{
		String phonenum = new String(field.getText());
		if(phonenum.matches("[0-9]+")){
			return true;
		}
		else{
			return false;
		}
	}
}
