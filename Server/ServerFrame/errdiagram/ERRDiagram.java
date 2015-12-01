package Server.errdiagram;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Dimension;

public class ERRDiagram extends JFrame{
	public ERRDiagram(){
		super("ERR Diagram");
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setPreferredSize(new Dimension(815,554));
		pack();

		JLabel labelImage = new JLabel();

		labelImage.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/ERRDiagram.png"));
		getContentPane().add(labelImage);
		labelImage.setBounds(0, 0, 815, 554);
	}
}