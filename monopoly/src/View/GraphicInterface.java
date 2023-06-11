package View;

import javax.swing.*;
import java.awt.*;

public class GraphicInterface {
	private JFrame mainFrame;
	public GamePanel gPanel;
    public GraphicInterface() {
    	mainFrame = new JFrame("Monopoly Deal");
    	PlayerWindow pw = new PlayerWindow(null, 0, null);
    	mainFrame.setSize(pw.playerWindowWidth * 2 + 16, pw.playerWindowHeight * 2 + PlayerWindow.MsgAreaHeight + 64);
    	mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	gPanel = new GamePanel();
    	mainFrame.setVisible(true);
    	GameListener ll = new GameListener(gPanel);
    	gPanel.addMouseListener(ll);
    	
    	mainFrame.add(gPanel);
    }   
}
