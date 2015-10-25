package serverframe;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.Map;

import insertdataframe.InsertDataFrame;
import dataqueryframe.DataQueryFrame;
import deletedataframe.DeleteDataFrame;
import updatedataframe.UpdateDataFrame;

import errdiagram.ERRDiagram;
import buttonflag.ButtonFlag;

public class ServerFrame extends JFrame implements ServerFrameInterface{
	ButtonFlag buttonFlag = new ButtonFlag();
	public ServerFrame(){
		super("ServerFrame");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setPreferredSize(new Dimension(610,460));
		pack();

		getContentPane().setLayout(null);
		getContentPane().add(appNameLabel);
		getContentPane().add(buttonExit);
		getContentPane().add(buttonQuery);
		getContentPane().add(buttonInsert);
		getContentPane().add(buttonDelete);
		getContentPane().add(buttonUpdate);
		getContentPane().add(diagramLabel);

		appNameLabel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/AppName.png"));
		appNameLabel.setBounds(150, 10, 300, 60);

		buttonExit.setBackground(Color.WHITE);
		buttonExit.setFont(new Font("Ubuntu", 1, 24));
		buttonExit.setForeground(new Color(22, 117, 245));
		buttonExit.setBounds(200, 340, 210, 70);
		buttonExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				Runtime run = Runtime.getRuntime();
				try{
					run.exec("/bin/sh shell.sh");
				}
				catch(Exception e){
					e.printStackTrace();
				}
				System.exit(0);
			}
		});

		buttonQuery.setBackground(Color.WHITE);
		buttonQuery.setFont(new Font("Ubuntu", 1, 17));
		buttonQuery.setForeground(new Color(22, 117, 245));
		buttonQuery.setBounds(120, 120, 150, 70);
		buttonQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(buttonFlag.buttonQueryFlag == 1){
					buttonFlag.buttonDeleteFlag = 0;
					buttonFlag.buttonInsertFlag = 0;
					buttonFlag.buttonUpdateFlag = 0;
					buttonFlag.buttonQueryFlag = 0;
					new DataQueryFrame(buttonFlag);
				}			
			}
		});

		buttonUpdate.setBackground(Color.WHITE);
		buttonUpdate.setFont(new Font("Ubuntu", 1, 17));
		buttonUpdate.setForeground(new Color(22, 117, 245));
		buttonUpdate.setBounds(330, 120, 150, 70);
		buttonUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(buttonFlag.buttonUpdateFlag == 1){
					buttonFlag.buttonInsertFlag = 0;
					buttonFlag.buttonDeleteFlag = 0;
					buttonFlag.buttonQueryFlag = 0;
					buttonFlag.buttonUpdateFlag = 0;
					new UpdateDataFrame(buttonFlag);
				}			
			}
		});

		buttonInsert.setBackground(Color.WHITE);
		buttonInsert.setFont(new Font("Ubuntu", 1, 17));
		buttonInsert.setForeground(new Color(22, 117, 245));
		buttonInsert.setBounds(120, 230, 150, 70);
		buttonInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(buttonFlag.buttonInsertFlag == 1){
					buttonFlag.buttonDeleteFlag = 0;
					buttonFlag.buttonQueryFlag = 0;
					buttonFlag.buttonInsertFlag = 0;
					buttonFlag.buttonUpdateFlag = 0;
					new InsertDataFrame(buttonFlag);
				}			
			}
		});

		buttonDelete.setBackground(Color.WHITE);
		buttonDelete.setFont(new Font("Ubuntu", 1, 17));
		buttonDelete.setForeground(new Color(22, 117, 245));
		buttonDelete.setBounds(330, 230, 150, 70);
		buttonDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(buttonFlag.buttonDeleteFlag == 1){
					buttonFlag.buttonDeleteFlag = 0;
					buttonFlag.buttonUpdateFlag = 0;
					buttonFlag.buttonInsertFlag = 0;
					buttonFlag.buttonQueryFlag = 0;
					new DeleteDataFrame(buttonFlag);
				}			
			}
		});

		diagramLabel.setFont(new Font("Ubuntu", 1, 16));
		diagramLabel.setForeground(new Color(22, 117, 245));
		diagramLabel.setHorizontalAlignment(SwingConstants.CENTER);
		diagramLabel.setBounds(480, 420, 120, 20);
		diagramLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(diagramLabel.getMouseListeners().length < 1){
			diagramLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					new ERRDiagram();
				}
				public void mouseEntered(MouseEvent me) {
					final Map attributes = (new Font("Ubuntu", 1, 16)).getAttributes();
					attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
					me.getComponent().setFont(new Font(attributes));
				}
				public void mouseExited(MouseEvent me) {
					me.getComponent().setFont(new Font("Ubuntu", 1, 16));
				}
			});
		}
	}
}