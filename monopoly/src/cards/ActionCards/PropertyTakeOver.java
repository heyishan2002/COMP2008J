package cards.ActionCards;

import cards.ActionCard;
import cards.Color;
import Game.Game;
import cards.Property;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class PropertyTakeOver extends ActionCard {
    public PropertyTakeOver(int money, String name, Game g) {
        super(money, name, g);
    }

    @Override
    public void use(Players p) {
        if(!ifUseAsMoney(p)) {
            System.out.println(p.getName() + "uses" + this.getName());
            Players embezzler = null;
            Stack<Color> staelableColor = new Stack<Color>();
            Color chooseColor = null;
            Scanner scanner = new Scanner(System.in);
            boolean correctNum = false;
            Property getProperty = null;
            Stack<Property> propertyToSteal = null;
            while (propertyToSteal != null) {
                embezzler = this.choosePlayer(this.game, p);
                propertyToSteal = embezzler.getMyProperty();
            }
            if (!embezzler.reject()) {
                Iterator<Property> propertyIterator = propertyToSteal.iterator();
                for (Color c : Color.values()) {
                    if (embezzler.isSet(c)) {
                        staelableColor.push(c);
                    }
                }
                Iterator<Color> colorIterator = staelableColor.iterator();
                int temp = 0;
                System.out.print("Please choose the color of the property to steal");
                while (colorIterator.hasNext()) {
                    System.out.print(++temp + ": " + colorIterator.next() + "/");
                }
                while (!correctNum) {
                    int index = scanner.nextInt();
                    if (index > 0 && index <= propertyToSteal.size()) {
                        chooseColor = staelableColor.remove(staelableColor.size() - index);
                        correctNum = true;
                    } else {
                        System.out.println("Please enter the right num");
                    }
                }
                for (int i = 0; i < propertyToSteal.size(); i++) {
                    if (propertyToSteal.get(i).getColor() == chooseColor) {
                        p.getMyProperty().push(propertyToSteal.remove(i));
                    }
                }
            }
        }
    }
}
