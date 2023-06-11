package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.Stack;

public class Steal extends ActionCard {
    Player embezzler = null;
    public Steal(int money, Game g) {
        super(money, "STEAL",g);
        setMessage("Steal other players' property");
    }

    @Override
    public boolean use(Player p) {
    	return false;
    }
}
