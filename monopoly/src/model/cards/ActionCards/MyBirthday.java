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
        for (Player player: this.game.getPlayers()) {
            if(player.getName() == p.getName()){
                continue;
            }
            boolean isRejected = false;
            if (player.reject(p)) {
                isRejected = true;
            }
            if (!isRejected) {
                Stack payCards = player.pay(2);
                if(payCards == null){
                    continue;
                }
                Iterator iterator = payCards.iterator();
                while (iterator.hasNext()) {
                    Object card = iterator.next();
                    if (card instanceof Property) {
                        p.addProperty((Property) card);
                    } else {
                        p.addMoney((Bankable) card);
                    }
                }
            }
        }
        return true;
    }
}
