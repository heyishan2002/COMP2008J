package model.cards.ActionCards;

import View.MInterface;
import model.cards.ActionCard;
import model.cards.PropertyColor;
import model.Game.Game;
import model.cards.Property;
import model.cards.Selectable;
import model.player.Player;

import java.util.ArrayList;
import java.util.Iterator;

public class DealBreaker extends ActionCard {
    public DealBreaker(int money, Game g) {
        super(money,"PROPERTY TAKE OVER", g);
        setMessage("Gain a complete set of industries from other players");
    }

    @Override
    public boolean use(Player p) {
        Player embezzler = p.choosePlayer();
        MInterface mInterface = p.mInterface;
        game.refreshSelected();
        p.selectable();
        if(embezzler.reject(p)){
                return true;
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(embezzler.getName(), "Please choose the property to take over", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
        if(buttonName == MInterface.ButtonName.cancel){
            return false;
        }
        ArrayList<Selectable> selected1 = this.game.getSelected();
        game.refreshSelected();
        Iterator<Selectable> iterator = selected1.iterator();
        PropertyColor color = null;
        while(iterator.hasNext()){
            Selectable next = iterator.next();
            if(next instanceof Property){
                color = ((Property) next).getColor();
                p.addProperty((Property) next);
            } else if (next instanceof House) {
                p.getColorProperty(color).addHouse((House) next);
            } else if (next instanceof Hotel) {
                p.getColorProperty(color).addHotel((Hotel) next);
            }
        }
        return true;
    }
}
