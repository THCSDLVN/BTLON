package updatedataframe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;	
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import buttonflag.ButtonFlag;
import sqlfunc.SQLFunc;

public class UpdateDataFrame extends JFrame implements UpdateDataFrameInterface{
	public SQLFunc sqlFunc;
	public int result;
	
	public UpdateDataFrame(ButtonFlag buttonFlag){
		super("Update Data");
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setPreferredSize(new Dimension(430, 420));
		pack();

		getContentPane().add(labelFrameName);
		getContentPane().add(labelTableName);
		getContentPane().add(labelHelp);
		getContentPane().add(labelSetNewValue);
		getContentPane().add(labelCondition);
		getContentPane().add(labelSetColumn);
		getContentPane().add(setColumnTextField);
		getContentPane().add(tableNameTextField);
		getContentPane().add(buttonOk);
		getContentPane().add(setNewValueTextField);
		getContentPane().add(conditionTextField);

		labelFrameName.setFont(new Font("Ubuntu", 1, 24));
		labelFrameName.setForeground(new Color(246, 9, 9));
		labelFrameName.setHorizontalAlignment(SwingConstants.CENTER);
		labelFrameName.setBounds(130, 10, 170, 50);

		labelTableName.setFont(new Font("Ubuntu", 1, 16));
		labelTableName.setForeground(new Color(22, 117, 245));
		labelTableName.setHorizontalAlignment(SwingConstants.CENTER);
		labelTableName.setBorder(BorderFactory.createEtchedBorder());
		labelTableName.setBounds(30, 70, 110, 40);

		labelSetColumn.setFont(new Font("Ubuntu", 1, 16));
		labelSetColumn.setForeground(new Color(22, 117, 245));
		labelSetColumn.setHorizontalAlignment(SwingConstants.CENTER);
		labelSetColumn.setBorder(BorderFactory.createEtchedBorder());
		labelSetColumn.setBounds(30, 140, 110, 40);

		labelSetNewValue.setFont(new Font("Ubuntu", 1, 15));
		labelSetNewValue.setForeground(new Color(22, 117, 245));
		labelSetNewValue.setHorizontalAlignment(SwingConstants.CENTER);
		labelSetNewValue.setBorder(BorderFactory.createEtchedBorder());
		labelSetNewValue.setBounds(30, 210, 110, 40);

		labelCondition.setFont(new Font("Ubuntu", 1, 16));
		labelCondition.setForeground(new Color(22, 117, 245));
		labelCondition.setHorizontalAlignment(SwingConstants.CENTER);
		labelCondition.setBorder(BorderFactory.createEtchedBorder());
		labelCondition.setBounds(30, 280, 110, 40);

		labelHelp.setFont(new Font("Ubuntu", 1, 16));
		labelHelp.setForeground(new Color(22, 117, 245));
		labelHelp.setHorizontalAlignment(SwingConstants.CENTER);
		labelHelp.setBounds(20, 380, 40, 20);
		labelHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(labelHelp.getMouseListeners().length < 1){
			labelHelp.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to update data, you enter into the textfield next to the table name Table Name 's label.");
					msg.append("\nAt every moment you can only update data of only one table nad only one column.");
					msg.append("\nIn the textfield next to the Set Column 's label,");
					msg.append("\nyou'll enter name of column in the table that you want to update");
					msg.append("\nIn the textfield next to the Set New Value 's Label");
					msg.append("\nyou'll input new value corresponds to the column above");
					msg.append("\nInput to the textfield next to the Condition 's label condition of update");
					msg.append("\nClick OK to complete update.");
					JOptionPane.showMessageDialog(null,msg.toString(),"Help Of DataQueryFrame",JOptionPane.INFORMATION_MESSAGE,icon);
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

		buttonOk.setFont(new Font("Ubuntu", 1, 18));
		buttonOk.setForeground(new Color(22, 117, 245));
		buttonOk.setBounds(150, 350, 130, 40);
		buttonOk.setBackground(Color.WHITE);
		if(buttonOk.getActionListeners().length < 1){
			buttonOk.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					sqlFunc = new SQLFunc();
					String tableName = new String(tableNameTextField.getText());
					String setColumn = new String(setColumnTextField.getText());
					String setNewValue = new String(setNewValueTextField.getText());
					String condition = new String(conditionTextField.getText());
					result = sqlFunc.updateDataQuery(tableName,setColumn,setNewValue,condition);
					if(result == 0){
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png");
						JOptionPane.showMessageDialog(null,"Error In Update Data Query Command","Help Of UpdateDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						return ;
					}
					else{
						Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/correct.png");
						JOptionPane.showMessageDialog(null,"Query Completed","Help Of UpdateDataFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						tableNameTextField.setText("");
						setNewValueTextField.setText("");
						conditionTextField.setText("");
						setColumnTextField.setText("");
						return ;
					}
				}
			});
		}
		
		tableNameTextField.setBounds(190, 70, 200, 40);
		tableNameTextField.setFont(new Font("Ubuntu" , 1, 18));
		setNewValueTextField.setFont(new Font("Ubuntu", 1, 18));
		setNewValueTextField.setBounds(190, 210, 200, 40);
		conditionTextField.setBounds(190, 280, 200, 40);
		conditionTextField.setFont(new Font("Ubuntu", 1, 18));
		setColumnTextField.setBounds(190, 140, 200, 40);
		setColumnTextField.setFont(new Font("Ubuntu", 1, 18));

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				buttonFlag.buttonInsertFlag = 1;
				buttonFlag.buttonUpdateFlag = 1;
				buttonFlag.buttonQueryFlag = 1;
				buttonFlag.buttonDeleteFlag = 1;

				tableNameTextField.setText("");
				setNewValueTextField.setText("");
				conditionTextField.setText("");
				setColumnTextField.setText("");
				dispose();
			}
		});
	}
}