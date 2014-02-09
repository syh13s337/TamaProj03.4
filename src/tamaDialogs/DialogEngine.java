package tamaDialogs;

import java.util.ArrayList;
import java.util.Random;
import tamaGUI.TamaGUI;
import tamaSystem.GameEngine;

/** TAMA DIALOGS CLASS
 * This class will generate dialogs for Tama to randomly say.
 * It does it by, adding strings to a ArrayList. 
 * Using a random int generator to give .get(randomInt) from list for dialogs.
 * And its looped and in the loop it got a random thread sleeper time, 
 * so the dialogs appear on different time.
 *  
 *  Future plans:
 *  Make more random dialogs,
 *  Connect Dialogs with hunger/depression/money sinks/gain, Like, gain money cuz your Tama found Treasure.
 *  Make a Emo Version for level 3, when the depression hits below 50%
 *  
 *  
 */

public class DialogEngine implements Runnable {

	//THE MILI SECS THAT RANDOM WILL WORK WITH 0-50000
	private final int setRandomTimerGenerator = 50000;

	private String dialogString = "Let the Adventure Begin!";
	private ArrayList<String> dialogList0 = new ArrayList<String>();
	private ArrayList<String> dialogList1 = new ArrayList<String>();
	private ArrayList<String> dialogList2 = new ArrayList<String>();
	private ArrayList<String> dialogList3 = new ArrayList<String>();

	private GameEngine ge;
	private TamaGUI tg;

	private Random intGenerator = new Random();
	private int randomNumber;
	private int randomTimerCount;

	public DialogEngine(GameEngine ge,TamaGUI tg){	
		this.ge = ge;
		this.tg = tg;
	}

	//STARTS/ADDS DIALOG IN ARRAY LISTS
	private void dialogLister(){
		dialogListLevel0();
		dialogListLevel1();
		dialogListLevel2();
		dialogListLevel3();	
	}

	// Just randome smilies, NOT in use at the moment.
	private void dialogListLevel0(){
		dialogList0.add("/(-.-)...");
		dialogList0.add("/('_')...");
		dialogList0.add("( '_')...");
		dialogList0.add("('_' )...");
		dialogList0.add("('-'*)...");
		dialogList0.add("(*'-')...");
		dialogList0.add("( -.-)...");
		dialogList0.add("(-.- )...");
	}

	//DIALOG LEVEL 1, (EASY MODE) BABY 
	private void dialogListLevel1(){
		dialogList1.add("grrrlll.......");
		dialogList1.add("hu?!?!?.......");
		dialogList1.add("WHAAA WHAAA!..");
		dialogList1.add("Hehehe........");
		dialogList1.add("phuuu.........");
		dialogList1.add("buuh!.........");
		dialogList1.add("Bah Bah.......");
		dialogList1.add("Gah Gah.......");
		dialogList1.add("bluh Gah......");
		dialogList1.add("pfff..........");
		dialogList1.add("hmmmf.........");
		dialogList1.add("Daah..........");
	}

	//DIALOG LEVEL 2, (NORMAL MODE) THE KID,
	private void dialogListLevel2(){
		dialogList2.add("Haaj");
		dialogList2.add("I should cal a friend");
		dialogList2.add("I wana Make a song");
		dialogList2.add("So cold in here");
		dialogList2.add("What should i do?");
		dialogList2.add("I read on the internet that the world is round");
		dialogList2.add("phuuuue");
		dialogList2.add("Woow! so much rain outisde");
		dialogList2.add("I love Red");
		dialogList2.add("I should learn Karate");
		dialogList2.add("Can you tel me a story?");
		dialogList2.add("");
		dialogList2.add("");
	}

	//DIALOG LEVEL 3, (HARD MODE) YOUNG ADULT, EMO
	private void dialogListLevel3(){
		dialogList3.add("YOLO");
		dialogList3.add("I HATE SCHOOL");
		dialogList3.add("I wana buy new cloth ");
		dialogList3.add("I look so ugly");
		dialogList3.add("I got girl friend! ");
		dialogList3.add("I wounder how other countrys are");
		dialogList3.add("Sing* Sing* Sing* Sing*");
		dialogList3.add("Why is the internet so slooooow!");
		dialogList3.add("I love my new EyePhone!");
		dialogList3.add("How can some people love TwatLight?");
		dialogList3.add("This Whats App is awsome!");
	}

	//TIMER AND DIALOG GETTER
	public void timerAndDialogs() throws InterruptedException{
		tg.setTextArea(dialogString + "\n");
		Thread.sleep(3000);

		while(ge.isALL_TREADS_RUNNING() == true){
			randomTimerCount = intGenerator.nextInt(setRandomTimerGenerator);

			if (ge.getGameLevel() == 1){
				tg.setTextArea(getRandomeDialogsLevel1() + "\n");
			}

			else if (ge.getGameLevel() == 2){
				tg.setTextArea(getRandomeDialogsLevel2() + "\n");

			}				
			else if (ge.getGameLevel() == 3){
				tg.setTextArea(getRandomDialogsLevel3() + "\n");
			}

			Thread.sleep(randomTimerCount);
		}
	}

	//RANDOM DIALOG LV1, TURN ARRAY TO STRING
	private String getRandomeDialogsLevel1(){
		String getRandomeDialogsLevel1;
		randomNumber = intGenerator.nextInt(dialogList1.size());
		getRandomeDialogsLevel1 = "..." + dialogList1.get(randomNumber);
		return getRandomeDialogsLevel1;
	}

	//RANDOM DIALOG LV2, TURN ARRAY TO STRING
	private String getRandomeDialogsLevel2(){
		String getRandomeDialogsLevel2;
		randomNumber = intGenerator.nextInt(dialogList2.size());
		getRandomeDialogsLevel2 = "..." + dialogList2.get(randomNumber);
		return getRandomeDialogsLevel2;
	}

	//RANDOM DIALOG LV3, TURN ARRAY TO STRING
	private String getRandomDialogsLevel3(){
		String getRandomeDialogsLevel3;
		randomNumber = intGenerator.nextInt(dialogList3.size());
		getRandomeDialogsLevel3 = "..." + dialogList3.get(randomNumber);
		return getRandomeDialogsLevel3;
	}

	@Override
	public void run() {
		try {
			dialogLister();
			timerAndDialogs();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}