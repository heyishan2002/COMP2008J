package control;

import java.lang.String;

import View.GraphicInterface;
import View.MInterface;

public class gmain{
	 public static Game game;
	 public static GraphicInterface gInterface;
	 public static MInterface mInterface;
	 public static void main(String[] args) {
		 mInterface = new MInterface();
		 gInterface = new GraphicInterface();
		 game = new Game(gInterface, mInterface);
		 game.startGame();
	 }
}
