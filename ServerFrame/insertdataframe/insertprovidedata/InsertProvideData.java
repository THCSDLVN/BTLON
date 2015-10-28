package insertdataframe.insertprovidedata;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import sqlfunc.SQLFunc;

public class InsertProvideData {

	public JLabel labelProvideName = new JLabel("INSERT PROVIDE DATA");
	public JLabel provideRIDLabel = new JLabel("RID");
	public JLabel provideCostLabel = new JLabel("Cost");
	public JLabel provideFIDLabel = new JLabel("FID");
	public JLabel provideHelpLabel = new JLabel("HELP");

	public JButton buttonOKProvide = new JButton("OK");

	public JTextField provideFIDTextField = new JTextField();
	public JTextField provideRIDTextField = new JTextField();
	public JTextField provideCostTextField = new JTextField();

	public SQLFunc sqlFunc;
	
	public InsertProvideData(JPanel panelProvide, JFrame frame){
		panelProvide.setLayout(null);
		
		panelProvide.add(labelProvideName);
		panelProvide.add(provideRIDLabel);
		panelProvide.add(provideCostLabel);
		panelProvide.add(provideFIDLabel);
		panelProvide.add(provideHelpLabel);

		panelProvide.add(provideFIDTextField);
		panelProvide.add(provideRIDTextField);
		panelProvide.add(provideCostTextField);

		panelProvide.add(buttonOKProvide);

		labelProvideName.setFont(new Font("Ubuntu", 1, 24));
		labelProvideName.setForeground(new Color(246, 9, 9));
		labelProvideName.setHorizontalAlignment(SwingConstants.CENTER);
		labelProvideName.setBounds(180, 20, 350, 28);

		provideRIDLabel.setFont(new Font("Ubuntu", 1, 16));
		provideRIDLabel.setForeground(new Color(22, 117, 245));
		provideRIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		provideRIDLabel.setBorder(BorderFactory.createEtchedBorder());
		provideRIDLabel.setBounds(140, 170, 120, 40);

		provideCostLabel.setFont(new Font("Ubuntu", 1, 16)); 
		provideCostLabel.setForeground(new Color(22, 117, 245));
		provideCostLabel.setHorizontalAlignment(SwingConstants.CENTER);
		provideCostLabel.setBorder(BorderFactory.createEtchedBorder());
		provideCostLabel.setBounds(140, 260, 120, 40);

		provideFIDLabel.setFont(new Font("Ubuntu", 1, 16)); 
		provideFIDLabel.setForeground(new Color(22, 117, 245));
		provideFIDLabel.setHorizontalAlignment(SwingConstants.CENTER);
		provideFIDLabel.setBorder(BorderFactory.createEtchedBorder());
		provideFIDLabel.setBounds(140, 90, 120, 40);

		provideFIDTextField.setFont(new Font("Ubuntu", 1, 18));
		provideFIDTextField.setBounds(320, 90, 230, 40);

		provideRIDTextField.setFont(new Font("Ubuntu", 1, 18)); 
		provideRIDTextField.setBounds(320, 170, 230, 40);

		provideCostTextField.setFont(new Font("Ubuntu", 1, 18));
		provideCostTextField.setBounds(320, 260, 230, 40);

		provideHelpLabel.setFont(new Font("Ubuntu", 1, 16));
		provideHelpLabel.setForeground(new Color(22, 117, 245));
		provideHelpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		provideHelpLabel.setBounds(30, 370, 40, 20);
		provideHelpLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(provideHelpLabel.getMouseListeners().length < 1){
			provideHelpLabel.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to insert new provide data to the database.");
					msg.append("\nInput data into textField correlative to labelName");
					msg.append("\nEach time you can only insert one record to table");
					JOptionPane.showMessageDialog(null,msg.toString(),"Help Of InsertProvideDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
				}
			});
		}

		buttonOKProvide.setFont(new Font("Ubuntu", 1, 18));
		buttonOKProvide.setForeground(new Color(22, 117, 245));
		buttonOKProvide.setBounds(270, 360, 120, 30);
		buttonOKProvide.setBackground(Color.WHITE);
		if(buttonOKProvide.getActionListeners().length < 1){
			buttonOKProvide.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					sqlFunc = new SQLFunc();
					String rid = new String(provideRIDTextField.getText());
					String fid = new String(provideFIDTextField.getText());
					String cost = new String(provideCostTextField.getText());

					if(sqlFunc.insertDataQuery("Provide",rid,fid,cost,"","","","","") > 0){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Command Is Completed","Help Of InsertProvideDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						
						provideRIDTextField.setText("");
						provideFIDTextField.setText("");
						provideCostTextField.setText("");
					}
					else{
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
						JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of InsertProvideDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
					}
				}
			});
		}

		frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				provideRIDTextField.setText("");
				provideFIDTextField.setText("");
				provideCostTextField.setText("");
			}
		});
	}
}