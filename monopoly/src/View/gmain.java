package View;

import java.lang.String;

import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Game.Game;
import model.player.Player;

import java.awt.Graphics;
import java.awt.event.*;

public class gmain{
	 static Game game;
	 static GraphicInterface gInterface;
	 static MInterface mInterface;
	 public static void main(String[] args) {
		 mInterface = new MInterface();
		 gInterface = new GraphicInterface();
		 game = new Game(gInterface, mInterface);
		 game.startGame();
	 }
}
