package Client.createorder;

import java.util.Calendar;
import java.util.logging.SimpleFormatter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.sql.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.awt.event.ItemListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class CreateOrder extends JDialog {

	public final JPanel contentPanel = new JPanel();

	public JComboBox monthCbox = new JComboBox();
	public JComboBox dayCbox = new JComboBox();
	public JComboBox hourCbox = new JComboBox();
	public JComboBox minsCbox = new JComboBox();
	
	public JLabel yeahLbl;
	public JLabel monthTxtLbl;
	public JLabel dayTxtLbl;
	public JLabel hourTxtLbl;
	public JLabel minTxtLbl;
	public JLabel dateLbl = new JLabel("Date :");
	public JLabel lblMonth = new JLabel("Month :");
	public JLabel lblDay = new JLabel("Day :");
	public JLabel hourLbl = new JLabel("Hour :");
	public JLabel minsLbl = new JLabel("Minutes :");
	
	public JButton cancelBtn = new JButton("Cancel");
	public JButton okBtn = new JButton("Confirm");

	// Declare datetime variable
	public String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
	public String month = new String("");
	public String day = new String("");
	public String hour = new String("");
	public String min = new String("");

	public CreateOrder(JComboBox b) {
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setPreferredSize(new Dimension(350, 190));
		setResizable(false);
		pack();

		contentPanel.setLayout(null);
		
		
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelBtn.setBounds(128, 152, 117, 25);
		contentPanel.add(cancelBtn);
		
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					month = (String)getMonth().getSelectedItem();
					day = (String)getDay().getSelectedItem();
					hour = (String)getHour().getSelectedItem();
					min = (String)getMin().getSelectedItem();
					String datetime = new String(year+"-"+month+"-"+day+" "+hour+":"+min);
					b.addItem(datetime);
					dispose();
				}
				catch(Exception p){
					p.printStackTrace();
				}
			}
		});
		okBtn.setBounds(138, 115, 97, 25);
		contentPanel.add(okBtn);
		
		dateLbl.setBounds(65, 90, 49, 15);
		contentPanel.add(dateLbl);
		
		monthCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				month = (String)getMonth().getSelectedItem();
				monthTxtLbl.setText(month+"-");
			}
		});
		monthCbox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		monthCbox.setMaximumRowCount(4);
		monthCbox.setBounds(128, 12, 49, 24);
		contentPanel.add(monthCbox);
		
		lblMonth.setBounds(65, 17, 57, 15);
		contentPanel.add(lblMonth);
		
		lblDay.setBounds(186, 17, 42, 15);
		contentPanel.add(lblDay);
		
		dayCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				day = (String)getDay().getSelectedItem();
				dayTxtLbl.setText(day);
			}
		});
		dayCbox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","11", "12", "13", "14", "15", "16", "17", "19", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		dayCbox.setMaximumRowCount(4);
		dayCbox.setBounds(262, 12, 62, 24);
		contentPanel.add(dayCbox);
		
		hourLbl.setBounds(65, 48, 57, 15);
		contentPanel.add(hourLbl);
		
		hourCbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hour = (String)getHour().getSelectedItem();
				hourTxtLbl.setText(hour+" : ");
			}
		});
		hourCbox.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7","8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		hourCbox.setMaximumRowCount(4);
		hourCbox.setBounds(128, 48, 49, 24);
		contentPanel.add(hourCbox);
		
		minsLbl.setBounds(186, 48, 70, 15);
		contentPanel.add(minsLbl);
	
		minsCbox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				min = (String)getMin().getSelectedItem();
				minTxtLbl.setText(min);
			}
		});
		minsCbox.setMaximumRowCount(4);
		minsCbox.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30"
																	, "35", "40", "45", "50", "55"}));
		minsCbox.setBounds(262, 48, 62, 24);
		contentPanel.add(minsCbox);
		
		yeahLbl = new JLabel(year+" - ",JLabel.CENTER);
		yeahLbl.setBounds(128, 90, 49, 15);
		contentPanel.add(yeahLbl);
		
		monthTxtLbl = new JLabel((String)getMonth().getSelectedItem()+" - ",SwingConstants.LEFT);
		monthTxtLbl.setBounds(174, 90, 38, 15);
		contentPanel.add(monthTxtLbl);
		
		dayTxtLbl = new JLabel((String)getDay().getSelectedItem()+" ");
		dayTxtLbl.setHorizontalAlignment(SwingConstants.LEFT);
		dayTxtLbl.setBounds(196, 90, 49, 15);
		contentPanel.add(dayTxtLbl);
		
		hourTxtLbl = new JLabel((String)getHour().getSelectedItem()+" :");
		hourTxtLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		hourTxtLbl.setBounds(247, 90, 38, 15);
		contentPanel.add(hourTxtLbl);
		
		minTxtLbl = new JLabel((String)getMin().getSelectedItem());
		minTxtLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		minTxtLbl.setBounds(272, 90, 39, 15);
		contentPanel.add(minTxtLbl);
	}

	private Boolean check_date(JComboBox day,JComboBox month,JComboBox year){
		int[] monthdays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
		int iday = day.getSelectedIndex()+1;
		int imonth = month.getSelectedIndex();
		int iyear = year.getSelectedIndex()+1920;
		monthdays[1] = (iyear%4 == 0)? 29 : 28;
		if(iday<=monthdays[imonth]){
			return true;
		}
		else {
			return false;
		}
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
}
