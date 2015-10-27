package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class foodedit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField PriceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			foodedit dialog = new foodedit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String FoodID = new String();
	private String FoodPrice = new String();
	private JLabel FoodnameInfo;
	/**
	 * Create the dialog.
	 */
	public foodedit() {
		setTitle("Edit Price");
		setResizable(false);
		setBounds(100, 100, 300, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFoodName = new JLabel("Food Name");
		lblFoodName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFoodName.setBounds(10, 11, 80, 22);
		contentPanel.add(lblFoodName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(10, 44, 80, 22);
		contentPanel.add(lblPrice);
		
		FoodnameInfo = new JLabel("");
		FoodnameInfo.setBounds(100, 11, 184, 22);
		contentPanel.add(FoodnameInfo);
		
		PriceField = new JTextField();
		PriceField.setBounds(100, 44, 184, 22);
		contentPanel.add(PriceField);
		PriceField.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Check-icon.png"));
		btnOK.setBounds(10, 77, 112, 58);
		contentPanel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\cancel.png"));
		btnCancel.setBounds(172, 77, 112, 58);
		contentPanel.add(btnCancel);
	}
	public JLabel getFoodnameInfo() {
		return FoodnameInfo;
	}
}
