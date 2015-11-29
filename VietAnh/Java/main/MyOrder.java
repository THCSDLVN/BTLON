import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import org.omg.CORBA.ORB;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyOrder extends JPanel {
	private JTable orderContentTable;
	private String AID = "";
	private String resAddress = "";
	private String orderDate = "";
	
	public JLabel lblTime;
	
	public SQLConnection c = new SQLConnection();
	public int row; // Dong` dc chon trong bang Cac mon an trong 1 order.
	private JButton fixBtn;
	private JButton deleteBtn;
	/**
	 * Create the panel.
	 */
	public MyOrder(HidePanel2 parent) {
		this.setBounds(12, 300, 400, 284);
		setLayout(null);
		
		lblTime = new JLabel();
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(12, 12, 262, 26);
		add(lblTime);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 48, 376, 163);
		add(scrollPane);
		
		orderContentTable = new JTable();
		orderContentTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = orderContentTable.getSelectedRow();
				String status = (String)orderContentTable.getValueAt(row, 3);
				if(status.equals("Updating")){
					getFixBtn().setEnabled(true);
					getDeleteBtn().setEnabled(true);
				}
				else{
					if(status.equals("Deny")){
						getDeleteBtn().setEnabled(true);
					}
					else{
						getFixBtn().setEnabled(false);
						getDeleteBtn().setEnabled(false);
					}
				}
			}
		});
		scrollPane.setViewportView(orderContentTable);
		
		fixBtn = new JButton("");
		fixBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0)
					return;
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				Double cost = Double.parseDouble((String)getOrderContentTable().getValueAt(row, 2));
				int nowQuantity = Integer.parseInt((String)getOrderContentTable().getValueAt(row, 1));
				OrderConfirm x =  new OrderConfirm(foodname, cost, AID, orderDate, resAddress,nowQuantity);
				x.setVisible(true);
			}
		});
		fixBtn.setIcon(new ImageIcon(MyOrder.class.getResource("/Picture/fix.png")));
		fixBtn.setBounds(266, 218, 55, 54);
		fixBtn.setEnabled(false);
		add(fixBtn);
		
		deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0)
					return;
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				String status = (String)getOrderContentTable().getValueAt(row, 3);
				if(status.equals("Updating")){
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete "+foodname+"",
							"Delete Confirm",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.NO_OPTION);
					else{
						int delete = c.deleteFoodFromThisOrder(AID, resAddress, foodname, orderDate);
						if(delete != 0){
							getOrderContentTable().setValueAt("Delete", row, 3);
						}
						else{
							JOptionPane.showMessageDialog(null, "Nha hang khong ton tai");
						}
					}
				}
				else if(status.equals("Deny")){
					int confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete "+foodname+"",
							"Delete Confirm",JOptionPane.YES_NO_OPTION);
					if(confirm == JOptionPane.NO_OPTION);
					else{
						int delete = c.DeleteDenyOrder(AID, resAddress, foodname, orderDate);
						if(delete != 0){
							DefaultTableModel tempModel = (DefaultTableModel)getOrderContentTable().getModel();
							tempModel.removeRow(row);
						}
						else{
							JOptionPane.showMessageDialog(null, "Nha hang khong ton tai");
						}
					}
				}
			}
		});
		deleteBtn.setIcon(new ImageIcon(MyOrder.class.getResource("/Picture/delete.png")));
		deleteBtn.setBounds(333, 218, 55, 54);
		deleteBtn.setEnabled(false);
		add(deleteBtn);
		
		JButton btnChangeTime = new JButton("");
		btnChangeTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateOrder(lblTime,AID,resAddress,orderDate,parent.getOrderTable()).setVisible(true);;
			}
		});
		btnChangeTime.setIcon(new ImageIcon(MyOrder.class.getResource("/Picture/calendar.png")));
		btnChangeTime.setBounds(354, 12, 34, 26);
		add(btnChangeTime);
		
		JButton okBtn = new JButton("");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0)
					return;
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				String status = (String)getOrderContentTable().getValueAt(row, 3); 
				if(!status.equals("Lock"));
				else{
					int success = c.OkLockOrder(AID, resAddress, foodname, orderDate);
					if(success != 0){
						getOrderContentTable().setValueAt("Delete", row, 3);
					}
					else{
						JOptionPane.showMessageDialog(null, "Nha hang khong ton tai");
					}
				}
			}
		});
		okBtn.setIcon(new ImageIcon(MyOrder.class.getResource("/Picture/ok.png")));
		okBtn.setBounds(199, 218, 55, 54);
		add(okBtn);
	}
	public void setAID(String AID){
		this.AID = AID;
	}
	public void setResAddress(String resAddress){
		this.resAddress = resAddress;
	}
	public void setOrderDate(String orderDate){
		this.orderDate = orderDate;
	}
	public String getOrderDate(){
		return(orderDate);
	}
	public String getAID(){
		return(AID);
	}
	public String getResAddress(){
		return(resAddress);
	}
	public JTable getOrderContentTable() {
		return orderContentTable;
	}
	public JButton getFixBtn() {
		return fixBtn;
	}
	public JButton getDeleteBtn() {
		return deleteBtn;
	}
}
