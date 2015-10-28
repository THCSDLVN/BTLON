package dataqueryframe;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
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

import buttonflag.ButtonFlag;
import sqlfunc.SQLFunc;
import tableforresult.TableForResult;

public class DataQueryFrame extends JFrame implements MouseListener,DataQueryFrameInterface{
	public String arrangement = new String("");
	public ButtonGroup rbGroup = new ButtonGroup();
	public JRadioButton[] rb = new JRadioButton[3];
	public final int RB_MAX = rb.length - 1;
	public int rbSelected = RB_MAX;
	
	public DataQueryFrame(ButtonFlag buttonFlag){
		super("Data Query");
		getContentPane().setLayout(null);
		setVisible(true);
		setResizable(false);
		setPreferredSize(new Dimension(485,580));
		pack();

		getContentPane().add(labelFrameName);
		getContentPane().add(labelTableName);
		getContentPane().add(labelColumnName);
		getContentPane().add(labelOnKey);
		getContentPane().add(labelCondition);
		getContentPane().add(labelHelp);
		getContentPane().add(labelGroupBy);
		getContentPane().add(labelHaving);
		getContentPane().add(labelOrderBy);

		getContentPane().add(tableNameTextField);
		getContentPane().add(conditionTextField);
		getContentPane().add(onKeyTextField);
		getContentPane().add(listColumnTextField);
		getContentPane().add(groupByTextField);
		getContentPane().add(havingTextField);
		getContentPane().add(orderByTextField);

		getContentPane().add(buttonOk);

		labelFrameName.setFont(new Font("Ubuntu", 1, 24));
		labelFrameName.setForeground(new Color(246, 9, 9));
		labelFrameName.setHorizontalAlignment(SwingConstants.CENTER);
		labelFrameName.setBounds(170, 10, 160, 40);

		labelTableName.setFont(new Font("Ubuntu", 1, 16));
		labelTableName.setForeground(new Color(22, 117, 245));
		labelTableName.setHorizontalAlignment(SwingConstants.CENTER);
		labelTableName.setBorder(BorderFactory.createEtchedBorder());
		labelTableName.setBounds(70, 70, 110, 40);

		labelColumnName.setFont(new Font("Ubuntu", 1, 16));
		labelColumnName.setForeground(new Color(22, 117, 245));
		labelColumnName.setHorizontalAlignment(SwingConstants.CENTER);
		labelColumnName.setBorder(BorderFactory.createEtchedBorder());
		labelColumnName.setBounds(70, 130, 110, 40);

		labelOnKey.setFont(new Font("Ubuntu", 1, 16));
		labelOnKey.setForeground(new Color(22, 117, 245));
		labelOnKey.setHorizontalAlignment(SwingConstants.CENTER);
		labelOnKey.setBorder(BorderFactory.createEtchedBorder());
		labelOnKey.setBounds(70, 190, 110, 40);

		labelCondition.setFont(new Font("Ubuntu", 1, 16));
		labelCondition.setForeground(new Color(22, 117, 245));
		labelCondition.setHorizontalAlignment(SwingConstants.CENTER);
		labelCondition.setBorder(BorderFactory.createEtchedBorder());
		labelCondition.setBounds(70, 250, 110, 40);

		labelGroupBy.setFont(new Font("Ubuntu", 1, 16));
		labelGroupBy.setForeground(new Color(22, 117, 245));
		labelGroupBy.setHorizontalAlignment(SwingConstants.CENTER);
		labelGroupBy.setBorder(BorderFactory.createEtchedBorder());
		labelGroupBy.setBounds(70, 310, 110, 40);

		labelHaving.setFont(new Font("Ubuntu", 1, 16));
		labelHaving.setForeground(new Color(22, 117, 245));
		labelHaving.setHorizontalAlignment(SwingConstants.CENTER);
		labelHaving.setBorder(BorderFactory.createEtchedBorder());
		labelHaving.setBounds(70, 370, 110, 40);

		labelOrderBy.setFont(new Font("Ubuntu", 1, 16));
		labelOrderBy.setForeground(new Color(22, 117, 245));
		labelOrderBy.setHorizontalAlignment(SwingConstants.CENTER);
		labelOrderBy.setBorder(BorderFactory.createEtchedBorder());
		labelOrderBy.setBounds(70, 430, 110, 40);

		labelHelp.setFont(new Font("Ubuntu", 1, 16));
		labelHelp.setForeground(new Color(22, 117, 245));
		labelHelp.setHorizontalAlignment(SwingConstants.CENTER);
		labelHelp.setBounds(20, 550, 49, 17);
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
		buttonOk.setForeground(new Color(22, 117, 245));
		buttonOk.setBounds(190, 520, 100, 50);
		buttonOk.setBackground(Color.WHITE);
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
					String orderBy = new String(orderByTextField.getText());
					ResultSet result = sqlFunc.dataQuery(tableName,listColumn,onKey,condition,groupBy,having,orderBy,arrangement);
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
						orderByTextField.setText("");
						onKeyTextField.setText("");
						arrangement = "";
					}
				}
			});
		}
		
		tableNameTextField.setBounds(230, 70, 200, 40);
		tableNameTextField.setFont(new Font("Ubuntu" , 1, 18));
		
		listColumnTextField.setFont(new Font("Ubuntu" , 1, 18));
		listColumnTextField.setBounds(230, 130, 200, 40);
		
		onKeyTextField.setBounds(230, 190, 200, 40);
		onKeyTextField.setFont(new Font("Ubuntu" , 1, 18));
		
		conditionTextField.setBounds(230, 250, 200, 40);
		conditionTextField.setFont(new Font("Ubuntu" , 1, 18));
		
		groupByTextField.setFont(new Font("Ubuntu" , 1, 18));
		groupByTextField.setBounds(230, 310, 200, 40);
		
		havingTextField.setFont(new Font("Ubuntu" , 1, 18));
		havingTextField.setBounds(230, 370, 200, 40);
		
		orderByTextField.setFont(new Font("Ubuntu" , 1, 18));
		orderByTextField.setBounds(230, 430, 200, 40);

		rb[0] = new JRadioButton("ASC");
		rb[1] = new JRadioButton("DESC");
		rb[2] = new JRadioButton();
		
		rb[0].addMouseListener(this);
		rb[1].addMouseListener(this);
		rb[2].addMouseListener(this);

		rb[0].setBounds(230, 490, 70, 21);
		rb[1].setBounds(360, 490, 70, 21);
		
		rbGroup.add(rb[0]);
		rbGroup.add(rb[1]);
		rbGroup.add(rb[2]);
		getContentPane().add(rb[0]);
		getContentPane().add(rb[1]);
		
		rb[rbSelected].setSelected(true);

		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				buttonFlag.buttonInsertFlag = 1;
				buttonFlag.buttonUpdateFlag = 1;
				buttonFlag.buttonDeleteFlag = 1;
				buttonFlag.buttonQueryFlag = 1;

				tableNameTextField.setText("");
				listColumnTextField.setText("");
				conditionTextField.setText("");
				groupByTextField.setText("");
				havingTextField.setText("");
				orderByTextField.setText("");
				onKeyTextField.setText("");
				arrangement = "";

				rbGroup.clearSelection();
			}
		});
	}
	
	public void mouseClicked(MouseEvent me){
		for(int x = 0; x < RB_MAX; x++){
			if(me.getSource() == rb[x]){
				if(x != rbSelected){
					rbSelected = x;
				}
				else{
					rb[RB_MAX].setSelected(true);
					rbSelected = RB_MAX;
				}
				if(rb[0].isSelected()){
					arrangement = "ASC";
				}
				else if(rb[1].isSelected()){
					arrangement = "DESC";
				}
				else if(rb[2].isSelected()){
					arrangement = "";
				}
				break;
			}
		}
	}
	public void mousePressed(MouseEvent me){}
	public void mouseReleased(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}
}