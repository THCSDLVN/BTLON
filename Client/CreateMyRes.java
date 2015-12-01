package Client.createmyres;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.util.List;
import java.util.ArrayList;

import Client.provideIDRandom.ProvideIDRandom;
import Client.check.Check;
import Client.clientprocess.ClientProcess;

public class CreateMyRes extends JDialog {
	public JTextField resNameTxF = new JTextField();
	public JTextField resAddrTxF = new JTextField();
	
	public ProvideIDRandom pir = new ProvideIDRandom();
	
	public JLabel lblName = new JLabel("Restaurant Name");
	public JLabel lblAddress = new JLabel("Restaurant Address");
	
	public JButton btnCreate = new JButton("CREATE");

	public Check check = new Check();

	public ProvideIDRandom provideIDRandom = new ProvideIDRandom();

	public CreateMyRes(String aid, ClientProcess clientProcess,JComboBox myresCb,List<String> restaurantnames, List<String> restaurantids) {
		setPreferredSize(new Dimension(450, 233));
		setResizable(false);
		pack();
		setTitle("Create My Restaurant");

		getContentPane().setLayout(null);
		getContentPane().add(lblName);
		getContentPane().add(lblAddress);
		getContentPane().add(resNameTxF);
		getContentPane().add(resAddrTxF);
		getContentPane().add(btnCreate);
		
		lblName.setBounds(10, 12, 150, 35);
		lblAddress.setBounds(10, 82, 150, 35);
		
		resNameTxF.setBounds(170, 12, 240, 35);
		resNameTxF.setColumns(10);
		
		resAddrTxF.setColumns(10);
		resAddrTxF.setBounds(170, 82, 240, 35);
		
		
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					String resName = resNameTxF.getText().toString();
					String resAddress = resAddrTxF.getText().toString();
					String resID = new String();

					if(!check.check_text(resName)){
						JOptionPane.showMessageDialog(null, "Wrong Resname - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Announce",JOptionPane.ERROR_MESSAGE);
						return ;
					}
					if(!check.check_text(resAddress)){
						JOptionPane.showMessageDialog(null, "Wrong ResAddress - It Must Be Not Empty and Must Be Digits Or Letters And Length Within 45 Letters","Announce",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					do{
						;
					}while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("dataQuery{Restaurant~ResID~\"\"~Resname = '" + resName  + "'~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					List<List<String>> resultList = new ArrayList();
					resultList = clientProcess.getResultList();
					clientProcess.setResultList();

					if(resultList.size() == 0){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Restaurant~ResID~\"\"~\"\"~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						List<List<String>> result1 = new ArrayList();
						result1 = clientProcess.getResultList();
						clientProcess.setResultList();

						resID = new String(provideIDRandom.ProvideIDRandom(result1,"REST"));
					}
					else{
						for(List<String> innerLs : resultList) {
							for (String str : innerLs) {
								resID = new String(str);
							}
						}
					}
					
					if(resultList.size() == 0){
						do{
							;
						}
						while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("insertDataQuery{Restaurant~"+ resID + "~" + resName + "~" + aid + "~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String success1 = new String();
						success1 = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();
						if(!success1.equals("0")){
							JOptionPane.showMessageDialog(null,"Create Restaurant Succesfully","Announce",JOptionPane.INFORMATION_MESSAGE);
							restaurantnames.add(resName);
							restaurantids.add(resID);
							myresCb.setModel(new DefaultComboBoxModel(restaurantnames.toArray()));
							do{
								;
							}while(!clientProcess.request.toString().equals(""));
							clientProcess.getRequestFromClient("dataQuery{Restaurant~Resname~\"\"~AID = '" + aid + "'~\"\"~\"\"}");
							do{			
								if(clientProcess.lock == 1){
									clientProcess.setRequest();
									JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								//Vong lap nay dung de cho den khi co ket qua
							}while(!clientProcess.request.toString().equals(""));
							List<List<String>> result = clientProcess.getResultList();
							clientProcess.setResultList();
							if(result.size() == 1){
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("insertDataQuery{AccountAssignment~" + aid +"~ROLE0002~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
								do{			
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.setResultAlterQuery();
							}
						}
						else{
							JOptionPane.showMessageDialog(null, "Create Restaurant Unsuccesfully - Please Check Infor Carefully","Annouce",JOptionPane.INFORMATION_MESSAGE);
						}
					}
					
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("insertDataQuery{SequenceRestaurant~"+ resAddress + "~" + resID + "~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"~\"\"}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					String success = new String();
					success = clientProcess.getResultAlterQuery();
					clientProcess.setResultAlterQuery();
					if(!success.equals("0")){
						JOptionPane.showMessageDialog(null, "Create Sequence Store Of Restaurant Succesfully","Annouce",JOptionPane.INFORMATION_MESSAGE);		
						dispose();
					}
					else{
						JOptionPane.showMessageDialog(null, "Create Sequence Store Of Restaurant Unsuccesfully - Please Check Infor Carefully","Annouce",JOptionPane.ERROR_MESSAGE);
						return ;
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		});
		btnCreate.setBounds(157, 149, 117, 30);
	}
}
