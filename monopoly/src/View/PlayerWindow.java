package View;

import java.awt.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import javax.swing.*;

import model.cards.Bankable;
import model.cards.Card;
import model.cards.Property;
import model.cards.Selectable;
import model.cards.Type;
import model.player.Player;
import model.player.PlayerProperty;

public class PlayerWindow {
	public static final int CardWidth = 40;
	public static final int CardHeight = 47;
	public static final int MCardNumInRow = 3;
	public static final int MCardNumInCol = 4;
	public static final int CardDist = 8;
	public static final int AreaBorderWidth = 2;
	public static final int MsgAreaHeight = 20;
	public static final int NameAreaHeight = 5;
	
	public static final Color BackGroundColor = Color.WHITE;
	
	public int playerWindowHeight, playerWindowWidth;
	public int ix, iy;
	
	private GamePanel p;
	private int playerNo;
	public Player player;
	
	private MArea msgArea, bankArea, hcArea, buttonArea;
	private MArea[] propertyArea;
	
	public MButton buttonBank, buttonAction, buttonSkip, buttonOK, buttonDiscard, buttonCancel;
	
	public PlayerWindow(GamePanel panel, int pN, Player player) {
		p = panel;
		playerNo = pN;
		this.player = player;
		
		int propertyAreaHeight = CardDist * 2 + (int)(CardHeight * 2.33);
		
		playerWindowHeight = NameAreaHeight + CardDist * 2;
        playerWindowHeight += propertyAreaHeight * 2;
		playerWindowHeight += CardHeight + CardDist * 2;
		playerWindowHeight += MsgAreaHeight + CardDist * 2;
				
		playerWindowWidth = (CardDist + CardWidth) * MCardNumInRow;
		playerWindowWidth += (CardWidth + 2* CardDist) * 5;
		
		switch (pN) {
		case 0:
			ix = 0;
			iy = 0;
			break;
		case 1:
			ix = playerWindowWidth;
			iy = 0;
			break;
		case 2:
			ix = 0;
			iy = playerWindowHeight;
			break;
		case 3:
			ix = playerWindowWidth;
			iy = playerWindowHeight;
		}
		
		msgArea = new MArea(ix, iy, playerWindowWidth, NameAreaHeight + CardDist * 2, Color.lightGray, " ");
		bankArea = new MArea(ix, iy + msgArea.height, (CardDist + CardWidth) * MCardNumInRow, propertyAreaHeight * 2, new Color(100,128,50), "Bank");
		hcArea = new MArea(ix, iy + msgArea.height + bankArea.height, playerWindowWidth, CardHeight + CardDist * 2, BackGroundColor, "HandCards");
		buttonArea = new MArea(ix, iy + msgArea.height + bankArea.height + hcArea.height, playerWindowWidth, MsgAreaHeight + CardDist * 2, BackGroundColor, " ");
		
		int x, y;
		
		propertyArea = new MArea[10];
		for (int i = 0; i < 10; i ++) {
			if (i < 5)
				y = iy + msgArea.height;
			else
				y = iy + msgArea.height + propertyAreaHeight;
			x = ix + (CardDist + CardWidth) * MCardNumInRow + (CardWidth + 2* CardDist) * (i % 5);
			
			propertyArea[i] = new MArea(x, y, CardWidth + 2* CardDist, propertyAreaHeight, Color.white, "Property");
			
		}
		
		y = iy + msgArea.height + propertyAreaHeight * 2 + CardHeight + CardDist * 3;
		x = ix + MButton.buttonWidth;
		buttonBank = new MButton(" bank", MButton.ButtonType.bank, x, y);
		x += MButton.buttonWidth;
		buttonOK = new MButton(" ok", MButton.ButtonType.ok, x , y);
		buttonDiscard = new MButton("discard", MButton.ButtonType.discard, x , y);
		x += MButton.buttonWidth;
		buttonAction = new MButton("action", MButton.ButtonType.action, x , y);
		x += MButton.buttonWidth;
		buttonCancel = new MButton("cancel", MButton.ButtonType.cancel, x , y);
		x += MButton.buttonWidth;
		buttonSkip = new MButton(" skip", MButton.ButtonType.skip, x , y);
				
	}
	
	public void drawWindowBackgroud(Graphics2D gc) {
		if (player != gmain.mInterface.getCurrentPlayer())
			hcArea.setColor(Color.lightGray);
		else
			hcArea.setColor(BackGroundColor);
		
		msgArea.drawArea(gc);
		bankArea.drawArea(gc);
		hcArea.drawArea(gc);
		buttonArea.drawArea(gc);
		
		for (int i = 0; i < 10; i++)
			propertyArea[i].drawArea(gc);
		
		gc.setStroke(new BasicStroke(AreaBorderWidth));
		gc.setColor(Color.black);
		gc.drawRect(ix, iy, playerWindowWidth , playerWindowHeight);
		
		
		Font f = new Font("Courier", Font.BOLD, 10);
		gc.setFont(f);
		
		gc.setColor(Color.MAGENTA);
        gc.drawString(player.getName(), ix + CardDist , iy + CardDist + 6);
        
        if (gmain.mInterface.getCurrentPlayer() == player){
        	switch (gmain.mInterface.opType) {
        		case ok_cancel:
        			buttonOK.drawMButton(gc);
        			buttonCancel.drawMButton(gc);
        			break;
        		case card_operation:
        			buttonBank.drawMButton(gc);
        	        buttonAction.drawMButton(gc);
        	        buttonSkip.drawMButton(gc);
        	        break;
        		case discard_cancel:
        			buttonDiscard.drawMButton(gc);
        			buttonCancel.drawMButton(gc);
        			break;
        		default:
        			buttonDiscard.drawMButton(gc);
        	}
        }
        
	}
	
	public void clearStatus() {
		
		buttonOK.setClicked(false);
		buttonOK.setPressed(false);
		buttonCancel.setClicked(false);
		buttonCancel.setPressed(false);
		buttonBank.setClicked(false);
		buttonBank.setPressed(false);
		buttonAction.setClicked(false);
		buttonAction.setPressed(false);
		buttonDiscard.setClicked(false);
		buttonDiscard.setPressed(false);
	}
	
	public void drawCards(Graphics2D gc) {
		ArrayList<Stack> s1 = player.getCards();
		
		Stack<Bankable> bankS = s1.get(0);
		Stack<PlayerProperty> propS = s1.get(1);
		Stack<Card> hcS = s1.get(2);
		
		int i = 0, x, y;
		Bankable bc;
		Iterator<Bankable> iterator1 = bankS.iterator();
        while(iterator1.hasNext()){
            bc = iterator1.next();
            x = bankArea.x + CardDist + (int)(i / MCardNumInCol) * (CardWidth + CardDist);
            y = bankArea.y + CardDist + (i % MCardNumInCol) * (CardHeight + CardDist);
            bc.setArea(x, y, CardWidth, CardHeight);
            i++;
            drawCard(gc, bc);
        }
        
        int j;
        i = 0;
        PlayerProperty ppt;
        Iterator<PlayerProperty> iterator2 = propS.iterator();
        while(iterator2.hasNext()){
            ppt = iterator2.next();
           
            Iterator<Property> iterator3 = ppt.getP().iterator();
            Property pt = null;
            x = y = 0;
            j = 0;
            while(iterator3.hasNext()){
                pt = iterator3.next();
               
                x = propertyArea[i].x + CardDist;
                y = propertyArea[i].y + CardDist + (int)(CardHeight * 0.33) * j;
                pt.setArea(x, y, CardWidth, (int)(CardHeight * 0.33));
                j++;
                drawCard(gc, pt);
            }
            if (j > 0) {
            	pt.setArea(x, y, CardWidth, CardHeight);
            	drawCard(gc, pt);
            }
            i++;
        }
        
        if (player != gmain.mInterface.getCurrentPlayer())
        	return;
        
        i = 0;
		Card hc;
		Iterator<Card> iterator4 = hcS.iterator();
        while(iterator4.hasNext()){
            hc = iterator4.next();
            x = hcArea.x + CardDist + (CardWidth + CardDist) * i;
            y = hcArea.y + CardDist;
            hc.setArea(x, y, CardWidth, CardHeight);
            i++;
            drawCard(gc, hc);
        }
	}
	
	Selectable setClicked(int x, int y) {

		if (player.isSelectable) {
			if (x >= ix && x <= ix + playerWindowWidth && y >= iy && y <= iy + playerWindowHeight) {
				player.setSelected(true);
				return player;
			}
		}
		
		if (player.bank.isSelectable) {
			if (bankArea.inArea(x, y)){
				player.setSelected(true);
				return player.bank;
			}
		}
		
		ArrayList<Stack> s1 = player.getCards();

		int i = 0;
		Stack bankS = s1.get(0);
		Stack propS = s1.get(1);
		Stack hcS = s1.get(2);

		PlayerProperty ppt;
		Iterator<PlayerProperty> iterator = propS.iterator();
	    while(iterator.hasNext()){
	            ppt = iterator.next();
	            if (ppt.isSelectable) {
	            	if (propertyArea[i].inArea(x, y)){
	    				ppt.setSelected(true);
	    				return ppt;
	    			}
	            }
	            i++;
	    }
	    
		Bankable bc;
		Iterator<Bankable> iterator1 = bankS.iterator();
        while(iterator1.hasNext()){
            bc = iterator1.next();
            if (bc.isSelectable && bc.inArea(x, y)) {
            	bc.setSelected(true);
            	return bc;
            }
        }

        Iterator<PlayerProperty> iterator2 = propS.iterator();
        while(iterator2.hasNext()){
            ppt = iterator2.next();
           
            Iterator<Property> iterator3 = ppt.getP().iterator();
            Property pt;
            while(iterator3.hasNext()){
                pt = iterator3.next();
                if (pt.isSelectable && pt.inArea(x,  y)){
                	pt.setSelected(true);
                	return pt;
                }
            }
        }
        
        if (player != gmain.mInterface.getCurrentPlayer())
        	return null;

		Card hc;
		Iterator<Card> iterator4 = hcS.iterator();
        while(iterator4.hasNext()){
            hc = iterator4.next();
            if (hc.isSelectable && hc.inArea(x, y)){
            	hc.setSelected(true);
            	return hc;
            }
        }
        
        return null;
	}
	
	void drawCard(Graphics2D gc, Card card) {

		Property p;
		Color fontColor;
		
		gc.setColor(Color.white);
		fontColor = Color.black;
		if (card.getType() == Type.PROPERTY) {
			p = (Property)card;
			switch (p.getColor()) {
				case Brown:
					gc.setColor(Color.magenta);
					break;
				case LightBlue:
					gc.setColor(Color.blue);
					break;
				case Pink:
					gc.setColor(Color.pink);
					break;
				case Orange:
					gc.setColor(Color.orange);
					break;
				case RED:
					gc.setColor(Color.red);
					break;
				case Yellow:
					gc.setColor(Color.yellow);
					break;
				case DarkGreen:
					gc.setColor(new Color(0, 128, 0));
					break;
				case DarkBlue:
					gc.setColor(new Color(0, 0, 128));
					break;
				case Black:
					gc.setColor(Color.black);
					fontColor = Color.white;
					break;
				case LightGreen:
					gc.setColor(Color.green);
					break;
			}
		}
		gc.fillRect(card.x, card.y, card.w, card.h);
		Font f = new Font("Courier", Font.BOLD, 6);
		gc.setFont(f);
		gc.setColor(fontColor);
		String str;
		str = String.format("$%d   %s", card.getMoney(), card.getName());
		//str = "CARD";
		gc.drawString(str, card.x + 2 , card.y + 10);
		/*
		switch (card.getType()) {
			case MONEY:
				System.out.println("money");
				break;
			case PROPERTY:
				System.out.println("money");
				break;
		}
		*/
		if (card.selected)
			gc.setStroke(new BasicStroke(AreaBorderWidth));
		else
			gc.setStroke(new BasicStroke(1));
		gc.setColor(Color.black);
		gc.drawRect(card.x, card.y, card.w, card.h);
		
	}
	
}
