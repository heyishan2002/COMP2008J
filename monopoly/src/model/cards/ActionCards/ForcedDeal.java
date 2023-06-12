package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;

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
            //Get the property
            game.refreshSelected();
            p.pro.selectable();
            MInterface.ButtonName buttonName = mInterface.gameInterface(p.getName(), "please choose a property to give", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

            if(buttonName == MInterface.ButtonName.cancel){
                return false;
            }
            Selectable next = game.getSelected().iterator().next();
            game.refreshSelected();
            exchanger.addProperty((Property) next);
            p.removeProperty((Property) next);

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
