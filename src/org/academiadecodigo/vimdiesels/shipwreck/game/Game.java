package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;

public class Game {

    private boolean onHold;
    private Player p1;
    private Player p2;
    private Board board;

    public Game(Player p1) {
        this.p1 = p1;
        this.board = new Board(20);
        onHold = true;
        System.out.println("Waiting for players to join");
    }

    public boolean isOnHold() {
        return onHold;
    }

    public void init() {
        System.out.println("GAME INIT");
        onHold = false;
        start();
    }


    private void showBoard(){
        this.board = new Board(10);
        board.init();
        board.drawBoard();
    }

    private void start(){

        showBoard();
        int[] move = p1.makeMove();
        board.getTile(move[0], move[1]);

    }

    public void addPlayer(Player player) {
        p2 = player;
    }
}
