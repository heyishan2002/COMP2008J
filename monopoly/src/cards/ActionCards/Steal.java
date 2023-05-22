package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class Steal extends ActionCard {
    Players embezzler = null;
    public Steal(int money, Game g) {
        super(money, "steal",g);
    }

    @Override
    public void use(Players p) {
        System.out.println(p.getName() + "uses" + this.getName());
        Scanner scanner = new Scanner(System.in);
        boolean correctNum = false;
        Property getProperty = null;
        Stack<Property> propertyToSteal = null ;
        while (propertyToSteal != null){
            embezzler = this.choosePlayer(this.game,p);
            propertyToSteal = embezzler.getMyProperty();

        }
        if(!embezzler.reject()) {
            Iterator<Property> propertyIterator = propertyToSteal.iterator();
            int temp = 0;
            System.out.print("Please choose ths property to steal");
            while (propertyIterator.hasNext()) {
                System.out.print(++temp + ": " + propertyIterator.next().getName() + "/");
            }
            while (!correctNum) {
                int index = scanner.nextInt();
                if (index > 0 && index <= propertyToSteal.size()) {
                    getProperty = propertyToSteal.remove(propertyToSteal.size() - index);
                    p.addProperty(getProperty);
                    correctNum = true;
                } else {
                    System.out.println("Please enter the right num");
                }
            }
        }
    }
}
