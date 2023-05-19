package cards;

import Game.Game;
import players.Players;

import java.util.Scanner;

public abstract class ActionCard extends Bankable{
    Scanner scanner = new Scanner(System.in);
    public Game game;

    public ActionCard(int money, String name, Game g) {
        super(money, name);
        this.game = g;
    }

    public int getMoney() {
        return this.getMoney();
    }

    public abstract void use(Players p);

    public Players choosePlayer(Game game,Players player) {
        Players returnPlayer = null;
        boolean rightP = false;
        while (!rightP) {
            System.out.print("Please choose the tenant");
            int temp = 0;
            for (Players op : game.getPlayers()) {
                if (op != player) {
                    System.out.print(++temp + ": " + op.getName() + "/");
                }
            }
            int index = scanner.nextInt();
            if (index > 0 && index <= game.getPlayers().length) {
                returnPlayer = game.getPlayers()[index - 1];
                rightP = true;
            }
        }
        return returnPlayer;
    }

    public boolean ifUseAsMoney(Players p){
        boolean correctEnter = false;
        boolean isUseAsMoney = false;
        while (!correctEnter){
            System.out.println("Please enter '0' to use as money, and '1' to use as action");
            int choose = scanner.nextInt();
            if(choose == 1){
                isUseAsMoney = false;
                correctEnter = true;
            } else if (choose == 0) {
                isUseAsMoney = true;
                correctEnter = true;
            }else{
                System.out.println("Please enter the right num");
            }
        }
        return isUseAsMoney;
    }
}
