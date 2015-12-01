package Client.hidepanel2;

import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.List;
import java.util.ArrayList;

import Client.accountGUI.AccountGUI;
import Client.clientprocess.ClientProcess;
import Client.solvearraylist.SolveArrayList;
import Client.myorder.MyOrder;

public class HidePanel2 extends JPanel {
	public JTable orderTable = new JTable();
	
	public String resAddress = "";
	
	public JLabel yourOrderlbl = new JLabel("Your Order");
	
	public JScrollPane scrollPane = new JScrollPane();
	
	public JButton viewOrderBtn = new JButton("View");

	public HidePanel2(AccountGUI aGUI, Border b, String aid, ClientProcess clientProcess) {
		this.setBounds(12, 300, 400, 284);
		this.setBorder(b);
		setLayout(null);
		
		yourOrderlbl.setHorizontalAlignment(SwingConstants.CENTER);
		yourOrderlbl.setFont(new Font("FreeSans", Font.BOLD | Font.ITALIC, 18));
		yourOrderlbl.setBounds(12, 12, 376, 34);
		add(yourOrderlbl);
		
		scrollPane.setBounds(12, 59, 376, 144);
		add(scrollPane);
		
		scrollPane.setViewportView(orderTable);
		
		viewOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(clientProcess.lock == 0){
					int index = orderTable.getSelectedRow();
					if(index == -1){
						;
					}
					else{
						String orderDate = (String)orderTable.getValueAt(index, 0);
						MyOrder x = aGUI.getOrderViewPanel();
						x.setAID(aid);					// truyen AID vao giao dien MyOrder
						x.setResAddress(resAddress);	// Truyen dia chi nha hang vao giao dien MyOrder
						x.setOrderDate(orderDate);		// Truyen ngay order vao dia chi nha hang vao giao dien MyOrder
						x.setBorder(getBorder());
						x.lblTime.setText("Time : "+orderDate);
						x.setVisible(true);
						JTable temp = x.getOrderContentTable();	// temp la bang cac mon an cua Order minh chon.
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("dataQuery{Reservation~Foodname,Quantity,Cost,StatusReser~\"\"~AID = '" + aid + "' and ResAddress = '" + resAddress + "' and Time = '" + orderDate + "'~\"\"~\"\"}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));

						List<List<String>> foodsFromThisOrder = new ArrayList();
						foodsFromThisOrder = clientProcess.getResultList();
						clientProcess.setResultList();
						
						String[][] dataFromThisOrder = SolveArrayList.ConvertFromArrayList(foodsFromThisOrder);
						DefaultTableModel model = new DefaultTableModel(dataFromThisOrder, new String[]{"Foodname","Quantity","Cost","Status"});
						temp.setModel(model);
						setVisible(false);
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
					return ;
				}
			}
		});
		viewOrderBtn.setBounds(165, 215, 73, 25);
		add(viewOrderBtn);
	}
	
	public JTable getOrderTable() {
		return orderTable;
	}

	public void setResAddress(String resAddress){
		this.resAddress = resAddress;
	}
}