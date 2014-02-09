package tamaDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextArea;

import tamaSystem.GameEngine;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/*MySQL Engine
 * 
 * This class will work with MySQL.
 * It will get DB and maybe get DB too.
 * 
 * MUST START SOMEWERE WHERE OTHER CLASS CAN GET GAMEVALUES?
 * 
 * 
 * CHECKA INDEX SYTAX/SYSTEM:
 * FOR FASTER SEARCH AND FINIDNG VALUES.
 * 
 * 
 * ADD CLOSERS!!!
 */

public class MySQLEngine {
	//ON TOP INCASE OF UPDATE OR CHANGE PATH.
	private final String SERVER_NAME = "localhost";
	private final String DATABASE_NAME = "dbprojecttama";
	private final int PORT = 3306;

	protected MysqlDataSource ds;
	protected Connection con = null;
	protected Statement queryCaller = null;
	protected ResultSet result = null;
	protected java.sql.PreparedStatement psStream = null;
	protected String inString = null;
	protected int nCols;

	protected int rowCount;
	protected GameEngine ge;
	protected TamaGUILogIn tgli;
	protected ArrayList <Integer> gameValues = new ArrayList<Integer>();
	protected String tmpString;

	private ArrayList <String> selectMethodSingle;
	public void clearSelectMethodSingleArray(){
		this.selectMethodSingle.clear();
	}
	public ArrayList<String> getSelectMethodSingle() {
		return selectMethodSingle;
	}

	public void getMySQLDB(String user, String password){
		connectionMethod(user, password);
		selectMethod("SELECT * FROM gamevalues;");
		getGameValue();
	}

	public MySQLEngine(GameEngine ge, TamaGUILogIn tgli){
		this.tgli = tgli;
		this.ge = ge;
	}

	public MySQLEngine(){
	}

	protected void connectionMethod(String user, String password){
		ds = new MysqlDataSource(); 
		ds.setServerName(SERVER_NAME);
		ds.setPort(PORT);
		ds.setDatabaseName(DATABASE_NAME);

		try {
			//DO NOT SHOW PASSWORD CODE IN YOUR CLIENT
			this.con = ds.getConnection(user, password); //try to connect to ds. With user and password.
		} catch (SQLException e) {
			System.out.println("-----ERROR: Could not connect!-----"); //Good to make a syso, just to check where the problem is.
			return;
		}		
		System.out.println("*****Connection succsessfull!*****"); //Good to make a syso that tells that its online.
	}

	protected void statementMethod(){
		try {
			queryCaller = con.createStatement();
		} catch (SQLException e) {
			try {
				con.close(); //close connection
			} catch (SQLException e1) {
			}
			System.out.println("-----STATETMENT ERROR!" + e.getMessage());
		}
		System.out.println("*****Statement Succsessfull!*****");
	}

	//NOT FULLY WORKING YET
	protected void preparedStatementMethod(String psString, String statementString){
		try {
			psStream = con.prepareStatement(psString);
			psStream.setString(1, statementString);

			psStream.executeQuery();
		} catch (SQLException e) {
			System.out.println("----PreparedStatement, ERROR! ----");
		}
	}

	//INSERT, ONE
	private void insertMethod(){
		int affectedRows = 0;
		try {
			affectedRows = queryCaller.executeUpdate("INSERT INTO actor VALUES('201', 'IVAN', 'DRAGO', '2006-02-15 04:34:33')");
		} catch (SQLException e) {
			System.out.println("-----INSERT ERROR-----" + e.getMessage());
		}
		System.out.println("*****INSERT SUCCSESSFULL*****");
		System.out.println("Adected rows: " + affectedRows);
	}

	//INSERT, MORE

	//Select and get information, Need path to column.
	protected void selectMethod(String querys){
		JTextArea tmpJT = null;
		try {
			//SELECT
			result = queryCaller.executeQuery(querys);
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			this.nCols = resultInfo.getColumnCount();

			for (int i = 1; i < nCols; i++) {
				System.out.println(resultInfo.getColumnLabel(i) + " ");

				//TEST
				tmpJT.append(resultInfo.getColumnLabel(i) + " ");
				tgli.setTextLogInInformation(tmpJT);
			}
			while(result.next()){
				//GET RESULT BY COLUM NAME
				//				System.out.println(result.getString("first_name"));	
				//				inString += " " + result.getString("first_name"); 

				// DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				//GET ALL RESULT		
				//				for (int i = 1; i < nCols; i++) {
				//					System.out.println(result.getString(i) + " : OUT PUT ");
				//				}
				String tamaname = result.getString("totalpoints");
				String deathBy = result.getString("deathBy");


				tmpJT.append(tamaname);
				tgli.setTextLogInInformation(tmpJT);
				tmpJT.append(deathBy);
				tgli.setTextLogInInformation(tmpJT);

				System.out.println();
				rowCount++;
			}

		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!-----" + e.getMessage());
		}
		System.out.println("*****QueryCaller succsess!*****");
	}

	//select method type 2 Returns a String.
	protected String selectMethodSingle(String querys){
		tmpString = "";
		//		selectMethodSingle = new ArrayList<String>();
		try {
			//SELECT
			result = queryCaller.executeQuery(querys);
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			this.nCols = resultInfo.getColumnCount();

			while(result.next()){
				for (int i = 1; i < nCols; i++) {
					//					selectMethodSingle.add(result.getString(i));

					tmpString += result.getString(i);
				}
			}
		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!-----" + e.getMessage());
		}
		System.out.println("*****QueryCaller succsess!*****");
		//		tmpString = selectMethodSingle.toString();
		//		
		//		System.out.println(tmpString + " STRING");
		//		System.out.println(selectMethodSingle + " array");

		return tmpString;
	}

	public ArrayList<Integer> getGameValue(){
		try {
			//SELECT
			result = queryCaller.executeQuery("SELECT * FROM gamevalues;");
			result.beforeFirst();
			ResultSetMetaData resultInfo = result.getMetaData();
			int nCols = resultInfo.getColumnCount();

			for (int i = 1; i < nCols; i++) {
				System.out.println(resultInfo.getColumnLabel(i) + " ");
			}

			while(result.next()){
				//SELFNOTE DATA BAS STUFF always starts at 1, not 0 like in normal JAVA.
				for (int i = 1; i < nCols; i++) {
					int	x = 0;
					System.out.println(result.getInt(i) + " ");
					gameValues.add(x, result.getInt(i));
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("-----QueryCaller ERROR!-----" + e.getMessage()); //TESTER
		}
		System.out.println("*****QueryCaller succsess!*****"); //TESTER
		return gameValues;
	}

	public void getScores(){
		selectMethod("SELECT totalpoints, tamaname, deathby FROM scores ORDER BY totalpoints desc limit 10;");
		//		selectMethod("SELECT * FROM scores;");	

	}

	//RESULT SET METHOD, get information
	//DO SOMETHING WITH IT TO UPDATE JTEXTAREA
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			// e.g. resultSet.getSTring(2);
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summary: " + summary);
			System.out.println("Comment: " + comment);
		}
	}



	public void closeEverything(){
		//CLOSE EVERYTHING
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
