package tamaDB;


/*USER CLASS
 * THIS CLASS WORKS WITH USER/PASSWORD
 * 
 * 1, LOG IN CHECKER.
 * 2, CREATE NEW USER
 * 3, GRANTS NEW USER.
 *  
 *CHANGE LATER:
 * tamaadmin GRANT privilage, so it dont get used in a bad way.
 * 
 * 
 * ACCOUNT FOR ADMIN: STRING
 * tamaadmin
 * java13
 * 
 * ACCOUNT FOR INFOGETTER: STRING
 * infogetter
 * tamaproj
 * 
 */
public class DBUserEngine {

	private String userName;
	private DBTamaGUILogIn dbtgli;
	private DBMySQLEngine dbmysqls;
	private TamaDBEngine tdbe;
	private DBTamaGUICreateUser dbtgcu;
	public void setDbtgcu(DBTamaGUICreateUser dbtgcu) {
		this.dbtgcu = dbtgcu;
	}

	private int userIdKey;

	public DBUserEngine(DBMySQLEngine dbmysqls, DBTamaGUILogIn dbtgli, TamaDBEngine tdbe){
		this.tdbe = tdbe;
		this.dbmysqls = dbmysqls;
		this.dbtgli = dbtgli;
	}

	//SHOW SCORE, START INFORMATION FOR USERS
	public void showScore(){
		dbtgli.setTextLogInInformation(dbmysqls.getScores());
	}
	
	//CREAT A USER
	//WITH BOOLEAN CHECKER ON USERNAME, PASSWORD AND EMAIL.
	//SENDS INFORMATION TO DBMySQLEngine,
	public void createUsers(String userName, String userPassword, String userEmail,
			boolean tmpBooleanPass, boolean tmpBooleanMail){
		this.userName=userName;
		dbmysqls.connectionMethod(dbmysqls.getGUI_USERNAME(), dbmysqls.getGUI_PASSWORD());
		if(userChecker(userName) == false && tmpBooleanPass == true
				&& tmpBooleanMail == true){
			System.out.println("YOU CAN CREATE A USER!");
			dbmysqls.createUser(userName, userPassword,
					userPassword);
		}
		else if (userChecker(userName) == true){
			dbtgli.popUpMessage("This user name is in use!"
					+ "\nTry a new one.");
		}
		else{
			System.out.println("NOPE!");
		}
	}

	//LOG IN A USER. MAIN METHOD TO LOG IN/CHECK AND PLAY.
	//CHECKS WITH KEY IF TAMA IS THERE.
	//SELF NOTE: When logged in, start the game!
	//Add game method.
	public void userLogIn(String userName, String userPassword){
		boolean booleantmp = false;
		this.userName=userName;
		String checkNameAndPass = "SELECT username='" + userName + "' FROM user WHERE password='" + userPassword + "';";
		dbmysqls.connectionMethod(dbmysqls.getGUI_USERNAME(), dbmysqls.getGUI_PASSWORD());
		dbmysqls.statementMethod();

		if (dbmysqls.selectMethodSingle(checkNameAndPass).equals("1")){
			String tmpStr = dbmysqls.selectMethodSingle("SELECT userid FROM user WHERE username='" + userName + "';");
			this.userIdKey  = Integer.parseInt(tmpStr); 

			System.out.println("Your logged in! " + userIdKey);

			//IF THERE IS A TAMA, selectMethodTamaStats. AND SEND TAMA INT STATS TO GameEngine.
			//USE UPDATE SQL TO UPDATE TAMA STATS
			if(tamaDbChecker(userIdKey) == false){
				System.out.println("There is Tama in DB!");
				dbmysqls.selectMethodTamaStats(userIdKey);
			}
			//else if there is no Tama, Insert name and new stats.
			//Maybe pop up game luncher.
			else if(tamaDbChecker(userIdKey) == true){
				System.out.println("No Tama in DB! ");
				tdbe.sendInfo(userIdKey);				

			}
			booleantmp = true;
		}
		else{
			dbtgli.popUpMessage("Problem with loggin!"
					+ "\nCheck user name and password!");
		}

		//STARTS GAME GUI WITH BOOLEAN CHECKER.
		if (booleantmp == true){
			tdbe.startGameGUI();
			dbtgli.hideFrame();
		}
	}

	//CHECK IF THE USER IS ALREADY CREATED. START AS FALSE, IF TRUE THE NAME IS IN USE.
	//SELF NOTE: : V1, Old method that checks the resultset string. if it equals userName.
	//V2, Let MySQL work with names that is already in DB and catch the error?
	//V3, make a boolean check if the username is already in DB
	private boolean userChecker(String userName){
		boolean userChecker;
		String logInChecker = "SELECT username FROM user WHERE username='" + userName + "';";
		if (dbmysqls.selectMethodSingle(logInChecker).equals(userName)){
			userChecker = true;
		}
		else{
			userChecker = false;
		}
		return userChecker;
	}

	//CHECKS IF THE KEY IS IN USE IN TAMA SAVE, 
	//IF NOT CREATE A NEW TAMA FROM SCRATCH
	private boolean tamaDbChecker(int userIdKey){
		boolean tamaDbChecker = true;
		String tmpStr = dbmysqls.selectMethodSingle("SELECT userid FROM tamastats WHERE userid='" + userIdKey + "';");
		if (!tmpStr.equals("nothingHere")){
			tamaDbChecker = false;
		}
		return tamaDbChecker;		
	}

	/**createUserId, NOT IN USE
	 *  <3 ANNIKA <3  eyes only!
	 *  Part of id key generator. It takes number from rowCount 
	 *  adds 1+. And use that as new key.
	 */ 	
	//GET INT# COUNT BY WHILE LOOP IN SUPER CLASS
	//MAYBE ANOTHER WAY TO DO IT???
	//SELF NOTE: Yes! the other way is auto increment.
	private int createUserId(){
		dbmysqls.selectMethod("SELECT userid FROM user; ");
		int createUserId = dbmysqls.getRowCount() + 1;
		return createUserId;
	}
}
