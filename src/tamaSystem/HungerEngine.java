package tamaSystem;

import java.util.Random;
import tamaGUI.TamaGUI;

/**HUNGER ENGINE CLASS
 * This class works with energy/food/hunger
 * It starts with X amount of energy, and every sec it ticks away a bit of the energy.
 * This class also update the graphic on HungerBar and have methods for gain/sink functions.
 * 
 * 
 * 
 *
 */

public class HungerEngine implements Runnable  {

	//
	//Note: In case future upgrade/change the varible is here.
	//
	private int tamaCurrentHunger = 10000;
	private int hungerValue = 30;
	
	private TamaGUI tg;
	private GameEngine ge;

	//HUNGER BUILDER TIME, THREAD SLEEPER
	private final int hungerBuilderTimeValue = 1000;

	//FOOD VALUES, CHANGE IT WITH DB LATER
	private final int foodItem1 = 500;
	private final int foodItem2 = 3000;
	private final int foodItem3 = 4500;
	private final int foodDeacreses1 = -500;
	private final int foodDeacreses2 = -1500;
	private final int foodDeacreses3 = -3000;
	private Random intGenerator = new Random();
	private boolean deathByHunger = false;

	public HungerEngine(GameEngine ge, TamaGUI tg){
		this.ge = ge;
		this.tg = tg;
	}

	//THE LOOP
	@Override
	public void run() {
		while(ge.isALL_TREADS_RUNNING() == true){
			tg.setHungerBar();
			hungerWarnings();
			TamaEatsAtFriend();
			hungerBuilder(hungerBuilderTimeValue);
			dieByHunger();
		}
	}

	//RANDOM GENERATE, EAT AT FREIND
	private void TamaEatsAtFriend(){
		if(ge.getGameLevel() >= 2){
			int rndNr = intGenerator.nextInt(32);
			if (rndNr == 5){
				foodItem1();
				tg.setTextArea("Your Tama ate at a friends house"
						+ "\nGained 500 Energy");
			}
		}
	}

	//THREAD SLEEPER, TIME
	private void hungerBuilder(int x){
		tamaCurrentHunger -= getHungerValue();
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//GET HUNGER BY TEXT, NOT FUNKTIONALL YET
	public String tamaHungerTeller(){
		String getTamaCurrentHunger = null;

		if(tamaCurrentHunger >= 9000){
			getTamaCurrentHunger = "...My Tummy is full";
		}		
		else if (tamaCurrentHunger >= 8500){
			getTamaCurrentHunger = "...My Tummy is full";
		}	
		else if (tamaCurrentHunger >= 7500){
			getTamaCurrentHunger = "...I am good at the moment";
		}
		else if (tamaCurrentHunger >= 5000){
			getTamaCurrentHunger = "...I am good at the moment";
		}		
		else if(tamaCurrentHunger >= 4000){
			getTamaCurrentHunger = "...I am starting to get hungry";
		}
		else if(tamaCurrentHunger >= 2500){
			getTamaCurrentHunger = "...I am starting to get hungry";
		}		
		else if(tamaCurrentHunger >= 2500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}		
		else if(tamaCurrentHunger >= 2000){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}	
		else if(tamaCurrentHunger >= 1500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}		
		else if (tamaCurrentHunger >= 500){
			getTamaCurrentHunger = "...I am HUNGRY! ";
		}	
		return getTamaCurrentHunger;
	}

	//CHANGE SYSTEM
	//
	//Method for when the Tama dies by hunger
	private void dieByHunger(){
		if (tamaCurrentHunger <= 0){
			deathByHunger = true;
		}
	}

	//Gives hunger warnings for the user
	private void hungerWarnings(){
		if (tamaCurrentHunger <= 1000){
			tg.setTextArea("...I am so hungry");
		}
	}

	//Food items
	public void foodItem1(){	
		tamaCurrentHunger += foodItem1;
	}
	public void foodItem2(){	
		tamaCurrentHunger += foodItem2;
	}
	public void foodItem3(){	
		tamaCurrentHunger += foodItem3;
	}

	//lower the energy
	public void foodDecreases1(){
		tamaCurrentHunger += foodDeacreses1;
	}
	public void foodDecreases2(){
		tamaCurrentHunger += foodDeacreses2;
	}
	public void foodDecreases3(){
		tamaCurrentHunger += foodDeacreses3;
	}

	public boolean isDeathByHunger() {
		return deathByHunger;
	}
	//GET hunger
	public int getTamaCurrentHunger() {
		return tamaCurrentHunger;
	}
	//SET hunger
	public void setTamaCurrentHunger(int tamaCurrentHunger) {
		this.tamaCurrentHunger = tamaCurrentHunger;
	}

	public int getHungerValue() {
		return hungerValue;
	}

	public void setHungerValue(int hungerValue) {
		this.hungerValue = hungerValue;
	}

}
