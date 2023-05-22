package cards.ActionCards;

import Game.Game;
import cards.*;
import players.Players;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class UniversalRent extends ActionCard {
    Color[] colors = new Color[]{Color.Orange,Color.Yellow,Color.Black,Color.Brown,Color.LightGreen,Color.LightBlue,Color.DarkGreen,Color.RED,Color.Pink,Color.DarkBlue};


    Players tenant = null;
    UniversalRent(int m, Game g){
        super(m,"UniversalRent",g);

    }

    @Override
    public void use(Players player) {
        System.out.println(player.getName() + "uses" + this.getName());
        Color color = null;
        int temp = 0;
        Scanner scanner = new Scanner(System.in);
        boolean correctColor = false;
        while(!correctColor){
            System.out.print("Please choose the color:");
            for(Color i :colors){
                System.out.print(++temp +": " + i + "/");
            }
            color = Color.valueOf(scanner.nextLine());
            if( player.getPropertyNum(color) != 0){
                correctColor = true;
            }else{
                System.out.println("Please enter the right color!");
            }
        }
        int payNum = color.getRent(player.getPropertyNum(color));
        tenant = this.choosePlayer(game,player);
        if(!tenant.reject()) {
            Stack payCards = player.pay(payNum);
            Iterator iterator = payCards.iterator();
            while (iterator.hasNext()) {
                Object card = iterator.next();
                if (card instanceof Property) {
                    player.addProperty((Property) card);
                } else {
                    player.addMoney((Bankable) card);
                }
            }
        }
    }
}
