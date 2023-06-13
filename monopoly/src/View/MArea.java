package View;

import javax.swing.*;
import java.awt.*;

public class MArea {
	private GamePanel p;
	
	public int x, y;
	public int width, height;
	
	private Color color;
	String name;
	
	public MArea(int ix1, int ix2, int w, int h, Color c, String name) {
		x = ix1;
		y = ix2;
		width = w;
		height = h;
		color = c;
		this.name = new String(name);
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void drawArea(Graphics2D gc, boolean fill) {
		if (fill) {
			gc.setColor(Color.white);
			gc.fillRect(x, y, width, height);
		}
		gc.setColor(color);
		gc.fillRect(x + 20, y, width - 20, 10);
		
		gc.setColor(Color.GRAY);
		gc.setStroke(new BasicStroke(PlayerWindow.AreaBorderWidth));
		gc.drawRect(x, y, width, height);
		gc.setColor(Color.black);
		Font f = new Font("Courier", Font.BOLD, PlayerWindow.CardDist - 1);
		gc.setFont(f);
		gc.drawString(name, x + 3 , y + PlayerWindow.CardDist - 1);
	}
	
	public void hideArea(Graphics2D gc) {
		gc.setColor(Color.lightGray);
		gc.fillRect(x, y, width, height);
		
		gc.setColor(Color.GRAY);
		gc.setStroke(new BasicStroke(PlayerWindow.AreaBorderWidth));
		gc.drawRect(x, y, width, height);
		gc.setColor(Color.black);
		Font f = new Font("Courier", Font.BOLD, PlayerWindow.CardDist - 1);
		gc.setFont(f);
		gc.drawString(name, x + 3 , y + PlayerWindow.CardDist - 1);
	}
	
	public boolean inArea(int ix, int iy) {
		if (ix >= x && ix <= x + width && iy >= y && iy <= y + height)
			return true;
		else
			return false;
	}
}
