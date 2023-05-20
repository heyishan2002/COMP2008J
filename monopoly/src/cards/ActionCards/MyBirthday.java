package cards.ActionCards;

import cards.ActionCard;
import cards.Bankable;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Stack;

public class MyBirthday extends ActionCard {
    public MyBirthday(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
        System.out.println(p.getName() + "uses" + this.getName());
        for (Players player: this.game.getPlayers()) {
            boolean isRejected = false;
            if (player.reject()) {
                isRejected = true;
            }
            if (!isRejected) {
                Stack payCards = player.pay(2);
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
        }else{

        }
    }
}
