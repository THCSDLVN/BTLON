package Client.check;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.Calendar;

public class Check {
	
	public boolean check_date(JComboBox day,JComboBox month,JComboBox year){	// Ham Kiem Tra Xem Ngay Co Ton Tai Khong
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

	public boolean check_date(String date){			//Ham Kiem Tra Ngay Doi Chieu Voi Thoi Gian He Thong
		try{
			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			Date order = format.parse(date);

			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.SECOND, 0);
			cal1.set(Calendar.MILLISECOND, 0);
			
			Date cur = cal1.getTime();

			if(order.compareTo(cur) == 0){
				return true;
			}
			else{
				if(order.before(cur)){
					return false;
				}
				else{
					Calendar cal2 = Calendar.getInstance();
					cal2.set(Calendar.SECOND, 0);
					cal2.set(Calendar.MILLISECOND, 0);
					cal2.add(Calendar.DATE, +7);
					if(order.after(cal2.getTime())){
						return false;
					}
					else{
						return true;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean check_date_before_after(String date){
		try{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date orderDate = format.parse(date);

			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.SECOND, 0);
			cal1.set(Calendar.MILLISECOND, 0);

			Date cur = cal1.getTime();

			if(orderDate.compareTo(cur) == 0){
				return true;
			}
			if(orderDate.before(cur)){
				return false;
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public boolean check_text(String text){		//Ham Kiem Tra Du Lieu Text
		if(text.length() > 0 && text.length() <= 45){
			if (text.matches("[A-Za-z0-9 ]+")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}

	public boolean checkRetypePassword(JPasswordField typ,JPasswordField retyp){	//Ham Kiem Tra RetypePassWord
		String typPass = new String(typ.getPassword());
		String retypPass = new String(retyp.getPassword());
		if (retypPass.equals(typPass) && !retypPass.isEmpty()){
			return true;
		}
		else{ 
			return false;
		}
	}

	public boolean check_pass(JPasswordField passwd){
		String pass = new String(passwd.getPassword());
		return check_text(pass);
	}
	
	public boolean checkPhoneNumber(JTextField PhoneField){		//Ham Kiem Tra So Dien Thoai
		String phonenum = new String(PhoneField.getText());
		if(phonenum.length() > 0 && phonenum.length() <= 13){
			if(phonenum.matches("[0-9]+")){
				return true;
			}
			else{
				return false;
			}
		}
		else{
			return false;
		}
	}
}