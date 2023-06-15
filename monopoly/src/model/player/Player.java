package model.player;

import View.MInterface;
import control.Game;
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

    public Stack<Bankable> getMyBank() {
        return myBank;
    }

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
                Iterator<PlayerProperty> iterator = this.myProperty.iterator();
                while(iterator.hasNext()){
                    PlayerProperty next1 = iterator.next();
                    Stack<Property> p1 = next1.getP();
                    for(Property pr:p1){
                        pr.selectable();
                    }
                }
                MInterface.ButtonName buttonName2 = mInterface.gameInterface(this.name, "Please pay " + n + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                ArrayList<Selectable> selected1 = game.getSelected();
                if(selected1.isEmpty() || buttonName2 == MInterface.ButtonName.cancel){
                    continue;
                }
                Iterator<Selectable> iterator2 = selected1.iterator();
                while (iterator2.hasNext()){
                    payMoney += ((Card)iterator2.next()).getMoney();
                }
                if(payProperty >= n-payMoney){
                    correctPay = true;
                    Iterator<Selectable> iterator3 = game.getSelected().iterator();
                    while (iterator3.hasNext()){
                        Selectable next1 = iterator3.next();
                        if(next1 instanceof Property){
                            System.out.println("1");
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
                for(Bankable b : this.myBank){
                    b.selectable();
                }

                MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "Please pay " + n + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                ArrayList<Selectable> selected1 = game.getSelected();
                if(selected1.isEmpty() || buttonName == MInterface.ButtonName.cancel){
                    continue;
                }

                Iterator<Selectable> iterator = selected1.iterator();
                while (iterator.hasNext()) {
                    payMoney += ((Card) iterator.next()).getMoney();
                }
                Iterator<Selectable> iterator2 = game.getSelected().iterator();
                while (iterator2.hasNext()) {
                    Selectable next1 = iterator2.next();
                    if (next1 instanceof Bankable) {
                        this.myBank.remove(next1);
                        payCards.push((Card) next1);
                    }
                }
                if (payMoney >= n) {
                    correctPay = true;
                } else {
                    if (this.myBank.empty()) {
                        int payProperty = 0;
                        game.refreshSelected();
                        Iterator<PlayerProperty> iterator5 = this.myProperty.iterator();
                        while(iterator5.hasNext()){
                            PlayerProperty next1 = iterator5.next();
                            Stack<Property> p1 = next1.getP();
                            for(Property pr:p1){
                                pr.selectable();
                            }
                        }
                        MInterface.ButtonName buttonName2 = mInterface.gameInterface(this.name, "Please pay " + (n- payMoney) + " billion", MInterface.OperationType.ok_cancel, MInterface.SelectionType.multiple);
                        ArrayList<Selectable> selected2 = game.getSelected();
                        game.refreshSelected();
                        if(selected2.isEmpty() || buttonName2 == MInterface.ButtonName.cancel){
                            continue;
                        }
                        Iterator<Selectable> iterator4 = selected2.iterator();
                        while (iterator4.hasNext()) {
                            Selectable next1 = iterator4.next();
                            payMoney += ((Card) next1 ).getMoney();
                            payCards.push((Card) next1);
                            this.removeProperty((Property) next1);
                        }
                        if (payProperty >= n - payMoney) {
                            correctPay = true;
                        } else {
                            if (this.getHasPropertyNum() == 0) {
                                correctPay = true;
                            } else {
                                this.pay(n - payMoney - payProperty);
                                break;
                            }
                        }
                    } else {
                        this.pay(n - payMoney);
                        break;
                    }
                }
            }
        }
        return payCards;
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
        myProperty.push(new PlayerProperty(PropertyColor.RED));
        myProperty.push(new PlayerProperty(PropertyColor.Black));
        myProperty.push(new PlayerProperty(PropertyColor.Brown));
        myProperty.push(new PlayerProperty(PropertyColor.LightBlue));
        myProperty.push(new PlayerProperty(PropertyColor.DarkGreen));
        myProperty.push(new PlayerProperty(PropertyColor.LightGreen));
        myProperty.push(new PlayerProperty(PropertyColor.DarkBlue));
        myProperty.push(new PlayerProperty(PropertyColor.Orange));
        myProperty.push(new PlayerProperty(PropertyColor.Pink));
        myProperty.push(new PlayerProperty(PropertyColor.Yellow));
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
        while(action++ < 3) {
            game.refreshSelected();

            Iterator<Card> iterator = this.handCards.iterator();
            while (iterator.hasNext()) {
                iterator.next().selectable();
            }

            MInterface.ButtonName buttonName = mInterface.gameInterface(this.name, "Please choose the card to use", MInterface.OperationType.card_operation, MInterface.SelectionType.single);

            if (buttonName == MInterface.ButtonName.skip) {
                game.refreshSelected();
                break;
            }
            ArrayList<Selectable> cards = this.game.getSelected();
            game.refreshSelected();
            if (cards.isEmpty()) {
                action--;
                continue;

            }
            Iterator<Selectable> card = cards.iterator();
            Card useCard = (Card) card.next();
            if (useCard == null) {
                break;
            }
            switch (useCard.getType()) {
                //The operation is performed according to the type of card returned
                case ACTION: {
                    if (buttonName == MInterface.ButtonName.bank) {
                        this.addMoney((Bankable) useCard);
                        this.handCards.remove(useCard);
                    } else {
                        if (((ActionCard) useCard).use(this)) {
                            this.handCards.remove(useCard);
                        } else {
                            action--;
                        }

                    }
                    break;
                }
                case MONEY: {
                    this.addMoney((Bankable) useCard);
                    this.getHandCards().remove(useCard);
                    break;
                }

                case PROPERTY: {
                    switch (((Property) useCard).availableColor) {
                        case 1: {
                            this.addProperty((Property) useCard);
                            this.handCards.remove(useCard);
                            break;
                        }
                        case 2: {
                            PropertyColor[] availablePropertyColors = ((Property) useCard).availablePropertyColors;
                            PropertyColor availablePropertyColor1 = availablePropertyColors[0];
                            PropertyColor availablePropertyColor2 = availablePropertyColors[1];
                            getColorProperty(availablePropertyColor1).selectable();
                            getColorProperty(availablePropertyColor2).selectable();
                            MInterface.ButtonName buttonName1 = mInterface.gameInterface(this.name, "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
                            if (buttonName1 == MInterface.ButtonName.cancel) {
                                action--;
                                continue;
                            }
                            ArrayList<Selectable> selected1 = game.getSelected();
                            if (selected1.isEmpty()) {
                                action--;
                                continue;
                            }
                            Iterator<Selectable> iterator1 = selected1.iterator();
                            game.refreshSelected();
                            PlayerProperty next1 = (PlayerProperty) iterator1.next();
                            ((Property) useCard).propertyColor = next1.getColor();
                            ((Property) useCard).use();
                            next1.addProperty((Property) useCard);
                            this.handCards.remove(useCard);
                            break;
                        }
                        case 3: {
                            Iterator<PlayerProperty> iterator2 = myProperty.iterator();
                            while (iterator2.hasNext()) {
                                iterator2.next().selectable();
                            }
                            MInterface.ButtonName buttonName1 = mInterface.gameInterface(this.name, "Please choose the color", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);
                            if (buttonName1 == MInterface.ButtonName.cancel) {
                                action--;
                                continue;
                            }
                            ArrayList<Selectable> selected1 = game.getSelected();
                            if (selected1.isEmpty()) {
                                action--;
                                continue;
                            }
                            Iterator<Selectable> iterator1 = selected1.iterator();
                            game.refreshSelected();
                            PlayerProperty next1 = (PlayerProperty) iterator1.next();
                            ((Property) useCard).propertyColor = next1.getColor();
                            ((Property) useCard).use();
                            next1.addProperty((Property) useCard);
                            this.handCards.remove(useCard);
                            break;
                        }
                    }
                }
            }
            if (isWin()) {
                game.gameOver(this);
            }
        }
        while (this.handCards.toArray().length > 7) {
            game.refreshSelected();

            Iterator<Card> iterator3 = this.handCards.iterator();
            while (iterator3.hasNext()) {
                iterator3.next().selectable();
            }

            MInterface.ButtonName buttonName3 = mInterface.gameInterface(this.name, "Please choose the card to quit", MInterface.OperationType.ok_cancel, MInterface.SelectionType.single);

            if (buttonName3 == MInterface.ButtonName.cancel) {
                game.refreshSelected();
                continue;
            }
            ArrayList<Selectable> cards3 = this.game.getSelected();
            game.refreshSelected();
            if (cards3.isEmpty()) {
                continue;
            }
            Iterator<Selectable> card3 = cards3.iterator();
            Card quitCard = (Card) card3.next();
            this.handCards.remove(quitCard);
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
        Iterator<PlayerProperty> iterator2 = this.getMyProperty().iterator();
        while (iterator2.hasNext()) {
            PlayerProperty next = iterator2.next();
            Stack<Property> p1 = next.getP();
            for(Property pr:p1){
                pr.selectable();
            }
        }
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
            ArrayList<Selectable> selected1 = game.getSelected();
            if(selected1.isEmpty()){
                return null;
            }
            Iterator<Selectable> iterator1 = selected1.iterator();
            game.refreshSelected();
            return (Player) iterator1.next();
        }
    }
}
