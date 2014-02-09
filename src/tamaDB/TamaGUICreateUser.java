package tamaDB;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;

import tamaSystem.GameEngine;

public class TamaGUICreateUser {

	public JFrame frame;
	private JTextField textFieldUserName;
	private JTextField txtEnterPassword;
	private JTextField txtReenterPassword;
	private JTextField txtTypeInCatcha;
	private JTextArea textTerms;

	private GameEngine ge;
	private TamaGUILogIn tgli;
	private UserEngine ue;
	private TamaGUICreateUser tgcu;

	//TERMS OF USE, IS LINKET TO JTextArea, textTerms.
	private String termsOfUse = "BLALBLALBLABLABLA Tama, "
			+ "\nIf you accept you sell your soul to the devil, and Elvise ";


	/**
	 * Create the application.
	 */
	public TamaGUICreateUser(GameEngine ge, TamaGUILogIn tgli, UserEngine ue, TamaGUICreateUser tgcu) {
		this.ge = ge;
		this.tgli = tgli;
		this.ue = ue;
		this.tgcu = tgcu;
		initialize();
	}

	public TamaGUICreateUser(){
	}

	public void tamaGUICreatUserStart(){
		frame.setVisible(true);			
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setForeground(Color.WHITE);
		frame.getContentPane().setForeground(Color.WHITE);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Arild\\Documents\\GitHub\\TamaProj03\\image\\Baby\\b3.png"));
		frame.setResizable(false);
		frame.setBounds(100, 100, 577, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

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

		textTerms = new JTextArea();
		textTerms.setText(termsOfUse);
		textTerms.setToolTipText("Terms of Agreements");
		textTerms.setEnabled(false);
		textTerms.setBounds(15, 16, 541, 224);
		frame.getContentPane().add(textTerms);

		txtReenterPassword = new JTextField();
		txtReenterPassword.setToolTipText("Reenter");
		txtReenterPassword.setText("Reenter Password");
		txtReenterPassword.setBounds(261, 339, 295, 26);
		frame.getContentPane().add(txtReenterPassword);
		txtReenterPassword.setColumns(10);

		JLabel lblCaptcha = new JLabel("Capthca");
		lblCaptcha.setToolTipText("Capthca");
		lblCaptcha.setBounds(15, 256, 231, 72);
		frame.getContentPane().add(lblCaptcha);

		txtTypeInCatcha = new JTextField();
		txtTypeInCatcha.setToolTipText("Type in Captcha");
		txtTypeInCatcha.setText("Type in Catcha");
		txtTypeInCatcha.setBounds(15, 339, 231, 26);
		frame.getContentPane().add(txtTypeInCatcha);
		txtTypeInCatcha.setColumns(10);
	}
//CREATE USER BUTTON, with password chercker.
	private void btnCreateUser(){
		try{
			if (txtEnterPassword.getText().equals(txtReenterPassword.getText())){
				ue.createUsers(textFieldUserName.getText(), txtEnterPassword.getText());
			}
			else{
				tgli.popUpMessage("Passwords don't match! ");
			}
		}
		catch(Exception e){
			System.out.println("NOT WORKING YET");
		}
	}

	private void btnBailOut(){
		frame.setVisible(false);
	}
}
