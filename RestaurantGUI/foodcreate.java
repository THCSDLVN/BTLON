package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class foodcreate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField PriceField;
	private JTextField FoodnameField;

	/**
	 * Launch the application.
	 */
	public static void main(String id) {
		try {
			foodcreate dialog = new foodcreate(id);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String ResID;
	private JTextField DescriptionField;
	/**
	 * Create the dialog.
	 */
	public foodcreate(String id) {
		ResID = id;
		setModal(true);
		setTitle("Create Food");
		setResizable(false);
		setBounds(100, 100, 300, 203);
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
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(FoodnameField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Fill the Food Name");
					return;
				}
				else if(!checkisNumber(PriceField))
				{
					JOptionPane.showMessageDialog(null, "Food Price must be a number");
					return;
				}
				else{
					try
					{
						String FoodName = FoodnameField.getText();
						int FoodPriceI = Integer.parseInt(PriceField.getText());
						String FoodPrice = Integer.toString(FoodPriceI);
						String description = DescriptionField.getText();
						
						String inst_query1 = "INSERT INTO foodset values('" + FoodName +"')"; 
						String inst_query2 ="INSERT INTO provide values('"+ ResID +"','"+ FoodName +"',"+ FoodPrice +",'"+ description +"')";
						Statement stmt = SQLConnection.conn.createStatement();
						
						stmt.executeUpdate(inst_query1);
						stmt.executeUpdate(inst_query2);
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
		btnOK.setBounds(10, 106, 119, 58);
		contentPanel.add(btnOK);
		
		JButton btnCacncel = new JButton("Cancel");
		btnCacncel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCacncel.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		btnCacncel.setBounds(165, 106, 119, 58);
		contentPanel.add(btnCacncel);
		
		FoodnameField = new JTextField();
		FoodnameField.setColumns(10);
		FoodnameField.setBounds(100, 11, 184, 22);
		contentPanel.add(FoodnameField);
		
		JLabel lblDesciption = new JLabel("Desciption");
		lblDesciption.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDesciption.setBounds(10, 77, 80, 22);
		contentPanel.add(lblDesciption);
		
		DescriptionField = new JTextField();
		DescriptionField.setColumns(10);
		DescriptionField.setBounds(100, 77, 184, 22);
		contentPanel.add(DescriptionField);
	}
	
	private Boolean checkisNumber(JTextField PhoneField)
	{
		String phonenum = new String(PhoneField.getText());
		if(phonenum.matches("[0-9]+"))
			return true;
		else
			return false;
	}
}
