package tamaGUI;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;

import tamaSystem.GameEngine;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/** TAMA GUI START, start information and level/name pick
 * This class just show start information for player.
 * Lets the player enter a Tama name and a level to play.
 * 
 * FUTURE PLAN: 
 * Better information for the player
 *
 */

public class TamaGUIStart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tamaName = "";
	private int gamleLevel;

	private JFrame frmTamav;
	private JTextField txtEnterTamaName;
	private JTextArea InfoText;
	private JScrollPane scrollPane;
	private int tmpX = 1;

	private GameEngine ge;
	
	private String gameInfoString = "Hi! You are now playing: " + GameEngine.TAMA_VERSION 
			+ "\n"
			+ "\nThe levels:"
			+ "\nLevel, 1 Baby mode"
			+ "\nIts on easy mode, and you just need to keep"
			+ "\nthe Tama alive for 1h"
			+ "\nLevel 2, Kid mode"
			+ "\nNormal mode, you need to keep it alive 1h."
			+ "\nLevel 3, Young Adult mode"
			+ "\nNo limit, this is the end game."
			+ "\nThe Tama may steal money "
			+ "\nand get randomly dipressed "
			+ "\nand start to be Emo."
			+ "\n"
			+ "\nTips:"
			+ "\nDraging the mouse back "
			+ "\nand forth on the frame will get you some Pesoh"
			+ "\nClicking on the frame will make Tama a bit happyer."
			+ "\nSorry for the spelling!"
			+ "\nGame is at the moment Unbalanced";
	private String aboutInfoString = GameEngine.TAMA_VERSION + " Made by:"
							+ "\n" + GameEngine.MADE_BY							
							+ "\nA Java13 project"
							+ "\nGame is at the moment Unbalanced"
							+ "\nand we love more feedback"
							+ "\nMail at: arildoderman@gmail.com"
							+ "\n"
							+ "\nUpdate inc soon!";
	
	
	//the starter, with own runnable
	public void TamaStartGUIStarter(GameEngine ge){
		frmTamav.setVisible(true);
		this.ge=ge;
	}


	public TamaGUIStart() {
		initialize();
	}

	//The Builder and components
	private void initialize() {
		frmTamav = new JFrame();
		frmTamav.setResizable(false);
		frmTamav.setTitle(GameEngine.TAMA_VERSION);
		frmTamav.getContentPane().setBackground(Color.WHITE);
		frmTamav.getContentPane().setForeground(Color.WHITE);
		frmTamav.setBounds(100, 100, 450, 300);
		frmTamav.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTamav.getContentPane().setLayout(null);

		//start text
		InfoText = new JTextArea();
		InfoText.setText(gameInfoString);

		InfoText.setToolTipText("Info about Tama v2.");
		InfoText.setBounds(139, 13, 281, 188);

		//SCROLL
		scrollPane = new JScrollPane(InfoText);
		scrollPane.setBounds(139, 13, 281, 188);
		scrollPane.setViewportView(InfoText);
		frmTamav.getContentPane().add(scrollPane);

		//TEXT FIELD
		txtEnterTamaName = new JTextField();
		txtEnterTamaName.setToolTipText("Enter the name of your Tama");
		txtEnterTamaName.setText("Enter Tama name, and pick level! ");
		txtEnterTamaName.setBounds(139, 214, 281, 22);
		frmTamav.getContentPane().add(txtEnterTamaName);
		txtEnterTamaName.setColumns(10);

		JButton btnStartLv1 = new JButton("Start Lv1");
		btnStartLv1.setToolTipText("(Easy) Baby Mode");
		btnStartLv1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				tamaName = txtEnterTamaName.getText();	
				if(!tamaName.equals("Enter Tama name, and pick level! ")) {
					tamaName = txtEnterTamaName.getText();
					frmTamav.setVisible(false);

					ge.GameGUI(1, " Baby (EasyMode) ", tamaName);

				}
				else {	
					InfoText.setText("You need to enter a name for your Tama! ");
				}				
			}
		});
		btnStartLv1.setBounds(12, 15, 97, 25);
		frmTamav.getContentPane().add(btnStartLv1);

		JButton btnStartLv2 = new JButton("Start Lv2");
		btnStartLv2.setToolTipText("(Normal) Kid Mode");
		btnStartLv2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				tamaName = txtEnterTamaName.getText();	
				if(!tamaName.equals("Enter Tama name, and pick level! ")) {
					tamaName = txtEnterTamaName.getText();
					frmTamav.setVisible(false);

					ge.GameGUI(2, " The Kid (NormalMode) ", tamaName);

				}
				else {	
					InfoText.setText("You need to enter a name for your Tama! ");
				}				
			}
		});
		btnStartLv2.setBounds(12, 54, 97, 25);
		frmTamav.getContentPane().add(btnStartLv2);

		JButton btnStartLv3 = new JButton("Start Lv3");
		btnStartLv3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				tamaName = txtEnterTamaName.getText();	
				if(!tamaName.equals("Enter Tama name, and pick level! ")) {
					tamaName = txtEnterTamaName.getText();
					frmTamav.setVisible(false);

					ge.GameGUI(3, " Young Adult (HardMode) ", tamaName);


				}
				else {	
					InfoText.setText("You need to enter a name for your Tama! ");
				}				
			}
		});
		btnStartLv3.setToolTipText("(Hard) Young Adult Mode");
		btnStartLv3.setBounds(12, 92, 97, 25);
		frmTamav.getContentPane().add(btnStartLv3);

		JButton btnAbout = new JButton("About");
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tmpX == 3){
					tmpX = 1;
				}
				if (tmpX == 1){
					InfoText.setText(aboutInfoString);
					tmpX++;
				}
				else if (tmpX == 2){
					InfoText.setText(gameInfoString);
					tmpX++;
				}
			}
		});
		btnAbout.setToolTipText("Information about the game");
		btnAbout.setBounds(12, 213, 97, 25);
		frmTamav.getContentPane().add(btnAbout);
	}

	public String getTamaName() {
		return tamaName;
	}

	public int getGamleLevel() {
		return gamleLevel;
	}

	public void setGamleLevel(int gamleLevel) {
		this.gamleLevel = gamleLevel;
	}
}
