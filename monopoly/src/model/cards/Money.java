package model.cards;

public class Money extends Bankable {
    //代表金钱牌的类，这种牌不具有任何功能
    public Money(int m){
        super(m, "MONEY");
        this.type = Type.MONEY;
        this.message = "MONEY";
    }
}
