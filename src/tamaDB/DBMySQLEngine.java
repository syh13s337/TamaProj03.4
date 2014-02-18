package tamaDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/*MySQL Engine
 * 
 * This class will work with MySQL.
 * It will get DB and maybe get DB too.
 * Most methods here takes querys strings from other class.
 *  
 * 
 * 
 * SELF NOTE:
 * CHECKA INDEX SYTAX/SYSTEM:
 * FOR FASTER SEARCH AND FINIDNG VALUES.
 *
 * ADD CLOSERS!!!
 */

public class DBMySQLEngine {
	//
	//SELF NOTE: In case future upgrade/change.
	//DB PATHWAY.
	private final String SERVER_NAME = "localhost";
	private final String DATABASE_NAME = "dbprojecttama";
	private final int PORT = 3306;

	//DBMySQLENGINE USERNAME AND PASS
	//RESTRICTED TO BASE FUNKTION
	private final String GUI_USERNAME = "tamaadmin";
	public String getGUI_USERNAME() {
		return GUI_USERNAME;
	}
	private final String GUI_PASSWORD = "java13";
	public String getGUI_PASSWORD() {
		return GUI_PASSWORD;
	}

	private MysqlDataSource ds;
	private Connection con = null;
	private Statement queryCaller = null;
	private ResultSet result = null;
	private java.sql.PreparedStatement psStream = null;
	private String inString = null;
	private int nCols;

	/**rowCount, NOT IN USE
	 * <3 ANNIKA <3 eyes only!
	 * NOTE: Was for a method that gets row count from a table.
	 * To use as id key generator. 
	 */
	private int rowCount;
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	//ARRAY WITH GAME VALUES
	private ArrayList <Integer> gameValues;
	public ArrayList<Integer> getGameValuesArray() {
		return gameValues;
	}
	public void setGameValuesArray(ArrayList<Integer> gameValues) {
		this.gameValues = gameValues;
	}

	private String tmpString;
	//OTHER CLASS
	private DBTamaGUILogIn dbtgli;
	private TamaDBEngine tdbe;

	private ArrayList <String> selectMethodSingle;
	public void clearSelectMethodSingleArray(){
		this.selectMethodSingle.clear();
	}
	public ArrayList<String> getSelectMethodSingle() {
		return selectMethodSingle;
	}

	//DOING NOTHING ATM.
	public void getMySQLDB(String user, String password){
		connectionMethod(user, password);
		selectMethod("SELECT * FROM gamevalues;");
		getGameValue();
	}

	public DBMySQLEngine(DBTamaGUILogIn dbtgli, TamaDBEngine tdbe){
		this.dbtgli = dbtgli;
		this.tdbe = tdbe;
	}

	public DBMySQLEngine(){
	}

	public void connectionMethod(String user, String password){
		ds = new MysqlDataSource(); 
		ds.setServerName(SERVER_NAME);
		ds.setPort(PORT);
		ds.setDatabaseName(DATABASE_NAME);

		try {
			this.con = ds.getConnection(user, password);
			statementMethod();
		} catch (SQLException e) {
			System.out.println("-ERROR: connectionMethod");
		}		
		System.out.println("*SUCCSESS: connectionMethod");
	}

	//SELF NOTE: Bake this in into another method.
	//Maybe in connectionMethod. Get on that FUTURE ARILD!
	public void statementMethod(){
		try {
			queryCaller = con.createStatement();
		} catch (SQLException e) {
			try {
				con.close();
			} catch (SQLException e1) {
			}
			System.out.println("-ERROR: statementMethod" + e.getMessage());
		}
		System.out.println("*SUCCSESS: statementMethod");
	}

	//INSERT V1, MOSTLY TEST
	//WILL MAKE INSERTS THROUGH A PREPARED STATEMENT
	public void insertMethod(){
		int affectedRows = 0;
		try {
			affectedRows = queryCaller.executeUpdate("INSERT INTO actor VALUES('201', 'IVAN', 'DRAGO', '2006-02-15 04:34:33')");
		} catch (SQLException e) {
			System.out.println("-ERROR: insertMethod" + e.getMessage());
		}
		System.out.println("*SUCCSESS: insertMethod");
		System.out.println("Adected rows: " + affectedRows);
	}

	public void createUser(String userName, String userPassword, String userEmail){
		System.out.println("INSERT INTO `dbprojecttama`.`user` (`username`, `password`, `usermail`) VALUES ('"+ userName +"', '"+ userPassword +"', '"+ userEmail +"');");
		try {
			queryCaller.executeUpdate("INSERT INTO `dbprojecttama`.`user` (`username`, `password`, `usermail`) VALUES ('"+ userName +"', '"+ userPassword +"', '"+ userEmail +"');");

		} catch (SQLException e) {
			System.out.println("-ERROR: createUser" + e.getMessage());
		}
	}

	//NOT IN USE, BACK UP.
	//SELF NOTE: Change to prepared statments later!
	public void saveStats(int userIdKey, String tamaName, int gameLevel,
			int hungerStats, int depressionStats, int moneyStats, int scoreStats, int tmpInt){

			if (tmpInt == 1){
				preparedStatmentsForSave(userIdKey, tamaName, gameLevel, hungerStats, depressionStats, moneyStats, scoreStats);
			}
			else if (tmpInt == 2){
				preparedStatmentsForUpdate(userIdKey, tamaName, gameLevel, hungerStats, depressionStats, moneyStats, scoreStats);
			}
	}

	
	//FOR SAVING SCORE TO DB
	public void saveScoreToDB(int userIdKey, String tamaName, int theScore, String deathBy){
		System.out.println("INSERT INTO dbprojecttama.scores (userid, tamaname, totalpoints, deathby) VALUES ("+userIdKey+", "+tamaName+", "+theScore+", "+deathBy+")");
		try {
			psStream = con.prepareStatement("INSERT INTO dbprojecttama.scores (userid, tamaname, totalpoints, deathby) VALUES (?, ?, ?, ?)");
			psStream.setInt(1, userIdKey);
			psStream.setString(2, tamaName);
			psStream.setInt(3, theScore);
			psStream.setString(4, deathBy);
			psStream.executeUpdate();
		} catch (SQLException e) {
			System.out.println("-ERROR: saveScoreToDB" + e.getMessage());
		}
	}

	//FOR SAVING TAMA STATS, INSERT
	public void preparedStatmentsForSave(int userIdKey, String tamaName, int gameLevel,
			int hungerStats, int depressionStats, int moneyStats, int scoreStats){
		try {
			psStream = con.prepareStatement("INSERT INTO dbprojecttama.tamastats (userid, tamaname, gamelevel, hungerstats, depressionstats, moneystats, scorestats) VALUES (?, ?, ?, ?, ?, ?, ?)");
			psStream.setInt(1, userIdKey);
			psStream.setString(2, tamaName);
			psStream.setInt(3, gameLevel);
			psStream.setInt(4, hungerStats);
			psStream.setInt(5, depressionStats);
			psStream.setInt(6, moneyStats);
			psStream.setInt(7, scoreStats);
			
			psStream.executeUpdate();
		} catch (SQLException e) {
			System.out.println("-ERROR: preparedStatmentsForSave" + e.getMessage());
		}
	}

	//FOR UPDATING TAMA STATS, UPDATE
	public void preparedStatmentsForUpdate(int userIdKey, String tamaName, int gameLevel,
			int hungerStats, int depressionStats, int moneyStats, int scoreStats){
		//TEST
		System.out.println("User ID: " + userIdKey);
		System.out.println("TamaName: " + tamaName);
		System.out.println("HungerStats: " + hungerStats);
		System.out.println("DepressionStats: " + depressionStats);
		System.out.println("MoneyStats: " + moneyStats);
		System.out.println("Score: " + scoreStats);
		
		try {
			psStream = con.prepareStatement("UPDATE dbprojecttama.tamastats SET tamaname=?, gamelevel=?, hungerstats=?, depressionstats=?, moneystats=?, scorestats=? WHERE userid=?;");
			psStream.setString(1, tamaName);
			psStream.setInt(2, gameLevel);
			psStream.setInt(3, hungerStats);
			psStream.setInt(4, depressionStats);
			psStream.setInt(5, moneyStats);
			psStream.setInt(6, scoreStats);		
			psStream.setInt(7, userIdKey);
			
			psStream.executeUpdate();
		} catch (SQLException e) {
			System.out.println("-ERROR: preparedStatmentsForUpdate" + e.getMessage());
		}
	}


	//NOT FULLY WORKING YET
	public void preparedStatementSaveForScore(int userIdKey, String tamaName, int theScore, String deathBy){
		try {
			psStream = con.prepareStatement("INSERT INTO `dbprojecttama`.`scores` (`userid`, `tamaname`, `totalpoints`, `deathby`) VALUES (?, ?, ?, ?);");
			psStream.setInt(1, userIdKey);
			psStream.setString(2, tamaName);
			psStream.setInt(3, theScore);
			psStream.setString(4, deathBy);

			psStream.executeQuery();
		} catch (SQLException e) {
			System.out.println("-ERROR: preparedStatementSaveForScore");
		}
	}

	//SELECT AND GETS INFO, NEED QUERYES
	//NOT IN USE.
	public void selectMethod(String querys){
		try {
			//SELECT
			result = queryCaller.executeQuery(querys);
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			this.nCols = resultInfo.getColumnCount();

			for (int i = 1; i < nCols; i++) {
				System.out.println(resultInfo.getColumnLabel(i) + " ");

				//TEST
				inString = resultInfo.getColumnLabel(i) + " ";
				//				dbtgli.setTextLogInInformation(inString);
			}
			while(result.next()){
				//GET RESULT BY COLUM NAME
				//				System.out.println(result.getString("first_name"));	
				//				inString += " " + result.getString("first_name"); 

				// DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				//GET ALL RESULT		
				for (int i = 1; i < nCols; i++) {
					inString += result.getString(i) + " : OUT PUT ";
					System.out.println(result.getString(i) + " : OUT PUT ");
				}

				//SPECIFIC
				//				String tamaname = result.getString("tamaname");
				//				String deathBy = result.getString("deathBy");
				//
				//				dbtgli.setTextLogInInformation(tamaname);
				//				dbtgli.setTextLogInInformation(deathBy);

				dbtgli.setTextLogInInformation(inString);
				System.out.println(inString);
				rowCount++;
				inString = "";
			}

		} catch (SQLException e) {
			System.out.println("-ERROR: queryCaller" + e.getMessage());
		}
		System.out.println("*SUCCSESS: queryCaller");
	}

	//SELECT SINGEL, RETURNS STRING VALUE OF 1 COLUMN, 1 ROW
	public String selectMethodSingle(String querys){
		tmpString = "";
		try {
			result = queryCaller.executeQuery(querys);
			result.first();
			try{
				tmpString = result.getString(1);	
			}
			catch (SQLException e1) {
				//ERROR MESSAGE IF RESULT TO STRING IS NULL
				tmpString = "nothingHere";
			}

		} catch (SQLException e) {
			System.out.println("-ERROR: queryCaller" + e.getMessage());
		}
		System.out.println("*SUCCSESS: queryCaller");
		return tmpString;
	}

	//METHOD THAT GETS TAMA STATS FROM SAVED TAMA
	//SENDS INFORMATION TO TamaDBEngine
	public void selectMethodTamaStats(int userId){
		String querys = "SELECT * FROM tamastats WHERE userid='" + userId + "';";

		try {
			result = queryCaller.executeQuery(querys);
			result.first();

			long tmpUserId = result.getLong(1);	
			String tamaName = result.getString(2);	
			long tmpGameLevel = result.getLong(3);	
			long tmpHungerStats = result.getLong(4);	
			long tmpDepressionStats = result.getLong(5);	
			long tmpMoneyStats = result.getLong(6);	
			long tmpscoreStats = result.getLong(7);	

			int userIdKey = (int)tmpUserId;
			int gameLevel = (int)tmpGameLevel;
			int hungerStats = (int)tmpHungerStats;
			int depressionStats = (int)tmpDepressionStats;
			int moneyStats = (int)tmpMoneyStats;
			int scoreStats = (int)tmpscoreStats;

			tdbe.tamaStats(userIdKey, tamaName, gameLevel,
					hungerStats, depressionStats, moneyStats, scoreStats);

		} catch (SQLException e) {
			System.out.println("-ERROR: queryCaller" + e.getMessage());
		}
		System.out.println("*SUCCSESS: queryCaller");
	}

	//EASY GET RESULT SET FROM QUERY STRING METHOD
	//NOT IN USE.
	//SELF NOTE: Make this main result.
	public ResultSet getResult(String querys){
		try {
			result = queryCaller.executeQuery(querys);
		} catch (SQLException e) {
			System.out.println("-ERROR: getResult");
		}
		return result;
	}

	//GET GAME VALUES FROM DB, 
	//PUT IT IN A ARRAY LIST.
	public void getGameValue(){
		gameValues = new ArrayList<Integer>();
		try {
			result = queryCaller.executeQuery("SELECT * FROM gamevalues;");
			ResultSetMetaData resultInfo = result.getMetaData();
			int nCols = resultInfo.getColumnCount();
			while(result.next()){	
				int tmpColumnCounter = 0;
				for (int i = 1; tmpColumnCounter < nCols; i++) {
					gameValues.add(result.getInt(i));		
					tmpColumnCounter++;
				}
			}
		} catch (SQLException e) {
			System.out.println("-QueryCaller ERROR!" + e.getMessage()); //TESTER
		}
		System.out.println("*QueryCaller succsess!"); //TESTER
	}

	//GET HIGH SCORE, WITH FULL DAY/YEAR INFORMATION
	//MAYBE MAKE NEW MATH ABOUT INFORMATION?
	//tmpInt/6 = hours
	//tmpInt/144 = days
	//tmpInt/4320 = months
	//tmpInt/51840 = years
	public String getScores(){
		String getScores  = "";
		getScores  = "\t (>^_^)> TAMA HIGH SCORE <(^_^<) \n\n";
		connectionMethod(GUI_USERNAME, GUI_PASSWORD);
		DecimalFormat df = new DecimalFormat("0.00"); 
		int higeScoreNr = 1;

		try {
			result = queryCaller.executeQuery("SELECT totalpoints, tamaname, deathby FROM scores ORDER BY totalpoints desc limit 10;");
			while(result.next()){
				String tamaPoints = result.getString("totalpoints");
				int points = Integer.parseInt(tamaPoints);
				String tamaname = result.getString("tamaname");
				String deathBy = result.getString("deathBy");
				getScores +="\t" + higeScoreNr + ">" + " : " + tamaPoints +"p" + " - " + tamaname + " died by " + deathBy + "\n"
						+ "\tDays alive - " + df.format(points/144) + "\n";
				higeScoreNr++;
			}

		} catch (SQLException e) {
			System.out.println("-QueryCaller ERROR!" + e.getMessage());
		}
		System.out.println("*QueryCaller succsess!");
		return getScores;
	}

	//RESULT SET TO STRING.
	//SELF NOTE: Make this method that takes in result set
	//makes it to String and sends it to JTextArea. 
	private String resultToGUI(ResultSet resultSetIn){
		String resultToString = null;
		try {
			while (resultSetIn.next()) {
				// It is possible to get the columns via name
				// also possible to get the columns via the column number
				// which starts at 1
				// e.g. resultSet.getSTring(2);

				String user = resultSetIn.getString("myuser");

				System.out.println("User: " + user);

			}
		} catch (SQLException e) {
			System.out.println("-ERROR: resultToGUI");
		}
		return resultToString;
	}

	//SELF NOTE: FUTURE ARILD, Make commit method before sending/executeQuery
	private void commitMethod(){
	}

	/**
	 * USE IT ARILD, USE THE FORCE!
	 */
	//CLOSE EVERYTHING METHOD
	public void closeEverything(){
		if(result != null){
			try {
				result.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close results.");
			}
		}
		if(queryCaller != null){
			try {
				queryCaller.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close the statement.");
			}
		}
		if(con != null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Warning: Couldn't close the connection.");
			}
		}
	}
}
