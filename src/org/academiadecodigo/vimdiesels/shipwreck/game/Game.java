package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Game {

    private boolean onHold;
    private Player p1;
    private Player p2;
    private Board board;
    private Player [] players;


    public Game(Player p1) {
        this.p1 = p1;
        this.board = new Board(20);
        onHold = true;
        this.players = new Player[2];
        System.out.println("Waiting for players to join");
    }

    public boolean isOnHold() {
        return onHold;
    }

    //we start the game and call the method start
    public void init() {
        System.out.println("GAME INIT");
        onHold = false;
        start();
    }


    private void showBoard(){

        PrintWriter printWriter;
        this.board = new Board(10);
        board.init();
        board.drawBoard();

        try {

            for (Player p: players) {

                printWriter = new PrintWriter(p.getPlayerSocket().getOutputStream());
                printWriter.print("HEREEEEEEEEEEE");
                printWriter.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void start(){

        showBoard();
        int[] move = p1.makeMove();
        board.getTile(move[0], move[1]);

    }

    public void addPlayer(Player player) {
        p2 = player;
        players [0] = p1;
        players [1] = p2;
    }
}
