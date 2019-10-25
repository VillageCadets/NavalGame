package org.academiadecodigo.vimdiesels.shipwreck;

public class Board {

    Tile[][] board;
    private int size;

    public Board(int size) {
        this.size = size;
        this.board = new Tile[size][size];
    }


    public void init() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SEA);
            }
        }
    }

    public void drawBoard() {
        System.out.println();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append("\t" + i);
        }
        System.out.print(builder.toString());
        System.out.println("\n");
        for (int i = 0; i < size; i++) {
            if (i < 10) {
                System.out.print(i + " ");
            } else {
                System.out.print(i + "");
            }
            for (int j = 0; j < size; j++) {
                this.board[i][j].draw();
            }
            System.out.println("\n");
        }
        System.out.print(" " + builder.toString());

    }


}
