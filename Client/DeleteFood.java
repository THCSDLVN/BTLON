package Client.deletefood;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import Client.clientprocess.ClientProcess;

public class DeleteFood extends JDialog {
	
	public String Foodname;
	public String ResID;

	public DeleteFood(ClientProcess clientProcess,String foodname,String id) {
		Foodname = foodname;
		ResID = id;
		setTitle("Delete Food");
		setModal(true);
		setPreferredSize(new Dimension(466, 178));
		setResizable(false);
		pack();
		
		getContentPane().setLayout(null);
		
		JLabel lblIMG = new JLabel("");
		lblIMG.setIcon(new ImageIcon(DeleteFood.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-confirm.png")));
		lblIMG.setBounds(10, 23, 50, 50);
		getContentPane().add(lblIMG);
		
		JLabel lblNewLabel_1 = new JLabel("Do you want to delete "+ foodname + " ?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(91, 23, 359, 31);
		getContentPane().add(lblNewLabel_1);
		
		JButton Ybtn = new JButton("YES");
		Ybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					do{
						;
					}
					while(!clientProcess.request.toString().equals(""));
					clientProcess.getRequestFromClient("deleteDataQuery{Provide~ResID = '" + ResID + "' and Foodname = '" + foodname + "'}");
					do{
						if(clientProcess.lock == 1){
							clientProcess.setRequest();
							JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
							return ;
						}
						//Vong lap nay dung de cho den khi co ket qua
					}while(!clientProcess.request.toString().equals(""));
					String result = clientProcess.getResultAlterQuery();
					clientProcess.setResultAlterQuery();
					if(!result.equals("0")){
						JOptionPane.showMessageDialog(null,"Delete Successfully");
					}
					else{
						JOptionPane.showMessageDialog(null,"Delete Unsuccessfully");
						return ;
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Server Has Crased","Annouce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
				dispose();
			}
		});
		Ybtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Ybtn.setBounds(91, 85, 132, 54);
		getContentPane().add(Ybtn);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNo.setBounds(240, 85, 132, 54);
		getContentPane().add(btnNo);
	}
}