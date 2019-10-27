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
    private Player[] players;
    PrintWriter printWriter;

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


    private void showBoard() {


        this.board = new Board(10);
        board.init();
        board.drawBoard();

        try {

            for (Player p : players) {

                printWriter = new PrintWriter(p.getPlayerSocket().getOutputStream());
                printWriter.print("HERE GOES A NAVAL BATTLE BOARD");
                printWriter.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void turn() {

    }

    private void start() {

            showBoard();
            fire(p1);
            fire(p2);
            start();
    }

    public void addPlayer(Player player) {
        p2 = player;
        players[0] = p1;
        players[1] = p2;
    }

    private void fire(Player player) {

        try {

            int[] move = player.makeMove();
            printWriter = new PrintWriter(player.getPlayerSocket().getOutputStream());
            System.out.println("p1 has played \n");
            System.out.println(board.getTile(move[0], move[1]));
            printWriter.print("your turn has finished \n");
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
