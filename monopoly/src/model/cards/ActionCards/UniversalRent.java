package model.cards.ActionCards;

import model.Game.Game;
import model.cards.*;
import model.player.Player;

import java.util.Iterator;
import java.util.Stack;

public class UniversalRent extends ActionCard {
    PropertyColor propertyColor;
    PropertyColor[] propertyColors = new PropertyColor[]{PropertyColor.Orange, PropertyColor.Yellow, PropertyColor.Black, PropertyColor.Brown, PropertyColor.LightGreen, PropertyColor.LightBlue, PropertyColor.DarkGreen, PropertyColor.RED, PropertyColor.Pink, PropertyColor.DarkBlue};
    Player tenant = null;
    public UniversalRent(int m, Game g){
        super(m,"UNIVERSAL RENT",g);
        setMessage("Charge rent to selected players");
    }

    @Override
    public boolean use(Player p) {
        int payNum = ((propertyColor.getRent(p.getPropertyNum(propertyColor)) + p.getColorProperty(propertyColor).rentNum()) * p.isDouble());
        Player p1 = p.choosePlayer();
        if(p1 == null){
           return false;
        }
        boolean isRejected = false;
        if (p.reject(p)) {
            isRejected = true;
        }
        if (!isRejected) {
            Stack payCards = tenant.pay(payNum);
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
        return true;
    }
}
