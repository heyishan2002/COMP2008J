package model.cards.ActionCards;

import View.MInterface;
import model.Game.Game;
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
    	return false;
    }
}
