package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.Iterator;
import java.util.Stack;

public class ForcedDeal extends ActionCard {
    public ForcedDeal(int money, Game g) {
        super(money, "FORCED TRANSACTION", g);
        setMessage("Trade one of your industries for a given player's industries");
    }

    @Override
    public boolean use(Player p) {
        MInterface mInterface = p.mInterface;
        /**Choose a player, choose one of the property,
         * choose one of your properties to exchange,
         * and if you don't have an industry, you fail to use the card**/
        if(p.getMyProperty().empty()){
            return false;
        }
        //Choose the exchanger
        Player exchanger = p.choosePlayer();
        int hasPropertyNum = exchanger.getHasPropertyNum();
        if(hasPropertyNum == 0) {
            return false;
        }
        if (!exchanger.reject(p)) {

            game.refreshSelected();
            Iterator<PlayerProperty> iterator3 = exchanger.getMyProperty().iterator();
            while (iterator3.hasNext()) {
                PlayerProperty next1 = iterator3.next();
                Stack<Property> p1 = next1.getP();
                for(Property pr:p1){
                    pr.selectable();
                }
            }
            MInterface.ButtonName buttonName2 = mInterface.gameInterface(exchanger.getName(), "please choose a property to get", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

            if(buttonName2 == MInterface.ButtonName.cancel){
                return false;
            }
            Selectable next2 = game.getSelected().iterator().next();
            if(exchanger.isSet(((Property)next2).getColor())){
                return false;
            }
            game.refreshSelected();
            p.addProperty((Property) next2);
            exchanger.removeProperty((Property) next2);
            //Get the property
            game.refreshSelected();
            Iterator<PlayerProperty> iterator2 = p.getMyProperty().iterator();
            while (iterator2.hasNext()) {
                PlayerProperty next = iterator2.next();
                Stack<Property> p1 = next.getP();
                for (Property pr : p1) {
                    pr.selectable();
                }
            }
            MInterface.ButtonName buttonName = mInterface.gameInterface(p.getName(), "please choose a property to give", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

            if (buttonName == MInterface.ButtonName.cancel) {
                return false;
            }
            Selectable next = game.getSelected().iterator().next();
            game.refreshSelected();
            exchanger.addProperty((Property) next);
            p.removeProperty((Property) next);

        }

        return true;
    }
}
