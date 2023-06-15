package model.cards.ActionCards;

import View.MInterface;
import control.Game;
import model.cards.*;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
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
        setMessage("Get rent from all players ,available color :" + c1.toString() + " and " + c2.toString());
        this.propertyColor1 = c1;
        this.propertyColor2 = c2;
    }

    @Override
    public boolean use(Player p) {
        MInterface mInterface = game.mInterface;
        p.getColorProperty(propertyColor1).selectable();
        p.getColorProperty(propertyColor2).selectable();
        MInterface.ButtonName buttonName1 = mInterface.gameInterface(p.getName(), "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
        if(buttonName1 == MInterface.ButtonName.cancel){
            return false;
        }
        ArrayList<Selectable> selected1 = game.getSelected();
        if(selected1.isEmpty()){
            return false;
        }
        Iterator<Selectable> iterator1 = selected1.iterator();
        game.refreshSelected();
        PlayerProperty next1 = (PlayerProperty) iterator1.next();
        this.propertyColor = next1.getColor();
        int payNum = ((propertyColor.getRent(p.getPropertyNum(propertyColor)) + p.getColorProperty(propertyColor).rentNum()) * p.isDouble());
        if(payNum == 0){
            return false;
        }
        for (Player player : this.game.getPlayers()) {
            if(player.getName() == p.getName()){
                continue;
            }
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
