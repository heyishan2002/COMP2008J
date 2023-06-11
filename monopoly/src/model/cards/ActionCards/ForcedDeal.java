package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;

public class ForcedDeal extends ActionCard {
    public ForcedDeal(int money, Game g) {
        super(money, "FORCED TRANSACTION", g);
        setMessage("Trade one of your industries for a given player's industries");
    }

    @Override
    public boolean use(Player p) {
    	return false;
    }
}
