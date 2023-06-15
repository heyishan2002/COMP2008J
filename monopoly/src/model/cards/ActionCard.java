package model.cards;

import control.Game;
import model.player.Player;

public abstract class ActionCard extends Bankable{

    public String mes;
    public Game game;
    public ActionCard(int money, String name, Game g) {
        super(money, name);
        this.game = g;
        this.type = Type.ACTION;
    }

    public int getMoney() {
        return money;
    }

    public abstract boolean use(Player p);

}
