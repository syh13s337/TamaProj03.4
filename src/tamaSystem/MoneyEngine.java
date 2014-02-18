package tamaSystem;


import java.util.Random;
import tamaGUI.TamaGUI;


/** MONEY ENGINE
 * 	This class is where the money is at!
 * 	It will update the money bar, handle gain/decrease money.
 * 	And of the HardCore mode, the have a chance to steal money. 	
 *
 */


public class MoneyEngine implements Runnable{
	//Current Money
	//
	//SELF NOTE: In case future upgrade/change.
	//
	private int currentMoney;
	public int getCurrentMoney() {
		return currentMoney;
	}
	public void setMoneyValue(int currentMoney) {
		this.currentMoney = currentMoney;
	}

	private Random intGenerator = new Random();
	private TamaGUI tg;
	private GameEngine ge;

	public MoneyEngine(GameEngine ge, TamaGUI tg){
		this.ge = ge;
		this.tg = tg;
	}	
	public MoneyEngine(){
	}

	//THE LOOP
	@Override
	public void run() {
		while(ge.isALL_TREADS_RUNNING() == true){
			if(currentMoney <= 0){
				currentMoney = 0;
			}
			tg.setMoneyBar(currentMoney);
			TamaFoundMoney();
			TamaStealMoney();
			moneyBarUpdaterTimer(1000);
		}
	}

	//RANDOM GENERATE, TAMA STEAL MONEY
	//SELF NOTE: Link it with a text/string Array.
	//so it tells what Tama does with the stolen money.
	private void TamaStealMoney(){
		if(ge.getGameLevel() == 3){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				moneyItem1();
				tg.setTextArea("Your Tama stole money from you"
						+ "\nLost 500 Pesoh");
			}
		}
	}

	//RANDOM GENERATE, TAMA FINDS MONEY AND GIVES IT TO USER
	//SELF NOTE: Make one that gives money if Tama have a job.
	private void TamaFoundMoney(){
		if(ge.getGameLevel() >= 2){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				moneyGain2();
				tg.setTextArea("Your Tama found money and gave it to you"
						+ "\nGained 300 Pesoh");
			}
		}
	}

	//SLEEP TIMER
	private void moneyBarUpdaterTimer(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//MONEY GAINERS/SINKERS ITEM
	//SELF NOTE: Maybe get values from DB in the future.
	public void moneyItem1(){
		currentMoney -= 500;
	}
	public void moneyItem2(){
		currentMoney -= 2000;
	}
	public void moneyItem3(){
		currentMoney -= 3500;
	}
	public void moneyGain1(){
		currentMoney += 10;
	}
	public void moneyGain2(){
		currentMoney += 300;
	}
}
