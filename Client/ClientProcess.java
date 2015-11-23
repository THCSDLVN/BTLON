package Client.clientprocess;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;

public class ClientProcess {
	public static StringBuffer request = new StringBuffer("");
	public static StringBuffer resultFromAlterQuery = new StringBuffer("");
	public static List<List<String>> resultList = new ArrayList();
	public static int lock = 1;
	public static JLabel label = new JLabel("");
	public static ArrayList<String> listUserIsOnline = new ArrayList<String>();
	
	/*Get Command Part*/

	public void getRequestFromClient(String command){
		request.append(command);
	}
	
	public String setRequestToSocket(){
		return request.toString();
	}

	/*Get Result From Server Part*/

	public void getResultFromAlterQuery(String result){
		resultFromAlterQuery.append(result);
	}

	public void getResultFromDataQuery(List<List<String>> list){
		resultList.addAll(list);
	}

	/*Get Value Part*/

	public String getResultAlterQuery(){
		return resultFromAlterQuery.toString();
	}

	public List<List<String>> getResultList(){
		return resultList;
	}

	/*Initialize Value Part*/

	public void setResultAlterQuery(){
		resultFromAlterQuery = new StringBuffer("");
	}

	public void setRequest(){
		request = new StringBuffer("");
	}

	public void setResultList(){
		resultList = new ArrayList();
	}

	/*Print Value Part*/

	public void printRequest(){
		System.out.println(request);
	}
}