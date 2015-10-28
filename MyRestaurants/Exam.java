import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
public class Exam {
	private static String url = "jdbc:mysql://localhost:3306/BT_LON";
	private static String username = "root";
	private static String password = "26091995";
	public static void main(String[] args) {
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
		List<List<String>> resultList = new ArrayList<>(); // List of list, one per row
		try{
			connect = DriverManager.getConnection(url,username,password);
			System.out.println("Get Connect To Database Complete ...\n");
			stmt = connect.createStatement();
			result = stmt.executeQuery("SELECT * FROM Account WHERE AID = 'ACC0001';");
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) { // don't skip the last column, use <=
					System.out.print(result.getString(i) + " ");
					row.add(result.getString(i++));
				}
				System.out.println();
				resultList.add(row); // add it to the result
			}
			connect.close();
			stmt.close();
			result.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		for(List<String> innerLs : resultList) {
			for (Iterator<String> i = innerLs.iterator(); i.hasNext();) {
				System.out.print(i.next() + " ");
			}
			System.out.println();
		}
	}
}
