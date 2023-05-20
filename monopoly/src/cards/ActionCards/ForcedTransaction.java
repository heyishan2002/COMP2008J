package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ForcedTransaction extends ActionCard {
    public ForcedTransaction(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
            Players exchanger = this.choosePlayer(this.game, p);
            Property getProperty = null;
            Scanner scanner = new Scanner(System.in);
            Stack<Property> propertyToExchange = null;
            boolean correctNum = false;
            while (propertyToExchange != null) {
                exchanger = this.choosePlayer(this.game, p);
                propertyToExchange = exchanger.getMyProperty();
            }
            if (!exchanger.reject()) {
                Iterator<Property> propertyIterator = propertyToExchange.iterator();
                Iterator<Property> myPropertyIterator = p.getMyProperty().iterator();
                int temp = 0;
                System.out.print("Please choose the property to get");
                while (propertyIterator.hasNext()) {
                    System.out.print(++temp + ": " + propertyIterator.next().getName() + "/");
                }
                while (!correctNum) {
                    int index = scanner.nextInt();
                    if (index > 0 && index <= propertyToExchange.size()) {
                        getProperty = propertyToExchange.remove(propertyToExchange.size() - index);
                        p.addProperty(getProperty);
                        correctNum = true;
                    } else {
                        System.out.println("Please enter the right num");
                    }
                }

                temp = 0;
                correctNum = false;
                System.out.print("Please choose the property to give");
                while (myPropertyIterator.hasNext()) {
                    System.out.print(++temp + ": " + myPropertyIterator.next().getName() + "/");
                }
                while (!correctNum) {
                    int index = scanner.nextInt();
                    if (index > 0 && index <= p.getMyProperty().size()) {
                        getProperty = p.getMyProperty().remove(p.getMyProperty().size() - index);
                        exchanger.addProperty(getProperty);
                        correctNum = true;
                    } else {
                        System.out.println("Please enter the right num");
                    }
                }
            }
        }
    }
}
