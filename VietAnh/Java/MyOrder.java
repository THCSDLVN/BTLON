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

public class MyOrder extends JPanel {
	private JTable orderContentTable;
	private String AID = "";
	private String resAddress = "";
	private String orderDate = "";
	
	public JLabel lblTime;
	
	public SQLConnection c = new SQLConnection();
	public int row; // Dong` dc chon trong bang Cac mon an trong 1 order.
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
		scrollPane.setViewportView(orderContentTable);
		
		JButton fixBtn = new JButton("");
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
		add(fixBtn);
		
		JButton deleteBtn = new JButton("");
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				row = getOrderContentTable().getSelectedRow();
				if(row < 0)
					return;
				String foodname = (String)getOrderContentTable().getValueAt(row, 0);
				String status = (String)getOrderContentTable().getValueAt(row, 3);
				if(status.equals("Delete")){
					JOptionPane.showMessageDialog(null,"Your "+foodname+" is being waiting for deleting");
				}
				else{
					if(status.equals("Lock")){
						JOptionPane.showMessageDialog(null, "Your "+foodname+" is locked");
					}
					else{
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
				}	
			}
		});
		deleteBtn.setIcon(new ImageIcon(MyOrder.class.getResource("/Picture/delete.png")));
		deleteBtn.setBounds(333, 218, 55, 54);
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
}
