package com.devansh.dwivedi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    List<Card> hand;
    int score;
    Card tieBreaker;
    int playerId;

    public int getID(){
        return playerId;
    }

    public Player(int id) {
        this.hand = new ArrayList<Card>();
        this.score = 0;
        this.playerId = id;
    }

    public void setTieBreaker(Card card){
        this.tieBreaker = card;
    }

    public Card getTieBreaker(){
        return this.tieBreaker;
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }

    public void calculateScore(){
        int max = 0;
        int sum = 0;
        for(Card card:this.hand){
            sum += card.getValue();
            if(card.getValue() > max){
                max = card.getValue();
            }
        }

        Map<Integer, List<Card>> cardMap = new HashMap<Integer, List<Card>>();
        boolean isPair = false;
        int pairKey = 0;
        for(Card card:this.hand){
            if(cardMap.get(card.getValue())!= null){
                isPair = true;
                pairKey = card.getValue();
                cardMap.get(card.getValue()).add(card);
            }else{
                List<Card> newList = new ArrayList<Card>();
                newList.add(card);
                cardMap.put(card.getValue(), newList);
            }
        }

        //Trail (3 of a kind)
        if(cardMap.keySet().size() == 1){
            this.score = 10000 + sum;
        }else{
            if(isPair){
                //2 of a kind
                int subScore = 2 * pairKey;
                this.score = 100 + subScore;
            }else{
                //Check for sequence
                List<Integer> cardValues = new ArrayList<Integer>(cardMap.keySet());
                Collections.sort(cardValues);
                if(isConsecutive(cardValues)){
                    //sequence Multiplier
                    this.score = 1000 + sum;
                }else{
                    this.score = max;
                }
            }
        }
    }

    private static boolean isConsecutive(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) + 1 != list.get(i)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Player{" + playerId + " " +
                "hand=" + hand +
                '}' + " SCORE: " + score +" TieBreaker: " + tieBreaker;
    }
}