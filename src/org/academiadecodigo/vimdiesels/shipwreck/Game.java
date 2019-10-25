package org.academiadecodigo.vimdiesels.shipwreck;

public class Game {

    private boolean isStarted;
    private Player p1;
    private Player p2;
    private Board board;

    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
       // this.board = new Board();
    }
}
