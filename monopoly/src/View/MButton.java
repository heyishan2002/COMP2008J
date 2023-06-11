package View;

import javax.swing.*;
import java.awt.*;

public class MButton {
	public static final int buttonWidth = 46;
	public static final int buttonHeight = 20;
	
	public enum ButtonType{
			ok,
			cancel,
			bank,
			action,
			skip,
			discard
	}
	
	private GamePanel p;
	
	public boolean buttonClicked, buttonPressed;
	
	private ButtonType type;
	private int x, y;
	private String str;
	public MButton(String s, ButtonType t, int ix, int iy) {
		str = new String(s);
		type = t;
		x = ix;
		y = iy;
		buttonClicked = false;
		buttonPressed = false;
	}
	
	public void drawMButton(Graphics2D gc){
		gc.setColor(Color.black);
		gc.drawRoundRect(x, y, buttonWidth, buttonHeight, buttonWidth / 4, buttonHeight / 4);
		if (buttonClicked)
			gc.setColor(Color.GRAY);
		else if (buttonPressed)
			gc.setColor(Color.BLUE);
		else
			gc.setColor(Color.GRAY);
		gc.fillRoundRect(x, y, buttonWidth, buttonHeight, buttonWidth / 4, buttonHeight / 4);
		Font f = new Font("Courier", Font.PLAIN, 12);
		gc.setFont(f);	
		gc.setColor(Color.BLACK);
		gc.drawString(str, x + 6 , y + 13);
	}
	
	public boolean clicked(int ix, int iy) {
		if (ix >= x && ix <= x + buttonWidth && iy >= y && iy <= y + buttonHeight)
			return true;
		else
			return false;
	}
	
	public void setClicked(boolean c) {
		buttonClicked = c;
	}
	
	public void setPressed(boolean p) {
		buttonPressed = p;
	}
	
}
