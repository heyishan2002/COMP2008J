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
    	return false;
    }
}
