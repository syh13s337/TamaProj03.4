package tamaSystem;

import tamaDB.TamaDBEngine;
import tamaDialogs.DialogEngine;
import tamaDialogs.TalkingToTamaEngine;
import tamaGUI.TamaGUI;
import tamaGUI.TamaGUIEnd;
import tamaGUI.TamaGUIFace;
import tamaGUI.TamaGUIStart;

/**THE GAME ENGINE
 * THE MASTER CLASS OF ALL CLASSES,
 * THE CLASS THAT RULE THEM ALL,
 */

public class GameEngine {

	//GAME VERSION, OH SNAP ITS STATIC!
	public static String TAMA_VERSION = "<TamaProj 03.4> ";
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

	//NEW SUPER PLUG IN, DB STUFF
	TamaDBEngine tdbe;
	public void setTdbe(TamaDBEngine tdbe) {
		this.tdbe = tdbe;

	}

	private TamaGUIEnd tge;
	private TamaGUIStart tgs;
	private TamaGUI tg;
	private TamaGUIFace tgf;

	private String frameTitle = "   ";
	private int moneyValue;
	private int depressionValue;
	private int energyValue;
	private int foodValue;
	private int happinessValue;

	//TAMASTATS VALUES AND UserID
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
	private	int userIdKey; 
	public int getUserIdKey() {
		return userIdKey;
	}
	public void setUserIdKey(int userIdKey) {
		this.userIdKey = userIdKey;
	}
	private	int hungerStats;
	private	int depressionStats;
	private	int moneyStats; //SAME AS moneyValue BUT FROM DB.

	private boolean tamaInDataBase = false;

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

	//START GAME AFTER DB
	public void startGameGUI(){
		if (tamaInDataBase == true){
			GameGUIWithStats(gameLevel, frameTitle, tamaName);		
		}
		else if (tamaInDataBase == false) {
			GameGuiStart();
			tamaInDataBase = false;
		}
	}

	//GAME LUNCHER. BEFORE GAME GUI
	//STARTS THIS WHEN NO TAMA IN DB
	//LAUNCHER AFTER LOG ING
	public void GameGuiStart(){
		tgs = new TamaGUIStart();
		tgs.TamaStartGUIStarter(ge);
	}
	
	//START GAME GUI WITOUTH STATS THAT IS SAVED FROM DB
	private void GameGUIWithStats(int gameLevel, String frameTitle, String tamaName){
		this.tamaName = tamaName;
		this.gameLevel = gameLevel;
		this.frameTitle = frameTitle;
		tg.TamaGUI(gameLevel, frameTitle, tamaName, he, mo, di, tt, de, ge);
		this.tgf = new TamaGUIFace(ge, tg, de, he);
		tdbe.gameValue(); 
		mo.setMoneyValue(moneyStats);
		threadStarter();
		tg.showGUI(true);
	}

	//THE MAIN GAME GUI,
	//SELF NOTE: Should start this if there is already a Tama.
	//THE MAIN GAME GUI
	//SELF NOTE: Now with gameValues from DB.
	public void GameGUIWithoutStats(int gameLevel, String frameTitle, String tamaName){
		initiater();
		this.tamaName = tamaName;
		this.gameLevel = gameLevel;
		this.frameTitle = frameTitle;
		tg.TamaGUI(gameLevel, frameTitle, tamaName, he, mo, di, tt, de, ge);
		this.tgf = new TamaGUIFace(ge, tg, de, he);
		tdbe.gameValue();
		he.setTamaCurrentHunger(10000);
		de.setTamaCurrentDepression(10000);
		threadStarter();
		tg.showGUI(true);
	}

	//GETS STATS FROM OTHER CLASSES AND SENDS IT TO TamaDBEngine
	public void saveTama(){
		if(tamaInDataBase == false){
			tdbe.saveStats(userIdKey, tamaName, gameLevel, he.getTamaCurrentHunger(),
					de.getTamaCurrentDepression(), mo.getCurrentMoney());
		}
		
		else if (tamaInDataBase == true){
			tdbe.updateStats(userIdKey, tamaName, gameLevel, he.getTamaCurrentHunger(),
					de.getTamaCurrentDepression(), mo.getCurrentMoney());		
			
		}

		
	}
	
	//GETS GAME VALUES FROM TamaDBEngine CLASS AND SETT THEM TO OTHER CLASSES.
	//METHOD THAT SETS THE VALUES BY GAME LEVEL.
	//AND SENDS VALUES TO ITS CLASSES.
	//NEED TamaDBEngine, WHERE IT GETS VALUES FROM DB.
	//SELF NOTE: FOOD and HAPPINESS value is not in use yet
	public void gameValueSetter(int mv1,int mv2,int mv3,int dv1,int dv2,
			int dv3,int ev1,int ev2,int ev3,int fv1,int fv2,int fv3,
			int hv1, int hv2, int hv3){

		if (gameLevel == 1){
			moneyValue = mv1;
			depressionValue = dv1;
			energyValue = ev1;
			foodValue = fv1;
			happinessValue = hv1;
		}
		else if (gameLevel == 2){
			moneyValue = mv2;
			depressionValue = dv2;
			energyValue = ev2;
			foodValue = fv2;
			happinessValue = hv2;
		}
		else if (gameLevel == 3){
			moneyValue = mv3;
			depressionValue = dv3;
			energyValue = ev3;
			foodValue = fv3;
			happinessValue = hv3;
		}

		mo.setMoneyValue(moneyValue);
		de.setDepressionValue(depressionValue);
		he.setHungerValue(energyValue);
	}

	//GETS TamaStats FROM TamaDBEngine AND SETS THEM TO THIS CLASS AND OTHER CLASSES.
	//SHOULD START AUTOMATIC IF THERE IS A TAMA IN DB.
	public void tamaStatsSetter(int userIdKey, String tamaName, int gameLevel, int hungerStats,
			int depressionStats, int moneyStats, boolean tamaInDataBase){
		initiater();
		this.tamaInDataBase = tamaInDataBase;
		this.userIdKey = userIdKey;
		this.tamaName = tamaName;
		this.gameLevel = gameLevel;
		this.hungerStats = hungerStats;
		this.depressionStats = depressionStats;
		this.moneyStats = moneyStats; 
		
		he.setTamaCurrentHunger(hungerStats);
		de.setTamaCurrentDepression(depressionStats);
		mo.setMoneyValue(moneyStats);

		//TEST
		System.out.println("userID: " + userIdKey + " TamaName: " + tamaName);
		System.out.println("GameLevel: " + gameLevel + " HungerStats: " + hungerStats);
		System.out.println("DepressionStats: " + depressionStats + " MoneyStats: " + moneyStats);
	}
	
	
	
	
}



