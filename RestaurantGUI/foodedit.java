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
import javax.management.Descriptor;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowFocusListener;
import java.sql.Statement;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class foodedit extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField PriceField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args,String id) {
		try {
			foodedit dialog = new foodedit(args,id);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String ResID;
	private String FoodPrice;
	private String FoodName;
	private String DescribeFood;
	private JLabel FoodnameInfo;
	private JTextField DescriptionField;
	/**
	 * Create the dialog.
	 */
	public foodedit(String[] info,String id) {
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
		lblFoodName.setBounds(10, 11, 80, 22);
		contentPanel.add(lblFoodName);
		
		JLabel lblPrice = new JLabel("Price             :");
		lblPrice.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPrice.setBounds(10, 44, 80, 22);
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
				if(!checkisNumber(PriceField))
				{
					JOptionPane.showMessageDialog(null, "Food Price must be a number");
					return;
				}
				else
				{
					int Inewprice = Integer.parseInt(PriceField.getText());
					String newprice = new String(Integer.toString(Inewprice));
					String newdescpt = DescriptionField.getText();
					String upt_query = "UPDATE provide SET Cost =" + newprice +",DescribeFood = '"+ newdescpt +"' WHERE ResID ='" + ResID +"' AND Foodname ='"+ FoodName +"'";
					try{
						Statement stmt = SQLConnection.conn.createStatement();
						stmt.executeUpdate(upt_query);
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
		btnOK.setBounds(10, 110, 119, 58);
		contentPanel.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		btnCancel.setBounds(165, 110, 119, 58);
		contentPanel.add(btnCancel);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDescription.setBounds(10, 77, 80, 22);
		contentPanel.add(lblDescription);
		
		DescriptionField = new JTextField(DescribeFood);
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
