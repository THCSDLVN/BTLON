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

public class SQLConnection {
	private static String url = "jdbc:mysql://localhost:3306/db_lab_vn";
	private static String username = "root";
	private static String password = "26091995";
	List<List<String>> resultList = null;
	public void abc() {
		try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("Load JDBC Driver Complete ...\n");
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
			System.out.println("Get Connect To Database Complete ...\n");
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM B1_Cung_Cap WHERE Gia_Tien = 0;");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			if(result.first()){
				resultList = new ArrayList<>();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // 1 row la 1 ban ghi.
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					//System.out.print(result.getString(i) + " ");
					row.add(result.getString(i++));
				}
				//System.out.println();
				resultList.add(row); // add it to the result
			}// Ket thuc vong lap while se duoc 1 list resultList chua cac ban ghi. Moi ban ghi la 1 list.
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
