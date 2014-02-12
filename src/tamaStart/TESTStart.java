package tamaStart;

import tamaDB.TamaDBEngine;
import tamaSystem.GameEngine;



public class TESTStart {

	public static void main(String[] args) {
		GameEngine ge = new GameEngine();
		ge.setGe(ge);
		TamaDBEngine tdbe = new TamaDBEngine(ge);
		ge.setTdbe(tdbe);
		
		tdbe.StartLogIn();
		
		
		//TEST
		ge.GameGuiStart();
	}
}
