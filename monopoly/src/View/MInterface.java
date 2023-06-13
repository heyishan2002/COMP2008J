package View;

import model.player.Player;

public class MInterface {
	public enum OperationType{
		ok_cancel,
		card_operation,
		discard_cancel,
		game_over
	}
	public enum ButtonName{
		ok,
		cancel,
		bank,
		action,
		skip,
		discard
	}
	
    public enum SelectionType{
    	single,
    	multiple
    }
	
	private Player p;
	private boolean buttonClicked;
	private ButtonName clickedButton;
	
	public OperationType opType;
	public SelectionType selType;
	
	public String interfaceMsg;
	
	public MInterface() {
		p = null;
	}
	
	public Player getCurrentPlayer() {
		return p;
	}
	
	public void setClickedButton(ButtonName b) {
		clickedButton = b;
	}
	
	public ButtonName getClickedButton() {
		return clickedButton;
	}
	
	public void setButtonClicked() {
		buttonClicked = true;
	}
	
	public ButtonName gameInterface(String pName, String msg, OperationType operationType, SelectionType selectionType) {
		buttonClicked = false;
		opType = operationType;
		selType = selectionType;
		interfaceMsg = new String(msg);
		p = gmain.gInterface.gPanel.getPlayer(pName);
		gmain.gInterface.gPanel.clearStatus();
		
		/*gmain.gInterface.gPanel.gameOver(pName + " " + msg);
		gmain.gInterface.gPanel.repaint();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ButtonName.ok;
		*/
		
		if (opType == OperationType.game_over) {
			gmain.gInterface.gPanel.gameOver(pName + " " + msg);
			gmain.gInterface.gPanel.repaint();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ButtonName.ok;
		}
		
		gmain.gInterface.gPanel.repaint();
		while (buttonClicked == false) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return clickedButton;
		
	}
	
	public void addPlayer(Player p) {
		gmain.gInterface.gPanel.addPlayer(p);
	}
}
