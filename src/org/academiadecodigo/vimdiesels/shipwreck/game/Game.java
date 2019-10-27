package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.vimdiesels.shipwreck.board.Board;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;
import org.academiadecodigo.vimdiesels.shipwreck.board.TileType;
import org.academiadecodigo.vimdiesels.shipwreck.ships.Ship;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Game {

    private boolean onHold;
    private Player p1;
    private Player p2;
    private Board p1Board;
    private Board p2Board;
    private Player[] players;
    PrintWriter printWriter;

    public Game(Player p1) {
        this.p1 = p1;
        this.p1Board = new Board(10);
        this.p2Board = new Board(10);
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


    private String showBoard() {

        p1Board.init();
        String p1BoardString = p1Board.drawBoard();
        p2Board.init();
        String p2BoardString = p2Board.drawBoard();

        try {

            for (Player p : players) {

                printWriter = new PrintWriter(p.getPlayerSocket().getOutputStream());
                printWriter.print("HERE GOES A NAVAL BATTLE BOARD \n");
                printWriter.print("P1 Board \n");
                printWriter.println(p1BoardString);
                printWriter.print("P2 Board \n");
                printWriter.println(p2BoardString);
                printWriter.flush();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return p1BoardString;
    }

    private String boardShow(String board) {
        for (Player p : players) {

            try {
                printWriter = new PrintWriter(p.getPlayerSocket().getOutputStream());
                printWriter.println(board);
                printWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        return board;

    }

    private void turn() {

    }

    private void  start() {


        showBoard();


        while (p1.getShotHit() < this.p2Board.getWinScore() && p2.getShotHit() < this.p1Board.getWinScore()) {

            fireP1(p1,this.p1Board, this.p2Board);
            fireP2(p2,this.p2Board, this.p1Board);

        }
    }

    public void addPlayer(Player player) {
        p2 = player;
        players[0] = p1;
        players[1] = p2;
    }

    private void fireP1(Player player, Board boardP1,Board boardP2) {

        try {

            int[] move = player.makeMove();
            printWriter = new PrintWriter(player.getPlayerSocket().getOutputStream());
            System.out.println("p1 has played \n");
            System.out.println(boardP2.getTile(move[0], move[1]));

            String boardAfterShot = shotCheck(move[0], move[1], boardP2, p1);

            printWriter.print("your turn has finished \n");
            printWriter.print("Enemy Board \n");
            printWriter.print(boardAfterShot);
            printWriter.print("Your Board \n");
            printWriter.print(boardP1.drawBoard());
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void fireP2(Player player, Board boardP2, Board boardP1) {

        try {

            int[] move = player.makeMove();
            printWriter = new PrintWriter(player.getPlayerSocket().getOutputStream());
            System.out.println("p1 has played \n");
            System.out.println(boardP1.getTile(move[0], move[1]));
            String boardAfterShot = shotCheck(move[0], move[1], boardP1, p2);
            printWriter.print("your turn has finished \n");
            printWriter.print("Enemy Board \n");
            printWriter.print(boardAfterShot);
            printWriter.print("Your Board \n");
            printWriter.print(boardP2.drawBoard());
            printWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String shotCheck(int col, int row, Board board,Player player) {
        Tile tile = board.getTile(col, row);
        if (tile.getType().equals(TileType.SHIP)) {

            tile.setType(TileType.HIT);
            player.hit();

        }
        if (tile.getType().equals(TileType.SEA)) {

            tile.setType(TileType.MISS);
        }

        return board.drawBoard();
    }


}
