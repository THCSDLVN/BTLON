

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class SelectedRestaurantGUI extends JFrame {

	private JPanel contentPane;

	int flag = 0; // notlove
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedRestaurantGUI frame = new SelectedRestaurantGUI("Demo Restaurant");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectedRestaurantGUI(String SelectedRestaurant) {
		super(SelectedRestaurant);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 415, 630);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel resInfoPanel = new JPanel();
		resInfoPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 102, 153), null));
		resInfoPanel.setBounds(12, 80, 391, 210);
		contentPane.add(resInfoPanel);
		resInfoPanel.setLayout(null);
		
		JLabel resAvatarLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/restaurant_icon.png")));
		resAvatarLbl.setBounds(12, 12, 160, 131);
		resInfoPanel.add(resAvatarLbl);
		
		JLabel resNameIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/Rsname.png")));
		resNameIconLbl.setBounds(184, 12, 45, 40);
		resInfoPanel.add(resNameIconLbl);
		
		JLabel resPhoneIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/res_phone.png")));
		resPhoneIconLbl.setBounds(184, 65, 45, 40);
		resInfoPanel.add(resPhoneIconLbl);
		
		JLabel resAddIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/AddressIcon.png")));
		resAddIconLbl.setBounds(184, 117, 45, 40);
		resInfoPanel.add(resAddIconLbl);
		
		JLabel resFaceIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/facebook.png")));
		resFaceIconLbl.setBounds(184, 158, 45, 40);
		resInfoPanel.add(resFaceIconLbl);
		
		SQLConnection c = new SQLConnection();
		c.getResInfo(SelectedRestaurant);
		List<List<String>> info = c.resultList;
		String resInfo[] = new String[info.get(0).size()];
		int x = 0;
		for(x=0;x< info.get(0).size();x++)
			resInfo[x] = info.get(0).get(x);
		
		JLabel resNameLbl = new JLabel(resInfo[0]);
		resNameLbl.setBounds(240, 12, 151, 32);
		resInfoPanel.add(resNameLbl);
		
		JLabel resPhoneLbl = new JLabel(resInfo[1]);
		resPhoneLbl.setBounds(241, 65, 151, 32);
		resInfoPanel.add(resPhoneLbl);
		
		JLabel resAddLbl = new JLabel(resInfo[2]);
		resAddLbl.setBounds(241, 117, 151, 32);
		resInfoPanel.add(resAddLbl);
		
		JLabel resFaceLbl = new JLabel(resInfo[3]);
		resFaceLbl.setBounds(241, 158, 151, 32);
		resInfoPanel.add(resFaceLbl);
		
		JLabel likeIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/ResResource/like.png")));
		likeIconLbl.setBounds(78, 151, 45, 47);
		resInfoPanel.add(likeIconLbl);
		
		JLabel likeNumberLbl = new JLabel("1000");
		likeNumberLbl.setBounds(24, 168, 70, 15);
		resInfoPanel.add(likeNumberLbl);
		
		JButton backBtn = new JButton("");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(flag == 0){
					backBtn.setIcon(new ImageIcon(this.getClass().getResource("/Resource/broken_heart.png")));
					flag = 1;
				}
				else{
					backBtn.setIcon(new ImageIcon(this.getClass().getResource("/Resource/flying_heart.png")));
					flag = 0;
				}
			}
		});
		backBtn.setIcon(new ImageIcon(this.getClass().getResource("/Resource/flying_heart.png")));
		backBtn.setBounds(344, 12, 58, 56);
		contentPane.add(backBtn);
		
		JLabel resManagerIconLbl = new JLabel(new ImageIcon(this.getClass().getResource("/Resource/AppName.png")));
		resManagerIconLbl.setBounds(12, 12, 325, 56);
		contentPane.add(resManagerIconLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 345, 391, 200);
		contentPane.add(scrollPane);
		
		c.getMenu(SelectedRestaurant);
		String menu[] = new String[c.resultList.size()];
		int index = 0;
		for(List<String> innerLs : c.resultList) {
			for (Iterator<String> i = innerLs.iterator(); i.hasNext();) {
				menu[index++] = i.next();
			}
		}
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return menu.length;
			}
			public Object getElementAt(int index) {
				return menu[index];
			}
		});
		scrollPane.setViewportView(list);
		
		JLabel menuLabel = new JLabel("Menu",JLabel.CENTER);
		menuLabel.setIcon(new ImageIcon(this.getClass().getResource("/ResResource/menu.png")));
		menuLabel.setFont(new Font("",Font.ITALIC,14));
		menuLabel.setBounds(12, 302, 391, 37);
		contentPane.add(menuLabel);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(286, 565, 117, 25);
		contentPane.add(btnOrder);
	}
}
