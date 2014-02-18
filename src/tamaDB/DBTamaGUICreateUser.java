package tamaDB;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;

import tamaSystem.GameEngine;

public class DBTamaGUICreateUser {

	public JFrame frame;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;
	private JTextField txtReenterPassword;
	private JTextField txtTypeInCatcha;
	private JTextArea textTerms;
	private JTextField txtEnterEmail;

	private DBTamaGUILogIn dbtgli;
	private DBUserEngine dbue;
	private DBTamaGUICreateUser dbtgcu;

	//TERMS OF USE, IS LINKET TO JTextArea, textTerms.
	//SELF NOTE: Make up a awesome terms of use. If lady/girl over 18
	//they are legally bound to send naked pics of them self to you.
	private String termsOfUse = "BLALBLALBLABLABLA Tama, "
			+ "\nIf you accept you sell your soul to the devil, and Elvise ";

	/**
	 * Create the application.
	 */
	public DBTamaGUICreateUser(DBTamaGUILogIn dbtgli, DBUserEngine dbue, DBTamaGUICreateUser dbtgcu) {
		this.dbtgli = dbtgli;
		this.dbue = dbue;
		this.dbtgcu = dbtgcu;
		initialize();
	}

	public DBTamaGUICreateUser(){
	}

	public void tamaGUICreatUserStart(){
		frame.setVisible(true);			
	}
	
	public void showFrame(){
		frame.setVisible(true);			
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle(GameEngine.TAMA_VERSION + " Create User GUI");
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("image\\Baby\\b3.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 577, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textTerms = new JTextArea();
		textTerms.setEditable(false);
		textTerms.setText(termsOfUse);
		textTerms.setBackground(Color.WHITE);
		textTerms.setForeground(Color.BLACK);
		textTerms.setFont(new Font("Verdana", Font.PLAIN, 14));
		textTerms.setToolTipText("Terms of Agreements");
		textTerms.setEnabled(true);
		textTerms.setBounds(15, 16, 541, 182);
		frame.getContentPane().add(textTerms);

		textFieldUserName = new JTextField();
		textFieldUserName.setToolTipText("Enter Username");
		textFieldUserName.setText("Enter Username");
		textFieldUserName.setBounds(261, 256, 295, 26);
		frame.getContentPane().add(textFieldUserName);
		textFieldUserName.setColumns(10);

		txtEnterPassword = new JTextField();
		txtEnterPassword.setToolTipText("Enter Password");
		txtEnterPassword.setText("Enter Password");
		txtEnterPassword.setBounds(261, 298, 295, 26);
		frame.getContentPane().add(txtEnterPassword);
		txtEnterPassword.setColumns(10);

		JButton btnBailOut = new JButton("Bail Out!");
		btnBailOut.setToolTipText("Don't Accept the terms");
		btnBailOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBailOut();
			}
		});
		btnBailOut.setBounds(278, 398, 173, 29);
		frame.getContentPane().add(btnBailOut);

		JButton btnCreateUser = new JButton("Accept Terms");
		btnCreateUser.setToolTipText("Accept Terms");
		btnCreateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCreateUser();
			}
		});
		btnCreateUser.setBounds(90, 398, 173, 29);
		frame.getContentPane().add(btnCreateUser);

		txtReenterPassword = new JTextField();
		txtReenterPassword.setToolTipText("Reenter");
		txtReenterPassword.setText("Reenter Password");
		txtReenterPassword.setBounds(261, 339, 295, 26);
		frame.getContentPane().add(txtReenterPassword);
		txtReenterPassword.setColumns(10);

		JLabel lblCaptcha = new JLabel("Capthca");
		lblCaptcha.setToolTipText("Capthca");
		lblCaptcha.setBounds(15, 214, 231, 114);
		frame.getContentPane().add(lblCaptcha);

		txtTypeInCatcha = new JTextField();
		txtTypeInCatcha.setToolTipText("Type in Captcha");
		txtTypeInCatcha.setText("Type in Catcha");
		txtTypeInCatcha.setBounds(15, 339, 231, 26);
		frame.getContentPane().add(txtTypeInCatcha);
		txtTypeInCatcha.setColumns(10);

		txtEnterEmail = new JTextField();
		txtEnterEmail.setToolTipText("Enter Email");
		txtEnterEmail.setText("Enter Email");
		txtEnterEmail.setBounds(261, 214, 295, 26);
		frame.getContentPane().add(txtEnterEmail);
		txtEnterEmail.setColumns(10);
	}


	//CREATS A USER, CHECKS IF PASSWORD ARE THE SAME AND EMAIL IS CHANGED.
	private void btnCreateUser(){
		String tmpStr = "Enter Email";
		try{
			if (txtEnterPassword.getText().equals(txtReenterPassword.getText()) && !tmpStr.equals(txtEnterEmail.getText())){
				boolean tmpBooleanPass = true;
				boolean tmpBooleanMail = true;
				dbue.createUsers(textFieldUserName.getText(), txtEnterPassword.getText(), tmpBooleanPass, tmpBooleanMail);			
			}
			else{
				dbtgli.popUpMessage("Passwords or Email don't match!");
			}
		}
		catch(Exception e){
			System.out.println("-ERROR: btnCreateUser");
		}
	}

	private void btnBailOut(){
		dbtgli.showFrame();
		frame.setVisible(false);
	}
	
}
