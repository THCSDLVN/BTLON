import java.util.Iterator;
import java.util.List;

public class TestJDBC {

	public static void main(String[] args) {
		try{
			SQLConnection c = new SQLConnection();
			c.abc();
			if(c.resultList == null){
				System.out.println("abcd");
			}
			else{
				for(List<String> innerLs : c.resultList) {
					for (Iterator<String> i = innerLs.iterator(); i.hasNext();) {
						System.out.print(i.next() + " ");
					}// Duyet danh sach con cua resultList.
					System.out.println();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
