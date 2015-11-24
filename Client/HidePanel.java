package Client.hidepanel;

import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Rectangle;

import Client.orderconfirm.OrderConfirm;
import Client.createorder.CreateOrder;
import Client.clientprocess.ClientProcess;

public class HidePanel extends JPanel {
	public String[] columnNames = new String[] {"Food", "Price"};
	
	public JTable menu = new JTable();
	
	public JComboBox comboBox = new JComboBox();
	
	public JButton orderBtn = new JButton("Order");
	public JButton btnNewButton = new JButton("New");
	
	public JLabel orderLbl = new JLabel("Your Order :");
	public JLabel time = new JLabel("");
	
	public JScrollPane scrollPane = new JScrollPane();

	public String resAddress ="";

	public HidePanel(Border b, String aid,ClientProcess clientProcess) {
		this.setBounds(12, 300, 400, 284);
		this.setBorder(b);
		setLayout(null);
		DefaultTableModel model = new DefaultTableModel(new String[][]{}, columnNames);
		
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getOrderItem().getSelectedIndex() < 0){
					JOptionPane.showMessageDialog(null, "Let choose your Order");
					return;
				}
				JTable tempTable = getMenu();
				int row = tempTable.getSelectedRow();
				if(row < 0){
					return;
				}
				DefaultTableModel model = (DefaultTableModel)tempTable.getModel();
				String foodname = (String)model.getValueAt(row, 0);
				int foodPrice = Integer.parseInt((String)model.getValueAt(row, 1));
				String orderDate = (String)getOrderItem().getSelectedItem();
				new OrderConfirm(clientProcess,foodname,foodPrice,aid,orderDate,resAddress).setVisible(true);
			}
		});
		orderBtn.setBounds(148, 247, 117, 25);
		add(orderBtn);
		
		orderLbl.setBounds(12, 12, 86, 15);
		add(orderLbl);
		
		comboBox.setBounds(116, 7, 189, 24);
		add(comboBox);
		
		
		scrollPane.setBounds(12, 74, 376, 164);
		scrollPane.setBorder(b);
		add(scrollPane);
		
		menu.setModel(model);
		scrollPane.setViewportView(menu);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateOrder(getOrderItem()).setVisible(true);
			}
		});
		btnNewButton.setBounds(317, 7, 71, 25);
		add(btnNewButton);
		
		time.setBounds(12, 39, 202, 15);
		add(time);
	}

	public JTable getMenu() {
		return menu;
	}

	public JComboBox getOrderItem() {
		return comboBox;
	}

	public void setResAddress(String resAddress){
		this.resAddress = resAddress;
	}
}