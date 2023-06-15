package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Steal extends ActionCard {
    public Steal(int money, Game g) {
        super(money, "STEAL",g);
        setMessage("Steal other players' property");
    }

    @Override
    public boolean use(Player p) {
            /**Select a player, select a set of industries to be placed in their own industry,
             * if the industry has become a set, you need to re-select**/
        MInterface mInterface = game.mInterface;
        Player exchanger = p.choosePlayer();
        if(exchanger == null){
            return false;
        }
        int hasPropertyNum = exchanger.getHasPropertyNum();
        if(hasPropertyNum == 0) {
            return false;
        }
        if(!exchanger.reject(p)){
            game.refreshSelected();
            Iterator<PlayerProperty> iterator2 = exchanger.getMyProperty().iterator();
            while (iterator2.hasNext()) {
                PlayerProperty next = iterator2.next();
                Stack<Property> p1 = next.getP();
                for(Property pr:p1){
                    pr.selectable();
                }
            }
            MInterface.ButtonName buttonName2 = mInterface.gameInterface(p.getName(), "please choose a property to get", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
            if(buttonName2 == MInterface.ButtonName.cancel){
                return false;
            }
            ArrayList<Selectable> selected1 = game.getSelected();
            if(selected1.isEmpty()){
                return false;
            }
            Selectable next2 = selected1.iterator().next();
            if(exchanger.isSet(((Property)next2).getColor())){
                return false;
            }
            game.refreshSelected();
            p.addProperty((Property) next2);
            exchanger.removeProperty((Property) next2);
        }
        return true;
    }
}
