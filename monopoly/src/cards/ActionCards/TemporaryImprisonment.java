package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import players.Players;

public class TemporaryImprisonment extends ActionCard {

    public TemporaryImprisonment(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        System.out.println(p.getName() + "uses" + this.getName());
        Players p_ =  this.choosePlayer(this.game,p);
        if(!p_.reject()){
            p_.goToPrison();
        }
    }
}
