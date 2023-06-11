package model.player;

import View.MInterface;
import model.Game.Game;
import model.cards.*;
import model.cards.ActionCards.DoubleRent;
import model.cards.ActionCards.JustSayNo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Player extends Selectable{
    public MInterface mInterface;
    public Selectable bank = new Selectable();
    public Selectable hand = new Selectable();
    public Selectable pro = new Selectable();
    int action;//The action times the player have now

    public Player next;//The next player

    Game game;//The game now
    boolean isInPrison = false;//Judge if the player is in prison

    //The basic information of the player
    String name;
    LocalDate birthDate;

    //Cards owned by the player
    Stack<PlayerProperty> myProperty = new Stack<>();
    Stack<Bankable> myBank = new Stack<>();
    Stack<Card> handCards = new Stack<>();


    /**Query how many industries the player has for this color**/
    public int getPropertyNum(PropertyColor c){
        Iterator<PlayerProperty> iterator = myProperty.iterator();
        while (iterator.hasNext()){
            PlayerProperty next1 = iterator.next();
            if(next1.propertyColor == c){
                return next1.getNum();
            }
        }
        return 0;
    }

    public int getHasPropertyNum(){
        int propertyNum = 0;
        Iterator<PlayerProperty> iterator = this.myProperty.iterator();
        while(iterator.hasNext()){
            propertyNum += iterator.next().getNum();
        }
        return propertyNum;
    }

    /**Determine how many color industries this player has complete**/
    public int getFullPropertyNum(){
        int numFullP = 0;
        PropertyColor[] propertyColors = {PropertyColor.RED, PropertyColor.Black, PropertyColor.Brown, PropertyColor.Pink, PropertyColor.Orange, PropertyColor.DarkBlue, PropertyColor.DarkGreen, PropertyColor.LightBlue, PropertyColor.LightGreen, PropertyColor.Yellow};
        for(PropertyColor c: propertyColors){
            if(isSet(c)){
                numFullP++;
            }
        }
        return numFullP;
    }

    /**Used to pay rent, debt, birthdays, etc**/
    public Stack pay(int n) {
        Stack<Card> payCards = new Stack<>();
        boolean correctPay = false;
        while(!correctPay){
            int payMoney = 0;
            if(myBank.empty() && getHasPropertyNum() == 0){
                return null;
            }
            if(myBank.empty()){
                int payProperty = 0;
                game.refreshSelected();
                this.bank.selectable();
                MInterface.ButtonName buttonName2 = mInterface.gameInterface(this.name, "Please pay " + n + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                Iterator<Selectable> iterator2 = game.getSelected().iterator();
                while (iterator2.hasNext()){
                    payMoney += ((Card)iterator2.next()).getMoney();
                }
                if(payProperty >= n-payMoney){
                    correctPay = true;
                    Iterator<Selectable> iterator3 = game.getSelected().iterator();
                    while (iterator3.hasNext()){
                        Selectable next1 = iterator3.next();
                        if(next1 instanceof Property){
                            payCards.push((Card) next1);
                            this.removeProperty((Property) next1);
                        }
                    }
                }else{
                    if(this.getHasPropertyNum() == 0){
                        correctPay = true;
                    }else{
                        this.pay(n - payMoney - payProperty);
                    }
                }
            }else {
                game.refreshSelected();
                this.bank.selectable();
                MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "Please pay " + n + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                Iterator<Selectable> iterator = game.getSelected().iterator();
                while (iterator.hasNext()) {
                    payMoney += ((Card) iterator.next()).getMoney();
                }
                if (payMoney >= n) {
                    correctPay = true;
                    Iterator<Selectable> iterator2 = game.getSelected().iterator();
                    while (iterator2.hasNext()) {
                        Selectable next1 = iterator2.next();
                        if (next1 instanceof Bankable) {
                            this.myBank.remove(next1);
                            payCards.push((Card) next1);
                        }
                    }
                } else {
                    if (this.myBank.empty()) {
                        int payProperty = 0;
                        game.refreshSelected();
                        this.bank.selectable();
                        MInterface.ButtonName buttonName2 = mInterface.gameInterface(this.name, "Please pay " + n + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                        Iterator<Selectable> iterator2 = game.getSelected().iterator();
                        while (iterator2.hasNext()) {
                            payMoney += ((Card) iterator2.next()).getMoney();
                        }
                        if (payProperty >= n - payMoney) {
                            correctPay = true;
                            Iterator<Selectable> iterator3 = game.getSelected().iterator();
                            while (iterator3.hasNext()) {
                                Selectable next1 = iterator3.next();
                                if (next1 instanceof Property) {
                                    payCards.push((Card) next1);
                                    this.removeProperty((Property) next1);
                                }
                            }
                        } else {
                            if (this.getHasPropertyNum() == 0) {
                                correctPay = true;
                            } else {
                                this.pay(n - payMoney - payProperty);
                            }
                        }
                    } else {
                        this.pay(n - payMoney);
                    }
                }
            }
        }
        return null;
    }


    /**Determine whether this color industry forms a set**/
    public boolean isSet(PropertyColor c){
        int numP = this.getPropertyNum(c);
        if(numP >= c.getNeedNum()){
            return true;
        }else{
            return false;
        }
    }


    /**Determine if the player wins**/
    public boolean isWin(){
        if(getFullPropertyNum() >= 3){
            return true;
        }else{
            return false;
        }
    }

    public void addProperty(Property p){
        Iterator<PlayerProperty> iterator = myProperty.iterator();
        while (iterator.hasNext()){
            PlayerProperty next1 = iterator.next();
            if(next1.propertyColor == p.getColor()){
                next1.addProperty(p);
            }
        }
    }

    public void removeProperty(Property p){
        Iterator<PlayerProperty> iterator = myProperty.iterator();
        while (iterator.hasNext()){
            PlayerProperty next1 = iterator.next();
            if(next1.propertyColor == p.getColor()){
                next1.remove(p);
            }
        }
    }

    public void addMoney(Bankable b){
        this.myBank.push(b);
    }
    public void addCard(Card g){
        this.handCards.push(g);
    }
    public Player(String name, LocalDate date,Game g){
        this.name = name;
        this.birthDate = date;
        this.game = g;
        this.mInterface = game.mInterface;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }

    public Stack<PlayerProperty> getMyProperty() {
        return myProperty;
    }


    public Stack<Card> getHandCards() {
        return handCards;
    }

    /**The player was thrown in jail**/
    public void goToPrison(){
        this.isInPrison = true;
    }

    /**Nullify other players' action cards for this player**/
    public boolean reject(Player p){
        game.refreshSelected();
        boolean hasReject = false;
        boolean isReject = false;
        Iterator<Card> iterator = this.handCards.iterator();
        while(iterator.hasNext()){
            Card next1 = iterator.next();
            if(next1 instanceof JustSayNo){
                next1.selectable();
                hasReject = true;
            }
        }
        if(!hasReject){
            return false;
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "If reject?", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

        if(buttonName == MInterface.ButtonName.cancel){
            game.refreshSelected();
            isReject = false;
        } else if (buttonName == MInterface.ButtonName.ok) {
            Iterator<Selectable> iterator1 = game.getSelected().iterator();
            game.refreshSelected();
            Selectable next1 = iterator1.next();
            if(next1 instanceof JustSayNo){
                if(!p.reject(this)) {
                    isReject = true;
                    this.handCards.remove(next1);
                }
            }
        }
        return isReject;
    }

    /**Determine if you need to double the player's rent**/
    public int isDouble (){
        game.refreshSelected();
        boolean hasDouble = false;
        Iterator<Card> iterator = this.handCards.iterator();
        while(iterator.hasNext()){
            Card next1 = iterator.next();
            if(next1 instanceof DoubleRent){
                hasDouble = true;
                next1.selectable();
            }
        }
        if(!hasDouble){
            return  1;
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "If use double rent?", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

        if(buttonName == MInterface.ButtonName.cancel){
            game.refreshSelected();
            return  1;
        } else if (buttonName == MInterface.ButtonName.ok) {

            Iterator<Selectable> iterator1 = game.getSelected().iterator();
            game.refreshSelected();
            Selectable next1 = iterator1.next();
            if(next1 instanceof DoubleRent){
                this.handCards.remove(next1);
                return 2;
            }
        }
        return 1;

    }


    /**Implement this player's turn**/
    public void round(){
        action = 0;
        //If the player is in prison, jump the round
        if(isInPrison){
            isInPrison = false;
            return ;
        }
        //If the hand is 0, touch five cards
        if(this.getHandCards().empty()){
            for(int i = 0;i < 5; i++){
                this.addCard(game.getDeck().pop());
            }
        }else{//If the hand isn't 0, touch two cards
            for(int i = 0;i < 2; i++){
                this.addCard(game.getDeck().pop());
            }
        }


        //Start action
        while(action++ < 3){
            game.refreshSelected();

            Iterator<Card> iterator = this.handCards.iterator();
            while(iterator.hasNext()){
                iterator.next().selectable();
            }

            MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "Please choose the card to use", MInterface.OperationType.card_operation, MInterface.SelectionType.single);

            if(buttonName == MInterface.ButtonName.cancel){
                game.refreshSelected();
                return;
            }
            ArrayList<Selectable> cards = this.game.getSelected();
            game.refreshSelected();
            Iterator<Selectable> card = cards.iterator();
            Card useCard = (Card) card.next();
            if(useCard == null){
                break;
            }
            switch (useCard.getType()){
                //The operation is performed according to the type of card returned
                case ACTION : {
                    if(buttonName == MInterface.ButtonName.bank){
                        this.addMoney((Bankable) useCard);
                        this.handCards.remove(useCard);
                    }else{
                        ((ActionCard) useCard).use(this);
                        this.handCards.remove(useCard);
                    }
                }
                case MONEY :{
                    this.addMoney((Bankable) useCard);
                    this.getHandCards().remove(useCard);
                    break;
                }

                case PROPERTY: {
                    switch (((Property)useCard).availableColor){
                        case 1:{
                            this.addProperty((Property) useCard);
                            this.handCards.remove(useCard);
                        }
                        case 2:{
                            PropertyColor[] availablePropertyColors = ((Property) useCard).availablePropertyColors;
                            PropertyColor availablePropertyColor1 = availablePropertyColors[0];
                            PropertyColor availablePropertyColor2 = availablePropertyColors[1];
                            getColorProperty(availablePropertyColor1).selectable();
                            getColorProperty(availablePropertyColor2).selectable();
                            MInterface.ButtonName buttonName1 = mInterface.gameInterface(this.name, "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
                            if(buttonName1 == MInterface.ButtonName.cancel){
                                action--;
                                continue;
                            }

                            Iterator<Selectable> iterator1 = game.getSelected().iterator();
                            game.refreshSelected();
                            PlayerProperty next1 = (PlayerProperty) iterator1.next();
                            ((Property) useCard).propertyColor = next1.getColor();
                            ((Property) useCard).use();
                            next1.addProperty((Property) useCard);
                        }
                        case 3:{
                            Iterator<PlayerProperty> iterator2 = myProperty.iterator();
                            while (iterator2.hasNext()){
                                iterator2.next().selectable();
                            }
                            MInterface.ButtonName buttonName1 = mInterface.gameInterface(this.name, "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
                            if(buttonName1 == MInterface.ButtonName.cancel){
                                action--;
                                continue;
                            }
                            Iterator<Selectable> iterator1 = game.getSelected().iterator();
                            game.refreshSelected();
                            PlayerProperty next1 = (PlayerProperty) iterator1.next();
                            ((Property) useCard).propertyColor = next1.getColor();
                            ((Property) useCard).use();
                            next1.addProperty((Property) useCard);
                        }
                    }
                }
            }
            if(isWin()){
                game.gameOver();
                break;
            }
        }



    }


    public ArrayList<Stack> getCards(){
        //Returns an array containing the player,
        // [0] of the array is the bank area and
        // [1] of the array is the industry area.
        // [2] of the array is the hand area
        // [3] of the array is the houses
        // [4] of the array is the hotels
        ArrayList<Stack> stacks = new ArrayList<>();
        stacks.add(this.myBank);
        stacks.add(this.myProperty);
        stacks.add(this.handCards);
        return stacks;
    }

    public void addAction(){
        this.action ++;
    }

    /**Get the property of this color**/
    public PlayerProperty getColorProperty(PropertyColor c){
        Iterator<PlayerProperty> iterator = myProperty.iterator();
        while(iterator.hasNext()){
            PlayerProperty next1 = iterator.next();
            if(next1.propertyColor == c){
                return next1;
            }
        }
        return null;
    }

    public void noSelectable(){
        this.unSelectable();
        this.selected = false;
        this.hand.unSelectable();
        this.hand.selected = false;
        this.bank.unSelectable();
        this.bank.selected = false;
    }

    public Player choosePlayer(){
        game.refreshSelected();
        Iterator<Player> iterator = game.getAllPlayers().iterator();
        while(iterator.hasNext()){
            iterator.next().selectable();
        }
        MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "Please choose a player", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
        if(buttonName == MInterface.ButtonName.cancel){
            game.refreshSelected();
            return null;
        }else{
            Iterator<Selectable> iterator1 = game.getSelected().iterator();
            game.refreshSelected();
            return (Player) iterator1.next();
        }
    }
}
