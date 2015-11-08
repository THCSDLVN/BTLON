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

import dataqueryframe.DataQueryFrame;

import errdiagram.ERRDiagram;

public class ServerFrame extends JFrame implements ServerFrameInterface{
	public ServerFrame(){
		super("ServerFrame");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		setPreferredSize(new Dimension(550,390));
		pack();

		getContentPane().setLayout(null);
		getContentPane().add(appNameLabel);
		getContentPane().add(buttonExit);
		getContentPane().add(buttonDiagram);
		getContentPane().add(buttonQuery);

		appNameLabel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/AppName.png"));
		appNameLabel.setBounds(130, 10, 300, 68);

		buttonExit.setBackground(Color.WHITE);
		buttonExit.setBounds(200, 250, 160, 70);
		buttonExit.setFont(new Font("Ubuntu", 1, 18));
		buttonExit.setForeground(new Color(22, 112, 245));
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
		buttonQuery.setBounds(100, 140, 160, 70);
		buttonQuery.setFont(new Font("Ubuntu", 1, 18));
		buttonQuery.setForeground(new Color(22, 112, 245));
		buttonQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new DataQueryFrame();			
			}
		});

		buttonDiagram.setBackground(Color.WHITE);
		buttonDiagram.setBounds(300, 140, 160, 70);
		buttonDiagram.setFont(new Font("Ubuntu", 1, 18));
		buttonDiagram.setForeground(new Color(22, 112, 245));
		buttonDiagram.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new ERRDiagram();			
			}
		});
	}
}