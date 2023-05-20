package cards.ActionCards;

import Game.Game;
import cards.*;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;

public class OverSpeed extends ActionCard {
    Scanner scanner = new Scanner(System.in);
    public OverSpeed(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
        System.out.println(p.getName() + "uses" + this.getName());
        for (Players player: this.game.getPlayers()) {
            boolean isRejected = false;
            if (player.reject()) {
                isRejected = true;
            }
            if (!isRejected) {
                boolean correctNum = false;
                Iterator<GetCard> cardIterator = player.getHandCards().iterator();
                int temp = 0;
                System.out.print("Please choose the property to quit");
                while (cardIterator.hasNext()) {
                    System.out.print(++temp + "/");
                }
                while (!correctNum) {
                    int index = scanner.nextInt();
                    if (index > 0 && index <= player.getHandCards().size()) {
                        player.getHandCards().remove(player.getHandCards().size() - index);
                        correctNum = true;
                    } else {
                        System.out.println("Please enter the right num");
                    }
                }
            }
        }
        }else{

        }
    }
}
