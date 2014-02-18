package tamaDB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
public class DBTamaGUILogIn {

	private JFrame frameLogIn;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;
	private int tmpXForButton = 1;
	private JButton btnHighScore; //BUTTON HAVE 2 FUNKTIONS, GET ABOUT INFO AND GET HIGHSCORE

	private JTextArea textLogInInformation;
	public void setTextLogInInformation(String textLogInInformation) {
		this.textLogInInformation.setText(textLogInInformation);
	}

	private DBUserEngine dbue;
	private DBTamaGUICreateUser dbtgcu;
	private DBTamaGUILogIn dbtgli;
	private String aboutInfo = GameEngine.TAMA_VERSION;


	public void loginStarter(DBUserEngine dbue, DBTamaGUILogIn dbtgli){
		this.dbue = dbue;
		this.dbtgli = dbtgli;

		dbue.showScore();
		frameLogIn.setVisible(true);
	}

	public void showFrame(){
		frameLogIn.setVisible(true);		
	}

	public void hideFrame(){
		frameLogIn.setVisible(false);		
	}

	public DBTamaGUILogIn() {
		initialize();
	}

	private void initialize() {
		frameLogIn = new JFrame();
		frameLogIn.getContentPane().setBackground(Color.WHITE);
		frameLogIn.setTitle(GameEngine.TAMA_VERSION + " Log In GUI");
		frameLogIn.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\Baby\\b3.png"));
		frameLogIn.setResizable(false);
		frameLogIn.setBounds(100, 100, 577, 476);
		frameLogIn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameLogIn.getContentPane().setLayout(null);

		textLogInInformation = new JTextArea();
		textLogInInformation.setEditable(false);
		textLogInInformation.setBackground(Color.WHITE);
		textLogInInformation.setForeground(Color.BLACK);
		textLogInInformation.setFont(new Font("Verdana", Font.PLAIN, 14));
		textLogInInformation.setText("Log in information");
		textLogInInformation.setToolTipText("Log in information");
		textLogInInformation.setEnabled(true);
		textLogInInformation.setBounds(15, 16, 541, 325);
		frameLogIn.getContentPane().add(textLogInInformation);

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
				btnLogIn();
			}
		});
		btnLogin.setBounds(200, 356, 173, 29);
		frameLogIn.getContentPane().add(btnLogin);

		JButton btnCreateUser = new JButton("Create new user");
		btnCreateUser.setToolTipText("Create new user?");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateUser();
			}
		});
		btnCreateUser.setBounds(200, 398, 173, 29);
		frameLogIn.getContentPane().add(btnCreateUser);

		//HIGHSCORE BUTTN, 2 FUNKTIONS, GET ABOUT INFO AND HIGHSCORE.
		btnHighScore = new JButton("About");
		btnHighScore.setToolTipText("Show High Score");
		btnHighScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tmpXForButton == 1){
					btnHighScore.setText("High Score");
					setTextLogInInformation(aboutInfo);
					tmpXForButton++;
				}
				else if (tmpXForButton == 2){
					dbue.showScore();
					btnHighScore.setText("About");
					tmpXForButton--;
				}
			}
		});
		btnHighScore.setBounds(383, 356, 173, 29);
		frameLogIn.getContentPane().add(btnHighScore);

		JButton btnDonation = new JButton("Donation ");
		btnDonation.setToolTipText("I KNOW YOU WANT TO!");
		btnDonation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUpMessage("We can't take your money right now. Stupid fancy laws..... ");
			}
		});
		btnDonation.setBounds(383, 400, 173, 29);
		frameLogIn.getContentPane().add(btnDonation);
	}

	private void btnLogIn(){
		dbue.userLogIn(textFieldUserName.getText(), txtEnterPassword.getText());
	}

	private void btnCreateUser(){
		if (dbtgcu==null){
			dbtgcu = new DBTamaGUICreateUser(dbtgli, dbue, dbtgcu);
			dbtgcu.tamaGUICreatUserStart();
			frameLogIn.setVisible(false);
		}
		else{
			dbtgcu.showFrame();	
			frameLogIn.setVisible(false);

		}
	}

	//POPUP
	//SELF NOT: Send everything that user need to know here! Future Arild!
	public void popUpMessage(String inStr){
		JOptionPane.showMessageDialog(null, inStr, GameEngine.TAMA_VERSION + "Messages! ", JOptionPane.ERROR_MESSAGE); 		
	}
}
