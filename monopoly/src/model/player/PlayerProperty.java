package model.player;

import model.cards.*;
import model.cards.ActionCards.Hotel;
import model.cards.ActionCards.House;

import java.util.Stack;

public class PlayerProperty extends Selectable {

    public House house;
    public Hotel hotel;
    int numP;
    Stack<Property> propertyStack = new Stack<>();
    PropertyColor propertyColor;

    public Stack<Property> getP() {
        return propertyStack;
    }

    public PropertyColor getColor() {
        return propertyColor;
    }


    public PlayerProperty(PropertyColor propertyColor) {
        this.propertyColor = propertyColor;
    }

    public int getNum(){
        return numP;
    }

    public void addProperty(Property p){
        this.propertyStack.push(p);
        numP ++;
    }

    public boolean isSet(){
        return (numP >= propertyColor.getNeedNum());
    }

    public int rentNum(){
        return propertyColor.getRent(numP);
    }

    public void remove(Property p){
        this.propertyStack.remove(p);
        numP--;
    }

    public boolean addHouse(House house){
        if(this.house == null){
            this.house = house;
            return true;
        }else{
            return false;
        }
    }

    public boolean addHotel(Hotel hotel){
        if(this.hotel == null){
            this.hotel = hotel;
            return true;
        }else{
            return false;
        }
    }

    public int hMoney(){
        if(this.house == null && this.hotel == null){
            return 0;
        } else if (this.hotel == null) {
            return this.house.getHouseMoney();
        }else{
            return this.house.getHouseMoney() + this.hotel.getHotelMoney();
        }
    }
}
