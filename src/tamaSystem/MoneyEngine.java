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
	//Note: In case future upgrade/change the variables is here.
	private int currentMoney = 10000;
	private Random intGenerator = new Random();
	private TamaGUI tg;
	private GameEngine ge;

	public MoneyEngine(GameEngine ge, TamaGUI tg){
		this.ge = ge;
		this.tg = tg;
	}	
	public MoneyEngine(){
		
	}

	//the Loop
	@Override
	public void run() {
		while(ge.isALL_TREADS_RUNNING() == true){
			if(currentMoney <= 0){
				currentMoney = 0;
			}
			tg.setMoneyBar();
			TamaFoundMoney();
			TamaStealMoney();
			moneyBarUpdaterTimer(1000);

		}
	}

	//Random generate, the Tama going to steal money.
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

	//Random generate, Tama finds money and gives it to player.
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

	//sleep timer
	private void moneyBarUpdaterTimer(int x){
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	//Money sinker/gainer item
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

	//GET money
	public int getCurrentMoney() {
		return currentMoney;
	}
	//SET money
	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}

}
