import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateMyRes extends JDialog {
	private JTextField resNameTxF;
	private JTextField resAddrTxF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CreateMyRes dialog = new CreateMyRes("asda");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CreateMyRes(String AID) {
		setBounds(100, 100, 450, 233);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ten");
		lblNewLabel.setBounds(12, 12, 118, 35);
		getContentPane().add(lblNewLabel);
		
		JLabel lblDiaChi = new JLabel("dia chi");
		lblDiaChi.setBounds(12, 72, 118, 35);
		getContentPane().add(lblDiaChi);
		
		resNameTxF = new JTextField();
		resNameTxF.setBounds(148, 12, 279, 35);
		getContentPane().add(resNameTxF);
		resNameTxF.setColumns(10);
		
		resAddrTxF = new JTextField();
		resAddrTxF.setColumns(10);
		resAddrTxF.setBounds(148, 80, 279, 35);
		getContentPane().add(resAddrTxF);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String resName = resNameTxF.getText().toString();
				String resAddress = resAddrTxF.getText().toString();
				String maxResID = "REST0018";
				int success = new SQLConnection().CreateMyRestaurant(AID, maxResID, resName, resAddress);
				if(success != 0){
					JOptionPane.showMessageDialog(null, "Thanh cong");
				}
			}
		});
		btnCreate.setBounds(177, 149, 117, 25);
		getContentPane().add(btnCreate);
	}
}
