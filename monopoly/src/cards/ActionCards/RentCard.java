package cards.ActionCards;

import Game.Game;
import cards.*;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class RentCard extends ActionCard {

    Color color1 = null;
    Color color2 = null;
    public RentCard(int m, Color c1, Color c2, Game g) {
        super(m,"Rent",g);
        this.color1 = c1;
        this.color2 = c2;
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
            Color color = null;
            Scanner scanner = new Scanner(System.in);
            boolean correctColor = false;
            while (!correctColor) {
                System.out.println("Please choose the color: " + color1 + "/" + color2);
                color = Color.valueOf(scanner.nextLine());
                if ((color == color1 || color == color2) && p.getPropertyNum(color) != 0) {
                    correctColor = true;
                } else {
                    System.out.println("Please enter the right color!");
                }
            }
            int payNum = color.getRent(p.getPropertyNum(color));
            for (Players player : this.game.getPlayers()) {
                boolean isRejected = false;
                if (player.reject()) {
                    isRejected = true;
                }
                if (!isRejected) {
                    Stack payCards = player.pay(payNum);
                    Iterator iterator = payCards.iterator();
                    while (iterator.hasNext()) {
                        Object card = iterator.next();
                        if (card instanceof Property) {
                            p.addProperty((Property) card);
                        } else {
                            p.addMoney((Bankable) card);
                        }
                    }
                }
            }
        }


    }
}
