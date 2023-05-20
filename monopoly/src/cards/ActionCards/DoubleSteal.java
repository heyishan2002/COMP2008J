package cards.ActionCards;

import cards.ActionCard;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class DoubleSteal extends ActionCard {
    Players embezzler = null;
    public DoubleSteal(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
            Scanner scanner = new Scanner(System.in);
            Property getProperty;
            embezzler = this.choosePlayer(this.game, p);
            Stack<Property> propertyToSteal = embezzler.getMyProperty();
            Iterator<Property> propertyIterator = propertyToSteal.iterator();
            if (!embezzler.reject()) {
                int temp;
                for (int i = 0; i < 2; i++) {
                    temp = 0;
                    System.out.print("Please choose ths property to steal");
                    while (propertyIterator.hasNext()) {
                        System.out.print(++temp + ": " + propertyIterator.next().getName() + "/");
                    }
                    int index = scanner.nextInt();
                    if (index > 0 && index <= propertyToSteal.size()) {
                        getProperty = propertyToSteal.remove(propertyToSteal.size() - index);
                        p.addProperty(getProperty);
                    }
                }
            }
        }else{

        }
    }
}
