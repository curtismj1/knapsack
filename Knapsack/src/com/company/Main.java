package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws  IOException{
	// write your code here

        System.out.println("Hello World");
        Charset encoding = Charset.defaultCharset();
        for(String s: args){
            Scanner scanner = new Scanner(new File(s));
            initializeAndRun(scanner);
        }
    }
    public static List<Item> takeEmForAllTheyGot(KnapSack mySack){
        int index = 0;
        while(index < mySack.possibleItems.size()){
            if(mySack.canFitInBag(mySack.possibleItems.get(index))){
                mySack.takeItem(index);
                index--;
                if(mySack.getCurrentPrice() > mySack.optimalPrice){
                    mySack.optimalPrice = mySack.getCurrentPrice();
                    mySack.bestChoices = mySack.sack;
                }
                return takeEmForAllTheyGot(mySack);
            }
            mySack.removeItem(index);
            index++;
        }
        index = 0;
        return mySack.bestChoices;
    }


    private static void initializeAndRun(Scanner scanner)
    {
        KnapSack mySack = new KnapSack();
        int r;
        int i = 0;
        int tempVal = 0;
        int tempWeight = 0;
        while (scanner.hasNextInt()) {

            if(i == 0){
                mySack.numItems = scanner.nextInt();
            }
            else if(i == 1){
                mySack.maxWeight = scanner.nextInt();
            }
            else if(i %2 == 0){
                tempVal = scanner.nextInt();
            }
            else{
                tempWeight = scanner.nextInt();
                mySack.possibleItems.add(new Item(tempVal, tempWeight));
            }
            i++;
        }

        List<Item> best = new ArrayList<>();
        best = takeEmForAllTheyGot(mySack);
        for(Item item : mySack.bestChoices) {
            System.out.println("Item Value: " + item.value + " Item Weight: " + item.weight);
        }
        for(Item sItem : mySack.sack){
            System.out.println("Value: " + sItem.value + " Weight: " + sItem.weight);
        }

    }
}
