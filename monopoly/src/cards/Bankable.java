package cards;

public class Bankable implements GetCard {
    //这个类表示可以放进银行的牌，包括行动牌和钱牌
    private int money;
    private String name;
    Bankable(int money , String name){
    this.money = money;
    this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }
}
