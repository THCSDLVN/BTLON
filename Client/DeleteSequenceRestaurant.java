package Client.deletesequencerestaurant;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import java.util.List;
import java.util.ArrayList;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Client.clientprocess.ClientProcess;

public class DeleteSequenceRestaurant extends JDialog{
	public JButton deletebtn = new JButton("Delete");
	public JComboBox seqCbox = new JComboBox();
	public JLabel storelbl = new JLabel();

	public DeleteSequenceRestaurant(ClientProcess clientProcess,String resName,String resID,List<String> restaurantnames,List<String> restaurantids,List<String> listAdd,String aid,JComboBox myresCb){
		setPreferredSize(new Dimension(300, 122));
		setResizable(false);
		pack();
		setTitle("Delete Store(s) Of Sequence");

		setVisible(true);
		getContentPane().add(storelbl);
		getContentPane().add(seqCbox);
		getContentPane().add(deletebtn);
		setLayout(null);

		storelbl.setText("Store(s)");
		storelbl.setBounds(20, 30, 60, 30);
		storelbl.setHorizontalAlignment(SwingConstants.CENTER);

		seqCbox.setModel(new DefaultComboBoxModel(listAdd.toArray()));
		seqCbox.setBounds(110, 30, 160, 27);

		deletebtn.setBounds(100, 80, 90, 27);
		if(seqCbox.getItemCount() == 0){
			deletebtn.setEnabled(false);
		}
		deletebtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				int index = seqCbox.getSelectedIndex();
				if(index == -1){
					return ;
				}
				else{
					if(clientProcess.lock == 0){
						String resAdd = new String(seqCbox.getItemAt(index).toString());
						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{Reservation~ResAddress = '"+ resAdd +"'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.setResultAlterQuery();

						do{
							;
						}while(!clientProcess.request.toString().equals(""));
						clientProcess.getRequestFromClient("deleteDataQuery{SequenceRestaurant~Address = '"+ resAdd +"' and ResID = '" + resID + "'}");
						do{
							if(clientProcess.lock == 1){
								clientProcess.setRequest();
								JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
								return ;
							}
							//Vong lap nay dung de cho den khi co ket qua
						}while(!clientProcess.request.toString().equals(""));
						String resultdelete = new String();
						resultdelete = clientProcess.getResultAlterQuery();
						clientProcess.setResultAlterQuery();

						if(!resultdelete.equals("0")){
							if(listAdd.size() == 1){
								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("deleteDataQuery{Provide~ResID = '"+ resID +"'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.setResultAlterQuery();

								do{
									;
								}while(!clientProcess.request.toString().equals(""));
								clientProcess.getRequestFromClient("deleteDataQuery{Restaurant~ResID = '"+ resID +"'}");
								do{
									if(clientProcess.lock == 1){
										clientProcess.setRequest();
										JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
										return ;
									}
									//Vong lap nay dung de cho den khi co ket qua
								}while(!clientProcess.request.toString().equals(""));
								String success = new String();
								success = clientProcess.getResultAlterQuery();
								clientProcess.setResultAlterQuery();

								if(!success.equals("0")){
									if(restaurantnames != null){
										restaurantnames.remove(resName);
										restaurantids.remove(resID);
										myresCb.removeAllItems();
										myresCb.setModel(new DefaultComboBoxModel(restaurantnames.toArray()));
									}
									do{
										;
									}while(!clientProcess.request.toString().equals(""));
									clientProcess.getRequestFromClient("dataQuery{Restaurant~Resname~\"\"~AID = '" + aid + "'~\"\"~\"\"}");
									clientProcess.printRequest();
									do{
										if(clientProcess.lock == 1){
											clientProcess.setRequest();
											JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
											return ;
										}
										//Vong lap nay dung de cho den khi co ket qua
									}while(!clientProcess.request.toString().equals(""));
									List<List<String>> resultlist = new ArrayList();
									resultlist = clientProcess.getResultList();
									clientProcess.setResultList();
								
									if(resultlist.size() == 0){
										do{
											;
										}while(!clientProcess.request.toString().equals(""));
										clientProcess.getRequestFromClient("deleteDataQuery{AccountAssignment~RLID = 'ROLE0002' and AID = '" + aid + "'}");
										do{
											if(clientProcess.lock == 1){
												clientProcess.setRequest();
												JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
												return ;
											}
											//Vong lap nay dung de cho den khi co ket qua
										}while(!clientProcess.request.toString().equals(""));
										clientProcess.setResultAlterQuery();
									}
								}
							}
							JOptionPane.showMessageDialog(null,"Delete Successfully");
							listAdd.remove(resAdd);
							seqCbox.removeAllItems();
							seqCbox.setModel(new DefaultComboBoxModel(listAdd.toArray()));
						}
						else{
							JOptionPane.showMessageDialog(null,"Delete Unsuccessfully");
							return ;
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Server Has Crashed","Announce",JOptionPane.WARNING_MESSAGE);
						return ;
					}
				}
			}
		});
	}
}