package gui;

import java.awt.EventQueue;
import java.sql.DriverManager;
import java.text.MessageFormat;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.synth.SynthStyleFactory;
import javax.tools.ForwardingJavaFileObject;
import javax.xml.ws.handler.MessageContext;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.GrayFilter;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.PageAttributes.OriginType;
import java.awt.event.MouseMotionAdapter;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.util.Map;
import java.util.Random;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JProgressBar;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.Component;

public class login {

	private JFrame frmLoginAsRestaurant;
	private JTextField usrnameField;
	private JPasswordField passwordField;
	private JLabel title;
	private JButton btnlogin;
	private JButton btnback;
	private JLabel lblHelp;
	private JLabel lblSignUp;
	private JPanel panel;
	private JLabel background;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
					window.frmLoginAsRestaurant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	private signup Signup = new signup();
	private Connection conn = null;
	
	public login() {
		conn = SQLConnection.DBConnect();
		if(conn == null) System.exit(0);
		initialize();	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private String usrname = "";
	private String passd = "";
	
	private void initialize() {
		frmLoginAsRestaurant = new JFrame();
		frmLoginAsRestaurant.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/login.png")));
		frmLoginAsRestaurant.setResizable(false);
		frmLoginAsRestaurant.setTitle("Login as restaurant");
		frmLoginAsRestaurant.setBounds((new Random()).nextInt(300),(new Random()).nextInt(300), 464, 311);
		frmLoginAsRestaurant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginAsRestaurant.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Resname");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUsername.setBounds(82, 82, 72, 14);
		frmLoginAsRestaurant.getContentPane().add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(82, 118, 72, 14);
		frmLoginAsRestaurant.getContentPane().add(lblNewLabel);
		
		usrnameField = new JTextField();
		usrnameField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			public void changed() {
				usrname = usrnameField.getText();
				if(usrname.length() * passd.length() > 0)
				{
					btnlogin.setEnabled(true);
				}
				else
				{
					btnlogin.setEnabled(false);
				}
			}
		});
		usrnameField.setBounds(177, 78, 123, 25);
		frmLoginAsRestaurant.getContentPane().add(usrnameField);
		usrnameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changed();
			}
			
			public void changed()
			{
				passd = new String(passwordField.getPassword()); 
				if(usrname.length() * passd.length() > 0)
				{
					btnlogin.setEnabled(true);
				}
				else
				{
					btnlogin.setEnabled(false);
				}
			}
		});
		passwordField.setBounds(177, 114, 123, 25);
		frmLoginAsRestaurant.getContentPane().add(passwordField);
		
		title = new JLabel("LOG IN YOUR RESTAURANT");
		title.setForeground(new Color(0, 51, 102));
		title.setFont(new Font("Magneto", Font.BOLD, 14));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(91, 11, 264, 46);
		frmLoginAsRestaurant.getContentPane().add(title);
		
		btnlogin = new JButton("LOG IN");
		btnlogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					if(btnlogin.isEnabled())
					{				
						String query = "SELECT RID,RestaurantName,Address,PhoneNumber,Facebook,Rate FROM restaurant WHERE ResName = '" + usrname +"' AND ResPass = '" + passd + "'";
						Statement stmt = conn.createStatement();
						SqlArrayList rsl = new SqlArrayList(stmt.executeQuery(query));
						if(rsl.getRownumber() > 0)
						{
							frmLoginAsRestaurant.dispose();
							restaurant res = new restaurant(rsl.getRow(0),conn);
							res.setVisible(true);
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Your username or password are incorrect","Login failed",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}catch(Exception ex)
				{
					JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnlogin.setIcon(new ImageIcon(this.getClass().getResource("/login.png")));
		btnlogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnlogin.setEnabled(false);
		btnlogin.setBounds(171, 169, 123, 36);
		frmLoginAsRestaurant.getContentPane().add(btnlogin,BorderLayout.CENTER);
		
		btnback = new JButton("");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnback.setBackground(UIManager.getColor("Button.light"));
		btnback.setIcon(new ImageIcon(this.getClass().getResource("/007355-blue-jelly-icon-arrows-arrow-styled-left.png")));
		btnback.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnback.setBounds(204, 218, 59, 36);
		frmLoginAsRestaurant.getContentPane().add(btnback,BorderLayout.CENTER);
		
		lblHelp = new JLabel("Help");
		lblHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHelp.setForeground(new Color(102, 51, 0));
		lblHelp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblHelp.addMouseListener(new MouseAdapter() {
			Font original;
			public void mouseEntered(MouseEvent e) {
				original = e.getComponent().getFont();
				Map attribute = original.getAttributes();
				attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				e.getComponent().setFont(original.deriveFont(attribute));
				frmLoginAsRestaurant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				e.getComponent().setFont(original);
				frmLoginAsRestaurant.setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon ic = new ImageIcon(this.getClass().getResource("/6e65c9db874c0cd88747b53d8f23af7c.png"));
				JOptionPane.showMessageDialog(null, "Some Information here..", "Help",JOptionPane.INFORMATION_MESSAGE,ic );
			}
		});
				lblHelp.setBounds(23, 261, 46, 14);
		frmLoginAsRestaurant.getContentPane().add(lblHelp);
		
		lblSignUp = new JLabel("Sign up");
		lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUp.setForeground(new Color(102, 51, 0));
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSignUp.addMouseListener(new MouseAdapter() {
			Font original;
			public void mouseEntered(MouseEvent e) {
				original = e.getComponent().getFont();
				Map attribute = original.getAttributes();
				attribute.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
				e.getComponent().setFont(original.deriveFont(attribute));
				frmLoginAsRestaurant.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
			public void mouseExited(MouseEvent e) {
				e.getComponent().setFont(original);
				frmLoginAsRestaurant.setCursor(Cursor.getDefaultCursor());
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!Signup.isShowing()) Signup = new signup();			
				Signup.setFocusable(true);
				Signup.setVisible(true);
			}
		});
		lblSignUp.setBounds(390, 261, 59, 14);
		frmLoginAsRestaurant.getContentPane().add(lblSignUp);
		
		background = new JLabel("");
		
		background.setIcon(new ImageIcon(this.getClass().getResource("/loginbkground.png")));
		background.setBounds(0, 0, 459, 285);
		frmLoginAsRestaurant.getContentPane().add(background);
		
		
	}
}
