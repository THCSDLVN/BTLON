package Server.tableforresult;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.RowSorter;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

import Server.solvearraylist.SolveArrayList;

public class TableForResult extends JFrame{ 
	public JScrollPane scrollPane = new JScrollPane();
	public JTable table = new JTable();

	public TableForResult(List<List<String>> result, List<String> columnNames){
		super("Result Table");
		setVisible(true);
		setPreferredSize(new Dimension(1200,550));
		pack();

		String [][] x = SolveArrayList.ConvertFromArrayList(result);
		DefaultTableModel model = new DefaultTableModel(x,columnNames.toArray()){
			boolean[] columnEditables = new boolean[] {false, false, false, false, false, false, false, false, false, false, false, false, false, false
			, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
			, false, false, false, false, false, false};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}	
		};

		scrollPane = new JScrollPane(table);
		RowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(model);
		table.setModel(model);
		table.setRowSorter(sorter);
		getContentPane().add(scrollPane,"Center");
		pack();
	}
}