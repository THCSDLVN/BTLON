package Client.clientTCPThread;

import java.net.*;
import java.io.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Client.clientprocess.ClientProcess;

public class ClientTCPThread {

	public final static String SERVER_ADDRESS = "localhost";
	public final static int TCP_SERVER_PORT = 5000;
	
	static Socket clientSocket = null;
	static BufferedReader inString = null;
	static PrintWriter outString = null;
	static ObjectInputStream inObject = null;

	public ClientTCPThread(ClientProcess clientProcess){
		String message = new String();
		String commandName = new String();

		while(true){
			if(hostAvailabilityCheck(clientProcess)){
				try{
					if(clientProcess.request.toString() != null && clientProcess.lock == 0){
						if(clientProcess.request.toString().equals("exit")){
							break;
						}
						else{
							if(!clientProcess.request.toString().equals("")){
								message = clientProcess.setRequestToSocket();
								outString.println(message);
								outString.flush();
								int i = message.indexOf("(");
								if(i >= 0){
									commandName = message.substring(0, i);
									if(commandName.equals("dataQuery")){
										List<List<String>> resultList = new ArrayList<>();
										resultList = (List<List<String>>) inObject.readObject();
										clientProcess.getResultFromDataQuery(resultList);
										clientProcess.setRequest();//Khi nao co ket qua thi request trung gian se bang ""
										for(List<String> innerLs : resultList) {
											for (Iterator<String> j = innerLs.iterator(); j.hasNext();) {
												System.out.print(j.next() + " ");
											}
											System.out.println();
										}
									}
									else if(commandName.equals("insertDataQuery") || commandName.equals("updateDataQuery") || commandName.equals("deleteDataQuery")){
										String result = new String();
										result = (String)inObject.readObject();
										clientProcess.getResultFromAlterQuery(result);
										clientProcess.setRequest();//Khi nao co ket qua thi request trung gian se bang ""
									}
								}
							}
						}
					}
				}
				catch(IOException ioe){
					clientProcess.lock = 1;
					try{
						if(clientSocket != null){
							clientSocket.close();
							clientSocket = null;
						}
						if(inString != null){
							inString.close();
							inString = null;
						}
						if(outString != null){
							outString.close();
							outString = null;
						}
						if(inObject != null){
							inObject.close();
							inObject  = null;
						}
					}
					catch(IOException e){
						e.printStackTrace();
					}
					clientProcess.label.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/offline.png"));
					ioe.printStackTrace();
					clientProcess.setRequest();
				}
				catch(ClassNotFoundException cnfe){
					clientProcess.lock = 1;
					try{
						if(clientSocket != null){
							clientSocket.close();
							clientSocket = null;
						}
						if(inString != null){
							inString.close();
							inString = null;
						}
						if(outString != null){
							outString.close();
							outString = null;
						}
						if(inObject != null){
							inObject.close();
							inObject  = null;
						}
					}
					catch(IOException e){
						e.printStackTrace();
					}
					clientProcess.label.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/offline.png"));
					cnfe.printStackTrace();
					clientProcess.setRequest();
				}
			}
			else {
				clientProcess.lock = 1;
				try{
					if(clientSocket != null){
						clientSocket.close();
						clientSocket = null;
					}
					if(inString != null){
						inString.close();
						inString = null;
					}
					if(outString != null){
						outString.close();
						outString = null;
					}
					if(inObject != null){
						inObject.close();
						inObject  = null;
					}
				}
				catch(IOException ioe){
					ioe.printStackTrace();
				}
				clientProcess.label.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/offline.png"));
				clientProcess.setRequest();
			}
		}	
	}

	public static boolean hostAvailabilityCheck(ClientProcess clientProcess){  
		boolean available = true;
		Socket s;
		try{
			s = new Socket(SERVER_ADDRESS, TCP_SERVER_PORT);
			if (s.isConnected()){ 
				s.close();
				clientProcess.label.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/ResourceForClient/online.png"));
				if(clientProcess.lock == 1){
					try{
	 					clientSocket = new Socket(InetAddress.getByName("localhost"),TCP_SERVER_PORT);
						inString = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
						outString = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
						inObject = new ObjectInputStream(clientSocket.getInputStream());
						clientProcess.lock = 0;
					}
					catch(Exception e){
						e.printStackTrace();
					} 
				}  
			}               
		} 
		catch (UnknownHostException e) { 
			// unknown host 
			available = false;
			s = null;
		} 
		catch (IOException e) { 
			// io exception, service probably not running 
			available = false;
			s = null;
		} 
		catch (NullPointerException e) {
			available = false;
			s = null;
		}
		return available;
	} 
}