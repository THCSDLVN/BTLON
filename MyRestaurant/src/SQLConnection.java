import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.PreparedStatement;

public class SQLConnection {
	private static String url = "jdbc:mysql://localhost:3306/BT_LON";
	private static String username = "root";
	private static String password = "26091995";
	public  List<List<String>> resultList = null;
	public void logIn(String usr,String pass) {
		try{
				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		//List<List<String>> resultList = new ArrayList<>(); // Mang cac List, moi 1 hang la 1 list.
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM Account WHERE UserName = '"+usr+"' and Password = '"+pass+"'");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			resultList = new ArrayList<>();
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
	}
	public void getRes() {
		try{
				Class.forName("com.mysql.jdbc.Driver");
				//System.out.println("Load JDBC Driver Complete ...\n");
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		Connection connect;
		Statement stmt;
		ResultSet result;
		ResultSetMetaData metadata;
		int numcols;
		try{
			connect = DriverManager.getConnection(url,username,password);
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT RestaurantName FROM Restaurant");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			resultList = new ArrayList<>();
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
	}
}
