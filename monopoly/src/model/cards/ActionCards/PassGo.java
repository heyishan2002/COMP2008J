package model.cards.ActionCards;

import model.cards.ActionCard;
import control.Game;
import model.player.Player;

public class PassGo extends ActionCard {
    public PassGo(int money, Game g) {
        super(money,"PERMIT", g);
        setMessage("Touch two cards");
    }

    @Override
    public boolean use(Player p) {
        p.addCard(game.getDeck().pop());
        p.addCard(game.getDeck().pop());
        return true;
    }
}
