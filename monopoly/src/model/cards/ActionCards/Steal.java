package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.Stack;

public class Steal extends ActionCard {
    Player embezzler = null;
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
        int hasPropertyNum = exchanger.getHasPropertyNum();
        if(hasPropertyNum == 0) {
            return false;
        }
        if(!exchanger.reject(p)){
            game.refreshSelected();
            exchanger.pro.selectable();
            MInterface.ButtonName buttonName2 = mInterface.gameInterface(exchanger.getName(), "please choose a property to get", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

            if(buttonName2 == MInterface.ButtonName.cancel){
                return false;
            }
            Selectable next2 = game.getSelected().iterator().next();
            game.refreshSelected();
            p.addProperty((Property) next2);
            exchanger.removeProperty((Property) next2);
        }
        return true;
    }
}
