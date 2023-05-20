package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import players.Players;

public class MakeAnObjection extends ActionCard {

    public MakeAnObjection(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if (!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
        }else{

        }
    }
}
