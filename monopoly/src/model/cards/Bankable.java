package model.cards;

public class Bankable extends Card {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    Bankable(int money , String name){
    this.money = money;
    this.name = name;
    }


}
