package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;

public class Game {

    private boolean isStarted;
    private Player p1;
    private Player p2;
    private Board board;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.board = new Board(20);
    }

    private void start(){

    }
}
