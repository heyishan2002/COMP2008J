package model.Game;

import View.GraphicInterface;
import View.MInterface;
import model.cards.*;
import model.cards.ActionCards.*;
import model.player.Player;
import model.player.PlayerProperty;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.*;

public class Game {
    public MInterface mInterface;
    public GraphicInterface gInterface;
    boolean over = false;
    //The class of a game, contain the player,deck

    Stack<Card> deck;//The deck of the game

    public Stack<Player> getAllPlayers() {
        return allPlayers;
    }

    Stack<Player> allPlayers;
    Player[] players;
    public Stack<Card> getDeck() {
        return deck;
    }
    public Player[] getPlayers() {
        return players;
    }

    public Game(GraphicInterface gInterface, MInterface mInterface){
    	this.gInterface = gInterface;
    	this.mInterface = mInterface;
    	allPlayers = new Stack<Player>();
    }


    public Stack<Card> newPile(){
        Stack<Card> cards = new Stack<>();
        Stack<Card> cards2 = new Stack<>();
        for(int i = 0; i< 1;i++){
            cards.push(new Money(10));
        }
        for(int i = 0; i< 2;i++){
            cards.push(new Property(PropertyColor.RED,PropertyColor.Yellow, "RED AND YELLOW"));
            cards.push(new Property(PropertyColor.Brown,PropertyColor.LightBlue, "BROWN AND LIGHT BLUE"));
            cards.push(new Property(PropertyColor.Pink,PropertyColor.Orange, "PINK AND ORANGE"));
            cards.push(new Property(PropertyColor.DarkBlue,PropertyColor.DarkGreen, "DARK BLUE AND DARK GREEN"));
            cards.push(new Property(PropertyColor.Black,PropertyColor.LightGreen, "BLACK AND LIGHT GREEN"));
            cards.push(new Property(PropertyColor.Black,PropertyColor.DarkGreen, "BLACK AND DARK GREEN"));
            cards.push(new Property(PropertyColor.Black,PropertyColor.LightBlue, "BLACK AND LIGHT BLACK"));
            cards.push(new Property(PropertyColor.Black,PropertyColor.LightGreen,PropertyColor.Yellow,PropertyColor.Brown,PropertyColor.DarkBlue,PropertyColor.DarkGreen, PropertyColor.Pink,PropertyColor.LightBlue,PropertyColor.Orange,PropertyColor.RED,"UNIVERSAL"));

            cards.push(new RentCard(1,PropertyColor.Brown,PropertyColor.LightBlue, this));
            cards.push(new RentCard(1,PropertyColor.Pink,PropertyColor.Orange, this));
            cards.push(new RentCard(1,PropertyColor.RED,PropertyColor.Yellow, this));
            cards.push(new RentCard(1,PropertyColor.DarkBlue,PropertyColor.DarkGreen, this));
            cards.push(new RentCard(1,PropertyColor.Black,PropertyColor.LightGreen, this));

            cards.push(new UniversalRent(1,this));

            cards.push(new DealBreaker(5,this));

            cards.push(new DoubleRent(1,this));

        }
        for(int i = 0; i< 3;i++){
            cards.push(new JustSayNo(4,this));
            cards.push(new Property(PropertyColor.Brown,"BROWN"));
            cards.push(new Property(PropertyColor.LightGreen,"LIGHTGREEN"));
            cards.push(new Property(PropertyColor.DarkBlue,"DARKBLUE"));
        }
        for(int i = 0; i< 4;i++){
            cards.push(new Steal(3,this));
            cards.push(new CollectionOfDebt(4,this));
            cards.push(new MyBirthday(2,"",this));
            cards.push(new Money(4));

            cards.push(new Property(PropertyColor.RED,"RED"));
            cards.push(new Property(PropertyColor.LightBlue,"lightblue"));
            cards.push(new Property(PropertyColor.Yellow,"YELLOW"));
            cards.push(new Property(PropertyColor.Pink,"PINK"));
            cards.push(new Property(PropertyColor.Orange,"ORANGE"));
            cards.push(new Property(PropertyColor.DarkGreen,"DARKGREEN"));
        }
        for(int i = 0; i< 5;i++){
            cards.push(new Property(PropertyColor.Black,"BLACK"));
        }
        for(int i = 0; i< 7;i++){
            cards.push(new House(3,4,this));
            cards.push(new Hotel(4,4,this));
            cards.push(new PassGo(1,this));
            cards.push(new Money(1));
            cards.push(new Money(2));
            cards.push(new Money(3));
            cards.push(new Money(4));
        }
        Object[] array = cards.toArray();
        List<Object> list = Arrays.asList(array);
        Collections.shuffle(list);
        Iterator<Object> iterator = list.iterator();
        while(iterator.hasNext()){
            cards2.push((Card) iterator.next());
        }
        return cards2;
    }

    public void startGame(){
        this.deck = newPile();
        Player nowPlayer;
        Player bob = new Player("Bob", LocalDate.of(2002, 7, 2), this);
        Player jack = new Player("Jack", LocalDate.of(2001, 2, 9), this);
        Player sam = new Player("Sam", LocalDate.of(1992, 11, 21), this);
        Player mike = new Player("Mike", LocalDate.of(2004, 1, 12),this);
        this.players = new Player[]{bob, jack, sam, mike};
        
        if (gInterface != null) {
        	gInterface.gPanel.addPlayer(bob);
        	gInterface.gPanel.addPlayer(jack);
        	gInterface.gPanel.addPlayer(sam);
        	gInterface.gPanel.addPlayer(mike);
        }
        
        this.allPlayers.push(bob);this.allPlayers.push(jack);this.allPlayers.push(sam);this.allPlayers.push(mike);
        bob.next = jack;jack.next = sam;sam.next = mike;mike.next = bob;
        nowPlayer = sam;
        while(!over){
            nowPlayer.next.round();
            nowPlayer = nowPlayer.next;
        }
    }

    /**Get information about all the cards that players have**/
    public ArrayList<ArrayList<Stack>> getStack(){
        //Returns an array containing all players
        ArrayList<ArrayList<Stack>> game = new ArrayList<>();
        game.add(players[0].getCards());
        game.add(players[1].getCards());
        game.add(players[2].getCards());
        game.add(players[3].getCards());
        return game;
    }

    public void gameOver(){
        this.over = true;
    }

    public ArrayList<Selectable> getSelected(){

        ArrayList<Selectable> selectables = new ArrayList<>();

        for(Player player:this.players) {
            if(player.selected){
                selectables.add(player);
            }
            Iterator<PlayerProperty> iterator2 = player.getMyProperty().iterator();
            while (iterator2.hasNext()) {
                PlayerProperty next = iterator2.next();
                Stack<Property> p1 = next.getP();
                for(Property pr:p1){
                    if(pr.selected){
                        selectables.add(pr);
                    }
                }
            }
        }
        ArrayList<ArrayList<Stack>> stack = this.getStack();
        for(ArrayList<Stack> each: stack){
            for (Stack stacks:each){
                Iterator iterator = stacks.iterator();
                while (iterator.hasNext()){
                   Selectable next = (Selectable) iterator.next();
                   if(next.selected){
                       selectables.add(next);
                   }
                }
            }
        }
        return selectables;
    }

    public void refreshSelected(){
        for(Player player:this.players) {
            player.noSelectable();
            Iterator<PlayerProperty> iterator2 = player.getMyProperty().iterator();
            while (iterator2.hasNext()) {
                PlayerProperty next = iterator2.next();
                Stack<Property> p1 = next.getP();
                for(Property pr:p1){
                    pr.unSelectable();
                }
                for(Bankable b:player.getMyBank()){
                    b.selectable();
                }
            }
        }
        ArrayList<ArrayList<Stack>> stack = this.getStack();
        for(ArrayList<Stack> each: stack){
            for (Stack stacks:each){
                Iterator iterator = stacks.iterator();
                while (iterator.hasNext()){
                    Selectable next = (Selectable) iterator.next();
                    next.unSelectable();
                    next.selected = false;
                    }
                }
            }
        }
    }

