package tamaGUI;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import tamaDialogs.DialogEngine;
import tamaDialogs.TalkingToTamaEngine;
import tamaSystem.DepressionEngine;
import tamaSystem.GameEngine;
import tamaSystem.HungerEngine;
import tamaSystem.MoneyEngine;

/** TAMA GUI, the game GUI
 * This is the Main game GUI Class,
 * It is build so that it takes arg, from Start GUI
 * and build the button/frame name from it. Aka different levels.
 * 
 * 
 * 
 *
 */

public class TamaGUI extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	//STATIC variables, so everyone can touch them for upgrade/graphic purpose.
	private JFrame GUIFrame;
	private JLabel label = new JLabel();
	private TextArea textArea;
	public TextArea getTextArea() {
		return textArea;
	}
	public void setTextArea(String textArea) {
		this.textArea.setText(textArea);
	}

	private int gameLevel;
	private JProgressBar hungerBar;
	private JProgressBar moneyBar;
	private JProgressBar depressionBar;
	private TamaGUI tg;
	public TamaGUI getTg() {
		return tg;
	}
	public void setTg(TamaGUI tg) {
		this.tg = tg;
	}

	private TextField textInPut;
	private ArrayList <String> buttonNames = new ArrayList<String>();
	private ArrayList <String> tooltips = new ArrayList<String>();
	private ArrayList <String> infoText = new ArrayList<String>();

	private JButton btnPlay1;
	private JButton btnPlay2;
	private JButton btnFood1;
	private JButton btnFood2;
	private JButton btnFood3;

	//MOUSE COUNTERS FOR FUTURE STUFF, 
	private int moneyMouseCounter = 0;
	public void setMoneyMouseCounter(int moneyMouseCounter) {
		this.moneyMouseCounter = moneyMouseCounter;
	}
	public int getMoneyMouseCounter() {
		return moneyMouseCounter;
	}

	private int mouseHappinessSinker = 0;
	public int getMouseHappinessSinker() {
		return mouseHappinessSinker;
	}
	public void setMouseHappinessSinker(int mouseHappinessSinker) {
		this.mouseHappinessSinker = mouseHappinessSinker;
	}

	private int mouseGainHappiness = 0;
	public int getMouseGainHappiness() {
		return mouseGainHappiness;
	}
	public void setMouseGainHappiness(int mouseGainHappiness) {
		this.mouseGainHappiness = mouseGainHappiness;
	}

	private DepressionEngine de;
	private HungerEngine he;
	private MoneyEngine mo;
	private DialogEngine di; //NOT IN USE YET, FUTURE FUNKTIONS!
	private TalkingToTamaEngine tt;

	public TamaGUI(){
	}

	//THE MAIN METHOD TO GET OBJECT FOR GUI
	public void TamaGUI(int lvNr, String frameTitle, String tamaName, HungerEngine he, MoneyEngine mo,
			DialogEngine di, TalkingToTamaEngine tt, DepressionEngine de) {
		gameLevel = lvNr;
		buttonNames(lvNr);
		initialize(frameTitle, tamaName);
		tt.setDialogLevel(lvNr);

		this.he = he;
		this.mo = mo;
		this.di = di;
		this.tt = tt;
		this.de = de;
	}

	//TAKES IN A BOOLEAN TO SHOW/HIDE GUI FRAME
	public void showGUI(boolean show){
		GUIFrame.setVisible(show);
	}

	private void initialize(String frameTitle, String TamaName) {
		GUIFrame = new JFrame();
		GUIFrame.getContentPane().setBackground(Color.WHITE);
		GUIFrame.setResizable(false);
		GUIFrame.setTitle(GameEngine.TAMA_VERSION + " " + frameTitle + TamaName);
		GUIFrame.setBounds(100, 100, 669, 366);
		GUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIFrame.getContentPane().setLayout(null);

		textArea = new TextArea();
		textArea.setBounds(349, 46, 304, 244);
		GUIFrame.getContentPane().add(textArea);

		//Text field for talking to Tama
		textInPut = new TextField();
		textInPut.setText("Wright something to your <Tama>");
		textInPut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tt.dontHaveTimeATMStr();
				textInPut.setText("Wright something to your <Tama>");
			}
		});
		textInPut.setBounds(15, 296, 643, 27);
		GUIFrame.getContentPane().add(textInPut);

		//bar, hunger
		hungerBar = new JProgressBar();
		hungerBar.setStringPainted(true);
		hungerBar.setToolTipText("Hunger Bar");
		hungerBar.setBounds(507, 16, 146, 14);
		GUIFrame.getContentPane().add(hungerBar);

		//bar, Depression
		depressionBar = new JProgressBar ();
		depressionBar.setStringPainted(true);
		depressionBar.setToolTipText("Happiness Bar");
		depressionBar.setBounds(349, 16, 146, 14);
		GUIFrame.getContentPane().add(depressionBar);

		//bar, money
		moneyBar = new JProgressBar();
		moneyBar.setStringPainted(true);
		moneyBar.setToolTipText("Your Money");
		moneyBar.setBounds(191, 16, 146, 14);
		GUIFrame.getContentPane().add(moneyBar);

		//for animation
		label.setBounds(142, 46, 201, 244);
		GUIFrame.add(label);	

		//PLAY BUTTON LEVEL 1
		btnPlay1 = new JButton(buttonNames.get(0));
		btnPlay1.setToolTipText(tooltips.get(0));
		btnPlay1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buttonPlay1();
			}
		});
		btnPlay1.setBounds(15, 47, 115, 29);
		GUIFrame.getContentPane().add(btnPlay1);

		//PLAY BUTTON LEVEL 2
		btnPlay2 = new JButton(buttonNames.get(1));
		btnPlay2.setToolTipText(tooltips.get(1));
		btnPlay2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPlay2();
			}
		});
		btnPlay2.setBounds(15, 87, 115, 29);
		GUIFrame.getContentPane().add(btnPlay2);

		//FOOD BUTTON LEVEL 1
		btnFood1 = new JButton(buttonNames.get(2));
		btnFood1.setToolTipText(tooltips.get(2));
		btnFood1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonFood1();
			}
		});
		btnFood1.setBounds(15, 172, 115, 29);
		GUIFrame.getContentPane().add(btnFood1);

		//FOOD BUTTON LEVEL 2
		btnFood2 = new JButton(buttonNames.get(3));
		btnFood2.setToolTipText(tooltips.get(3));
		btnFood2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonFood2();
			}
		});
		btnFood2.setBounds(15, 213, 115, 29);
		GUIFrame.getContentPane().add(btnFood2);

		//FOOD BUTTON LEVEL 3
		btnFood3 = new JButton(buttonNames.get(4));
		btnFood3.setToolTipText(tooltips.get(4));
		btnFood3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonFood3();
			}
		});
		btnFood3.setBounds(15, 255, 115, 29);
		GUIFrame.getContentPane().add(btnFood3);
		GUIFrame.addMouseListener(this);
	}

	//ARRAY METHOD LIST FOR BUTTON NAMES
	private void buttonNames(int gameLevel){
		if (gameLevel == 1){
			buttonNames.add("Tickle ");
			buttonNames.add("Give hug");
			buttonNames.add("Candy");
			buttonNames.add("Ice Cream");
			buttonNames.add("Baby food");
			tooltips.add("Tickle the litle Tama, gain happiness");
			tooltips.add("Give Tama a hug, gain happiness");
			tooltips.add("Give Tama candy, gain happiness. Cost 250 Pesoh");
			tooltips.add("Give Tama Ice Cream, gain happiness. Cost 2000 Pesoh");
			tooltips.add("Give Tama baby food, Medium tummy Filler. Cost 3500 Pesoh");
			infoText.add("...hihihi \n ");
			infoText.add("Tama Smiles at you");
			infoText.add("Tama embraces your hug");
			infoText.add("You feel worm and cosy inside.");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("Looks like Tama likes candy");
			infoText.add("Looks like Tama likes ice cream");
			infoText.add("Looks like Tama likes baby food");
		}
		else if (gameLevel == 2){
			buttonNames.add("Play a game");
			buttonNames.add("Watch movie");
			buttonNames.add("Snacks");
			buttonNames.add("Home Meal");
			buttonNames.add("Fancy Restaurant");
			tooltips.add("Play a easy game to make your Tama happy");
			tooltips.add("Go for a movie? Will make Tama extremely happy. Takes 1 Tama hour and cost 3500");
			tooltips.add("Snacks. Small tummy filler. The fat can make the Tama a bit depressed. Cost 500 Pesoh");
			tooltips.add("Home Cooked meal. Medium tummy filler. Cost 2000 Pesos");
			tooltips.add("Eat at a Fancy Resturant. High tummy filler. Cost 3500 Pesos");
			infoText.add("Playing a game \n ");
			infoText.add("...Thannks for playing with me."
					+ "\n...I am a bit tired now \n");
			infoText.add("Out Watching Movie" + "\n...Back in 1 Hour");
			infoText.add("...That was an awesome movie!");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("...I LOVE SNACKS! \n");
			infoText.add("...Home cooked food is the best! ");
			infoText.add("...What a Fancy Restaurant! ");
		}
		else if (gameLevel == 3){
			buttonNames.add("Give money");
			buttonNames.add("Watch movie");
			buttonNames.add("Snacks");
			buttonNames.add("Home Meal");
			buttonNames.add("Fancy Restaurant");
			tooltips.add("Give money to your Tama, gain happiness");
			tooltips.add("Go for a movie? Will make Tama extremely happy. Takes 1 Tama hour and cost 3500");
			tooltips.add("Snacks. Small tummy filler. The fat can make the Tama a bit depressed. Cost 500 Pesoh");
			tooltips.add("Home Cooked meal. Medium tummy filler. Cost 2000 Pesos");
			tooltips.add("Eat at a Fancy Resturant. High tummy filler. Cost 3500 Pesos");
			infoText.add("You hand out money to your Tama.");
			infoText.add("...Thanks for the money" );
			infoText.add("Out Watching Movie" + "\n...Back in 1 Hour");
			infoText.add("...That was an awesome movie!");
			infoText.add("...OmNomNonNOm\n");
			infoText.add("...I LOVE SNACKS! \n");
			infoText.add("...Home cooked food is the best! ");
			infoText.add("...What a Fancy Restaurant! ");
		}
	}

	//SET AND UPDATE DEPRESSION BAR, TRIGGED BY DEPRESSINENGINE CLASS
	public void setDepressionBar(){
		int deppresionValue = de.getTamaCurrentDepression();
		depressionBar.setString("Happiness: " + Integer.toString(deppresionValue));

		if (deppresionValue >= 9000){
			depressionBar.setForeground(new Color(0, 128, 0));
			depressionBar.setValue(100);
		}
		else if (deppresionValue >= 7500){
			depressionBar.setForeground(new Color(0, 128, 0));
			depressionBar.setValue(85);

		}
		else if (deppresionValue >= 5000){
			depressionBar.setForeground(new Color(0, 128, 0));
			depressionBar.setValue(65);
		}
		else if(deppresionValue >= 4000){
			depressionBar.setForeground(Color.ORANGE);
			depressionBar.setValue(50);

		}
		else if(deppresionValue >= 3500){
			depressionBar.setForeground(Color.ORANGE);
			depressionBar.setValue(45);

		}
		else if(deppresionValue >= 3000){
			depressionBar.setForeground(Color.ORANGE);
			depressionBar.setValue(30);

		}
		else if(deppresionValue >= 2500){
			depressionBar.setForeground(Color.PINK);
			depressionBar.setValue(20);

		}
		else if(deppresionValue >= 2000){
			depressionBar.setForeground(Color.RED);
			depressionBar.setValue(10);

		}
		else if(deppresionValue >= 1500){
			depressionBar.setValue(5);

		}
		else if(deppresionValue >= 1000){
			depressionBar.setValue(0);
		}

	}

	//SET AND UPDATE MONEYBAR, TRIGGED BY MONEYENGINE CLASS
	public void setMoneyBar(){
		int moneyValue = mo.getCurrentMoney();
		moneyBar.setString(Integer.toString(moneyValue) + " Pesoh");
		moneyBar.setForeground(Color.BLUE);

		if(moneyValue <= 0){
			moneyBar.setValue(0);
		}
		else if(moneyValue <= 1000){
			moneyBar.setValue(10);
		}

		else if(moneyValue <= 2500){
			moneyBar.setValue(25);
		}

		else if(moneyValue <= 3500){
			moneyBar.setValue(35);
		}
		else if(moneyValue <= 5000){
			moneyBar.setValue(50);
		}
		else if (moneyValue <= 7500){
			moneyBar.setValue(75);
		}
		else if (moneyValue <= 9000){
			moneyBar.setValue(90);
		}
		else if (moneyValue <= 10000){
			moneyBar.setValue(10000);
		}

	}

	//SET AND UPDATE HUNGERBAR, TRIGGED BY HUNGERENGINE CLASS
	public void setHungerBar(){
		int hungerValue = he.getTamaCurrentHunger();
		hungerBar.setString("Hunger: " + Integer.toString(hungerValue));
		if(hungerValue >= 9000){
			hungerBar.setValue(100);
			hungerBar.setForeground(new Color(0, 128, 0));
		}		
		else if (hungerValue >= 8500){
			hungerBar.setValue(85);
			hungerBar.setForeground(new Color(0, 128, 0));
		}	
		else if (hungerValue >= 7500){
			hungerBar.setValue(75);
			hungerBar.setForeground(new Color(0, 128, 0));
		}
		else if (hungerValue >= 5000){
			hungerBar.setForeground(Color.ORANGE);
			hungerBar.setValue(60);
		}		
		else if(hungerValue >= 4000){
			hungerBar.setForeground(Color.ORANGE);
			hungerBar.setValue(50);
		}
		else if(hungerValue >= 2500){
			hungerBar.setForeground(Color.ORANGE);
			hungerBar.setValue(30);
		}		
		else if(hungerValue >= 2500){
			hungerBar.setForeground(Color.ORANGE);
			hungerBar.setValue(30);
		}		
		else if(hungerValue >= 2000){
			hungerBar.setForeground(Color.RED);
			hungerBar.setValue(20);
		}	
		else if(hungerValue >= 1500){
			hungerBar.setForeground(Color.RED);
			hungerBar.setValue(10);
		}
		else if(hungerValue >= 1000){
			hungerBar.setForeground(Color.PINK);
			hungerBar.setValue(10);
		}		
		else if (hungerValue >= 500){
			hungerBar.setForeground(Color.RED);
			hungerBar.setValue(10);
		}
		else if (hungerValue >= 500){
			hungerBar.setForeground(Color.PINK);
			hungerBar.setValue(10);
		}
	}

	//ANIMATION UPDATER
	public void labelUpdater(ImageIcon tmp){
		label.setIcon(tmp);
	}

	private void buttonPlay1(){
		if (gameLevel == 1){
			de.happinessGainedLv2();
			textArea.setText(infoText.get(0));
			textArea.setText(infoText.get(1));

		}
		else if (gameLevel >= 2){
			he.foodDecreases1();
			de.happinessGainedLv2();
			textArea.setText(infoText.get(0));
			textArea.setText(infoText.get(1));
		}
	}

	private void buttonPlay2(){
		if (gameLevel == 1){
			de.happinessGainedLv2();
			textArea.setText(infoText.get(2));
			textArea.setText(infoText.get(3));
		}						

		else if (gameLevel >= 2){
			if (mo.getCurrentMoney() >= 3500){
				he.foodDecreases3();
				mo.moneyItem3();
				de.happinessGainedLv2();
				textArea.setText(infoText.get(2));
				textArea.setText(infoText.get(3));
			}						
			else if(mo.getCurrentMoney() <= 3500) {
				textArea.setText("You don't have money for it!\n");
			}
		}
	}

	private void buttonFood1(){
		if (gameLevel == 1){
			if (mo.getCurrentMoney() >= 500){
				getClass();
				mo.moneyItem1();
				de.happinessGainedLv1();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(5));
			}						
			else if(mo.getCurrentMoney() <= 500) {
				textArea.setText("You don't have money for it!\n");
			}	
		}
		else if (gameLevel >= 2){
			if (mo.getCurrentMoney() >= 500){
				he.foodItem1();
				mo.moneyItem1();
				de.happinessLevel2();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(5));
			}						
			else if(mo.getCurrentMoney() <= 500) {
				textArea.setText("You don't have money for it!\n");
			}					
		}
	}

	private void buttonFood2(){
		if (gameLevel == 1){
			if (mo.getCurrentMoney() >= 2000){
				he.foodItem2();
				mo.moneyItem2();
				de.happinessGainedLv1();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(6));
			}				
			else if(mo.getCurrentMoney() <= 2000) {
				textArea.setText("You don't have money for it!\n");
			}
		}
		else if (gameLevel >= 2){
			if (mo.getCurrentMoney() >= 2000){
				he.foodItem2();
				mo.moneyItem2();
				de.happinessGainedLv1();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(6));
			}				
			else if(mo.getCurrentMoney() <= 2000) {
				textArea.setText("You don't have money for it!\n");
			}
		}
	}

	private void buttonFood3(){
		if (gameLevel == 1){
			if (mo.getCurrentMoney() >= 3500){
				he.foodItem3();
				mo.moneyItem3();
				de.happinessLevel2();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(7));
			}
			else if(mo.getCurrentMoney() <= 3500) {
				textArea.setText("You don't have money for it!\n");
			}
		}
		else if (gameLevel >= 2){
			if (mo.getCurrentMoney() >= 3500){
				he.foodItem3();
				mo.moneyItem3();
				de.happinessLevel2();
				textArea.setText(infoText.get(4));
				textArea.setText(infoText.get(7));
			}
			else if(mo.getCurrentMoney() <= 3500) {
				textArea.setText("You don't have money for it!\n");
			}
		}
	}
	//Tama gets happiness by clicking in frame
	@Override
	public void mouseClicked(MouseEvent arg0) {
		de.mouseHappiness();
		mouseGainHappiness++;
	}
	//player gain money while entering frame
	@Override
	public void mouseEntered(MouseEvent arg0) {
		mo.moneyGain1();
		moneyMouseCounter++;
	}
	//tama get depressed when mouse exits the Frame
	@Override
	public void mouseExited(MouseEvent arg0) {
		de.mouseHappinessSinker();
		mouseHappinessSinker++;
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
