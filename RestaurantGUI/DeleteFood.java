package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteFood extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String foodname,String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteFood dialog = new DeleteFood(foodname,id);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private String Foodname;
	private String ResID;

	/**
	 * Create the dialog.
	 */
	public DeleteFood(String foodname,String id) {
		Foodname = foodname;
		ResID = id;
		setTitle("Delete");
		setResizable(false);
		setModal(true);
		setBounds(300, 200, 466, 178);
		getContentPane().setLayout(null);
		
		JLabel lblIMG = new JLabel("");
		lblIMG.setIcon(new ImageIcon(DeleteFood.class.getResource("/com/sun/javafx/scene/control/skin/modena/dialog-confirm.png")));
		lblIMG.setBounds(10, 23, 50, 50);
		getContentPane().add(lblIMG);
		
		JLabel lblNewLabel_1 = new JLabel("Do you want to delete "+ foodname +"?");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(91, 23, 359, 31);
		getContentPane().add(lblNewLabel_1);
		
		JButton Ybtn = new JButton("YES");
		Ybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String del_query = "DELETE FROM provide WHERE ResID ='"+ ResID +"' AND Foodname ='"+ foodname +"'";
					Statement stmt = SQLConnection.conn.createStatement();
					stmt.executeUpdate(del_query);
					stmt.close();
				}catch(Exception ex)
				{
					ex.printStackTrace();
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
