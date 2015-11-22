import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderConfirm extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			OrderConfirm dialog = new OrderConfirm("Foodname",10000);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public OrderConfirm(String foodname,int foodPrice) {
		setResizable(false);
		setBounds(400, 450, 297, 182);
		getContentPane().setLayout(null);
		
		JLabel foodnameAvaLbl = new JLabel();
		foodnameAvaLbl.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/foodname.png")));
		foodnameAvaLbl.setBounds(12, 12, 45, 41);
		getContentPane().add(foodnameAvaLbl);
		
		JLabel foodnameLbl = new JLabel(foodname);
		foodnameLbl.setBounds(53, 12, 159, 30);
		getContentPane().add(foodnameLbl);
		
		
		JLabel lblFood = new JLabel(Integer.toString(foodPrice),JLabel.RIGHT);
		lblFood.setIcon(new ImageIcon(OrderConfirm.class.getResource("/Picture/coins.png")));
		lblFood.setBounds(12, 54, 159, 41);
		getContentPane().add(lblFood);
		
		JSpinner spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int quantity = (int)spinner.getValue();
				int total = quantity * foodPrice;
				lblFood.setText(Integer.toString(total));
			}
		});
		spinner.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinner.setBounds(211, 18, 51, 20);
		getContentPane().add(spinner);
		
		JLabel lblUsd = new JLabel("VND");
		lblUsd.setBounds(221, 54, 51, 41);
		getContentPane().add(lblUsd);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnConfirm.setBounds(95, 118, 117, 25);
		getContentPane().add(btnConfirm);
	}
}
