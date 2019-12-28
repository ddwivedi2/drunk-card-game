package com.devansh.dwivedi;

import com.devansh.dwivedi.entities.Deck;
import com.devansh.dwivedi.entities.Game;
import com.devansh.dwivedi.entities.Player;

/**
 * Aloha, buzzed card lovers
 */
public class GameRunner 
{

    static int PLAYER_COUNT = 4;
    static int CARD_COUNT = 3;

    //Runs a game, taking a parameter which determines if shuffling and distribution should be done
    //which is a valid case for a general (random) execution through static main method but for Unit test cases
    //we want to be able to assign particular cards to particular players for validation (Refer : Unit Test Cases)

    public Player runGame(Player[] players, Deck deck, Game game, boolean shuffleAndDistribute) {
        if(shuffleAndDistribute){
            game.shuffleDeck();
            game.distributeCards();
        }
        Player winner = game.determineWinner();
        System.out.println("============================================");
        System.out.println(winner.toString() + " won");
        return winner;
    }

    public static void main(String[] args)
    {
        Deck deck = new Deck();
        Player[] players = new Player[PLAYER_COUNT];
        Game game = new Game(players, deck, CARD_COUNT);

        GameRunner gameRunner = new GameRunner();
        gameRunner.runGame(players, deck, game, true);
        
        //Current state of cards after shuffling and dealing
        
    }   
}