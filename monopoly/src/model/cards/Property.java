package model.cards;

import java.util.Scanner;

public class Property extends Card {
    public  int availableColor;
    String name;
    String mes;
    public PropertyColor propertyColor;
    public PropertyColor[] availablePropertyColors = new PropertyColor[10];
    int[] rent;

    public Property(PropertyColor c, String name) {
        availableColor = 1;
        propertyColor = c;
        availablePropertyColors[0] = c;
        this.name = name;
        this.type = Type.PROPERTY;
    }

    public Property(PropertyColor c1, PropertyColor c2, String name) {
        availableColor = 2;
        this.availablePropertyColors[0] = c1;
        this.availablePropertyColors[1] = c2;
        this.name = name;
        this.type = Type.PROPERTY;
    }

    public Property(PropertyColor c1, PropertyColor c2, PropertyColor c3, PropertyColor c4, PropertyColor c5, PropertyColor c6, PropertyColor c7,
                    PropertyColor c8, PropertyColor c9, PropertyColor c10, String name) {
        availableColor = 3;
        this.availablePropertyColors = new PropertyColor[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        this.name = name;
        this.type = Type.PROPERTY;
    }

    public void use() {
        switch (this.propertyColor) {
            case RED -> {
                this.money = 3;
                this.rent = new int[]{2, 3, 6};
                break;
            }
            case Pink -> {
                this.money = 2;
                this.rent = new int[]{1, 2, 4};
                break;
            }
            case Black -> {
                this.money = 2;
                this.rent = new int[]{1, 2, 3, 4};
                break;
            }
            case Brown -> {
                this.money = 1;
                this.rent = new int[]{1, 2};
                break;
            }
            case Orange -> {
                this.money = 2;
                this.rent = new int[]{1, 3, 5};
                break;
            }
            case Yellow -> {
                this.money = 3;
                this.rent = new int[]{2, 4, 6};
                break;
            }
            case DarkBlue -> {
                this.money = 4;
                this.rent = new int[]{3, 8};
            }
            case DarkGreen -> {
                this.money = 4;
                this.rent = new int[]{2, 4, 7};
                break;
            }
            case LightBlue -> {
                this.money = 1;
                this.rent = new int[]{1, 2, 3};
                break;
            }
            case LightGreen -> {
                this.money = 2;
                this.rent = new int[]{1, 2};
                break;
            }
        }
        if(rent.length == 2){
            this.mes = ("-----" + rent[0] + "\n" +
                    "-----" + rent[1] + "\n" );
        } else if (rent.length == 3) {
            this.mes = ("-----" + rent[0] + "\n" +
                    "-----" + rent[1] + "\n" +
                    "-----" + rent[2] + "\n" );
        }else{
            this.mes = ("-----" + rent[0] + "\n" +
                    "-----" + rent[1] + "\n" +
                    "-----" + rent[2] + "\n" +
                    "-----" + rent[3] + "\n");
        }
    }

    public PropertyColor getColor() {
        return this.propertyColor;
    }

    public int getMoney() {
        return money;
    }

}
