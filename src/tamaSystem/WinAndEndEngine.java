package tamaSystem;

import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIEnd;

/** WIN ENGINE CLASS
 * This class is for winners! 
 * The class will will start a counter and when its reached
 * player win Tama. It also check if Tama have died.
 * 
 *
 */

public class WinAndEndEngine implements Runnable {

	private boolean win = false;
	private GameEngine ge;
	private	TamaGUIEnd tge;
	private TamaGUI tg;
	private DepressionEngine de;
	private HungerEngine he;

	//VALUE FOR THE WIN TIM, 3600 SEC = 1 HOUR.
	private int tamaWinTimer = 3600;

	public WinAndEndEngine(GameEngine ge, TamaGUI tg, TamaGUIEnd tge,
			DepressionEngine de, HungerEngine he){
		this.ge = ge;
		this.tg = tg;
		this.tge = tge;
		this.de = de;
		this.he = he;
	}

	public WinAndEndEngine(){
	}
	
	//THE LOOP
	//SLEEP TIMER 1000 MILISEC, NORMAL (CHANGE IT LATER IF NEEDED)
	@Override
	public void run() {
		int tmpTimeCounter = 0;

		while(ge.isALL_TREADS_RUNNING() == true){
			if(tamaWinTimer <= tmpTimeCounter){
				win = true;
			}
			else if (tmpTimeCounter != tamaWinTimer){
				try {
					deathAndWinChecker();
					tmpTimeCounter++;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//CHECKS OTHER CLASSEX IF TAMA DIED BY HUNGER OR DEPRESSION.
	//IT ALSO CHECKS IF USER WON, BY WIN TIME.
	private void deathAndWinChecker(){
		if (de.isDeathByDepression() == true){
			deathByDepression(ge.getTamaName());
			tg.showGUI(false);
		}
		else if(he.isDeathByHunger() == true){
			deathByHunger(ge.getTamaName());
			tg.showGUI(false);
		}
		else if (isWin() == true){
			winning(ge.getTamaName());
			tg.showGUI(false);
		}
	}


	private void deathByHunger(String tamaName){
		tge.setTextEndInfo("YOUR TAMA DIED OF HUNGER \n");
		tge.setTextEndInfo("RIP " + tamaName + "\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
		ge.tamaDied("Hunger");
	}

	private void deathByDepression(String tamaName){
		tge.setTextEndInfo("YOUR TAMA DIED OF DEPRESSION \n");
		tge.setTextEndInfo("RIP " + tamaName +"\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
		ge.tamaDied("Depression");
	}

	private void winning(String tamaName){
		tge.setTextEndInfo("YOU WIN \n");
		tge.setTextEndInfo(tamaName + " is all grown up now!\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
		ge.tamaDied("WIN");
	}

	public boolean isWin() {
		return win;
	}
}
