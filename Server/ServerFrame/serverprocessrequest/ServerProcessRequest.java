package serverprocessrequest;

import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import sqlfunc.SQLFunc;

public class ServerProcessRequest {
	String command = new String();
	String fillParam[];
	SQLFunc funcTool = new SQLFunc();
	
	public boolean checkReceivedRequest(String request){
		if(request.equals("")){
			return false;
		}
		else{
			if(request.charAt(0) == '(' || request.indexOf('(') < 0 || request.indexOf(')') < 0){
				return false;
			}
			else{
				for(int i = 0 ; i < request.length() ; i++){
					if(request.charAt(i) == '(' && request.charAt(i + 1) == ')'){
						return false;
					}
				}
			}
		}
		int i = request.indexOf('(');
		command = request.substring(0,i);
		fillParam = stringTokenizer(request.substring(i + 1,request.length() - 1));
		return true;
	}

	public boolean checkCommand(){
		if(!command.equals("dataQuery") && !command.equals("deleteDataQuery") && !command.equals("updateDataQuery") && !command.equals("insertDataQuery")){
			return false;
		}
		return true;
	}

	public boolean checkParam(){
		if(command.equals("dataQuery")){
			if(fillParam.length != 6){
				return false;
			}
		}
		else if(command.equals("deleteDataQuery")){
			if(fillParam.length != 2){
				return false;
			}
		}
		else if(command.equals("insertDataQuery")){
			if(fillParam.length != 9){
				return false;
			}
		}
		else if(command.equals("updateDataQuery")){
			if(fillParam.length != 4){
				return false;
			}
		}
		return true;
	}

	public String[] stringTokenizer(String paramList){
		int i,counter = 0;
		StringTokenizer token = new StringTokenizer(paramList,"~");
		for(i = 0 ; i < paramList.length() ; i++){
			if(paramList.charAt(i) == '~'){
				counter++;
			}
		}
		String stringResultArray[] = new String[counter + 1];
		counter = 0;
		while(token.hasMoreTokens()){
			stringResultArray[counter] = new String(token.nextToken());
			if(stringResultArray[counter].equals("\"\"")){
				stringResultArray[counter] = "";
			}
			counter++;
		}
		return stringResultArray;
	}

	public List<List<String>> makeArrayList(ResultSet result){
		int numcols;
		List<List<String>> resultList = new ArrayList<>();  // List of list, one per row
		ResultSetMetaData metadata;

		try{
			metadata = result.getMetaData();
			numcols = metadata.getColumnCount();	
			while (result.next()) {
				List<String> row = new ArrayList<>(numcols); // new list per row
				int i = 1;
				while (i <= numcols) {  // don't skip the last column, use <=
					row.add(result.getString(i++));
				}
					resultList.add(row); // add it to the result
			}
			result.close();
			return resultList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public List<List<String>> executeDataQuery(){
		ResultSet result = funcTool.dataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3],fillParam[4],fillParam[5]);
		if(result == null){
			return null;
		}
		return makeArrayList(result);
	}

	public String executeChangeDataQuery(){
		if(command.equals("updateDataQuery")){
			return Integer.toString(funcTool.updateDataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3]));
		}
		else if(command.equals("insertDataQuery")){
			return Integer.toString(funcTool.insertDataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3],fillParam[4],fillParam[5],fillParam[6],fillParam[7],fillParam[8]));
		}
		else if(command.equals("deleteDataQuery")){
			return Integer.toString(funcTool.deleteDataQuery(fillParam[0],fillParam[1]));
		}
		return null;
	}
}