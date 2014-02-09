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

public class TamaGUILOGIN_TEST {

	private JFrame frameLogIn;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;
	private JTextField txtReenterPassword;
	private JTextField txtTypeInCatcha;
	private JLabel lblCaptcha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TamaGUILOGIN_TEST window = new TamaGUILOGIN_TEST();
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
	public TamaGUILOGIN_TEST() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameLogIn = new JFrame();
		frameLogIn.getContentPane().setBackground(Color.WHITE);
		frameLogIn.setForeground(Color.WHITE);
		frameLogIn.getContentPane().setForeground(Color.WHITE);
		frameLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arild\\Documents\\GitHub\\TamaProj03\\image\\Baby\\b3.png"));
		frameLogIn.setResizable(false);
		frameLogIn.setBounds(100, 100, 577, 476);
		frameLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogIn.getContentPane().setLayout(null);
		
		textFieldUserName = new JTextField();
		textFieldUserName.setToolTipText("Enter Username");
		textFieldUserName.setText("Enter Username");
		textFieldUserName.setBounds(261, 256, 295, 26);
		frameLogIn.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);
		
		txtEnterPassword = new JTextField();
		txtEnterPassword.setToolTipText("Enter Password");
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setBounds(261, 298, 295, 26);
		frameLogIn.getContentPane().add(txtEnterPassword);
		txtEnterPassword.setColumns(10);
		
		JButton btnLogin = new JButton("Bail Out!");
		btnLogin.setToolTipText("Don't Accept the terms");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnLogin.setBounds(278, 398, 173, 29);
		frameLogIn.getContentPane().add(btnLogin);
		
		JButton btnCreateUser = new JButton("Accept Terms");
		btnCreateUser.setToolTipText("Accept Terms");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCreateUser.setBounds(90, 398, 173, 29);
		frameLogIn.getContentPane().add(btnCreateUser);
		
		JTextArea textTerms = new JTextArea();
		textTerms.setToolTipText("Terms of Agreements");
		textTerms.setEnabled(false);
		textTerms.setBounds(15, 16, 541, 224);
		frameLogIn.getContentPane().add(textTerms);
		
		txtReenterPassword = new JTextField();
		txtReenterPassword.setToolTipText("Reenter");
		txtReenterPassword.setText("Reenter Password");
		txtReenterPassword.setBounds(261, 339, 295, 26);
		frameLogIn.getContentPane().add(txtReenterPassword);
		txtReenterPassword.setColumns(10);
		
		lblCaptcha = new JLabel("Capthca");
		lblCaptcha.setToolTipText("Capthca");
		lblCaptcha.setBounds(15, 256, 231, 72);
		frameLogIn.getContentPane().add(lblCaptcha);
		
		txtTypeInCatcha = new JTextField();
		txtTypeInCatcha.setToolTipText("Type in Captcha");
		txtTypeInCatcha.setText("Type in Catcha");
		txtTypeInCatcha.setBounds(15, 339, 231, 26);
		frameLogIn.getContentPane().add(txtTypeInCatcha);
		txtTypeInCatcha.setColumns(10);
	}
}
