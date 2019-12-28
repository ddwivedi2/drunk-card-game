package com.devansh.dwivedi.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.devansh.dwivedi.types.Suit;

public class Deck {
    List<Card> cardDeck;

    /*
    * Initializing a deck with 52 cards, 1: Ace ..13 King with ENUMS specifying suits
    */
    public Deck() {
        this.cardDeck = new ArrayList<Card>();
        for(int value = 1 ; value <= 13 ; value++){
            for(Suit suit : Suit.values()){
             cardDeck.add(new Card(value, suit));
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

    /*
    * Deal a particular card from the deck
    * Thwows exception if the card is missing
    */
    public Card dealCard(Card card) throws Exception{
        boolean removePossible = cardDeck.remove(card);
        if(!removePossible){
            throw new Exception("Card not present");
        }
        return card;
    }

    /*
    * Removing the top card from the deck
    */
    public Card dealCard(){
        Card removedCard = cardDeck.remove(0);
        return removedCard;
    }

    public int getSizeOfDeck(){
        return cardDeck.size();
    }
}