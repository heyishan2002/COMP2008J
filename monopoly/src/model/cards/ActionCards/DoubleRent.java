package model.cards.ActionCards;

import model.cards.ActionCard;
import model.Game.Game;
import model.player.Player;

public class DoubleRent extends ActionCard {
    public DoubleRent(int money, Game g) {
        super(money, "DOUBLE RENT", g);
        setMessage("Double the usage rent");
    }

    @Override
    public boolean use(Player p) {
        p.addAction();
        return true;
    }
}
