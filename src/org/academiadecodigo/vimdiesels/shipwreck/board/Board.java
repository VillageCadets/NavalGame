package org.academiadecodigo.vimdiesels.shipwreck.board;

import org.academiadecodigo.vimdiesels.shipwreck.ships.Direction;
import org.academiadecodigo.vimdiesels.shipwreck.ships.OutOfBoardException;
import org.academiadecodigo.vimdiesels.shipwreck.ships.OverShipException;
import org.academiadecodigo.vimdiesels.shipwreck.ships.Ship;

public class Board {

    private Tile[][] board;
    private int size;
    private int winScore;

    public Board(int size) {
        this.size = size;
        this.board = new Tile[size][size];
    }

    public String init(boolean hidden) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.board[i][j] = new Tile(i, j, TileType.SEA);
            }
        }
        Ship ship = new Ship(4);
        for (int i = 0; i < 5; i++) {
            this.placeShip(ship);
            winScore += ship.getShipSize();
        }

        return this.drawBoard(hidden);
    }

    public String drawBoard(boolean hidden) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i == 0) {
                builder.append("    " + i);
                continue;
            }
            builder.append("   " + i);
        }
        builder.append("\n");

        for (int i = 0; i < size; i++) {
            if (i < 10) {
                builder.append(i + " ");
            } else {
                System.out.print(i + "");
            }
            for (int j = 0; j < size; j++) {
                this.board[i][j].draw(hidden);
                builder.append(this.board[i][j].toString());
            }
            builder.append("\n");
        }
        return builder.toString();

    }


    public void placeShip(Ship ship) {

        try {
            ship.placeShip(this, this.getTile((int) (Math.random() * (size - 2)), (int) (Math.random() * (size - 2))),
                    Direction.values()[(int) (Math.random() * (Direction.values().length))]);

        } catch (OverShipException e) {
            e.printStackTrace();
        } catch (OutOfBoardException e) {
            e.printStackTrace();
        }

    }

    public int getWinScore() {
        return winScore;
    }

    public int getSize() {
        return size;
    }

    public Tile getTile(int col, int row) {

        return board[col][row];

    }

}
