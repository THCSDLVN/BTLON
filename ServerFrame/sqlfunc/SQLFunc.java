package sqlfunc;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.StringTokenizer;

import tableforresult.TableForResult;

public class SQLFunc implements SQLFuncInterface{
	Connection connect;
	Statement stmt;
	ResultSet result;
	
	public void connectToDBMS(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public void getStatement(){
		try{ 
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
		}
		catch(SQLException sqle){
			sqle.getMessage();
		}
	}

	public boolean checkString(String input, boolean flag){
		if(!input.equals("")){ 
			int i,counter = 0;
			char value;
			for(i = 0 ; i < input.length() ; i++){
				value = input.charAt(i);
				if(flag == true){
					if(standardCharacter1.indexOf(value) < 0 && standardCharacter2.indexOf(value) < 0 && standardCharacter3.indexOf(value) < 0 && standardCharacter4.indexOf(value) < 0){
						return false;
					}
				}
				else{
					if(standardCharacter1.indexOf(value) < 0 && standardCharacter2.indexOf(value) < 0 && standardCharacter3.indexOf(value) < 0){
						return false;
					}
				}
			}
			if(flag == true){
				for(i = 0 ; i < input.length() ; i++){
					if(input.charAt(i) == ','){
						counter ++;
						if(i == input.length() - 1 || i == 0){
							return false;
						}
						if(i < input.length() - 1 && input.charAt(i + 1) == ','){
							return false;
						}
					}
				}
				if(counter == 0 && input.length() > 32){
					return false;
				}
			}
		}
		return true;
	}

	public String[] stringTokenizer(String input){
		int i,counter = 0;
		StringTokenizer token = new StringTokenizer(input,",");
		for(i = 0 ; i < input.length() ; i++){
			if(input.charAt(i) == ','){
				counter++;
			}
		}
		String stringResultArray[] = new String[counter + 1];
		counter = 0;
		while(token.hasMoreTokens()){
			stringResultArray[counter++] = new String(token.nextToken());
		}
		return stringResultArray;
	}

	public String tableNameTreating(String input){	
		StringBuffer buff = new StringBuffer("");
		String stringResultArray[];
		stringResultArray = stringTokenizer(input);
		buff.append(stringResultArray[0]);
		for(int i = 1 ; i < stringResultArray.length ; i++){
			buff.append(" natural join ");
			buff.append(stringResultArray[i]);
		}
		return buff.toString();
	}

	public ResultSet executeQueryCommand(String queryCommand){
		try{ 
			return stmt.executeQuery(queryCommand);
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int executeUpdateCommand(String updateCommand){
		try{ 
			return stmt.executeUpdate(updateCommand); 
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public ResultSet dataQuery(String tableName, String listColumn, String condition, String groupBy, String having, String orderBy, String arrangement){
		if(tableName.equals("") || listColumn.equals("") || !checkString(tableName,true)){
			return null;
		}
		if(!listColumn.equals("*")){
			if(!checkString(listColumn,true)){
				return null;
			}
		}

		connectToDBMS();
		getStatement();
		
		String tableNameOut = tableNameTreating(tableName);
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("SELECT " + listColumn + " FROM " + tableNameOut);
		if(!condition.equals("")){
			queryCommand.append(" WHERE " + condition);
		}
		if(!groupBy.equals("")){
			queryCommand.append(" GROUP BY " + groupBy);
		}
		if(!having.equals("")){
			queryCommand.append(" HAVING " + having);
		}
		if(!orderBy.equals("")){
			queryCommand.append(" ORDER BY " + orderBy);
		}
		if(!arrangement.equals("")){
			queryCommand.append(" " + arrangement);
		}
		queryCommand.append(";");
	
		return executeQueryCommand(queryCommand.toString());
	}

	public int deleteDataQuery(String tableName, String condition){
		if(tableName.equals("") || !checkString(tableName,false) || condition.equals("")){
			return 0;
		}
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("DELETE FROM " + tableName + " WHERE " + condition + " ;");

		connectToDBMS();
		getStatement();
		return executeUpdateCommand(queryCommand.toString());
	}

	public int updateDataQuery(String tableName, String setColumn, String setNewValue, String condition){
		if(tableName.equals("") || !checkString(tableName,false) || setColumn.equals("") || setNewValue.equals("")){
			return 0;
		}
		connectToDBMS();
		getStatement();
		
		StringBuffer queryCommand = new StringBuffer();
		if(tableName.equals("Account")){
			if(setColumn.equals("UserName")){
				if(!checkDataInsertForAccount(setNewValue,"")){
					return 0;
				}
			}
			else if(setColumn.equals("PhoneNumber")){
				if(!checkDataInsertForAccount("",setNewValue)){
					return 0;
				}
			}
		}
		else if(tableName.equals("Restaurant")){
			if(setColumn.equals("RestaurantName")){
				if(!checkDataInsertForRestaurant(setNewValue,"","","")){
					return 0;
				}
			}
			else if(setColumn.equals("Address")){
				if(!checkDataInsertForRestaurant("",setNewValue,"","")){
					return 0;
				}
			}
			else if(setColumn.equals("PhoneNumber")){
				if(!checkDataInsertForRestaurant("","",setNewValue,"")){
					return 0;
				}
			}
			else if(setColumn.equals("ResName")){
				if(!checkDataInsertForRestaurant("","","",setNewValue)){
					return 0;
				}
			}
		}
		else if(tableName.equals("Menu")){
			if(setColumn.equals("FoodName")){
				if(!checkDataInsertForMenu(setNewValue)){
					return 0;
				}
			}
		}
		queryCommand.append("UPDATE " + tableName + " SET " + setColumn + " = '" + setNewValue + "'");
		if(!condition.equals("")){
			queryCommand.append(" WHERE " + condition);
		}
		queryCommand.append(";");

		return executeUpdateCommand(queryCommand.toString());
	}

	public int insertDataQuery(String tableName, String value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8){
		if(tableName.equals("") || !checkString(tableName,false)){
			return 0;
		}
		connectToDBMS();
		getStatement();
		
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("INSERT INTO " + tableName + " VALUE ");


		if(tableName.equals("Restaurant")){
			if(!checkDataInsertForRestaurant(value2,value3,value4,value7) || value1.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value7 + "','" + value8 + "');");
		}
		else if(tableName.equals("Account")){
			if(!checkDataInsertForAccount(value3,value5) || value1.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "');");
		}
		else if(tableName.equals("Menu")){
			if(!checkDataInsertForMenu(value2)  || value1.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "');");
		}
		else if(tableName.equals("Provide")){
			if(value1.equals("") || value2.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "');");
		}
		else if(tableName.equals("Reservations")){
			if(value1.equals("") || value2.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "')");
		}
		else if(tableName.equals("FoodShip")){
			if(value1.equals("") || value2.equals("")){
				return 0;
			}
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "')");
		}

		return executeUpdateCommand(queryCommand.toString());
	}

	public boolean checkDataInsertForRestaurant(String restaurantName, String address, String phoneNumber, String resName){
		int rows = 0,counter = 0;
		try{
			result = executeQueryCommand("SELECT * FROM Restaurant;");
			if(result.last()){
				rows = result.getRow();
				result.beforeFirst();
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}

		String restaurantNameArray[] = new String[rows];
		String addressArray[] = new String[rows];
		String phoneNumberArray[] = new String[rows];
		String resNameArray[] = new String[rows];
		
		try{ 
			while(result.next()){
				restaurantNameArray[counter] = result.getString("RestaurantName");
				addressArray[counter] = result.getString("Address");
				phoneNumberArray[counter] = result.getString("PhoneNumber");
				resNameArray[counter++] = result.getString("ResName");
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}

		for(counter = 0 ; counter < rows ; counter ++){
			if((!restaurantName.equals("") && restaurantName.equals(restaurantNameArray[counter])) || (!address.equals("") && address.equals(addressArray[counter])) || (!phoneNumber.equals("") && phoneNumber.equals(phoneNumberArray[counter])) || (!resName.equals("") && resName.equals(resNameArray[counter]))){
				return false;
			}
		}

		return true;
	}
	
	public boolean checkDataInsertForAccount(String userName, String phoneNumber){
		int rows = 0,counter = 0;
		try{ 
			result = executeQueryCommand("SELECT * FROM Account;");
			if(result.last()){
				rows = result.getRow();
				result.beforeFirst();
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}

		String userNameArray[] = new String[rows];
		String phoneNumberArray[] = new String[rows];

		try{ 
			while(result.next()){
				userNameArray[counter] = result.getString("UserName");
				phoneNumberArray[counter++] = result.getString("PhoneNumber");
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}

		for(counter = 0 ; counter < rows ; counter ++){
			if((!userName.equals("") && userName.equals(userNameArray[counter])) || (!phoneNumber.equals("") && phoneNumber.equals(phoneNumberArray[counter]))){
				return false;
			}
		}
		return true;	
	}
	
	public boolean checkDataInsertForMenu(String foodName){
		int rows = 0,counter = 0;
		try{ 
			result = executeQueryCommand("SELECT * FROM Menu;");
			if(result.last()){
				rows = result.getRow();
				result.beforeFirst();
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}

		String foodNameArray[] = new String[rows];

		try{
			while(result.next()){
				foodNameArray[counter++] = result.getString("FoodName");
			}
		}
		catch(SQLException sqle){
			System.out.println(sqle.getMessage());
		}
		
		for(counter = 0 ; counter < rows ; counter ++){
			if((!foodName.equals("") && foodName.equals(foodNameArray[counter]))){
				return false;
			}
		}
		return true;
	} 
}