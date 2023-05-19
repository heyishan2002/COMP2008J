package players;

import cards.*;
import cards.ActionCards.MakeAnObjection;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.Stack;

public class Players {
    boolean isInPrison = false;
    Scanner myScanner = new Scanner(System.in);
    int fullProperty = 0;
    String name;
    LocalDate birthDate;
    Stack<Property> myProperty = new Stack<Property>();
    Stack<Bankable> myBank = new Stack<Bankable>();
    Stack<GetCard> handCards = new Stack<GetCard>();

    public int getPropertyNum(Color c){
        int numP = 0 ;
        Iterator<Property> p =myProperty.iterator();
        while (p.hasNext()){
            if(p.next().getColor() == c){
                    numP += 1;
            }
        }
        return numP;
    }

    public Stack pay(int n) {
        int payMoney = 0;
        Stack payCards = null;
        while (payMoney < n) {
            if (this.myBank != null) {
                System.out.println("Please choose the card to pay");
                for (Bankable i : this.myBank) {
                    System.out.println(i.getName() + ": " + i.getMoney() + ",");
                }
                int index = myScanner.nextInt();
                if (index <= 0 || index > this.myBank.size()) {
                    System.out.println("The index is not available!");
                    continue;
                }
                Bankable b = myBank.remove(myBank.size() - index);
                payCards.push(b);
                payMoney += b.getMoney();

            }else {
                if(this.myProperty != null){
                    System.out.println("Please choose the card to pay");
                    int j = 0 ;
                    for (Property i : this.myProperty) {
                        System.out.println(++j + ": "+i.getName() + ": " + i.getMoney() + ",");
                    }
                    int index = myScanner.nextInt();
                    if (index <= 0 || index > this.myProperty.size()) {
                        System.out.println("The index is not available!");
                        continue;
                    }
                    Property p =myProperty.remove(myProperty.size() - index);
                    payCards.push(p);
                    payMoney += p.getMoney();
                }
            }
        }
        return payCards;
    }

    public boolean isSet(Color c){
        int numP = this.getPropertyNum(c);
        if(numP >= c.getNeedNum()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isWin(){
        if(fullProperty >= 3){
            return true;
        }else{
            return false;
        }
    }
    public void addProperty(Property p){
        this.myProperty.push(p);
    }
    public void addMoney(Bankable b){
        this.myBank.push(b);
    }
    public void addCard(GetCard g){
        this.handCards.push(g);
    }
    public Players(String name,LocalDate date){
        this.name = name;
        this.birthDate = date;
    }

    public String getName() {
        return name;
    }
    public LocalDate getBirthDate(){
        return birthDate;
    }

    public Stack<Property> getMyProperty() {
        return myProperty;
    }

    public Stack<GetCard> getHandCards() {
        return handCards;
    }
    public void goToPrison(){
        this.isInPrison = true;
    }
    public boolean reject(){
        boolean isReject = false;
        for (GetCard g:this.getHandCards()){
            if(g instanceof MakeAnObjection){
                System.out.println("Tap '1' to reject:");
                if(myScanner.nextLine() == "1"){
                    isReject = true;
                    this.getHandCards().remove(g);
                }
            }
        }
        return isReject;
    }

    public void round(){
        if(!isInPrison){
            int action = 3;
            GetCard useCard = null;
            while(action > 0){
                boolean correctNum = false;
                Iterator<GetCard> cardIterator = this.getHandCards().iterator();
                int temp = 0;
                System.out.print("Please choose the card use");
                while (cardIterator.hasNext()) {
                    System.out.print(++temp + "/");
                }
                while (!correctNum) {
                    int index = myScanner.nextInt();
                    if (index > 0 && index <= this.getHandCards().size()) {
                        useCard = this.getHandCards().remove(this.getHandCards().size() - index);
                        correctNum = true;
                    } else {
                        System.out.println("Please enter the right num");
                    }
                }
            }
            if(useCard instanceof Property){
                this.getMyProperty().push((Property) useCard);
            } else if (useCard instanceof Money) {
                this.myBank.push((Money)useCard);
            }else {
                ((ActionCard)useCard).use(this);
            }
        }
    }
    public boolean transfer(){
        boolean isTransfer = false;
        for (GetCard g:this.getHandCards()){
            if(g instanceof MakeAnObjection){
                System.out.println("Tap '1' to transfer:");
                if(myScanner.nextLine() == "1"){
                    isTransfer = true;
                    this.getHandCards().remove(g);
                }
            }
        }
        return isTransfer;
    }
}
