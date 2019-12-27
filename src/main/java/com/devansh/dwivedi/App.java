package com.devansh.dwivedi;

import java.util.ArrayList;
import java.util.List;

import com.devansh.dwivedi.entities.Deck;
import com.devansh.dwivedi.entities.Player;

/**
 * Aloha, buzzed card lovers
 */
public class App 
{

    static int PLAYER_COUNT = 4;
    static int CARD_COUNT = 3;
    public static void main( String[] args )
    {
        Deck deck = new Deck();
        deck.shuffle();

        Player[] players = new Player[PLAYER_COUNT];

        for(int i=0;i<players.length;i++){
            players[i] = new Player(i);
            for(int j=0;j<CARD_COUNT;j++){
                players[i].getHand().add(deck.dealCard());
            }
        }
        //Current state of cards after shuffling and dealing
        for(int i=0;i<players.length;i++){
            players[i].calculateScore();
        }

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
            for(int i=0;i<players.length;i++){
                System.out.println(players[i]);
            }
            System.out.println(winners.get(0).toString() + " won");
        }else{
            HandleTieBreak(deck, players, winners);
        }
    }

    public static void HandleTieBreak(Deck deck, Player[] players, List<Player> winners){
        while(deck.getSizeOfDeck() > 0){
            for(Player tiedPlayer : winners){
                tiedPlayer.setTieBreaker(deck.dealCard());
                if(deck.getSizeOfDeck() == 0){
                    System.out.println("No winner was found");
                }
            }
            winners.clear();
            int tiedTopScore = 0;
            for(int i=0;i<players.length;i++){
                if(players[i].getTieBreaker() != null){
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
                for(int i=0;i<players.length;i++){
                    System.out.println(players[i]);
                }
                System.out.println(winners.get(0).toString() + " won");
                break;
            }
        }
    }
}
