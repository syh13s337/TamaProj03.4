package tamaDB;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DropMode;

public class DBTAMAGUILogIn_TEST {

	private JFrame frameLogIn;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;
	private JTextField txtReenterPassword;
	private JTextField txtTypeInCatcha;
	private JLabel lblCaptcha;
	private JTextArea textLogInInformation;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBTAMAGUILogIn_TEST window = new DBTAMAGUILogIn_TEST();
					window.frameLogIn.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBTAMAGUILogIn_TEST() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogIn = new JFrame();
		frameLogIn.setBackground(Color.WHITE);
		frameLogIn.getContentPane().setBackground(Color.WHITE);
		//		frameLogIn.setForeground(Color.WHITE);
		//		frameLogIn.getContentPane().setForeground(Color.WHITE);
		frameLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arild\\Documents\\GitHub\\TamaProj03\\image\\Baby\\b3.png"));
		frameLogIn.setResizable(false);
		frameLogIn.setBounds(100, 100, 577, 476);
		frameLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogIn.getContentPane().setLayout(null);

		textFieldUserName = new JTextField();
		textFieldUserName.setToolTipText("Enter Username");
		textFieldUserName.setText("Enter Username");
		textFieldUserName.setBounds(15, 357, 173, 26);
		frameLogIn.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);

		txtEnterPassword = new JTextField();
		txtEnterPassword.setToolTipText("Enter Password");
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setBounds(15, 399, 173, 26);
		frameLogIn.getContentPane().add(txtEnterPassword);
		txtEnterPassword.setColumns(10);

		JButton btnLogin = new JButton("Log In");
		btnLogin.setToolTipText("Log in?");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(200, 356, 173, 29);
		frameLogIn.getContentPane().add(btnLogin);

		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setToolTipText("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateUser.setBounds(200, 398, 173, 29);
		frameLogIn.getContentPane().add(btnCreateUser);

		textLogInInformation = new JTextArea();
		textLogInInformation.setEditable(false);
		textLogInInformation.setDropMode(DropMode.ON);
		textLogInInformation.setBackground(Color.WHITE);
		textLogInInformation.setForeground(Color.BLACK);
		textLogInInformation.setFont(new Font("Verdana", Font.PLAIN, 18));
		textLogInInformation.setText("Log in information");
		textLogInInformation.setToolTipText("Log in information");
		textLogInInformation.setBounds(15, 16, 541, 325);
		frameLogIn.getContentPane().add(textLogInInformation);
		
		JButton btnHighScore = new JButton("High Score");
		btnHighScore.setToolTipText("Show High Score");
		btnHighScore.setBounds(383, 356, 173, 29);
		frameLogIn.getContentPane().add(btnHighScore);
		
		JButton btnDonation = new JButton("Donation ");
		btnDonation.setToolTipText("Donate to this project");
		btnDonation.setBounds(385, 400, 173, 29);
		frameLogIn.getContentPane().add(btnDonation);
	}
}
