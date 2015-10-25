package tableforresult;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.table.AbstractTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.table.TableRowSorter;
import javax.swing.RowSorter;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.Arrays;

abstract class ResultSetTableModel extends AbstractTableModel{
	private ResultSet rs;
	private ResultSetMetaData rsmd;

	public ResultSetTableModel(ResultSet aRes){
		rs = aRes;
		try{
			rsmd = rs.getMetaData();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}

	public int getColumnCount(){
		try{
			return rsmd.getColumnCount();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public String getColumnName(int c){
		try{
			return rsmd.getColumnName(c + 1);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return "";
		}
	}

	public ResultSet getResultSet(){
		return rs;
	}
}

class ScrollPaneResultSetTableModel extends ResultSetTableModel{
	public ScrollPaneResultSetTableModel(ResultSet aRes){
		super(aRes);
	}

	public Object getValueAt(int r,int c){
		try{
			ResultSet rs = getResultSet();
			rs.absolute(r + 1);
			return rs.getObject(c + 1);
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public int getRowCount(){
		try{ 
			ResultSet rs = getResultSet();
			rs.last();
			return rs.getRow();
		}
		catch(SQLException e){
			System.out.println(e.getMessage());
			return 0;
		}
	}
}

public class TableForResult extends JFrame{ 
	public ResultSetTableModel model;
	public JScrollPane scrollPane = new JScrollPane();
	public JTable table = new JTable();

	public TableForResult(ResultSet result){
		super("Result Table");
		setVisible(true);
		setPreferredSize(new Dimension(1200,550));
		pack();

		model = new ScrollPaneResultSetTableModel(result);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		RowSorter<ResultSetTableModel> sorter = new TableRowSorter<ResultSetTableModel>(model);
		table.setRowSorter(sorter);
		getContentPane().add(scrollPane,"Center");
		pack();
	}
}