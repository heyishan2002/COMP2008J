package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import players.Players;

public class DoubleRent extends ActionCard {
    public DoubleRent(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if (!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
        }else {
            p.addMoney(this);
        }
    }
}
