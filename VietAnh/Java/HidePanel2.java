import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class HidePanel2 extends JPanel {
	private JTable OrdersTable;
	private String resAddress = "";
	
	public SQLConnection c = new SQLConnection();
	/**
	 * Create the panel.
	 */
	public HidePanel2(AccountGUI aGUI,Border b,String AID) {
		this.setBounds(12, 300, 400, 284);
		this.setBorder(b);
		setLayout(null);
		
		JLabel yourOrderlbl = new JLabel("Your Order");
		yourOrderlbl.setHorizontalAlignment(SwingConstants.CENTER);
		yourOrderlbl.setFont(new Font("FreeSans", Font.BOLD | Font.ITALIC, 18));
		yourOrderlbl.setBounds(12, 12, 376, 34);
		add(yourOrderlbl);
		
		JLabel annouceLbl = new JLabel("You have ? deals (? are accepted) !!!");
		annouceLbl.setHorizontalAlignment(SwingConstants.CENTER);
		annouceLbl.setFont(new Font("Dialog", Font.ITALIC, 12));
		annouceLbl.setBounds(12, 248, 376, 24);
		add(annouceLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 59, 376, 144);
		add(scrollPane);
		
		OrdersTable = new JTable();
		scrollPane.setViewportView(OrdersTable);
		
		JButton viewOrderBtn = new JButton("View");
		viewOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = OrdersTable.getSelectedRow();
				if(index == -1);
				else{
					String orderDate = (String)OrdersTable.getValueAt(index, 0);
					MyOrder x = aGUI.getOrderViewPanel();
					x.setAID(AID);// truyen AID vao giao dien MyOrder
					x.setResAddress(resAddress);// Truyen dia chi nha hang vao giao dien MyOrder
					x.setOrderDate(orderDate);// Truyen ngay order vao dia chi nha hang vao giao dien MyOrder
					x.setBorder(getBorder());
					x.lblTime.setText("Time : "+orderDate);
					x.setVisible(true);
					JTable temp = x.getOrderContentTable();// temp la bang cac mon an cua Order minh chon.
					//SQL :select Foodname,Quantity,Cost,StatusReser from `Reservation` "
					//+ "where AID = '"+AID+"' and ResAddress = '"+resAddress+"' and `Time` = '"+orderDate+"'
					List<List<String>> foodsFromThisOrder = c.getFoodsFromThisOrder(AID, resAddress, orderDate);
					String[][] dataFromThisOrder = SolveArrayList.ConvertFromArrayList(foodsFromThisOrder);
					DefaultTableModel model = new DefaultTableModel(dataFromThisOrder, new String[]{"Foodname","Quantity","Cost","Status"});
					temp.setModel(model);
					setVisible(false);
				}
			}
		});
		viewOrderBtn.setBounds(165, 215, 73, 25);
		add(viewOrderBtn);
	}
	public JTable getOrderTable() {
		return OrdersTable;
	}
	public void setResAddress(String resAddress){
		this.resAddress = resAddress;
	}
}
