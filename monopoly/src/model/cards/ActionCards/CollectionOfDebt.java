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
        /**The user selects a player who pays a $5,000 debt to the user**/
        Player tenant = player.choosePlayer();
        if(tenant == null){
            return false;
        }
        if (!tenant.reject(player)) {
            Stack payCards = tenant.pay(5);
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
        return true;
    }
}
