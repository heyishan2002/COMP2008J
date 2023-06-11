package model.cards.ActionCards;

import model.cards.ActionCard;
import model.cards.Bankable;
import model.Game.Game;
import model.cards.Property;
import model.player.Player;

import java.util.Iterator;
import java.util.Stack;

public class MyBirthday extends ActionCard {
    public MyBirthday(int money, String name, Game g) {
        super(money,"MY BIRTHDAY", g);
        setMessage("All players pay $2 million to themselves");
    }

    @Override
    public boolean use(Player p) {
    	return false;
    }
}
