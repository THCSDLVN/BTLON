package Client.createorder;

import java.util.Calendar;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.JTable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import Client.check.Check;
import Client.clientprocess.ClientProcess;

public class CreateOrder extends JDialog {

	public JComboBox monthCbox = new JComboBox();
	public JComboBox dayCbox = new JComboBox();
	public JComboBox hourCbox = new JComboBox();
	public JComboBox minsCbox = new JComboBox();
	public JComboBox yearCbox = new JComboBox();
	
	public JLabel yearTxtLbl;
	public JLabel monthTxtLbl;
	public JLabel dayTxtLbl;
	public JLabel hourTxtLbl;
	public JLabel minTxtLbl;
	public JLabel dateLbl = new JLabel("Date");
	
	public JLabel lblMonth = new JLabel("Month");
	public JLabel lblDay = new JLabel("Day");
	public JLabel lblYear = new JLabel("Year");
	public JLabel hourLbl = new JLabel("Hour");
	public JLabel minsLbl = new JLabel("Minute");
	
	public JButton cancelBtn = new JButton("Cancel");
	public JButton okBtn = new JButton("Confirm");

	// Declare datetime variable
	public String year = new String("");
	public String month = new String("");
	public String day = new String("");
	public String hour = new String("");
	public String min = new String("");

	public Check check = new Check();

	public CreateOrder(JComboBox b) {
		
		initialize();

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(check.check_date(dayCbox,monthCbox,yearCbox)){
						month = (String)getMonth().getSelectedItem();
						day = (String)getDay().getSelectedItem();
						hour = (String)getHour().getSelectedItem();
						min = (String)getMin().getSelectedItem();
						year = (String)getYear().getSelectedItem();
						StringBuffer dateOrder = new StringBuffer("");
						dateOrder.append(year + "/" + month + "/" + day + " " + hour + ":" + min);
						if(!check.check_date(dateOrder.toString())){
							JOptionPane.showMessageDialog(null,"Day Must Be Withtin 1 Week","Announce",JOptionPane.ERROR_MESSAGE);
						}
						else{
							String datetime = new String(year+"-"+month+"-"+day+" "+hour+":"+min);
							b.addItem(datetime);
							dispose();
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Day Isn't Exists","Announce",JOptionPane.ERROR_MESSAGE);
					}
				}
				catch(Exception p){
					p.printStackTrace();
				}
			}
		});
	}

	public CreateOrder(ClientProcess clientProcess,String foodname,String aid,String resAddress,String oldTime,JTable parentOrderTable){
		initialize();

		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(check.check_date_before_after(oldTime)){
						if(check.check_date(dayCbox,monthCbox,yearCbox)){
							month = (String)getMonth().getSelectedItem();
							day = (String)getDay().getSelectedItem();
							hour = (String)getHour().getSelectedItem();
							min = (String)getMin().getSelectedItem();
							year = (String)getYear().getSelectedItem();
							StringBuffer dateOrder = new StringBuffer("");
							dateOrder.append(year + "/" + month + "/" + day + " " + hour + ":" + min);
							if(!check.check_date(dateOrder.toString())){
								JOptionPane.showMessageDialog(null,"Day Must Be Withtin 1 Week","Announce",JOptionPane.ERROR_MESSAGE);
							}
							else{
								String datetime = new String(year+"-"+month+"-"+day+" "+hour+":"+min+":00");
								if(clientProcess.lock == 0){
									do{
										;
									}
									while(!clientProcess.request.toString().equals(""));
									clientProcess.getRequestFromClient("updateDataQuery{Reservation~Time~" + datetime +"~AID = '" + aid + "' and ResAddress = '" + resAddress + "' and StatusReser = 'Updating' and Foodname = '" + foodname +"'}");
									do{
										if(clientProcess.lock == 1){
											clientProcess.setRequest();
											JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
											return ;
										}
										//Vong lap nay dung de cho den khi co ket qua
									}while(!clientProcess.request.toString().equals(""));
									String okUpdate = new String();
									okUpdate = clientProcess.getResultAlterQuery();
									clientProcess.setResultAlterQuery();
									
									if(!okUpdate.equals("0")){
										JOptionPane.showMessageDialog(null, "Change Successfully Order Date");
										parentOrderTable.setValueAt(datetime, parentOrderTable.getSelectedRow(), 0);
									}
									else{
										JOptionPane.showMessageDialog(null, "Change Unsuccessfully. Order Has Been Changed By Restaurant. Please Back And Come In Again");
									}
								}
								else{
									JOptionPane.showMessageDialog(null,"Server Has Crashed","Annouce",JOptionPane.WARNING_MESSAGE);
									return ;
								}
								dispose();
							}
						}
						else{
							JOptionPane.showMessageDialog(null,"Day Isn't Exists","Announce",JOptionPane.ERROR_MESSAGE);
							return ;
						}
					}
					else{
						JOptionPane.showMessageDialog(null,"Order Is Out Of Date. Can't Fix Anything");
						return ;
					}
				}
				catch(Exception p){
					p.printStackTrace();
				}
			}
		});
	}

	public void initialize(){
		setTitle("Create Order");
		setPreferredSize(new Dimension(560, 230));
		setResizable(false);
		pack();

		getContentPane().setLayout(null);
		getContentPane().add(okBtn);
		getContentPane().add(cancelBtn);
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(370, 180, 90, 27);

		okBtn.setBounds(110, 180, 90, 27);
		
		dateLbl.setBounds(20, 30, 26, 17);
		getContentPane().add(dateLbl);
		
		monthCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				month = (String)getMonth().getSelectedItem();
				monthTxtLbl.setText(month + " - ");
			}
		});
		monthCbox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		monthCbox.setMaximumRowCount(4);
		monthCbox.setBounds(270, 20, 79, 27);
		getContentPane().add(monthCbox);


		yearCbox.setModel(new DefaultComboBoxModel(new String[] {"2015", "2016",}));
		yearCbox.addItemListener(new ItemListener(){
			public void itemStateChanged(ItemEvent e){
				year = (String)getYear().getSelectedItem();
				yearTxtLbl.setText(year);
			}
		});
		yearCbox.setMaximumRowCount(4);
		yearCbox.setBounds(450, 20, 79, 27);
		getContentPane().add(yearCbox);

		dayCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				day = (String)getDay().getSelectedItem();
				dayTxtLbl.setText(day+" - ");
			}
		});
		dayCbox.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10","11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayCbox.setMaximumRowCount(4);
		dayCbox.setBounds(90, 20, 79, 27);
		getContentPane().add(dayCbox);
		
		lblMonth.setBounds(190, 30, 46, 17);
		getContentPane().add(lblMonth);
		
		lblDay.setBounds(20, 30, 30, 17);
		getContentPane().add(lblDay);

		lblYear.setBounds(380, 30, 40, 17);
		getContentPane().add(lblYear);

		yearTxtLbl = new JLabel((String)getYear().getSelectedItem(),SwingConstants.CENTER);
		yearTxtLbl.setBounds(188, 140, 40, 15);
		getContentPane().add(yearTxtLbl);
		
		monthTxtLbl = new JLabel((String)getMonth().getSelectedItem()+" - ",SwingConstants.CENTER);
		monthTxtLbl.setBounds(158, 140, 30, 15);
		getContentPane().add(monthTxtLbl);
		
		dayTxtLbl = new JLabel((String)getDay().getSelectedItem()+" - ");
		dayTxtLbl.setHorizontalAlignment(SwingConstants.CENTER);
		dayTxtLbl.setBounds(128, 140, 30, 15);
		getContentPane().add(dayTxtLbl);

		dateLbl.setBounds(79,140,49,15);
		getContentPane().add(dateLbl);

		hourCbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hour = (String)getHour().getSelectedItem();
				hourTxtLbl.setText(hour+" : ");
			}
		});
		hourCbox.setModel(new DefaultComboBoxModel(new String[] {"00", "01", "02", "03", "04", "05", "06", "07","08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		hourCbox.setMaximumRowCount(4);
		hourCbox.setBounds(90, 80, 79, 27);
		getContentPane().add(hourCbox);

		minsCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				min = (String)getMin().getSelectedItem();
				minTxtLbl.setText(min);
			}
		});
		minsCbox.setMaximumRowCount(4);
		minsCbox.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30"
																	, "35", "40", "45", "50", "55"}));
		minsCbox.setBounds(270, 80, 79, 27);
		getContentPane().add(minsCbox);
		
		hourLbl.setBounds(20, 90, 35, 17);
		getContentPane().add(hourLbl);

		minsLbl.setBounds(190, 90, 49, 17);
		getContentPane().add(minsLbl);
		
		hourTxtLbl = new JLabel((String)getHour().getSelectedItem()+" :");
		hourTxtLbl.setHorizontalAlignment(SwingConstants.CENTER);
		hourTxtLbl.setBounds(380, 90, 30, 15);
		getContentPane().add(hourTxtLbl);
		
		minTxtLbl = new JLabel((String)getMin().getSelectedItem());
		minTxtLbl.setHorizontalAlignment(SwingConstants.CENTER);
		minTxtLbl.setBounds(410, 90, 40, 15);
		getContentPane().add(minTxtLbl);
	}

	public JComboBox getMonth() {
		return monthCbox;
	}

	public JComboBox getDay() {
		return dayCbox;
	}

	public JComboBox getHour() {
		return hourCbox;
	}

	public JComboBox getMin() {
		return minsCbox;
	}

	public JComboBox getYear(){
		return yearCbox;
	}
}
