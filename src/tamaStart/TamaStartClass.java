package tamaStart;

/** Just a start Class
 * 
 *  Made by Arild Oderman, Java13
 *  *  Yes even the png. PAINT proh!
 * 
 * 
 * 
 * NOTE MAKE THE GAME GUI START...
 * 
 * GAME ENGINE IS THE START CLASS,
 * 
 * TamaGUILogInUncher
 * >tamaguicreateuser 
 * 
 * > GAME GUI START LUNCHER,
 * then
 * > THE GAME GUI,
 * 
 */

import tamaSystem.GameEngine;

public class TamaStartClass {

	public static void main(String[] args) {
		GameEngine ge = new GameEngine();
		ge.setGe(ge);
		ge.GameGuiStart();

		//		TamaGUIStart tgs = new TamaGUIStart();
		//		tgs.TamaStartGUIStarter();


		//		ge.StartLogIn();


	}
}



