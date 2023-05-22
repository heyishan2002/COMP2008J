package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import cards.GetCard;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Successor extends ActionCard {
    Players inheritor;
    public Successor(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        System.out.println(p.getName() + "uses" + this.getName());
        Scanner scanner = new Scanner(System.in);
        GetCard getCard= null;
        inheritor = this.choosePlayer(this.game,p);
        boolean correctNum = false;
        Stack<GetCard> cardToExtends = null;
        while (cardToExtends != null){
            inheritor = this.choosePlayer(this.game,p);
            cardToExtends = inheritor.getHandCards();
        }
        if(!inheritor.reject()) {
            Iterator<GetCard> cardIterator = cardToExtends.iterator();
            int temp = 0;
            System.out.print("Please choose the property to steal");
            while (cardIterator.hasNext()) {
                System.out.print(++temp + "/");
            }
            while (!correctNum) {
                int index = scanner.nextInt();
                if (index > 0 && index <= cardToExtends.size()) {
                    getCard = cardToExtends.remove(cardToExtends.size() - index);
                    p.addCard(getCard);
                    correctNum = true;
                } else {
                    System.out.println("Please enter the right num");
                }
            }
        }
    }
}
