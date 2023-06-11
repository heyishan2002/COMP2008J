package model.cards.ActionCards;

import model.cards.ActionCard;
import model.cards.Bankable;
import model.Game.Game;
import model.cards.Property;
import model.player.Player;

import java.util.Iterator;
import java.util.Stack;

public class CollectionOfDebt extends ActionCard {
    public CollectionOfDebt(int money, Game g) {
        super(money, "COLLECTION OF DEBT", g);
        setMessage("Collect $5 million debt from selected players");
    }

    @Override
    public boolean use(Player player) {
       return false;
    }
}
