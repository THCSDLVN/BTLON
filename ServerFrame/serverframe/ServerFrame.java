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
		setPreferredSize(new Dimension(550,420));
		pack();

		getContentPane().setLayout(null);
		getContentPane().add(appNameLabel);
		getContentPane().add(exitLabel);
		getContentPane().add(queryLabel);
		getContentPane().add(buttonExit);
		getContentPane().add(buttonQuery);
		getContentPane().add(diagramLabel);

		appNameLabel.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/AppName.png"));
		appNameLabel.setBounds(120, 10, 300, 68);

		exitLabel.setFont(new Font("Ubuntu", 1, 18));
		exitLabel.setForeground(new Color(22, 112, 245));
		exitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		exitLabel.setBorder(BorderFactory.createEtchedBorder());
		exitLabel.setBounds(80, 250, 140, 60);

		queryLabel.setFont(new Font("Ubuntu", 1, 18));
		queryLabel.setForeground(new Color(22, 112, 245));
		queryLabel.setHorizontalAlignment(SwingConstants.CENTER);
		queryLabel.setBorder(BorderFactory.createEtchedBorder());
		queryLabel.setBounds(80, 120, 140, 60);

		buttonExit.setBackground(Color.WHITE);
		buttonExit.setBounds(320, 250, 120, 60);
		buttonExit.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/exit.png"));
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
		buttonQuery.setBounds(320, 120, 120, 60);
		buttonQuery.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/maginifier.png"));
		buttonQuery.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				new DataQueryFrame();			
			}
		});

		diagramLabel.setFont(new Font("Ubuntu", 1, 16));
		diagramLabel.setForeground(new Color(22, 117, 245));
		diagramLabel.setHorizontalAlignment(SwingConstants.CENTER);
		diagramLabel.setBounds(410, 380, 140, 30);
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