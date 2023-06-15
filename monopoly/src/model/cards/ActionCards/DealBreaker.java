package model.cards.ActionCards;

import View.MInterface;

import model.cards.*;
import control.Game;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DealBreaker extends ActionCard {
    public DealBreaker(int money, Game g) {
        super(money,"PROPERTY TAKE OVER", g);
        setMessage("Gain a complete set of industries from other players");
    }

    @Override
    public boolean use(Player p) {
        Player embezzler = p.choosePlayer();
        if(embezzler == null){
            return false;
        }
        MInterface mInterface = p.mInterface;
        game.refreshSelected();
        if(embezzler.reject(p)){
                return true;
        }
        Iterator<PlayerProperty> iterator2 = embezzler.getMyProperty().iterator();
        while(iterator2.hasNext()){
            iterator2.next().selectable();
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(embezzler.getName(), "Please choose the property to take over", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
        if(buttonName == MInterface.ButtonName.cancel){
            return false;
        }
        ArrayList<Selectable> selected1 = this.game.getSelected();
        if(selected1.isEmpty()){
            return false;
        }
        game.refreshSelected();
        Iterator<Selectable> iterator = selected1.iterator();
        PlayerProperty next1 = (PlayerProperty) iterator.next();
        PropertyColor color = next1.getColor();
        if(!embezzler.isSet(color)){
            return false;
        }
        Stack<Property> propertyStack  = new Stack<>();
        Iterator<Property> iterator1 = next1.getP().iterator();
        House house = next1.house;
        Hotel hotel = next1.hotel;
        while(iterator1.hasNext()){
            Property next = iterator1.next();
            propertyStack.add(next);
            p.addProperty(next);
        }
        Iterator<Property> iterator3 = propertyStack.iterator();
        while(iterator3.hasNext()){
            embezzler.removeProperty(iterator3.next());
        }
        next1.house = null;
        next1.hotel = null;
        PlayerProperty colorProperty = p.getColorProperty(next1.getColor());
        colorProperty.hotel = hotel;
        colorProperty.house = house;
        return true;
    }
}
