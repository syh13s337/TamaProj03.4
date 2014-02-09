package tamaSystem;

import java.text.DecimalFormat;

import tamaGUI.TamaGUIEnd;


/** SCORE ENGINE CLASS
 * This class will do:
 *	1. count score
 * 	2. Show score
 * 
 */
public class ScoreEngine implements Runnable {
	
	private GameEngine ge;
	private TamaGUIEnd tge;
	
	private int theScore;
	public int getTheScore() {
		return theScore;
	}
	public void setTheScore(int theScore) {
		this.theScore = theScore;
	}

	private double scoreCounter;
	private double hAlive;
	private double dAlive;
	private double mAlive;
	private double yAlive;

	private DecimalFormat df = new DecimalFormat("0.00");  

	public ScoreEngine(GameEngine ge, TamaGUIEnd tge){
		this.ge = ge;
		this.tge = tge;
	}

	public void totalAliveTime(){
		hAlive = scoreCounter/6;
		dAlive = scoreCounter/144;
		mAlive = scoreCounter/4320;
		yAlive = scoreCounter/51840;
		
		tge.setTextEndInfo("Total Alive Time, in Tama Time: "
				+ "\n"
				+ "\nHoures Alive: " + df.format(hAlive)
				+ "\nDays Alive: " + df.format(dAlive)
				+ "\nMonth Alive: " + df.format(mAlive)
				+ "\nYears Alive: " + df.format(yAlive)
				+ "\nyour score is: " + (scoreCounter) +"\n");
	}

	@Override
	public void run() {
		scoreCounter = 0;
		int z = 1;
		while(z == 1){
			if (ge.isALL_TREADS_RUNNING() == false){
				totalAliveTime();
				z = 0;
				break;
			}
			else if (ge.isALL_TREADS_RUNNING() == true){
				try {
					this.scoreCounter += 10;
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}






