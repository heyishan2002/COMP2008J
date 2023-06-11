package model.cards;

public class Card extends Selectable{
    int money;
    String name;
    Type type;
    String message;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getName(){
        return this.name;
    };
    public Type getType(){
        return this.type;
    };
    public int getMoney() {
        return money;
    }
}
