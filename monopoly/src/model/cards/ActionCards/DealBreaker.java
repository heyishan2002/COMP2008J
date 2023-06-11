package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.cards.PropertyColor;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class DealBreaker extends ActionCard {
    public DealBreaker(int money, Game g) {
        super(money,"PROPERTY TAKE OVER", g);
        setMessage("Gain a complete set of industries from other players");
    }

    @Override
    public boolean use(Player p) {
    	return false;
    }
}
