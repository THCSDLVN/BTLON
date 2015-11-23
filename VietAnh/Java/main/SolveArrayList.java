import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class SolveArrayList {
	public static int getRows(List<List<String>> a){
		if(a.isEmpty()){
			return 0;
		}
		return a.size();
	}
	public static int getColumns(List<List<String>> a){
		if(getRows(a) == 0){
			return 0;
		}
		else{
			if(a.get(0).isEmpty()){
				return 0;
			}
			else
				return a.get(0).size();
		}
	}
	public static String[][] ConvertFromArrayList(List<List<String>> a){
		int rows = getRows(a),columns = getColumns(a);
		if(rows == 0 || columns == 0)
			return null;
		int irows = 0,ycols = 0;
		String[][] trave = new String[rows][columns];
		for(List<String> innerLs : a){
			for(Iterator<String> i = innerLs.iterator();i.hasNext();){
				trave[irows][ycols++] = i.next();
			}
			ycols = 0;
			irows++;
		}
		return trave;
	}
	public static void main(String[] args){
		SQLConnection c = new SQLConnection();
		List<List<String>> resList = new ArrayList<>();
		resList = c.getAcc("longdh","daohailong0101");
		String a[][]; 
		a = ConvertFromArrayList(resList);
		int i,j;
		for(i=0;i<a.length;i++){
			for(j=0;j<a[i].length;j++)
				System.out.print(a[i][j]+" ");
		}
	}
}
