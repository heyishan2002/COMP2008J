package model.cards.ActionCards;

import model.cards.ActionCard;
import control.Game;
import model.player.Player;

public class JustSayNo extends ActionCard {

    public JustSayNo(int money, Game g) {
        super(money, "MAKE AN OBJECTION", g);
        setMessage("Nullify another player's action cards");
    }

    @Override
    public boolean use(Player p) {
        /**A defensive card, used to oppose an action card used by the opponent against the Player,
         *  cannot be actively used,
         *  and is implemented in player.reject () **/
        return false;
    }
}
