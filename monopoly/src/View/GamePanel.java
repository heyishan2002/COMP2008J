package View;

import javax.swing.*;

import control.gmain;
import model.cards.Card;
import model.cards.Selectable;
import model.player.Player;

import java.awt.*;

public class GamePanel extends JPanel {

	public PlayerWindow[] playerWindow; 
	public Selectable lastSelected;
	private boolean gameOver;
	private String gameOverMsg;
	
	public GamePanel() {
		playerWindow= new PlayerWindow[4];
		for (int i = 0; i < 4; i ++) {
			playerWindow[i] = new PlayerWindow(this, i, null);
		}
		lastSelected = null;
		gameOver = false;
		gameOverMsg = null;
	}
	
	public void addPlayer(Player p) {
		int i;
		for (i = 0; i < 4; i ++) {
			if (playerWindow[i].player == null)
				break;
		}
		if (i >= 4)
			return;
		playerWindow[i].player = p;
		if  (i == 3)
			for (i = 0; i < 4; i ++) 
				playerWindow[i].setPropertyArea();
	}
	
	public Player getPlayer(String name) {
		int i;
		for (i = 0; i < 4; i ++) {
			if (playerWindow[i].player.getName() == name)
				return playerWindow[i].player;
		}
		return null;
	}
	
	public void setBC(Color c) {

	}
	
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (gameOver) {
			showGameOver(g2);
			return;
		}

		for (int i = 0; i < 4; i ++) {
			playerWindow[i].drawWindowBackgroud(g2);
			playerWindow[i].drawCards(g2);
		}
		
		int y;
		Card card;
		
		g2.setColor(Color.white);
		g2.fillRect(0, playerWindow[0].playerWindowHeight * 2 + PlayerWindow.AreaBorderWidth / 2, 
				playerWindow[0].playerWindowWidth * 2, playerWindow[0].playerWindowHeight - PlayerWindow.AreaBorderWidth / 2);
		y = playerWindow[0].playerWindowHeight * 2 + 16;
		Font f = new Font("Courier", Font.PLAIN, 16);
		g2.setFont(f);
		g2.setColor(Color.magenta);
		if (lastSelected != null && lastSelected.w == PlayerWindow.CardWidth) {
			card = (Card)lastSelected;
			g2.drawString("Card Description: " + card.getMessage(), 10 , y);
		}
		y += 22;
		if (gmain.mInterface.interfaceMsg != null)
			g2.drawString(gmain.mInterface.getCurrentPlayer().getName() + " : " + gmain.mInterface.interfaceMsg, 10 , y);
	}
	
	public void clearStatus() {
		for (int i = 0; i < 4; i ++) {
			playerWindow[i].clearStatus();
		}
	}
	
	public void showMsg() {
		
	}
	
	public void gameOver(String msg) {
		gameOverMsg = new String(msg);
		gameOver = true;
	}
	
	public void showGameOver(Graphics2D gc) {
		int h = 50;
		int x = playerWindow[0].playerWindowWidth - gameOverMsg.length() * h / 6 - 50;
		int y = playerWindow[0].playerWindowHeight - h / 2;
		int w = gameOverMsg.length() * h / 3 + 100;

		gc.setColor(Color.lightGray);
		gc.fillRoundRect(x, y, w, h, w / 8, h / 8);
		gc.setStroke(new BasicStroke(5));
		gc.setColor(Color.black);
		gc.drawRoundRect(x , y, w, h, w / 8, h / 8);
		
		Font f = new Font("Courier", Font.PLAIN, h / 2 );
		gc.setFont(f);	
		gc.setColor(Color.magenta);
		gc.drawString(gameOverMsg, x + 100 , y + h * 2 / 3);
	}
	
	
}
