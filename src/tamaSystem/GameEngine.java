package tamaSystem;

import tamaDB.MySQLEngine;
import tamaDB.TamaGUILogIn;
import tamaDB.UserEngine;
import tamaDialogs.DialogEngine;
import tamaDialogs.TalkingToTamaEngine;
import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIEnd;
import tamaGUI.TamaGUIFace;
import tamaGUI.TamaGUIStart;

/**THE GAME ENGINE
 * THE MASTER CLASS OF ALL CLASSES,
 * THE CLASS THAT RULE THEM ALL,
 * 
 * 
 *
 */

public class GameEngine {

	//GAME VERSION, OH SNAP ITS STATIC!
	public static String TAMA_VERSION = "<TamaProj 03.3> ";
	public static String MADE_BY = "Arild Oderman";

	//THE LOOP/ENGINE STOPPER, SHOULD BE CONNECTED TO ALL "4EVER" LOOPED THREAD.
	private boolean ALL_TREADS_RUNNING = true;
	public boolean isALL_TREADS_RUNNING() {
		return ALL_TREADS_RUNNING;
	}
	public void setALL_TREADS_RUNNING(boolean aLL_TREADS_RUNNING) {
		ALL_TREADS_RUNNING = aLL_TREADS_RUNNING;
	}

	private WinAndEndEngine we;
	private DepressionEngine de;
	private HungerEngine he;
	private MoneyEngine mo;
	private DialogEngine di;
	private TalkingToTamaEngine tt;
	private ScoreEngine se;
	private GameEngine ge;
	public GameEngine getGe() {
		return ge;
	}
	public void setGe(GameEngine ge) {
		this.ge = ge;
	}

	private TamaGUIEnd tge;
	private TamaGUIStart tgs;
	private TamaGUI tg;
	private TamaGUIFace tgf;

	private MySQLEngine mysql;
	private UserEngine ue;
	private TamaGUILogIn tgli;

	private String tamaName = "";
	public String getTamaName() {
		return tamaName;
	}
	public void setTamaName(String tamaName) {
		this.tamaName = tamaName;
	}

	private int gameLevel;
	public int getGameLevel() {
		return gameLevel;
	}
	public void setGameLevel(int gameLevel) {
		this.gameLevel = gameLevel;
	}

	private void initiater(){
		this.tg = new TamaGUI();
		this.tge = new TamaGUIEnd();
		
		this.de = new DepressionEngine(ge, tg);
		this.he = new HungerEngine(ge, tg);
		this.mo = new MoneyEngine(ge, tg);

		this.di = new DialogEngine(ge, tg);
		this.tt = new TalkingToTamaEngine(tg);
		this.se = new ScoreEngine(ge, tge);
		this.we = new WinAndEndEngine(ge, tg, tge, de, he);
	}

	public GameEngine(){
	}

	private void threadStarter(){
		Thread depEngine = new Thread(de, "DepressionThread");
		Thread winEngine = new Thread(we, "WinThread");
		Thread hunEngine = new Thread(he, "FoodThread");
		Thread monEngine = new Thread(mo, "MoneyThead");
		Thread diaEngine = new Thread(di, "DialogThread");
		Thread scoEngine = new Thread(se, "ScoreThread");
		Thread tgfEngine = new Thread(tgf, "TgfThread");

		diaEngine.start();
		scoEngine.start();
		depEngine.start();
		winEngine.start();
		hunEngine.start();
		monEngine.start();
		tgfEngine.start();
	}

	//LOGIN LUNCHER!
	public void StartLogIn(){
		this.tgli = new TamaGUILogIn();
		this.ue = new UserEngine(tgli);
		this.mysql = new MySQLEngine(ge, tgli);


		this.tgli.loginStarter(ge, tgli, ue, mysql);
	}

	//LAUNCHER AFTER LOG ING
	//
	public void GameGuiStart(){
		tgs = new TamaGUIStart();
		tgs.TamaStartGUIStarter(ge);
	}

	//THE MAIN GAME GUI
	public void GameGUI(int gameLevel, String frameTitle, String tamaName){
		initiater();

		this.tamaName = tamaName;
		this.gameLevel = gameLevel;

		tg.TamaGUI(gameLevel, frameTitle, tamaName, he, mo, di, tt, de);
		this.tgf = new TamaGUIFace(ge, tg, de, he);

		threadStarter();

		tg.showGUI(true);
	}
}



