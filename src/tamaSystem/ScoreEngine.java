package tamaSystem;

import java.text.DecimalFormat;
import tamaGUI.TamaGUIEnd;

/** SCORE ENGINE CLASS
 * This class will do:
 *	1. count score
 * 	2. Show score
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

	private double hAlive;
	private double dAlive;
	private double mAlive;
	private double yAlive;

	private DecimalFormat df = new DecimalFormat("0.00");  

	public ScoreEngine(GameEngine ge, TamaGUIEnd tge){
		this.ge = ge;
		this.tge = tge;
	}

	//THE LOOP
	//COUNTS THE SCORE BY ADDING 10 POINTS * GAME LEVEL.
	@Override
	public void run() {
		int z = 1;
		while(z == 1){
			if (ge.isALL_TREADS_RUNNING() == false){
				totalAliveTime();
				z = 0;
				break;
			}
			else if (ge.isALL_TREADS_RUNNING() == true){
				try {
					this.theScore += 10 * ge.getGameLevel();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	//METHOD THAT TURNS SCORE INT TO HOUR, DAY, MONTH, YEARS
	//AND SET IT TO GUI.
	//SELF NOTE: the numbers that divides the score might need to change.
	//make it so that 1h in real life is 1 year in Tama time
	//Maybe make the method return to string value for better coding.
	public void totalAliveTime(){
		hAlive = theScore/6;
		dAlive = theScore/144;
		mAlive = theScore/4320;
		yAlive = theScore/51840;

		tge.setTextEndInfo("Total Alive Time, in Tama Time: "
				+ "\n"
				+ "\nHoures Alive: " + df.format(hAlive)
				+ "\nDays Alive: " + df.format(dAlive)
				+ "\nMonth Alive: " + df.format(mAlive)
				+ "\nYears Alive: " + df.format(yAlive)
				+ "\nyour score is: " + (theScore) +"\n");
	}
}






