package com.devansh.dwivedi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.devansh.dwivedi.types.Suit;

public class Deck {
    List<Card> cardDeck;

    public Deck() {
        this.cardDeck = new ArrayList<Card>();
        for(int value = 1 ; value <= 13 ; value++){
            for(Suit suit : Suit.values()){
             cardDeck.add(new Card(value,suit));
            }
        }
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cardDeck=" + cardDeck +
                '}';
    }

    public void shuffle(){
        Collections.shuffle(cardDeck);
    }

    public Card dealCard(Card card) throws Exception{
        boolean removePossible = cardDeck.remove(card);
        if(!removePossible){
            throw new Exception("Card not present");
        }
        return card;
    }

    public Card dealCard(){
        Card removedCard = cardDeck.remove(0);
        return removedCard;
    }

    //Size of the deck for testing purpose
    public int getSizeOfDeck(){
        return cardDeck.size();
    }
}