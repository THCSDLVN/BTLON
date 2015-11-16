import java.util.List;
public class SolveArrayList {
	public String[][] ConvertFromArrayList(List<List<String>> a){
		String[][] trave = new String[a.size()][a.get(0).size()];
		int i;
		for(i=0;i<a.size();i++){
			int j;
			for(j=0;j<a.get(i).size();j++)
				trave[i][j] = a.get(i).get(j);
		}
		return trave;
	}
}
