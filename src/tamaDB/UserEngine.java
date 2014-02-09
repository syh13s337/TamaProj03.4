package tamaDB;

import java.sql.SQLException;
import com.mysql.jdbc.PreparedStatement;


/*USER CLASS
 * THIS CLASS WORKS WITH USER/PASSWORD
 * 
 * 1, LOG IN CHECKER.
 * 2, CREATE NEW USER
 * 3, GRANTS NEW USER.
 * 
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
public class UserEngine extends MySQLEngine {

	private String userName;
	private TamaGUILogIn tgli;

	public UserEngine(TamaGUILogIn tgli){
		this.tgli = tgli;
	}
	
	public UserEngine(){
	}

	//CREAT A USER
	public void createUsers(String userName, String userPassword){
		this.userName=userName;
		connectionMethod("tamaadmin", "java13");
		statementMethod();
		userChecker(userName);


		//THE INJECTION STRING
		//	System.out.println(getGameValue());
		//	System.out.println(createUserId() +  " " + userName + userPassword);
	}
	//LOG IN A USER
	public void userLogIn(String userName, String userPassword){
		this.userName=userName;
		connectionMethod("tamaadmin", "java13");
		statementMethod();
		
		String checkName = "SELECT username, password FROM user WHERE username='" + userName + "';";
		String passWord = "SELECT username='" + userName + "' FROM user WHERE password='" + userPassword + "';";
		selectMethodSingle(passWord);
		
		System.out.println("Out" + selectMethodSingle(passWord));
		
//		clearSelectMethodSingleArray();
	}

	//CHECK IF THE USER IS ALREADY CREATED.
	private void userChecker(String userName){

		String logInChecker = "SELECT username, password FROM user WHERE username='" + userName + "';";
//		selectMethodSingle(logInChecker);
		
		if (selectMethodSingle(logInChecker).equals(userName)){
			tgli.popUpMessage("This name in use");
			System.out.println(getSelectMethodSingle().toString());
		}
		

//		for (int i = 0; i < getSelectMethodSingle().size(); i++) {
//			if (getSelectMethodSingle().get(i).equals(userName)){
//				tgli.popUpMessage("This name in use");
//				break;
//			}
//		}
	}

	/** <3 ANNIKA <3 
	* Metoden som räknar rows och plussar med 1, för att skapa ny
	* id key.
	*/ 	
	//GET INT# COUNT BY WHILE LOOP IN SUPER CLASS
	//MAYBE ANOTHER WAY TO DO IT???
	private int createUserId(){
		selectMethod("SELECT userid FROM user; ");
		int createUserId = rowCount + 1;
		return createUserId;
	}
}
