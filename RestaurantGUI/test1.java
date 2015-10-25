package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class test1 extends JFrame {

	private JPanel contentPane;
	private JTable foodtb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test1 frame = new test1();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private String ResID = "RES0002";
	private SqlArrayList RequestList = null;
	private String Requestquery = "SELECT FullName,Facebook,PhoneNumber,Time,FoodList FROM reservations natural join account WHERE RID = '" + ResID + "' ORDER BY(Time) DESC";
	private Statement stmt;
	private Connection conn = null;
	/**
	 * Create the frame.
	 */
	public test1() {
		try{
			conn = SQLConnection.DBConnect();
			stmt = conn.createStatement();
			RequestList = new SqlArrayList(stmt.executeQuery(Requestquery));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane RequestPane = new JLayeredPane();
		RequestPane.setBounds(10, 35, 774, 361);
		contentPane.add(RequestPane);
		RequestPane.setLayout(null);
		
		JPanel RQListPn = new JPanel();
		RQListPn.setBounds(0, 0, 361, 361);
		RequestPane.add(RQListPn);
		RQListPn.setLayout(null);
		
		JLabel label = new JLabel("REQUEST FROM CUSTOMERS");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(0, 0, 360, 40);
		RQListPn.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 39, 361, 322);
		RQListPn.add(scrollPane);
		
		JList RQlist = new JList();
		RQlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					JList list = (JList) e.getSource();
					int index = list.getSelectedIndex();
					String[] info = RequestList.getRow(index);
					String foodlist = new String(info[4]);
					foodlist = foodlist.replace(",", "','");
					float totalcost = 0;
					SqlArrayList foodinfo = new SqlArrayList(stmt.executeQuery("SELECT FoodName,Cost FROM provide natural join menu natural join restaurant WHERE FID IN ('" + foodlist + "') AND RID ='" + ResID + "'"));
					
					JPanel pn = (JPanel) RequestPane.getComponent(1);
					JLabel name = (JLabel) pn.getComponent(8);
					JLabel phone = (JLabel) pn.getComponent(9);
					JLabel face = (JLabel) pn.getComponent(10);
					JLabel time = (JLabel) pn.getComponent(11);
					JLabel cost = (JLabel) pn.getComponent(12);
					JTable food = (JTable)((JScrollPane)pn.getComponent(4)).getViewport().getView();
					DefaultTableModel tbmodel =(DefaultTableModel) food.getModel();
					name.setText(info[0]);
					face.setText(info[1]);
					phone.setText(info[2]);
					time.setText(info[3]);
					tbmodel.setRowCount(0);
					for(int i=0; i < foodinfo.getRownumber(); i++)
					{
						tbmodel.addRow(foodinfo.getRow(i));
						totalcost += Float.valueOf(foodinfo.getRow(i)[1]);
					}
					cost.setText(Float.toString(totalcost));
					foodinfo.close();
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}			
			}
		});
		RQlist.setFont(new Font("Tahoma", Font.PLAIN, 25));
		RQlist.setModel(new AbstractListModel() {
			String[] values = RequestList.getColumn(0);
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPane.setViewportView(RQlist);
		
		JPanel RQinfoPn = new JPanel();
		RQinfoPn.setBounds(371, 0, 403, 361);
		RequestPane.add(RQinfoPn);
		RQinfoPn.setLayout(null);
		
		JLabel lblRequestInfo = new JLabel("REQUEST INFO");
		lblRequestInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblRequestInfo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRequestInfo.setBounds(0, 0, 403, 40);
		RQinfoPn.add(lblRequestInfo);
		
		JLabel lblCusname = new JLabel("Customer name :");
		lblCusname.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCusname.setBounds(10, 51, 106, 25);
		RQinfoPn.add(lblCusname);
		
		JLabel lblPhoneNum = new JLabel("Phone num :");
		lblPhoneNum.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhoneNum.setBounds(10, 87, 106, 25);
		RQinfoPn.add(lblPhoneNum);
		
		JLabel lblFacebook = new JLabel("Facebook :");
		lblFacebook.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFacebook.setBounds(10, 123, 106, 25);
		RQinfoPn.add(lblFacebook);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 184, 372, 120);
		RQinfoPn.add(scrollPane_1);
		
		foodtb = new JTable();
		foodtb.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Food", "Cost"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		foodtb.getColumnModel().getColumn(0).setResizable(false);
		foodtb.getColumnModel().getColumn(1).setResizable(false);
		scrollPane_1.setViewportView(foodtb);
		
		JButton btnNewButton = new JButton("Serve");
		btnNewButton.setBounds(293, 310, 89, 40);
		RQinfoPn.add(btnNewButton);
		
		JLabel lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTime.setBounds(10, 159, 106, 25);
		RQinfoPn.add(lblTime);
		
		JLabel lblTotal = new JLabel("Total :");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(10, 315, 40, 25);
		RQinfoPn.add(lblTotal);
		
		JLabel lblNameinfo = new JLabel("");
		lblNameinfo.setBounds(126, 51, 267, 25);
		lblNameinfo.setToolTipText(lblNameinfo.getText());
		RQinfoPn.add(lblNameinfo);
		
		JLabel lblAPhoneNumber = new JLabel("");
		lblAPhoneNumber.setBounds(126, 87, 267, 25);
		RQinfoPn.add(lblAPhoneNumber);
		
		JLabel lblAFacebookAddress = new JLabel("");
		lblAFacebookAddress.setBounds(126, 123, 267, 25);
		lblAFacebookAddress.setToolTipText(lblAFacebookAddress.getText());
		RQinfoPn.add(lblAFacebookAddress);
		
		JLabel lblTimeinfo = new JLabel("");
		lblTimeinfo.setBounds(126, 159, 267, 25);
		RQinfoPn.add(lblTimeinfo);
		
		JLabel lblAmountOfCost = new JLabel("4800000.0");
		lblAmountOfCost.setBounds(49, 315, 160, 25);
		RQinfoPn.add(lblAmountOfCost);
	}
}
