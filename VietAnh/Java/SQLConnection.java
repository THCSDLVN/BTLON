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
	public List<List<String>> getResInfo(String resName){
		Connection connect;
		PreparedStatement preStmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "select PhoneNumber from Account "
					+ "where AID = (select AID from Restaurant where Resname = ?)";
			preStmt = connect.prepareStatement(sql);
			preStmt.setString(1,resName);
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
	public void insertReservation(String AID,String ResAddress,String foodname,String orderDate,int quantity,double cost){
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
			String sql = "insert into Reservation value (?, ?, ?, ?, ?,'Updating',?)";
			stmt = connect.prepareStatement(sql);
			stmt.setString(1, AID);
			stmt.setString(2, ResAddress);
			stmt.setString(3, foodname);
			stmt.setString(4, orderDate);
			stmt.setInt(5, quantity);
			stmt.setDouble(6, cost);
			stmt.execute();
			connect.close();
			stmt.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public List<List<String>> getMyRes(String myAID){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("select ResID,Resname from Restaurant where AID = '"+myAID+"'");
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
	public List<List<String>> getThisFoodInfo(String resAddress,String foodName){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("select FoodName,Cost from Provide"
										+ "	where ResID = (select ResID from SequenceRestaurant where Address = '"+resAddress+"') and"
										+ "	FoodName = '"+foodName+"'");
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
	public List<List<String>> getMyOrderFromThisRes(String AID,String resAddress){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			String sql = "select distinct `Time` from `Reservation` "
					+ "where AID = '"+AID+"' and `ResAddress` = '"+resAddress+"'";
			result = stmt.executeQuery(sql);
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
	public List<List<String>> getFoodsFromThisOrder(String AID,String resAddress,String orderDate){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			String sql = "select Foodname,Quantity,Cost,StatusReser from `Reservation` "
						+ "where AID = '"+AID+"' and ResAddress = '"+resAddress+"' and `Time` = '"+orderDate+"'";
			result = stmt.executeQuery(sql);
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
	public int deleteFoodFromThisOrder(String AID,String ResAddress,String foodname,String orderDate){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "update `Reservation` set `StatusReser` = 'Delete' "
					+ "where AID = '"+AID+"' and `ResAddress` = '"+ResAddress+"' and `Time` = '"+orderDate+"' and `Foodname` = '"+foodname+"'";
			stmt = connect.createStatement();
			int TraVe = stmt.executeUpdate(sql);
			connect.close();
			stmt.close();
			return TraVe;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public int updateNewTime(String AID,String ResAddress,String oldTime,String newTime){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "Update `Reservation` "
					+ "set `Time` = '"+newTime+"' "
					+ "where AID = '"+AID+"' and `ResAddress` = '"+ResAddress+"' and `Time` = '"+oldTime+"' "
					+ "and `StatusReser` = 'Updating'";
			stmt = connect.createStatement();
			int TraVe = stmt.executeUpdate(sql);
			connect.close();
			stmt.close();
			return TraVe;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public List<List<String>> getMyOrderDateFromThisRes(String AID,String resAddress){
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		List<List<String>>a = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			String sql = "select distinct `Time` from `Reservation` "
					+ "where AID = '"+AID+"' and ResAddress = '"+resAddress+"'";
			result = stmt.executeQuery(sql);
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
	public int updateNewQuantity(String AID,String ResAddress,String foodName,String orderDate,int newQuantity){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		try{
			connect = DriverManager.getConnection(url,username,password);
			String sql = "Update `Reservation` set "
					+ "`Quantity` = '"+newQuantity+"' "
					+ "where AID = '"+AID+"' and `ResAddress` = '"+ResAddress+"' "
					+ "and `Time` = '"+orderDate+"' and `StatusReser` = 'Updating' and Foodname = '"+foodName+"'";
			stmt = connect.createStatement();
			int TraVe = stmt.executeUpdate(sql);
			connect.close();
			stmt.close();
			return TraVe;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
	public int DeleteThisRestaurant(String ResID){
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
			String sql1 = "delete from `Reservation` where "
					+ "ResAddress in (select Address from SequenceRestaurant where ResID = '"+ResID+"');";
			stmt = connect.prepareStatement(sql1);
			int TraVe = stmt.executeUpdate();
			String sql2 = "delete from `Provide` where ResID = '"+ResID+"';";
			stmt = connect.prepareStatement(sql2);
			TraVe *= stmt.executeUpdate();
			String sql3 = "delete from `SequenceRestaurant` where ResID = '"+ResID+"';";
			stmt = connect.prepareStatement(sql3);
			TraVe *= stmt.executeUpdate();
			String sql4 = "delete from `Restaurant` where ResID = '"+ResID+"';";
			stmt = connect.prepareStatement(sql4);
			TraVe *= stmt.executeUpdate();
			connect.close();
			stmt.close();
			return TraVe;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
public int CreateMyRestaurant(String AID,String resID,String resName,String resAddress){
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
		String sql1 = "insert into Restaurant value ('"+resID+"','"+resName+"','"+AID+"');";
		stmt = connect.prepareStatement(sql1);
		int TraVe = stmt.executeUpdate();
		String sql2 = "insert into SequenceRestaurant value('"+resAddress+"','"+resID+"');";
		stmt = connect.prepareStatement(sql2);
		TraVe *= stmt.executeUpdate();
		connect.close();
		stmt.close();
		return TraVe;
	}
	catch(Exception e){
		System.out.println(e.getMessage());
		return 0;
	}
}
}
