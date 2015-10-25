package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLayeredPane;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.Toolkit;

public class restaurant extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					restaurant frame = new restaurant();
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
	public restaurant() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\6520\\workspace\\gui\\img\\restaurant_icon.png"));
		setTitle("Restaurant");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnlogout = new JButton("");
		btnlogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				login.main(null);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-out-icon.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-icon.png")));
			}
		});
		btnlogout.setIcon(new ImageIcon(this.getClass().getResource("/door-icon.png")));
		btnlogout.setBounds(734, 11, 50, 50);
		contentPane.add(btnlogout);
		
		JButton btnrefresh = new JButton("");
		btnrefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				Image ref = (new ImageIcon(this.getClass().getResource("/refresh.png"))).getImage();
				Image hlgt = ref.getScaledInstance(36, 36, Image.SCALE_SMOOTH);
				btnrefresh.setIcon(new ImageIcon(hlgt));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnrefresh.setIcon(new ImageIcon(this.getClass().getResource("/refresh.png")));
			}
		});
		btnrefresh.setIcon(new ImageIcon(this.getClass().getResource("/refresh.png")));
		btnrefresh.setBounds(665, 11, 50, 50);
		contentPane.add(btnrefresh);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 200, 774, 361);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		JButton btnRequest = new JButton("");
		btnRequest.setBounds(315, 25, 150, 150);
		layeredPane.add(btnRequest);
		btnRequest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnRequest.setIcon(null);
				btnRequest.setText("Request");
				btnRequest.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnRequest.setIcon(new ImageIcon(this.getClass().getResource("/request-128.png")));
				btnRequest.setText(null);
			}
		});
		btnRequest.setIcon(new ImageIcon(this.getClass().getResource("/request-128.png")));
		
		JButton btnServe = new JButton("");
		btnServe.setBounds(115, 194, 150, 150);
		layeredPane.add(btnServe);
		btnServe.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnServe.setIcon(null);
				btnServe.setText("Served");
				btnServe.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnServe.setText(null);
				btnServe.setIcon(new ImageIcon(this.getClass().getResource("/served.png")));
			}
		});
		btnServe.setIcon(new ImageIcon(this.getClass().getResource("/served.png")));
		btnServe.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 18));
		
		JButton btnMenu = new JButton("");
		btnMenu.setBounds(515, 194, 150, 150);
		layeredPane.add(btnMenu);
		btnMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMenu.setIcon(null);
				btnMenu.setText("Food Menu");
				btnMenu.setFont(new Font("Magneto", Font.BOLD | Font.ITALIC, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMenu.setIcon(new ImageIcon(this.getClass().getResource("/food.png")));
				btnMenu.setText(null);
			}
		});
		btnMenu.setIcon(new ImageIcon(this.getClass().getResource("/food.png")));
		
		JButton button = new JButton("");
		button.setEnabled(false);
		button.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\007355-blue-jelly-icon-arrows-arrow-styled-left.png"));
		button.setBounds(596, 11, 50, 50);
		contentPane.add(button);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 51), null));
		panel.setBounds(10, 11, 487, 178);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\restaurant_icon.png"));
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(204, 153, 0), new Color(204, 153, 51)));
		label.setBounds(0, 0, 141, 141);
		panel.add(label);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Rsname.png"));
		label_1.setBounds(151, 11, 28, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Name of restaurant");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_2.setBounds(183, 11, 304, 28);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\res_phone.png"));
		label_3.setBounds(151, 43, 28, 28);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Phone number");
		label_4.setBounds(183, 43, 304, 28);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("");
		label_5.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\AddressIcon.png"));
		label_5.setBounds(151, 76, 28, 28);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("Address");
		label_6.setBounds(183, 76, 304, 28);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("");
		label_7.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\facebook.png"));
		label_7.setBounds(151, 109, 28, 28);
		panel.add(label_7);
		
		JLabel label_8 = new JLabel("Facebook");
		label_8.setBounds(183, 109, 304, 28);
		panel.add(label_8);
		
		JLabel label_9 = new JLabel("");
		label_9.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\star.png"));
		label_9.setBounds(32, 143, 30, 30);
		panel.add(label_9);
		
		JLabel label_10 = new JLabel("5.0");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_10.setBounds(77, 143, 30, 30);
		panel.add(label_10);
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnServe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
	}
}
