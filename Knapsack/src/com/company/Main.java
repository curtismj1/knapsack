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

    //This is where the magic happens.
    //The idea is to process every single combination and to output the best one.
    public static List<Item> takeEmForAllTheyGot(KnapSack mySack){
        int index = 0;
        mySack.outputBest();
        while(index < mySack.possibleItems.size()){
            if(mySack.canFitInBag(mySack.possibleItems.get(index))) {
                mySack.takeItem(index);
                if (mySack.getCurrentPrice() > mySack.optimalPrice) {
                    mySack.optimalPrice = mySack.getCurrentPrice();
                    mySack.bestChoices = mySack.sack;
                }
                takeEmForAllTheyGot(mySack);
            }
            index++;
        }
        if(mySack.sack.size() >0){
            mySack.removeItem(mySack.sack.size()-1);
        }
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
        for(Item item : best) {
            System.out.println("Item Value: " + item.value + " Item Weight: " + item.weight);
        }
        for(Item sItem : mySack.sack){
            System.out.println("Value: " + sItem.value + " Weight: " + sItem.weight);
        }

    }
}
