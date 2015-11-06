import java.net.*;
import java.io.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Client {
	public static void main(String[] args) {
		try{
			Socket clientSocket = new Socket(InetAddress.getByName("localhost"),5000);
			BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
			BufferedReader inString = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter outString = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
			ObjectInputStream inObject = new ObjectInputStream(clientSocket.getInputStream());

			String message;
			String commandName;
			while(true){
				System.out.print("Send to server :");
				message = user.readLine();
				if(message.equals("exit")){
					break;
				}
				outString.println(message);
				outString.flush();
				
				String reply;
				reply = inString.readLine();
				if(reply.equals("error")){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
					JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertMenuDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
				}
				else{
					int i = message.indexOf('(');
					commandName = message.substring(0,i);
					if(commandName.equals("dataQuery")){
						List<List<String>> resultList = new ArrayList<>();
						resultList = (List<List<String>>) inObject.readObject();
						if(resultList != null){
							for(List<String> innerLs : resultList) {
								for (Iterator<String> j = innerLs.iterator(); j.hasNext();) {
									System.out.print(j.next() + " ");
								}
								System.out.println();
							}
						}
						else{
							Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
							JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertMenuDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						}
					}
					else{
						String result = (String)inObject.readObject();
						if(result.equals("0")){
							Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
							JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertMenuDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						}
						else{
							Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
							JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertMenuDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						}
					}
				}
			}
			clientSocket.close();
			inString.close();
			outString.close();
			inObject.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}