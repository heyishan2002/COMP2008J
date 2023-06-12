package model.cards.ActionCards;

import model.Game.Game;
import model.cards.*;
import model.player.Player;

import java.util.Iterator;
import java.util.Stack;

public class RentCard extends ActionCard {
    public PropertyColor getColor() {
        return propertyColor;
    }

    public void setColor(PropertyColor propertyColor) {
        this.propertyColor = propertyColor;
    }

    PropertyColor propertyColor;

    PropertyColor propertyColor1 = null;
    PropertyColor propertyColor2 = null;
    public RentCard(int m, PropertyColor c1, PropertyColor c2, Game g) {
        super(m,"RENT CARD",g);
        setMessage("Rent is charged to all players");
        this.propertyColor1 = c1;
        this.propertyColor2 = c2;
    }

    @Override
    public boolean use(Player p) {
            int payNum = ((propertyColor.getRent(p.getPropertyNum(propertyColor)) + p.getColorProperty(propertyColor).rentNum()) * p.isDouble());
            if(payNum == 0){
                return false;
            }
            for (Player player : this.game.getPlayers()) {
                    boolean isRejected = false;
                    if (player.reject(p)) {
                        isRejected = true;
                    }
                    if (!isRejected) {
                        Stack payCards = player.pay(payNum);
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
