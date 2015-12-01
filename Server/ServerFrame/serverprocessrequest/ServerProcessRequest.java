package Server.serverprocessrequest;

import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import Server.sqlfunc.SQLFunc;
import Server.makearraylist.MakeArrayList;

public class ServerProcessRequest {
	String command;
	String fillParam[];
	SQLFunc funcTool;
	MakeArrayList makeArrayList = new MakeArrayList();
	
	public ServerProcessRequest(){
		command = new String();
		funcTool = new SQLFunc();
	}

	public boolean checkReceivedRequest(String request){
		if(request.equals("")){
			return false;
		}
		else{
			if(request.charAt(0) == '{' || request.indexOf('{') < 0 || request.indexOf('}') < 0){
				return false;
			}
			else{
				for(int i = 0 ; i < request.length() ; i++){
					if(request.charAt(i) == '{' && request.charAt(i + 1) == '}'){
						return false;
					}
				}
			}
		}
		int i = request.indexOf('{');
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
			if(fillParam.length != 12){
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

	public List<List<String>> executeDataQuery(){
		ResultSet result = funcTool.dataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3],fillParam[4],fillParam[5]);
		if(result == null){
			List<List<String>> resultList = new ArrayList<>();
			return resultList;
		}
		return makeArrayList.makeArrayList(result);
	}

	public String executeChangeDataQuery(){
		if(command.equals("updateDataQuery")){
			return Integer.toString(funcTool.updateDataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3]));
		}
		else if(command.equals("insertDataQuery")){
			return Integer.toString(funcTool.insertDataQuery(fillParam[0],fillParam[1],fillParam[2],fillParam[3],fillParam[4],fillParam[5],fillParam[6],fillParam[7],fillParam[8],fillParam[9],fillParam[10],fillParam[11]));
		}
		else if(command.equals("deleteDataQuery")){
			return Integer.toString(funcTool.deleteDataQuery(fillParam[0],fillParam[1]));
		}
		return null;
	}
}