package Server.sqlfunc;

import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import Server.makearraylist.MakeArrayList;

public class SQLFunc implements SQLFuncInterface{

	public MakeArrayList make = new MakeArrayList();
	public List<String> listColumnNames = new ArrayList();
	
	public void connectToDBMS(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

	public SQLFunc(){
		connectToDBMS();
	}

	public void getColumnNamesList(ResultSet rs){
		try{
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			listColumnNames.clear();
			// The column count starts from 1
			for (int i = 1; i < columnCount + 1; i++ ) {
				listColumnNames.add(rsmd.getColumnName(i).toString());
				// Do stuff with name
			}
		}
		catch(Exception e){
			e.printStackTrace();
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
			buff.append(" inner join ");
			buff.append(stringResultArray[i]);
		}
		return buff.toString();
	}

	public String listColumnTreating(String tableName,String on){
		String tableNameResult[] = stringTokenizer(tableName);
		StringBuffer onKey = new StringBuffer();
		onKey.append(tableNameResult[0]);
		for(int i = 1 ; i < tableNameResult.length ; i++){
			onKey.append("." + on + " = " +  tableNameResult[i] + "." + on);
			if(i < tableNameResult.length - 1){
				onKey.append(" and " + tableNameResult[i]);
			}
		} 
		return onKey.toString();
	} 

	public List<List<String>> executeQueryCommand(String queryCommand){
		try{ 
			Connection connect = DriverManager.getConnection(url,username,password);
			Statement stmt = connect.createStatement();
			ResultSet resultSet = stmt.executeQuery(queryCommand);

			getColumnNamesList(resultSet);
			List<List<String>> resultList = new ArrayList();
			resultList = make.makeArrayList(resultSet);
			
			resultSet.close();			
			stmt.close();
			connect.close();


			return resultList;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int executeUpdateCommand(String updateCommand){
		try{ 
			Connection connect = DriverManager.getConnection(url,username,password);
			Statement stmt = connect.createStatement();
			int result = stmt.executeUpdate(updateCommand);
			
			stmt.close();
			connect.close();
			return result;
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public List<List<String>> dataQuery(String tableName, String listColumn, String on, String condition, String groupBy, String having){
		if(tableName.equals("") || listColumn.equals("") || !checkString(tableName,true)){
			return null;
		}
		if(!listColumn.equals("*")){
			if(!checkString(listColumn,true)){
				return null;
			}
		}
		
		String tableNameOut = tableNameTreating(tableName);
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("SELECT DISTINCT " + listColumn + " FROM " + tableNameOut);
		if(!on.equals("")){
			queryCommand.append(" ON " + on);
		}
		if(!condition.equals("")){
			queryCommand.append(" WHERE " + condition);
		}
		if(!groupBy.equals("")){
			queryCommand.append(" GROUP BY " + groupBy);
		}
		if(!having.equals("")){
			queryCommand.append(" HAVING " + having);
		}
		queryCommand.append(";");
		System.out.println(queryCommand.toString());
		return executeQueryCommand(queryCommand.toString());
	}

	public int deleteDataQuery(String tableName, String condition){
		if(tableName.equals("") || !checkString(tableName,false) || condition.equals("")){
			return 0;
		}
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("DELETE FROM " + tableName + " WHERE " + condition + " ;");

		System.out.println(queryCommand.toString());
		return executeUpdateCommand(queryCommand.toString());
	}

	public int updateDataQuery(String tableName, String setColumn, String setNewValue, String condition){
		if(tableName.equals("") || !checkString(tableName,false) || setColumn.equals("") || setNewValue.equals("")){
			return 0;
		}
		
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("UPDATE " + tableName + " SET " + setColumn + " = '" + setNewValue + "'");
		if(!condition.equals("")){
			queryCommand.append(" WHERE " + condition);
		}
		queryCommand.append(";");
		System.out.println(queryCommand.toString());

		return executeUpdateCommand(queryCommand.toString());
	}

	public int insertDataQuery(String tableName, String value1, String value2, String value3, String value4, String value5, String value6,String value7,String value8,String value9,String value10,String value11){
		if(tableName.equals("") || !checkString(tableName,false)){
			return 0;
		}
		
		StringBuffer queryCommand = new StringBuffer();
		queryCommand.append("INSERT INTO " + tableName + " VALUES ");


		if(tableName.equals("Restaurant")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "');");
		}
		else if(tableName.equals("Account")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value7 + "','" + value8 + "');");
		}
		else if(tableName.equals("FoodSet")){
			queryCommand.append("('" + value1 +"');");
		}
		else if(tableName.equals("Provide")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "');");
		}
		else if(tableName.equals("Reservation")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 + "','" + value7 +"','" + value8 + "','" + value9 +"','" + value10 +"','" + value11 +"');");
		}
		else if(tableName.equals("SequenceRestaurant")){
			queryCommand.append("('" + value1 + "','" + value2 + "');");
		}
		else if(tableName.equals("Permisions")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 +"');");
		}
		else if(tableName.equals("PermisionsAssignment")){
			queryCommand.append("('" + value1 + "','" + value2 +"');");
		}
		else if(tableName.equals("Objects")){
			queryCommand.append("('" + value1 + "','" + value2 +"');");
		}
		else if(tableName.equals("AccountAssignment")){
			queryCommand.append("('" + value1 + "','" + value2 +"');");
		}
		else if(tableName.equals("Roles")){
			queryCommand.append("('" + value1 + "','" + value2 +"');");
		}
		else if(tableName.equals("Operations")){
			queryCommand.append("('" + value1 + "','" + value2 + "','" + value3 + "','" + value4 + "','" + value5 + "','" + value6 +"');");
		}

		System.out.println(queryCommand.toString());

		return executeUpdateCommand(queryCommand.toString());
	}
}