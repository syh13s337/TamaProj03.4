package tamaDB;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tamaSystem.GameEngine;

/*LOG IN GUI CLASS
 * This class i all about loging in/launcher.
 * Its purpose is to get information about user, username and password.
 * Give the top 10 Score
 * Give information about updates, and about Tama.
 * 
 * 
 * NEED TO UPDATE/CHANGE:
 * THIS CLASS NEED TO COME AFTER GAME ENGINE.
 * SO IT CAN SEND INPUT TO MYSQL CLASS.
 * 
 * GET PROJECT NAME FROM GAME ENGINE!
 * 
 * IF user creats a new USER, Tama creat user GUI pops up.
 * THERE user need to type in new
 * userame
 * passowrd
 * retype password
 * captcha. <- skickar kanske från DB? eller bara random bilder från en res folder.
 * 
 * TERMS OF AGREEMENTS
 * 
 * 
 */
public class TamaGUILogIn {

	private JFrame frameLogIn;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;

	private JTextArea textLogInInformation;
	public void setTextLogInInformation(JTextArea textLogInInformation) {
		this.textLogInInformation = textLogInInformation;
	}

	private GameEngine ge;
	private UserEngine ue;
	private TamaGUICreateUser tgcu;
	private TamaGUILogIn tgli;
	private MySQLEngine mysql;
	
	public void loginStarter(GameEngine ge,TamaGUILogIn tgli, UserEngine ue, MySQLEngine mysql){
		this.ge = ge;
		this.tgli = tgli;
		this.ue = ue;
		this.mysql = mysql;
		frameLogIn.setVisible(true);

		mysql.getScores();
	}

	public TamaGUILogIn() {
		initialize();
	}

	private void initialize() {
		frameLogIn = new JFrame();
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
		textFieldUserName.setBounds(15, 357, 353, 26);
		frameLogIn.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);

		txtEnterPassword = new JTextField();
		txtEnterPassword.setToolTipText("Enter Password");
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setBounds(15, 399, 353, 26);
		frameLogIn.getContentPane().add(txtEnterPassword);
		txtEnterPassword.setColumns(10);

		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnLogIn();
			}
		});
		btnLogin.setBounds(383, 356, 173, 29);
		frameLogIn.getContentPane().add(btnLogin);

		JButton btnCreateUser = new JButton("Create User");
		btnCreateUser.setToolTipText("Create User");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateUser();
			}
		});
		btnCreateUser.setBounds(383, 398, 173, 29);
		frameLogIn.getContentPane().add(btnCreateUser);

		textLogInInformation = new JTextArea();
		textLogInInformation.setText("test");
		textLogInInformation.setToolTipText("Log in information");
		textLogInInformation.setEnabled(false);
		textLogInInformation.setBounds(15, 16, 541, 325);
		frameLogIn.getContentPane().add(textLogInInformation);
	}
	
	private void btnLogIn(){
		ue.userLogIn(textFieldUserName.getText(), txtEnterPassword.getText());
	}

	private void btnCreateUser(){
		if (tgcu==null){
			tgcu = new TamaGUICreateUser(ge, tgli, ue, tgcu);
			tgcu.tamaGUICreatUserStart();
			frameLogIn.setVisible(false);
		}
		else if (tgcu != null){
		}
	}

	public void popUpMessage(String inStr){
		JOptionPane.showMessageDialog(null, inStr, "Message", JOptionPane.ERROR_MESSAGE); 		
	}

}
