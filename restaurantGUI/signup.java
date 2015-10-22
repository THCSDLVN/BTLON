package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.omg.PortableInterceptor.SUCCESSFUL;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.CompoundBorder;
import java.awt.Cursor;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

public class signup extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					signup frame = new signup();
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
	public signup() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/icon_signup.png")));
		setTitle("Sign up");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds((new Random()).nextInt(300)+100,(new Random()).nextInt(300), 398, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel signup = new JPanel();
		signup.setBackground(new Color(255, 255, 153));
		signup.setForeground(new Color(255, 255, 153));
		signup.setBounds(0, 0, 392, 432);
		contentPane.add(signup);
		signup.setLayout(null);
		
		JLabel label_1 = new JLabel("CREATE YOUR ACCOUNT");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(51, 153, 102));
		label_1.setFont(new Font("Lucida Console", Font.BOLD, 14));
		label_1.setBounds(10, 21, 372, 51);
		signup.add(label_1);
		
		JLabel label_2 = new JLabel("Username");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_2.setBounds(27, 107, 105, 20);
		signup.add(label_2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(175, 107, 112, 20);
		signup.add(textField);
		
		JLabel label_3 = new JLabel("Password");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_3.setBounds(27, 148, 105, 20);
		signup.add(label_3);
		
		JLabel label_4 = new JLabel("Re-type password");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_4.setBounds(27, 189, 138, 20);
		signup.add(label_4);
		
		JLabel label_5 = new JLabel("Facebook");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_5.setBounds(27, 230, 105, 20);
		signup.add(label_5);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(175, 230, 195, 20);
		signup.add(textField_1);
		
		JLabel label_6 = new JLabel("Address");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_6.setBounds(27, 271, 105, 20);
		signup.add(label_6);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(175, 271, 195, 20);
		signup.add(textField_2);
		
		JLabel label_7 = new JLabel("Phone number");
		label_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_7.setBounds(27, 312, 105, 20);
		signup.add(label_7);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(175, 312, 195, 20);
		signup.add(textField_3);
		
		JButton button = new JButton("Sign up");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				signup.setVisible(false);
				contentPane.getComponents()[1].setVisible(true);
			}
		});
		button.setIcon(new ImageIcon(this.getClass().getResource("/icon_signup.png")));
		button.setBounds(34, 364, 159, 46);
		signup.add(button);
		
		JButton button_1 = new JButton("Cancel");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		button_1.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		button_1.setBounds(198, 364, 159, 46);
		signup.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(175, 148, 112, 20);
		signup.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(175, 189, 112, 20);
		signup.add(passwordField_1);
		
		JPanel info = new JPanel();
		info.setLayout(null);
		info.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(51, 153, 102), new Color(51, 153, 153)));
		info.setBackground(new Color(204, 204, 255));
		info.setBounds(10, 83, 372, 268);
		signup.add(info);
		
		JPanel success = new JPanel();
		success.setVisible(false);
		success.setBounds(0, 0, 392, 432);
		contentPane.add(success);
		success.setLayout(null);
		
		JLabel message = new JLabel("YOUR ACCOUNT HAS BEEN CREATED");
		message.setFont(new Font("Arial", Font.BOLD, 15));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setBounds(0, 115, 392, 111);
		success.add(message);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\6520\\workspace\\gui\\img\\Check-icon.png"));
		btnNewButton.setBounds(171, 237, 50, 50);
		success.add(btnNewButton);
		
		JLabel label = new JLabel("LOG IN YOUR ACCOUNT");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(0, 51, 102));
		label.setFont(new Font("Magneto", Font.BOLD, 14));
		label.setBounds(10, 26, 264, 46);
		
	}
}
