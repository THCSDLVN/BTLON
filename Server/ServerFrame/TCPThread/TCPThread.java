package TCPThread;

import java.net.Socket;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.ObjectOutputStream;

import serverprocessrequest.ServerProcessRequest;

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
				if(!spr.checkReceivedRequest(message)){
					outString.println("error");
					outString.flush();
				}
				else{
					if(!spr.checkCommand() || !spr.checkParam()){
						outString.println("error");
						outString.flush();
					}
					else{
						outString.println("complete");
						outString.flush();
						
						int i = message.indexOf('(');
						commandName = message.substring(0,i);
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
			System.out.println("Client has stopped sending data!");
			socket.close();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}