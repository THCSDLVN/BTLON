import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class SQLConnection {
	private static String url = "jdbc:mysql://localhost:3306/BT_LON";
	private static String username = "root";
	private static String password = "26091995";
	
	public List<List<String>> getRes(){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("select Resname,Address from Restaurant natural join SequenceRestaurant");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					//System.out.print(result.getString(i) + " ");
					row.add(result.getString(i++));
				}
				//System.out.println();
				a.add(row); // add it to the result
			}
			connect.close();
			stmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return a;
	}
	public List<List<String>> getAcc(String usr,String pass){
		Connection connect;
		PreparedStatement preStmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "select * from Account where Username = ? and `Password` = ?";
			preStmt = connect.prepareStatement(sql);
			preStmt.setString(1, usr);
			preStmt.setString(2, pass);
			result = preStmt.executeQuery();
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					//System.out.print(result.getString(i) + " ");
					row.add(result.getString(i++));
				}
				//System.out.println();
				a.add(row); // add it to the result
			}
			connect.close();
			preStmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return a;
	}
	public List<List<String>> getResInfo(String resAddress){
		Connection connect;
		PreparedStatement preStmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "select PhoneNumber from Account "
					+ "where AID = (select AID from SequenceRestaurant where Address = ?)";
			preStmt = connect.prepareStatement(sql);
			preStmt.setString(1,resAddress);
			result = preStmt.executeQuery();
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					row.add(result.getString(i++));
				}
				a.add(row); // add it to the result
			}
			connect.close();
			preStmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return a;
	}
	public List<List<String>> getFoodFromRes(String ResAddress){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>> resultList = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("select FoodName,Cost from Provide "
					+ "where ResID = (select ResID from SequenceRestaurant where Address = '"+ResAddress+"')");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					row.add(result.getString(i++));
				}
				resultList.add(row); // add it to the result
			}
			connect.close();
			stmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return resultList;
	}
	public void insertReservation(String AID,String ResAddress,String foodname,String orderDate,int quantity){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		PreparedStatement stmt;
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "insert into Reservation value (?, ?, ?, ?, ?,0)";
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, AID);
			stmt.setString(2, ResAddress);
			stmt.setString(3, foodname);
			stmt.setString(4, orderDate);
			stmt.setInt(5, quantity);
			stmt.execute();
			connect.close();
			stmt.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
