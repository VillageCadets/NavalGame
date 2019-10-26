package org.academiadecodigo.vimdiesels.shipwreck.board;

import org.academiadecodigo.vimdiesels.shipwreck.ships.Direction;
import org.academiadecodigo.vimdiesels.shipwreck.ships.OutOfBoardException;
import org.academiadecodigo.vimdiesels.shipwreck.ships.OverShipException;
import org.academiadecodigo.vimdiesels.shipwreck.ships.Ship;

public class Board {

    private Tile[][] board;
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


    public void placeShip(Ship ship){

        try {
                ship.placeShip(this, this.getTile((int) (Math.random() * (size - 2)), (int) (Math.random() * (size - 2))),
                        Direction.values()[(int) (Math.random() * (Direction.values().length))]);

        } catch (OverShipException e) {
            e.printStackTrace();
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

    }


    public int getSize() {
        return size;
    }

    public Tile getTile(int col, int row) {

        return board[col][row];

    }

}
