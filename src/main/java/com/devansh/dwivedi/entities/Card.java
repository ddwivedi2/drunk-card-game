package com.devansh.dwivedi.entities;

import com.devansh.dwivedi.types.Suit;

public class Card {
    int value;
    Suit suit;

    public Card(int value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuite() {
        return suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setSuite(Suit suit) {
        this.suit = suit;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card card = (Card) obj;
            return card.suit == this.suit && card.value == this.value;
        }
        return false;
    }   

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", suite=" + suit +
                '}';
    }
}