package gui;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class EditProfile extends JDialog {
	private JTextField AddressField;

	/**
	 * Launch the application.
	 */
	public static void main(String id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditProfile dialog = new EditProfile(id);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String ResID;
	/**
	 * Create the dialog.
	 */
	public EditProfile(String id) {
		ResID = id;
		setTitle("CREATE ADDRESS");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 375, 198);
		getContentPane().setLayout(null);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(48, 59, 77, 28);
		getContentPane().add(lblAddress);
		
		JLabel AddressIMG = new JLabel("");
		AddressIMG.setIcon(new ImageIcon(this.getClass().getResource("/AddressIcon.png")));
		AddressIMG.setBounds(10, 59, 28, 28);
		getContentPane().add(AddressIMG);
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(AddressField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Fill the new Address");
					return;
				}
				else
				{
					try
					{
						String new_address = AddressField.getText();
						
						String inst_query1 = "INSERT INTO sequencerestaurant values ('" + new_address +"','" + ResID +"');"; 
						Statement stmt = SQLConnection.conn.createStatement();
						
						stmt.executeUpdate(inst_query1);
						stmt.close();
					}catch(Exception ex)
					{
						ex.printStackTrace();
					}
					dispose();				
					}
				}
		});
		btnOK.setIcon(new ImageIcon(this.getClass().getResource("/Check-icon.png")));
		btnOK.setBounds(30, 105, 132, 54);
		getContentPane().add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		btnCancel.setBounds(207, 105, 132, 54);
		getContentPane().add(btnCancel);
		
		AddressField = new JTextField();
		AddressField.setColumns(10);
		AddressField.setBounds(132, 59, 219, 28);		
		getContentPane().add(AddressField);
		
		JLabel lblNewLabel = new JLabel("INPUT YOUR NEW ADDRESS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 349, 28);
		getContentPane().add(lblNewLabel);
	}
}
