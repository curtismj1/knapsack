package com.company;

import java.util.ArrayList;
import java.util.List;

class Item{
    public int value;
    public int weight;

    Item(int v, int w){
        value = v;
        weight = w;
    }
    Item(){

    }

}

public class KnapSack{
    public int numItems;
    public int maxWeight;
    public int sacWeight = 0;
    int optimalPrice = 0;
    public List<Item> bestChoices = new ArrayList<>();
    public List<Item> possibleItems = new ArrayList<>();
    public List<Item> sack = new ArrayList<>();
    KnapSack(){

    }

    boolean canFitInBag(Item item){
        if(sacWeight + item.weight > maxWeight){
            return false;
        }
        else{
            return true;
        }
    }
    int getCurrentPrice(){
        int price = 0;
        for(Item item : sack){
            price += item.value;
        }
        return  price;
    }
    void takeItem(int index){
        sacWeight += possibleItems.get(index).weight;
        sack.add(possibleItems.get(index));
        possibleItems.remove(index);
    }
    void removeItem(int index){
        possibleItems.add(sack.get(index));
        sacWeight -= sack.get(index).weight;
        sack.remove(index);
    }


}
