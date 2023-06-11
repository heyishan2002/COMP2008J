package model.cards.ActionCards;

import View.MInterface;
import model.Game.Game;
import model.cards.ActionCard;
import model.cards.PropertyColor;
import model.cards.Selectable;
import model.cards.Type;
import model.player.Player;
import model.player.PlayerProperty;

import java.util.ArrayList;
import java.util.Iterator;

public class Hotel extends ActionCard {
    int houseRent;

    public int getHotelMoney(){
        return houseRent;
    }
    public Hotel(int money, int houseRent, Game g) {
        super(money, "HOTEL", g);
        this.houseRent = houseRent;
        setMessage("Add a hotel");
    }

    @Override
    public boolean use(Player p) {
        return false;
    }
}
