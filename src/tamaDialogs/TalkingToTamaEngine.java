package tamaDialogs;

import java.util.ArrayList;
import java.util.Random;
import tamaGUI.TamaGUI;

/** TALKING TO TAMA ENGINE
 *  This class will work with text input from user and give feedback.
 *  At the moment it just gives random "Don't have time" answers.
 *  
 *  Future plans: 
 *  More dialogs answers that pick some words from strings.
 *
 *
 */
public class TalkingToTamaEngine {

	private Random intGenerator = new Random();
	private int dialogLevel;
	private ArrayList <String> dontHaveTimeDialogs = new ArrayList<String>();
	private ArrayList <String> dontHaveTimeDialogs2 = new ArrayList<String>();
	private TamaGUI tg;

	public TalkingToTamaEngine(TamaGUI tg){
		this.tg = tg;
		dialogLister();
	}
	private void dialogLister(){
		dontHaveTimeDialogs();
	}

	private void dontHaveTimeDialogs(){
		dontHaveTimeDialogs.add("Dont have time rite now");
		dontHaveTimeDialogs.add("Can we do it later");
		dontHaveTimeDialogs.add("I am busy");
		dontHaveTimeDialogs.add("I am playing a game");

		dontHaveTimeDialogs2.add("GOD! I Dont have time rite now");
		dontHaveTimeDialogs2.add("I don't wana talk to you!" );
		dontHaveTimeDialogs2.add("Can't You see I am busy! ");
		dontHaveTimeDialogs2.add("I am PLAYING COD! ");
		dontHaveTimeDialogs2.add("I am PLAYING WOW! ");
		dontHaveTimeDialogs2.add("MawhGAD! can't you see I am on the Phone! ");
	}



	//dont have time right now generator
	public void dontHaveTimeATMStr(){
		if(dialogLevel == 0){
			tg.setTextArea("..." + "The tolder seams to like your voice " + "\n");

		}
		else if (dialogLevel == 2){
			int tmp = intGenerator.nextInt(dontHaveTimeDialogs.size());
			String dontHaveTimeATMStr = (dontHaveTimeDialogs.get(tmp));
			tg.setTextArea("..." + dontHaveTimeATMStr + "\n");

		}
		else if(dialogLevel == 3){
			int tmp = intGenerator.nextInt(dontHaveTimeDialogs2.size());
			String dontHaveTimeATMStr = (dontHaveTimeDialogs2.get(tmp));
			tg.setTextArea("..." + dontHaveTimeATMStr + "\n");

		}
	}

	// sets dialog level.
	public void setDialogLevel(int x){
		this.dialogLevel = dialogLevel + x;		
	}
}
