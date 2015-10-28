package dataqueryframe;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public interface DataQueryFrameInterface {
	public JButton buttonOk = new JButton("OK");
	public JLabel labelFrameName = new JLabel("DATA QUERY");
	public JLabel labelTableName = new JLabel("Table Name");
	public JLabel labelColumnName = new JLabel("List Column");
	public JLabel labelOnKey = new JLabel("On Key");
	public JLabel labelCondition = new JLabel("Condition");
	public JLabel labelGroupBy = new JLabel("Group By");
	public JLabel labelHaving = new JLabel("Having");
	public JLabel labelOrderBy = new JLabel("Order By");
	public JLabel labelHelp = new JLabel("HELP");

	public JTextField tableNameTextField = new JTextField();
	public JTextField listColumnTextField = new JTextField();
	public JTextField onKeyTextField = new JTextField();
	public JTextField conditionTextField = new JTextField();
	public JTextField groupByTextField = new JTextField();
	public JTextField havingTextField = new JTextField();
	public JTextField orderByTextField = new JTextField();
}