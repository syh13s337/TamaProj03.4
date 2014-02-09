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

	//in seconds, 3600 = 1h
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

	//THE LOOP, WIN/DEATH CHECKER
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


	public void deathByHunger(String tamaName){
		tge.setTextEndInfo("YOUR TAMA DIED OF HUNGER \n");
		tge.setTextEndInfo("RIP " + tamaName + "\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
	}

	public void deathByDepression(String tamaName){
		tge.setTextEndInfo("YOUR TAMA DIED OF DEPRESSION \n");
		tge.setTextEndInfo("RIP " + tamaName +"\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
	}

	public void winning(String tamaName){
		tge.setTextEndInfo("YOU WIN \n");
		tge.setTextEndInfo(tamaName + " is all grown up now!\n\n");
		ge.setALL_TREADS_RUNNING(false);
		tge.tamaEndGUIStarter();
	}

	public boolean isWin() {
		return win;
	}
}
