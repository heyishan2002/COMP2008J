package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import players.Players;

public class Permit extends ActionCard {
    public Permit(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if (!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
            p.addCard(game.getDrawPile().pop());
            p.addCard(game.getDrawPile().pop());
        }
    }
}
