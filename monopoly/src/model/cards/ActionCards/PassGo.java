package model.cards.ActionCards;

import model.cards.ActionCard;
import model.Game.Game;
import model.player.Player;

public class PassGo extends ActionCard {
    public PassGo(int money, Game g) {
        super(money,"PERMIT", g);
        setMessage("Touch two cards");
    }

    @Override
    public boolean use(Player p) {
    	return false;
    }
}
