package cards.ActionCards;

import cards.ActionCard;
import cards.Bankable;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Stack;

public class CollectionOfDebt extends ActionCard {
    public CollectionOfDebt(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players player) {
        if(!this.ifUseAsMoney(player)) {
            System.out.println(player.getName() + "uses" + this.getName());
            Players tenant;
            tenant = this.choosePlayer(game, player);
            if (!tenant.reject()) {

                Stack payCards = player.pay(5);
                Iterator iterator = payCards.iterator();
                while (iterator.hasNext()) {
                    Object card = iterator.next();
                    if (card instanceof Property) {
                        player.addProperty((Property) card);
                    } else {
                        player.addMoney((Bankable) card);
                    }
                }
            }
        }else{
            player.addMoney(this);
        }
    }
}
