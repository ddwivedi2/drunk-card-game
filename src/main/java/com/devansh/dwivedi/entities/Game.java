package com.devansh.dwivedi.entities;

import java.util.ArrayList;
import java.util.List;

public class Game {
    Player[] players;
    Deck deck;

    public Game(Player[] players, Deck deck){
        this.players = players;
        this.deck = deck;
    }

    public void shuffleDeck(){
        this.deck.shuffle();
    }

    public void displayScores(){
        for(int i=0;i<players.length;i++){
            System.out.println(players[i]);
        }
    }

    public void distributeCards(int cardCount){
        for(int i=0;i<players.length;i++){
            players[i] = new Player(i);
            for(int j=0;j<cardCount;j++){
                players[i].getHand().add(deck.dealCard());
            }
        }
    }

    public Player determineWinner(){
        System.out.println("============================================");
        System.out.println("Calculating scores");
        System.out.println("============================================");
        for(int i=0;i<players.length;i++){
            players[i].calculateScore();
        }

        //Using List of winners Data structure for handling ties, the code will only terminate once this list has a size of one
        List<Player> winners = new ArrayList<Player>();
        int topScore = 0;
        for(int i=0;i<players.length;i++){
            if(players[i].getScore() > topScore){
                winners.clear();
                winners.add(players[i]);
                topScore = players[i].getScore();
            }else if(players[i].getScore() == topScore){
                winners.add(players[i]);
            }
        }

        if(winners.size() == 1){
            displayScores();
            return winners.get(0);
        }else{
            return HandleTieBreak(winners);
        }
    }

    public Player HandleTieBreak(List<Player> winners){
        while(deck.getSizeOfDeck() > 0){
            for(Player tiedPlayer : winners){
                tiedPlayer.setTieBreaker(deck.dealCard());
                if(deck.getSizeOfDeck() == 0){
                    return null;
                }
            }
            winners.clear();
            int tiedTopScore = 0;
            for(int i=0;i<players.length;i++){
                if(players[i].getTieBreaker() != null){
                    //TODO: Handle Aces
                    if(players[i].getTieBreaker().getValue() > tiedTopScore){
                        winners.clear();
                        winners.add(players[i]);
                        tiedTopScore = players[i].getTieBreaker().getValue();
                    }else if(players[i].getScore() == tiedTopScore){
                        winners.add(players[i]);
                    }
                }
            }
            if(winners.size() == 1){
                displayScores();
                return winners.get(0);
            }
        }
        return null;
    }
}