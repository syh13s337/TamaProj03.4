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

	public DBUserEngine(DBMySQLEngine dbmysqls, DBTamaGUILogIn dbtgli){
		this.dbmysqls = dbmysqls;
		this.dbtgli = dbtgli;
	}

	//SHOW SCORE, START INFORMATION FOR USERS
	public void showScore(){
		dbtgli.setTextLogInInformation(dbmysqls.getScores());
	}

	//CREAT A USER
	//WITH BOOLEAN CHECKER ON USERNAME, PASSWORD AND EMAIL.
	public void createUsers(String userName, String userPassword,
			boolean tmpBooleanPass, boolean tmpBooleanMail){
		this.userName=userName;
		dbmysqls.connectionMethod(dbmysqls.getGUI_USERNAME(), dbmysqls.getGUI_PASSWORD());
		if(userChecker(userName) == false && tmpBooleanPass == true
				&& tmpBooleanMail == true){
			System.out.println("YOU CAN CREATE A USER!");
		}
		else if (userChecker(userName) == true){
			dbtgli.popUpMessage("This user name is in use!"
					+ "\nTry a new one.");
		}
		else{
			System.out.println("NOPE!");

		}
	}

	//LOG IN A USER
	//SELF NOTE: When logged in, start the game!
	//Add game method.
	public void userLogIn(String userName, String userPassword){
		this.userName=userName;
		String checkNameAndPass = "SELECT username='" + userName + "' FROM user WHERE password='" + userPassword + "';";
		dbmysqls.connectionMethod(dbmysqls.getGUI_USERNAME(), dbmysqls.getGUI_PASSWORD());
		dbmysqls.statementMethod();

		if (dbmysqls.selectMethodSingle(checkNameAndPass).equals("1")){
			System.out.println("Your logged in!");
		}
		else{
			dbtgli.popUpMessage("Problem with loggin!"
					+ "\nCheck user name and password!");
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
