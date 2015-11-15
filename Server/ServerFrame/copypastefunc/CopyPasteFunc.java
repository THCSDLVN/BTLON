package copypastefunc;

import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.BorderFactory;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class CopyPasteFunc implements FocusListener {
	JPopupMenu menu = new JPopupMenu();
	JMenuItem cut = new JMenuItem("Cut");
	JMenuItem copy = new JMenuItem("Copy");
	JMenuItem paste = new JMenuItem("Paste");
	JMenuItem selectAll = new JMenuItem("Select All");
	JTextField previouslyFocusedTextBox = new JTextField();

	public CopyPasteFunc(){
		menu.add(cut);
		menu.add(copy);
		menu.add(paste);
		menu.add(selectAll);

		cut.setForeground(Color.WHITE);
		cut.setOpaque(true);
		cut.setBackground(new Color(0, 126, 168));
		
		copy.setForeground(Color.WHITE);
		copy.setOpaque(true);
		copy.setBackground(new Color(0, 140, 187));

		paste.setForeground(Color.WHITE);		
		paste.setOpaque(true);
		paste.setBackground(new Color(0, 126, 168));
		
		selectAll.setForeground(Color.WHITE);
		selectAll.setOpaque(true);
		selectAll.setBackground(new Color(0, 140, 187));
		
		cut.setBorder(BorderFactory.createLineBorder(new Color(0, 126, 168)));
		copy.setBorder(BorderFactory.createLineBorder(new Color(0, 140, 187)));
		paste.setBorder(BorderFactory.createLineBorder(new Color(0, 126, 168)));
		selectAll.setBorder(BorderFactory.createLineBorder(new Color(0, 140, 187)));

		menu.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 164)));
	}

	public void addCopyToArea(){
		copy.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				previouslyFocusedTextBox.copy();
			}
		});
	}

	public void addSelectAllToArea(){
		selectAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				previouslyFocusedTextBox.selectAll();
			}
		});
	}
	
	public void addPasteToArea(){
		paste.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				previouslyFocusedTextBox.paste();
				if(previouslyFocusedTextBox.getText().equals("copyPasteFunc.addPopUpMenu(tableName);")){
					previouslyFocusedTextBox.setText("");
				}
			}
		});
	}

	public void addCutToArea(){
		cut.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				previouslyFocusedTextBox.cut();
			}
		});
	}

	public void addPopUpMenu(JTextField textField){
		textField.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if (e.isPopupTrigger()){
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
			public void mouseReleased(MouseEvent e){
				if (e.isPopupTrigger()){
					menu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
	}

	public void addFocusListen(JTextField textField){
		textField.addFocusListener(this);
	}

	public void focusGained(FocusEvent ev) {
		if(ev.getSource() instanceof JTextField) {
			previouslyFocusedTextBox = (JTextField) ev.getSource();
		}
	}
	
	public void focusLost(FocusEvent ev) {
	
	}
}