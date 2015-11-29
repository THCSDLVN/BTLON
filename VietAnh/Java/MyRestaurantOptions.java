import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MyRestaurantOptions extends JFrame {

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public SQLConnection c = new SQLConnection();
	public MyRestaurantOptions(String resID,String resName,String AID) {
		super(resName);
		setBounds(100, 100, 382, 129);
		getContentPane().setLayout(null);
		
		JButton deleteMyResBtn = new JButton("Delete");
		deleteMyResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(null, "Are You Sure Want To Delete This Restaurant", "", JOptionPane.YES_NO_OPTION);
				if(confirm == JOptionPane.NO_OPTION);
				else{
					int success = c.DeleteThisRestaurant(resID);
					if(success != 0){
						JOptionPane.showMessageDialog(null, "Thanh Cong");
					}
				}
			}
		});
		if(resID == null)
			deleteMyResBtn.setEnabled(false);
		deleteMyResBtn.setBounds(132, 12, 109, 59);
		getContentPane().add(deleteMyResBtn);
		
		JButton viewManageBtn = new JButton("Manage");
		viewManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mo giao dien cua Hung lam.
			}
		});
		if(resID == null)
			viewManageBtn.setEnabled(false);
		viewManageBtn.setBounds(12, 12, 109, 59);
		getContentPane().add(viewManageBtn);
		
		JButton addMyNewResBtn = new JButton("Add");
		addMyNewResBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateMyRes(AID).setVisible(true);
			}
		});
		addMyNewResBtn.setBounds(253, 12, 109, 59);
		getContentPane().add(addMyNewResBtn);
	}
}
