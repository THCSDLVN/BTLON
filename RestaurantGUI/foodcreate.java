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

public class foodcreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField PriceField;
	private JTextField FoodnameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			foodcreate dialog = new foodcreate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public foodcreate() {
		setTitle("Create Food");
		setResizable(false);
		setBounds(100, 100, 300, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblFoodname = new JLabel("Food Name");
		lblFoodname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFoodname.setBounds(10, 11, 80, 22);
		contentPanel.add(lblFoodname);
		
		JLabel lblprice = new JLabel("Price");
		lblprice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblprice.setBounds(10, 44, 80, 22);
		contentPanel.add(lblprice);
		
		PriceField = new JTextField();
		PriceField.setColumns(10);
		PriceField.setBounds(100, 44, 184, 22);
		contentPanel.add(PriceField);
		
		JButton btnOK = new JButton("OK");
		btnOK.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Check-icon.png"));
		btnOK.setBounds(10, 77, 112, 58);
		contentPanel.add(btnOK);
		
		JButton btnCacncel = new JButton("Cancel");
		btnCacncel.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\cancel.png"));
		btnCacncel.setBounds(172, 77, 112, 58);
		contentPanel.add(btnCacncel);
		
		FoodnameField = new JTextField();
		FoodnameField.setColumns(10);
		FoodnameField.setBounds(100, 11, 184, 22);
		contentPanel.add(FoodnameField);
	}
}
