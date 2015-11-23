package Server.serverframe;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

import Server.dataqueryframe.DataQueryFrame;
import Server.errdiagram.ERRDiagram;
import Server.buttonflag.ButtonFlag;
import Server.sqlfunc.SQLFunc;

public class ServerFrame extends JFrame implements ServerFrameInterface,WindowListener{
	public ButtonFlag buttonFlag = new ButtonFlag();
	public SQLFunc funcTool = new SQLFunc();
	public ServerFrame(){
		super("ServerFrame");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setPreferredSize(new Dimension(580,386));
		pack();

		getContentPane().setLayout(null);
		getContentPane().add(appNameLabel);
		getContentPane().add(buttonExit);
		getContentPane().add(buttonDiagram);
		getContentPane().add(buttonQuery);
		getContentPane().add(backGroundLabel);

		appNameLabel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/AppName.png"));
		appNameLabel.setBounds(20, 0, 300, 68);

		backGroundLabel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/background.png"));
		backGroundLabel.setBounds(-1, 0, 580, 390);

		buttonExit.setBackground(new Color(0, 153, 204));
		buttonExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonExit.setBounds(450, 160, 110, 30);
		buttonExit.setFont(new Font("Ubuntu", 1, 15));
		buttonExit.setForeground(Color.WHITE);
		buttonExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Runtime run = Runtime.getRuntime();
				try{
					run.exec("/bin/sh shell.sh");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				funcTool.updateDataQuery("Account","Status","0","Status = 1");
				System.exit(0);
			}
		});

		buttonQuery.setBackground(new Color(0, 153, 204));
		buttonQuery.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonQuery.setBounds(450, 60, 110, 30);
		buttonQuery.setFont(new Font("Ubuntu", 1, 14));
		buttonQuery.setForeground(Color.WHITE);
		if(buttonQuery.getActionListeners().length < 1){
			buttonQuery.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					if(buttonFlag.buttonQueryFlag == 1){
						buttonFlag.buttonQueryFlag = 0;
						new DataQueryFrame(buttonFlag);	
					}		
				}
			});
		}

		buttonDiagram.setBackground(new Color(0, 153, 204));
		buttonDiagram.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonDiagram.setBounds(450, 110, 110, 30);
		buttonDiagram.setFont(new Font("Ubuntu", 1, 15));
		buttonDiagram.setForeground(Color.WHITE);
		buttonDiagram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new ERRDiagram();			
			}
		});
	}
	public void windowOpened(WindowEvent we){

	}

	public void windowClosing(WindowEvent we){
		Runtime run = Runtime.getRuntime();
		try{
			run.exec("/bin/sh shell.sh");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		funcTool.updateDataQuery("Account","Status","0","Status = 1");
		System.exit(0);
	}

	public void windowClosed(WindowEvent we){

	}

	public void windowIconified(WindowEvent we){

	}

	public void windowDeiconified(WindowEvent we){

	}

	public void windowActivated(WindowEvent we){

	}

	public void windowDeactivated(WindowEvent we){

	}
}