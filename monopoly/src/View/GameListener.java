package View;

import java.awt.Color;
import java.awt.event.*;

import model.cards.Selectable;

public class GameListener implements MouseListener{
	GamePanel panel;
	public GameListener(GamePanel p) {
		panel = p;
	}
	
	private enum SetType{
		clicked,
		pressed
	}
	
	private boolean postionAndSet(MouseEvent e, SetType st) {
		int pn;
		
		for (pn = 0; pn < 4; pn++) {
			if (gmain.gInterface.gPanel.playerWindow[pn].player == gmain.mInterface.getCurrentPlayer())
				break;
		}
		if (pn >= 4)
			return false;
		
		PlayerWindow pw = gmain.gInterface.gPanel.playerWindow[pn];
		switch (gmain.game.mInterface.opType) {
			case ok_cancel:
				if (pw.buttonOK.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						pw.buttonOK.setClicked(true);
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.ok);
					}
					else
						pw.buttonOK.setPressed(true);
					return true;
				}
				if (pw.buttonCancel.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						pw.buttonCancel.setClicked(true);
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.cancel);
					}
					else
						pw.buttonCancel.setPressed(true);
					return true;
				}
				break;
			case card_operation:
				if (pw.buttonBank.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.bank);
						pw.buttonBank.setClicked(true);
					}
					else
						pw.buttonBank.setPressed(true);
					return true;
				}
				if (pw.buttonAction.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.action);
						pw.buttonAction.setClicked(true);
					}
					else
						pw.buttonAction.setPressed(true);
					return true;
				}
				if (pw.buttonSkip.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.skip);
						pw.buttonSkip.setClicked(true);
					}
					else
						pw.buttonSkip.setPressed(true);
					return true;
				}
				break;
			case discard_cancel:
				if (pw.buttonDiscard.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.discard);
						pw.buttonDiscard.setClicked(true);
					}
					else
						pw.buttonDiscard.setPressed(true);
					return true;
				}
				if (pw.buttonCancel.clicked(e.getX(), e.getY())) {
					if (st == SetType.clicked) {
						gmain.game.mInterface.setClickedButton(MInterface.ButtonName.cancel);
						pw.buttonCancel.setClicked(true);
					}
					else
						pw.buttonCancel.setPressed(true);
					return true;
				}
				break;
		}
		return false;
		
	}
	
	public void mouseClicked(MouseEvent e) {
		if (postionAndSet(e, SetType.clicked) == true) {
			panel.repaint();
			gmain.game.mInterface.setButtonClicked();
			return;
		}
		
		Selectable selected;
		
		for (int i = 0; i < 4; i ++) {
			selected = gmain.gInterface.gPanel.playerWindow[i].setClicked(e.getX(), e.getY());
			if (selected != null) {
				if (gmain.mInterface.selType == MInterface.SelectionType.single) {
					if (gmain.gInterface.gPanel.lastSelected != null)
						gmain.gInterface.gPanel.lastSelected.setSelected(false);
				}
				gmain.gInterface.gPanel.lastSelected = selected;
				panel.repaint();
				System.out.println("repaint");
				break;
			}
		}
	}
	public void mousePressed(MouseEvent e) {
		if (postionAndSet(e, SetType.pressed) == true)
			panel.repaint();
	}
	public void mouseReleased(MouseEvent e) {
		
	}
	public void mouseEntered(MouseEvent e) {
		
	}
	public void mouseExited(MouseEvent e) {
		
	}
}
