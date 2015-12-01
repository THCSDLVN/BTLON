package Server.dataqueryframe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;

import java.util.Map;

import java.sql.ResultSet;

import Server.sqlfunc.SQLFunc;
import Server.tableforresult.TableForResult;
import Server.textprompt.TextPrompt;
import Server.buttonflag.ButtonFlag;
import Server.copypastefunc.CopyPasteFunc;

public class DataQueryFrame extends JFrame implements DataQueryFrameInterface{	
	public CopyPasteFunc copyPasteFunc = new CopyPasteFunc();

	public TextPrompt tpTableName = new TextPrompt("Enter Table Name(s)",tableNameTextField);
	public TextPrompt tpListColumn = new TextPrompt("Enter List Column(s)",listColumnTextField);
	public TextPrompt tpOnKey = new TextPrompt("Enter Key",onKeyTextField);
	public TextPrompt tpCondition = new TextPrompt("Enter Condition",conditionTextField);
	public TextPrompt tpGroupBy = new TextPrompt("Enter Group Column(s)",groupByTextField);
	public TextPrompt tpHaving = new TextPrompt("Enter Having Column(s)",havingTextField);
	
	public DataQueryFrame(ButtonFlag buttonFlag){
		super("Data Query");
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setPreferredSize(new Dimension(480,520));
		pack();

		getContentPane().add(labelFrameName);
		getContentPane().add(labelTableName);
		getContentPane().add(labelColumnName);
		getContentPane().add(labelOnKey);
		getContentPane().add(labelCondition);
		getContentPane().add(labelHelp);
		getContentPane().add(labelGroupBy);
		getContentPane().add(labelHaving);
		getContentPane().add(labelIcon);

		getContentPane().add(tableNameTextField);
		getContentPane().add(conditionTextField);
		getContentPane().add(onKeyTextField);
		getContentPane().add(listColumnTextField);
		getContentPane().add(groupByTextField);
		getContentPane().add(havingTextField);

		getContentPane().add(buttonOk);

		labelBackGround.setBounds(0, 0, 480, 520);
		labelBackGround.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/backgroundDQ.png"));

		labelIcon.setBounds(100, 10, 50, 50);
		labelIcon.setIcon(new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/find.png"));

		labelFrameName.setFont(new Font("Ubuntu", 1, 24));
		labelFrameName.setForeground(new Color(254, 254, 254));
		labelFrameName.setHorizontalAlignment(SwingConstants.CENTER);
		labelFrameName.setBounds(160, 10, 180, 40);

		labelTableName.setFont(new Font("Ubuntu", 1, 16));
		labelTableName.setForeground(new Color(254, 254, 254));
		labelTableName.setHorizontalAlignment(SwingConstants.CENTER);
		labelTableName.setBounds(50, 70, 110, 40);

		labelColumnName.setFont(new Font("Ubuntu", 1, 16));
		labelColumnName.setForeground(new Color(254, 254, 254));
		labelColumnName.setHorizontalAlignment(SwingConstants.CENTER);
		labelColumnName.setBounds(50, 130, 110, 40);

		labelOnKey.setFont(new Font("Ubuntu", 1, 16));
		labelOnKey.setForeground(new Color(254, 254, 254));
		labelOnKey.setHorizontalAlignment(SwingConstants.CENTER);
		labelOnKey.setBounds(50, 190, 110, 40);

		labelCondition.setFont(new Font("Ubuntu", 1, 16));
		labelCondition.setForeground(new Color(254, 254, 254));
		labelCondition.setHorizontalAlignment(SwingConstants.CENTER);
		labelCondition.setBounds(50, 250, 110, 40);

		labelGroupBy.setFont(new Font("Ubuntu", 1, 16));
		labelGroupBy.setForeground(new Color(254, 254, 254));
		labelGroupBy.setHorizontalAlignment(SwingConstants.CENTER);	
		labelGroupBy.setBounds(50, 310, 110, 40);

		labelHaving.setFont(new Font("Ubuntu", 1, 16));
		labelHaving.setForeground(new Color(254, 254, 254));
		labelHaving.setHorizontalAlignment(SwingConstants.CENTER);
		labelHaving.setBounds(50, 370, 110, 40);

		labelHelp.setFont(new Font("Ubuntu", 1, 16));
		labelHelp.setForeground(new Color(254, 254, 254));
		labelHelp.setHorizontalAlignment(SwingConstants.CENTER);
		labelHelp.setBounds(30, 480, 49, 17);
		labelHelp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		if(labelHelp.getMouseListeners().length < 1){
			labelHelp.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent me){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/float.png");
					StringBuffer msg = new StringBuffer();
					msg.append("This is the window to retrieve data,");
					msg.append("\nyou enter table name into the textfield next to the Table Name 's label.");
					msg.append("\nIn the textfield next to the List Column 's label,");
					msg.append("\nyou'll enter a list of data columns in the table that you want to retrieve");
					msg.append("\nIn the textfield next to the OnKey Column 's label,");
					msg.append("\nyou'll enter a column in the table that you want to retrieve");
					msg.append("\nIn the textfield next to the Condition 's label,");
					msg.append("\nyou'll enter conditions that you want to retrieve");
					msg.append("\nIn the textfield next to the Group By 's label,");
					msg.append("\nyou'll enter columns that you want to group");
					msg.append("\nIn the textfield next to the Having 's label,");
					msg.append("\nyou'll enter conditions that you want to group");
					msg.append("\nIn the textfield next to the Order By 's label,");
					msg.append("\nyou'll enter columns in the table that you want to order by");
					msg.append("\nthe names of the columns will be separated by ','.");
					msg.append("\nClick OK to complete the retrieval.");
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
		buttonOk.setForeground(new Color(254, 254, 254));
		buttonOk.setBounds(200, 450, 100, 50);
		buttonOk.setBackground(new Color(254, 120, 4));
		if(buttonOk.getActionListeners().length < 1){
			buttonOk.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					Icon icon = new ImageIcon("/home/mylaptop/AppDatabase/DatabaseOfResApp/Resource/wrong.png"); 
					SQLFunc sqlFunc = new SQLFunc();
					String tableName = new String(tableNameTextField.getText());
					String listColumn = new String(listColumnTextField.getText());
					String onKey = new String(onKeyTextField.getText());
					String condition = new String(conditionTextField.getText());
					String groupBy = new String(groupByTextField.getText());
					String having = new String(havingTextField.getText());
					ResultSet result = sqlFunc.dataQuery(tableName,listColumn,onKey,condition,groupBy,having);
					if(result == null){						
						JOptionPane.showMessageDialog(null,"Error In Query Command","Help Of DataQueryFrame",JOptionPane.INFORMATION_MESSAGE,icon);
						return ;
					}
					else{
						new TableForResult(result);
						tableNameTextField.setText("");
						listColumnTextField.setText("");
						conditionTextField.setText("");
						groupByTextField.setText("");
						havingTextField.setText("");
						onKeyTextField.setText("");
					}
				}
			});
		}

		tpTableName.setForeground(new Color(208, 206, 206));
		tpListColumn.setForeground(new Color(208, 206, 206));
		tpOnKey.setForeground(new Color(208, 206, 206));
		tpCondition.setForeground(new Color(208, 206, 206));
		tpGroupBy.setForeground(new Color(208, 206, 206));
		tpHaving.setForeground(new Color(208, 206, 206));
		
		tableNameTextField.setBounds(210, 70, 220, 40);
		listColumnTextField.setBounds(210, 130, 220, 40);
		onKeyTextField.setBounds(210, 190, 220, 40);
		conditionTextField.setBounds(210, 250, 220, 40);
		groupByTextField.setBounds(210, 310, 220, 40);
		havingTextField.setBounds(210, 370, 220, 40);

		copyPasteFunc.addPopUpMenu(tableNameTextField);
		copyPasteFunc.addPopUpMenu(listColumnTextField);
		copyPasteFunc.addPopUpMenu(onKeyTextField);
		copyPasteFunc.addPopUpMenu(conditionTextField);
		copyPasteFunc.addPopUpMenu(groupByTextField);
		copyPasteFunc.addPopUpMenu(havingTextField);

		copyPasteFunc.addPasteToArea();
		copyPasteFunc.addCopyToArea();
		copyPasteFunc.addSelectAllToArea();
		copyPasteFunc.addCutToArea();

		copyPasteFunc.addFocusListen(tableNameTextField);
		copyPasteFunc.addFocusListen(listColumnTextField);
		copyPasteFunc.addFocusListen(onKeyTextField);
		copyPasteFunc.addFocusListen(conditionTextField);
		copyPasteFunc.addFocusListen(groupByTextField);
		copyPasteFunc.addFocusListen(havingTextField);

		getContentPane().add(labelBackGround);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				tableNameTextField.setText("");
				listColumnTextField.setText("");
				conditionTextField.setText("");
				groupByTextField.setText("");
				havingTextField.setText("");
				onKeyTextField.setText("");

				buttonFlag.buttonQueryFlag = 1;
				buttonFlag.buttonRefreshFlag = 1;
			}
		});
	}
}