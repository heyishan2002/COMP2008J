package model.cards.ActionCards;

import View.MInterface;

import control.Game;
import model.cards.*;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
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
        MInterface mInterface = game.mInterface;
        Iterator<PlayerProperty> iterator2 = p.getMyProperty().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().selectable();
        }
        MInterface.ButtonName buttonName1 = mInterface.gameInterface(p.getName(), "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
        if (buttonName1 == MInterface.ButtonName.cancel) {
            return false;
        }
        ArrayList<Selectable> selected1 = game.getSelected();
        if (selected1.isEmpty()) {
            return false;
        }
        Iterator<Selectable> iterator1 = selected1.iterator();
        game.refreshSelected();
        PlayerProperty next1 = (PlayerProperty) iterator1.next();
        this.propertyColor = next1.getColor();
        int payNum = ((propertyColor.getRent(p.getPropertyNum(propertyColor)) + p.getColorProperty(propertyColor).rentNum()) * p.isDouble());
        tenant = p.choosePlayer();
        if(tenant == null){
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
