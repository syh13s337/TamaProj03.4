package tamaDB;

import tamaSystem.GameEngine;

/*TAMA DB ENGINE
 *The Class that rule all the DB/MySQL sub-classes.
 * 
 * 
 */

public class TamaDBEngine {

	private TamaDBEngine tdbe;
	public TamaDBEngine getTdbe() {
		return tdbe;
	}
	public void setTdbe(TamaDBEngine tdbe) {
		this.tdbe = tdbe;
	}

	private DBMySQLEngine dbmysql;
	private DBUserEngine dbue;
	private	DBTamaGUILogIn dbtgli;
	private GameEngine ge;

	public TamaDBEngine(GameEngine ge){
		this.ge = ge;
	}

	//LOGIN LUNCHER!
	public void StartLogIn(){
		dbtgli = new DBTamaGUILogIn();	
		dbmysql = new DBMySQLEngine(dbtgli, tdbe);
		dbue = new DBUserEngine(dbmysql, dbtgli, tdbe);

		dbtgli.loginStarter(dbue, dbtgli);
	}

	//SAVES STATS BY UPDATE, SENDS IT TO DBMySQLEngine.
	public void updateStats(int userIdKey, String tamaName, int gameLevel, int hungerStats,
			int depressionStats, int moneyStats, int scoreStats){
		dbmysql.saveStats(userIdKey, tamaName, gameLevel,
				hungerStats, depressionStats, moneyStats, scoreStats,  2);
	}

	//SAVES STATS, SENDS IT TO DBMySQLEngine.
	public void saveStats (int userIdKey, String tamaName, int gameLevel, int hungerStats,
			int depressionStats, int moneyStats, int scoreStats){
		dbmysql.saveStats(userIdKey, tamaName, gameLevel,
				hungerStats, depressionStats, moneyStats, scoreStats, 1);	
	}

	public void sendInfo(int userIdKey){
		ge.setUserIdKey(userIdKey);	
	}

	//METHOD FOR GAME VALUES.
	//GETS VALUES AND SENDS IT TO GameEngine.
	//METHOD IN GameEngine SORTS THE VALUES TO OTHER CLASSES
	public void gameValue(){
		dbmysql.getGameValue();
		int mv1, mv2, mv3, dv1, dv2, dv3, ev1, ev2, ev3, fv1, fv2, fv3,
		hv1, hv2, hv3;
		//LEVEL1
		mv1 = dbmysql.getGameValuesArray().get(1);
		dv1 = dbmysql.getGameValuesArray().get(2);
		ev1 = dbmysql.getGameValuesArray().get(3);
		fv1 = dbmysql.getGameValuesArray().get(4);
		hv1 = dbmysql.getGameValuesArray().get(5);
		//LEVEL2
		mv2 = dbmysql.getGameValuesArray().get(7);
		dv2 = dbmysql.getGameValuesArray().get(8);
		ev2 = dbmysql.getGameValuesArray().get(9);
		fv2 = dbmysql.getGameValuesArray().get(10);
		hv2 = dbmysql.getGameValuesArray().get(11);
		//LEVEL3
		mv3 = dbmysql.getGameValuesArray().get(13);
		dv3 = dbmysql.getGameValuesArray().get(14);
		ev3 = dbmysql.getGameValuesArray().get(15);
		fv3 = dbmysql.getGameValuesArray().get(16);
		hv3 = dbmysql.getGameValuesArray().get(17);

		ge.gameValueSetter(mv1, mv2, mv3, dv1, dv2,
				dv3, ev1, ev2, ev3, fv1, fv2, fv3, hv1, hv2, hv3);		

		//SYSO TEST
		System.out.println(dbmysql.getGameValuesArray());
		System.out.println("\nMv1-3 " + mv1 + " " + mv2 + " " + mv3 
				+ "\nDv1-3 " + dv1 + " " + dv2 + " " + dv3
				+ "\nEv1-3 " + ev1 + " " + ev2 + " " + ev3 
				+ "\nFv1-3 " + fv1 + " " + fv2 + " " + fv3
				+ "\nHv1-3 " + hv1 + " " + hv2 + " " + hv3 + "\n");
	}

	//JUST SENDS INFORMATION TO GameEngine.
	//SELF NOTE: Change the system!
	public void tamaStats(int userIdKey, String tamaName, int gameLevel, int hungerStats,
			int depressionStats, int moneyStats, int scoreStats){
		boolean tamaInDataBase = true;

		ge.tamaStatsSetter(userIdKey, tamaName, gameLevel,
				hungerStats, depressionStats, moneyStats, scoreStats, tamaInDataBase);
	}

	public void saveScoreToDB(int userIdKey, String tamaName, int theScore, String deathBy){
		dbmysql.saveScoreToDB(userIdKey, tamaName, theScore, deathBy);

	}


	//REDERECTS TO GameEngine THAT STARTS THE GAME GUI
	public void startGameGUI(){
		ge.startGameGUI();
	}
}
