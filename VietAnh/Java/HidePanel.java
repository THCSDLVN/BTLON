import java.awt.Rectangle;
import java.util.Calendar;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class HidePanel extends JPanel {
	public String[] columnNames = new String[] {"Food", "Price"};
	SQLConnection c = new SQLConnection();
	private JTable menu;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public HidePanel(Border b) {
		this.setBounds(12, 300, 400, 284);
		this.setBorder(b);
		setLayout(null);
		DefaultTableModel model = new DefaultTableModel(new String[][]{}, columnNames);
		
		JButton orderBtn = new JButton("Order");
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getOrderItem().getSelectedIndex() < 0){
					JOptionPane.showMessageDialog(null, "Let choose your Order");
					return;
				}
				JTable tempTable = getMenu();
				int row = tempTable.getSelectedRow();
				if(row < 0)
					return;
				DefaultTableModel model = (DefaultTableModel)tempTable.getModel();
				String foodname = (String)model.getValueAt(row, 0);
				int foodPrice = Integer.parseInt((String)model.getValueAt(row, 1));
				new OrderConfirm(foodname, foodPrice).setVisible(true);
			}
		});
		orderBtn.setBounds(148, 247, 117, 25);
		add(orderBtn);
		
		JLabel orderLbl = new JLabel("Your Order :");
		orderLbl.setBounds(12, 12, 86, 15);
		add(orderLbl);
		
		comboBox = new JComboBox();
		//comboBox.setModel(new DefaultComboBoxModel(new String[] {"1995", "2000", "1997", "2888"}));
		comboBox.setBounds(116, 7, 189, 24);
		add(comboBox);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 74, 376, 164);
		scrollPane.setBorder(b);
		add(scrollPane);
		
		menu = new JTable();
		menu.setModel(model);
		scrollPane.setViewportView(menu);
		
		JButton btnNewButton = new JButton("New");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(getOrderItem().getSelectedIndex());
			}
		});
		btnNewButton.setBounds(317, 7, 71, 25);
		add(btnNewButton);
		
		JLabel time = new JLabel("");
		time.setBounds(12, 39, 202, 15);
		add(time);
		TimeRunning(time);
		
	}
	public JTable getMenu() {
		return menu;
	}
	public JComboBox getOrderItem() {
		return comboBox;
	}
	public void TimeRunning(JLabel timeLbl){
		Calendar c = Calendar.getInstance();
		int now = c.get(Calendar.SECOND);
		int temp = 100;
		//while(true){
		for(;;){
			c = Calendar.getInstance();
			if(temp != now){
				now = temp;
				timeLbl.setText(Integer.toString(c.get(Calendar.HOUR_OF_DAY))+":"+
						Integer.toString(c.get(Calendar.MINUTE))+":"+
						Integer.toString(c.get(Calendar.SECOND)));
			}
			temp = c.get(Calendar.SECOND);
		}
	}
}
