package com.devansh.dwivedi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.devansh.dwivedi.entities.Card;
import com.devansh.dwivedi.entities.Deck;
import com.devansh.dwivedi.entities.Game;
import com.devansh.dwivedi.entities.Player;
import com.devansh.dwivedi.types.Suit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for Game Runner App
 */
public class GameRunnerTest {
    private Deck deck;
    private Player[] players;
    private Game game;

    /**
     * Setting up the Deck, Player and Game instances 
     */
    @BeforeEach
    public void GameRunnerTestInit() {

        int cardCount = 4;
        System.out.println("===================BEFORE EVERY TEST==========================");
        this.deck = new Deck();
        this.players = new Player[cardCount];
        for (int i = 0; i < cardCount; i++) {
            this.players[i] = new Player(i);
        }
        this.game = new Game(players, deck, 4);
    }

    private void distributeCards(Map<Integer, List<Card>> cardDis) throws Exception {
        Iterator entries = cardDis.entrySet().iterator();
        while (entries.hasNext()) {
            Entry thisEntry = (Entry) entries.next();
            int playerId = (Integer) thisEntry.getKey();
            List<Card> cardList = (List<Card>) thisEntry.getValue();
            for (Card card : cardList) {
                this.players[playerId].getHand().add(deck.dealCard(card));
            }
        }
    }

    //Testing a scenario where we have one trail
    @Test
    public void testAppWithTrail() throws Exception {
        Map<Integer, List<Card>> cardDis = new HashMap<Integer, List<Card>>();
        cardDis.put(0, new ArrayList<Card>(
                Arrays.asList(new Card(13, Suit.SPADES), new Card(13, Suit.CLUBS), new Card(13, Suit.DIAMONDS))));
        cardDis.put(1, new ArrayList<Card>(
                Arrays.asList(new Card(4, Suit.SPADES), new Card(10, Suit.CLUBS), new Card(9, Suit.DIAMONDS))));
        cardDis.put(2, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.SPADES), new Card(7, Suit.CLUBS), new Card(8, Suit.DIAMONDS))));
        cardDis.put(3, new ArrayList<Card>(
                Arrays.asList(new Card(3, Suit.SPADES), new Card(5, Suit.CLUBS), new Card(6, Suit.DIAMONDS))));

        distributeCards(cardDis);

        GameRunner gameRunner = new GameRunner();
        Player winner = gameRunner.runGame(this.players, this.deck, this.game, false);
        System.out.println(winner);
        assertEquals(winner.getID(), 0);
    }

    //Testing a scenario where we have two trails, higher trail value should win
    @Test
    public void testAppWithTwoTrails() throws Exception {
        Map<Integer, List<Card>> cardDis = new HashMap<Integer, List<Card>>();
        cardDis.put(0, new ArrayList<Card>(
                Arrays.asList(new Card(10, Suit.SPADES), new Card(10, Suit.CLUBS), new Card(10, Suit.DIAMONDS))));
        cardDis.put(1, new ArrayList<Card>(
                Arrays.asList(new Card(13, Suit.SPADES), new Card(13, Suit.CLUBS), new Card(13, Suit.DIAMONDS))));
        cardDis.put(2, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.SPADES), new Card(7, Suit.CLUBS), new Card(8, Suit.DIAMONDS))));
        cardDis.put(3, new ArrayList<Card>(
                Arrays.asList(new Card(3, Suit.SPADES), new Card(5, Suit.CLUBS), new Card(6, Suit.DIAMONDS))));

        distributeCards(cardDis);

        GameRunner gameRunner = new GameRunner();
        Player winner = gameRunner.runGame(this.players, this.deck, this.game, false);
        System.out.println(winner);
        assertEquals(winner.getID(), 1);
    }

    //Testing a scenario where we have two sequences, higher sequence should win
    @Test
    public void testAppWithTwoSequences() throws Exception {
        Map<Integer, List<Card>> cardDis = new HashMap<Integer, List<Card>>();
        cardDis.put(0, new ArrayList<Card>(
                Arrays.asList(new Card(10, Suit.SPADES), new Card(11, Suit.CLUBS), new Card(12, Suit.DIAMONDS))));
        cardDis.put(1, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.SPADES), new Card(3, Suit.CLUBS), new Card(4, Suit.DIAMONDS))));
        cardDis.put(2, new ArrayList<Card>(
                Arrays.asList(new Card(1, Suit.SPADES), new Card(5, Suit.CLUBS), new Card(8, Suit.DIAMONDS))));
        cardDis.put(3, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.CLUBS), new Card(7, Suit.CLUBS), new Card(3, Suit.DIAMONDS))));

        distributeCards(cardDis);

        GameRunner gameRunner = new GameRunner();
        Player winner = gameRunner.runGame(this.players, this.deck, this.game, false);
        System.out.println(winner);
        assertEquals(winner.getID(), 0);
    }

    //Testing a scenario where we a pair in one of the hands
    @Test
    public void testAppwithPair() throws Exception {
        Map<Integer, List<Card>> cardDis = new HashMap<Integer, List<Card>>();
        cardDis.put(0, new ArrayList<Card>(
                Arrays.asList(new Card(5, Suit.SPADES), new Card(11, Suit.CLUBS), new Card(12, Suit.DIAMONDS))));
        cardDis.put(1, new ArrayList<Card>(
                Arrays.asList(new Card(4, Suit.SPADES), new Card(3, Suit.CLUBS), new Card(9, Suit.DIAMONDS))));
        cardDis.put(2, new ArrayList<Card>(
                Arrays.asList(new Card(1, Suit.SPADES), new Card(5, Suit.CLUBS), new Card(8, Suit.DIAMONDS))));
        cardDis.put(3, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.CLUBS), new Card(3, Suit.HEARTS), new Card(3, Suit.DIAMONDS))));

        distributeCards(cardDis);

        GameRunner gameRunner = new GameRunner();
        Player winner = gameRunner.runGame(this.players, this.deck, this.game, false);
        System.out.println(winner);
        assertEquals(winner.getID(), 3);
    }

    //Testing a scenario where we have a high card clash
    @Test
    public void testAppwithClashingHighCard() throws Exception {
        Map<Integer, List<Card>> cardDis = new HashMap<Integer, List<Card>>();
        cardDis.put(0, new ArrayList<Card>(
                Arrays.asList(new Card(5, Suit.SPADES), new Card(11, Suit.CLUBS), new Card(12, Suit.DIAMONDS))));
        cardDis.put(1, new ArrayList<Card>(
                Arrays.asList(new Card(4, Suit.SPADES), new Card(3, Suit.CLUBS), new Card(9, Suit.DIAMONDS))));
        cardDis.put(2, new ArrayList<Card>(
                Arrays.asList(new Card(1, Suit.SPADES), new Card(5, Suit.CLUBS), new Card(8, Suit.DIAMONDS))));
        cardDis.put(3, new ArrayList<Card>(
                Arrays.asList(new Card(2, Suit.CLUBS), new Card(1, Suit.HEARTS), new Card(12, Suit.HEARTS))));

        distributeCards(cardDis);

        GameRunner gameRunner = new GameRunner();
        Player winner = gameRunner.runGame(this.players, this.deck, this.game, false);
        System.out.println(winner);

        //Either Player 0 or Player 3 can win, depending on the random draw face-off
        assertThat(winner.getID(), anyOf(equalTo(0), equalTo(3)));
    }
}
