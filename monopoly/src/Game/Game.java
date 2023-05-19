package Game;

import cards.GetCard;
import players.Players;

import java.util.Stack;

public class Game {
    Players[] players = new Players[4];
    Stack<GetCard> drawPile = null;

    public Players[] getPlayers() {
        return players;
    }

    public Stack<GetCard> getDrawPile() {
        return drawPile;
    }
}
