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
    public void    outputBest(){
        for(int i = 0; i< bestChoices.size(); i++){
            System.out.println("Value: " + bestChoices.get(i).value + " Weight: " + bestChoices.get(i).weight);
        }
        System.out.println();
    }
    public void outputSack(){
        for(int i = 0; i< sack.size(); i++){
            System.out.println("Value: " + sack.get(i).value + " Weight: " + sack.get(i).weight);
        }
        System.out.println();
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
        sacWeight -= sack.get(index).weight;
        possibleItems.add(sack.get(index));
        sack.remove(index);
    }


}
