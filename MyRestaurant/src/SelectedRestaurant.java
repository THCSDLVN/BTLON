import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.AbstractListModel;

public class SelectedRestaurant extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectedRestaurant frame = new SelectedRestaurant("Nha Hang 123");
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
	public SelectedRestaurant(String SelectRestaurant) {
		super(SelectRestaurant);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setPreferredSize(new Dimension(470,530));
		pack();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("");
		contentPane.setBackground(Color.lightGray);
		
		JLabel label_1 = new JLabel(new ImageIcon(this.getClass().getResource("/Icons_Vertical_Markets_Restaurant.png")));
		label_1.setBounds(47, 37, 151, 152);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("New label");
		label_2.setBounds(224, 63, 202, 21);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("New label");
		label_3.setBounds(224, 96, 202, 21);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("New label");
		label_4.setBounds(224, 129, 202, 21);
		contentPane.add(label_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(67, 201, 329, 174);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"abcds", "asdasfd", "asdafgwe", "asfgdfhhg", "fweterhysrth", "sdfsdvsdv", "gregfdv", "sfcsfdg", "wfsdgreyer", "svsdgergwef", "sdgerfsd", "ergefsdf"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setBounds(183, 399, 117, 25);
		contentPane.add(btnOrder);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(23, 24, 413, 451);
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setOpaque(true);
		contentPane.add(lblNewLabel);
		label.setBackground(Color.lightGray);
		label.setOpaque(true);
		label.setBounds(0, 12, 470, 513);
		contentPane.add(label);
	}
}
