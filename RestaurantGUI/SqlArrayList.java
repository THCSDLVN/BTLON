package gui;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

public class SqlArrayList {
	private List<List<String>> ResultList;
	private ResultSet sqlresult;
	private ResultSetMetaData metadata;
	private int colnum;
	private int rownum;
	
	private void createArrayfromResult()
	{
		try
		{
			ResultList.clear();
			rownum = 0;
			while (sqlresult.next()) 
			{
				List<String> row = new ArrayList<String>(colnum); // new list per row
				int i = 1;
				while (i <= colnum) 
				{  // don't skip the last column, use <=
					row.add(sqlresult.getString(i++)); // add it to the result
				}
				ResultList.add(row);
				rownum++;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void setResult(ResultSet rsl)
	{
		try
		{
			sqlresult = rsl;
			metadata = rsl.getMetaData();
			colnum = metadata.getColumnCount();
			createArrayfromResult();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<List<String>> getArrayList()
	{
		try
		{
			return ResultList;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getSQLResult()
	{
		try
		{
			return sqlresult;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}	
	
	public SqlArrayList(ResultSet rsl) 
	{
		ResultList = new ArrayList<>();
		setResult(rsl);	
	}
	
	public SqlArrayList()
	{
		ResultList = new ArrayList<>();
	}	
	
	public void close()
	{
		try
		{
			ResultList.clear();;
			sqlresult.close();
			colnum = 0;
			rownum = 0;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String[] getColumn(int index)
	{
		try
		{
			if(rownum == 0) return new String[]{};
			List<String> columnlist = new ArrayList<String>();
			for(List<String> innerLs : ResultList)
			{	
				columnlist.add(innerLs.get(index));
			}
			return columnlist.toArray(new String[columnlist.size()]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	public String[] getRow(int index)
	{
		try
		{
			if(rownum == 0) return new String[]{};
			List<String> rowlist = new ArrayList<String>(ResultList.get(index));
			return rowlist.toArray(new String[rowlist.size()]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}	
	}
	
	public String[][] get2dArray()
	{
		String ret[][] = new String[rownum][colnum];
		if(rownum == 0) return ret;
		else
		{
			for(int i=0;i<rownum;i++)
			{
				ret[i] = getRow(i);
			}
			return ret;
		}
	}
	
	public int getRownumber()
	{
		return rownum;
	}
	
	public int getColnumber()
	{
		return colnum;
	}
	
}
