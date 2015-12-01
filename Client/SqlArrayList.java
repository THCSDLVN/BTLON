package Client.sqlarraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class SqlArrayList {
	public List<List<String>> ResultList = new ArrayList();

	public int colnum;
	public int rownum;
	
	public List<List<String>> getArrayList(){
		try{
			return ResultList;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public SqlArrayList(List<List<String>> result) {
		ResultList = result;
		int mark = 0;
		rownum = 0;
		for(List<String> innerLs : ResultList) {
			for (Iterator<String> j = innerLs.iterator(); j.hasNext();) {
				if(mark == 0){
					colnum++;
				}
				j.next();
			}
			mark = 1;
			rownum++;
		}
	}

	public SqlArrayList(){
		;
	}
	
	public void close(){
		try{
			ResultList.clear();
			colnum = 0;
			rownum = 0;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String[] getColumn(int index){
		try{
			if(rownum == 0) return new String[]{};
			List<String> columnlist = new ArrayList<String>();
			for(List<String> innerLs : ResultList)
			{	
				columnlist.add(innerLs.get(index));
			}
			return columnlist.toArray(new String[columnlist.size()]);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public String[] getRow(int index){
		try{
			if(rownum == 0) return new String[]{};
			List<String> rowlist = new ArrayList<String>(ResultList.get(index));
			return rowlist.toArray(new String[rowlist.size()]);
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}	
	}
	
	public String[][] get2dArray(){
		String ret[][] = new String[rownum][colnum];
		if(rownum == 0) {
			return ret;
		}
		else{
			for(int i=0;i<rownum;i++){
				ret[i] = getRow(i);
			}
			return ret;
		}
	}
	
	public int getRownumber(){
		return rownum;
	}
	
	public int getColnumber(){
		return colnum;
	}
}