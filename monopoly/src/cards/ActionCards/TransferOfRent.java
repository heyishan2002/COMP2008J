package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import players.Players;

public class TransferOfRent extends ActionCard {
    public TransferOfRent(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        System.out.println(p.getName() + "uses" + this.getName());
    }
}
