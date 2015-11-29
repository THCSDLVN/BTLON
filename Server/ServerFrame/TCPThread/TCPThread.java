package Server.TCPThread;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectOutputStream;

import Server.serverprocessrequest.ServerProcessRequest;

public class TCPThread implements Runnable{
	private Socket socket;

	public TCPThread(Socket s){
		socket = s;
	}

	public void run(){
		try{
			ServerProcessRequest spr = new ServerProcessRequest();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter outString = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream());
			
			String message;
			String commandName = new String();
			while((message = in.readLine()) != null){
				if(!message.equals("exit")){
					int i = message.indexOf('(');
					if(i >= 0){
						commandName = message.substring(0,i);
						if(spr.checkReceivedRequest(message) && spr.checkCommand() && spr.checkParam()){
							if(commandName.equals("dataQuery")){
								outObject.writeObject(spr.executeDataQuery());
								outObject.flush();
							}
							else if(commandName.equals("updateDataQuery") || commandName.equals("deleteDataQuery") || commandName.equals("insertDataQuery")){
								outObject.writeObject(spr.executeChangeDataQuery());
								outObject.flush();
							}
						}
					}
				}
				else{
					break;
				}
			}
			System.out.println("Client has stopped sending data!");
			socket.close();
			in.close();
			outString.close();
			outObject.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}