package model.cards.ActionCards;

import View.MInterface;
import control.Game;
import model.cards.ActionCard;
import model.cards.PropertyColor;
import model.cards.Selectable;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
import java.util.Iterator;

public class House extends ActionCard {

    public int getHouseMoney() {
        return houseMoney;
    }

    int houseMoney;

    public House(int money, int houseMoney, Game g) {
        super(money, "HOUSE",g);
        this.houseMoney = houseMoney;
        setMessage("Add a house");
    }

    @Override
    public boolean use(Player p) {
        MInterface mInterface = game.mInterface;
        game.refreshSelected();
        Iterator<PlayerProperty> iterator2 = p.getMyProperty().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().selectable();
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(p.getName(), "Please choose the property to add a house", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
        if(buttonName == MInterface.ButtonName.cancel){
            return false;
        }
        ArrayList<Selectable> selected1 = this.game.getSelected();
        if(selected1.isEmpty()){
            return false;
        }
        game.refreshSelected();
        Iterator<Selectable> iterator = selected1.iterator();
        PlayerProperty next = (PlayerProperty) iterator.next();
        if(next.getColor() == PropertyColor.Black || next.getColor() == PropertyColor.LightGreen ){
            return false;
        }else{
            next.addHouse(this);
        }
        return true;
    }
}
